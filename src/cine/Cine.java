package cine;

public class Cine {
	private String nombre;
	private int [] salas;
	
	public Cine(String nombre, int [] salas){
		this.nombre = nombre;
		this.salas = salas;
	}
	
	public void comprarEntradas(int sala, int sesion, int fila, int columna){
		
	}
	
	public ButacasContiguas recomendarButacasContiguas(int noButacas, int sala, int sesion){
		
	}
	
	public char [][] getIdEntrada(int sala, int sesion){
		
	}
	
	public String [] getPeliculas(){
		
	}
	
	public String [] getEstadoSesionDeSala(int sala){
		
	}
	
	public String recogerEntradas(int id, int sala, int sesion){
		
	}
	
	public int getButacasDisponiblesSesion(int sala, int sesion){
		
	}
	
	public void comprarEntradasRecomendadas(int sala, int sesion, ButacasContiguas butacas){
		
	}
	
	public void incluirSesion(int sala, String horaSesion){
		
	}
	
	public void borrarSesion(int sala, String horaSesion){
		
	}
}
