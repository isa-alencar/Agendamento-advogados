package br.com.adv.bean;

import java.io.Serializable;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import jakarta.annotation.PostConstruct;
import br.com.adv.dao.UsuarioDao;
import br.com.adv.modelo.Usuario;

@Named
@SessionScoped
public class LoginBean implements Serializable {

	private static final long serialVersionUID = 1L;
    private String email;
    private String senha;
    private Usuario usuarioLogado;
    private UsuarioDao usuarioDAO = new UsuarioDao();
    private Usuario novoUsuario;
    
    @PostConstruct
    public void init() {
        this.novoUsuario = new Usuario();
    }
    
    public void salvarPrimeiroAcesso() {
        if (this.email == null || this.email.trim().isEmpty() || this.senha == null || this.senha.trim().isEmpty()) {
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_WARN, "Preencha o e-mail e a senha para realizar o primeiro acesso.", null));
            return;
        }

        try {
            Usuario novo = new Usuario();
            novo.setEmail(this.email);
            novo.setSenha(this.senha);
            novo.setNome("Usuário Novo");
            novo.setPerfil("USER");
            
            usuarioDAO.salvar(novo);

            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Primeiro acesso cadastrado com sucesso! Clique em Entrar.", null));
                
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao realizar primeiro acesso: " + e.getMessage(), null));
        }
    }

    public String autenticar() {
        this.usuarioLogado = usuarioDAO.buscarPorLogin(email, senha);

        if (this.usuarioLogado != null) {
            return "agendamento?faces-redirect=true"; // Direciona para a lista
        } else {
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login ou Senha incorretos!", null));
            return null; // Fica na mesma página
        }
    }

    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "login?faces-redirect=true";
    }

    // Getters e Setters
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }
    public Usuario getUsuarioLogado() { return usuarioLogado; }
    public boolean isLogado() { return usuarioLogado != null; }
    public Usuario getNovoUsuario() { return novoUsuario; }
    public void setNovoUsuario(Usuario novoUsuario) { this.novoUsuario = novoUsuario; }
}
