<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Messagens de Erros</title>
</head>
<body>
<h1>Por favor entra em contato com a equipe</h1>
<%
out.print(request.getAttribute("msg"));
%>>

</body>
</html>