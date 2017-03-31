package comprobaratributos;



import static org.junit.Assert.fail;

import java.lang.reflect.Field;

import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.DisableOnDebug;
import org.junit.rules.TestRule;
import org.junit.rules.Timeout;

public class TestAtributosCine {

	private static Class<?> clazz; 
	private static ArrayList<Atributo> atributos = new ArrayList<Atributo>();
	

	private static final String PKG = "cine.";
	private static final String CLAZZ = PKG + "Cine";
	

	@Rule //Se establece un time out general para todos los tests. Se debe comentar esta línea y la de abajo para depurar
	public TestRule  globalTimeout = new DisableOnDebug(Timeout.millis(100)); // 100 milisegundos máximos por test

	@BeforeClass
	public static void setup () {
		
		try {
			clazz = Class.forName(CLAZZ);
		} catch (ClassNotFoundException e) {
			fail("No se ha encontrado la clase '" + CLAZZ + "': No se continúa con el resto de tests."); 
		} 
		
		
		atributos.add(new Atributo("nombre","java.lang.String")); 
		atributos.add(new Atributo("salas","cine.Sala[]"));	
		
	}
	

	@Test
	public void testClass() {

	}

	
	

	

	@Test 
	public void testAtributos () {
		for (int i = 0; i<atributos.size(); i++){
			testAtributo(atributos.get(i)); 
		}
	}

	
	
	

	private void testAtributo (Atributo atributo) {
		Field field=null;
		try {
			field = clazz.getDeclaredField(atributo.getNombre());
			
			if (!atributo.getTipoConvertido().equals(field.getType().getName())) {
				fail("Se ha encontrado el atributo " + atributo.getNombre() +  " no coincide el tipo esperado (" + atributo.getTipo()+")"); 
			}
			
		} catch (IllegalArgumentException e) {
			fail("No se ha podido acceder al atributo " + atributo + " en la clase " + clazz);
		} catch (NoSuchFieldException e) {
			fail("No se ha encontrado el atributo " + atributo + " en la clase " + clazz); 
		} catch (SecurityException e) {
			fail("No se ha podido acceder al atributo " + atributo + " en la clase " + clazz);
		}
	}

	static class Miembro {
		private String nombre;
		
		public Miembro (String nombre) {
			this.nombre= nombre; 
		}
		
		public String getNombre () {
			return nombre; 
		}
	}
	
	static class Atributo extends Miembro {
		
		private String tipo;

		public Atributo(String nombre, String tipo) {
			super(nombre);
			this.tipo = tipo;
		}
		
		public String getTipo () {
			return tipo; 
		}
		
		public String getTipoConvertido() {
			return convertirTipo(this.tipo);
		} 

		@Override
		public String toString() {
			return "<" + tipo + " " + getNombre() + ">";   
		}
	}

	
	private static String convertirTipo (String tipo) {
		if (tipo.endsWith("[]")){
			return "[" + getTipo(tipo.substring(0,tipo.length()-2)); 
		}
		else {
			return tipo;
		}
	}
	
	private static String getTipo (String tipo) {
		if (tipo.equals("int")) return "I"; 
		else if (tipo.equals("long")) return "J"; 
		else if (tipo.equals("float")) return "F"; 
		else if (tipo.equals("double")) return "D"; 
		else if (tipo.equals("char")) return "C"; 
		else if (tipo.equals("byte")) return "B"; 
		else if (tipo.equals("void")) return "V";
		else return "L" + tipo + ";"; 
		
	}
	
	
	
	
}

