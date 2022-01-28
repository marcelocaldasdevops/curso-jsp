package servlets;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import dao.DAOUsuarioRepository;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ModelLogin;


@WebServlet("/ServLetUsuarioController")
public class ServLetUsuarioController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   DAOUsuarioRepository daoUsuarioRepository = new DAOUsuarioRepository();
	
    public ServLetUsuarioController() {
        super();
     
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			
		
		String acao = request.getParameter("acao");
		
		if(acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("deletar")) {
			String isUser = request.getParameter("id");
			daoUsuarioRepository.deletarUser(isUser);
			
			request.setAttribute("msg", "Excluido com sucesso!");
			
		}else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("deletarajax")) {
			String isUser = request.getParameter("id");
			daoUsuarioRepository.deletarUser(isUser);
			
			response.getWriter().write("Excluido com sucesso!");
		}else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("buscaUserajax")) {
			String nomeBusca = request.getParameter("nomeBusca");
			List<ModelLogin> dadosJsonUsaer = daoUsuarioRepository.consultaUsuariList(nomeBusca);
			
			ObjectMapper mapper = new ObjectMapper();
			
			String json = mapper.writeValueAsString(dadosJsonUsaer);
			System.out.println(json);
			response.getWriter().write(json);
		}else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("buscaEditar")) {
			String id = request.getParameter("id");
			ModelLogin usuario = daoUsuarioRepository.consultaUsuarioId(id);
			
			request.setAttribute("msg", "Usuário em edição");
			request.setAttribute("modolLogin", usuario);
			request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);
		}else {
			request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);			
		}
		
		} catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher redirecionar = request.getRequestDispatcher("erro.jsp");
			request.setAttribute("msg", e.getMessage());
			redirecionar.forward(request, response);
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
		String msg = "";	
		
		String id = request.getParameter("id");
		String nome = request.getParameter("nome");
		String email = request.getParameter("email");
		String login = request.getParameter("login");
		String senha = request.getParameter("senha");
		
		ModelLogin modelLogin = new ModelLogin();
		
		modelLogin.setId(id != null && !id.isEmpty() ? Long.parseLong(id) : null);
		modelLogin.setNome(nome);
		modelLogin.setEmail(email);
		modelLogin.setLogin(login);
		modelLogin.setSenha(senha);
		
		if(daoUsuarioRepository.validarLogin(modelLogin.getLogin()) && modelLogin.getId() == null) {
			msg = "Já existe usuário como mesmo login, informe outro login";
		}else {
			if(modelLogin.isNovo()) {
				msg = "Gravado como sucesso!";
			}else {
				msg = "Atualização feita com sucesso!";				
			}
			modelLogin = daoUsuarioRepository.gravarUsuario(modelLogin);	
		}
		
		
		request.setAttribute("msg", msg);
		request.setAttribute("modolLogin", modelLogin);
		request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher redirecionar = request.getRequestDispatcher("erro.jsp");
			request.setAttribute("msg", e.getMessage());
			redirecionar.forward(request, response);
		}
	}

}
