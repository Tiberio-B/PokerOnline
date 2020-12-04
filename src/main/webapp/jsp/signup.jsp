<!doctype html>
<html lang="it">
<head>
<jsp:include page="./header.jsp" />
<title>Registrazione</title>

<!-- style per le pagine diverse dalla index -->
<link href="./assets/css/global.css" rel="stylesheet">
</head>
<body>
	<jsp:include page="./navbar.jsp" />

	<main role="main" class="container">

		<jsp:include page="./outcome-message.jsp" />

		<div class='card'>
			<div class='card-header'>
				<h5>Registrazione</h5>
			</div>
			<div class='card-body'>

				<form method="post" action="ExecuteInsertUtenteServlet"
					novalidate="novalidate">

					<div class="form-row">
					
						<div class="form-group col-md-6">
							<label>Nome</label> <input type="text" name="nome" id="nome"
								class="form-control" placeholder="Inserire il nome" value="${requestScope.nome}" required>
						</div>
						
						<div class="form-group col-md-6">
							<label>Cognome</label> <input type="text" name="cognome"
								id="cognome" class="form-control"
								placeholder="Inserire il cognome" value="${requestScope.cognome}" required>
						</div>
						
					</div>
					
					<div class="form-row">

						<div class="form-group col-md-6">
							<label>Username</label> <input type="text" name="username"
								id="username" class="form-control"
								placeholder="Inserire lo username" value="${requestScope.username}" required>
						</div>
						
						<div class="form-group col-md-6">
							<label>Password</label> <input type="password" name="password"
								id="password" class="form-control"
								placeholder="Inserire la password" value="${requestScope.password}" required>
						</div>

					</div>

					<button type="submit" name="submit" value="submit" id="submit"
						class="btn btn-primary">Registrati</button>


				</form>
				<!-- end card-body -->
			</div>

		</div>


		<!-- end container -->
	</main>
	<jsp:include page="./footer.jsp" />

</body>
</html>