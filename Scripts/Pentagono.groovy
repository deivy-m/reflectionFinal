import Figura

class Pentagono implements Figura {
    double lado = 0

    @Override
    double perimetro() {
        return lado * 5
    }

    @Override
    double area() {
        return Math.round((lado * lado * 1.7204) * 100) / 100
    }
}