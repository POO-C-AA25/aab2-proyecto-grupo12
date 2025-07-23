package M;
import java.util.Date;

public class ExamenDiagnostico extends Examen {
    private double porcentajeMinimo;

    public ExamenDiagnostico(Date fecha, double puntajeObtenido, double porcentajeMinimo) {
        super(fecha, puntajeObtenido);
        this.porcentajeMinimo = porcentajeMinimo;
    }

    @Override
    public boolean esAprobado() {
        double minimo = (PUNTAJE_MAXIMO * porcentajeMinimo) / 100.0;
        return puntajeObtenido >= minimo;
    }

    @Override
    public String toString() {
        return String.format("ExamenDiagnostico[fecha=%s, puntaje=%.2f, pctMin=%.0f%%]",
                fecha, puntajeObtenido, porcentajeMinimo);
    }
}