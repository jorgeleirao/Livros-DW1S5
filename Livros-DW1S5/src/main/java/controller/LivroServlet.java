package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Livro;
import java.io.IOException;
import dao.LivroDAO;

/**
 * Servlet implementation class LivroServlet
 */
@WebServlet("/BookServlet")
public class LivroServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    LivroDAO ldao = new LivroDAO();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LivroServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String usuario = (String) request.getSession().getAttribute("usuario");
		if(usuario != null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/livro_cadastro.jsp");
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
			String titulo = request.getParameter("titulo");
			String autor = request.getParameter("autor");
			String genero = request.getParameter("genero");
			int ano = Integer.parseInt(request.getParameter("ano"));
			if((titulo.isEmpty() || titulo.equals("") || titulo.equals(" ") || titulo.isBlank())){
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/livro_cadastro_falha_titulo.jsp");
				dispatcher.forward(request, response);
			} else {
				Livro l = new Livro();
				l.setTitulo(titulo);
				l.setAutor(autor);
				l.setGenero(genero);
				l.setAno(ano);
	
				try {
					ldao.cadastrarLivro(l);
				}catch(ClassNotFoundException e) {
					e.printStackTrace();
				}
				
				request.setAttribute("lista_livros", ldao.getLivrosUsuario());
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/usuario_livros.jsp");
				dispatcher.forward(request, response);
			}
		}else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/usuario_login.jsp");
			dispatcher.forward(request, response);
		}
	}

}
