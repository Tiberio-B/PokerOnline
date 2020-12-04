<!doctype html>
<html lang="it">
<head>
<jsp:useBean id="utils" class="it.solvingteam.pokeronline.util.Utils"/>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
	<jsp:include page="../header.jsp" />
	<title>Modifica Utente</title>
	
	<!-- style per le pagine diverse dalla index -->
    <link href="../assets/css/global.css" rel="stylesheet">
    
</head>
<body>
	<jsp:include page="../navbar.jsp" />
	
	<main role="main" class="container">
	
		<jsp:include page="../outcome-message.jsp" />
		
		<div class='card'>
		    <div class='card-header'>
		        <h5>Modifica Utente</h5> 
		    </div>
		    <div class='card-body'>

					<form method="post" action="ExecuteUpdateUtenteServlet" novalidate="novalidate">
					
						<c:set var = "utente" scope = "request" value = "${utenteDTO}"/>
					
					<div class="form-row">
						
							<input type="hidden" name="id" id="id" value="${requestScope.id}">
							
							<div class="form-group col-md-4">
								<label>Nome</label>
								<input type="text" name="nome" id="nome" class="form-control" placeholder="Inserire il nome dell'utente" value="${utente.nome}" required>
							</div>
							
							<div class="form-group col-md-4">
								<label>Cognome</label>
								<input type="text" name="cognome" id="cognome" class="form-control" placeholder="Inserire il cognome dell'utente" value="${utente.cognome}" required>
							</div>
							
							<div class="form-group col-md-4">
								<label>Username</label>
								<input type="text" name="username" id="username" class="form-control" placeholder="Inserire l'username dell'utente" value="${utente.username}" required>
							</div>
							
					</div>

					<div class="form-row">
					
							<div class="form-group col-md-3">
								<label>Esperienza</label>
								<input type="number" name="exp" id="exp" class="form-control" placeholder="Inserire l'esperienza" value="${utente.exp}" required>
							</div>
							
							<div class="form-group col-md-3">
								<label>Credito</label>
								<input type="number" name="credito" id="credito" class="form-control" placeholder="Inserire il credito" value="${utente.credito}" required>
							</div>
					
							<div class="form-group col-md-3">
								<label>Stato</label>
								<input type="text" name="stato" id="stato" class="form-control" placeholder="Inserire lo stato" value="${utente.stato}" disabled readonly>
							</div>
							
							<!-- 
							<div class="form-group col-md-6">
							<label>Stato <span class="text-danger">*</span></label> <select
								class="browser-default custom-select" name="stato">

								<option value="${utente.stato}">${utente.stato}</option>

								<c:forEach var="stato" items="${requestScope.stati}">
									<c:if test="${stato != utente.stato }">
										<option value="${stato}">${stato}</option>
									</c:if>
								</c:forEach>
							</select>
						</div>
						 -->
						 
						 <c:if test="${utente.stato == 'CREATO'}">
						
						<div class="form-group col-md-3">
							<label>Ruolo Utente</label> 

								<c:forEach var="ruolo" items="${requestScope.ruoli}">
									<br/>
									<input type="checkbox" id="ruoliId" name="ruoliId" value="${ruolo.id}">
									<label for="ruoliId"> ${ruolo.codice}</label>
								</c:forEach>
						</div>
						
						</c:if>
							
						</div>
							
						<button type="submit" name="submit" value="submit" id="submit" class="btn btn-primary">Modifica</button>

					</form>
			<!-- end card-body -->			   
		    </div>
		    
		    <div class='card-footer'>
		        
		    </div>
		</div>	
	
	
	<!-- end container -->	
	</main>
	<jsp:include page="../footer.jsp" />
	
</body>
</html>