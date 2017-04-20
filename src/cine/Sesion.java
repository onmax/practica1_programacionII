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
	
	//---------------ATRIBUTOS---------------//
	
	private String hora;
	private int asientosDisponibles;
	private int sigIdCompra;
	private int[][] estadoAsientos;
	
	//---------------CONSTRUCTOR---------------//
	
	public Sesion(String hora, int fila, int columnas) {
		this.hora = hora;
		this.estadoAsientos = new int[fila][columnas];
		this.asientosDisponibles = fila * columnas;
		this.sigIdCompra = 1;
	}//Fin constructor

	//---------------GETTERS---------------//
	
	public String getHora() {
		return this.hora;
	}//Fin metodo
	
	public int getIdEntrada(int fila, int columna) {
		return this.estadoAsientos[fila - 1][columna - 1];
	}//Fin metodo

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
	}//Fin metodo

	public int getButacasDisponiblesSesion() {
		return this.asientosDisponibles;
	}//Fin metodo
	
	//---------------METODOS---------------//
	
	public boolean equals(Object object) {
		Sesion sesion = (Sesion) object;
		return sesion.getHora() == this.hora;
	}//Fin metodo

	public void comprarEntrada(int fila, int columna) {
		this.estadoAsientos[fila - 1][columna - 1] = this.sigIdCompra;
		this.sigIdCompra++;
		this.asientosDisponibles--;
	}//Fin metodo

	public String recogerEntradas(int id) {
		String res = this.getHora() + "+";
		boolean bool = false;
		for (int i = 0; i < this.estadoAsientos.length && !bool; i++) {
			for (int j = 0; j < this.estadoAsientos[i].length; j++) {
				if (this.estadoAsientos[i][j] == id) {
					bool = true;
					res += (i + 1) + "," + (j + 1) + "+";
				}//fin de if
			}//fin de for
		}//fin de for
		if (!bool) {
			return null;
		} else {
			return res;
		}//Fin else
	}//Fin del metodo

	public ButacasContiguas recomendarButacasContiguas(int noButacas) {
		int puntero = (this.estadoAsientos.length+1) / 2 + 1;
		if(puntero == this.estadoAsientos.length){  
			puntero = 1;
		}//Fin if
		int contador = 0;
		boolean encontrado = false;
		boolean signo = true;
		int asientos;
		ButacasContiguas butacas = null;
		while(!encontrado && contador < this.estadoAsientos.length){ //Inicio while
			asientos = noButacas;
			for(int i = this.estadoAsientos[puntero].length - 1; !encontrado && i >= 0; i--){ //Inicio for
				
				if(this.estadoAsientos[puntero][i] == 0){//Inicio if1
					asientos --;
				}//Fin if1
				else{
					asientos = noButacas;
				}//Fin else
				
				if(asientos == 0){//inicio if2
					butacas = new ButacasContiguas(puntero + 1, i + 1, noButacas);
					encontrado = true;
				}//Fin if2
			}//Fin for
			contador ++;
			
			if(signo){//Inicio if1
				
				if(puntero == this.estadoAsientos.length-1){
					signo = false;
					puntero = (this.estadoAsientos.length+1) / 2;
				}//Fin if
				
				if(signo){//Inicio if2
					puntero ++;
				}//Fin if2
				
			}//Fin if1
			
			else{
				puntero -= 1;
			}//Fin else
			
		}//Fin while
		
		if(encontrado){
			return butacas;
		}else{
			return null;
		}//Fin else
	}//Fin metodo

	public void comprarEntradasRecomendadas(ButacasContiguas butacas) {
		int fila = butacas.getFila();
		int columna = butacas.getColumna();
		int noButacas = butacas.getNoButacas();
		while (noButacas > 0) {//Inicio while
			this.estadoAsientos[fila - 1][columna - 1] = this.sigIdCompra;
			columna++;
			noButacas--;
			this.asientosDisponibles--;
		}//Fin while
		
		this.sigIdCompra++;
	}//Fin metodo
}
