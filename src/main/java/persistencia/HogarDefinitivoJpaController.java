
package persistencia;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import modelo.HogarDefinitivo;
import persistencia.exceptions.NonexistentEntityException;


 

public class HogarDefinitivoJpaController implements Serializable {

    public HogarDefinitivoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;
    
    public HogarDefinitivoJpaController() {
        emf = Persistence.createEntityManagerFactory("tpiPU");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(HogarDefinitivo hogarDefinitivo) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(hogarDefinitivo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(HogarDefinitivo hogarDefinitivo) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            hogarDefinitivo = em.merge(hogarDefinitivo);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = hogarDefinitivo.getIdHogar();
                if (findHogarDefinitivo(id) == null) {
                    throw new NonexistentEntityException("The hogarDefinitivo with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(int id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            HogarDefinitivo hogarDefinitivo;
            try {
                hogarDefinitivo = em.getReference(HogarDefinitivo.class, id);
                hogarDefinitivo.getIdHogar();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The hogarDefinitivo with id " + id + " no longer exists.", enfe);
            }
            em.remove(hogarDefinitivo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<HogarDefinitivo> findHogarDefinitivoEntities() {
        return findHogarDefinitivoEntities(true, -1, -1);
    }

    public List<HogarDefinitivo> findHogarDefinitivoEntities(int maxResults, int firstResult) {
        return findHogarDefinitivoEntities(false, maxResults, firstResult);
    }

    private List<HogarDefinitivo> findHogarDefinitivoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(HogarDefinitivo.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public HogarDefinitivo findHogarDefinitivo(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(HogarDefinitivo.class, id);
        } finally {
            em.close();
        }
    }

    public int getHogarDefinitivoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<HogarDefinitivo> rt = cq.from(HogarDefinitivo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
