package ec.edu.espe.logica_negocio;

import ec.edu.espe.datos.factory.EstudianteFactory;
import ec.edu.espe.datos.model.Estudiante;
import ec.edu.espe.datos.repository.EstudianteRepository;
import java.util.List;

public class EstudianteService {
    private EstudianteRepository repository;

    public EstudianteService() {
        this.repository = new EstudianteRepository();
    }

    public void guardarEstudiante(String id, String nombres, String edadStr) throws Exception {
        // 1. Validar que NO se repita (lógica de negocio)
        if (repository.buscarPorId(id) != null) {
            throw new Exception("Ya existe un estudiante con la cédula: " + id);
        }

        // 2. Crear estudiante usando Factory (validaciones centralizadas)
        Estudiante estudiante = EstudianteFactory.crearEstudiante(id, nombres, edadStr);
        
        // 3. Guardar en repositorio
        repository.crear(estudiante);
    }

    public void editarEstudiante(String id, String nombres, String edadStr) throws Exception {
        // Validamos existencia
        if (repository.buscarPorId(id) == null) {
            throw new Exception("No se puede editar. El estudiante no existe.");
        }
        
        // Crear estudiante actualizado usando Factory (sin validar duplicidad)
        Estudiante estudiante = EstudianteFactory.crearParaActualizacion(id, nombres, edadStr);
        repository.actualizar(estudiante);
    }

    public void eliminarEstudiante(String id) {
        repository.eliminar(id);
    }

    public List<Estudiante> obtenerEstudiantes() {
        return repository.listar();
    }

    // Método para exponer la búsqueda al UI
    public Estudiante buscarPorCedula(String cedula) throws Exception {
        if (cedula == null || cedula.trim().isEmpty()) {
            throw new Exception("Debe ingresar una cédula.");
        }
        return repository.buscarPorId(cedula.trim());
    }
}