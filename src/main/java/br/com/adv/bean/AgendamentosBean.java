package br.com.adv.bean;

import java.io.Serializable;
import java.util.List;
import jakarta.faces.view.ViewScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import br.com.adv.dao.AgendamentoDAO;
import br.com.adv.modelo.Agendamento;
import org.primefaces.PrimeFaces;

@Named("agendamentoBean")
@ViewScoped
public class AgendamentosBean implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Inject
    private LoginBean loginBean; // Injeta o bean que guarda a sessão

    private AgendamentoDAO agendamentoDao = new AgendamentoDAO();
    private List<Agendamento> agendamentos;

    // No método que carrega a lista (geralmente dentro de um @PostConstruct ou init())
    public void carregarAgendamentos() {
        this.agendamentos = agendamentoDao.listar(loginBean.getUsuarioLogado());
    }
	
	// espelho dos campos da index.xhtml
    private Agendamento agendamento = new Agendamento();
    private List<Agendamento> listagem; 
    private AgendamentoDAO dao = new AgendamentoDAO();
    
    public void salvar() {
        try {
            // 1. Proteção: Verifica se existe um usuário logado antes de tentar salvar
            if (loginBean.getUsuarioLogado() == null) {
                adicionarMensagem("Erro", "Sessão expirada. Faça login novamente.");
                return;
            }

            // 2. Associação: Associa o usuário logado ao agendamento
            // Importante: Seu Agendamento.java deve ter o método setUsuario(Usuario usuario)
            this.agendamento.setUsuario(loginBean.getUsuarioLogado());

            // 3. Salva ou Atualiza
            if (agendamento.getId() == null) {
                agendamentoDao.salvar(agendamento);
                adicionarMensagem("Concluído", "Agendamento salvo");
            } else {
                agendamentoDao.atualizar(agendamento);
                adicionarMensagem("Concluído", "Agendamento editado");
            }
            
            // 4. Limpeza: Reseta o objeto para o próximo cadastro
            this.agendamento = new Agendamento(); 
            
            // 5. Atualização da Lista: Força o bean a buscar os dados novamente na próxima exibição
            this.listagem = null; 
           
            // 6. Feedback visual
            PrimeFaces.current().executeScript(
                "Swal.fire('Agendamento concluído', 'Seu pedido de agendamento foi salvo.', 'success');"
            );
            
            // Dica: Se precisar fechar um Dialog, adicione: PrimeFaces.current().executeScript("PF('dialogNome').hide();");

        } catch (Exception e) {
            adicionarMensagem("Erro", "Falha no agendamento: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    // exclui o agendamento salvo anteriormente
    public void excluir(Agendamento agendamentoSelecionado) {
        try {
        	Long id = agendamentoSelecionado.getId();
            agendamentoDao.deletar(id);
            adicionarMensagem("concluído", "agendamento removido");
            
            listagem = null; // atualiza a lista para o agendamento ser removido
        
            PrimeFaces.current().executeScript(
                    "Swal.fire('Agendamento removido', 'Seu pedido de cancelamento foi salvo.', 'success');"
                );
        } catch (Exception e) {
            adicionarMensagem("Erro", "falha ao excluir: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // para o pop-up de msg aparecer
    private void adicionarMensagem(String sumario, String detalhe) {
        FacesContext.getCurrentInstance().addMessage(null, 
            new FacesMessage(FacesMessage.SEVERITY_INFO, sumario, detalhe));
    }

    public Agendamento getAgendamento() {
        return agendamento;
    }

    public void setAgendamento(Agendamento agendamento) {
        this.agendamento = agendamento;
    }

    public List<Agendamento> getListagem() {
        if (listagem == null) {
            listagem = agendamentoDao.listarTodos();
        }
        return listagem;
    }
}
