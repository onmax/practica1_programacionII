package cine;
import list.ArrayList;

public class Sala {
	private String pelicula;
	// título de la película.
	private ArrayList<Sesion> sesiones;
	// son las sesiones en las que se proyecta la película en esta sala
	// representadas por un arraylist de objetos de tipo Sesion. El arraylist
	// estará ordenado de menor
	// a mayor por la hora de la sesión.
	private int filas, columnas;
	
	private String [] ordenarBurbuja (String [] aux){
		String z = "";
		for (int i = 0; i < aux.length - 1; i++) {
			for (int j = 0; j < aux.length-1-i; j++) {
				if (aux[j + 1].compareTo(aux[j])<0) {
					z = aux[j+1];
					aux[j+1] = aux[j];
					aux[j] = z;
				}
			}
		}
		return aux;
	}

	public Sala(String pelicula, String[] horasSesiones, int filas, int columnas) {
		// constructor de la clase Sala que recibe como argumentos el título de
		// la película, un vector de String con las horas de las sesiones de
		// esta sala, y el número de filas y
		// columnas de la sala. Con estos argumentos, inicializa los atributos
		// del objeto.
		this.pelicula = pelicula;
		this.filas = filas;
		this.columnas = columnas;
		this.sesiones = new ArrayList<Sesion>();
		horasSesiones = ordenarBurbuja(horasSesiones);
		for (int i = 0; i < horasSesiones.length; i++) {
			Sesion aux = new Sesion(horasSesiones[i], filas, columnas);
			sesiones.add(i, aux);
		}
	}
	
	public void comprarEntrada(int sesion, int fila, int columna) {
		// método que compra una entrada con la fila y columna dadas para sesión
		// dada de la propia sala. La compra queda registrada en el objeto de
		// tipo Sesion correspondiente
		Sesion comprada = this.sesiones.get(sesion - 1);
		comprada.comprarEntrada(fila, columna);
	}

	public int getIdEntrada(int sesion, int fila, int columna) {
		// método que devuelve el identificador de venta para una entrada en la
		// propia sala especificada mediante su sesión, fila y columna. El
		// algoritmo para obtener este identificador
		// se explica en la especificación del método con el mismo nombre en la
		// clase Sesion.
		Sesion obtenerId = this.sesiones.get(sesion - 1);
		return obtenerId.getIdEntrada(fila, columna);
	}

	public String[] getHorasDeSesionesDeSala() {
		// método que devuelve un vector de String con las horas de las sesiones
		// asociadas a la propia sala. En la posición 0 del vector se encontrará
		// la hora de la sesión 1, en la posición 1
		// la de la sesión 2, y así sucesivamente.
		String[] horasSala = new String[this.sesiones.size()];
		for (int i = 0; i < horasSala.length; i++) {
			horasSala[i] = this.sesiones.get(i).getHora();
		}
		return horasSala;
	}

	public char[][] getEstadoSesion(int sesion) {
		// método que devuelve una matriz de caracteres en la que se representa
		// el estado de ocupación de la propia sala para una sesión dada. El
		// contenido de esta matriz se especifica en el método
		// con el mismo nombre de la clase Sesion.
		return this.sesiones.get(sesion - 1).getEstadoSesion();
	}

	public String getPelicula() {
		// método que devuelve el título de la película asociada a la propia
		// sala.
		return this.pelicula;
	}

	public String recogerEntradas(int id, int sesion) {
		// método que devuelve las N entradas asociadas a un identificador de
		// venta dado para la propia sala y una sesión dada. Las N entradas se
		// devolverán dentro de un String con el siguiente
		// formato:
		// “título_de_la_película@hora_de_la_sesión+fila1,columna1+fila2,columna2+
		// … +filaN,columnaN+” Si el identificador de venta dado no existe en la
		// propia sala y la sesión dada, se devuelve null.
		String res = getPelicula() + "@" + this.sesiones.get(sesion - 1).recogerEntradas(id);
		return res;
	}

	public int getButacasDisponiblesSesion(int sesion) {
		// método que devuelve el número de butacas disponibles en la propia
		// sala para una sesión dada.
		return this.sesiones.get(sesion - 1).getButacasDisponiblesSesion();
	}

	public ButacasContiguas recomendarButacasContiguas(int noButacas, int sesion) {
		// método que dados un número de butacas y una sesión de la propia sala,
		// devuelve un objeto de tipo ButacasContiguas que contiene la fila y la
		// columna de la butaca recomendaba con menor número de columna,
		// y el número de butacas solicitadas. El algoritmo para obtener las
		// butacas recomendadas se explica en la especificación del método con
		// el mismo nombre en la clase Sesion.
		ButacasContiguas butacas = this.sesiones.get(sesion - 1).recomendarButacasContiguas(noButacas);
		return butacas;
	}

	public void comprarEntradasRecomendadas(int sesion, ButacasContiguas butacas) {
		// método que dados un objeto de tipo ButacasContiguas y una sesión de
		// la propia sala, registra la compra en el objeto de tipo Sesion
		// correspondiente
		this.sesiones.get(sesion - 1).comprarEntradasRecomendadas(butacas);
	}

	public void incluirSesion(String horaSesion) {
		// método que añade una nueva sesión con la hora dada a la propia sala.
		// Se realizará la inserción de forma que se mantenga ordenado el
		// arraylist de sesiones
		Sesion introducir = new Sesion(horaSesion, this.filas, this.columnas);
		boolean aux = false;
		for (int i = 0; i < this.sesiones.size() && !aux; i++) {
			if (horaSesion.compareTo(this.sesiones.get(i).getHora()) < 0) {
				this.sesiones.add(i, introducir);
				aux = true;
			}
		}
	}

	public void borrarSesion(String horaSesion) {
		// método que borra la sesión con la hora dada de la propia sala.
		boolean aux = false;
		for (int i = 0; this.sesiones.get(i).getHora().equals(horaSesion) && !aux; i++) {
			this.sesiones.removeElementAt(i);
			aux = true;
		}
	}
}
