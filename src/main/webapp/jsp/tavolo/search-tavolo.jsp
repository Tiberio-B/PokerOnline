<!doctype html>
<html lang="it">
<head>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
	<jsp:include page="../header.jsp" />
	<title>Cerca un Tavolo</title>
	
	<!-- style per le pagine diverse dalla index -->
    <link href="./assets/css/global.css" rel="stylesheet">
    
</head>
<body>
	<jsp:include page="../navbar.jsp" />
	
	<main role="main" class="container">
	
		<jsp:include page="../outcome-message.jsp" />
		
		<form method="post" action="FormTavoloServlet" novalidate="novalidate">
		
		<!--<c:set var = "notSpecialPlayer" scope = "session" value = "${not specialPlayerPriviledges}"/>
		    <a class="btn btn-primary ${notSpecialPlayer?'disabled':''}" href="PrepareInsertTavoloServlet" aria-disabled="${notSpecialPlayer}">Crea Tavolo</a>
		-->
		
		<button type="submit" name="action" value="PrepareInsertTavoloServlet"class="btn btn-primary" aria-disabled="${notSpecialPlayer}">Crea Tavolo</button>
		
		<div class='card'>
		    <div class='card-header'>
		        <h5>Cerca un Tavolo</h5> 
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
							
						<button type="submit" name="action" value="ExecuteSearchTavoloServlet" class="btn btn-primary">Cerca</button>

					
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