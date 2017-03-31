package interfazusuario.menu;

import java.util.Scanner;

/**
 * <p align="justify">False que implementa un menú en pantalla.<BR>
 * Esta clase se encarga de generar un menú por consola a partir de un
 * vector de cadenas. Por defecto a cada opción del menú se le asigna un número. 
 * Si se desea se puede indicar que utilice las letras del abecedario.</p> 
 * 
 * 
 * @author Ángel Lucas González Martínez
 * @version 0.1
 */
public class Menu {//Menu
	/**
	 * Va a contener las cadenas que representan las opciones del menú.
	 */
	private String [] opciones=null;
	/**
	 * Enumerado que definir las distintas formas de indicar cómo se selecciona una opción del menú.
	 * @author agonzalez
	 *
	 */
	public enum Numerar {NUMERO, LETRA_ABC};

	/**
	 * Atributo que indica cómo se han de numerar las opciones y que indica cuál es el tipo
	 * de valor que se espera.
	 */
	private Numerar numerar = Numerar.NUMERO;

	/**
	 * Constructor que recibe las opciones y cómo se han de numerar
	 * @param opciones es el vector de cadenas de caracteres que se utilizará en el menú
	 * @param numerar establece cómo se ha de numerar, y cual es el valor que ha de introducir el usuario para seleccionar una opción
	 */
	public Menu(String []opciones, Numerar numerar)
	{//Constructor
		copiaOpciones(opciones);
		this.numerar=numerar;
	}//Constructor

	/**
	 * Constructor que recibe sólo las opciones. Las opciones se numerarán utilizando números
	 * @param opciones es el vector de cadenas de caracteres que se utilizará en el menú
	 */
	public Menu(String [] opciones)
	{//Menu
		copiaOpciones(opciones);
	}//Menu
	/**
	 * Servicio que activa el menú y retorna una cadea de caracteres con la opción seleccionada. 
	 * @return String  con la opción seleccionada. Si el modo es númer ver Numerar.NUMERO el valor 
	 * irá de 1 a n en cambio si es letra ver Numerar.LETRA_ABC el valor irá de 'a' a 'z'
	 */
	public String activar()
	{//Activar
		String opcionSeleccionada=null;
		int nValor = -1;
		mostrarOpciones();
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		while (opcionSeleccionada==null
				|| (this.numerar==Numerar.LETRA_ABC && (opcionSeleccionada.charAt(0)<'a'
						|| opcionSeleccionada.charAt(0)>=((int)'a'+opciones.length)))
						|| (this.numerar==Numerar.NUMERO && 
						(nValor<1 ||
								nValor>opciones.length)))
		{//W
			System.out.print ("\n Seleccione una de las opciones para continuar: ");

			opcionSeleccionada=scanner.next();
			if (this.numerar==Numerar.NUMERO && opcionSeleccionada.matches("\\d+")) 
				nValor = Integer.valueOf(opcionSeleccionada);	

		}//W

		return opcionSeleccionada;
	}//Activar

	/**
	 * Servicio privado que sirve para copiar las opciones del menú que se pasan a cualquiera de los constructores dado	
	 * @param opciones contiene un vector de Strings con las opciones que va a tener el menu
	 */
	private void copiaOpciones (String []opciones)
	{//copiaOpciones
		this.opciones = new String [opciones.length];
		for (int i=0; i<opciones.length; i++)
		{//for
			this.opciones[i]=opciones[i];
		}//for
	}//copia Opciones

	/**
	 * Método que se encarga de mostrar las opciones del menú anteponiendo un número o una letra según corresponda
	 * 
	 */
	private void mostrarOpciones()
	{//mostrarOpciones
		System.out.println();
		for (int i=0;i<opciones.length;i++)
		{//for
			String opcion=((this.numerar==Numerar.NUMERO) ? Integer.toString(i+1) : 
				Character.toString((char)((int)'a'+i)));
			System.out.printf ("%s)\t%s\n", opcion,opciones[i]);
		}//for
	}//mostrarOpciones

}//Menu
