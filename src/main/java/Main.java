import java.util.Scanner;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("SISTEMA DE FIGURAS DINÁMICAS ACTIVO");
        System.out.println("Escribe el nombre de la figura (o 'salir'):");

        while (true) {
            try {
                System.out.print("\nFigura > ");
                String nombre = scanner.next();
                if (nombre.equalsIgnoreCase("salir")) break;

                System.out.print("Valor del lado/radio > ");
                double valor = scanner.nextDouble();

                Figura f = FiguraFactory.crear(nombre, Map.of("lado", valor, "radio", valor));

                System.out.println("Resultado: Área = " + f.area() + " | Perímetro = " + f.perimetro());

            } catch (Exception e) {
                System.err.println("Error: " + e.getMessage());
            }
        }
        scanner.close();
    }
}