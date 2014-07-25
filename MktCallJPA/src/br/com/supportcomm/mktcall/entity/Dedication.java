package br.com.supportcomm.mktcall.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Index;

import br.com.supportcomm.mktcall.entity.Dialing;

/**
 * The persistent class for the Dedication database table.
 * 
 */
@Entity
@Table(name = "Dedication")

@NamedQueries({
	@NamedQuery(name="Dedication.getByName"  ,      query = "select d From Dedication d where d.name = :name"),
	@NamedQuery(name="Dedication.getByOrigin",      query = "select d From Dedication d where d.msisdnOrigin = :msisdnOrigin"),
	@NamedQuery(name="Dedication.getByDestination", query = "select d From Dedication d where d.msisdnDestination = :msisdnDestination")
})

public class Dedication implements Serializable
{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dedication_id", unique = true, nullable = false)
    private Long dedicationId;
    
    @Column(name = "msisdn_origin" , length = 21, nullable = false)
    @Index(name = "idx_msisdn_origin")
    private String msisdnOrigin;
	
    @Column(name = "msisdn_destination" , length = 21, nullable = false)
    @Index(name = "idx_msisdn_destination")
    private String msisdnDestination;	

    @Column(name = "datetime_inserted", nullable = false)
    private Timestamp datetimeInserted;

    @Column(length = 100)
    private String description;

	@Column(nullable = false, length = 50, unique = true)
    @Index(name = "idx_dedication_name")
    private String name;

    @Column(nullable = false)
    @Index(name = "idx_dedication_type")
    private Integer type;

    @Column(nullable = false)
    @Index(name = "idx_dedication_status")
    private Integer status;

    
    @Column(name = "datetime_schedule", nullable = false)
    @Index(name = "idx_datetime_scheduled")
    private Timestamp datetimeScheduled;
    
    
    @Column(name = "callid_origin")
    private String callIdOrigin;
    
    @Column(name = "callid_destination")
    private String callIdDestination;
    
    @Column(name = "audio")
    @Index(name = "idx_dedication_audio")
    private String audio;

    // bi-directional many-to-one association to Dialing
/*    @OneToMany(mappedBy = "dedication")
    private List<Dialing> dialings;
*/
    public Dedication()
    {
    }

    public Long getId()
    {
        return this.dedicationId;
    }

    public void setId(Long dedicationId)
    {
        this.dedicationId = dedicationId;
    }

    public Timestamp getDatetimeInserted()
    {
        return this.datetimeInserted;
    }

    public void setDatetimeInserted(Timestamp datetimeInserted)
    {
        this.datetimeInserted = datetimeInserted;
    }

    public String getDescription()
    {
        return this.description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getName()
    {
        return this.name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Integer getType()
    {
        return this.type;
    }

    public void setType(Integer type)
    {
        this.type = type;
    }

   /* public List<Dialing> getDialings()
    {
        return this.dialings;
    }

    public void setDialings(List<Dialing> dialings)
    {
        this.dialings = dialings;
    }*/

    public Integer getStatus()
    {
        return this.status;
    }

    public void setStatus(Integer status)
    {
        this.status = status;
    }
    
    public String getAudio() {
		return audio;
	}

	public void setAudio(String audio) {
		this.audio = audio;
	}

	public String getMsisdnOrigin() {
		return msisdnOrigin;
	}

	public void setMsisdnOrigin(String msisdnOrigin) {
		this.msisdnOrigin = msisdnOrigin;
	}

	public String getMsisdnDestination() {
		return msisdnDestination;
	}

	public void setMsisdnDestination(String msisdnDestination) {
		this.msisdnDestination = msisdnDestination;
	}

	public Timestamp getDatetimeScheduled() {
		return datetimeScheduled;
	}

	public void setDatetimeScheduled(Timestamp datetimeScheduled) {
		this.datetimeScheduled = datetimeScheduled;
	}

	public String getCallIdOrigin() {
		return callIdOrigin;
	}

	public void setCallIdOrigin(String callIdOrigin) {
		this.callIdOrigin = callIdOrigin;
	}

	public String getCallIdDestination() {
		return callIdDestination;
	}

	public void setCallIdDestination(String callIdDestination) {
		this.callIdDestination = callIdDestination;
	}

}
