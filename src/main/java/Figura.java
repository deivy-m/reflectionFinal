import java.util.List;

public interface Figura {
    double area();
    double perimetro();
    default void setDefaultParams() {}
}
