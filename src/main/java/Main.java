import groovy.lang.GroovyClassLoader;
import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Lista de figuras que queremos intentar cargar
        List<String> nombresFiguras = new ArrayList<>();
        nombresFiguras.add("Cuadrado");   // Nativa
        nombresFiguras.add("Pentagono");  // Groovy

        String rutaScripts = "scripts/";
        GroovyClassLoader gcl = new GroovyClassLoader();

        for (String nombre : nombresFiguras) {
            try {
                Class<?> clazz;
                try {

                    clazz = Class.forName(nombre);
                } catch (ClassNotFoundException e) {

                    File scriptFile = new File(rutaScripts + nombre + ".groovy");
                    if (scriptFile.exists()) {
                        clazz = gcl.parseClass(scriptFile);
                    } else {
                        System.out.println("No se encontró la figura: " + nombre);
                        continue;
                    }
                }

                // Instanciación dinámica (usando constructor vacío)
                Figura fg = (Figura) clazz.getDeclaredConstructor().newInstance();


                try {
                    Field field = clazz.getDeclaredField("lado");
                    field.setAccessible(true);
                    field.set(fg, 10);
                } catch (NoSuchFieldException e) {
                    fg.setDefaultParams();
                }

                System.out.println("--- Figura: " + clazz.getSimpleName() + " ---");
                System.out.println("Área: " + fg.area());
                System.out.println("Perímetro: " + fg.perimetro());
                System.out.println("----------------------------");

            } catch (Exception e) {
                System.err.println("Error procesando " + nombre + ": " + e.getMessage());
            }
        }
    }
}