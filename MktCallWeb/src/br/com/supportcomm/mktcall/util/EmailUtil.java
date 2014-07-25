package br.com.supportcomm.mktcall.util;

import java.util.Date;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.SimpleEmail;

import br.com.supportcomm.mktcall.util.JSFUtil;

/**
 * Classe de utilização para envio de email utilizando a biblioteca
 * apache-commons-email
 * 
 * 
 */
public class EmailUtil
{
    /**
     * Carrega valores que estão no arquivo de propriedades - email.properties
     */
    private static String servidor = JSFUtil.getEmailMessage("servidor");
    private static String from = JSFUtil.getEmailMessage("remetenteServidor");
    private static String fromName = JSFUtil.getEmailMessage("chamadasPatrocinadas");

    /**
     * Envio de email comum
     * 
     * @param de
     * @param to
     * @param nameTo
     * @param subject
     * @param messageBody
     * @throws EmailException
     */
    public static void sendMail(String de,
                                String to,
                                String nameTo,
                                String subject,
                                String messageBody) throws EmailException

    {
        SimpleEmail email = new SimpleEmail();

        email.setHostName(servidor); // o servidor SMTP para envio do e-mail

        email.addTo(to, nameTo); // destinatário

        email.setFrom(de == null ? from : de, fromName); // remetente

        email.setSubject(subject); // assunto do e-mail

        email.setMsg("teste"); // conteudo do e-mail

        email.send(); // envia o e-mail

    }

    /**
     * Envio de email com códigos html no corpo da mensagem
     * 
     * @param de
     * @param to
     * @param nameTo
     * @param subject
     * @param messageBody
     * @throws EmailException
     */
    public static void sendHTMLMail(String de,
                                    String to,
                                    String nameTo,
                                    String subject,
                                    String messageBody) throws EmailException
    {

        HtmlEmail email = new HtmlEmail();

        email.setHostName(servidor); // o servidor SMTP
                                     // para envio do
                                     // e-mail

        email.addTo(to, nameTo); // destinatário

        email.setFrom(de == null ? from : de, fromName); // remetente
        email.setSentDate(new Date());
        email.setSubject(subject); // assunto do e-mail

        // configura a mensagem para o formato HTML

        email.setHtmlMsg(messageBody);

        // configure uma mensagem alternativa caso o servidor não suporte HTML

        email.setTextMsg(JSFUtil.getEmailMessage("erroHTMl"));

        email.send(); // envia o e-mail

    }
}