package br.com.supportcomm.mktcall.impl.spot;

import java.sql.Timestamp;
import java.util.List;
//import org.apache.log4j.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.com.supportcomm.mktcall.entity.Spot;
import br.com.supportcomm.mktcall.entity.SpotAudioFile;
import br.com.supportcomm.mktcall.entity.SpotResponse;
import br.com.supportcomm.mktcall.entity.SpotTipo;


/**
 * @generated DT_ID=none
 */
@Stateless(name = "SpotBean", mappedName = "MktCallJPA-SpotBean")
public class SpotBean
        implements SpotBeanLocal
{

    /**
     * @generated DT_ID=none
     */
    @PersistenceContext(unitName="MktCallJPA")
    private EntityManager em;
    
 	//private Logger logger = Logger.getLogger(getClass().getName());

    /**
     * @generated DT_ID=none
     */
    public SpotBean() {
    }

    /**
     * @generated DT_ID=none
     */
    public Object queryByRange(String jpqlStmt, int firstResult,
                               int maxResults) {
        Query query = em.createQuery(jpqlStmt);
        if (firstResult > 0) {
            query = query.setFirstResult(firstResult);
        }
        if (maxResults > 0) {
            query = query.setMaxResults(maxResults);
        }

        return query.getResultList();
    }

    /**
     * @generated DT_ID=none
     */
    public Spot persistSpot(Spot spot) {
        em.persist(spot);
        return spot;
    }

    /**
     * @generated DT_ID=none
     */
    public Spot mergeSpot(Spot spot) {
        return em.merge(spot);
    }

    /**
     * @generated DT_ID=none
     */
    public void removeSpot(Spot spot) {
       // Spot _spot = em.find(Spot.class, spot.getIdSpot());
       // em.remove(_spot);
        em.createQuery("Delete from Spot s where s.idSpot = :idSpot").setParameter("idSpot", spot.getIdSpot()).executeUpdate();
    }

    /**
     * @generated DT_ID=none
     */
    @SuppressWarnings("unchecked")
    public List<Spot> getSpotUsuario(Long idUsuario) {
        return em.createNamedQuery("Spot.Usuario").setParameter("idUsuario", idUsuario).getResultList();
    }

    /**
     * @generated DT_ID=none
     */
    @SuppressWarnings("unchecked")
    public List<Spot> getSpotAnunciante(Long idAnunciante) {
        return em.createNamedQuery("Spot.Anunciante").setParameter("idAnunciante", idAnunciante).getResultList();
    }

    /**
     * @generated DT_ID=none
     */
    @SuppressWarnings("unchecked")
    public List<Spot> getSpotAgenciaUsuario(Long idUsuario) {
        return em.createNamedQuery("Spot.AgenciaUsuario").setParameter("idUsuario", idUsuario).getResultList();
    }

    /**
     * @generated DT_ID=none
     */
    @SuppressWarnings("unchecked")
    public List<Spot> getSpotById(Long id) {
    	
    	Timestamp intDate = new Timestamp(System.currentTimeMillis());
		List<Spot> rs = em.createNamedQuery("Spot.ById").setParameter("id", id).getResultList();
		
		Timestamp endTime = new Timestamp(System.currentTimeMillis());
		
		Long elap = endTime.getTime() - intDate.getTime();
		//logger.debug("Elaptime "+ this.getClass().getName() +" -------->" + elap);
    	
    	return rs;
    }

    /**
     * @generated DT_ID=none
     */
    @SuppressWarnings("unchecked")
    public List<Spot> getSpotAll() {
        return em.createNamedQuery("Spot.All").getResultList();
    }

    /**
     * @generated DT_ID=none
     */
    @SuppressWarnings("unchecked")
    public List<Spot> getSpotCampanha(Long idCampanha) {
        return em.createNamedQuery("Spot.Campanha").setParameter("idCampanha", idCampanha).getResultList();
    }

    /**
     * @generated DT_ID=none
     */
    @SuppressWarnings("unchecked")
    public List<Spot> getSpotByName(String spotName) {
        return em.createNamedQuery("Spot.ByName").setParameter("spotName", spotName).getResultList();
    }

    /**
     * @generated DT_ID=none
     */
    public SpotAudioFile persistSpotAudioFile(SpotAudioFile spotAudioFile) {
        em.persist(spotAudioFile);
        return spotAudioFile;
    }

    /**
     * @generated DT_ID=none
     */
    public SpotAudioFile mergeSpotAudioFile(SpotAudioFile spotAudioFile) {
        return em.merge(spotAudioFile);
    }

    /**
     * @generated DT_ID=none
     */
    public void removeSpotAudioFile(SpotAudioFile spotAudioFile) {
        spotAudioFile = em.find(SpotAudioFile.class, spotAudioFile.getIdSpotAudioFiles());
        em.remove(spotAudioFile);
    }

    /**
     * @generated DT_ID=none
     */
    @SuppressWarnings("unchecked")
    public List<SpotAudioFile> getSpotAudioFileSpotFileNameExistente(String nomeAudio) {
        return em.createNamedQuery("SpotAudioFile.spotFileNameExistente").setParameter("nomeAudio", nomeAudio).getResultList();
    }

    /**
     * @generated DT_ID=none
     */
    @SuppressWarnings("unchecked")
    public List<SpotAudioFile> getSpotAudioFileByFileName(String filename) {
        return em.createNamedQuery("SpotAudioFile.ByFileName").setParameter("filename", filename).getResultList();
    }

    /**
     * @generated DT_ID=none
     */
    @SuppressWarnings("unchecked")
    public List<SpotAudioFile> getSpotAudioFileBySpotId(Long idSpot) {

    	Timestamp intDate = new Timestamp(System.currentTimeMillis());
    	List<SpotAudioFile> rs = em.createNamedQuery("SpotAudioFile.BySpotId").setParameter("idSpot", idSpot).getResultList();
		Timestamp endTime = new Timestamp(System.currentTimeMillis());
		
		Long elap = endTime.getTime() - intDate.getTime();
		//logger.debug("Elaptime "+ this.getClass().getName() +" getSpotAudioFileBySpotId -------->" + elap);
        return rs;
    }

    /**
     * @generated DT_ID=none
     */
    @SuppressWarnings("unchecked")
    public List<SpotAudioFile> getSpotAudioFileById(Long id) {
        return em.createNamedQuery("SpotAudioFile.ById").setParameter("id", id).getResultList();
    }

    /**
     * @generated DT_ID=none
     */
    public SpotResponse persistSpotResponse(SpotResponse spotResponse) {
        em.persist(spotResponse);
        return spotResponse;
    }

    /**
     * @generated DT_ID=none
     */
    public SpotResponse mergeSpotResponse(SpotResponse spotResponse) {
        return em.merge(spotResponse);
    }

    /**
     * @generated DT_ID=none
     */
    public void removeSpotResponse(SpotResponse spotResponse) {
        spotResponse = em.find(SpotResponse.class, spotResponse.getIdSpotResponse());
        em.remove(spotResponse);
    }

    /**
     * @generated DT_ID=none
     */
    @SuppressWarnings("unchecked")
    public List<SpotResponse> getSpotId(Long idSpotResponse) {
        return em.createNamedQuery("Spot.id").setParameter("idSpotResponse", idSpotResponse).getResultList();
    }

    /**
     * @generated DT_ID=none
     */
    @SuppressWarnings("unchecked")
    public List<SpotResponse> getSpotAudioFiles(Long idSpotAudioFile) {
        return em.createNamedQuery("Spot.AudioFiles").setParameter("idSpotAudioFile", idSpotAudioFile).getResultList();
    }

    /**
     * @generated DT_ID=none
     */
    @SuppressWarnings("unchecked")
    public List<SpotResponse> getSpotByMsisdnCampanha(Long idMsisdnCampanha) {
        return em.createNamedQuery("Spot.byMsisdnCampanha").setParameter("idMsisdnCampanha", idMsisdnCampanha).getResultList();
    }

    /**
     * @generated DT_ID=none
     */
    public SpotTipo persistSpotTipo(SpotTipo spotTipo) {
        em.persist(spotTipo);
        return spotTipo;
    }

    /**
     * @generated DT_ID=none
     */
    public SpotTipo mergeSpotTipo(SpotTipo spotTipo) {
        return em.merge(spotTipo);
    }

    /**
     * @generated DT_ID=none
     */
    public void removeSpotTipo(SpotTipo spotTipo) {
        spotTipo = em.find(SpotTipo.class, spotTipo.getIdSpotTipo());
        em.remove(spotTipo);
    }

    /**
     * @generated DT_ID=none
     */
    @SuppressWarnings("unchecked")
    public List<SpotTipo> getSpotTipoAll() {
        return em.createNamedQuery("spotTipo.AllNotObsolete").getResultList();
    }

    /**
     * @generated DT_ID=none
     */
    @SuppressWarnings("unchecked")
    public List<SpotTipo> getSpotTipoId(Long id) {
        return em.createNamedQuery("spotTipo.Id").setParameter("id", id).getResultList();
    }

}
