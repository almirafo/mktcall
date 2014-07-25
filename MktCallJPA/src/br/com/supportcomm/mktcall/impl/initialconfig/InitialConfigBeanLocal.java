package br.com.supportcomm.mktcall.impl.initialconfig;

import java.util.List;

import javax.ejb.Local;

import br.com.supportcomm.mktcall.entity.InitialConfig;


/**
 * @generated DT_ID=none
 */
@Local
public interface InitialConfigBeanLocal
{


    /**
     * Lista de Uras para FTP <br/>
     * @generated DT_ID=none
     */
    public List<InitialConfig> getInitialConfigByType(String type);
    
    /**
     * Retorna se esta habilitado para executar ftp <br/>
     * @return
     */
    public Boolean getEnableFTP();
    
    /***
     * Retorna o endereço do arquivo origem para FTP <br/>
     * @return
     */
    public String getOriFTP();
    
    /**
     * Retorna o login e senha para efetuar FTP
     * @return
     */
    public String getLoginFTP();
    
}
