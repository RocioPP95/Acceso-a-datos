package ceu.dam.ad.api;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ceu.dam.ad.ApiSpringPostresApplication;
import ceu.dam.ad.model.Postre;

@RestController
@RequestMapping("/postre")
public class PostreService {

	private final ApiSpringPostresApplication apiSpringPostresApplication;

	PostreService(ApiSpringPostresApplication apiSpringPostresApplication) {
		this.apiSpringPostresApplication = apiSpringPostresApplication;
	}

	@GetMapping("/{id}")
	public Postre getById(@PathVariable Long id) {
		return new Postre(id, "chocolate", new BigDecimal(300), new BigDecimal(200), "tarta choco", false);

	}

	@PostMapping("")

	public Postre create(@RequestBody Postre postre) {
		postre.setId(877L);
		return postre;
	}

	@GetMapping("")
	public List<Postre> search(@RequestParam(required = false) String sabor,
			@RequestParam(required = false) String nombre) {
		List<Postre> postres = new ArrayList<>();
		for (int i = 0; i < 7; i++) {
			postres.add(new Postre(i + 100L, sabor, new BigDecimal(300), new BigDecimal(200), nombre, false));

		}

		return postres;
	}

	@DeleteMapping("/{id}")
	public void deleteId(@PathVariable Long id) {
		System.out.println("Borrado");
	}

	@PutMapping("/{id}")
	public Postre actualizar(@PathVariable Long id, @RequestBody Postre postre) {
		System.out.println("postre con id" + id + "actualizado");
		postre.setId(id);
		return postre;
	}

}
