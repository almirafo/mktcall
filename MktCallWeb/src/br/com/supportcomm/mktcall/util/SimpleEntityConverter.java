package br.com.supportcomm.mktcall.util;

import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.supportcomm.mktcall.entity.Agencia;
import br.com.supportcomm.mktcall.entity.Anunciante;
import br.com.supportcomm.mktcall.entity.Campanha;
import br.com.supportcomm.mktcall.entity.Spot;
import br.com.supportcomm.mktcall.util.BaseEntity;

/**
 * Conversor de objetos, para lista de objetos de combobox renderizados
 * automaitcamente
 * 
 * 
 */
@FacesConverter( value="simpleEntityConverter")
public class SimpleEntityConverter implements Converter
{
    /**
     * retorna o objeto, classe do objeto deve implementar BaseEntity
     */
    public Object getAsObject(FacesContext ctx, UIComponent component, String value)
    {
        if (value != null)
        {
            return this.getAttributesFrom(component).get(value);
        }
        return null;
    }

    /**
     * retorna valor em string do objeto
     */
    public String getAsString(FacesContext ctx, UIComponent component, Object value)
    {

        if (value != null && !"".equals(value))
        {

        	String key = "";
        	
            //BaseEntity entity = (BaseEntity) value;

            // adiciona item como atributo do componente
            // this.addAttribute(component, entity);
            
            if ( value instanceof Campanha){
            	key = ((Campanha) value).getIdCampanha().toString();
            }
            
            if ( value instanceof Agencia){
            	key = ((Agencia) value).getIdAgencia().toString();
            }
            
            if ( value instanceof Anunciante){
            	key = ((Anunciante) value).getIdAnunciante().toString();
            }
            
            if ( value instanceof Spot){
            	key = ((Spot) value).getIdSpot().toString();
            }
            
            this.getAttributesFrom(component).put(key, value);
            
            Integer codigo = Integer.parseInt(key);
            if (codigo != null)
            {
                return String.valueOf(codigo);
            }
        }

        return (String) value;
    }

    /**
     * adiciona atributo ao componente
     * 
     * @param component
     * @param o
     */
    protected void addAttribute(UIComponent component, BaseEntity  o)
    {
        String key = o.getId().toString(); // codigo como chave neste
                                           // caso
        this.getAttributesFrom(component).put(key, o);
    }

    /**
     * retorna os atributos do componente
     * 
     * @param component
     * @return
     */
    protected Map<String, Object> getAttributesFrom(UIComponent component)
    {
        return component.getAttributes();
    }

}
