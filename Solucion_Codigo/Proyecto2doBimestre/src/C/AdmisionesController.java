package C;
import M.Carrera;
import M.Postulante;
import M.EstadoPostulacion;
import java.util.*;
import java.io.*;

public class AdmisionesController {
    private List<Postulante> postulantes;
    private List<Carrera> carreras;

    public AdmisionesController() {
        postulantes = new ArrayList<>();
        carreras = new ArrayList<>();
    }

    @SuppressWarnings("unchecked")
    public void cargarDatos() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("postulantes.dat"))) {
            postulantes = (List<Postulante>) ois.readObject();
        } catch (Exception e) {}
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("carreras.dat"))) {
            carreras = (List<Carrera>) ois.readObject();
        } catch (Exception e) {}
    }

    public void guardarDatos() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("postulantes.dat"))) {
            oos.writeObject(postulantes);
        } catch (IOException e) { e.printStackTrace(); }
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("carreras.dat"))) {
            oos.writeObject(carreras);
        } catch (IOException e) { e.printStackTrace(); }
    }

    public void inscribir(Postulante p) { postulantes.add(p); }
    public void agregarCarrera(Carrera c) { carreras.add(c); }

    public void procesarAdmisiones() {
        for (Carrera c : carreras) {
            List<Postulante> admitidos = c.asignarCupos(postulantes);
            for (Postulante p : postulantes) {
                if (p.getCarreraSeleccionada().equals(c)) {
                    if (admitidos.contains(p)) p.setEstado(EstadoPostulacion.ACEPTADO);
                    else p.setEstado(EstadoPostulacion.RECHAZADO);
                }
            }
        }
    }

    public List<Postulante> getPostulantes() { return postulantes; }
    public List<Carrera> getCarreras() { return carreras; }
}