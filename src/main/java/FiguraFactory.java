import groovy.lang.GroovyClassLoader;
import java.io.File;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class FiguraFactory {
    private static final String RUTA_SCRIPTS = "scripts/";
    private static final GroovyClassLoader loader = new GroovyClassLoader();


    private static final Map<String, CacheEntry> cache = new HashMap<>();

    private static class CacheEntry {
        Class<?> clazz;
        long lastModified;

        CacheEntry(Class<?> clazz, long lastModified) {
            this.clazz = clazz;
            this.lastModified = lastModified;
        }
    }

    public static Figura crear(String nombre, Map<String, Object> params) throws Exception {
        Class<?> clazz = obtenerClase(nombre);
        Figura instancia = (Figura) clazz.getDeclaredConstructor().newInstance();

        for (Map.Entry<String, Object> entry : params.entrySet()) {
            try {
                Field field = clazz.getDeclaredField(entry.getKey());
                field.setAccessible(true);
                Object value = entry.getValue();
                if (value instanceof Integer) value = ((Integer) value).doubleValue();
                field.set(instancia, value);
            } catch (NoSuchFieldException ignored) {}
        }
        return instancia;
    }

    private static Class<?> obtenerClase(String nombre) throws Exception {
        File file = new File(RUTA_SCRIPTS + nombre + ".groovy");


        if (file.exists()) {
            long lastMod = file.lastModified();
            if (!cache.containsKey(nombre) || cache.get(nombre).lastModified < lastMod) {
                System.out.println("[Sistema] Cargando/Actualizando figura dinámica: " + nombre);
                Class<?> nuevaClase = loader.parseClass(file);
                cache.put(nombre, new CacheEntry(nuevaClase, lastMod));
            }
            return cache.get(nombre).clazz;
        }


        if (!cache.containsKey(nombre)) {
            try {
                Class<?> nativa = Class.forName(nombre);
                cache.put(nombre, new CacheEntry(nativa, 0));
            } catch (ClassNotFoundException e) {
                throw new IllegalArgumentException("La figura '" + nombre + "' no existe en Java ni en scripts.");
            }
        }

        return cache.get(nombre).clazz;
    }
}