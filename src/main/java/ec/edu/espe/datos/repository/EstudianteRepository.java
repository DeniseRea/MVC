package ec.edu.espe.datos.repository;

import ec.edu.espe.datos.model.Estudiante;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class EstudianteRepository {
    private List<Estudiante> estudiantes;
    private final String FILE_NAME = "estudiantes.txt";

    public EstudianteRepository() {
        this.estudiantes = new ArrayList<>();
        cargarDesdeArchivo(); // Carga datos al iniciar
    }

    public void crear(Estudiante estudiante) {
        estudiantes.add(estudiante);
        guardarEnArchivo(); // Guarda cambios
    }

    public List<Estudiante> listar() {
        return estudiantes;
    }

    public Estudiante buscarPorId(String id) {
        for (Estudiante est : estudiantes) {
            if (est.getId().equals(id)) {
                return est;
            }
        }
        return null;
    }

    public void actualizar(Estudiante estudianteActualizado) {
        for (int i = 0; i < estudiantes.size(); i++) {
            if (estudiantes.get(i).getId().equals(estudianteActualizado.getId())) {
                estudiantes.set(i, estudianteActualizado);
                break;
            }
        }
        guardarEnArchivo(); // Actualiza el TXT
    }

    public void eliminar(String id) {
        estudiantes.removeIf(e -> e.getId().equals(id));
        guardarEnArchivo(); // Actualiza el TXT
    }

    // --- MÉTODOS PRIVADOS PARA MANEJO DE ARCHIVOS ---

    private void guardarEnArchivo() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Estudiante est : estudiantes) {
                // Formato CSV: id,nombre,edad
                writer.write(est.getId() + "," + est.getNombres() + "," + est.getEdad());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error al guardar en archivo: " + e.getMessage());
        }
    }

    private void cargarDesdeArchivo() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(",");
                if (partes.length == 3) {
                    Estudiante est = new Estudiante(partes[0], partes[1], Integer.parseInt(partes[2]));
                    estudiantes.add(est);
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer archivo: " + e.getMessage());
        }
    }
}