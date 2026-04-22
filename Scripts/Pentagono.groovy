import Figura;
class Pentagono extends Figura {
    int lado = 0

    @Override
    Long perimetro() {
        return (Long) (lado * 5)
    }

    @Override
    Long area() {
        // Fórmula simplificada
        return (Long) Math.round(lado * lado * 1.72)
    }

    @Override
    void setDefaultParams() {
        if (this.lado == 0) this.lado = 5
    }
}