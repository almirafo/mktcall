package br.com.supportcomm.mktcall.system;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

/**
 * Filtro para controle de cache dos navegadores
 * 

 * 
 */
public class NoCacheFilter implements Filter
{
    private FilterConfig filterConfig;

    public void init(FilterConfig filterConfig)
    {
        this.setFilterConfig(filterConfig);
    }

    public void destroy()
    {
        this.setFilterConfig(null);
    }

    /**
     * O verdadeiro trabalho acontece em doFilter(). A referência ao objeto de
     * resposta é do tipo ServletResponse, portanto, precisamos convertê-lo para
     * HttpServletResponse
     */

    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain) throws IOException, ServletException
    {
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        /**
         * Então apenas definir os cabeçalhos apropriados ao invocar a chamda
         * aos métodos
         */
        httpResponse.setHeader("Cache-Control", "no-cache");
        httpResponse.setDateHeader("Expires", 0);
        httpResponse.setHeader("Pragma", "No-cache");
        chain.doFilter(request, response);
    }

    public void setFilterConfig(FilterConfig filterConfig)
    {
        this.filterConfig = filterConfig;
    }

    public FilterConfig getFilterConfig()
    {
        return filterConfig;
    }
}
