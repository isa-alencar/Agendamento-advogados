package br.com.adv.bean;

import java.io.Serializable;
import java.util.List;
import jakarta.faces.view.ViewScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import br.com.adv.dao.AgendamentoDAO;
import br.com.adv.modelo.Agendamento;

@Named("agendamentoBean")
@ViewScoped
public class AgendamentosBean implements Serializable{
	private static final long serialVersionUID = 1L;
	
	// espelho dos campos da index.xhtml
    private Agendamento agendamento = new Agendamento();
    private List<Agendamento> listagem; 
    private AgendamentoDAO dao = new AgendamentoDAO();
    
    // salva o agendamento ou edita um agendamento já no banco de dados
    public void salvar() {
        try {
            if (agendamento.getId() == null) {
                dao.salvar(agendamento);
                adicionarMensagem("concluído", "agendamento salvo");
            } else {
                dao.atualizar(agendamento);
                adicionarMensagem("concluído", "agendamento editado");
            }
            
            agendamento = new Agendamento(); 
            
            listagem = null; // busca os dados atualizados do banco
            
        } catch (Exception e) {
            adicionarMensagem("Erro", "falha no agendamento: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    // exclui o agendamento salvo anteriormente
    public void excluir(Agendamento agendamentoSelecionado) {
        try {
        	Long id = agendamentoSelecionado.getId();
            dao.deletar(id);
            adicionarMensagem("concluído", "agendamento removido");
            
            listagem = null; // atualiza a lista para o agendamento ser removido
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
            listagem = dao.listarTodos();
        }
        return listagem;
    }
}
