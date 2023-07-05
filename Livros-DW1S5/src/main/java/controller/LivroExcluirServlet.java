package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import dao.LivroDAO;

/**
 * Servlet implementation class LivroExcluirServlet
 */
@WebServlet("/BookDeleteServlet")
public class LivroExcluirServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	LivroDAO ldao = new LivroDAO();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LivroExcluirServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String usuario = (String) request.getSession().getAttribute("usuario");
		if(usuario != null) {
			int id_livro = Integer.parseInt(request.getParameter("id_excluir"));
			
			try {
				System.out.println("Excluir");
				ldao.excluirLivro(id_livro);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/livro_excluida.jsp");
				dispatcher.forward(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/usuario_login.jsp");
			dispatcher.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
