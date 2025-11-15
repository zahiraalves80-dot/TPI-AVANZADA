package controladora;

import java.time.LocalDate;
import java.util.Date;
import persistencia.ControladoraPersistencia;
import modelo.Gato;
import modelo.FamiliaAdoptante;
import modelo.Postulacion; // (Asegúrate de tener esta clase)
import modelo.OperacionException;
import java.util.List;
import modelo.Administrador;
import modelo.HistoriaClinica;
import modelo.LoginException;
import modelo.RegistroException;
import modelo.Tarea;
import modelo.Usuario;
import modelo.Veterinario;
import modelo.Visita;
import modelo.Voluntario;
import modelo.Zona;


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

    

    // (Este método también)
    public void registrarVisitaDeSeguimiento(int idFamilia, int idVoluntario, 
                                            LocalDate fecha, String descripcion) 
                                            throws OperacionException {
        // ... (la lógica de la respuesta anterior)
    }
    
    public void registrarGato(String nombre, String raza, String sexo, String color, 
                              String esterilizado, String caracteristicas, 
                              String estadoSalud, String disponible, String nombreZona) 
                              throws OperacionException 
    {
        // 1. Validación de campos obligatorios
        if (nombre.isEmpty() || raza.isEmpty() || sexo.equals("-") || color.isEmpty() || 
            esterilizado.equals("-") || estadoSalud.equals("-") || disponible.equals("-") || nombreZona.equals("-")) 
        {
            throw new OperacionException("Debe completar todos los campos obligatorios.");
        }

        try {
            // 2. Mapeo de Enums (Los valores deben coincidir con los Enums en Gato.java)
            Gato.RespuestaBinaria esterilizadoEnum = Gato.RespuestaBinaria.valueOf(esterilizado.toUpperCase());
            // Reemplazar espacios para que coincida con el Enum (ej. "EN TRATAMIENTO" a EN_TRATAMIENTO)
            Gato.EstadoSalud estadoFisicoEnum = Gato.EstadoSalud.valueOf(estadoSalud.replace(" ", "_").toUpperCase()); 
            Gato.RespuestaBinaria disponibleEnum = Gato.RespuestaBinaria.valueOf(disponible.toUpperCase());

            // 3. Buscar Zona y validar
            Zona zona = controlpersis.buscarZonaPorNombre(nombreZona);
            if (zona == null) {
                throw new OperacionException("La Zona '" + nombreZona + "' no existe. Debe registrar la zona primero.");
            }

            // 4. Crear el objeto Gato y su Historia Clínica
            Gato nuevoGato = new Gato();
            nuevoGato.setNombre(nombre);
            nuevoGato.setRaza(raza);
            nuevoGato.setSexo(sexo);
            nuevoGato.setColor(color);
            nuevoGato.setEsterilizado(esterilizadoEnum);
            nuevoGato.setCaracteristicas(caracteristicas);
            nuevoGato.setestadoFisico(estadoFisicoEnum);
            nuevoGato.setDisponible(disponibleEnum);
            
            // Asignar relaciones
            nuevoGato.setZona(zona);
            nuevoGato.setHistoriaClinica(new HistoriaClinica("Historia inicial al registro")); 
            
            // 5. Delegar la persistencia
            controlpersis.crearGato(nuevoGato);
            
        } catch (IllegalArgumentException e) {
            throw new OperacionException("Error de datos: Uno de los campos de selección (Enum) es inválido. Revise Sexo/Estado/Disponibilidad.", e);
        } catch (OperacionException e) {
            throw e; // Relanza las excepciones de negocio (ej. Zona no existe)
        } catch (Exception e) {
            throw new OperacionException("Error de persistencia al registrar el gato: " + e.getMessage(), e);
        }
    }

    // Método auxiliar para cargar el ComboBox de Zonas (en caso de que lo necesites)
    public List<Zona> traerTodasLasZonas() {
        return controlpersis.traerTodasLasZonas();
    }
    
    public void asignarGatoAFamilia(long idGato, int idFamilia) throws OperacionException {
    try {
        Gato gato = controlpersis.buscarGato((int)idGato);
        FamiliaAdoptante familia = controlpersis.buscarFamilia(idFamilia);

        // 1. Validaciones de Negocio
        if (gato == null) {
            throw new OperacionException("Error: No se encontró el gato seleccionado.");
        }
        if (familia == null) {
            throw new OperacionException("Error: No se encontró la familia destino.");
        }
        if (gato.getDisponible() != Gato.RespuestaBinaria.SI) {
            throw new OperacionException("El gato no está disponible para asignación (Estado=NO).");
        }
        
        // 2. Lógica de Asignación
        gato.setFamiliaAdoptante(familia);
        gato.setDisponible(Gato.RespuestaBinaria.NO); // Ya no está disponible

        // 3. Persistencia
        controlpersis.modificarGato(gato);

    } catch (OperacionException e) {
        throw e;
    } catch (Exception e) {
        throw new OperacionException("Error crítico al asignar el gato: " + e.getMessage(), e);
    }
}

// --- Método para llenar el ComboBox de Familias ---
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
public Gato buscarGato(String nombreGato) {
    // Si tu GatoJpaController tiene un método para buscar por nombre, úsalo aquí.
    // Si no, debes implementarlo en ControladoraPersistencia primero.
    // POR AHORA, LO DEJAREMOS COMO UN TO-DO Y ASUMIREMOS EL ID PARA EL EJEMPLO.
    return controlpersis.buscarGatoPorNombre(nombreGato); 
}


// --- Método Principal: Registrar Tarea ---
public void registrarTarea(long idVoluntario, String nombreGato, 
                           String fechaStr, String tipoTareaStr, String descripcion) throws OperacionException 
{
    // 1. Validación de campos obligatorios
    if (nombreGato.equals("-") || tipoTareaStr.equals("-") || descripcion.isEmpty() || fechaStr.isEmpty()) 
    {
        throw new OperacionException("Debe completar todos los campos obligatorios.");
    }

    try {
        // 2. Conversión de Datos y Búsqueda de Entidades
        
        // a) Voluntario (Ya lo tenemos, pero aseguramos la persistencia)
        Voluntario voluntario = controlpersis.buscarVoluntario(idVoluntario);
        if (voluntario == null) {
            throw new OperacionException("Error interno: El voluntario logueado no pudo ser encontrado.");
        }
        
        // b) Gato (Asumiendo que encuentras el gato por nombre/ID)
        Gato gato = controlpersis.buscarGatoPorNombre(nombreGato);
        if (gato == null) {
            throw new OperacionException("El gato seleccionado no existe.");
        }
        
        // c) Mapeo de Enums y Fecha
        Tarea.TipoTarea tipoTarea = Tarea.TipoTarea.valueOf(tipoTareaStr.replace(" ", "_").toUpperCase());
       
        
        // 3. Crear la entidad Tarea
        Tarea nuevaTarea = new Tarea();
        nuevaTarea.setFecha(new Date()); 
        nuevaTarea.setTipoTarea(tipoTarea);
        nuevaTarea.setDescripcion(descripcion);
        nuevaTarea.setGatoAsignado(gato);
        nuevaTarea.setVoluntarioQueRealiza(voluntario);

        // 4. Persistencia (Necesitamos crear el método en ControladoraPersistencia)
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

// --- Nuevo: Buscar Voluntario ---
public Voluntario buscarVoluntario(long idVoluntario) {
    
    return controlpersis.buscarVoluntario(idVoluntario);
}

// --- Nuevo: Buscar Gato por Nombre (o ID si cambias la Vista) ---
public Gato buscarGatoPorNombre(String nombreGato) {
    
    return controlpersis.buscarGatoPorNombre(nombreGato);
}
}

