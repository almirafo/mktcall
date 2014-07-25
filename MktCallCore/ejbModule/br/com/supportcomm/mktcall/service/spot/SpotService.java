package br.com.supportcomm.mktcall.service.spot;

import java.sql.Timestamp;
import java.util.List;
import org.apache.log4j.Logger;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import br.com.supportcomm.mktcall.entity.Spot;
import br.com.supportcomm.mktcall.entity.SpotAudioFile;
import br.com.supportcomm.mktcall.entity.SpotResponse;
import br.com.supportcomm.mktcall.entity.SpotTipo;
import br.com.supportcomm.mktcall.impl.spot.SpotBeanLocal;

/**
 * Session Bean implementation class SpotService
 */
@Stateless
@LocalBean
public class SpotService {

	@EJB private SpotBeanLocal spotBean;
	
	private Logger logger = Logger.getLogger(getClass().getName());
    /**
     * Default constructor. 
     */
    public SpotService() {
        // TODO Auto-generated constructor stub
    }


	
	public Object queryByRange(String jpqlStmt, int firstResult, int maxResults) {
		// TODO Auto-generated method stub
		return spotBean.queryByRange(jpqlStmt, firstResult, maxResults);
	}


	
	public Spot persistSpot(Spot spot) {
		// TODO Auto-generated method stub
		return spotBean.persistSpot(spot);
	}


	
	public Spot mergeSpot(Spot spot) {
		// TODO Auto-generated method stub
		return spotBean.mergeSpot(spot);
	}


	
	public void removeSpot(Spot spot) {
		// TODO Auto-generated method stub
		spotBean.removeSpot(spot);
	}


	
	public List<Spot> getSpotUsuario(Long idUsuario) {
		// TODO Auto-generated method stub
		return spotBean.getSpotUsuario(idUsuario);
	}


	
	public List<Spot> getSpotAnunciante(Long idAnunciante) {
		// TODO Auto-generated method stub
		return spotBean.getSpotAnunciante(idAnunciante);
	}


	
	public List<Spot> getSpotAgenciaUsuario(Long idUsuario) {
		// TODO Auto-generated method stub
		return spotBean.getSpotAgenciaUsuario(idUsuario);
	}


	
	public List<Spot> getSpotById(Long id) {
		// TODO Auto-generated method stub
		return spotBean.getSpotById(id);
	}


	
	public List<Spot> getSpotAll() {
		// TODO Auto-generated method stub
		return spotBean.getSpotAll();
	}


	
	public SpotAudioFile persistSpotAudioFile(SpotAudioFile spotAudioFile) {
		// TODO Auto-generated method stub
		return spotBean.persistSpotAudioFile(spotAudioFile);
	}


	
	public SpotAudioFile mergeSpotAudioFile(SpotAudioFile spotAudioFile) {
		// TODO Auto-generated method stub
		return spotBean.mergeSpotAudioFile(spotAudioFile);
	}


	
	public void removeSpotAudioFile(SpotAudioFile spotAudioFile) {
		// TODO Auto-generated method stub
		spotBean.removeSpotAudioFile(spotAudioFile);
	}


	
	public List<SpotAudioFile> getSpotAudioFileSpotFileNameExistente(String nomeAudio) {
		// TODO Auto-generated method stub
		return spotBean.getSpotAudioFileSpotFileNameExistente(nomeAudio);
	}


	
	public List<SpotAudioFile> getSpotAudioFileByFileName(String filename) {
		// TODO Auto-generated method stub
		return spotBean.getSpotAudioFileByFileName(filename);
	}


	
	public List<SpotAudioFile> getSpotAudioFileBySpotId(Long idSpot) {
		// TODO Auto-generated method stub

    	Timestamp intDate = new Timestamp(System.currentTimeMillis());
    	 List<SpotAudioFile> rs = spotBean.getSpotAudioFileBySpotId(idSpot);
		
    	Timestamp endDate = new Timestamp(System.currentTimeMillis());
		Long elap = endDate.getTime() - intDate.getTime();

		logger.debug("Elaptime "+ this.getClass().getName() +"getSpotAudioFileBySpotId -------->" + elap);
		
    	
		return rs;
	}


	
	public List<SpotAudioFile> getSpotAudioFileById(Long id) {
		// TODO Auto-generated method stub
		return spotBean.getSpotAudioFileById(id);
	}


	
	public SpotResponse persistSpotResponse(SpotResponse spotResponse) {
		// TODO Auto-generated method stub
		return spotBean.persistSpotResponse(spotResponse);
	}


	
	public SpotResponse mergeSpotResponse(SpotResponse spotResponse) {
		// TODO Auto-generated method stub
		return spotBean.mergeSpotResponse(spotResponse);
	}


	
	public void removeSpotResponse(SpotResponse spotResponse) {
		// TODO Auto-generated method stub
		spotBean.removeSpotResponse(spotResponse);
	}


	
	public SpotTipo persistSpotTipo(SpotTipo spotTipo) {
		// TODO Auto-generated method stub
		return spotBean.persistSpotTipo(spotTipo);
	}


	
	public SpotTipo mergeSpotTipo(SpotTipo spotTipo) {
		// TODO Auto-generated method stub
		return spotBean.mergeSpotTipo(spotTipo);
	}


	
	public void removeSpotTipo(SpotTipo spotTipo) {
		// TODO Auto-generated method stub
		spotBean.removeSpotTipo(spotTipo);
	}


	
	public List<SpotTipo> getSpotTipoAll() {
		// TODO Auto-generated method stub
		return spotBean.getSpotTipoAll();
	}


	
	public List<SpotTipo> getSpotTipoId(Long id) {
		// TODO Auto-generated method stub
		return spotBean.getSpotTipoId(id);
	}

	
	public List<Spot> getSpotCampanha(Long idCampanha) {
		// TODO Auto-generated method stub
		return spotBean.getSpotCampanha(idCampanha);
	}


	public List<Spot> getSpotByName(String spotName) {
		// TODO Auto-generated method stub
		return spotBean.getSpotByName(spotName);
	}


	public List<SpotResponse> getSpotId(Long idSpotResponse) {
		// TODO Auto-generated method stub
		return spotBean.getSpotId(idSpotResponse);
	}


	public List<SpotResponse> getSpotAudioFiles(Long idSpotAudioFile) {
		// TODO Auto-generated method stub
		return spotBean.getSpotAudioFiles(idSpotAudioFile);
	}


	public List<SpotResponse> getSpotByMsisdnCampanha(Long idMsisdnCampanha) {
		// TODO Auto-generated method stub
		return spotBean.getSpotByMsisdnCampanha(idMsisdnCampanha);
	}

	
}
