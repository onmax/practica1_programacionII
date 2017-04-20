package cine;
import anotacion.Programacion2;
@Programacion2 (
		nombreAutor1 = "Máximo",
		apellidoAutor1 = "García Martínez",
		emailUPMAutor1 = "maximo.garcia.martinez@alumnos.upm.es",
		nombreAutor2 = "Javier",
		apellidoAutor2 = "Barragán Haro", 
		emailUPMAutor2 = "javier.barragan.haro@alumnos.upm.es"
	)

public class Cine {
	//---------------ATRIBUTOS---------------//
	
	private String nombre;
	private Sala[] salas;
	
	//---------------CONSTRUCTOR---------------//
	public Cine(String nombre, Sala[] salas) {
		this.nombre = nombre;
		this.salas = salas;
	}
	
	//---------------GETTERS---------------//
	
	public int getIdEntrada(int sala, int sesion, int fila, int columna) {
		return this.salas[sala - 1].getIdEntrada(sesion, fila, columna);
	}//Fin metodo

	public String[] getPeliculas() {
		String[] aux = new String[this.salas.length];
		for (int i = 0; i < this.salas.length; i++) {
			aux[i] = this.salas[i].getPelicula();
		}//Fin for
		return aux;
	}//Fin metodo

	public String[] getHorasDeSesionesDeSala(int sala) {
		
		return this.salas[sala - 1].getHorasDeSesionesDeSala();
	}//Fin metodo

	public char[][] getEstadoSesion(int sala, int sesion) {
		return this.salas[sala - 1].getEstadoSesion(sesion);
	}//Fin metodo

	public int getButacasDisponiblesSesion(int sala, int sesion) {
		return this.salas[sala - 1].getButacasDisponiblesSesion(sesion);
	}//Fin metodo
	
	//---------------METODOS---------------//
	
	public void comprarEntrada(int sala, int sesion, int fila, int columna) {
		this.salas[sala - 1].comprarEntrada(sesion, fila, columna);
	}//Fin metodo

	public String recogerEntradas(int id, int sala, int sesion) {
		String info = this.salas[sala - 1].recogerEntradas(id, sesion);
		if (info != null) {
			return this.nombre + "@" + info;
		} else {
			return null;
		}//Fin else
	}//Fin metodo

	public ButacasContiguas recomendarButacasContiguas(int noButacas, int sala, int sesion) {
		return this.salas[sala - 1].recomendarButacasContiguas(noButacas, sesion);
	}//Fin metodo

	public void comprarEntradasRecomendadas(int sala, int sesion, ButacasContiguas butacas) {
		this.salas[sala - 1].comprarEntradasRecomendadas(sesion, butacas);
	}//Fin metodo

	public void incluirSesion(int sala, String horaSesion) {
		this.salas[sala - 1].incluirSesion(horaSesion);
	}//Fin metodo

	public void borrarSesion(int sala, String horaSesion) {
		this.salas[sala - 1].borrarSesion(horaSesion);
	}//Fin metodo
	
}
