<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Transferencia</title>
</head>
<body>
	<h1>Transferencia</h1>
	<form action="Transferencia" method="POST">
		Cantidad:<input type="text" name="cantidad"><br>
		Cuenta destino:<input type="text" name="cuenta2"><br>
		<input type="submit" value="Transferir">
	</form>
	<br>
	<a href="menu.jsp">Volver al menu</a>
</body>
</html>