package cine;

public class Sesion {
	private String hora;
	// hora de la sesión en formato HH:MM
	private int asientosDisponibles;
	// es un entero que indica el número de asientos disponibles en la propia
	// sesión
	private int sigIdCompra;
	// es un entero que se incrementa cada vez que se realiza una venta de
	// entradas para la propia sesión. Se utiliza para generar identificadores
	// de venta diferentes para cada compra
	private int[][] estadoAsientos;
	// es una matriz de enteros con las mismas dimensiones que la sala asociada
	// a la propia sesión, en la que se representan las butacas compradas y las
	// disponibles para la propia sesión.
	// Si una butaca en la posición (i, j) está disponible, se guarda un valor
	// 0, y en caso contrario, se guarda el identificador de venta asociado a la
	// butaca. Como en una venta se puede comprar más de una butaca,
	// podría suceder que en la matriz haya varios identificadores de ventas
	// iguales en posiciones contiguas.

	public Sesion(String hora, int fila, int columnas) {
		// constructor de la clase Sesion que recibe como argumentos la hora de
		// proyección, y el número de filas y columnas de la sala asociada a la
		// propia sesión. Con estos argumentos,
		// inicializa los atributos del objeto. El atributo sigIdComprase
		// inicializa a 1.
		this.hora = hora;
		this.estadoAsientos = new int[fila][columnas];
		this.asientosDisponibles = fila * columnas;
		this.sigIdCompra = 1;
	}

	public String getHora() {
		// método que devuelve la hora asociada a la propia sesión
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
		// método que compra una entrada con la fila y columna dadas para la
		// propia sesión. Para registrar la venta, se guarda el valor actual del
		// atributo sigIdCompra en la posición
		// (fila-1, columna-1) del atributo estadoAsientos. A continuación, se
		// incrementa en uno el atributo sigIdCompra.
		this.estadoAsientos[fila - 1][columna - 1] = this.sigIdCompra;
		this.sigIdCompra++;
		this.asientosDisponibles--;

	}

	public int getIdEntrada(int fila, int columna) {
		// método que devuelve el identificador de venta para una entrada en la
		// propia sesión especificada mediante su fila y columna. El
		// identificador que se devuelve se toma de la posición
		// (fila-1, columna-1) del atributo estadoAsientos
		return this.estadoAsientos[fila - 1][columna - 1];
	}

	public char[][] getEstadoSesion() {
		// método que devuelve una matriz de caracteres en la que se representa
		// el estado de ocupación de la propia sesión. La matriz resultado tiene
		// el mismo número de filas y columnas que la sala asociada
		// a la propia sesión, y en cada posición (i, j) de la matriz debe
		// aparecer un carácter ‘#’ si la butaca ha sido comprada, y un carácter
		// ‘O’ (O mayúscula) si la butaca está disponible. En la figura 2 se
		// puede
		// observar un ejemplo de una matriz de caracteres que refleja el estado
		// de ocupación de una sala en una sesión
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
		// método que devuelve el número de butacas disponibles en la propia
		// sesión.
		return this.asientosDisponibles;
	}

	public String recogerEntradas(int id) {
		// método que devuelve las N entradas asociadas a un identificador de
		// venta dado para la propia sesión. Las N entradas se devolverán dentro
		// de un String con el siguiente formato:
		// “hora_de_la_sesión+fila1,columna1+fila2,columna2+ … +filaN,columnaN+”
		// Si el identificador de venta dado no existe en la propia sesión, se
		// devuelve null.
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
		// método que dados un número N de butacas, devuelve un objeto de tipo
		// ButacasContiguas que contiene la fila y la columna de la butaca
		// recomendaba con menor número de columna, y el número de butacas
		// solicitadas.
		// El algoritmo para obtener las butacas recomendadas es el siguiente:
		// 1. Se buscan las primeras N butacas contiguas libres empezando por la
		// fila (N_FILAS+1)/2+1 y acabando en la fila N_FILAS. Cada fila se
		// recorre
		// empezando por la columna N_COLUMNAS y acabando en la columna 1. 2. Si
		// en el paso anterior no se encuentran N butacas contiguas libres, se
		// buscan las primeras N butacas contiguas libres empezando por la fila
		// (N_FILAS+1)/2 y acabando en la fila 1. Cada fila se recorre empezando
		// por la columna N_COLUMNAS y acabando en la columna 1. Si no existen N
		// butacas libres contiguas en la sala para la propia sesión, se
		// devuelve null.
		// Por ejemplo, si tomamos la sala de la figura 2 y buscamos 3 butacas
		// contiguas libres, este algoritmo devolverá las butacas (4, 3), (4, 4)
		// y (4, 5).
		int recorrido = (this.estadoAsientos.length / 2) + 1;
		int contador = 0;
		boolean suma = true;
		int asientos;
		ButacasContiguas butacas = null;
		int columna = this.estadoAsientos[0].length;
		boolean aux = false;
		while (contador < this.estadoAsientos.length && !aux) {
			asientos = 0;
			for (int i = columna - 1; i > 0 && i - noButacas > 0 && !aux; i--) {
				if (this.estadoAsientos[recorrido][i] == 0) {
					asientos++;
				} else {
					asientos = 0;
				}
				if (asientos == noButacas) {
					butacas = new ButacasContiguas(recorrido + 1, i + 1, noButacas);
					aux = true;
				}
			}

			contador++;
			if (recorrido == this.estadoAsientos.length) {
				suma = false;
				recorrido = this.estadoAsientos.length / 2;
			}

			if (suma) {
				recorrido++;
			} else {
				recorrido--;
			}
		}
		return butacas;
	}

	public void comprarEntradasRecomendadas(ButacasContiguas butacas) {
		// método que dado un objeto de tipo ButacasContiguas, registra la
		// compra en la propia sesión guardando el valor actual del atributo
		// sigIdCompra
		// en las posiciones especificadas por el objeto dado como argumento. A
		// continuación, se incrementa en uno el atributo sigIdCompra.
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
