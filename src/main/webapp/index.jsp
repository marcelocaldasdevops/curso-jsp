<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- Bootstrap CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">
<link href='https://fonts.googleapis.com/css?family=Ubuntu:500'
	rel='stylesheet' type='text/css'>
<link rel="stylesheet" type="text/css" href="style.css">
<title>Curso de JSP</title>
</head>

<body>

	<h5>Bem vindo a JSP</h5>

		<form action="<%= request.getContextPath() %>/ServletsLogin" method="post"
			class="row g-3 needs-validation" novalidate>

			<input type="hidden" value="<%=request.getParameter("url")%>"
				name="url">
			
			<div class="mb-3">
				<label class="form-label">Login:</label> <input class="form-control"
					type="text" name="login" placeholder="Login" required="required">
				<div class="invalid-feedback">Obrigadorio.</div>
				<div class="valid-feedback">Ok.</div>
			</div>

			<div class="mb-3">
				<label class="form-label">Senha:</label> <input class="form-control"
					type="password" name="senha" placeholder="Senha"
					required="required">
				<div class="invalid-feedback">Obrigadorio.</div>
				<div class="valid-feedback">Ok.</div>
			</div>
			<input type="submit" value="Acessar" class="btn btn-primary">
		</form>
	<div class="alert " role="alert">
	<h5 class="msg">${msg}</h5>
	</div>




	<script src="validacao.js"></script>

	<!-- Option 1: Bootstrap Bundle with Popper -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
		crossorigin="anonymous">
	</script>
</body>

</html>