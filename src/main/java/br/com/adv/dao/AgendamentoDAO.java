package br.com.adv.dao;

import br.com.adv.modelo.Agendamento;
import br.com.adv.util.HibernateUtil;
import jakarta.persistence.EntityManager;
import java.util.List;


public class AgendamentoDAO {
	// 1. SALVAR (CREATE)
    public void salvar(Agendamento agendamento) {
        EntityManager em = HibernateUtil.getEntityManager();
        try {
            em.getTransaction().begin(); // Abre a transação com o banco
            em.persist(agendamento);     // O JPA gera o INSERT INTO automaticamente!
            em.getTransaction().commit(); // Confirma e grava no banco de dados
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback(); // Cancela tudo se der erro para não quebrar o banco
            }
            e.printStackTrace();
        } finally {
            em.close(); // Fecha a conexão (Obrigatório para não esgotar a memória)
        }
    }

    // 2. ATUALIZAR (UPDATE)
    public void atualizar(Agendamento agendamento) {
        EntityManager em = HibernateUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(agendamento);       // O JPA gera o UPDATE de forma automática!
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    // 3. BUSCAR POR ID (READ - Único)
    public Agendamento buscarPorId(Long id) {
        EntityManager em = HibernateUtil.getEntityManager();
        try {
            // O JPA gera o SELECT * FROM Agendamento WHERE id = ?
            return em.find(Agendamento.class, id);
        } finally {
            em.close();
        }
    }

    // 4. LISTAR TODOS (READ - Lista)
    public List<Agendamento> listarTodos() {
        EntityManager em = HibernateUtil.getEntityManager();
        try {
            // Aqui usamos JPQL (a linguagem de consulta do JPA que foca na Classe, não na tabela)
            return em.createQuery("FROM Agendamento", Agendamento.class).getResultList();
        } finally {
            em.close();
        }
    }

    // 5. DELETAR (DELETE)
    public void deletar(Long id) {
        EntityManager em = HibernateUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            // No JPA, para deletar um objeto, ele primeiro precisa estar "rastreado" pela conexão
            Agendamento agendamento = em.find(Agendamento.class, id);
            if (agendamento != null) {
                em.remove(agendamento);   // O JPA gera o DELETE FROM Agendamento WHERE id = ?
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}
