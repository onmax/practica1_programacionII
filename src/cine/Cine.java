package cine;

public class Cine {
	private String nombre;
	private Sala[] salas;

	public Cine(String nombre, Sala[] salas) {
		this.nombre = nombre;
		this.salas = salas;
	}

	public void comprarEntrada(int sala, int sesion, int fila, int columna) {
		this.salas[sala - 1].comprarEntrada(sesion, fila, columna);
	}

	public int getIdEntrada(int sala, int sesion, int fila, int columna) {
		return this.salas[sala - 1].getIdEntrada(sesion, fila, columna);
	}

	public String[] getPeliculas() {
		String[] aux = new String[this.salas.length];
		for (int i = 0; i < this.salas.length; i++) {
			aux[i] = this.salas[i].getPelicula();
		}
		return aux;
	}

	public String[] getHorasDeSesionesDeSala(int sala) {
		return this.salas[sala - 1].getHorasDeSesionesDeSala();
	}

	public char[][] getEstadoSesion(int sala, int sesion) {
		return this.salas[sala - 1].getEstadoSesion(sesion);
	}

	public int getButacasDisponiblesSesion(int sala, int sesion) {
		return this.salas[sala - 1].getButacasDisponiblesSesion(sesion);
	}

	public String recogerEntradas(int id, int sala, int sesion) {
		String info = this.salas[sala].recogerEntradas(id, sesion);
		if (info != null) {
			return this.nombre + "@" + info;
		} else {
			return null;
		}
	}

	public ButacasContiguas recomendarButacasContiguas(int noButacas, int sala, int sesion) {
		return this.salas[sala - 1].recomendarButacasContiguas(noButacas, sesion);
	}

	public void comprarEntradasRecomendadas(int sala, int sesion, ButacasContiguas butacas) {
		this.salas[sala - 1].comprarEntradasRecomendadas(sesion, butacas);
	}

	public void incluirSesion(int sala, String horaSesion) {
		this.salas[sala - 1].incluirSesion(horaSesion);
	}

	public void borrarSesion(int sala, String horaSesion) {
		this.salas[sala - 1].borrarSesion(horaSesion);
	}
}
