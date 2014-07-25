//
// Este arquivo foi gerado pela Arquitetura JavaTM para Implementação de Referência (JAXB) de Bind XML, v2.2.5-2 
// Consulte <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas as modificações neste arquivo serão perdidas após a recompilação do esquema de origem. 
// Gerado em: PM.12.19 às 05:35:05 PM BRST 
//


package br.com.supportcomm.mktcall.xml.status.advertisement;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the br.com.supportcomm.mktcall.xml.status.advertisement package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: br.com.supportcomm.mktcall.xml.status.advertisement
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Response }
     * 
     */
    public ResponseAdvertisement createResponse() {
        return new ResponseAdvertisement();
    }

    /**
     * Create an instance of {@link Response.Body }
     * 
     */
    public ResponseAdvertisement.Body createResponseBody() {
        return new ResponseAdvertisement.Body();
    }

    /**
     * Create an instance of {@link Response.Body.Audios }
     * 
     */
    public ResponseAdvertisement.Body.Audios createResponseBodyAudios() {
        return new ResponseAdvertisement.Body.Audios();
    }

    /**
     * Create an instance of {@link Response.Body.Audios.Audio }
     * 
     */
    public ResponseAdvertisement.Body.Audios.Audio createResponseBodyAudiosAudio() {
        return new ResponseAdvertisement.Body.Audios.Audio();
    }

}
