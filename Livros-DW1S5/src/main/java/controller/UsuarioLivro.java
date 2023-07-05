package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Usuario;

import java.io.IOException;
import java.text.ParseException;

import dao.LivroDAO;

/**
 * Servlet implementation class UsuarioLivro
 */
@WebServlet("/BooksUserServlet")
public class UsuarioLivro extends HttpServlet {
	private static final long serialVersionUID = 1L;
	LivroDAO ldao = new LivroDAO();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UsuarioLivro() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String usuario = (String) request.getSession().getAttribute("usuario");
		if(usuario != null) {
			ServletContext sc = getServletContext();
			Usuario u = (Usuario) sc.getAttribute("usuario");
			try {
				ldao.buscarLivros();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			request.setAttribute("lista_livros", ldao.getLivrosUsuario());
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/usuario_livros.jsp");
			dispatcher.forward(request, response);
		} else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/usuario_login.jsp");
			dispatcher.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String usuario = (String) request.getSession().getAttribute("usuario");
		if(usuario != null) {
			ServletContext sc = getServletContext();
			Usuario u = (Usuario) sc.getAttribute("usuario");
			
			String tituloBuscar = (String) request.getParameter("titulo");
			String autor = request.getParameter("autor");
			String genero = request.getParameter("genero");
			try {
				if(tituloBuscar.equals("") && autor.equals("") && genero.equals("")) {
					try {
						ldao.buscarLivros();
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					request.setAttribute("lista_livros", ldao.getLivrosUsuario());
					RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/usuario_livros.jsp");
					dispatcher.forward(request, response);
				} else if(!tituloBuscar.isEmpty() && !autor.isEmpty() && !genero.isEmpty()){
					request.setAttribute("lista_livros", ldao.pesquisarLivro(tituloBuscar, autor, genero));
					RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/usuario_livros.jsp");
					dispatcher.forward(request, response);
				} else if (!tituloBuscar.isEmpty() && !autor.isEmpty()){
					request.setAttribute("lista_livros", ldao.pesquisarLivro(tituloBuscar, autor));
					RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/usuario_livros.jsp");
					dispatcher.forward(request, response);
				} else if (!genero.isEmpty() && !autor.isEmpty()) {
					request.setAttribute("lista_livros", ldao.pesquisarLivro(autor, genero));
					RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/usuario_livros.jsp");
					dispatcher.forward(request, response);
				} else if(!tituloBuscar.isEmpty()) {
					request.setAttribute("lista_livros", ldao.pesquisarLivro(tituloBuscar));
					RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/usuario_livros.jsp");
					dispatcher.forward(request, response);
				} else if (!autor.isEmpty()) {
					request.setAttribute("lista_livros", ldao.pesquisarLivro(autor, "autor"));
					RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/usuario_livros.jsp");
					dispatcher.forward(request, response);
				} else if (!genero.isEmpty()) {
					request.setAttribute("lista_livros", ldao.pesquisarLivro(genero, "genero"));
					RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/usuario_livros.jsp");
					dispatcher.forward(request, response);
					
				}
				
				
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/usuario_login.jsp");
			dispatcher.forward(request, response);
		}
	}

}
