<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="jakarta.servlet.http.HttpSession" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cadastro de Livro</title>
<style><%@include file="/WEB-INF/view/estilos.css"%></style>
</head>
<body>

<header>
    <nav>
       <ul class="menu">
            <li class="borda_right">
                <a href = "/Livros-DW1S5/BookServlet">Cadastrar Novo Livro</a>
            </li>
            <li class="borda_right">
                <a href = "/Livros-DW1S5/BooksUserServlet">Livros Cadastrados</a>
            </li>
            <li class="borda_right">
                <a href="/Livros-DW1S5/LogoutServlet">Sair</a>
            </li>
        </ul>
    </nav>
</header>

	<fieldset>
		<legend>Cadastrar Livro</legend>
		<form action="<%=request.getContextPath()%>/BookServlet" method="post">
			<p>
				<span>Título:</span> <input type="text" name="titulo" required="required" />
			</p>
			<p>
				<span>Autor:</span> <input type="text" name="autor" />
			</p>
			<p>
				<span>Gênero:</span> <input type="text" name="genero" />
			</p><p>
				<span>Ano:</span> <input type="number" min="1900" max="2099" name="ano" />
			</p>
			<p>
				<input class = "botao" type="submit" value="Enviar" />
			</p>
		</form>
	</fieldset>
</body>
</html>