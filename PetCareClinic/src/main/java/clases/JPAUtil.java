package clases;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {

    private static final EntityManagerFactory emFactory;

    static {
        emFactory = Persistence.createEntityManagerFactory("PetCare");
    }

    public static EntityManager getEntityManager() {
    	System.out.println("getentitymanager");
        return emFactory.createEntityManager();
    }

    public static void close() {
        emFactory.close();
    }
}