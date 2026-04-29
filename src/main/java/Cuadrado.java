public class Cuadrado implements Figura {
    private double lado;

    public Cuadrado() {}

    @Override
    public double area() { return lado * lado; }

    @Override
    public double perimetro() { return lado * 4; }
}