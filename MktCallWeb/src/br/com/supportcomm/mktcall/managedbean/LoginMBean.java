package br.com.supportcomm.mktcall.managedbean;

import java.util.Date;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.primefaces.event.CloseEvent;

import br.com.supportcomm.mktcall.entity.UserAccess;
import br.com.supportcomm.mktcall.service.user.UserService;
import br.com.supportcomm.mktcall.util.Constantes;

/**
 * Classe backingbean de login
 * 
 * 
 * 
 */
@ManagedBean(name="loginMBean")
public class LoginMBean extends AbstractManagedBean
{
    private HttpSession session;
    private String login, senha;
    
    Logger logger = Logger.getLogger(LoginMBean.class);
    
    @EJB
    private UserService userService;

    public LoginMBean()
    {
        super();
    }
    
    public boolean getIsAdmin(){
    
    	FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
    	UserAccess user = (UserAccess) request.getSession().getAttribute("usuarioAutenticado");
    	
    	if(user.getUserType().getCodeUserType().equals("1")) return true;
    	
    	return false;
    }
    
    public boolean getIsAnunciante(){
    	
    	FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
		UserAccess user = (UserAccess) request.getSession().getAttribute("usuarioAutenticado");
		
		if(user.getUserType().getCodeUserType().equals("2")) return true;
		
    	return false;
    }
    
    public boolean getIsAgencia(){
    	
    	FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
		UserAccess user = (UserAccess) request.getSession().getAttribute("usuarioAutenticado");
		
		if(user.getUserType().getCodeUserType().equals("3")) return true;
		
    	return false;
    }
    
    public String autenticarUsuario()
    {
        try
        {
            UserAccess usuarioAutenticado = userService.getUserAccessLoginPass(this.getLogin(), this.getSenha()).get(0);
            if (super.isNullOrBlank(usuarioAutenticado))
            {
                super.addError(super.getMessage("usuarioNaoEncontrado"));
            }
            else if (usuarioAutenticado.getStatus() == Constantes.STATUS_INATIVO.getValor())
            {
                super.addError(super.getMessage("usuarioInativo"));
            }
            else
            {
                session = super.getSessionAtiva(false);
                session.setAttribute("usuarioAutenticado", usuarioAutenticado);
                session.setAttribute("nomeLogin", usuarioAutenticado.getLogin());
                if (usuarioAutenticado.getUserType()
                                      .getCodeUserType()
                                      .equals(Constantes.USUARIO_ADMINISTRADOR))
                {
                    return "campanha";
                }
                else
                {
                    return "campanha";
                }
            }
        }
        catch (Exception e)
        {
            super.addError(super.getMessage("problemaConexao"));
            logger.error("Método autenticarUsuario - message: " + e.getMessage());
            logger.error("Método autenticarUsuario - cause: " + e.getCause());
        }
		return "";
    }

    /**
     * M�todo respons�vel por remover o usu�rio da sess�o quando o mesmo realiza
     * logout. O bot�o sair chama este m�todo.
     */
    public String encerraSessao()
    {
        try
        {
        	FacesContext context = FacesContext.getCurrentInstance();
    		HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
    		request.getSession().invalidate();
    		
            return "inicio";
        }
        catch (Exception e)
        {
            logger.error("M�todo encerraSessao - message: " + e.getMessage());
            logger.error("M�todo encerraSessao - cause: " + e.getCause());
        }
        
        return "inicio";
    }

    public String teste() throws Exception
    {
    	return "seguranca";
    }

    /**
     * M�todo que executa o encerrar sess�o
     * 
     * @param closeEvent
     */
    public void sessaoExpirada(CloseEvent closeEvent)
    {
        this.encerraSessao();
    }

    /**
     * Mostra a data do dia
     * 
     * @return
     */
    public String getDataAtual()
    {
        return super.getDataExtenso(new Date());
    }

    public String getLogin()
    {
        return login;
    }

    public void setLogin(String login)
    {
        this.login = login;
    }

    public String getSenha()
    {
        return senha;
    }

    public void setSenha(String senha)
    {
        this.senha = senha;
    }

}
