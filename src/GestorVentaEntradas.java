import interfazusuario.LecturaTeclado;
import interfazusuario.VentanillaVirtualAdministrador;
import interfazusuario.VentanillaVirtualUsuario;
import interfazusuario.menu.Menu;
import cine.ButacasContiguas;
import cine.Cine;
import cine.Sala;

public class GestorVentaEntradas {		

	private static void gestionarMenuAdm(VentanillaVirtualAdministrador ventanilla, Cine cine) {
		final String INCLUIR = "1";
		final String BORRAR = "2";
		final String SALIR = "3";

		boolean seguir = true;
		do {
			// Mostramos el menu principal y pedimos una operacion al usuario
			ventanilla.pedirOperacion();
			String opcion = ventanilla.getOperacionSeleccionada();

			switch (opcion){					

			case INCLUIR :
				ventanilla.pedirDatosIncluirSesion();
				cine.incluirSesion(ventanilla.getSala(), ventanilla.getHora());
				break;

			case BORRAR : 
				ventanilla.pedirDatosBorrarSesion();
				cine.borrarSesion(ventanilla.getSala(), ventanilla.getHora());
				break;

			case SALIR : seguir = false;
			}
		} while(seguir);

		
	}

	private static void gestionarMenuUsuario(VentanillaVirtualUsuario ventanilla, Cine cine){
		final String COMPRAR = "1";
		final String RECOGER = "2";
		final String CONSULTAR_SESION = "3";
		final String COMPRAR_RECOMENDACION = "4";
		final String SALIR = "5";

		boolean seguir = true;
		do {
			// Mostramos el menu principal y pedimos una operacion al usuario
			ventanilla.pedirOperacion();
			String opcion = ventanilla.getOperacionSeleccionada();

			switch (opcion){
			case COMPRAR : 
				// pedimos al usuario la sala, sesion y la butaca
				// solo se admiten butacas libres
				// estos datos quedan almacenados en el objeto ventanilla
				ventanilla.pedirDatosCompra();
				// comprobamos si la sesion de la sala seleccionada esta llena
				if (!ventanilla.getSesionLlena()){
					cine.comprarEntrada(	ventanilla.getSala(), 
							ventanilla.getSesion(), 
							ventanilla.getFila(), 
							ventanilla.getColumna());
					System.out.println("El id de la venta es " + 
							cine.getIdEntrada(ventanilla.getSala(), 
									ventanilla.getSesion(), 
									ventanilla.getFila(), 
									ventanilla.getColumna()));
				} else
					System.out.println("La sesion seleccionada está llena");
				break;

			case RECOGER :
				// pedimos al usuario el id de la compra, la sala y la sesion
				// estos datos quedan almacenados en el objeto ventanilla
				ventanilla.pedirDatosRecogida();
				String entradas = cine.recogerEntradas(ventanilla.getId(), 
						ventanilla.getSala(), 
						ventanilla.getSesion());
				// comprobamos si existe el id de compra dado en la sala y
				// la sesion dadas
				if (entradas == null)
					System.out.println("Los datos de la venta son incorrectos");
				else
					System.out.println("Tus entradas son: " + entradas);
				break;					

			case CONSULTAR_SESION :
				// pedimos al usuario la sala y la sesion
				// estos datos quedan almacenados en el objeto ventanilla
				ventanilla.pedirDatosConsultaSesion(); 
				char[][] estadoSesion = cine.getEstadoSesion(
						ventanilla.getSala(), 
						ventanilla.getSesion());
				ventanilla.mostrarEstadoSesion(estadoSesion);			
				break;

			case COMPRAR_RECOMENDACION : 
				// pedimos al usuario el no. de butacas contiguas, la sala y la sesion
				// estos datos quedan almacenados en el objeto ventanilla
				ventanilla.pedirDatosRecomendacion();
				ButacasContiguas butacas = cine.recomendarButacasContiguas(
						ventanilla.getNoButacas(),
						ventanilla.getSala(), 
						ventanilla.getSesion());
				// comprobamos si existen suficientes butacas contiguas
				// en la sala y sesion dadas
				if (butacas != null){
					ventanilla.pedirConfirmacionCompraRecomendacion(butacas);
					// pedimos al usuario que confirme si quiere las butacas recomendadas
					// la respuesta queda guardada en el objeto ventanilla
					if (ventanilla.getRespuesta() == 's' || ventanilla.getRespuesta() == 'S'){
						cine.comprarEntradasRecomendadas(ventanilla.getSala(), 
								ventanilla.getSesion(), butacas);
						System.out.println("El id de la venta es " + 
								cine.getIdEntrada(ventanilla.getSala(), 
										ventanilla.getSesion(), 
										butacas.getFila(), 
										butacas.getColumna()));
					} else
						System.out.println("Has descartado la recomendación");

				} else
					System.out.println("No hay tantas butacas disponibles contiguas");

				break;

			case SALIR : seguir = false;
			}
		} while(seguir);

	}


	public static void main(String[] args) {		

		// creamos un cine con 2 salas (con 2 sesiones) para probar las operaciones
		String[] horasSesiones = {"18:00", "22:00"};
		Sala[] salas = {new Sala("Tiburon", horasSesiones, 9, 5), 
				new Sala("Tron", horasSesiones, 2, 2)};
		Cine cine = new Cine("CinemaVintage", salas);	

		// este programa puede utilizar solo las operaciones obligatorias o todas
		LecturaTeclado teclado = new LecturaTeclado();

		char key = teclado.leerCaracter("Deseas probar solo las operaciones obligatorias? (s/n)");

		VentanillaVirtualUsuario ventanillaUsuario;
		VentanillaVirtualAdministrador ventanillaAdm;
		
		if (key == 's' || key == 'S') {
			// creamos una ventanilla solo para las operaciones obligatorias
			ventanillaUsuario = new VentanillaVirtualUsuario(cine, false);
			ventanillaAdm = 
			        new VentanillaVirtualAdministrador(cine, false);
		} else {
			// creamos una ventanilla para todas las operaciones
			ventanillaUsuario = new VentanillaVirtualUsuario(cine, true);
			ventanillaAdm = 
                    new VentanillaVirtualAdministrador(cine, true);			
		}		

		String[] menusDisponibles = {"Operaciones de Usuario", "Operaciones de Administrador", "Salir"};
		Menu menu = new Menu(menusDisponibles);

		int opcion;
		do {
			opcion = Integer.valueOf(menu.activar());

			if (opcion == 1)		
				gestionarMenuUsuario(ventanillaUsuario, cine);
			else if (opcion == 2)
				gestionarMenuAdm(ventanillaAdm, cine);
		} while (opcion != 3);


	}




}
