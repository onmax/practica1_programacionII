package cine;

public class Cine {
	private String nombre;
	private Sala[] salas;

	public Cine(String nombre, Sala[] salas) {
		this.nombre = nombre;
		this.salas = salas;
	}

	public void comprarEntrada(int sala, int sesion, int fila, int columna) {
		this.salas[sala].comprarEntrada(sesion, fila, columna);
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
	
	public int getButacasDisponiblesSesion(int sala, int sesion) {
		return this.salas[sala].getButacasDisponiblesSesion(sesion);
	}
	
	public String recogerEntradas(int id, int sala, int sesion) {
		String info = this.salas[sala].recogerEntradas(id, sesion);
		if(info == null){
			return this.nombre + "@" + info;
		}else{
			return null;
		}
	}

	public ButacasContiguas recomendarButacasContiguas(int noButacas, int sala, int sesion) {
		return this.salas[sala].recomendarButacasContiguas(noButacas, sesion);
	}

	public void comprarEntradasRecomendadas(int sala, int sesion, ButacasContiguas butacas) {
		this.salas[sala].comprarEntradasRecomendadas(sesion, butacas);
	}

	public void incluirSesion(int sala, String horaSesion) {
		this.salas[sala].incluirSesion(horaSesion);
	}

	public void borrarSesion(int sala, String horaSesion) {
		this.salas[sala].borrarSesion(horaSesion);
	}
}
