package cine;

//@Programacion2(nombreAutor1 = "Máximo", apellidoAutor1 = "García Martínez", emailUPMAutor1 = "maximo.garcia.martinez@alumnos.upm.es", 
//				nombreAutor2 = "Javier", apellidoAutor2 = "Barragan Haro", emailUPMAutor2 = "javier.barrragan.haro@alumnos.upm.es");
public class Sesion {
	private String hora;
	// hora de la sesión en formato HH:MM
	private int asientosDisponibles;
	//es un entero que indica el número de asientos disponibles en la propia sesión
	private int sigIdCompra;
	//es un entero que se incrementa cada vez que se realiza una venta de entradas para la propia sesión. Se utiliza para generar identificadores de venta diferentes para cada compra
	private int [][] estadoAsientos;
	//es una matriz de enteros con las mismas dimensiones que la sala asociada a la propia sesión, en la que se representan las butacas compradas y las disponibles para la propia sesión. 
	//Si una butaca en la posición (i, j) está disponible, se guarda un valor 0, y en caso contrario, se guarda el identificador de venta asociado a la butaca. Como en una venta se puede comprar más de una butaca, 
	//podría suceder que en la matriz haya varios identificadores de ventas iguales en posiciones contiguas. 

	
	public Sesion (String hora, int fila, int columnas){
	//constructor de la clase Sesion que recibe como argumentos la hora de proyección, y el número de filas y columnas de la sala asociada a la propia sesión. Con estos argumentos, 
	//inicializa los atributos del objeto. El atributo sigIdComprase inicializa a 1. 
		this.hora = hora;
		this.estadoAsientos = new int [fila][columnas];
		this.asientosDisponibles = fila * columnas;
		this.sigIdCompra = 1;
	}
	
	public String getHora(){
		//método que devuelve la hora asociada a la propia sesión
		Sesion sesion = (Sesion) object;
		return sesion.getHora() == this.hora;
	}
	
	public boolean equals(Sesion obj){
		//método que compara el objeto de tipo Sesion dado con la propia sesión, y devuelve cierto si son iguales y falso en caso contrario. Se considera que dos sesiones son iguales si son iguales sus atributos hora.  
		Sesion sesion = (Sesion) object;
		return sesion.getHora() == this.hora;
	}
	
	public void comprarEntrada(int fila, int columna){
	//método que compra una entrada con la fila y columna dadas para la propia sesión. Para registrar la venta, se guarda el valor actual del atributo sigIdCompra en la posición 
	//(fila-1, columna-1) del atributo estadoAsientos. A continuación, se incrementa en uno el atributo sigIdCompra.
		if(estadoAsientos[fila - 1][columna - 1] == 0){
			this.estadoAsientos[fila - 1][columna - 1]= sigIdCompra;
			this.sigIdCompra += 1;
			this.asientosDisponibles -= 1;
		}
	}
	
	public int getIdEntrada(int fila, int columna){
	// método que devuelve el identificador de venta para una entrada en la propia sesión especificada mediante su fila y columna. El identificador que se devuelve se toma de la posición 
	//(fila-1, columna-1) del atributo estadoAsientos
		return this.estadoAsientos[fila - 1][columna - 1];
	}
	
	public char [][] getEstadoSesion(){
	//método que devuelve una matriz de caracteres en la que se representa el estado de ocupación de la propia sesión. La matriz resultado tiene el mismo número de filas y columnas que la sala asociada
	//a la propia sesión, y en cada posición (i, j) de la matriz debe aparecer un carácter ‘#’ si la butaca ha sido comprada, y un carácter ‘O’ (O mayúscula) si la butaca está disponible. En la figura 2 se puede 
	//observar un ejemplo de una matriz de caracteres que refleja el estado de ocupación de una sala en una sesión
		char res [][] = new char[this.estadoAsientos.length][this.estadoAsientos[0].length];
		for(int i= 0; i<this.estadoAsientos.length; i++){
			for (int j = 0; j< this.estadoAsientos[i].length;j++){
				if(this.estadoAsientos[i][j] == 0){
					res[i][j] = 'O';
				}else{
					res[i][j] = '#';
				}
			}
		}
		return res;
	}
	
	public int getButacasDisponiblesSesion(){
	//método que devuelve el número de butacas disponibles en la propia sesión.
		//OPCION 1: Para esta opción hace falta añadir <-- this.asientosDisponibles -= 1; --> en comprarEntrada, pero no viene en el pdf.
		//return this.asientosDisponibles;
		
		//OPCION 2
		int res = 0;
		char estadoSesion [][] = getEstadoSesion();
		for(int i= 0; i<this.estadoAsientos.length; i++){
			for (int j = 0; j< this.estadoAsientos[i].length;j++){
				if(estadoSesion[i][j] == '#'){
					res += 1;
				}
			}
		}
		return res;
	}
	
	public String recogerEntradas(int id){
	//método que devuelve las N entradas asociadas a un identificador de venta dado para la propia sesión. Las N entradas se devolverán dentro de un String con el siguiente formato: 
	//“hora_de_la_sesión+fila1,columna1+fila2,columna2+ … +filaN,columnaN+” Si el identificador de venta dado no existe en la propia sesión, se devuelve null.
		String res = this.getHora() + "+";
		boolean bool = false;
		for(int i= 0; i<this.estadoAsientos.length; i++){
			for (int j = 0; j< this.estadoAsientos[i].length;j++){
				if(this.estadoAsientos[i][j] == id){
					bool = true;
					res += i+1 + "," + j+1 + "+";
				}
			}
		}
		if(!bool){
			return null;
		}else{
			return res;
		}
	}
	
	public ButacasContiguas recomendarButacasContiguas(int noButacas){
		int fila_seleccionada  = (estadoAsientos.length/2) + 1;
		int top = fila_seleccionada;
		int columna = estadoAsientos[0].length - 1;
		int contador = 0;
		int aux = 0;
		boolean signo = true;
		boolean encontrado = false;
		ButacasContiguas butacas = null;
		
		while(contador < estadoAsientos.length && !encontrado){
			aux = 0;
			for(int i=columna; i >= 0; i--){
				if(estadoAsientos[fila_seleccionada][i] == 0 && noButacas - aux  >= 0){
					aux ++;
				}
				if(aux == noButacas){
					butacas = new ButacasContiguas(fila_seleccionada, i, noButacas);
					encontrado = true;
				}
			}
			
			if(estadoAsientos.length - contador == top){
				fila_seleccionada = estadoAsientos.length/2;
				signo = false;
			}else if(signo){
				fila_seleccionada ++;
			}else if(!signo){
				fila_seleccionada --;
			}
			contador ++;
		}
		
		return butacas;
	}
	
	public void comprarEntradasRecomendadas(ButacasContiguas butacas){
		int fila = butacas.getFila();
		int columna = butacas.getColumna();
		int noButacas = butacas.getNoButacas();
		for(int i = columna; i<= noButacas; i++){
			this.estadoAsientos[fila][i] = sigIdCompra;
		}
		this.sigIdCompra ++;
	}
}
