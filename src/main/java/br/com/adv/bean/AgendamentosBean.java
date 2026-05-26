package br.com.adv.bean;

import java.io.Serializable;
import java.util.List;
import jakarta.faces.view.ViewScoped;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.bean.ManagedBean;
import br.com.adv.dao.AgendamentoDAO;
import br.com.adv.modelo.Agendamento;

@ManagedBean(name = "agendamentoBean")
@ViewScoped
public class AgendamentosBean implements Serializable{
	private static final long serialVersionUID = 1L;
    private Agendamento agendamento = new Agendamento();
    private List<Agendamento> listagem; 
    private AgendamentoDAO dao = new AgendamentoDAO();
    
    public void salvar() {
        try {
            if (agendamento.getId() == null) {
                dao.salvar(agendamento);
                adicionarMensagem("concluído", "agendamento finalizado");
            } else {
                dao.atualizar(agendamento);
                adicionarMensagem("concluído", "agendamento finalizado");
            }
            
            agendamento = new Agendamento(); 
            
            listagem = null; 
            
        } catch (Exception e) {
            adicionarMensagem("Erro", "Falha ao processar o agendamento: " + e.getMessage());
            e.printStackTrace();
        }
    }

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
