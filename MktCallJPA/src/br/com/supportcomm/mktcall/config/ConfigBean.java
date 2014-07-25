package br.com.supportcomm.mktcall.config;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.supportcomm.mktcall.impl.anunciante.AnuncianteBeanLocal;

/**
 * @generated DT_ID=none
 */
@Stateless(name = "ConfigBean", mappedName = "MktCallJPA-ConfigBean")
public class ConfigBean implements ConfigBeanLocal{

    /**
     * @generated DT_ID=none
     */
    @PersistenceContext(unitName="MktCallJPA")
    private EntityManager em;

	@Override
	public String getValueByIndentify(String indentify) {
		String resultado = (String)em.createNativeQuery("Select c.value from Config c where c.indentify= :indentify").setParameter("indentify", indentify).getSingleResult();		
		return resultado;
	}



}
