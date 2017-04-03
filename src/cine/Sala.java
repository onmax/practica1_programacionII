package cine;
import list.ArrayList;


public class Sala {
	private String pelicula;
	// t�tulo de la pel�cula. 
	private ArrayList<Sesion> sesion;
	//son las sesiones en las que se proyecta la pel�cula en esta sala representadas por un arraylist de objetos de tipo Sesion. El arraylist estar� ordenado de menor a mayor por la hora de la sesi�n. 
	
	public Sala(String pelicula, String [] horasSesiones, int filas, int columnas){
	//constructor de la clase Sala que recibe como argumentos el t�tulo de la pel�cula, un vector de String con las horas de las sesiones de esta sala, y el n�mero de filas y 
	//columnas de la sala. Con estos argumentos, inicializa los atributos del objeto.
		this.pelicula = pelicula;
	}
	
	public void comprarEntrada(int sesion, int fila, int columna){
	// m�todo que compra una entrada con la fila y columna dadas para sesi�n dada de la propia sala. La compra queda registrada en el objeto de tipo Sesion correspondiente
	}
	
	public int getIdEntrada(int sesion, int fila, int columna){
	//m�todo que devuelve el identificador de venta para una entrada en la propia sala especificada mediante su sesi�n, fila y columna. El algoritmo para obtener este identificador 
	//se explica en la especificaci�n del m�todo con el mismo nombre en la clase Sesion.
		return 0;
	}
	
	public String [] getHorasDeSesionesDeSala(){
	// m�todo que devuelve un vector de String con las horas de las sesiones asociadas a la propia sala. En la posici�n 0 del vector se encontrar� la hora de la sesi�n 1, en la posici�n 1
	//la de la sesi�n 2, y as� sucesivamente.
		String [] y = new String[1];
		return y;
	}
	
	public char [][] getEstadoSesion(int sesion){
	// m�todo que devuelve una matriz de caracteres en la que se representa el estado de ocupaci�n de la propia sala para una sesi�n dada. El contenido de esta matriz se especifica en el m�todo 
	//con el mismo nombre de la clase Sesion.
		char [][] a = new char[1][1];
		return a;
	}
	
	public String getPelicula(){
	//m�todo que devuelve el t�tulo de la pel�cula asociada a la propia sala.
		return this.pelicula;
	}
	
	public String recogerEntradas(int id, int sesion){
	// m�todo que devuelve las N entradas asociadas a un identificador de venta dado para la propia sala y una sesi�n dada. Las N entradas se devolver�n dentro de un String con el siguiente 
	//formato:  �t�tulo_de_la_pel�cula@hora_de_la_sesi�n+fila1,columna1+fila2,columna2+ � +filaN,columnaN+� Si el identificador de venta dado no existe en la propia sala y la sesi�n dada, se devuelve null.
		return "ds";
	}
	
	public int getButacasDisponiblesSesion(int sesion){
	//m�todo que devuelve el n�mero de butacas disponibles en la propia sala para una sesi�n dada. 
		return 0;
	}
	
	public ButacasContiguas recomendarButacasContiguas(int noButacas, int sesion){
	//m�todo que dados un n�mero de butacas y una sesi�n de la propia sala, devuelve un objeto de tipo ButacasContiguas que contiene la fila y la columna de la butaca recomendaba con menor n�mero de columna, 
	//y el n�mero de butacas solicitadas. El algoritmo para obtener las butacas recomendadas se explica en la especificaci�n del m�todo con el mismo nombre en la clase Sesion.
		ButacasContiguas t = new ButacasContiguas(1,1,1);
		return t;
	}
	
	public void comprarEntradasRecomendadas(int sesion, ButacasContiguas butacas){
	// m�todo que dados un objeto de tipo ButacasContiguas y una sesi�n de la propia sala, registra la compra en el objeto de tipo Sesion correspondiente
	}
	
	public void incluirSesion(String horaSesion){
	// m�todo que a�ade una nueva sesi�n con la hora dada a la propia sala. Se realizar� la inserci�n de forma que se mantenga ordenado el arraylist de sesiones
	}
	
	public void borrarSesion(String horaSesion){
	//m�todo que borra la sesi�n con la hora dada de la propia sala. 
	}
	
}
