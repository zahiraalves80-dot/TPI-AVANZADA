
package persistencia;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import modelo.Administrador;
import modelo.FamiliaAdoptante;
import modelo.Usuario;
import modelo.Veterinario;
import modelo.Voluntario;
import modelo.Gato;
import modelo.Postulacion; 
import java.util.List;
import modelo.Visita;



public class ControladoraPersistencia {
    
    AdministradorJpaController adminJpa = new AdministradorJpaController();
    EstudioJpaController estudiojpa= new EstudioJpaController();
    FamiliaAdoptanteJpaController familiaAdoptanteJpa = new FamiliaAdoptanteJpaController();
    GatoJpaController gatoJpa = new GatoJpaController();
    HistoriaClinicaJpaController historiaClinicaJpa = new HistoriaClinicaJpaController();
    PostulacionJpaController postulacionJpa = new PostulacionJpaController();
    ReporteJpaController reporteJpa = new ReporteJpaController();
    SesionJpaController sesionJpa = new SesionJpaController();
    TareaJpaController tareaJpa = new TareaJpaController();
    TratamientoJpaController tratamientoJpa = new TratamientoJpaController();
    UsuarioJpaController usuarioJpa = new UsuarioJpaController();
    VeterinarioJpaController veterinarioJpa = new VeterinarioJpaController();
    VisitaJpaController visitaJpa = new VisitaJpaController();
    VoluntarioJpaController voluntarioJpa = new VoluntarioJpaController();
    ZonaJpaController zonaJpa = new ZonaJpaController();
    AptitudJpaController aptitudjpa = new AptitudJpaController();
    
   public void crearFamiliaAdoptante(FamiliaAdoptante familia) throws Exception {
        familiaAdoptanteJpa.create(familia); //
    }
   
    public void crearAdministrador(Administrador admin) throws Exception {
        adminJpa.create(admin); 
    }
    
    public void crearVeterinario(Veterinario vet) throws Exception {
        veterinarioJpa.create(vet);
    }
    
    public void crearVoluntario(Voluntario vol) throws Exception {
        voluntarioJpa.create(vol);
    }
    
    public Usuario buscarUsuarioPorCorreo(String correo) {
        
        // Obtenemos el EntityManager del JpaController de Usuario
        EntityManager em = usuarioJpa.getEntityManager();
        
        try {
            // 1. Crear la consulta JPQL (Java Persistence Query Language)
            // Busca en la entidad Usuario
            TypedQuery<Usuario> query = em.createQuery(
                "SELECT u FROM Usuario u WHERE u.correo = :correo", 
                Usuario.class
            );
            
            // 2. Asignar el parámetro :correo
            query.setParameter("correo", correo);
            
            // 3. Ejecutar la consulta y obtener un único resultado
            return query.getSingleResult();
            
        } catch (NoResultException e) {
            // Esto es normal y esperado: significa que no se encontró
            // ningún usuario con ese correo.
            return null;
        } catch (Exception e) {
            // Otro error (ej. DB caída, error de sintaxis JPQL)
            System.err.println("Error al buscar usuario por correo: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
    public List<Gato> buscarGatosDisponibles() {
        EntityManager em = gatoJpa.getEntityManager();
        try {
            // JPQL: "Selecciona el objeto Gato g DONDE el atributo 'disponible' sea true"
            TypedQuery<Gato> query = em.createQuery(
                "SELECT g FROM Gato g WHERE g.disponible = true", 
                Gato.class
            );
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public Gato buscarGato(int idGato) {
        try {
            return gatoJpa.findGato(idGato);
        } catch (Exception e) {
            return null;
        }
    }
    
    public FamiliaAdoptante buscarFamilia(int idFamilia) {
        try {
            return familiaAdoptanteJpa.findFamiliaAdoptante(idFamilia);
        } catch (Exception e) {
            return null;
        }
        
        
    }
    
    public boolean existePostulacion(int idGato, int idFamilia) {
        EntityManager em = postulacionJpa.getEntityManager();
        try {
            // JPQL: "Cuenta las postulaciones p DONDE el ID del gato y el ID de la familia coincidan"
            TypedQuery<Long> query = em.createQuery(
                "SELECT COUNT(p) FROM Postulacion p WHERE p.gato.idGato = :idGato AND p.familiaAdoptante.idUsuario = :idFamilia", 
                Long.class
            );
            query.setParameter("idGato", idGato);
            query.setParameter("idFamilia", idFamilia);
            
            // Si el conteo es mayor a 0, significa que ya existe.
            return query.getSingleResult() > 0;
            
        } catch (NoResultException e) {
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public void crearPostulacion(Postulacion postulacion) throws Exception {
        // Asumiendo que tienes 'postulacionJpa' declarado arriba con los otros JpaControllers
        postulacionJpa.create(postulacion);
    }
    
    public Visita buscarVisita(long id) {
        return visitaJpa.findVisita(id);
    }
    
    public void editarVisita(Visita visita) throws Exception {
        visitaJpa.edit(visita);
    }
    
    public void eliminarVisita(long id) throws persistencia.exceptions.NonexistentEntityException {
        visitaJpa.destroy(id);
    }
    
    public List<Visita> traerTodasVisitas() {
        return visitaJpa.findVisitaEntities();
    }
    
    /**
     * Busca visitas filtrando por el nombre de la familia y el nombre del voluntario.
     */
    public List<Visita> buscarVisitasFiltradas(String nombreFamilia, String nombreVoluntario) {
        EntityManager em = visitaJpa.getEntityManager();
        try {
            // Consulta base JPQL
            String jpql = "SELECT v FROM Visita v WHERE 1=1";
            
            // Añadir filtros dinámicamente
            if (nombreFamilia != null && !nombreFamilia.isEmpty()) {
                // 'familia' es el nombre del atributo en la entidad Visita
                // 'nombre' es el atributo en la entidad FamiliaAdoptante (que hereda de Usuario)
                jpql += " AND v.familia.nombre LIKE :familia";
            }
            if (nombreVoluntario != null && !nombreVoluntario.isEmpty()) {
                // 'voluntarioEncargado' es el atributo en Visita
                // 'nombre' es el atributo en Voluntario (que hereda de Usuario)
                jpql += " AND v.voluntarioEncargado.nombre LIKE :voluntario";
            }
            
            TypedQuery<Visita> query = em.createQuery(jpql, Visita.class);
            
            // Asignar los parámetros
            if (nombreFamilia != null && !nombreFamilia.isEmpty()) {
                query.setParameter("familia", "%" + nombreFamilia + "%");
            }
            if (nombreVoluntario != null && !nombreVoluntario.isEmpty()) {
                query.setParameter("voluntario", "%" + nombreVoluntario + "%");
            }
            
            return query.getResultList();
            
        } finally {
            em.close();
        }
    }
    public List<FamiliaAdoptante> traerTodasLasFamilias() {
        // Llama al método del JpaController que SÍ está visible aquí
        return familiaAdoptanteJpa.findFamiliaAdoptanteEntities();
    }
}