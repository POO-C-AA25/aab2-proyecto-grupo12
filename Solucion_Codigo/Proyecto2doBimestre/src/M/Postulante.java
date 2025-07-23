package M;
import java.io.Serializable;
import java.util.Date;

public class Postulante implements Serializable {
    private String id;
    private String nombre;
    private String apellido;
    private Date fechaNacimiento;
    private double puntajeExamen;
    private EstadoPostulacion estado;
    private Carrera carreraSeleccionada;
    private boolean abanderado;
    private boolean afinCarrera;
    private double porcentajeCapacidadEspecial;

    public Postulante(String id, String nombre, String apellido, Date fechaNacimiento,
                      double puntajeExamen, Carrera carreraSeleccionada,
                      boolean abanderado, boolean afinCarrera, double porcentajeCapacidadEspecial) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.puntajeExamen = puntajeExamen;
        this.estado = EstadoPostulacion.INSCRITO;
        this.carreraSeleccionada = carreraSeleccionada;
        this.abanderado = abanderado;
        this.afinCarrera = afinCarrera;
        this.porcentajeCapacidadEspecial = porcentajeCapacidadEspecial;
    }

    public double calcularMerito() {
        Merito m = new Merito();
        return m.calcularPuntajeAdicional(this);
    }

    public String getId() {
        return id; 
    }
    
    public String getNombre() {
        return nombre; 
    }
    
    public String getApellido() {
        return apellido; 
    }
    
    public Date getFechaNacimiento() {
        return fechaNacimiento; 
    }
    
    public double getPuntajeExamen() {
        return puntajeExamen; 
    }
    
    public EstadoPostulacion getEstado() {
        return estado; 
    }
    
    public Carrera getCarreraSeleccionada() {
        return carreraSeleccionada; 
    }
    
    public boolean isAbanderado() {
        return abanderado; 
    }
    
    public boolean isAfinCarrera() {
        return afinCarrera; 
    }
    
    public double getPorcentajeCapacidadEspecial() {
        return porcentajeCapacidadEspecial; 
    }
    
    public void setEstado(EstadoPostulacion estado) {
        this.estado = estado; 
    }

    @Override
    public String toString() {
        return String.format("Postulante[id=%s, nombre=%s %s, carrera=%s, puntaje=%.2f, merito=%.2f, estado=%s]",
                id, nombre, apellido,
                carreraSeleccionada.getNombre(),
                puntajeExamen,
                calcularMerito(),
                estado);
    }
}