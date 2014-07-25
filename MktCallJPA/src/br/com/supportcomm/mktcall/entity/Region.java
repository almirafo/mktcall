package br.com.supportcomm.mktcall.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the region database table.
 * 
 */
@Entity




@NamedQueries({ @NamedQuery(name = "Region.getByCampanha", query = "select c from Region c where c.idCampanha= :idCampanha"),
                @NamedQuery(name = "Region.getByAreaCode", query = "Select r from Region r where r.areaCode = :areaCode")
})

public class Region implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="REGION_IDREGION_GENERATOR" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="REGION_IDREGION_GENERATOR")
	@Column(name="id_region")
	private Long idRegion;

	@Column(name="id_campanha")
	private Long idCampanha;

	//bi-directional many-to-one association to AreaCode
	@ManyToOne
	@JoinColumn(name="id_area_code")
	private AreaCode areaCode;

	public Region() {
	}

	public Long getIdRegion() {
		return this.idRegion;
	}

	public void setIdRegion(Long idRegion) {
		this.idRegion = idRegion;
	}

	public Long getIdCampanha() {
		return this.idCampanha;
	}

	public void setIdCampanha(Long idCampanha) {
		this.idCampanha = idCampanha;
	}

	public AreaCode getAreaCode() {
		return this.areaCode;
	}

	public void setAreaCode(AreaCode areaCode) {
		this.areaCode = areaCode;
	}

	
}