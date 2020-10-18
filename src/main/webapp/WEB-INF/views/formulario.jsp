<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Câmbio Fácil</title>
</head>
<body>
		
		<h1>Conversor Cambial</h1>
		
		<form:form modelAttribute="moeda" method="POST">
		<p> Valor em dólares ($): <input type="text" name="valorDolares"> </p>
		<input type="submit" value="Enviar">
		<p> ${mensagem} </p>
		
		</form:form>
		
</body>
</html>