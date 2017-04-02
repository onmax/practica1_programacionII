package cine;

import anotacion.Programacion2;

@Programacion2(nombreAutor1 = "Máximo", apellidoAutor1 = "García Martínez", emailUPMAutor1 = "maximo.garcia.martinez@alumnos.upm.es", nombreAutor2 = "Javier", apellidoAutor2 = "", emailUPMAutor2 = "")
public class Sesion {
	private String hora; // HH:MM
	private int asientosDisponibles, sigIdCompra;
	private int[][] estadoAsientos; // int [filas][columnas]

	public Sesion(String hora, int fila, int columnas) {
		this.estadoAsientos = new int[fila][columnas];
		this.hora = hora;
		this.sigIdCompra = 1;
	}

	public void comprarEntrada(int fila, int columna) {

	}

	public int getIdEntrada(int fila, int columna) {

	}

	public int getButacasDisponiblesSesion() {

	}

	public String getHora() {
		return this.hora;
	}

	public boolean equals(Sesion sesion2) {
		return sesion2.getHora() == this.hora;
	}

	public char[][] getEstadoSesion() {

	}

	public String recogerEntradas(int id) {

	}

}