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
		return this.hora;
	}
	
	public boolean equals(Sesion obj){
		//método que compara el objeto de tipo Sesion dado con la propia sesión, y devuelve cierto si son iguales y falso en caso contrario. Se considera que dos sesiones son iguales si son iguales sus atributos hora.  
		return obj.getHora() == this.hora;
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
	// método que dados un número N de butacas, devuelve un objeto de tipo ButacasContiguas que contiene la fila y la columna de la butaca recomendaba con menor número de columna, y el número de butacas solicitadas. 
	//El algoritmo para obtener las butacas recomendadas es el siguiente: 1. Se buscan las primeras N butacas contiguas libres empezando por la fila (N_FILAS+1)/2+1 y acabando en la fila N_FILAS. Cada fila se recorre 
	//empezando por la columna N_COLUMNAS y acabando en la columna 1. 2. Si en el paso anterior no se encuentran N butacas contiguas libres, se buscan las primeras N butacas contiguas libres empezando por la fila 
	//(N_FILAS+1)/2 y acabando en la fila 1. Cada fila se recorre empezando por la columna N_COLUMNAS y acabando en la columna 1. Si no existen N butacas libres contiguas en la sala para la propia sesión, se devuelve null.
	//Por ejemplo, si tomamos la sala de la figura 2 y buscamos 3 butacas contiguas libres, este algoritmo devolverá las butacas (4, 3), (4, 4) y (4, 5).
		
		//--------METODO DE MAX--------//
		int filas = (estadoAsientos.length/2) + 1;
		int columnas = estadoAsientos[0].length - 1;
		int cont = 0;
		boolean bool = false;
		ButacasContiguas butacas;
		while(!bool && cont < estadoAsientos.length){
			int aux = 0;
			for(int i = 0; i>=0; i--){
				if(estadoAsientos[filas][i] == 0 && aux - noButacas >= 0){
					aux += 1;
					if(aux == noButacas){
						butacas = new ButacasContiguas(filas, i, noButacas);
						bool = true;
					}
				}
			}
			if(filas == estadoAsientos.length - 1){
				filas = 0;
				cont ++;
			}else{
				filas ++;
				cont ++;
			}
			
		}
		
	}
	
	//--------METODO DEL PUTO AMO----------//
	
	public ButacasContiguas recomendarButacasContiguas(int noButacas) {
		
		//------------------Variable--------------------- //
		
		int con = 0;
		char[][] aux = getEstadoSesion();
		
		// ----------------Parte1----------------------- //
		
		for (int i = (this.estadoAsientos.length + 1) / 2 + 1; this.estadoAsientos.length > i; i++) {
			for (int j = this.estadoAsientos[i].length-1; j > 1; j--) {
				if (aux[i][j] == 'O') {
					con++;
					if (con == noButacas) {
						ButacasContiguas solucion = new ButacasContiguas(i, j, noButacas);
						return solucion;
					} // Fin if2
				} else {
					con = 0;
				}
			} // Fin for j
		} // Fin for i

		// ---------------Parte2------------------- //

		for (int i = (this.estadoAsientos.length + 1) / 2; i >= 1; i--) {
			for (int j = this.estadoAsientos[i].length; j > 1; j--) {
				if (aux[i][j] == 'O') {
					con++;
					if (con == noButacas) {
						ButacasContiguas solucion = new ButacasContiguas(i, j, noButacas);
						return solucion;
					} // Fin if2
				} else {
					con = 0;
				} // Fin else
			} // Fin for j
		} // Fin for i

		// ---------------Parte3---------------- //
		
		return null;
	}
	public void comprarEntradasRecomendadas(ButacasContiguas butacas){
	//método que dado un objeto de tipo ButacasContiguas, registra la compra en la propia sesión guardando el valor actual del atributo sigIdCompra en las posiciones especificadas por el objeto dado como argumento. 
	//A continuación, se incrementa en uno el atributo sigIdCompra. 
	}
}
