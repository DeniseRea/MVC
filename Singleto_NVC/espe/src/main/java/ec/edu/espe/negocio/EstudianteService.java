package ec.edu.espe.negocio;

import ec.edu.espe.datos.model.Estudiante;
import ec.edu.espe.datos.repository.EstudianteRepository;
import java.util.List;

public class EstudianteService {
    private EstudianteRepository repository;

    public EstudianteService() {
        // USO DEL SINGLETON
        this.repository = EstudianteRepository.getInstance();
    }

    public void guardar(String id, String nombre, String edadStr) throws Exception {
        validar(id, nombre, edadStr);
        if (repository.buscarPorId(id) != null) throw new Exception("Cédula repetida");
        repository.crear(new Estudiante(id, nombre, Integer.parseInt(edadStr)));
    }

    public void editar(String id, String nombre, String edadStr) throws Exception {
        validar(id, nombre, edadStr);
        if (repository.buscarPorId(id) == null) throw new Exception("Estudiante no existe");
        repository.actualizar(new Estudiante(id, nombre, Integer.parseInt(edadStr)));
    }

    public void eliminar(String id) throws Exception {
        if (repository.buscarPorId(id) == null) throw new Exception("No existe");
        repository.eliminar(id);
    }

    public Estudiante buscar(String id) {
        return repository.buscarPorId(id);
    }

    public List<Estudiante> listar() {
        return repository.listar();
    }

    private void validar(String id, String nom, String edad) throws Exception {
        if (id.isEmpty() || nom.isEmpty() || edad.isEmpty()) throw new Exception("Campos vacíos");
        if (!id.matches("\\d{10}")) throw new Exception("Cédula inválida"); // Validación simple
    }
}