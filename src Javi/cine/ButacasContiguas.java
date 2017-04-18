package cine;

public class ButacasContiguas {

	private int fila, columna, noButacas;

	public ButacasContiguas(int fila, int columna, int noButacas) {
		this.fila = fila;
		this.columna = columna;
		this.noButacas = noButacas;
	}

	public String toString() {
		String aux = "";
		return aux += this.fila + "\n" + this.columna + "\n" + this.noButacas + "\n";
	}

	public int getFila() {
		return fila;
	}

	public int getColumna() {
		return columna;
	}

	public int getNoButacas() {
		return noButacas;
	}

}
