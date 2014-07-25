package br.com.supportcomm.mktcall.impl.areacode;

import java.util.List;

import javax.ejb.Local;

import br.com.supportcomm.mktcall.entity.AreaCode;
import br.com.supportcomm.mktcall.entity.Campanha;


/**
 * @generated DT_ID=none
 */
@Local
public interface AreaCodeBeanLocal
{

	public List<AreaCode> getAreaCodeAll();
	public List<AreaCode> getAreaCodeGetSelected(Campanha campanha);
	public List<AreaCode> getAreaCodeGetNotSelected(Campanha campanha);
	public AreaCode getAreaCode(AreaCode areaCode);
	public AreaCode getAreaCodeByCode(String code);

}
