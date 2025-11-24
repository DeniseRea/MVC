package ec.edu.espe.logica_negocio;

import ec.edu.espe.datos.model.Estudiante;
import ec.edu.espe.datos.repository.EstudianteRepository;
import java.util.List;

public class EstudianteService {
    // Patrón Singleton
    private static EstudianteService instancia;

    private EstudianteRepository repository;

    // Constructor privado para evitar instanciación directa
    private EstudianteService() {
        this.repository = EstudianteRepository.getInstancia();
    }

    // Método para obtener la única instancia (thread-safe)
    public static synchronized EstudianteService getInstancia() {
        if (instancia == null) {
            instancia = new EstudianteService();
        }
        return instancia;
    }

    public void guardarEstudiante(String id, String nombres, String edadStr) throws Exception {
        // 1. Validaciones básicas
        if (id.isEmpty() || nombres.isEmpty() || edadStr.isEmpty()) {
            throw new Exception("Todos los campos son obligatorios.");
        }

        // 2. Validar Cédula Ecuatoriana
        if (!validarCedula(id)) {
            throw new Exception("La cédula ingresada es incorrecta.");
        }

        // 3. Validar que NO se repita
        if (repository.buscarPorId(id) != null) {
            throw new Exception("Ya existe un estudiante con la cédula: " + id);
        }

        int edad;
        try {
            edad = Integer.parseInt(edadStr);
        } catch (NumberFormatException e) {
            throw new Exception("La edad debe ser un número.");
        }

        if (edad <= 0) {
            throw new Exception("La edad debe ser mayor a 0.");
        }

        Estudiante estudiante = new Estudiante(id, nombres, edad);
        repository.crear(estudiante);
    }

    public void editarEstudiante(String id, String nombres, String edadStr) throws Exception {
        // Validamos existencia
        if (repository.buscarPorId(id) == null) {
            throw new Exception("No se puede editar. El estudiante no existe.");
        }
        // Validamos datos básicos (sin validar cédula de nuevo ni duplicado, pues es el mismo ID)
        int edad = Integer.parseInt(edadStr);
        Estudiante estudiante = new Estudiante(id, nombres, edad);
        repository.actualizar(estudiante);
    }

    public void eliminarEstudiante(String id) {
        repository.eliminar(id);
    }

    public List<Estudiante> obtenerEstudiantes() {
        return repository.listar();
    }

    // Método nuevo para exponer la búsqueda al UI
    public Estudiante buscarPorCedula(String cedula) throws Exception {
        if (!validarCedula(cedula)) {
            throw new Exception("Formato de cédula inválido.");
        }
        return repository.buscarPorId(cedula);
    }

    // --- ALGORITMO DE VALIDACIÓN DE CÉDULA ECUATORIANA ---
    private boolean validarCedula(String cedula) {
        if (cedula == null || cedula.length() != 10) return false;
        try {
            int provincia = Integer.parseInt(cedula.substring(0, 2));
            if (provincia < 1 || provincia > 24) return false;

            int tercerDigito = Integer.parseInt(cedula.substring(2, 3));
            if (tercerDigito >= 6) return false; // Solo personas naturales

            int[] coeficientes = {2, 1, 2, 1, 2, 1, 2, 1, 2};
            int total = 0;

            for (int i = 0; i < 9; i++) {
                int valor = Integer.parseInt(String.valueOf(cedula.charAt(i))) * coeficientes[i];
                total += (valor > 9) ? valor - 9 : valor;
            }

            int digitoVerificador = Integer.parseInt(String.valueOf(cedula.charAt(9)));
            int decenaSuperior = (total % 10 == 0) ? total : (total / 10 + 1) * 10;

            return (decenaSuperior - total) == digitoVerificador;

        } catch (NumberFormatException e) {
            return false;
        }
    }
}