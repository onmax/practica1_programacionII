package cine;

public class Cine {
	private String nombre;
	private Sala[] salas;

	public Cine(String nombre, Sala[] salas) {
		this.nombre = nombre;
		this.salas = salas;
	}

	public void comprarEntradas(int sala, int sesion, int fila, int columna) {
		this.salas[sala].comprarEntrada(sesion, fila, columna);
	}

	public ButacasContiguas recomendarButacasContiguas(int noButacas, int sala, int sesion) {

	}

	public int getIdEntrada(int sala, int sesion, int fila, int columna) {
		return this.salas[sala].getIdEntrada(sesion, fila, columna);
	}

	public String[] getPeliculas() {
		String[] aux = new String[this.salas.length];
		for (int i = 0; i < this.salas.length; i++) {
			aux[i] = this.salas[i].getPelicula();
		}
		return aux;
	}

	public String[] getHorasDeSesionesDeSala(int sala) {
		return this.salas[sala].getHorasDeSesionesDeSala();
	}

	public char[][] getEstadoSesion(int sala, int sesion) {
		return this.salas[sala].getEstadoSesion(sesion);
	}

	public String[] getEstadoSesionDeSala(int sala) {

	}

	public String recogerEntradas(int id, int sala, int sesion) {

	}

	public int getButacasDisponiblesSesion(int sala, int sesion) {
		return this.salas[sala].getButacasDisponiblesSesion(sesion);
	}

	public void comprarEntradasRecomendadas(int sala, int sesion, ButacasContiguas butacas) {

	}

	public void incluirSesion(int sala, String horaSesion) {

	}

	public void borrarSesion(int sala, String horaSesion) {

	}
}
