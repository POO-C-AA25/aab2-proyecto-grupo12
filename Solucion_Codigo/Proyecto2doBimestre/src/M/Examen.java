package M;
import java.io.Serializable;
import java.util.Date;

public abstract class Examen implements Serializable {
    protected Date fecha;
    protected static final double PUNTAJE_MAXIMO = 100;
    protected double puntajeObtenido;

    public Examen(Date fecha, double puntajeObtenido) {
        this.fecha = fecha;
        this.puntajeObtenido = puntajeObtenido;
    }

    public abstract boolean esAprobado();

    @Override
    public String toString() {
        return String.format("Examen[fecha=%s, puntaje=%.2f]", fecha, puntajeObtenido);
    }
}