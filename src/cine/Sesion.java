package cine;

public class Sesion {
	private String hora;
	private int asientosDisponibles;
	private int sigIdCompra;
	private int[][] estadoAsientos;

	public Sesion(String hora, int fila, int columnas) {
		this.hora = hora;
		this.estadoAsientos = new int[fila][columnas];
		this.asientosDisponibles = fila * columnas;
		this.sigIdCompra = 1;
	}

	public String getHora() {
		return this.hora;
	}

	public boolean equals(Object object) {
		// método que compara el objeto de tipo Sesion dado con la propia
		// sesión, y devuelve cierto si son iguales y falso en caso contrario.
		// Se considera que dos sesiones son iguales si son iguales sus
		// atributos hora.
		Sesion sesion = (Sesion) object;
		return sesion.getHora() == this.hora;
	}

	public void comprarEntrada(int fila, int columna) {
		this.estadoAsientos[fila - 1][columna - 1] = this.sigIdCompra;
		this.sigIdCompra++;
		this.asientosDisponibles--;

	}

	public int getIdEntrada(int fila, int columna) {
		return this.estadoAsientos[fila - 1][columna - 1];
	}

	public char[][] getEstadoSesion() {
		char res[][] = new char[this.estadoAsientos.length][this.estadoAsientos[0].length];
		for (int i = 0; i < this.estadoAsientos.length; i++) {
			for (int j = 0; j < this.estadoAsientos[i].length; j++) {
				if (this.estadoAsientos[i][j] == 0) {
					res[i][j] = 'O';
				} else {
					res[i][j] = '#';
				}
			}
		}
		return res;
	}

	public int getButacasDisponiblesSesion() {
		return this.asientosDisponibles;
	}

	public String recogerEntradas(int id) {
		String res = this.getHora() + "+";
		boolean bool = false;
		for (int i = 0; i < this.estadoAsientos.length && !bool; i++) {
			for (int j = 0; j < this.estadoAsientos[i].length; j++) {
				if (this.estadoAsientos[i][j] == id) {
					bool = true;
					res += "fila" + (i + 1) + ",columna" + (j + 1) + "+";
				}
			}
		}
		if (!bool) {
			return null;
		} else {
			return res;
		}
	}

	public ButacasContiguas recomendarButacasContiguas(int noButacas) {
		int puntero = (this.estadoAsientos.length / 2) + 1;
		int contador = 0;
		int asiento;
		boolean signo = true;
		boolean encontrado = false;
		ButacasContiguas butacas = null;
		while (contador < this.estadoAsientos.length && !encontrado) {

			System.out.println(this.estadoAsientos.length);
			asiento = noButacas;
			for (int i = this.estadoAsientos[0].length - 1; i >= 0 && !encontrado; i--) {
				if (this.estadoAsientos[puntero + 1][i] == 0) {
					asiento--;
				} else {
					asiento = noButacas;
				}

				if (asiento == 0) {
					butacas = new ButacasContiguas(puntero + 1, i + 1, noButacas);
					encontrado = true;
				}
			}

			contador++;

			if (puntero == this.estadoAsientos.length - 1) {
				puntero = this.estadoAsientos.length / 2;
				signo = false;
			}

			if (signo) {
				puntero++;
			} else {
				puntero--;
			}
		}

		if (encontrado) {
			return butacas;
		} else {
			return null;
		}
	}

	public void comprarEntradasRecomendadas(ButacasContiguas butacas) {
		int fila = butacas.getFila();
		int columna = butacas.getColumna();
		int noButacas = butacas.getNoButacas();
		while (noButacas > 0) {
			this.estadoAsientos[fila - 1][columna - 1] = this.sigIdCompra;
			columna++;
			noButacas--;
			this.asientosDisponibles--;
		}
		this.sigIdCompra++;

	}

}
