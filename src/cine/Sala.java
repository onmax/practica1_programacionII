package cine;

import list.ArrayList;

public class Sala {
	private String pelicula;
	private ArrayList<Sesion> sesiones;
	private int filas, columnas;

	private String[] ordenarBurbuja(String[] aux) {
		String z = "";
		for (int i = 0; i < aux.length - 1; i++) {
			for (int j = 0; j < aux.length - 1 - i; j++) {
				if (aux[j + 1].compareTo(aux[j]) < 0) {
					z = aux[j + 1];
					aux[j + 1] = aux[j];
					aux[j] = z;
				}//fin de if
			}//fin de for
		}//fin de for
		return aux;
	}

	public Sala(String pelicula, String[] horasSesiones, int filas, int columnas) {
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
		Sesion comprada = this.sesiones.get(sesion - 1);
		comprada.comprarEntrada(fila, columna);
	}

	public int getIdEntrada(int sesion, int fila, int columna) {
		Sesion obtenerId = this.sesiones.get(sesion - 1);
		return obtenerId.getIdEntrada(fila, columna);
	}

	public String[] getHorasDeSesionesDeSala() {
		String[] horasSala = new String[this.sesiones.size()];
		for (int i = 0; i < horasSala.length; i++) {
			horasSala[i] = this.sesiones.get(i).getHora();
		}
		return horasSala;
	}

	public char[][] getEstadoSesion(int sesion) {
		return this.sesiones.get(sesion - 1).getEstadoSesion();
	}

	public String getPelicula() {
		// sala.
		return this.pelicula;
	}

	public String recogerEntradas(int id, int sesion) {
		String res = getPelicula() + "@" + this.sesiones.get(sesion - 1).recogerEntradas(id);
		return res;
	}

	public int getButacasDisponiblesSesion(int sesion) {
		return this.sesiones.get(sesion - 1).getButacasDisponiblesSesion();
	}

	public ButacasContiguas recomendarButacasContiguas(int noButacas, int sesion) {
		ButacasContiguas butacas = this.sesiones.get(sesion - 1).recomendarButacasContiguas(noButacas);
		return butacas;
	}

	public void comprarEntradasRecomendadas(int sesion, ButacasContiguas butacas) {
		this.sesiones.get(sesion - 1).comprarEntradasRecomendadas(butacas);
	}

	public void incluirSesion(String horaSesion) {
		Sesion introducir = new Sesion(horaSesion, this.filas, this.columnas);
		boolean aux = false;
		for (int i = 0; i < this.sesiones.size() && !aux; i++) {
			if (horaSesion.compareTo(this.sesiones.get(i).getHora()) < 0) {
				this.sesiones.add(i, introducir);
				aux = true;
			}//fin de if
		}//fin de for
	}

	public void borrarSesion(String horaSesion) {
		boolean aux = false;
		for (int i = 0; this.sesiones.get(i).getHora().equals(horaSesion) && !aux; i++) {
			this.sesiones.removeElementAt(i);
			aux = true;
		}
	}
}
