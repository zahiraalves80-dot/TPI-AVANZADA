
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
import modelo.HogarTransito;
import persistencia.exceptions.NonexistentEntityException;

/**
 *
 * @author Zahira Alves
 */
public class HogarTransitoJpaController implements Serializable {

    public HogarTransitoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;
    
    public HogarTransitoJpaController() {
        emf = Persistence.createEntityManagerFactory("tpiPU");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(HogarTransito hogarTransito) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(hogarTransito);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(HogarTransito hogarTransito) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            hogarTransito = em.merge(hogarTransito);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = hogarTransito.getIdHogar();
                if (findHogarTransito(id) == null) {
                    throw new NonexistentEntityException("The hogarTransito with id " + id + " no longer exists.");
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
            HogarTransito hogarTransito;
            try {
                hogarTransito = em.getReference(HogarTransito.class, id);
                hogarTransito.getIdHogar();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The hogarTransito with id " + id + " no longer exists.", enfe);
            }
            em.remove(hogarTransito);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<HogarTransito> findHogarTransitoEntities() {
        return findHogarTransitoEntities(true, -1, -1);
    }

    public List<HogarTransito> findHogarTransitoEntities(int maxResults, int firstResult) {
        return findHogarTransitoEntities(false, maxResults, firstResult);
    }

    private List<HogarTransito> findHogarTransitoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(HogarTransito.class));
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

    public HogarTransito findHogarTransito(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(HogarTransito.class, id);
        } finally {
            em.close();
        }
    }

    public int getHogarTransitoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<HogarTransito> rt = cq.from(HogarTransito.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
