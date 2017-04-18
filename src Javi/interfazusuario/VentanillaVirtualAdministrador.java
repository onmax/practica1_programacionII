package interfazusuario;

import java.util.Arrays;


import cine.Cine;
import interfazusuario.menu.Menu;

public class VentanillaVirtualAdministrador {
	private int sala; 
	
	private String hora;
	
	private Menu menuPrincipal;
	private Cine cine;
	private final String[] opcionesMenuPrincipal = {"Incluir Sesion", "Borrar Sesion",
			 "Salir"};
	private final String[] opcionesMenuPrincipalObligatorias = {"Incluir Sesion",
    "Salir"};
	
	private String operacion;
	// indica si trabajamos con un menu completo 
    // o solo con las operaciones obligatorias
    private boolean versionCompleta;
	
	public VentanillaVirtualAdministrador(Cine cine, boolean versionCompleta) {		
	    this.versionCompleta = versionCompleta;
		this.cine = cine;		
		if (versionCompleta)
		    this.menuPrincipal = new Menu(opcionesMenuPrincipal);
		else
		    this.menuPrincipal = new Menu(opcionesMenuPrincipalObligatorias);
	}
	
	/**
	 *  pedimos al usuario que introduzca un numero de operacion
	 */
	public void pedirOperacion() {
		// TODO Auto-generated method stub
		operacion = this.menuPrincipal.activar();
		if (!this.versionCompleta)
            if (operacion.equals("2"))
                operacion = "3";
	}
	
	public String getOperacionSeleccionada() {
		// TODO Auto-generated method stub
		return operacion;
	}
	
	/** pedimos una sala valida al usuario
	 se guardan en el atributo sala
	*/
	private void pedirSala(){
		Menu menu = new Menu(cine.getPeliculas());
		sala = Integer.valueOf(menu.activar());		
	}
	
	/** pedimos una sesion valida al usuario
	 se guarda en el atributo sala sesion
	*/
	private void pedirSesion(){		
		String[] sesionesDisponibles = cine.getHorasDeSesionesDeSala(sala);
		Menu menu = new Menu(sesionesDisponibles);
		hora = sesionesDisponibles[Integer.valueOf(menu.activar())-1];		
	}
	
	public void pedirDatosBorrarSesion(){
		this.pedirSala();
		this.pedirSesion();		
	}
	
	public void pedirDatosIncluirSesion(){
		this.pedirSala();
		
		LecturaTeclado teclado = new LecturaTeclado();	
		String sesionesSala = Arrays.toString(cine.getHorasDeSesionesDeSala(sala));
		
		System.out.println("Las sesiones ya existentes son: " + sesionesSala);
		
		boolean seguir = true;
		do {
			hora = teclado.leerString("Introduce la hora de la sesion (HH:MM):");
			seguir = sesionesSala.contains(hora) || !hora.matches("\\d\\d:\\d\\d");
			String[] partes = hora.split(":");
			seguir = seguir || !(Integer.parseInt(partes[0]) >0 && Integer.parseInt(partes[0]) < 25 &&
					Integer.parseInt(partes[1]) >=0 && Integer.parseInt(partes[1]) < 60);
			if (seguir)
				System.out.println("Esa sesion ya existe o tiene un formato incorrecto");
		} while (seguir);		
		
	}
	
	public String getHora(){
		return hora;
	}	
	
	public int getSala(){
		return sala;
	}
}
