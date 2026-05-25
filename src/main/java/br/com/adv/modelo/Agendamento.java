package br.com.adv.modelo;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import java.time.LocalDateTime;

@Entity
@Table(name = "agendamentos")
public class Agendamento {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private Long id;
	
	@Column(name= "data", nullable = false)
	private LocalDateTime data;
	
	@Column(name = "nomeUsuario", nullable = false, length = 100)
	private String nomeUsuario;
	
	@Column(name = "cpfUsuario", nullable = false, length = 14)
	private String cpfUsuario;
	
	@Column(name = "emailUsuario", nullable = false, length = 50)
	private String emailUsuario;
	
	@Column(name = "nomeAdvogado", nullable = false, length = 100)
	private String nomeAdvogado;
	
	@Column(name = "tipoAtividade", nullable = false, length = 50)
	private String tipoAtividade;
	
	@Column(name = "primeiraReuniao", nullable = false)
	private Boolean primeiraReuniao;
	
	@Column(name = "observacao", nullable = true ,columnDefinition = "TEXT")
	private String observacao;
	
	public Agendamento() {
    }

    // --- GETTERS E SETTERS ---
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getCpfUsuario() {
        return cpfUsuario;
    }

    public void setCpfUsuario(String cpfUsuario) {
        this.cpfUsuario = cpfUsuario;
    }

    public String getEmailUsuario() {
        return emailUsuario;
    }

    public void setEmailUsuario(String emailUsuario) {
        this.emailUsuario = emailUsuario;
    }

    public String getNomeAdvogado() {
        return nomeAdvogado;
    }

    public void setNomeAdvogado(String nomeAdvogado) {
        this.nomeAdvogado = nomeAdvogado;
    }

    public String getTipoAtividade() {
        return tipoAtividade;
    }

    public void setTipoAtividade(String tipoAtividade) {
        this.tipoAtividade = tipoAtividade;
    }

    public Boolean getPrimeiraReuniao() {
        return primeiraReuniao;
    }

    public void setPrimeiraReuniao(Boolean primeiraReuniao) {
        this.primeiraReuniao = primeiraReuniao;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
}