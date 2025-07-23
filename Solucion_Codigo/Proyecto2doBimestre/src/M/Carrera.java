package M;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

public class Carrera implements Serializable {
    private String codigo;
    private String nombre;
    private int cupos;
    private TipoExamen tipoExamen;
    private double parametroMin;

    public Carrera(String codigo, String nombre, int cupos, TipoExamen tipoExamen, double parametroMin) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.cupos = cupos;
        this.tipoExamen = tipoExamen;
        this.parametroMin = parametroMin;
    }

    public List<Postulante> asignarCupos(List<Postulante> postulantes) {
        List<Postulante> seleccionados;
        if (tipoExamen == TipoExamen.DIAGNOSTICO) {
            seleccionados = postulantes.stream()
                    .filter(p -> p.getCarreraSeleccionada().equals(this))
                    .limit(cupos)
                    .collect(Collectors.toList());
        } else {
            seleccionados = postulantes.stream()
                    .filter(p -> p.getCarreraSeleccionada().equals(this))
                    .filter(p -> p.getPuntajeExamen() + p.calcularMerito() >= parametroMin)
                    .sorted((a, b) -> Double.compare(b.getPuntajeExamen() + b.calcularMerito(),
                            a.getPuntajeExamen() + a.calcularMerito()))
                    .limit(cupos)
                    .collect(Collectors.toList());
        }
        return seleccionados;
    }

    public String getNombre() { return nombre; }
    public TipoExamen getTipoExamen() { return tipoExamen; }
    public double getParametroMin() { return parametroMin; }

    @Override
    public String toString() {
        return String.format("Carrera[codigo=%s, nombre=%s, cupos=%d, tipo=%s, min=%.2f]",
                codigo, nombre, cupos, tipoExamen, parametroMin);
    }
}