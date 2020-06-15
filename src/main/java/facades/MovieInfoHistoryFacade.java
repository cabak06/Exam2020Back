
package facades;

import entities.MovieInfoHistory;
import entities.User;
import errorhandling.AuthenticationException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author cahit
 */
public class MovieInfoHistoryFacade {
   
    private static EntityManagerFactory emf;
    private static MovieInfoHistoryFacade instance;

    public MovieInfoHistoryFacade() {}
    
     //* @param _emf
    // * @return the instance of this facade.
   
    public static MovieInfoHistoryFacade getMovieInfoHistoryFacade (EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new MovieInfoHistoryFacade();
        }
        return instance;
    }
    
    
     public long getCount(String title) throws AuthenticationException {
        EntityManager em = emf.createEntityManager();
        
        try {
            long count = (long) em.createQuery("SELECT COUNT(m) FROM MovirInfoHistory m").getSingleResult();
            return count;
        } finally {
            em.close();
        }
        
    }
     
      public void addMovieInfoHistory(MovieInfoHistory movieInfoHistory) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(movieInfoHistory);
            em.getTransaction().commit();
        
        } finally {
            em.close();
        }
    }
     
     
    
    
    
}
