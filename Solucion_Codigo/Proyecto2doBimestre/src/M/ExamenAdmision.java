package M;
import java.util.Date;

public class ExamenAdmision extends Examen {
    private double puntajeMinimo;

    public ExamenAdmision(Date fecha, double puntajeObtenido, double puntajeMinimo) {
        super(fecha, puntajeObtenido);
        this.puntajeMinimo = puntajeMinimo;
    }

    @Override
    public boolean esAprobado() {
        return puntajeObtenido >= puntajeMinimo;
    }

    @Override
    public String toString() {
        return String.format("ExamenAdmision[fecha=%s, puntaje=%.2f, min=%.2f]", fecha, puntajeObtenido, puntajeMinimo);
    }
}