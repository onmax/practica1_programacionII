package interfazusuario;

import java.util.Scanner;

public class LecturaTeclado {
	private Scanner teclado;
	
	public LecturaTeclado(){
		teclado = new Scanner(System.in);
	}

	public int leerNatural(String msg){
		String entrada = null;
		do {
			System.out.print(msg);
			entrada = teclado.next();
		} while (!entrada.matches("\\d+") || Integer.valueOf(entrada) == 0);	
		return Integer.valueOf(entrada);
	}
	
	public char leerCaracter(String msg){
		String entrada = null;
		do {
			System.out.print(msg);
			entrada = teclado.next();
		} while ((entrada.length() > 1));	
		return entrada.charAt(0);
	}
	
	public String leerString(String msg){
		System.out.print(msg);
		return teclado.next();
	}
}
