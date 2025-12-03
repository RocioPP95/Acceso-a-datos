package ceu.dam.ad.ejerciciosTema2.xml.ejercicio04.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ceu.dam.ad.ejerciciosTema2.xml.ejercicio04.modelo.Edicion;
import ceu.dam.ad.ejerciciosTema2.xml.ejercicio04.modelo.Libro;
import ceu.dam.ad.ejerciciosTema2.xml.ejercicio04.service.LibroService;
import ceu.dam.ad.ejerciciosTema2.xml.ejercicio04.service.LibroServiceImpl;
import ceu.dam.ad.ejerciciosTema2.xml.ejercicio04.service.LibroXmlException;

public class Test {
	  public static void main(String[] args) {

	        List<Libro> libros = createLibrosDeEjemplo();
	        LibroService service = new LibroServiceImpl();

	        try {
	            service.generarXml("c:/temporal/libros.xml", libros);
	        } catch (LibroXmlException e) {
	            e.printStackTrace();
	        }
	    }

	    public static List<Libro> createLibrosDeEjemplo() {

	        List<Libro> libros = new ArrayList<>();

	        // ----- LIBRO 1 -----
	        Libro l1 = new Libro();
	        l1.setIsbn(405297764); // si en tu modelo es String, usa: l1.setIsbn("405297764");
	        l1.setTitulo("mxqzi cpavqs");
	        l1.setAutores(Arrays.asList("hce icpoeomqjq"));

	        List<Edicion> ediciones1 = new ArrayList<>();
	        Edicion l1e1 = new Edicion();
	        l1e1.setAño(1969);  // o setAnio(1969);
	        l1e1.setEditorial("qpce qlpwfiawc ntisjojos");
	        ediciones1.add(l1e1);

	        Edicion l1e2 = new Edicion();
	        l1e2.setAño(1965);
	        l1e2.setEditorial("esagghkk kplevsmpgn webkpczh");
	        ediciones1.add(l1e2);

	        l1.setEdiciones(ediciones1);
	        libros.add(l1);

	        // ----- LIBRO 2 -----
	        Libro l2 = new Libro();
	        l2.setIsbn(353157915);
	        l2.setTitulo("auf ozgni dmmdc");
	        l2.setAutores(Arrays.asList("enlrp pwbowgh"));

	        List<Edicion> ediciones2 = new ArrayList<>();
	        Edicion l2e1 = new Edicion();
	        l2e1.setAño(1958);
	        l2e1.setEditorial("kgxkc eioiflpofo xzwmltxiw");
	        ediciones2.add(l2e1);

	        l2.setEdiciones(ediciones2);
	        libros.add(l2);

	        // ----- LIBRO 3 -----
	        Libro l3 = new Libro();
	        l3.setIsbn(280516166);
	        l3.setTitulo("uxwrexlae ebefrvqvb");
	        l3.setAutores(Arrays.asList("hjxufk gdknwt", "woungd tytmdukjbi"));

	        List<Edicion> ediciones3 = new ArrayList<>();
	        Edicion l3e1 = new Edicion();
	        l3e1.setAño(1991);
	        l3e1.setEditorial("adt myjlxww zqzmpmb");
	        ediciones3.add(l3e1);

	        l3.setEdiciones(ediciones3);
	        libros.add(l3);

	        // ----- LIBRO 4 -----
	        Libro l4 = new Libro();
	        l4.setIsbn(796113700);
	        l4.setTitulo("jzrdgc zbimxbie ktp");
	        l4.setAutores(Arrays.asList("xdvkhmtles vodxgl", "rekb uzsk"));

	        List<Edicion> ediciones4 = new ArrayList<>();
	        Edicion l4e1 = new Edicion();
	        l4e1.setAño(1964);
	        l4e1.setEditorial("bwruhflni avmlw ovamvlugj");
	        ediciones4.add(l4e1);

	        Edicion l4e2 = new Edicion();
	        l4e2.setAño(1992);
	        l4e2.setEditorial("ciinr iwpfl");
	        ediciones4.add(l4e2);

	        l4.setEdiciones(ediciones4);
	        libros.add(l4);

	        // ----- LIBRO 5 -----
	        Libro l5 = new Libro();
	        l5.setIsbn(764885581);
	        l5.setTitulo("najobmtdkl rmmewtv");
	        l5.setAutores(Arrays.asList("bzmjwi tdgppavwbp"));

	        List<Edicion> ediciones5 = new ArrayList<>();
	        Edicion l5e1 = new Edicion();
	        l5e1.setAño(1961);
	        l5e1.setEditorial("igiwcch ddgcccu");
	        ediciones5.add(l5e1);

	        l5.setEdiciones(ediciones5);
	        libros.add(l5);

	        return libros;
	    }

}
