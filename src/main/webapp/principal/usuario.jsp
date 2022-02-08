<%@page import="model.ModelLogin"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">

<jsp:include page="head.jsp"></jsp:include>

<body>
	<!-- Pre-loader start -->
	<jsp:include page="theme-loader.jsp"></jsp:include>
	<!-- Pre-loader end -->
	<div id="pcoded" class="pcoded">
		<div class="pcoded-overlay-box"></div>
		<div class="pcoded-container navbar-wrapper">

			<jsp:include page="navbar.jsp"></jsp:include>

			<div class="pcoded-main-container">
				<div class="pcoded-wrapper">

					<jsp:include page="navbarmainmenu.jsp"></jsp:include>

					<div class="pcoded-content">
						<!-- Page-header start -->

						<jsp:include page="page-header.jsp"></jsp:include>

						<!-- Page-header end -->
						<div class="pcoded-inner-content">
							<!-- Main-body start -->
							<div class="main-body">
								<div class="page-wrapper">
									<!-- Page-body start -->
									<div class="page-body">

										<!-- task, page, download counter  start -->

										<div class="row">
											<div class="col-sm-12">
												<!-- Basic Form Inputs card start -->
												<div class="card">

													<div class="card-block">
														<h4 class="sub-title">Cad. Usuário</h4>

														<form class="form-material" enctype="multipart/form-data"
															action="<%=request.getContextPath()%>/ServLetUsuarioController"
															method="post" id="formUser">

															<input type="hidden" name="acao" id="acao" value="">

															<div class="form-group form-default form-static-label">
																<input type="text" name="id" id="id"
																	class="form-control" readonly="readonly"
																	value="${modolLogin.id}"> <span
																	class="form-bar"></span> <label class="float-label">ID:</label>
															</div>
															<div class="form-group form-default input-group mb-4">
																<div class="input-group-prepend">
																<img alt="Imagem User" id="fotoembase64" src="" width="70px">
																</div>
																<input type="file" id="filefoto" name="filefoto" accept="image/*" onchange="visualizarImg('fotoembase64', 'filefoto')" class="form-control-file" style="margin-top:15px; margin-left:5px;">
															
															</div>
															<div class="form-group form-default form-static-label">
																<input type="text" name="nome" id="nome"
																	class="form-control" required=""
																	value="${modolLogin.nome}"> <span
																	class="form-bar"></span> <label class="float-label">Nome:</label>
															</div>
															<div class="form-group form-default form-static-label">
																<input type="email" name="email" id="email"
																	class="form-control" required="" autocapitalize="off"
																	value="${modolLogin.email}"> <span
																	class="form-bar"></span> <label class="float-label">E-mail:</label>
															</div>
															<div class="form-group form-default form-static-label">
																<input type="text" name="login" id="login"
																	class="form-control" required=""
																	value="${modolLogin.login}"> <span
																	class="form-bar"></span> <label class="float-label">Login:</label>
															</div>
															<div class="form-group form-default form-static-label">
																<input type="radio" name="sexo" value="MASCULINO" checked="checked"
																	<%ModelLogin modelLogin = (ModelLogin) request.getAttribute("modolLogin"); 
																	if(modelLogin != null && modelLogin.getSexo().equals("MASCULINO")){
																	out.print(" ");
																	out.print("checked=\"checked\"");
																	out.print(" ");
																}
																%>
																>
																<span></span> <label >Masculino:</label>
																<input type="radio" name="sexo" value="FEMININO"
																	<%modelLogin = (ModelLogin) request.getAttribute("modolLogin"); 
																	if(modelLogin != null && modelLogin.getSexo().equals("FEMININO")){
																	out.print(" ");
																	out.print("checked=\"checked\"");
																	out.print(" ");
																}
																%>
																>
																<span></span> <label >Feminino:</label>
															</div>
															<div class="form-group form-default form-static-label">
															<select  class="form-control" aria-label="Default select example" name="perfil" >
																<option  disabled>[Selecio o perfil]</option>
																<option value="ADMIN"
																<% modelLogin = (ModelLogin) request.getAttribute("modolLogin"); 
																if(modelLogin != null && modelLogin.getPerfil().equals("ADMIN")){
																	out.print(" ");
																	out.print("selected=\"selected\"");
																	out.print(" ");
																}
																%>
																>Admin</option>
																<option value="SECRETARIA"
																	<%modelLogin = (ModelLogin) request.getAttribute("modolLogin"); 
																if(modelLogin != null && modelLogin.getPerfil().equals("SECRETARIA")){
																	out.print(" ");
																	out.print("selected=\"selected\"");
																	out.print(" ");
																}
																%>
																>Secretária</option>
																<option value="AUXILIAR"
																	<% modelLogin = (ModelLogin) request.getAttribute("modolLogin"); 
																if(modelLogin != null && modelLogin.getPerfil().equals("AUXILIAR")){
																	out.print(" ");
																	out.print("selected=\"selected\"");
																	out.print(" ");
																}
																%>
																>Auxiliar</option>
															  </select>
															  <span
															  class="form-bar"></span> <label class="float-label">Perfil:</label>
															</div>
															<div class="form-group form-default form-static-label">
																<input type="password" name="senha" id="senha"
																	class="form-control" required="" autocapitalize="off"
																	value="${modolLogin.senha}"> <span
																	class="form-bar"></span> <label class="float-label">Senha:</label>
															</div>

															<button type="button"
																class="btn btn-primary waves-effect waves-light"
																onclick="limpaForm()">Novo</button>
															<button class="btn btn-success waves-effect waves-light">Salvar</button>
															<button type="button"
																class="btn btn-info waves-effect waves-light"
																onclick="criarDeleteAjax()">Excluir</button>
															<button type="button" class="btn btn-secondary"
																data-toggle="modal" data-target="#exampleModalUser">Pesquisar
															</button>
														</form>
													</div>
												</div>
											</div>
											<span id="msg">${msg}</span>
										
										</div>
										<div style="height: 300px; overflow: scroll;">
											<table class="table" id="tabelaresultadoview">
												<thead>
													<tr>
														<th scope="col">ID</th>
														<th scope="col">Nome</th>
														<th scope="col">Ver</th>

													</tr>
												</thead>
												<tbody>
													<c:forEach items='${modelLogins}' var='ml'>
														<tr>
														<td><c:out value='${ml.id}'></c:out></td>
														<td><c:out value='${ml.nome}'></c:out></td>
														<td><a class="btn btn-success" href="<%= request.getContextPath() %>/ServLetUsuarioController?acao=buscaEditar&id=${ml.id}">Ver</a></td>
														</tr>
													</c:forEach>
												</tbody>
											</table>
											</div>
										<!--  project and team member end -->

									</div>
									<!-- Page-body end -->
								</div>
								<div id="styleSelector"></div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- Modal -->
	<div class="modal fade" id="exampleModalUser" tabindex="-1"
		role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Pesquisar
						usuário</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<div class="input-group mb-3">
						<input type="text" class="form-control" placeholder="Nome"
							id="nomeBusca" aria-label="nome" aria-describedby="button-addon2">
						<div class="input-group-append">
							<button class="btn btn-primary" type="button" id="button-addon2"
								onclick="nomeBusca()">Busca</button>
						</div>
					</div>
					<div style="height: 300px; overflow: scroll;">
						<table class="table" id="tabelaresultado">
							<thead>
								<tr>
									<th scope="col">ID</th>
									<th scope="col">Nome</th>
									<th scope="col">Ver</th>

								</tr>
							</thead>
							<tbody>

							</tbody>
						</table>
					</div>
					  <span id="total"></span>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">Sair</button>

				</div>
			</div>
		</div>
	</div>

	<jsp:include page="javascriptfile.jsp"></jsp:include>
	<script type="text/javascript">

		function visualizarImg(fotoembase64, filefoto) {
			var preview = document.getElementById(fotoembase64);
			var fileUser = document.getElementById(filefoto).files[0];
			var reader = new FileReader();

			reader.onloadend = function(){
				preview.src = reader.result;
			};
			if(fileUser){
				reader.readAsDataURL(fileUser);
			}else{
				preview.src = '';
			}
		}

		function verEditar(id){
			var urlAction = document.getElementById('formUser').action;

			window.location.href = urlAction +'?acao=buscaEditar&id='+id;
		}

		function nomeBusca(){
			var nomeBusca = document.getElementById("nomeBusca").value;
			if(nomeBusca != null && nomeBusca != '' && nomeBusca.trim() != ''){

				var urlAction = document.getElementById('formUser').action;
				$.ajax({
					method: "get",
					url: urlAction,
					data: "nomeBusca=" + nomeBusca + "&acao=buscaUserajax",
					success: function (response) {
						
						var json = JSON.parse(response);
						$('#tabelaresultado > tbody > tr').remove();
						
						for(p = 0; p < json.length; p++){
							$('#tabelaresultado > tbody ').
							append('<tr><td>'+json[p].id+'</td> <td>'+json[p].nome+
								'</td><td> <button type="button" class="btn btn-info" onclick="verEditar('+json[p].id+')">Ver</button></td> </tr>');
						
						}
						document.getElementById("total").textContent = 'Resultado: ' + json.length;
					}
				}).fail(function (xhr, status, errorThrown) {
					alert('Erro ao busca usuário por nome:' + xhr.responseText);
				})
			}
		}

		function criarDeleteAjax() {
			if (confirm('Deseja realmente excluir os Dados?')) {
				var urlAction = document.getElementById('formUser').action;
				var idUser = document.getElementById('id').value;

				$.ajax({
					method: "get",
					url: urlAction,
					data: "id=" + idUser + "&acao=deletarajax",
					success: function (response) {
						limpaForm();
						document.getElementById("msg").textContent = response;
					}
				}).fail(function (xhr, status, errorThrown) {
					alert('Erro ao deletar usuário por id:' + xhr.responseText);
				})
			}
		}

		function criarDelete() {

			if (confirm('Deseja realmente excluir os Dados?')) {
				document.getElementById("formUser").method = 'get';
				document.getElementById("acao").value = 'deletar';
				document.getElementById("formUser").submit();
			}
		}


		function limpaForm() {
			var elemento = document.getElementById("formUser").elements;

			for (p = 0; p < elemento.length; p++) {
				elemento[p].value = '';
			}
		}
	</script>
</body>

</html>