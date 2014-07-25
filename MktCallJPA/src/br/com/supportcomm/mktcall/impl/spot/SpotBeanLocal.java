package br.com.supportcomm.mktcall.impl.spot;

import br.com.supportcomm.mktcall.entity.Spot;
import br.com.supportcomm.mktcall.entity.SpotAudioFile;
import br.com.supportcomm.mktcall.entity.SpotResponse;
import br.com.supportcomm.mktcall.entity.SpotTipo;

import java.util.List;
import javax.ejb.Local;


/**
 * @generated DT_ID=none
 */
@Local
public interface SpotBeanLocal
{

    /**
     * @generated DT_ID=none
     */
    Object queryByRange(String jpqlStmt, int firstResult, int maxResults);

    /**
     * @generated DT_ID=none
     */
    public Spot persistSpot(Spot spot);

    /**
     * @generated DT_ID=none
     */
    public Spot mergeSpot(Spot spot);

    /**
     * @generated DT_ID=none
     */
    public void removeSpot(Spot spot);

    /**
     * @generated DT_ID=none
     */
    public List<Spot> getSpotUsuario(Long idUsuario);

    /**
     * @generated DT_ID=none
     */
    public List<Spot> getSpotAnunciante(Long idAnunciante);

    /**
     * @generated DT_ID=none
     */
    public List<Spot> getSpotAgenciaUsuario(Long idUsuario);

    /**
     * @generated DT_ID=none
     */
    public List<Spot> getSpotById(Long id);

    /**
     * @generated DT_ID=none
     */
    public List<Spot> getSpotAll();

    /**
     * @generated DT_ID=none
     */
    public List<Spot> getSpotCampanha(Long idCampanha);

    /**
     * @generated DT_ID=none
     */
    public List<Spot> getSpotByName(String spotName);

    /**
     * @generated DT_ID=none
     */
    public SpotAudioFile persistSpotAudioFile(SpotAudioFile spotAudioFile);

    /**
     * @generated DT_ID=none
     */
    public SpotAudioFile mergeSpotAudioFile(SpotAudioFile spotAudioFile);

    /**
     * @generated DT_ID=none
     */
    public void removeSpotAudioFile(SpotAudioFile spotAudioFile);

    /**
     * @generated DT_ID=none
     */
    public List<SpotAudioFile> getSpotAudioFileSpotFileNameExistente(String nomeAudio);

    /**
     * @generated DT_ID=none
     */
    public List<SpotAudioFile> getSpotAudioFileByFileName(String filename);

    /**
     * @generated DT_ID=none
     */
    public List<SpotAudioFile> getSpotAudioFileBySpotId(Long idSpot);

    /**
     * @generated DT_ID=none
     */
    public List<SpotAudioFile> getSpotAudioFileById(Long id);

    /**
     * @generated DT_ID=none
     */
    public SpotResponse persistSpotResponse(SpotResponse spotResponse);

    /**
     * @generated DT_ID=none
     */
    public SpotResponse mergeSpotResponse(SpotResponse spotResponse);

    /**
     * @generated DT_ID=none
     */
    public void removeSpotResponse(SpotResponse spotResponse);

    /**
     * @generated DT_ID=none
     */
    public List<SpotResponse> getSpotId(Long idSpotResponse);

    /**
     * @generated DT_ID=none
     */
    public List<SpotResponse> getSpotAudioFiles(Long idSpotAudioFile);

    /**
     * @generated DT_ID=none
     */
    public List<SpotResponse> getSpotByMsisdnCampanha(Long idMsisdnCampanha);

    /**
     * @generated DT_ID=none
     */
    public SpotTipo persistSpotTipo(SpotTipo spotTipo);

    /**
     * @generated DT_ID=none
     */
    public SpotTipo mergeSpotTipo(SpotTipo spotTipo);

    /**
     * @generated DT_ID=none
     */
    public void removeSpotTipo(SpotTipo spotTipo);

    /**
     * @generated DT_ID=none
     */
    public List<SpotTipo> getSpotTipoAll();

    /**
     * @generated DT_ID=none
     */
    public List<SpotTipo> getSpotTipoId(Long id);

}
