package interfazusuario;
import interfazusuario.menu.Menu;
import cine.ButacasContiguas;
import cine.Cine;


public class VentanillaVirtualUsuario {
	private int sala, sesion, fila, columna;
	private int id;
	private Cine cine;
	private boolean sesionLlena;
	private int noButacas;
	private char respuesta;
	private Menu menuPrincipal;

	private final String[] opcionesMenuPrincipal = {"Comprar Entrada", "Recoger Entrada",
			"Consultar Estado Sesión", "Comprar con recomendación de butacas", "Salir"};
	private final String[] opcionesObligatoriasMenuPrincipal = {"Comprar Entrada",
			"Consultar Estado Sesión", "Salir"};
	private String operacion;
	// indica si trabajamos con un menu completo 
	// o solo con las operaciones obligatorias
	private boolean versionCompleta;
		
	
	public VentanillaVirtualUsuario(Cine cine, boolean versionCompleta) {		
		this.versionCompleta = versionCompleta;
		this.cine = cine;
		if (versionCompleta)
			this.menuPrincipal = new Menu(opcionesMenuPrincipal);
		else
			this.menuPrincipal = new Menu(opcionesObligatoriasMenuPrincipal);
	}

	/** pedimos una sala y una sesion validas al usuario
	 se guardan en los atributos sala y sesion
	*/
	private void pedirSalaSesion(){
		Menu menu = new Menu(cine.getPeliculas());
		sala = Integer.valueOf(menu.activar());
		String[] sesionesDisponibles = cine.getHorasDeSesionesDeSala(sala);
		menu = new Menu(sesionesDisponibles);
		sesion = Integer.valueOf(menu.activar());
	}

	/** mostramos en la consola el estado dado de una sesion
	 * 
	 */
	public void mostrarEstadoSesion(char[][] estadoSesion){	
		System.out.print("\n\t");
		for(int i=0; i<estadoSesion[0].length; i++)
			System.out.print((i+1) + "\t");
		System.out.print("\n\n");
		for(int i=0; i<estadoSesion.length; i++){
			System.out.print((i+1) + "\t");
			for(char butaca: estadoSesion[i])
				System.out.print(butaca + "\t");
			System.out.println();
		}	
		System.out.println();
	}

	/**
	 * pedimos una sala, una sesion y una butaca validas al usuario
	 * se guardan en los atributos sala, sesion y (fila, columna)
	 */
	public void pedirDatosCompra() {
		sesionLlena = false;
		this.pedirSalaSesion();
		if (cine.getButacasDisponiblesSesion(sala, sesion) == 0)
			sesionLlena = true;
		else {
			char[][] estadoSesion = cine.getEstadoSesion(sala, sesion);
			this.mostrarEstadoSesion(estadoSesion);

			
			LecturaTeclado teclado = new LecturaTeclado();

			boolean sigue;
			do {
				sigue = false;				
				fila=teclado.leerNatural("Elige una fila:");				
				columna=teclado.leerNatural("Elige una columna:");	
				// comprobamos si la fila y la columna existen en la sala
				if(fila < 0 || fila > estadoSesion.length ||
						columna < 0 || columna > estadoSesion[0].length) {
					System.out.println("La butaca seleccionada "
							+ "no existe, elige otra");
					sigue = true;
				} else
					// comprobamos si la butaca esta ya vendida
					if(estadoSesion[fila-1][columna-1] == '#'){
						System.out.println("La butaca seleccionada "
								+ "está ocupada, elige otra");
						sigue = true;
					}

			} while(sigue);		
		}
	}

	/** pedimos una sala y una sesion validas al usuario
	 se guardan en los atributos sala y sesion
	*/
	public void pedirDatosConsultaSesion() {
		this.pedirSalaSesion();				
	}

	public int getSala() {
		return sala;
	}

	public int getSesion() {
		return sesion;
	}

	public int getFila() {
		return fila;
	}

	public int getColumna() {
		return columna;
	}

	public int getId() {
		return id;
	}

	public int getNoButacas() {
		return noButacas;
	}
	
	/** pedimos una sala y una sesion validas al usuario, y un id de compra
	 se guardan en los atributos id, sala y sesion
	*/
	public void pedirDatosRecogida() {
		this.pedirSalaSesion();
		
		LecturaTeclado teclado = new LecturaTeclado();
		
		id = teclado.leerNatural("Introduce un id de venta correcto:"); 		

	}

	public boolean getSesionLlena() {
		// TODO Auto-generated method stub
		return sesionLlena;
	}

	/** pedimos una sala y una sesion validas al usuario, y un no. de butacas contiguas
	 se guardan en los atributos noButacas, sala y sesion
	*/
	public void pedirDatosRecomendacion() {
		this.pedirSalaSesion();
		
		LecturaTeclado teclado = new LecturaTeclado();
		do {			
			noButacas = teclado.leerNatural("Introduce el no. de butacas contiguas:");		
		} while (noButacas <= 0);
	}

	/** 
	 * pedimos al usuario que confirme la compra de varias butacas contiguas	
	*/
	public void pedirConfirmacionCompraRecomendacion(ButacasContiguas butacas) {
		char[][] estadoSesion = cine.getEstadoSesion(
				sala, 
				sesion);
		// marcamos las butacas seleccionadas por el usuario en el estado actual
		// de la sesion
		for(int i=0; i<butacas.getNoButacas(); i++)
			estadoSesion[butacas.getFila()-1][butacas.getColumna()+i-1] = 'R';
		
		// mostramos la seleccion de butacas en consola
		mostrarEstadoSesion(estadoSesion);
		System.out.println("Se recomiendan las butacas marcadas con R");
		
		LecturaTeclado teclado = new LecturaTeclado();		
		respuesta = teclado.leerCaracter("Aceptas la compra? (s/n)");					
	}

	public char getRespuesta() {
		// TODO Auto-generated method stub
		return respuesta;
	}

	/**
	 *  pedimos al usuario que introduzca un numero de operacion
	 */
	public void pedirOperacion() {
		// TODO Auto-generated method stub
		operacion = this.menuPrincipal.activar();
		if (!this.versionCompleta)
			if (operacion.equals("3"))
				operacion = "5";
			else if (operacion.equals("2"))
				operacion = "3";
	}

	public String getOperacionSeleccionada() {
		// TODO Auto-generated method stub
		return operacion;
	}

}
