package br.com.supportcomm.mktcall.entity;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.Index;

import br.com.supportcomm.mktcall.vo.ListaFidelizadaVO;

import java.sql.Timestamp;

/**
 * The persistent class for the lista_geral database table.
 * 
 */
@Entity
@Table(name = "lista_geral")
@NamedQueries({ @NamedQuery(name = "ListaGeral.existMsisdn", query = "select l from ListaGeral l where l.msisdn = :msisdn"),
		@NamedQuery(name = "ListaGeral.ByMsisdn", query = "select l.idCampanha from ListaGeral l where l.msisdn = :msisdn"), 
		@NamedQuery(name = "ListaGeral.All", query = "select l from ListaGeral l"),
		@NamedQuery(name = "ListaGeral.Campanha", query = "select distinct l from ListaGeral l where l.idCampanha = :idCampanha"),
		@NamedQuery(name = "ListaGeral.RemoveListByIdCampanha", query = "delete from ListaGeral as l where l.idCampanha = :idCampanha")
		 })

@NamedNativeQueries({@NamedNativeQuery(name = "ListaGeral.ListaCustomizada", query = "select " +
																					" c.name as campanhaNome, count(l.msisdn) as msisdnCount, l.id_campanha as idCampanha, c.status as status " +
																					"  from lista_geral l,campanha c where l.id_campanha = c.id_campanha group by c.name, l.id_campanha, c.status",
																					resultSetMapping="ListaGeralCustomizada")
})


@SqlResultSetMapping(name = "ListaGeralCustomizada", entities = {  @EntityResult(entityClass = ListaFidelizadaVO.class, fields = { 
	    @FieldResult(name = "campanhaNome", column = "campanhaNome"),
	    @FieldResult(name = "msisdnContagem", column = "msisdnCount"),
		@FieldResult(name = "idCampanha", column = "idCampanha"),
	    @FieldResult(name = "status", column = "status")

})

}

)
public class ListaGeral implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_list", unique = true, nullable = false)
	@Index(name = "idx_listageral_id")
	private Long idList;

	@Index(name = "idx_listageral_inserted_date")
	@Column(name = "inserted_date")
	private Timestamp insertedDate;

	@Index(name = "idx_listageral_msisdn")
	@Column(length = 15)
	private String msisdn;

	@Index(name = "idx_listageral_campanha")
	@Column(name = "id_campanha")
	private Long idCampanha;

	public ListaGeral() {
	}

	public Long getIdList() {
		return this.idList;
	}

	public void setIdList(Long idList) {
		this.idList = idList;
	}

	public Timestamp getInsertedDate() {
		return this.insertedDate;
	}

	public void setInsertedDate(Timestamp insertedDate) {
		this.insertedDate = insertedDate;
	}

	public String getMsisdn() {
		return this.msisdn;
	}

	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}

	public Long getIdCampanha() {
		return idCampanha;
	}

	public void setIdCampanha(Long idCampanha) {
		this.idCampanha = idCampanha;
	}

}