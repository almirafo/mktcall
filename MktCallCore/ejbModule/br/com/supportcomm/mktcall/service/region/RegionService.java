package br.com.supportcomm.mktcall.service.region;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import br.com.supportcomm.mktcall.entity.AreaCode;
import br.com.supportcomm.mktcall.entity.Campanha;
import br.com.supportcomm.mktcall.entity.Region;
import br.com.supportcomm.mktcall.impl.areacode.AreaCodeBeanLocal;
import br.com.supportcomm.mktcall.impl.campanha.CampanhaBeanLocal;
import br.com.supportcomm.mktcall.region.RegionBeanLocal;

@Stateless
@LocalBean
public class RegionService {
	
	@EJB private RegionBeanLocal regionBean;
	@EJB private AreaCodeBeanLocal areaCodeBean;
	@EJB private CampanhaBeanLocal campanhaBean;
	public Object queryByRange(String jpqlStmt, int firstResult, int maxResults) {
		// TODO Auto-generated method stub
		return regionBean.queryByRange(jpqlStmt, firstResult, maxResults);
	}

	public Region persistRegion(Region region) {
		// TODO Auto-generated method stub
		return regionBean.persistRegion(region);
	}

	public Region mergeRegion(Region region) {
		// TODO Auto-generated method stub
		return regionBean.mergeRegion(region);
	}

	public void removeRegion(Region region) {
		// TODO Auto-generated method stub
		regionBean.removeRegion(region);
	}

	public List<Campanha> getRegionAdvertisement(String code) {
		List<Campanha> campanhas = new ArrayList<>();
		AreaCode areaCode = areaCodeBean.getAreaCodeByCode(code);
		if(areaCode.getIdAreaCode()!=null){
    	List<Region> regions =regionBean.getRegionByAreaCode(areaCode);
    	for(Region region:regions){
    		Campanha campanha = new Campanha();
    		
    		campanha = campanhaBean.getCampanhaId(region.getIdCampanha()).get(0);
    		if(campanha!=null){
    			campanhas.add(campanha);
    		}
    	}
		}
		return campanhas;
	}

	public List<Region> getRegionByCampanha(Campanha campanha) {
		// TODO Auto-generated method stub
		return regionBean.getRegionByCampanha(campanha);
	}

}
