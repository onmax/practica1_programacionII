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
				}//fin else
			}//fin for
		}//fin for
		return res;
	}//fin método

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
				}//fin de if
			}//fin de for
		}//fin de for
		if (!bool) {
			return null;
		} else {
			return res;
		}
	}

	public ButacasContiguas recomendarButacasContiguas(int noButacas) {
		int puntero = (this.estadoAsientos.length+1 / 2) + 1;
		int contador = 0;
		boolean encontrado = false;
		boolean signo = true;
		int asientos;
		ButacasContiguas butacas = null;
		while(!encontrado && contador < this.estadoAsientos.length){
			asientos = noButacas;
			for(int i = this.estadoAsientos[puntero].length - 1; !encontrado && i > 0; i--){
				if(this.estadoAsientos[puntero+1][i] == 0){
					asientos --;
				}else{
					asientos = noButacas;
				}
				if(asientos == 0){
					butacas = new ButacasContiguas(puntero + 1, i + 1, noButacas);
					encontrado = true;
				}
			}
			
			
			contador ++;
			if(puntero == this.estadoAsientos.length - 1){
				signo = false;
				puntero = this.estadoAsientos.length / 2;
			}
			if(signo){
				puntero ++;
			}else{
				puntero --;
			}
		}
		
		if(encontrado){
			return butacas;
		}else{
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
