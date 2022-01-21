package filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;


@WebFilter(urlPatterns = {"/principal/*"})
public class FilterAutenticacao implements Filter {
       
    
    public FilterAutenticacao() {
        super();
 
    }


	public void destroy() {
	
	}

	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();
		
		String usuarioLogado =  (String) session.getAttribute("usuario");
		String urlParaAutenticar = req.getServletPath();
		
		if(usuarioLogado == null &&
				!urlParaAutenticar.equalsIgnoreCase("/principal/ServletsLogin") ) {
			
			RequestDispatcher redirecionar = request.getRequestDispatcher("/index.jsp?url="+urlParaAutenticar);
			request.setAttribute("msg", "Por favor realiz o login!");
			redirecionar.forward(request, response);
			return;
		}else {
			chain.doFilter(request, response);
			
		}
				
		
	}


	public void init(FilterConfig fConfig) throws ServletException {

	}

}
