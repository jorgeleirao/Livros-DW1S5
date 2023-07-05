<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="jakarta.servlet.http.HttpSession" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Livros Cadastradas</title>
<style><%@include file="/WEB-INF/view/estilos.css"%></style>
</head>
<body>

<header>
    <nav>
        <ul class="menu">
            <li class="borda_right">
                <a href = "/Livros-DW1S5/BookServlet">Novo cadastro de livro</a>
            </li>
            <li class="borda_right">
                <a href="/Livros-DW1S5/LogoutServlet">Sair</a>
            </li>
        </ul>
    </nav>
</header>

<div class = "tabela">

<fieldset>
	<legend>Pesquisar Livro</legend>
	<form action="<%=request.getContextPath()%>/BooksUserServlet" method="post">
		<p>
			<span>Título:</span> <input type="text" name="titulo" />
		</p>
		<p>
			<span>Autor:</span> <input type="text" name="autor" />
		</p>
		<p>
			<span>Gênero:</span> <input type="text" name="genero" />
		</p>
		<p>
			<input name = "buscar" class = "botao" type="submit" value="Buscar Livro" />
		</p>
	</form>
</fieldset>

<h1>Livros</h1>

<br  /><br  />

<form action="<%=request.getContextPath()%>/BooksUserServlet" method="post">
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.Livro" %>
<table>
	<tr>
		<th>Título</th>
		<th>Autor</th>
		<th>Gênero</th>
		<th>Ano</th>
		<th>Alterar Livro</th>
		<th>Excluir Livro</th>
	</tr>
	<c:forEach items="${requestScope.lista_livros}" var="c">
		<tr>
			<td>
				${c.titulo}
			</td>
			<td>
				${c.autor}
			</td>
			<td>
				${c.genero}
			</td>
			<td>
				${c.ano}
			</td>
			<td>
				<a class = "botao_tabela" href="/Livros-DW1S5/BookEditServlet?id_livro=${c.id}">Editar</a>
			</td>
			<td>
				<input type="hidden" name="id_excluir" value="${c.id}" />
				<input class = "botao_tabela" type="submit" value="Excluir" />
			</td>
		</tr>
	</c:forEach>
</table>
</form>
</div>
</body>
</html>