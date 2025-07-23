package V;
import C.AdmisionesController;
import M.Postulante;
import M.Carrera;
import M.TipoExamen;

import java.text.SimpleDateFormat;
import java.util.*;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final AdmisionesController ctrl = new AdmisionesController();
    private static final SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");

    public static void main(String[] args) {
        ctrl.cargarDatos();
        boolean exit = false;
        while (!exit) {
            mostrarMenu();
            int op = Integer.parseInt(scanner.nextLine());
            switch (op) {
                case 1: registrarCarrera(); break;
                case 2: registrarPostulante(); break;
                case 3:
                    ctrl.procesarAdmisiones();
                    System.out.println("Admisiones procesadas.");
                    break;
                case 4: mostrarResultados(); break;
                case 0:
                    exit = true;
                    ctrl.guardarDatos();
                    break;
                default: System.out.println("Opción inválida.");
            }
        }
        scanner.close();
    }

    private static void mostrarMenu() {
        System.out.println("\n=== Gestor de Admisiones UTPL ===");
        System.out.println("=== BY JUAN TACURI & ANTHONY VICENTE ===");
        System.out.println("1. Registrar carrera");
        System.out.println("2. Inscribir postulante");
        System.out.println("3. Procesar admisiones");
        System.out.println("4. Mostrar resultados");
        System.out.println("0. Salir");
        System.out.print("Seleccione opción: ");
    }

    private static void registrarCarrera() {
        try {
            System.out.print("Código Carrera: "); String codigo = scanner.nextLine();
            System.out.print("Nombre Carrera: "); String nombre = scanner.nextLine();
            System.out.print("Cupos: "); int cupos = Integer.parseInt(scanner.nextLine());
            System.out.print("Tipo de Examen (ADMISION/DIAGNOSTICO): ");
            TipoExamen tipo = TipoExamen.valueOf(scanner.nextLine().toUpperCase());
            System.out.print("Valor mínimo (puntaje o %): "); double param = Double.parseDouble(scanner.nextLine());
            Carrera c = new Carrera(codigo, nombre, cupos, tipo, param);
            ctrl.agregarCarrera(c);
            ctrl.guardarDatos();
            System.out.println("Carrera registrada y guardada en carreras.dat.");
        } catch (Exception e) {
            System.out.println("Error en datos de carrera.");
        }
    }

    private static void registrarPostulante() {
        try {
            List<Carrera> lista = ctrl.getCarreras();
            if (lista.isEmpty()) {
                System.out.println("Debe registrar al menos una carrera primero.");
                return;
            }
            System.out.print("ID: "); String id = scanner.nextLine();
            System.out.print("Nombre: "); String nom = scanner.nextLine();
            System.out.print("Apellido: "); String ape = scanner.nextLine();
            System.out.print("Fecha Nac (dd/MM/yyyy): "); Date fn = fmt.parse(scanner.nextLine());
            System.out.print("Puntaje Examen: "); double pt = Double.parseDouble(scanner.nextLine());
            System.out.println("Carreras disponibles:");
            for (int i = 0; i < lista.size(); i++) {
                System.out.printf("%d) %s\n", i+1, lista.get(i));
            }
            System.out.print("Seleccione carrera (número): ");
            Carrera sel = lista.get(Integer.parseInt(scanner.nextLine()) - 1);
            System.out.print("Abanderado Bachillerato (s/n): "); boolean ab = scanner.nextLine().equalsIgnoreCase("s");
            System.out.print("Afinidad a la carrera (s/n): "); boolean af = scanner.nextLine().equalsIgnoreCase("s");
            System.out.print("Porcentaje Capacidad Especial: "); double pct = Double.parseDouble(scanner.nextLine());
            Postulante p = new Postulante(id, nom, ape, fn, pt, sel, ab, af, pct);
            ctrl.inscribir(p);
            ctrl.guardarDatos();
            System.out.println("Postulante inscrito.");
        } catch (Exception e) {
            System.out.println("Error en datos ingresados.");
        }
    }

    private static void mostrarResultados() {
        System.out.println("\n--- Carreras Registradas ---");
        for (Carrera c : ctrl.getCarreras()) {
            System.out.println(c);
        }
        System.out.println("\n--- Postulantes ---");
        for (Postulante p : ctrl.getPostulantes()) {
            System.out.println(p);
        }
    }
}
