package br.com.adv.dao;

import br.com.adv.modelo.Usuario;
import br.com.adv.util.HibernateUtil;
import jakarta.persistence.EntityManager;

public class UsuarioDao {
	
	private EntityManager em = HibernateUtil.getEntityManager();
	
	// salva um usuário no banco de dados
	public void salvar(Usuario usuario) {
        try {
            em.getTransaction().begin();
            em.persist(usuario);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        }
	}
	
	// faz a busca do usuario pelo login
	public Usuario buscarPorLogin(String email, String senha) {
        try {
            return em.createQuery("SELECT u FROM Usuario u WHERE u.email = :email AND u.senha = :senha", Usuario.class)
                     .setParameter("email", email)
                     .setParameter("senha", senha)
                     .getSingleResult();
        } catch (Exception e) {
            // Retorna null caso não encontre ou ocorra erro
            return null; 
        }
    }
}
