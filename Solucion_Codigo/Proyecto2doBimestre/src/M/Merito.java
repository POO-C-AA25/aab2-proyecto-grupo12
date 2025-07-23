package M;
public class Merito {
    public int calcularPuntajeAdicional(Postulante p) {
        int puntos = 0;
        if (p.isAbanderado()) puntos += 5;
        if (p.isAfinCarrera()) puntos += 2;
        if (p.getPorcentajeCapacidadEspecial() >= 35) puntos += 3;
        return puntos;
    }
}