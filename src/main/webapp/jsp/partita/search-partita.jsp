<!doctype html>
<html lang="it">
<head>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
	<jsp:include page="../header.jsp" />
	<title>Cerca una Partita</title>
	
	<!-- style per le pagine diverse dalla index -->
    <link href="./assets/css/global.css" rel="stylesheet">
    
</head>
<body>
	<jsp:include page="../navbar.jsp" />
	
	<main role="main" class="container">
	
		<jsp:include page="../outcome-message.jsp" />
		
		<form method="post" action="ExecuteSearchPartitaServlet" novalidate="novalidate">
		
		<div class='card'>
		    <div class='card-header'>
		        <h5>Cerca Partita</h5> 
		    </div>
		    <div class='card-body'>
	
						<c:set var = "tavolo" scope = "request" value = "${tavoloDTO}"/>
					
						<div class="form-row">
							<div class="form-group col-md">
								<label>Nome Tavolo</label>
								<input type="text" name="nome" id="nome" class="form-control" placeholder="Inserire il nome del tavolo" value="${tavolo.nome}" required>
							</div>
							
							<div class="form-group col-md-2">
								<label>Puntata Minima</label>
								<input type="number" name="puntataMin" id="puntataMin" class="form-control" placeholder="Inserire la puntata minima" value="${tavolo.puntataMin}" required>
							</div>
							
							<div class="form-group col-md-2">
								<label>Esperienza Minima</label>
								<input type="number" name="expMin" id="expMin" class="form-control" placeholder="Inserire l'esperienza minima" value="${tavolo.expMin}" required>
							</div>
							
							<div class="form-group col-md-2">
								<label>Data Creazione</label>
								<input type="date" name="dataCreazione" id="dataCreazione" class="form-control" placeholder="Inserire la data di creazione" value="${tavolo.dataCreazione}" required>
							</div>
							
							<div class="form-group col-md-2">
								<label>Proprietario</label>
								<input type="text" name="proprietario" id="proprietario" class="form-control" value="@${sessionScope.utente.username}" disabled readonly>
							</div>
							
						</div>
						
						<div class="form-row">
						
							<div class="form-group col-md-6">
								<label>Proprietario</label>
								<select class="browser-default custom-select" name="stato">
								
								<option value="">Seleziona un proprietario...</option>

								<c:forEach var="proprietario" items="${requestScope.utenti}">
								
									<option value="${proprietario.id}">${proprietario.username}</option>
									
								</c:forEach>
								
							</select>
							</div>
							
							<div class="form-group col-md-6">
								<label>Partecipante</label>
								<select class="browser-default custom-select" name="stato">
								
								<option value="">Seleziona un partecipante...</option>

								<c:forEach var="giocatore" items="${requestScope.utenti}">
								
								<option value="${giocatore.id}">${giocatore.username}</option>
									
								</c:forEach>
								
							</select>
							</div>
						</div>
							
						<button type="submit" name="submit" class="btn btn-primary">Cerca</button>

					
			<!-- end card-body -->			   
		    </div>
		    
		    <div class='card-footer'>
		        
		    </div>
		</div>
		</form>
	
	
	<!-- end container -->	
	</main>
	<jsp:include page="../footer.jsp" />
	
</body>
</html>