package br.com.adv.dao;

import br.com.adv.modelo.Agendamento;
import br.com.adv.util.HibernateUtil;
import jakarta.persistence.EntityManager;
import java.util.List;


public class AgendamentoDAO {
	
	public void salvar(Agendamento agendamento) {
	    EntityManager em = HibernateUtil.getEntityManager();
	    try {
	        em.getTransaction().begin();
	        em.persist(agendamento);
	        em.getTransaction().commit();
	    } catch (Exception e) {
	        if (em.getTransaction().isActive()) {
	            em.getTransaction().rollback();
	        }
	        e.printStackTrace();
	        throw e;
	    } finally {
	        em.close();
	    }
	}

    // atualiza o agendamento depois do editar
    public void atualizar(Agendamento agendamento) {
        EntityManager em = HibernateUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(agendamento);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
	        throw e;
        } finally {
            em.close();
        }
    }

    // busca o agendamento por seu id
    public Agendamento buscarPorId(Long id) {
        EntityManager em = HibernateUtil.getEntityManager();
        try {
            return em.find(Agendamento.class, id);
        } finally {
            em.close();
        }
    }

    // faz a lista de todos os agendamentos do banco
    public List<Agendamento> listarTodos() {
        EntityManager em = HibernateUtil.getEntityManager();
        try {
            return em.createQuery("FROM Agendamento", Agendamento.class).getResultList();
        } finally {
            em.close();
        }
    }

    // deleta o agendamento
    public void deletar(Long id) {
        EntityManager em = HibernateUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            Agendamento agendamento = em.find(Agendamento.class, id);
            if (agendamento != null) {
                em.remove(agendamento);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
	        throw e;
        } finally {
            em.close();
        }
    }
}
