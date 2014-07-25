package br.com.supportcomm.mktcall.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Index;

/**
 * Tabela que conterá o numero que vai ser discado
 * vai fazer um join com a tabela de lista e onde esta fará join com a campanha
 * 
 */
@Entity
@Table(name = "Dialing")
public class Dialing implements Serializable
{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dialing_id", unique = true, nullable = false)
    private Long dialingId;

    @Column(name = "datetime_inserted", nullable = false)
    private Timestamp datetimeInserted;

    @Column(name = "datetime_last_operation", nullable = false)
    private Timestamp datetimeLastOperation;

    @Column(name = "datetime_schedule", nullable = false)
    @Index(name = "idx_datetime_scheduled")
    private Timestamp datetimeScheduled;

    @Column(name = "msisdn_dialing", length = 21, nullable = false)
    @Index(name = "idx_msisdn")
    private String msisdnDialing;
    
    @Column(nullable = false)
    @Index(name = "idx_status")
    private Integer status;

    @Column(length = 10, nullable = false)
    @Index(name = "idx_action")
    private String action;
    
    @Column(name = "attempts", nullable = false)
    @Index(name = "idx_attempts")
    private Integer attempts; 
    
    @Column(name = "response_code")
    private String responseCode;
    
    @Column(name = "response_message")
    private String responseMessage;    
    

	
	@ManyToOne
	@JoinColumn(name="id_list")
	private ListDetail idList;
    
    public Dialing()
    {
    }

    public Long getId()
    {
        return this.dialingId;
    }

    public void setId(Long dialingId)
    {
        this.dialingId = dialingId;
    }

    public Timestamp getDatetimeInserted()
    {
        return this.datetimeInserted;
    }

    public void setDatetimeInserted(Timestamp datetimeInserted)
    {
        this.datetimeInserted = datetimeInserted;
    }

    public Timestamp getDatetimeLastOperation()
    {
        return this.datetimeLastOperation;
    }

    public void setDatetimeLastOperation(Timestamp datetimeLastOperation)
    {
        this.datetimeLastOperation = datetimeLastOperation;
    }

    public String getMsisdnDialing()
    {
        return this.msisdnDialing;
    }

    public void setMsisdnDialing(String msisdnDialing)
    {
        this.msisdnDialing = msisdnDialing;
    }

    public Integer getStatus()
    {
        return this.status;
    }

    public void setStatus(Integer status)
    {
        this.status = status;
    }

    public String getAction()
    {
        return this.action;
    }

    public void setAction(String action)
    {
        this.action = action;
    }

	public void setAttempts(Integer attempts) {
		this.attempts = attempts;
	}

	public Timestamp getDatetimeScheduled() {
		return datetimeScheduled;
	}

	public void setDatetimeScheduled(Timestamp datetimeScheduled) {
		this.datetimeScheduled = datetimeScheduled;
	}

	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	public String getResponseMessage() {
		return responseMessage;
	}

	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}

	public Integer getAttempts() {
		return attempts;
	}

	public ListDetail getIdList() {
		return idList;
	}

	public void setIdList(ListDetail idList) {
		this.idList = idList;
	}

	
}

