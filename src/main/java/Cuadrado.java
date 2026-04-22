public class Cuadrado extends Figura {
    private int lado = 0;

    @Override
    public Long perimetro() {
        return (long) (lado * 4);
    }

    @Override
    public Long area() {
        return (long) (lado * lado);
    }

    @Override
    public void setDefaultParams() {
        if (this.lado == 0) this.lado = 4;
    }
}