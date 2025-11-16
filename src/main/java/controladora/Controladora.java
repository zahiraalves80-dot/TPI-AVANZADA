package controladora;

import java.time.LocalDate;
import java.util.Date;
import persistencia.ControladoraPersistencia;
import modelo.Gato;
import modelo.FamiliaAdoptante;
import modelo.Postulacion;
import modelo.OperacionException;
import java.util.List;
import modelo.Administrador;
import modelo.Estudio;
import modelo.HistoriaClinica;
import modelo.LoginException;
import modelo.RegistroException;
import modelo.Tarea;
import modelo.Tratamiento;
import modelo.Usuario;
import modelo.Veterinario;
import modelo.Visita;
import modelo.Voluntario;
import modelo.Zona;

public class Controladora {
    
    ControladoraPersistencia controlpersis = new ControladoraPersistencia();
    
    public Usuario validarUsuario(String correo, String contrasena) throws LoginException, Exception {
        Usuario usuario = controlpersis.buscarUsuarioPorCorreo(correo);
        if (usuario == null) {
            throw new LoginException("Usuario no encontrado. Verifique el correo.");
        }
        if (!usuario.getContrasena().equals(contrasena)) {
            throw new LoginException("Contraseña incorrecta.");
        }
        return usuario;
    }
    
    // --- LÓGICA DE GATO Y VETERINARIO ---

    public List<Gato> traerTodosLosGatos() throws OperacionException {
        try {
            List<Gato> gatos = controlpersis.traerTodosLosGatos();
            if (gatos == null || gatos.isEmpty()) {
                throw new OperacionException("No hay ningún gato registrado en el sistema.");
            }
            return gatos;
        } catch (Exception e) {
            throw new OperacionException("Error al traer los gatos: " + e.getMessage(), e);
        }
    }

    public Gato buscarGatoCompleto(long idGato) throws OperacionException { 
        Gato gato = controlpersis.buscarGato(idGato); 
        if (gato == null) {
            throw new OperacionException("Error: No se pudo encontrar el gato seleccionado.");
        }
        return gato;
    }

    public void modificarEstadoSaludGato(long idGato, Gato.EstadoSalud nuevoEstado) throws OperacionException {
        try {
            Gato gato = controlpersis.buscarGato(idGato);
            if (gato == null) {
                throw new OperacionException("No se encontró el gato a modificar.");
            }
            gato.setestadoFisico(nuevoEstado); //
            controlpersis.modificarGato(gato);
        } catch (Exception e) {
            throw new OperacionException("Error al modificar el estado de salud: " + e.getMessage(), e);
        }
    }

    public void emitirCertificadoAptitud(long idGato, int idVeterinario) throws OperacionException {
        // Lógica futura para crear un objeto Aptitud
        throw new OperacionException("La función 'Emitir Certificado' aún no está implementada.");
    }
    
    // --- LÓGICA DE HISTORIA CLÍNICA ---
    
    public HistoriaClinica buscarHistoriaClinica(long idHistoria) throws OperacionException {
        HistoriaClinica hc = controlpersis.buscarHistoriaClinica(idHistoria);
        if (hc == null) {
            throw new OperacionException("No se encontró la Historia Clínica.");
        }
        return hc;
    }

    public void agregarTratamientoAHistoria(long idHistoria, String diagnostico, String descripcion) throws OperacionException {
        if (diagnostico.isEmpty() || descripcion.isEmpty()) {
            throw new OperacionException("El diagnóstico y la descripción son obligatorios.");
        }
        try {
            HistoriaClinica hc = buscarHistoriaClinica(idHistoria);
            Tratamiento nuevoTratamiento = new Tratamiento(diagnostico, descripcion, hc); //
            controlpersis.crearTratamiento(nuevoTratamiento); 
        } catch (Exception e) {
            throw new OperacionException("Error al guardar el tratamiento: " + e.getMessage(), e);
        }
    }

    public void agregarEstudioAHistoria(long idHistoria, String nombreEstudio, String resultado) throws OperacionException {
         if (nombreEstudio.isEmpty() || resultado.isEmpty()) {
            throw new OperacionException("El nombre del estudio y el resultado son obligatorios.");
        }
        try {
            HistoriaClinica hc = buscarHistoriaClinica(idHistoria);
            Estudio nuevoEstudio = new Estudio(); //
            nuevoEstudio.setNombreEstudio(nombreEstudio); //
            nuevoEstudio.setDescripcion(resultado); //
            nuevoEstudio.setHistoriaClinica(hc); //
            controlpersis.crearEstudio(nuevoEstudio);
        } catch (Exception e) {
            throw new OperacionException("Error al guardar el estudio: " + e.getMessage(), e);
        }
    }
    
    // --- LÓGICA DE USUARIOS Y REGISTRO ---
    
    public void registrarUsuarioPorRol(String nombre, String correo, String contrasena, 
                                       String telefono, String direccion, String matricula, 
                                       String rol) throws RegistroException {
        try {
            double telefonoDouble = Double.parseDouble(telefono);
            int matriculaInt = 0; 
            
            if (rol.equals("VETERINARIO")) {
                if (matricula.isEmpty()) {
                    throw new RegistroException("El campo Matrícula es obligatorio para Veterinarios.");
                }
                try {
                    matriculaInt = Integer.parseInt(matricula); 
                } catch (NumberFormatException e) {
                    throw new RegistroException("La Matrícula debe ser un número entero válido.", e);
                }
            }
            
            Usuario nuevoUsuario = null; 

            switch (rol) {
                case "FAMILIA":
                    nuevoUsuario = new FamiliaAdoptante(
                        direccion, nombre, correo, contrasena, telefonoDouble, rol
                    );
                    controlpersis.crearFamiliaAdoptante((FamiliaAdoptante) nuevoUsuario);
                    break;
                case "VETERINARIO":
                    nuevoUsuario = new Veterinario(
                        matriculaInt, nombre, correo, contrasena, telefonoDouble, rol, direccion
                    );
                    controlpersis.crearVeterinario((Veterinario) nuevoUsuario);
                    break;
                case "ADMINISTRADOR":
                    nuevoUsuario = new Administrador(
                        nombre, correo, contrasena, telefonoDouble, rol, direccion
                    );
                    controlpersis.crearAdministrador((Administrador) nuevoUsuario);
                    break;
                case "VOLUNTARIO":
                    nuevoUsuario = new Voluntario(
                        nombre, correo, contrasena, telefonoDouble, rol, direccion
                    );
                    controlpersis.crearVoluntario((Voluntario) nuevoUsuario);
                    break;
                default:
                    throw new RegistroException("Rol de usuario inválido.");
            }
        } catch (NumberFormatException e) {
            throw new RegistroException("El campo Teléfono debe contener solo números válidos.", e);
        } catch (RegistroException e) {
            throw e;
        } catch (Exception e) {
            throw new RegistroException("Fallo de persistencia: " + e.getMessage(), e);
        }
    }
    
    // --- LÓGICA DE POSTULACIÓN Y ASIGNACIÓN ---
    
    public void crearPostulacion(long idGato, int idFamilia) throws OperacionException {
        try {
            Gato gato = controlpersis.buscarGato(idGato);
            FamiliaAdoptante familia = controlpersis.buscarFamilia(idFamilia);

            if (gato == null) throw new OperacionException("Gato no encontrado.");
            if (familia == null) throw new OperacionException("Familia no encontrada.");

            boolean yaExiste = controlpersis.existePostulacion(idGato, idFamilia);
            if (yaExiste) {
                throw new OperacionException("Ya te has postulado para este gato.");
            }
            if (gato.getDisponible() != modelo.Gato.RespuestaBinaria.SI) {
                 throw new OperacionException("Lo sentimos, este gato no está disponible para adopción.");
            }

            Postulacion nuevaPostulacion = new Postulacion();
            nuevaPostulacion.setGatoRelacionado(gato);
            nuevaPostulacion.setFamiliaPostulante(familia);
            nuevaPostulacion.setEstado(Postulacion.Estado.PENDIENTE); 
            controlpersis.crearPostulacion(nuevaPostulacion);
        } catch (OperacionException e) {
            throw e; 
        } catch (Exception e) {
            throw new OperacionException("Error de persistencia al crear la postulación: " + e.getMessage(), e);
        }
    }
   
    public List<Gato> traerGatosDisponibles() throws OperacionException {
        try {
            List<Gato> gatos = controlpersis.buscarGatosDisponibles(); 
            if (gatos == null || gatos.isEmpty()) {
                throw new OperacionException("No hay gatos disponibles para mostrar en este momento.");
            }
            return gatos;
        } catch (Exception e) {
            throw new OperacionException("Error al traer los gatos: " + e.getMessage(), e);
        }
    } 

    public void asignarGatoAFamilia(long idGato, int idFamilia) throws OperacionException {
        try {
            // 🟢 CORRECCIÓN: Eliminado el (int) cast
            Gato gato = controlpersis.buscarGato(idGato); 
            FamiliaAdoptante familia = controlpersis.buscarFamilia(idFamilia);

            if (gato == null) {
                throw new OperacionException("Error: No se encontró el gato seleccionado.");
            }
            if (familia == null) {
                throw new OperacionException("Error: No se encontró la familia destino.");
            }
            if (gato.getDisponible() != Gato.RespuestaBinaria.SI) {
                throw new OperacionException("El gato no está disponible para asignación (Estado=NO).");
            }
            
            gato.setFamiliaAdoptante(familia);
            gato.setDisponible(Gato.RespuestaBinaria.NO); 
            controlpersis.modificarGato(gato);
        } catch (OperacionException e) {
            throw e;
        } catch (Exception e) {
            throw new OperacionException("Error crítico al asignar el gato: " + e.getMessage(), e);
        }
    }

    public List<FamiliaAdoptante> traerTodasLasFamilias() throws OperacionException {
        try {
            List<FamiliaAdoptante> familias = controlpersis.traerTodasLasFamilias();
            if (familias == null || familias.isEmpty()) {
                throw new OperacionException("No hay familias adoptantes registradas.");
            }
            return familias;
        } catch (Exception e) {
            throw new OperacionException("Error al traer las familias: " + e.getMessage(), e);
        }
    }
    
    // --- LÓGICA DE VISITAS ---
    
    public List<Visita> traerTodasLasVisitas() {
        return controlpersis.traerTodasVisitas();
    }

    public List<Visita> filtrarVisitas(String nombreFamilia, String nombreVoluntario) {
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
            Visita visita = this.buscarVisita(idVisita); 
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

    public void registrarVisitaDeSeguimiento(int idFamilia, int idVoluntario, 
                                            LocalDate fecha, String descripcion) 
                                            throws OperacionException {
        // ... (Tu lógica para crear y guardar una nueva visita)
    }
    
    // --- LÓGICA DE REGISTRO DE GATO ---
    
    public void registrarGato(String nombre, String raza, String sexo, String color, 
                              String esterilizado, String caracteristicas, 
                              String estadoSalud, String disponible, String nombreZona,
                              String rutaFoto)
                              throws OperacionException {
        if (nombre.isEmpty() || raza.isEmpty() || sexo.equals("-") || color.isEmpty() || 
            esterilizado.equals("-") || estadoSalud.equals("-") || disponible.equals("-") || nombreZona.equals("-")) {
            throw new OperacionException("Debe completar todos los campos obligatorios.");
        }
        try {
            Gato.RespuestaBinaria esterilizadoEnum = Gato.RespuestaBinaria.valueOf(esterilizado.toUpperCase());
            Gato.EstadoSalud estadoFisicoEnum = Gato.EstadoSalud.valueOf(estadoSalud.replace(" ", "_").toUpperCase()); 
            Gato.RespuestaBinaria disponibleEnum = Gato.RespuestaBinaria.valueOf(disponible.toUpperCase());

            Zona zona = controlpersis.buscarZonaPorNombre(nombreZona);
            if (zona == null) {
                throw new OperacionException("La Zona '" + nombreZona + "' no existe. Debe registrar la zona primero.");
            }

            Gato nuevoGato = new Gato();
            nuevoGato.setNombre(nombre);
            nuevoGato.setRaza(raza);
            nuevoGato.setSexo(sexo);
            nuevoGato.setColor(color);
            nuevoGato.setEsterilizado(esterilizadoEnum);
            nuevoGato.setCaracteristicas(caracteristicas);
            nuevoGato.setestadoFisico(estadoFisicoEnum);
            nuevoGato.setDisponible(disponibleEnum);
            
            if (rutaFoto != null && !rutaFoto.isEmpty()) {
                 nuevoGato.setRutaFoto(rutaFoto);
            }
        
            nuevoGato.setZona(zona);
            nuevoGato.setHistoriaClinica(new HistoriaClinica("Historia inicial al registro")); 
            
            controlpersis.crearGato(nuevoGato);
        } catch (IllegalArgumentException e) {
            throw new OperacionException("Error de datos: Uno de los campos de selección (Enum) es inválido. Revise Sexo/Estado/Disponibilidad.", e);
        } catch (OperacionException e) {
            throw e; 
        } catch (Exception e) {
            throw new OperacionException("Error de persistencia al registrar el gato: " + e.getMessage(), e);
        }
    }
    
    // --- LÓGICA DE TAREAS ---

    public void registrarTarea(long idVoluntario, String nombreGato, 
                               String fechaStr, String tipoTareaStr, String descripcion) throws OperacionException {
        if (nombreGato.equals("-") || tipoTareaStr.equals("-") || descripcion.isEmpty() || fechaStr.isEmpty()) {
            throw new OperacionException("Debe completar todos los campos obligatorios.");
        }
        try {
            Voluntario voluntario = controlpersis.buscarVoluntario(idVoluntario);
            if (voluntario == null) {
                throw new OperacionException("Error interno: El voluntario logueado no pudo ser encontrado.");
            }
            Gato gato = controlpersis.buscarGatoPorNombre(nombreGato);
            if (gato == null) {
                throw new OperacionException("El gato seleccionado no existe.");
            }
            
            Tarea.TipoTarea tipoTarea = Tarea.TipoTarea.valueOf(tipoTareaStr.replace(" ", "_").toUpperCase());
            
            Tarea nuevaTarea = new Tarea();
            nuevaTarea.setFecha(new Date()); 
            nuevaTarea.setTipoTarea(tipoTarea);
            nuevaTarea.setDescripcion(descripcion);
            nuevaTarea.setGatoAsignado(gato);
            nuevaTarea.setVoluntarioQueRealiza(voluntario);

            controlpersis.crearTarea(nuevaTarea);
        } catch (IllegalArgumentException e) {
            throw new OperacionException("Error de datos: El tipo de tarea seleccionado no es válido.", e);
        } catch (Exception e) {
            throw new OperacionException("Error de persistencia al registrar la tarea: " + e.getMessage(), e);
        }
    }

    public void crearTarea(Tarea tarea) throws Exception {
        controlpersis.crearTarea(tarea);
    }

    public Voluntario buscarVoluntario(long idVoluntario) {
        return controlpersis.buscarVoluntario(idVoluntario);
    }

    public Gato buscarGatoPorNombre(String nombreGato) {
        return controlpersis.buscarGatoPorNombre(nombreGato);
    }

    // --- LÓGICA DE ZONAS ---

    public void registrarZona(String nombreZona, String ubicacionGPS) throws OperacionException {
        if (nombreZona.isEmpty() || ubicacionGPS.isEmpty()) {
            throw new OperacionException("El nombre de la zona y la ubicación GPS son obligatorios.");
        }
        try {
            Zona nuevaZona = new Zona();
            nuevaZona.setNombreZona(nombreZona);
            nuevaZona.setUbicacionGPS(ubicacionGPS);
            controlpersis.crearZona(nuevaZona);
        } catch (Exception e) {
            throw new OperacionException("Error al registrar la zona: Verifique duplicados.", e);
        }
    }

    public List<Zona> traerTodasLasZonas() throws OperacionException {
        List<Zona> zonas = controlpersis.traerTodasLasZonas();
        if (zonas == null || zonas.isEmpty()) {
            throw new OperacionException("No hay zonas registradas en el sistema.");
        }
        return zonas;
    }

    public void modificarZona(long idZona, String nombreZona, String ubicacionGPS) throws OperacionException {
        if (nombreZona.isEmpty() || ubicacionGPS.isEmpty()) {
            throw new OperacionException("El nombre de la zona y la ubicación GPS son obligatorios.");
        }
        try {
            Zona zona = controlpersis.buscarZona(idZona);
            if (zona == null) {
                throw new OperacionException("La zona que intenta modificar no existe.");
            }
            zona.setNombreZona(nombreZona);
            zona.setUbicacionGPS(ubicacionGPS);
            controlpersis.modificarZona(zona);
        } catch (Exception e) {
            throw new OperacionException("Error al modificar la zona.", e);
        }
    }

    public void eliminarZona(long idZona) throws OperacionException {
        try {
            controlpersis.eliminarZona(idZona);
        } catch (persistencia.exceptions.NonexistentEntityException e) {
            throw new OperacionException("Error: La zona seleccionada no existe.", e);
        } catch (Exception e) {
            throw new OperacionException("Error crítico al eliminar la zona. Verifique que no tenga gatos asignados.", e);
        }
    }

    public Zona buscarZona(long idZona) {
        return controlpersis.buscarZona(idZona);
    }
}