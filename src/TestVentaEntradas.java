import interfazusuario.VentanillaVirtualUsuario;
import cine.ButacasContiguas;
import cine.Cine;
import cine.Sala;

/**
 * Este programa de prueba no es exhaustivo, solo ilustrativo de
 * como se pueden probar directamente los metodos de la practica.
 * Por tanto, el alumno deberia ampliar este main con el fin de 
 * asegurarse de la correccion de su codigo.
 * 
 * @author jramirez
 *
 */

public class TestVentaEntradas {

	public static void main(String[] args) {
		// creamos un cine con 2 salas (con 2 sesiones) para probar las operaciones
		String[] horasSesiones = {"18:00", "22:00"};
		Sala[] salas = {new Sala("Tiburon", horasSesiones, 9, 5), 
				new Sala("Tron", horasSesiones, 2, 2)};
		Cine cine = new Cine("CinemaVintage", salas);

		cine.incluirSesion(1, "23:00");
		
		cine.borrarSesion(1, "23:00");
		
		
		// necesitamos la ventanilla para mostrar el estado de la sesion
		VentanillaVirtualUsuario ventanilla = new VentanillaVirtualUsuario(cine, true);

		for (String pelicula: cine.getPeliculas())
			System.out.println(pelicula);

		cine.comprarEntrada(1, 1, 2, 1);	
		cine.comprarEntrada(1, 1, 9, 3);

		int idVenta = cine.getIdEntrada(1, 1, 9, 3);

		System.out.println("Id de venta es:" + idVenta);

		ButacasContiguas butacas = 
				cine.recomendarButacasContiguas(4, 1, 1);

		cine.comprarEntradasRecomendadas(1, 1, butacas);

		int idVenta1 = cine.getIdEntrada(1, 1, butacas.getFila(), 
				butacas.getColumna());

		cine.recogerEntradas(idVenta1, 1, 1);

		ventanilla.mostrarEstadoSesion(
				cine.getEstadoSesion(1, 1));

		System.out.println("No. de butacas disponibles: " + 
				cine.getButacasDisponiblesSesion(1, 1));

		System.out.println("Tickets :" + 
				cine.recogerEntradas(idVenta, 1, 1));
		
		System.out.println("Tickets recomendados:" + 
				cine.recogerEntradas(idVenta1, 1, 1));		
	}

}
