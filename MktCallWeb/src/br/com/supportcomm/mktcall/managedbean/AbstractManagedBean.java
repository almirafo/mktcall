package br.com.supportcomm.mktcall.managedbean;

import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlCommandLink;
import javax.faces.event.ComponentSystemEvent;

import org.primefaces.component.column.Column;
import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.commandlink.CommandLink;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.component.spinner.Spinner;

import br.com.supportcomm.mktcall.entity.UserAccess;
import br.com.supportcomm.mktcall.util.Constantes;
import br.com.supportcomm.mktcall.util.JSFUtil;

/**
 * 
 * 
 */
public class AbstractManagedBean extends JSFUtil
{
    private UserAccess userAccess;
    private boolean sucesso;
    private boolean permissao;
   
    public boolean isPermissao()
    {
        return permissao;
    }

    public void setPermissao(boolean permissao)
    {
        this.permissao = permissao;
    }

    public boolean isSucesso()
    {
        return sucesso;
    }

    public void setSucesso(boolean sucesso)
    {
        this.sucesso = sucesso;
    }

    public UserAccess getUserAccess()
    {
        return userAccess;
    }

    public void setUserAccess(UserAccess userAccess)
    {
        this.userAccess = userAccess;
    }

    public AbstractManagedBean()
    {
        this.userAccess = super.getUsuarioAutenticado();
    }

    public int getUserType()
    {
        return Integer.parseInt(this.userAccess.getUserType().getCodeUserType());
    }

    /**
     * 
     * @param event m�todo que libera o acesso aos componentes de tela, atrav�s
     *            do id do componente que est� sendo carregado e das permiss�es
     *            do usu�rio logado
     */
    public void permiteAcesso(ComponentSystemEvent event)
    {
        if (getUserType() != Constantes.USUARIO_ADMINISTRADOR.getValor())
        {
            UIComponent uiComponent = event.getComponent();
            for (UIComponent ui : uiComponent.getChildren())
            {
                if (ui instanceof CommandLink || ui instanceof HtmlCommandLink)
                {
                    if (ui.getId().equals("sair") || ui.getId().startsWith("listar"))
                    {
                        ui.setRendered(true);
                    }
                    else if (ui.getId().equals("valorTarifa"))
                    {
                        Spinner spinner = (Spinner) ui;
                        spinner.setReadonly(!permitirAcesso(ui.getId()));
                    }
                    else
                    {
                        ui.setRendered(permitirAcesso(ui.getId()));
                    }
                }
                if (ui instanceof DataTable)
                {
                    Column column = (Column) ui.findComponent("acoes");
                    for (UIComponent component : column.getChildren())
                    {
                        if (component instanceof CommandButton)
                        {
                            component.setRendered(permitirAcesso(component.getId()));
                        }
                    }
                }
            }
        }
    }

    /**
     * M�todo que gera o corpo do email para o envio de senha e login do usu�rio
     * cadastrado
     * 
     * @param login
     * @param senha
     * @return String
     */
    public String getEmailCadastroUsuario(String login, String senha)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("<html><body><br>");
        sb.append("Usuário: " + login + "<br>");
        sb.append("Senha: " + senha + "<br>");
        sb.append("Para maiores informações entrar em contato com administração: sacvoz@supportcomm.com.br");
        sb.append("</body></html>");
        return sb.toString();
    }

    /**
     * m�todo que desabilita componente de acordo com o tipo do usuario logado
     * 
     * @return boolean
     */
    public boolean getDesabilitarComponente()
    {
        if (this.getUserType() == Constantes.USUARIO_ADMINISTRADOR.getValor())
        {
            return false;
        }
        return true;
    }

}
