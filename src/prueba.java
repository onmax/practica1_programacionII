import cine.Sesion;
public class prueba {
	
	
	public static void main (String [] args){
		Sesion sesion1 = new Sesion("10:20",2,3);
		Sesion sesion2 = new Sesion("10:30",2,3);
		System.out.println(sesion1.equals(sesion2));
		 sesion1.comprarEntrada(1, 1);
		 sesion1.comprarEntrada(1, 3);
		 sesion1.comprarEntrada(2, 3);
		 
		System.out.println(sesion1.recogerEntradas(1));
	}
}