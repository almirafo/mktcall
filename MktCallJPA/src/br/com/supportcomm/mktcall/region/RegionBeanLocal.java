package br.com.supportcomm.mktcall.region;

import java.util.List;

import javax.ejb.Local;

import br.com.supportcomm.mktcall.entity.AreaCode;
import br.com.supportcomm.mktcall.entity.Campanha;
import br.com.supportcomm.mktcall.entity.Region;


/**
 * @generated DT_ID=none
 */
@Local
public interface RegionBeanLocal
{

    /**
     * @generated DT_ID=none
     */
    Object queryByRange(String jpqlStmt, int firstResult, int maxResults);

    /**
     * @generated DT_ID=none
     */
    public Region persistRegion(Region region);

    /**
     * @generated DT_ID=none
     */
    public Region mergeRegion(Region region);

    /**
     * @generated DT_ID=none
     */
    public void removeRegion(Region region);

    /**
     * @generated DT_ID=none
     */
    public List<Region> getRegionByAreaCode(AreaCode areaCode);

	public List<Region> getRegionByCampanha(Campanha campanha);

}
