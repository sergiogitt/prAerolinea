package pruebas;



import java.util.List;

import controladores.ControladorAvion;
import controladores.ControladorCompanya;
import entidades.Avion;
import entidades.Companyia;

public class PruebaControladorAvion {
	public static void main(String[] args) {
		ControladorCompanya cv = new ControladorCompanya();

		// Se obtienen todas las instancias
		List <Companyia>  listaAviones = cv.findAll();

		// Se imprime la lista
		System.out.println("Todas las entidades ------------ ");
		for (Companyia a : listaAviones) {
			System.out.println(a);
		}

		// Se obtiene una entidad
		System.out.println("Buscar Vehiculo de matr�cula 0034AAB ------------ ");
		Companyia aux = cv.findByNombre("Iberia");
		System.out.println(aux);

		// Creaci�n de una entidad
		Companyia v = new Companyia();
		v.setCodcompanyia(4);
		v.setDireccionSucursalCentral("Mi casa");
		v.setCorreroContacto("contacto@barato.es");
		v.setNomcompanyia("Barato");
		v.setTelefonoContacto("345673456");
		cv.createCompanyia(v); // Si est� creada lanzar� una excepci�n
/*
		// Se obtienen todas las instancias
		listaVehiculos = cv.findAll();
		System.out.println("Todas las entidades despu�s de crear una ------------ ");
		listaVehiculos.forEach(System.out::println);

		// Se modifica el precio del veh�culo id = 1
		Avion vehicModificar = cv.findByPK(1);
		if (vehicModificar != null) {
			vehicModificar.setPrecio(24.2);
			cv.modifyVehiculo(vehicModificar);
		}

		// Se obtienen todas las instancias
		listaVehiculos = cv.findAll();
		System.out.println("Todas las entidades despu�s de modificar una ------------ ");
		listaVehiculos.forEach(System.out::println);

		// Borrado del veh�culo de matr�cula 0998FRR
		Avion vehicBorrar = cv.findByMatricula("0998FRR");
		cv.borrarVehiculo(vehicBorrar);

		// Se obtienen todas las instancias
		listaVehiculos = cv.findAll();
		System.out.println("Todas las entidades despu�s de borrar una ------------ ");
		listaVehiculos.forEach(System.out::println);

		// Se obtienen todas las instancias de la marca Seat
		listaVehiculos = cv.findByMarca("Seat");
		System.out.println("Todas las entidades Seat ------------ ");
		listaVehiculos.forEach(System.out::println);
*/
	}

}
