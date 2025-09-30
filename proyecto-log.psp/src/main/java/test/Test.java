package test;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Test {

	private static final Logger log = LoggerFactory.getLogger(Test.class);

	public static void main(String[] args) {
//		log.info("prueba");
//
//		try {
//			Integer x = 4 / 0;
//		} catch (Exception e) {
//			log.error("Error dividendo", e);
//		}
//		

		String password = "nachete";
		String passCifrada = DigestUtils.sha3_512Hex(password);

		System.out.println(passCifrada);
	}
}
