package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Criptografia;
import model.Usuario;

import java.io.IOException;

import dao.LivroDAO;
import dao.UsuarioDAO;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UsuarioDAO udao = new UsuarioDAO();
	LivroDAO ldao = new LivroDAO();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/usuario_login.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login = request.getParameter("login");
		//String password = request.getParameter("password");
		String password = Criptografia.criptografar(request.getParameter("password"));
		try {
			Usuario u = udao.buscarUsuario(login, password);
			if(u != null) {
				request.getSession().setAttribute("usuario", login);
				ServletContext sc = getServletContext();
				sc.setAttribute("login", login);
				sc.setAttribute("password", password);
				sc.setAttribute("usuario", u);
				
				request.setAttribute("lista_livros", ldao.getLivrosUsuario());
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/usuario_livros.jsp");
				dispatcher.forward(request, response);
			} else {
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/usuario_login_erro.jsp");
				dispatcher.forward(request, response);
			}
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}

}
