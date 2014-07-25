package br.com.supportcomm.mktcall.entity;

import java.io.Serializable;
import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the area_code database table.
 * 
 */
@Entity
@Table(name="area_code")
@NamedQueries({ @NamedQuery(name = "AreaCode.All"        , query = "select s from AreaCode s order by s.description"),
	            @NamedQuery(name = "AreaCode.Selected"   , query = "select s from AreaCode s inner join s.regions as region where region.idCampanha = :idCampanha order by s.description"),
	            @NamedQuery(name = "AreaCode.NotSelected", query = "select s from AreaCode s  where s.idAreaCode not in (select p.areaCode.idAreaCode from Region p where  p.idCampanha = :idCampanha) order by s.description")
})
public class AreaCode implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="AREA_CODE_IDAREACODE_GENERATOR" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="AREA_CODE_IDAREACODE_GENERATOR")
	@Column(name="id_area_code")
	private Long idAreaCode;

	private String code;

	private String description;

	//bi-directional many-to-one association to Region
	@OneToMany(mappedBy="areaCode")
	private List<Region> regions;

	public AreaCode() {
	}

	public Long getIdAreaCode() {
		return this.idAreaCode;
	}

	public void setIdAreaCode(Long idAreaCode) {
		this.idAreaCode = idAreaCode;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Region> getRegions() {
		return this.regions;
	}

	public void setRegions(List<Region> regions) {
		this.regions = regions;
	}

	
	public Region addRegions(Region regions) {
		getRegions().add(regions);
		regions.setAreaCode(this);

		return regions;
	}

	public Region removeRegions(Region regions) {
		getRegions().remove(regions);
		regions.setAreaCode(null);

		return regions;
	}
}