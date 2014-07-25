package br.com.supportcomm.mktcall.entity;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.Index;

import java.sql.Timestamp;

/**
 * The persistent class for the cdr database table.
 * 
 */
@Entity
@Table(name = "cdr")
@NamedQueries({ @NamedQuery(name = "Cdr.Usuario"         , query = "select distinct c from Cdr as c, Spot as s, Anunciante as a, UserAccess as ua where ua.idUserAccess = :idUserAccess"),
               @NamedQuery(name = "Cdr.Agencia"          , query = "select distinct c from Cdr as c, Agencia as a, Spot as s, Anunciante as n where a.idAgencia = :idAgencia"),
               @NamedQuery(name = "Cdr.AgenciaUsuario"   , query = "select distinct c from Cdr as c, Spot as s, Anunciante as n, UserAccess as u where u.idUserAccess = :idUserAccess"),
               @NamedQuery(name = "Cdr.ByMsisdn"         , query = "select c from Cdr as c, Spot as s, Anunciante as n, UserAccess as ua where c.msisdnOriginator = :msisdnOriginator"),
               @NamedQuery(name = "Cdr.ByCallId"         , query = "select c from Cdr as c where c.callid = :callId"),
               @NamedQuery(name = "Cdr.tempoMedioChamada", query = "select c from Cdr as c where c.callDatetime >= :dataInicio and c.callDatetime <= :dataFim and c.msisdnOriginator like :ddd group by " + "c.callDatetime, c.campanha.idCampanha, c.campanha.name order by 1,2"),
               @NamedQuery(name = "Cdr.All"              , query = "select c from Cdr c"),
			   @NamedQuery(name = "Cdr.TodayOnce"        , query= "select c.callDatetime from Cdr c where c.duration >= :duration and c.msisdnOriginator = :msisdnOriginator and c.dialstatus = :dialstatus "),
               @NamedQuery(name = "Cdr.TodayOnceDay"     , query= "select c from Cdr c where c.duration >= :duration and c.msisdnOriginator = :msisdnOriginator and c.dialstatus = :dialstatus and  cast(c.callDatetime as date) = :diadehoje" )
               })
@NamedNativeQueries({ @NamedNativeQuery(name = "Cdr.DuracaoMediaChamadaPorCampanha", query = "select c from Cdr", resultClass = Cdr.class) })
public class Cdr implements Serializable
{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cdr", unique = true, nullable = false)
    @Index(name="idx_cdr_id")
    private Long idCdr;

    @Index(name = "idx_cdr_answer_datetime")
    @Column(name = "answer_datetime")
    private Timestamp answerDatetime;

    @Index(name = "idx_cdr_call_datetime")
    @Column(name = "call_datetime")
    private Timestamp callDatetime;

    @Index(name = "idx_cdr_destination_call")
    @Column(name = "destination_call", length = 20)
    private String destinationCall;

    @Index(name = "idx_cdr_duration")
    private Integer duration;

    @Index(name = "idx_cdr_callid")
    @Column(name = "callid")
    private String callid;

    @Index(name = "idx_cdr_hangup_cause")
    @Column(name = "hangup_cause")
    private int hangupCause;

    @Index(name = "idx_cdr_msisdn_originator")
    @Column(name = "msisdn_originator", length = 15)
    private String msisdnOriginator;

    @Index(name = "idx_cdr_session_time")
    @Column(name = "session_time")
    private int sessionTime;
    
    @Index(name = "idx_cdr_dialstatus")
    @Column(name = "dialstatus")
    private int dialstatus; 
    
    //bi-directional many-to-one association to Campanha
    @ManyToOne
    @JoinColumn(name = "id_campanha")
    private Campanha campanha;

    public Cdr()
    {
    }

    public Long getIdCdr()
    {
        return this.idCdr;
    }

    public void setIdCdr(Long idCdr)
    {
        this.idCdr = idCdr;
    }

    public Timestamp getAnswerDatetime()
    {
        return this.answerDatetime;
    }

    public void setAnswerDatetime(Timestamp answerDatetime)
    {
        this.answerDatetime = answerDatetime;
    }

    public Timestamp getCallDatetime()
    {
        return this.callDatetime;
    }

    public void setCallDatetime(Timestamp callDatetime)
    {
        this.callDatetime = callDatetime;
    }

    public String getDestinationCall()
    {
        return this.destinationCall;
    }

    public void setDestinationCall(String destinationCall)
    {
        this.destinationCall = destinationCall;
    }

    public Integer getDuration()
    {
        return this.duration;
    }

    public void setDuration(Integer duration)
    {
        this.duration = duration;
    }

    public int getHangupCause()
    {
        return this.hangupCause;
    }

    public void setHangupCause(int hangupCause)
    {
        this.hangupCause = hangupCause;
    }

    public String getMsisdnOriginator()
    {
        return this.msisdnOriginator;
    }

    public void setMsisdnOriginator(String msisdnOriginator)
    {
        this.msisdnOriginator = msisdnOriginator;
    }

    public Campanha getCampanha()
    {
        return this.campanha;
    }

    public void setCampanha(Campanha campanha)
    {
        this.campanha = campanha;
    }

    public String getCallid()
    {
        return callid;
    }

    public void setCallid(String callid)
    {
        this.callid = callid;
    }

	public int getSessionTime() {
		return sessionTime;
	}

	public void setSessionTime(int sessionTime) {
		this.sessionTime = sessionTime;
	}

	public int getDialstatus() {
		return dialstatus;
	}

	public void setDialstatus(int dialstatus) {
		this.dialstatus = dialstatus;
	}

}
