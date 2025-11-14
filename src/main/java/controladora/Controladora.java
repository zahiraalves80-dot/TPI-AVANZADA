package controladora;

import java.time.LocalDate;
import persistencia.ControladoraPersistencia;
import modelo.Gato;
import modelo.FamiliaAdoptante;
import modelo.Postulacion; // (Asegúrate de tener esta clase)
import modelo.OperacionException;
import java.util.List;
import modelo.Administrador;
import modelo.LoginException;
import modelo.RegistroException;
import modelo.Usuario;
import modelo.Veterinario;
import modelo.Visita;
import modelo.Voluntario;


public class Controladora {
    // va a llamar a los metodos de controladora de persistencia que a su vez llama a los de jpa
    ControladoraPersistencia controlpersis = new ControladoraPersistencia();
    
    public Usuario validarUsuario(String correo, String contrasena) throws LoginException, Exception {
        
        // 1. Delegar la búsqueda a la capa de persistencia
        // (La persistencia solo busca por correo, que es lo que queremos)
        Usuario usuario = controlpersis.buscarUsuarioPorCorreo(correo);
        
        // 2. VALIDACIONES DE NEGOCIO
        
        // Validación 1: ¿Existe el usuario?
        if (usuario == null) {
            throw new LoginException("Usuario no encontrado. Verifique el correo.");
        }
        
        // Validación 2: ¿La contraseña coincide?
        if (!usuario.getContrasena().equals(contrasena)) {
            throw new LoginException("Contraseña incorrecta.");
        }
        
        // 3. Éxito: El usuario es válido
        // Retornamos el objeto completo (que incluye el ROL)
        return usuario;
    }
    
    public void registrarUsuarioPorRol(String nombre, String correo, String contrasena, 
                                       String telefono, String direccion, String matricula, 
                                       String rol) throws RegistroException {
        
        try {
            // 1. Conversión de Teléfono (De String a double)
            double telefonoDouble = Double.parseDouble(telefono);
            
            // 2. LÓGICA CONDICIONAL DE MATRÍCULA
            int matriculaInt = 0; // Valor por defecto
            
            if (rol.equals("VETERINARIO")) {
                // 🛑 Validación Específica: Matrícula obligatoria para Veterinarios
                if (matricula.isEmpty()) {
                    throw new RegistroException("El campo Matrícula es obligatorio para Veterinarios.");
                }
                // Intenta convertir la matrícula (puede fallar si ponen letras)
                try {
                    matriculaInt = Integer.parseInt(matricula); 
                } catch (NumberFormatException e) {
                    throw new RegistroException("La Matrícula debe ser un número entero válido.", e);
                }
            }
            
            Usuario nuevoUsuario = null; // Variable Polimórfica

            // 3. INSTANCIACIÓN Y DELEGACIÓN
            // (La validación de campos obligatorios generales se hizo en la Vista)
            switch (rol) {
                case "FAMILIA":
                    // Llama al constructor de FamiliaAdoptante
                    nuevoUsuario = new FamiliaAdoptante(
                        direccion, nombre, correo, contrasena, telefonoDouble, rol
                    );
                    // Llama al método específico en la capa de persistencia
                    controlpersis.crearFamiliaAdoptante((FamiliaAdoptante) nuevoUsuario);
                    break;
                    
                case "VETERINARIO":
                    // Llama al constructor de Veterinario
                    nuevoUsuario = new Veterinario(
                        matriculaInt, nombre, correo, contrasena, telefonoDouble, rol, direccion
                    );
                    controlpersis.crearVeterinario((Veterinario) nuevoUsuario);
                    break;
                    
                case "ADMINISTRADOR":
                    // Llama al constructor de Administrador
                    nuevoUsuario = new Administrador(
                        nombre, correo, contrasena, telefonoDouble, rol, direccion
                    );
                    controlpersis.crearAdministrador((Administrador) nuevoUsuario);
                    break;
                    
                case "VOLUNTARIO":
                    // Llama al constructor de Voluntario
                    nuevoUsuario = new Voluntario(
                        nombre, correo, contrasena, telefonoDouble, rol, direccion
                    );
                    controlpersis.crearVoluntario((Voluntario) nuevoUsuario);
                    break;
                
                default:
                    throw new RegistroException("Rol de usuario inválido.");
            }
            
        } catch (NumberFormatException e) {
            // Captura si el teléfono no es numérico
            throw new RegistroException("El campo Teléfono debe contener solo números válidos.", e);
        } catch (RegistroException e) {
            // Relanza la excepción de validación específica (ej. Matrícula vacía)
            throw e;
        } catch (Exception e) {
            // Captura errores de persistencia (ej. Correo duplicado, fallo en la DB)
            // Esto es crucial para tu TPI: notifica si el correo ya existe.
            throw new RegistroException("Fallo de persistencia: " + e.getMessage(), e);
        }
    }
    
    public Gato buscarGatoCompleto(int idGato) throws OperacionException {
    // 1. Llama a la persistencia (Este método ya lo creamos para "Postularse")
    Gato gato = controlpersis.buscarGato(idGato); 
    
    // 2. Validación de negocio
    if (gato == null) {
        throw new OperacionException("Error: No se pudo encontrar el gato seleccionado.");
    }
    
    return gato;
}
    
   public void crearPostulacion(int idGato, int idFamilia) throws OperacionException {
    try {
        Gato gato = controlpersis.buscarGato(idGato);
        FamiliaAdoptante familia = controlpersis.buscarFamilia(idFamilia);

        // ... (Validaciones de Gato y Familia)

        boolean yaExiste = controlpersis.existePostulacion(idGato, idFamilia);
        if (yaExiste) {
            throw new OperacionException("Ya te has postulado para este gato.");
        }
        
       
        // Compara si el Enum 'disponible' NO es 'SI'
        if (gato.getDisponible() != modelo.Gato.RespuestaBinaria.SI) { //
             throw new OperacionException("Lo sentimos, este gato no está disponible para adopción.");
        }
        // -----------------------------------------------------------

        // 5. Lógica de Creación (Corregida para tu clase Postulacion)
        Postulacion nuevaPostulacion = new Postulacion();
        nuevaPostulacion.setGatoRelacionado(gato);
        nuevaPostulacion.setFamiliaPostulante(familia);
        nuevaPostulacion.setEstado(Postulacion.Estado.PENDIENTE); 
        // (Tu constructor de Postulacion ya asigna la fecha)

        controlpersis.crearPostulacion(nuevaPostulacion);
        
    } catch (OperacionException e) {
        throw e; 
    } catch (Exception e) {
        throw new OperacionException("Error de persistencia al crear la postulación: " + e.getMessage(), e);
    }
}
   
  public List<Gato> traerGatosDisponibles() throws OperacionException {
        try {
            // 1. Llama a la persistencia
            List<Gato> gatos = controlpersis.buscarGatosDisponibles(); //
            
            // 2. Validación de negocio
            if (gatos == null || gatos.isEmpty()) {
                throw new OperacionException("No hay gatos disponibles para mostrar en este momento.");
            }
            
            return gatos;
            
        } catch (Exception e) {
            throw new OperacionException("Error al traer los gatos: " + e.getMessage(), e);
        }
    } 
    
    public List<Visita> traerTodasLasVisitas() {
        return controlpersis.traerTodasVisitas();
    }

    public List<Visita> filtrarVisitas(String nombreFamilia, String nombreVoluntario) {
        // Reemplazo los campos de la imagen por filtros que sí se pueden hacer
        // con tu modelo: Familia y Voluntario.
        return controlpersis.buscarVisitasFiltradas(nombreFamilia, nombreVoluntario);
    }
    
    public Visita buscarVisita(long idVisita) throws OperacionException {
        Visita v = controlpersis.buscarVisita(idVisita);
        if (v == null) {
            throw new OperacionException("No se encontró la visita con ID: " + idVisita);
        }
        return v;
    }
    
    public void modificarVisita(long idVisita, String nuevaDescripcion, LocalDate nuevaFecha) throws OperacionException {
        try {
            Visita visita = this.buscarVisita(idVisita); // Reutiliza el método
            
            visita.setDescripcion(nuevaDescripcion);
            visita.setFecha(nuevaFecha);
            
            controlpersis.editarVisita(visita);
            
        } catch (Exception e) {
            throw new OperacionException("Error al modificar la visita: " + e.getMessage(), e);
        }
    }
    
    public void eliminarVisita(long idVisita) throws OperacionException {
        try {
            controlpersis.eliminarVisita(idVisita);
        } catch (Exception e) {
            throw new OperacionException("Error al eliminar la visita: " + e.getMessage(), e);
        }
    }

    // (Este método ya lo teníamos para el ComboBox)
    public List<FamiliaAdoptante> traerTodasLasFamilias() {
        // Llama al método público de controlpersis, NO al jpa
        return controlpersis.traerTodasLasFamilias();
    }

    // (Este método también)
    public void registrarVisitaDeSeguimiento(int idFamilia, int idVoluntario, 
                                            LocalDate fecha, String descripcion) 
                                            throws OperacionException {
        // ... (la lógica de la respuesta anterior)
    }
}
