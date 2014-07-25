package br.com.supportcomm.mktcall.config;

import javax.ejb.Local;


@Local
public interface ConfigBeanLocal 
{
    public String getValueByIndentify(String indentify) ;

    
}
