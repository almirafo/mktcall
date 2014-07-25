package br.com.supportcomm.mktcall.impl.areacode;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.supportcomm.mktcall.entity.AreaCode;
import br.com.supportcomm.mktcall.entity.Campanha;


/**
 * @generated DT_ID=none
 */
@Stateless(name = "AreaCodeBean", mappedName = "MktCallJPA-AreaCodeBean")
public class AreaCodeBean
        implements AreaCodeBeanLocal
{

    /**
     * @generated DT_ID=none
     */
    @PersistenceContext(unitName="MktCallJPA")
    private EntityManager em;

    /**
     * @generated DT_ID=none
     */
    public AreaCodeBean() {
    }

    
    /**
     * @generated DT_ID=none
     */ 
    public List<AreaCode> getAreaCodeAll(){
    	int a=1;
    	List<AreaCode> rs = em.createNamedQuery("AreaCode.All").getResultList();
    	 return rs;
    }
    
    /**
     * @generated DT_ID=none
     */
	public List<AreaCode> getAreaCodeGetSelected(Campanha campanha){
		List<AreaCode> rs = new ArrayList<>();
		if (campanha!=null){
			rs =  em.createNamedQuery("AreaCode.Selected").setParameter("idCampanha", campanha.getIdCampanha()).getResultList();
		}else
		{
			rs =  em.createNamedQuery("AreaCode.Selected").setParameter("idCampanha",0l).getResultList();
		}
		 return rs;
	}
	
    /**
     * @generated DT_ID=none
     */
	@SuppressWarnings("unchecked")
	public List<AreaCode> getAreaCodeGetNotSelected(Campanha campanha){
		 return em.createNamedQuery("AreaCode.NotSelected").setParameter("idCampanha", campanha.getIdCampanha()).getResultList();
	}
    
	public AreaCode getAreaCode(AreaCode areaCode){
		return em.find(AreaCode.class, areaCode.getIdAreaCode());
	}

	public AreaCode getAreaCodeByCode(String code){
		AreaCode ac=new AreaCode();
		List<AreaCode> acs = em.createQuery( "Select c from AreaCode c where c.code = :code"  ).setParameter("code", code).getResultList(); 
		if (!acs.isEmpty()){
			 ac = acs.get(0);
		}
		
		return ac; 
	}
	
}
