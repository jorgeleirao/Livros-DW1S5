<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="jakarta.servlet.http.HttpSession" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sucesso!</title>
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
	<br /><br /><br /><br /><br /><br /><br />
	<h1>Livro editado com sucesso!</h1>
</body>
</html>