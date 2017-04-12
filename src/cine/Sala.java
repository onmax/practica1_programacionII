package cine;

import list.ArrayList;
import java.util.Collection;

public class Sala {
	private String pelicula;
	private ArrayList<Sesion> sesiones;
	private int filas, columnas;

	private void burbuja (String [] aux){
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
	}
	
	public Sala(String pelicula, String[] horasSesiones, int filas, int columnas) {
		this.pelicula = pelicula;
		this.filas = filas;
		this.columnas = columnas;
		this.sesiones = new ArrayList<Sesion>();
		burbuja(horasSesiones);
		for (int i = 0; i < horasSesiones.length; i++) {
			Sesion aux = new Sesion(horasSesiones[i], filas, columnas);
			sesiones.add(i, aux);
		}
	}

	public String getSesion() {
		String res = "";
		for (int i = 0; i < this.sesiones.size(); i++) {
			res += this.sesiones.get(i).getHora() + "\n";
		}
		return res;
	}

	public void comprarEntrada(int sesion, int fila, int columna) {
		Sesion aux = this.sesiones.get(sesion);
		aux.comprarEntrada(fila, columna);
	}

	public int getIdEntrada(int sesion, int fila, int columna) {
		return this.sesiones.get(sesion).getIdEntrada(fila, columna);
	}

	public String[] getHorasDeSesionesDeSala() {
		// método que devuelve un vector de String con las horas de las sesiones
		// asociadas a la propia sala. En la posición 0 del vector se encontrará
		// la hora de la sesión 1, en la posición 1
		// la de la sesión 2, y así sucesivamente.
		String[] arr = new String[this.sesiones.size()];
		for (int i = 0; i < this.sesiones.size(); i++) {
			arr[i] = this.sesiones.get(i).getHora();
		}
		return arr;
	}

	public char[][] getEstadoSesion(int sesion) {
		// método que devuelve una matriz de caracteres en la que se representa
		// el estado de ocupación de la propia sala para una sesión dada. El
		// contenido de esta matriz se especifica en el método
		// con el mismo nombre de la clase Sesion.
		return this.sesiones.get(sesion).getEstadoSesion();
	}

	public String getPelicula() {
		return this.pelicula;
	}

	public int getButacasDisponiblesSesion(int sesion) {
		// método que devuelve el número de butacas disponibles en la propia
		// sala para una sesión dada.
		return this.sesiones.get(sesion).getButacasDisponiblesSesion();
	}

	public String recogerEntradas(int id, int sesion) {
		// método que devuelve las N entradas asociadas a un identificador de
		// venta dado para la propia sala y una sesión dada. Las N entradas se
		// devolverán dentro de un String con el siguiente
		// formato:
		// “título_de_la_película@hora_de_la_sesión+fila1,columna1+fila2,columna2+
		// … +filaN,columnaN+” Si el identificador de venta dado no existe en la
		// propia sala y la sesión dada, se devuelve null.
		String res = "";
		if (this.sesiones.get(sesion).recogerEntradas(id) != null) {
			return res += this.pelicula + "@" + this.sesiones.get(sesion).recogerEntradas(id);
		} else {
			return null;
		}
	}

	public ButacasContiguas recomendarButacasContiguas(int noButacas, int sesion) {
		// método que dados un número de butacas y una sesión de la propia sala,
		// devuelve un objeto de tipo ButacasContiguas que contiene la fila y la
		// columna de la butaca recomendaba con menor número de columna,
		// y el número de butacas solicitadas. El algoritmo para obtener las
		// butacas recomendadas se explica en la especificación del método con
		// el mismo nombre en la clase Sesion.
		return this.sesiones.get(sesion).recomendarButacasContiguas(noButacas);

	}

	public void comprarEntradasRecomendadas(int sesion, ButacasContiguas butacas) {
		// método que dados un objeto de tipo ButacasContiguas y una sesión de
		// la propia sala, registra la compra en el objeto de tipo Sesion
		// correspondiente
		this.sesiones.get(sesion).comprarEntradasRecomendadas(butacas);
	}

	public void incluirSesion(String horaSesion) {
		boolean encontrado = true;
		Sesion introducir = new Sesion(horaSesion, this.filas, this.columnas);
		for (int i = 0; i < this.sesiones.size() && encontrado; i++) {
			if (horaSesion.compareTo(this.sesiones.get(i).getHora()) < 0) {
				this.sesiones.add(i, introducir);
				encontrado = false;
			}
		}
	}

	public void borrarSesion(String horaSesion) {
		// método que borra la sesión con la hora dada de la propia sala.
		boolean aux = false;
		for (int i = 0; !this.sesiones.get(i).getHora().equals(horaSesion) && !aux; i++) {
			this.sesiones.removeElementAt(i);
			aux = true;
		}
	}
}
