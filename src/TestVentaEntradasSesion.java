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

		System.out.println("La hora de la sesion es:" + sesion.getHora());
		
		System.out.println(sesion.equals(new Sesion("11:00", 3, 4))); // true
		
		System.out.println(sesion.equals(new Sesion("10:00", 3, 4))); // false
		
		// necesitamos la ventanilla para mostrar el estado de la sesion
		VentanillaVirtualUsuario ventanilla = new VentanillaVirtualUsuario(null, true);
	
		sesion.comprarEntrada(2, 1);	
		sesion.comprarEntrada(9, 3);

		int idVenta = sesion.getIdEntrada(9, 3);

		System.out.println("Id de venta es:" + idVenta);

		ButacasContiguas butacas = 
				sesion.recomendarButacasContiguas(1);

		sesion.comprarEntradasRecomendadas(butacas);

		int idVenta1 = sesion.getIdEntrada(butacas.getFila(), 
				butacas.getColumna());

		sesion.recogerEntradas(idVenta1);

		ventanilla.mostrarEstadoSesion(
				sesion.getEstadoSesion());

		System.out.println("No. de butacas disponibles: " + 
				sesion.getButacasDisponiblesSesion());

		System.out.println("Tickets :" + 
				sesion.recogerEntradas(idVenta));
		
		System.out.println("Tickets recomendados:" + 
				sesion.recogerEntradas(idVenta1));		
	}

}
