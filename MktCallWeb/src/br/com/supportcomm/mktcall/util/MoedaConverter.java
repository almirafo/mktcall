package br.com.supportcomm.mktcall.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
/**
 * Conversor de mascara de moedas
 * Locale pt-BR
 *
 */
@FacesConverter(value="MoedaConverter")
public class MoedaConverter implements Converter
{

    @Override
    public Object getAsObject(FacesContext facesContext,
                              UIComponent uiComponent,
                              String value)
    {
        if (value != null)
        {
            value = value.trim();
            if (value.length() > 0)
            {
                try
                {
                    DecimalFormat df = new DecimalFormat("#,###.##",
                                                         new DecimalFormatSymbols(new Locale("pt",
                                                                                             "BR"))); // esta
                                                                                                      // �
                                                                                                      // metade
                                                                                                      // da
                                                                                                      // m�gica...
                    df.setParseBigDecimal(true); // esta � a outra metade da
                                                 // m�gica.
                    BigDecimal valor = (BigDecimal) df.parse(value);
                    return valor;
                }
                catch (ParseException e)
                {
                    return null;
                }
            }
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext facesContext,
                              UIComponent uiComponent,
                              Object value)
    {

        if (value == null)
        {
            return "";
        }
        if (value instanceof String)
        {
            return (String) value;
        }
        try
        {
            FacesContext fc = FacesContext.getCurrentInstance();
            Locale l = fc.getViewRoot().getLocale();
            NumberFormat formatador = NumberFormat.getNumberInstance(l);
            formatador.setGroupingUsed(true);
            return formatador.format(value);

        }
        catch (Exception e)
        {
            throw new ConverterException("Formato n�o � n�mero.");
        }
    }
}