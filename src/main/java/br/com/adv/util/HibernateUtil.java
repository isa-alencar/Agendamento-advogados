package br.com.adv.util;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class HibernateUtil {
	private static EntityManagerFactory factory;
	
	static {
        try {
            factory = Persistence.createEntityManagerFactory("advAgendamentosPU");
        } catch (Throwable ex) {
            System.err.println("Falha na inicialização do EntityManagerFactory: " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
	
	public static EntityManager getEntityManager() {
        return factory.createEntityManager();
    }
	
	public static void close() {
        if (factory != null && factory.isOpen()) {
            factory.close();
        }
    }
}