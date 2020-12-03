<!doctype html>
<html lang="it">
<head>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../header.jsp" />
<title>Cerca un Utente</title>

<!-- style per le pagine diverse dalla index -->
<link href="./assets/css/global.css" rel="stylesheet">

</head>
<body>
	<jsp:include page="../navbar.jsp" />

	<main role="main" class="container">

		<jsp:include page="../outcome-message.jsp" />

		<div class='card'>
			<div class='card-header'>
				<h5>Cerca un Utente</h5>
			</div>
			<div class='card-body'>

				<form method="post" action="ExecuteSearchUtenteServlet"
					novalidate="novalidate">

					<div class="form-row">
					
					<div class="form-group col-md-4">
							<label>Nome</label> <input type="text" name="nome" id="nome"
								class="form-control" placeholder="Inserire il nome" value="${utenteDTO.nome}">
						</div>
						
						<div class="form-group col-md-4">
							<label>Cognome</label> <input type="text" name="cognome"
								id="cognome" class="form-control"
								placeholder="Inserire il cognome" value="${utenteDTO.cognome}">
						</div>

						<div class="form-group col-md-4">
							<label>Username</label> <input type="text" name="username"
								id="username" class="form-control"
								placeholder="Inserire lo username" value="${utenteDTO.username}">
						</div>

					</div>
					
					<div class="form-row">
					
					<div class="form-group col-md-4">
							<label>Esperienza</label> <input type="number" name="exp" id="exp"
								class="form-control" placeholder="Inserire l'esperienza" value="${utenteDTO.exp}">
						</div>
						
						<div class="form-group col-md-4">
							<label>Credito</label> <input type="number" name="credito"
								id="credito" class="form-control"
								placeholder="Inserire il credito" value="${utenteDTO.credito}">
						</div>

						<div class="form-group col-md-4">
							<label>Data Registrazione</label> <input type="date" name="dataRegistrazione"
								id="dataRegistrazione" class="form-control"
								placeholder="Inserire la data di registrazione" value="${utenteDTO.dataRegistrazione}">
						</div>

					</div>

					<div class="form-row">
						<div class="form-group col-md-6">
							<label>Stato Utente</label>
							<select class="browser-default custom-select" name="stato">
							
								<option value="">Seleziona uno stato...</option>

								<c:forEach var="stato" items="${requestScope.stati}">
								<c:if test="${stato != utenteDTO.stato.name()}">
									<option value="${stato}">${stato}</option>
									</c:if>
								</c:forEach>
								
							</select>
						</div>

						<div class="form-group col-md-6">
							<label>Ruolo Utente</label> 

								<select class="browser-default custom-select" name="ruoloId">
								
								<option value="">Seleziona un ruolo...</option>

								<c:forEach var="ruolo" items="${requestScope.ruoli}">
									<option value="${ruolo.id}">${ruolo.codice.name()}</option>
								</c:forEach>
								
							</select>
						</div>

					</div>

					<button type="submit" name="submit" value="submit" id="submit"
						class="btn btn-primary">Conferma</button>


				</form>
				<!-- end card-body -->
			</div>

			<div class='card-footer'>
				<a href="ListAutoriServlet" class='btn btn-outline-secondary'
					style='width: 100px'> <i class='fa fa-chevron-left'></i>
					Indietro
				</a>
			</div>
		</div>


		<!-- end container -->
	</main>
	<jsp:include page="../footer.jsp" />

</body>
</html>