package ceu.dam.ad.users.services;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ceu.dam.ad.users.dao.UserDao;
import ceu.dam.ad.users.model.User;

public class UserServiceImpl extends Service implements UserService {
	private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

	private UserDao dao;

	public UserServiceImpl() {
		dao = new UserDao();
	}

	/**
	 * Recibe un usuario que trae indicado su username, email y password (sin
	 * cifrar). El servicio tendrá que: 1. Verificar que no existe usuario con ese
	 * email ni ese username. En caso contrario, lanzar DuplicateUserException 2.
	 * Registrar el usuario en BBDD completando su fecha de alta y cifrando su
	 * password con SHA3-256 3. Devolver el usuario con todos sus datos (incluyendo
	 * el ID) 4. Si hay algún error, lanzará UserException con el origen
	 */

	@Override
	public User createUser(User user) throws DuplicateUserException, UserException {
		log.debug("Creando nuevo usuario: " + user);
		dao = new UserDao();

		try (Connection conn = abrirConexionSakila()) {

			if (dao.getByEmail(conn, user.getEmail()) != null || dao.getByUserName(conn, user.getUsername()) != null) {
				log.debug("ya existe usuario");
				throw new DuplicateUserException("Usuario ya existe");
			}
			user.setCreatedDate(LocalDate.now());
			user.setLastLoginDate(LocalDate.now());

			String passCifrada = DigestUtils.sha3_256Hex(user.getPassword());
			user.setPassword(passCifrada);

			Long id = dao.insert(conn, user);
			user.setId(id);
			return user;
		} catch (SQLException e) {
			log.error("Error registrando usuario", e);
			throw new UserException("error registrando al usuario", e);
		}

	}

	/**
	 * Recibe el id de un usuario, el password antiguo y el nuevo. Los dos sin
	 * cifrar. El servicio tendrá que: 1. Si el usuario no existe con ese ID, lanzar
	 * UserNotFoundException 2. Verificar que la nueva password no es igual a la
	 * antigua. Si lo es, lanzar UserUnauthorizedException 3. Verificar que la
	 * password antigua es correcta. Si no lo es, lanzar UserUnauthorizedException
	 * 4. Actualizar el nuevo password en el usuario cifrándolo previamente. 5. Si
	 * hay algún error, lanzará UserException con el origen
	 */
	@Override
	public void changePassword(Long idUser, String oldPassword, String newPassword)
			throws UserNotFoundException, UserUnauthorizedException, UserException {
		dao = new UserDao();
		try (Connection conn = abrirConexionSakila()) {
			User user = dao.getById(conn, idUser);
			if (user == null) {
				// advertencia
				log.warn("No existe usuario con el ID indicado ");
				throw new UserNotFoundException("No existe usuario con el ID indicado ");

			}

			if (oldPassword.equals(newPassword)) {
				log.debug("La password no puede ser igual a la antigua");
				throw new UserUnauthorizedException("La password no puede ser igual a la antigua");

			}

			String passwordCifrada = DigestUtils.sha3_256Hex(user.getPassword());

			if (!user.getPassword().equals(passwordCifrada)) {
				log.debug("la pasword antigua no es correcta");
				throw new UserUnauthorizedException("la pasword antigua no es correcta");

			}
			String passwordNewCifrada = DigestUtils.sha3_256Hex((newPassword));
			user.setPassword(passwordNewCifrada);
			dao.update(conn, user);

		} catch (SQLException e) {
			log.error("Error cambiando usuario", e);
			throw new UserException("error cambiando al usuario", e);

		}

	}

	/**
	 * Recibe un login que puede ser un username o un email, y el password sin
	 * cifrar. El servicio tendrá que: 1. Verificar que existe algún usuario con ese
	 * username o email. Si no es así, lanzar UserNotFoundException 2. Verificar que
	 * password es correcta. Si lo es, lanzar UserUnauthorizedException 3.
	 * Actualizamos fecha del último login. Si hay algún error aquí, registramos en
	 * el log, pero continuamos. 4. Devolver el usuario con todos sus datos que ha
	 * realizado el login. 5. Si hay algún error, lanzará UserException con el
	 * origen
	 */
	@Override
	public User login(String login, String password)
			throws UserNotFoundException, UserUnauthorizedException, UserException {

		log.debug("Realizando login con usuario " + login);
		try (Connection conn = abrirConexionSakila()) {
			User user = dao.getByUserName(conn, login);
			if (user == null) {
				user = dao.getByEmail(conn, login);
				if (user == null) {
					throw new UserNotFoundException("No existe el usuario indicado");
				}
			}
			String passwordCifrada = DigestUtils.sha3_256Hex(password);
			if (!passwordCifrada.equals(user.getPassword())) {
				throw new UserUnauthorizedException("Password incorrecta");
			}
			try {
				user.setLastLoginDate(LocalDate.now());
				Integer updates = dao.update(conn, user);
				if (updates == 0) {
					log.error("Error actualizando fecha último login");
				}
			} catch (Exception e) {
				log.error("Error actualizando fecha último login", e);
			}
			return user;

		} catch (SQLException e) {
			log.error("Error haciendo login", e);
			throw new UserException("Error haciendo login", e);
		}

	}

	/**
	 * Recibe el id de un usuario. El servicio tendrá que: 1. Si el usuario no
	 * existe con ese ID, lanzar UserNotFoundException 2. Devolver los datos
	 * completos del usuario 3. Si hay algún error, lanzará UserException con el
	 * origen
	 */

	@Override
	public User getUser(Long idUser) throws UserNotFoundException, UserException {
		log.debug("Consultando usuario con id " + idUser);
		try (Connection conn = abrirConexionSakila()) {
			User user = dao.getById(conn, idUser);
			if (user == null) {
				log.warn("No existe usuario con el ID indicado");
				throw new UserNotFoundException("No existe usuario con el ID indicado");
			}
			return user;

		} catch (SQLException e) {
			log.error("Error consultando usuario", e);
			throw new UserException("Error consultando usuario", e);
		}
	}

}
