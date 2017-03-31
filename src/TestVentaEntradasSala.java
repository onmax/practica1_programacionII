import interfazusuario.VentanillaVirtualUsuario;
import cine.ButacasContiguas;
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

public class TestVentaEntradasSala {

	public static void main(String[] args) {
		// creamos un sala con 2 salas (con 2 sesiones) para probar las operaciones
		String[] horasSesiones = {"18:00", "22:00"};
		Sala sala = new Sala("Tiburon", horasSesiones, 9, 5);
		
		sala.incluirSesion("20:00");		
		
		for (String hora: sala.getHorasDeSesionesDeSala())
			System.out.println(hora);
		
		sala.borrarSesion("15:00"); // no hace nada
		sala.borrarSesion("20:00");
		
		for (String hora: sala.getHorasDeSesionesDeSala())
			System.out.println(hora);

		// necesitamos la ventanilla para mostrar el estado de la sesion
		VentanillaVirtualUsuario ventanilla = new VentanillaVirtualUsuario(null, true);
	

		sala.comprarEntrada(1, 2, 1);	
		sala.comprarEntrada(1, 9, 3);

		int idVenta = sala.getIdEntrada(1, 9, 3);

		System.out.println("Id de venta es:" + idVenta);

		ButacasContiguas butacas = 
				sala.recomendarButacasContiguas(1, 1);

		sala.comprarEntradasRecomendadas(1, butacas);

		int idVenta1 = sala.getIdEntrada(1, butacas.getFila(), 
				butacas.getColumna());

		sala.recogerEntradas(idVenta1, 1);

		ventanilla.mostrarEstadoSesion(
				sala.getEstadoSesion(1));

		System.out.println("No. de butacas disponibles: " + 
				sala.getButacasDisponiblesSesion(1));

		System.out.println("Tickets :" + 
				sala.recogerEntradas(idVenta, 1));
		
		System.out.println("Tickets recomendados:" + 
				sala.recogerEntradas(idVenta1, 1));		
	}

}
