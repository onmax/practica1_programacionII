package test;
import interfazusuario.VentanillaVirtualUsuario;
import cine.ButacasContiguas;
import cine.Sesion;

/**
 * Este programa de prueba no es exhaustivo, solo ilustrativo de
 * como se pueden probar directamente los metodos de la practica.
 * Por tanto, el alumno deberia ampliar este main con el fin de 
 * asegurarse de la correccion de su codigo.
 * 
 * @author jramirez
 *
 */

public class TestVentaEntradasSesion {

	public static void main(String[] args) {
		// creamos una sesion para probar las operaciones
		
		Sesion sesion = new Sesion("11:00", 9, 5);
		Sesion sesion2 = new Sesion("16:00", 6,5);
		System.out.println("La hora de la sesion es:" + sesion.getHora());
		
		System.out.println(sesion.equals(new Sesion("11:00", 3, 4))); // true
		
		System.out.println(!sesion.equals(new Sesion("10:00", 3, 4))); // true
		
		// necesitamos la ventanilla para mostrar el estado de la sesion
		VentanillaVirtualUsuario ventanilla = new VentanillaVirtualUsuario(null, true);
	
		sesion.comprarEntrada(2, 1);	
		sesion.comprarEntrada(9, 3);

		int idVenta1 = sesion.getIdEntrada(9, 3);
		int idVenta2 = sesion.getIdEntrada(9, 3);
		int idVenta3 = sesion.getIdEntrada(9, 3);
		if(idVenta1 == 1 && idVenta2 == 2 && idVenta3==0)
		System.out.println("El id esta feten"); 
		else
		System.out.println("El id no esta feten" + idVenta1);

		ButacasContiguas butacas = 
				sesion.recomendarButacasContiguas(1);

		sesion.comprarEntradasRecomendadas(butacas);

		int idVentaA = sesion.getIdEntrada(butacas.getFila(), 
				butacas.getColumna());

		sesion.recogerEntradas(idVenta1);

		ventanilla.mostrarEstadoSesion(
				sesion.getEstadoSesion());

		System.out.println("No. de butacas disponibles: " + 
				sesion.getButacasDisponiblesSesion());

		System.out.println("Tickets :" + 
				sesion.recogerEntradas(idVenta1));
		
		System.out.println("Tickets recomendados:" + 
				sesion.recogerEntradas(idVenta1));		
	}

}
