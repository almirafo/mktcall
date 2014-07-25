package br.com.supportcomm.mktcall.entity;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.Index;

import java.sql.Timestamp;


/**
 * The persistent class for the cadastro_participante database table.
 * 
 */
@Entity
@Table(name="cadastro_participante")
public class CadastroParticipante implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_cadastro_participante", unique=true, nullable=false)
	@Index(name="idx_cadastro_participante_id")
	private Long idCadastroParticipante;

	@Index(name = "idx_cadastroparticipante_msisdn")
	@Column(length=15)
	private String msisdn;

	@Index(name = "idx_cadastroparticipante_opcao_escolha")
	@Column(name="opcao_escolha")
	private Integer opcaoEscolha;

	@Index(name = "idx_cadastroparticipante_operation_date")
	@Column(name="operation_date")
	private Timestamp operationDate;

	@Index(name = "idx_cadastroparticipante_texto_sms")
	@Column(name="texto_sms", length=255)
	private String textoSms;

	//bi-directional many-to-one association to Campanha
	@ManyToOne
	@JoinColumn(name="id_campanha")
	private Campanha campanha;

	public CadastroParticipante() {
	}

	public Long getIdCadastroParticipante() {
		return this.idCadastroParticipante;
	}

	public void setIdCadastroParticipante(Long idCadastroParticipante) {
		this.idCadastroParticipante = idCadastroParticipante;
	}

	public String getMsisdn() {
		return this.msisdn;
	}

	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}

	public Integer getOpcaoEscolha() {
		return this.opcaoEscolha;
	}

	public void setOpcaoEscolha(Integer opcaoEscolha) {
		this.opcaoEscolha = opcaoEscolha;
	}

	public Timestamp getOperationDate() {
		return this.operationDate;
	}

	public void setOperationDate(Timestamp operationDate) {
		this.operationDate = operationDate;
	}

	public String getTextoSms() {
		return this.textoSms;
	}

	public void setTextoSms(String textoSms) {
		this.textoSms = textoSms;
	}

	public Campanha getCampanha() {
		return this.campanha;
	}

	public void setCampanha(Campanha campanha) {
		this.campanha = campanha;
	}

}