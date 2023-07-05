package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import model.Livro;
import dao.LivroDAO;

/**
 * Servlet implementation class LivroEditarServlet
 */
@WebServlet("/BookEditServlet")
public class LivroEditarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	LivroDAO ldao = new LivroDAO();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LivroEditarServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String usuario = (String) request.getSession().getAttribute("usuario");
		if(usuario != null) {
			int id_livro = Integer.parseInt(request.getParameter("id_livro"));
			ServletContext vc = getServletContext();
			vc.setAttribute("id_livro", id_livro);
			
			Livro livro = null;
			try {
				livro = ldao.buscarLivroEdicao(id_livro);
			}catch(Exception e) {
				e.printStackTrace();
			}
			
			request.setAttribute("titulo", livro.getTitulo());
			System.out.println("Get: " + livro.getTitulo());
			request.setAttribute("autor", livro.getAutor());
			request.setAttribute("genero", livro.getGenero());
			request.setAttribute("ano", livro.getAno());
			
			vc.setAttribute("livro", livro);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/livro_editar.jsp");
			dispatcher.forward(request, response);
		}else {
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
								
				ServletContext sc = getServletContext();
				int id_livro = (int) sc.getAttribute("id_livro");
				l.setId(id_livro);
				
				try {
					ldao.alterarLivro(l);
					RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/livro_editada.jsp");
					dispatcher.forward(request, response);
				}catch(ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
		}else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/usuario_login.jsp");
			dispatcher.forward(request, response);
		}
	}

}
