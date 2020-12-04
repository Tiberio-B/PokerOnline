<!doctype html>
<html lang="it">
<head>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
	<jsp:include page="../header.jsp" />
	<title>Crea un Tavolo</title>
	
	<!-- style per le pagine diverse dalla index -->
    <link href="../assets/css/global.css" rel="stylesheet">
    
</head>
<body>
	<jsp:include page="../navbar.jsp" />
	
	<main role="main" class="container">
	
		<jsp:include page="../outcome-message.jsp" />
		
		<div class='card'>
		    <div class='card-header'>
		        <h5>Crea un Tavolo</h5> 
		    </div>
		    <div class='card-body'>

					<form method="post" action="/tavolo/ExecuteInsertTavoloServlet" novalidate="novalidate">
					
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
								<input type="text" name="dataCreazione" id="dataCreazione" class="form-control" value="${tavolo.dataCreazione}" disabled readonly>
							</div>
							
							<div class="form-group col-md-2">
								<label>Proprietario</label>
								<input type="text" name="proprietario" id="proprietario" class="form-control" value="@${requestScope.proprietario}" disabled readonly>
							</div>
							
						</div>
							
						<button type="submit" name="submit" value="submit" id="submit" class="btn btn-primary">Crea</button>

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