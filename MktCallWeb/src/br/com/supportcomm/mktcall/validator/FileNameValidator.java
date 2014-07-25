package br.com.supportcomm.mktcall.validator;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import br.com.supportcomm.mktcall.entity.ListDetail;
import br.com.supportcomm.mktcall.service.listdetail.ListDetailService;




@ManagedBean(name="fileNameValidator")
@RequestScoped
public class FileNameValidator implements Validator{

	
	@EJB private ListDetailService listDetailService;
	
	@Override
	public void validate(FacesContext context, UIComponent component,Object value)	throws ValidatorException {
		String segmentName = (String) value;
		
		ListDetail listDetail =   listDetailService.findListDeailBySegmentName(segmentName);
		if(listDetail!=null){
			FacesMessage msg =	new FacesMessage("","Nome de lista Existente");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(msg);
		}
	}
	

}
