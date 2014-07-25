package br.com.supportcomm.mktcall.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Index;


/**
 * The persistent class for the estimulo database table.
 * 
 */
@Entity
@Table(name="estimulo")
public class Estimulo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_estimulo", unique=true, nullable=false)
	@Index(name="idx_estimulo_id")
	private Long idEstimulo;

	@Column(name="clientes_efetivamente_impactados")
	@Index(name="idx_estimulo_cliente_efetivamente_impactados")
	private Long clientesEfetivamenteImpactados;

	@Column(name="clientes_impactados")
	@Index(name="idx_estimulo_clientes_impactados")
	private Long clientesImpactados;

	@Column(name="operation_date")
	@Index(name="idx_estimulo_cliente_operation")
	private Timestamp operationDate;

	public Estimulo() {
	}

	public Long getIdEstimulo() {
		return this.idEstimulo;
	}

	public void setIdEstimulo(Long idEstimulo) {
		this.idEstimulo = idEstimulo;
	}

	public Long getClientesEfetivamenteImpactados() {
		return this.clientesEfetivamenteImpactados;
	}

	public void setClientesEfetivamenteImpactados(Long clientesEfetivamenteImpactados) {
		this.clientesEfetivamenteImpactados = clientesEfetivamenteImpactados;
	}

	public Long getClientesImpactados() {
		return this.clientesImpactados;
	}

	public void setClientesImpactados(Long clientesImpactados) {
		this.clientesImpactados = clientesImpactados;
	}

	public Timestamp getOperationDate() {
		return this.operationDate;
	}

	public void setOperationDate(Timestamp operationDate) {
		this.operationDate = operationDate;
	}

}