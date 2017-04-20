package cine;

import list.ArrayList;

public class Sala {
	//---------------ATRIBUTOS---------------//
	
	private String pelicula;
	private ArrayList<Sesion> sesiones;
	private int filas, columnas;
	
	//---------------CONSTRUCTOR---------------//
	
	public Sala(String pelicula, String[] horasSesiones, int filas, int columnas) {
		this.pelicula = pelicula;
		this.filas = filas;
		this.columnas = columnas;
		this.sesiones = new ArrayList<Sesion>();
		for(int i=0; i<horasSesiones.length; i++){
			incluirSesion(horasSesiones[i]);
		}//Fin for
	}
	//---------------GETTERS---------------//
	
	public int getIdEntrada(int sesion, int fila, int columna) {
		return this.sesiones.get(sesion - 1).getIdEntrada(fila, columna);
	}

	public String[] getHorasDeSesionesDeSala() {
		String[] horasSala = new String[this.sesiones.size()];
		for (int i = 0; i < horasSala.length; i++) {
			horasSala[i] = this.sesiones.get(i).getHora();
		}//Fin for
		return horasSala;
	}

	public char[][] getEstadoSesion(int sesion) {
		return this.sesiones.get(sesion - 1).getEstadoSesion();
	}

	public String getPelicula() {
		return this.pelicula;
	}
	
	public int getButacasDisponiblesSesion(int sesion) {
		return this.sesiones.get(sesion - 1).getButacasDisponiblesSesion();
	}

	//---------------METODOS---------------//
	
	public void comprarEntrada(int sesion, int fila, int columna) {
		this.sesiones.get(sesion - 1).comprarEntrada(fila, columna);	
	}

	
	public String recogerEntradas(int id, int sesion) {
		String num = this.sesiones.get(sesion - 1).recogerEntradas(id);
		if(num == null){
			return null;
		}else{
			return getPelicula() + "@" + num;
		}//Fin else
	}

	
	public ButacasContiguas recomendarButacasContiguas(int noButacas, int sesion) {
		return this.sesiones.get(sesion - 1).recomendarButacasContiguas(noButacas);
	}

	public void comprarEntradasRecomendadas(int sesion, ButacasContiguas butacas) {
		this.sesiones.get(sesion - 1).comprarEntradasRecomendadas(butacas);
	}

	public void incluirSesion(String horaSesion) {
		Sesion introducir = new Sesion(horaSesion, this.filas, this.columnas);
		int i;
		for (i=sesiones.size()-1; i>=0 && sesiones.get(i).getHora().compareTo(horaSesion) > 0; i--);
		sesiones.add(i+1, introducir);
	}
		
	
	public void borrarSesion(String horaSesion) {
		boolean aux = false;
		for (int i = 0;i<this.sesiones.size() && !aux; i++) {
			if(this.sesiones.get(i).getHora().equals(horaSesion)){
				this.sesiones.removeElementAt(i);
				aux = true;
			}//Fin if
		}//Fin for
	}
	
}

