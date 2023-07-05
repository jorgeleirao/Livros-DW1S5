package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Livro;

public class LivroDAO {
	
	String serverName="localhost";
	String dataBasePort="3306";
	String mydatabase="biblioteca";
	String url="jdbc:mysql://" + serverName + ":" + dataBasePort + "/" + mydatabase;
	String usernameb="root";
	String passwordb="root";
	
	ArrayList<Livro> livrosUsuario = new ArrayList<Livro>();
	
	public ArrayList<Livro> getLivrosUsuario() {
		return livrosUsuario;
	}

	public int cadastrarLivro(Livro l) throws ClassNotFoundException{
        String INSERT_USERS_SQL = "INSERT INTO livros" + 
                "(titulo, autor, genero, ano) VALUES " +
                "(?,?,?,?);";
        
        int result = 0;
        
        Class.forName("com.mysql.jdbc.Driver");
        
        try (Connection connection = DriverManager.getConnection(url,usernameb,passwordb);
        	PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)){
        	preparedStatement.setString(1, l.getTitulo());
        	preparedStatement.setString(2, l.getAutor());
        	preparedStatement.setString(3, l.getGenero());
        	preparedStatement.setInt(4, l.getAno());
        	System.out.println(preparedStatement);
        	
        	result = preparedStatement.executeUpdate();
        	ResultSet rs = preparedStatement.getGeneratedKeys();
        	if (rs.next()) {
        	    l.setId(rs.getInt(1));
        	}
        	
        }catch(SQLException e) {
        	e.printStackTrace();
        }
        
        return result;
	}
	
	public void buscarLivros() throws ClassNotFoundException {
		livrosUsuario.clear();
		String SELECT_USERS_SQL = "SELECT * FROM livros;";
        
        Class.forName("com.mysql.jdbc.Driver");
        
        Livro l = null;
        
        try (Connection connection = DriverManager.getConnection(url,usernameb,passwordb);
        	PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USERS_SQL)){
        	System.out.println(preparedStatement);
        	int flag = 0;
        	ResultSet rs = preparedStatement.executeQuery();
        	while(rs.next()) {
        		l = new Livro();
        		l.setId(rs.getInt("id"));
        		l.setTitulo(rs.getString("titulo"));
        		l.setAutor(rs.getString("autor"));
        		l.setGenero(rs.getString("genero"));
        		l.setAno(rs.getInt("ano"));
        		for(Livro tt : livrosUsuario) {
        			if(tt.getId() == l.getId()) {
        				flag = 1;
        			}
        		}
        		if(flag == 0) {
        			livrosUsuario.add(l);
        		}
        	}
        }catch(SQLException ex) {
        	ex.printStackTrace();
        }
	}
	
	public Livro buscarLivroEdicao(int id_livro) throws ClassNotFoundException {
		String SELECT_USERS_SQL = "SELECT * FROM livros WHERE id = ?";
        
        Class.forName("com.mysql.jdbc.Driver");
        
        Livro l = null;
        
        try (Connection connection = DriverManager.getConnection(url,usernameb,passwordb);
        	PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USERS_SQL)){
        	preparedStatement.setInt(1, id_livro);
        	System.out.println(preparedStatement);
        	
        	ResultSet rs = preparedStatement.executeQuery();
        	if(rs.next()) {
        		l = new Livro();
        		l.setId(rs.getInt("id"));
        		System.out.println(rs.getInt("id"));
        		l.setTitulo(rs.getString("titulo"));
        		System.out.println(rs.getString("titulo"));
        		l.setAutor(rs.getString("autor"));
        		System.out.println(rs.getString("autor"));
        		l.setGenero(rs.getString("genero"));
        		System.out.println(rs.getString("genero"));
        		l.setAno(rs.getInt("ano"));
        		System.out.println(rs.getString("ano"));
        	}
        }catch(SQLException ex) {
        	ex.printStackTrace();
        }
        
        return l;
	}
	
	public ArrayList<Livro> pesquisarLivro(String search) throws ClassNotFoundException {
		ArrayList<Livro> livrosEncontradas = new ArrayList<Livro>();
		String SELECT_USERS_SQL = "SELECT * FROM livros WHERE lower(titulo) like lower(concat(?, '%')) order by titulo;";
        
        Class.forName("com.mysql.jdbc.Driver");
        
        Livro l = null;
        
        try (Connection connection = DriverManager.getConnection(url,usernameb,passwordb);
        	PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USERS_SQL)){
        	preparedStatement.setString(1, search);
        	System.out.println(preparedStatement);
        	
        	ResultSet rs = preparedStatement.executeQuery();
        	while(rs.next()) {
        		l = new Livro();
        		l.setId(rs.getInt("id"));
        		l.setTitulo(rs.getString("titulo"));
        		l.setAutor(rs.getString("autor"));
        		l.setGenero(rs.getString("genero"));
        		l.setAno(rs.getInt("ano"));
        		System.out.println(rs.getString("titulo"));
        		livrosEncontradas.add(l);
        	}
        }catch(SQLException ex) {
        	ex.printStackTrace();
        }
        return livrosEncontradas;
	}
	
	public ArrayList<Livro> pesquisarLivro(String search, String search2) throws ClassNotFoundException {
		ArrayList<Livro> livrosEncontrados = new ArrayList<Livro>();
		String SELECT_USERS_SQL = "SELECT * FROM livros WHERE lower(titulo) like lower(concat(?, '%')) AND lower(autor) like lower(concat(?, '%')) order by titulo";
        
        Class.forName("com.mysql.jdbc.Driver");
        
        Livro l = null;
        
        try (Connection connection = DriverManager.getConnection(url,usernameb,passwordb);
        	PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USERS_SQL)){
        	preparedStatement.setString(1, search);
        	preparedStatement.setString(2, search2);
        	System.out.println(preparedStatement);
        	
        	ResultSet rs = preparedStatement.executeQuery();
        	while(rs.next()) {
        		l = new Livro();
        		l.setId(rs.getInt("id"));
        		l.setTitulo(rs.getString("titulo"));
        		l.setAutor(rs.getString("autor"));
        		l.setGenero(rs.getString("genero"));
        		l.setAno(rs.getInt("ano"));
        		livrosEncontrados.add(l);
        	}
        }catch(SQLException ex) {
        	ex.printStackTrace();
        }
        return livrosEncontrados;
	}
	
	public ArrayList<Livro> pesquisarLivro(String titulo, String autor, String genero) throws ClassNotFoundException {
		ArrayList<Livro> livrosEncontrados = new ArrayList<Livro>();
		String SELECT_USERS_SQL = "SELECT * FROM livros WHERE lower(titulo) like lower(concat(?, '%')) AND lower(autor) like lower(concat(?, '%')) AND lower(genero) like lower(concat(?, '%')) order by titulo";
        
        Class.forName("com.mysql.jdbc.Driver");
        
        Livro l = null;
        
        try (Connection connection = DriverManager.getConnection(url,usernameb,passwordb);
        	PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USERS_SQL)){
        	preparedStatement.setString(1, titulo);
        	preparedStatement.setString(2, autor);
        	preparedStatement.setString(3, genero);
        	System.out.println(preparedStatement);
        	
        	ResultSet rs = preparedStatement.executeQuery();
        	while(rs.next()) {
        		l = new Livro();
        		l.setId(rs.getInt("id"));
        		l.setTitulo(rs.getString("titulo"));
        		l.setAutor(rs.getString("autor"));
        		l.setGenero(rs.getString("genero"));
        		l.setAno(rs.getInt("ano"));
        		livrosEncontrados.add(l);
        	}
        }catch(SQLException ex) {
        	ex.printStackTrace();
        }
        return livrosEncontrados;
	}
	
	public void alterarLivro(Livro l) throws ClassNotFoundException {
		 String UPDATE_SQL = "UPDATE livros SET titulo = ?, autor = ?, genero = ?, ano = ? WHERE id = ?";
	        
	        Class.forName("com.mysql.jdbc.Driver");
	        
	        try (Connection connection = DriverManager.getConnection(url,usernameb,passwordb);
	        	PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SQL)){
	        	preparedStatement.setString(1, l.getTitulo());
	        	preparedStatement.setString(2, l.getAutor());
	        	preparedStatement.setString(3, l.getGenero());
	        	preparedStatement.setInt(4, l.getAno());
	        	preparedStatement.setInt(5, l.getId());
	        	System.out.println(preparedStatement);
	        	
	        	preparedStatement.executeUpdate();
	        	
	        }catch(SQLException e) {
	        	e.printStackTrace();
	        }
	}
	
	public void excluirLivro(int id) throws ClassNotFoundException{
        String DELETE_SQL = "DELETE FROM livros WHERE id = ?";
        
        Class.forName("com.mysql.jdbc.Driver");
        
        try (Connection connection = DriverManager.getConnection(url,usernameb,passwordb);
        	PreparedStatement preparedStatement = connection.prepareStatement(DELETE_SQL)){
        	preparedStatement.setInt(1, id);
        	System.out.println(preparedStatement);
        	
        	preparedStatement.executeUpdate();
        	
        }catch(SQLException e) {
        	e.printStackTrace();
        }
        
	}
	
}
