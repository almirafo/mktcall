//
// Este arquivo foi gerado pela Arquitetura JavaTM para Implementação de Referência (JAXB) de Bind XML, v2.2.5-2 
// Consulte <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas as modificações neste arquivo serão perdidas após a recompilação do esquema de origem. 
// Gerado em: PM.12.19 às 05:35:05 PM BRST 
//


package br.com.supportcomm.mktcall.xml.status.advertisement;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java de anonymous complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="body" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="statusMSISDN" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="campaignID" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *                   &lt;element name="advertisementType" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *                   &lt;element name="message" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="serverURL" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="confirmationDigit" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *                   &lt;element name="callDuration" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="allowOnNet" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                   &lt;element name="allowOffNet" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                   &lt;element name="allowLandlineOnNet" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                   &lt;element name="allowLandlineOffNet" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                   &lt;element name="nextCampaign" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                   &lt;element name="newUser" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                   &lt;element name="playWelcome" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                   &lt;element name="audios" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="audio" maxOccurs="unbounded" minOccurs="0">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence>
 *                                       &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *                                       &lt;element name="fileName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                                       &lt;element name="order" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *                                       &lt;element name="getDigits" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *                                     &lt;/sequence>
 *                                   &lt;/restriction>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "status",
    "body"
})
@XmlRootElement(name = "response")
public class ResponseAdvertisement {

    protected String status;
    protected ResponseAdvertisement.Body body;

    /**
     * Obtém o valor da propriedade status.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatus() {
        return status;
    }

    /**
     * Define o valor da propriedade status.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatus(String value) {
        this.status = value;
    }

    /**
     * Obtém o valor da propriedade body.
     * 
     * @return
     *     possible object is
     *     {@link Response.Body }
     *     
     */
    public ResponseAdvertisement.Body getBody() {
        return body;
    }

    /**
     * Define o valor da propriedade body.
     * 
     * @param value
     *     allowed object is
     *     {@link Response.Body }
     *     
     */
    public void setBody(ResponseAdvertisement.Body value) {
        this.body = value;
    }


    /**
     * <p>Classe Java de anonymous complex type.
     * 
     * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="statusMSISDN" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="campaignID" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
     *         &lt;element name="advertisementType" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
     *         &lt;element name="message" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="serverURL" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="confirmationDigit" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
     *         &lt;element name="callDuration" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="allowOnNet" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *         &lt;element name="allowOffNet" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *         &lt;element name="allowLandlineOnNet" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *         &lt;element name="allowLandlineOffNet" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *         &lt;element name="nextCampaign" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *         &lt;element name="newUser" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *         &lt;element name="playWelcome" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *         &lt;element name="forwad"  minOccurs="0"/>
     *         
     *         &lt;element name="audios" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="audio" maxOccurs="unbounded" minOccurs="0">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;sequence>
     *                             &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
     *                             &lt;element name="fileName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                             &lt;element name="order" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
     *                             &lt;element name="getDigits" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
     *                           &lt;/sequence>
     *                         &lt;/restriction>
     *                       &lt;/complexContent>
     *                     &lt;/complexType>
     *                   &lt;/element>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "description",
        "statusMSISDN",
        "campaignID",
        "advertisementType",
        "message",
        "serverURL",
        "protocol",
        "confirmationDigit",
        "callDuration",
        "allowOnNet",
        "allowOffNet",
        "allowLandlineOnNet",
        "allowLandlineOffNet",
        "nextCampaign",
        "newUser",
        "playWelcome",
        "audios",
        "forward"
    })
    public static class Body {

        protected String description;
        protected String statusMSISDN;
        protected Integer campaignID;
        protected Integer advertisementType;
        protected String message;
        protected String serverURL;
        protected String protocol;
        protected String confirmationDigit;
        protected String callDuration;
        protected Boolean allowOnNet;
        protected Boolean allowOffNet;
        protected Boolean allowLandlineOnNet;
        protected Boolean allowLandlineOffNet;
        protected Boolean nextCampaign;
        protected Boolean newUser;
        protected Boolean playWelcome;
        protected ResponseAdvertisement.Body.Forward forward;
        protected ResponseAdvertisement.Body.Audios audios;

        /**
         * Obtém o valor da propriedade description.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getDescription() {
            return description;
        }

        /**
         * Define o valor da propriedade description.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setDescription(String value) {
            this.description = value;
        }

        /**
         * Obtém o valor da propriedade statusMSISDN.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getStatusMSISDN() {
            return statusMSISDN;
        }

        /**
         * Define o valor da propriedade statusMSISDN.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setStatusMSISDN(String value) {
            this.statusMSISDN = value;
        }

        /**
         * Obtém o valor da propriedade campaignID.
         * 
         * @return
         *     possible object is
         *     {@link Integer }
         *     
         */
        public Integer getCampaignID() {
            return campaignID;
        }

        /**
         * Define o valor da propriedade campaignID.
         * 
         * @param value
         *     allowed object is
         *     {@link Integer }
         *     
         */
        public void setCampaignID(Integer value) {
            this.campaignID = value;
        }

        /**
         * Obtém o valor da propriedade advertisementType.
         * 
         * @return
         *     possible object is
         *     {@link Integer }
         *     
         */
        public Integer getAdvertisementType() {
            return advertisementType;
        }

        /**
         * Define o valor da propriedade advertisementType.
         * 
         * @param value
         *     allowed object is
         *     {@link Integer }
         *     
         */
        public void setAdvertisementType(Integer value) {
            this.advertisementType = value;
        }

        /**
         * Obtém o valor da propriedade message.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getMessage() {
            return message;
        }

        /**
         * Define o valor da propriedade message.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setMessage(String value) {
            this.message = value;
        }

        /**
         * Obtém o valor da propriedade serverURL.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getServerURL() {
            return serverURL;
        }

        /**
         * Define o valor da propriedade serverURL.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setServerURL(String value) {
            this.serverURL = value;
        }

        public String getProtocol() {
			return protocol;
		}

		public void setProtocol(String protocol) {
			this.protocol = protocol;
		}
		
        /**
         * Obtém o valor da propriedade confirmationDigit.
         * 
         * @return
         *     possible object is
         *     {@link Integer }
         *     
         */
        public String getConfirmationDigit() {
            return confirmationDigit;
        }

        /**
         * Define o valor da propriedade confirmationDigit.
         * 
         * @param value
         *     allowed object is
         *     {@link Integer }
         *     
         */
        public void setConfirmationDigit(String value) {
            this.confirmationDigit = value;
        }

        /**
         * Obtém o valor da propriedade callDuration.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCallDuration() {
            return callDuration;
        }

        /**
         * Define o valor da propriedade callDuration.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCallDuration(String value) {
            this.callDuration = value;
        }

        /**
         * Obtém o valor da propriedade allowOnNet.
         * 
         * @return
         *     possible object is
         *     {@link Boolean }
         *     
         */
        public Boolean isAllowOnNet() {
            return allowOnNet;
        }

        /**
         * Define o valor da propriedade allowOnNet.
         * 
         * @param value
         *     allowed object is
         *     {@link Boolean }
         *     
         */
        public void setAllowOnNet(Boolean value) {
            this.allowOnNet = value;
        }

        /**
         * Obtém o valor da propriedade allowOffNet.
         * 
         * @return
         *     possible object is
         *     {@link Boolean }
         *     
         */
        public Boolean isAllowOffNet() {
            return allowOffNet;
        }

        /**
         * Define o valor da propriedade allowOffNet.
         * 
         * @param value
         *     allowed object is
         *     {@link Boolean }
         *     
         */
        public void setAllowOffNet(Boolean value) {
            this.allowOffNet = value;
        }

        /**
         * Obtém o valor da propriedade allowLandlineOnNet.
         * 
         * @return
         *     possible object is
         *     {@link Boolean }
         *     
         */
        public Boolean isAllowLandlineOnNet() {
            return allowLandlineOnNet;
        }

        /**
         * Define o valor da propriedade allowLandlineOnNet.
         * 
         * @param value
         *     allowed object is
         *     {@link Boolean }
         *     
         */
        public void setAllowLandlineOnNet(Boolean value) {
            this.allowLandlineOnNet = value;
        }

        /**
         * Obtém o valor da propriedade allowLandlineOffNet.
         * 
         * @return
         *     possible object is
         *     {@link Boolean }
         *     
         */
        public Boolean isAllowLandlineOffNet() {
            return allowLandlineOffNet;
        }

        /**
         * Define o valor da propriedade allowLandlineOffNet.
         * 
         * @param value
         *     allowed object is
         *     {@link Boolean }
         *     
         */
        public void setAllowLandlineOffNet(Boolean value) {
            this.allowLandlineOffNet = value;
        }

        /**
         * Obtém o valor da propriedade nextCampaign.
         * 
         * @return
         *     possible object is
         *     {@link Boolean }
         *     
         */
        public Boolean isNextCampaign() {
            return nextCampaign;
        }

        /**
         * Define o valor da propriedade nextCampaign.
         * 
         * @param value
         *     allowed object is
         *     {@link Boolean }
         *     
         */
        public void setNextCampaign(Boolean value) {
            this.nextCampaign = value;
        }

        /**
         * Obtém o valor da propriedade newUser.
         * 
         * @return
         *     possible object is
         *     {@link Boolean }
         *     
         */
        public Boolean isNewUser() {
            return newUser;
        }

        /**
         * Define o valor da propriedade newUser.
         * 
         * @param value
         *     allowed object is
         *     {@link Boolean }
         *     
         */
        public void setNewUser(Boolean value) {
            this.newUser = value;
        }

        /**
         * Obtém o valor da propriedade playWelcome.
         * 
         * @return
         *     possible object is
         *     {@link Boolean }
         *     
         */
        public Boolean isPlayWelcome() {
            return playWelcome;
        }

        /**
         * Define o valor da propriedade playWelcome.
         * 
         * @param value
         *     allowed object is
         *     {@link Boolean }
         *     
         */
        public void setPlayWelcome(Boolean value) {
            this.playWelcome = value;
        }

        /**
         * Obtém o valor da propriedade audios.
         * 
         * @return
         *     possible object is
         *     {@link Response.Body.Audios }
         *     
         */
        public ResponseAdvertisement.Body.Audios getAudios() {
            return audios;
        }

        /**
         * Define o valor da propriedade audios.
         * 
         * @param value
         *     allowed object is
         *     {@link Response.Body.Audios }
         *     
         */
        public void setAudios(ResponseAdvertisement.Body.Audios value) {
            this.audios = value;
        }
        //----
        /**
         * Obtém o valor da propriedade audios.
         * 
         * @return
         *     possible object is
         *     {@link Response.Body.Audios }
         *     
         */
        public ResponseAdvertisement.Body.Forward getForward() {
            return forward;
        }

        /**
         * Define o valor da propriedade audios.
         * 
         * @param value
         *     allowed object is
         *     {@link Response.Body.Audios }
         *     
         */
        public void setforwad(ResponseAdvertisement.Body.Forward value) {
            this.forward = value;
        }
        //----

        /**
         * <p>Classe Java de anonymous complex type.
         * 
         * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       &lt;sequence>
         *         &lt;element name="audio" maxOccurs="unbounded" minOccurs="0">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;sequence>
         *                   &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
         *                   &lt;element name="fileName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *                   &lt;element name="order" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
         *                   &lt;element name="getDigits" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
         *                 &lt;/sequence>
         *               &lt;/restriction>
         *             &lt;/complexContent>
         *           &lt;/complexType>
         *         &lt;/element>
         *       &lt;/sequence>
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "audio"
        })
        public static class Audios {

            protected List<ResponseAdvertisement.Body.Audios.Audio> audio;

            /**
             * Gets the value of the audio property.
             * 
             * <p>
             * This accessor method returns a reference to the live list,
             * not a snapshot. Therefore any modification you make to the
             * returned list will be present inside the JAXB object.
             * This is why there is not a <CODE>set</CODE> method for the audio property.
             * 
             * <p>
             * For example, to add a new item, do as follows:
             * <pre>
             *    getAudio().add(newItem);
             * </pre>
             * 
             * 
             * <p>
             * Objects of the following type(s) are allowed in the list
             * {@link Response.Body.Audios.Audio }
             * 
             * 
             */
            public List<ResponseAdvertisement.Body.Audios.Audio> getAudio() {
                if (audio == null) {
                    audio = new ArrayList<ResponseAdvertisement.Body.Audios.Audio>();
                }
                return this.audio;
            }




            
            
            
            /**
             * <p>Classe Java de anonymous complex type.
             * 
             * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
             * 
             * <pre>
             * &lt;complexType>
             *   &lt;complexContent>
             *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *       &lt;sequence>
             *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
             *         &lt;element name="fileName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
             *         &lt;element name="order" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
             *         &lt;element name="getDigits" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
             *       &lt;/sequence>
             *     &lt;/restriction>
             *   &lt;/complexContent>
             * &lt;/complexType>
             * </pre>
             * 
             * 
             */
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "", propOrder = {
                "id",
                "fileName",
                "order",
                "lengthDigit"
            })
            public static class Audio {

                protected Integer id;
                protected String fileName;
                protected Integer order;
                protected Integer lengthDigit;

                /**
                 * Obtém o valor da propriedade id.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Integer }
                 *     
                 */
                public Integer getId() {
                    return id;
                }

                /**
                 * Define o valor da propriedade id.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Integer }
                 *     
                 */
                public void setId(Integer value) {
                    this.id = value;
                }

                /**
                 * Obtém o valor da propriedade fileName.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getFileName() {
                    return fileName;
                }

                /**
                 * Define o valor da propriedade fileName.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setFileName(String value) {
                    this.fileName = value;
                }

                /**
                 * Obtém o valor da propriedade order.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Integer }
                 *     
                 */
                public Integer getOrder() {
                    return order;
                }

                /**
                 * Define o valor da propriedade order.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Integer }
                 *     
                 */
                public void setOrder(Integer value) {
                    this.order = value;
                }

                /**
                 * Obtém o valor da propriedade getDigits.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Integer }
                 *     
                 */
                public Integer getLengthDigit() {
                    return lengthDigit;
                }

                /**
                 * Define o valor da propriedade getDigits.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Integer }
                 *     
                 */
                public void setLengthDigit(Integer value) {
                    this.lengthDigit = value;
                }

            }
            
            

        }
        
        //---------------------------------------------------------------------------------------------------
        /**
         * Define os paremetros para tocar o MDivulga
         * @author almir.oliveira
         *
         */
	        @XmlAccessorType(XmlAccessType.FIELD)
	        @XmlType(name = "", propOrder = {
	            "forwardEnabled",
	            "cfu",
	            "audioPosition",
	            "audioFilename"
	        })
	        public static class Forward{
	            protected Boolean forwardEnabled;
	            protected String cfu;
	            protected String audioPosition;
	            protected String audioFilename ;
				public Boolean getFowardEnabled() {
					return forwardEnabled;
				}
				public void setForwardEnabled(Boolean forwardEnabled) {
					this.forwardEnabled = forwardEnabled;
				}
				public String getCfu() {
					return cfu;
				}
				public void setCfu(String cfu) {
					this.cfu = cfu;
				}
				public String getAudioPosition() {
					return audioPosition;
				}
				public void setAudioPosition(String audioPosition) {
					this.audioPosition = audioPosition;
				}
				public String getAudioFileName() {
					return audioFilename;
				}
				public void setAudioFileName(String audioFilename) {
					this.audioFilename = audioFilename;
				}
	        }
	        //------------------------------------------------------------------------------------------------------------
        
        
    }

}
