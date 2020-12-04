<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="it">
<head>
	<jsp:include page="../header.jsp" />
	<title>Visualizza elemento</title>
	
	<!-- style per le pagine diverse dalla index -->
    <link href="../assets/css/global.css" rel="stylesheet">
    
</head>
<body>
	<jsp:include page="../navbar.jsp" />
	
	<main role="main" class="container">
		
		<div class='card'>
		    <div class='card-header'>
		        Dettaglio Tavolo
		    </div>
		    
		    <div class='card-body'>
		    <c:set var="item" value="${requestScope.tavoloDTO}"/>
		    	<dl class="row">
				  <dt class="col-sm-3 text-right">Nome:</dt>
				  <dd class="col-sm-9">${item.nome}</dd>
		    	</dl>
		    	
		    	<dl class="row">
				  <dt class="col-sm-3 text-right">Esperienza Minima:</dt>
				  <dd class="col-sm-9"> ${item.expMin}</dd>
		    	</dl>
		    	
		    	<dl class="row">
				  <dt class="col-sm-3 text-right">Puntata Minima:</dt>
				  <dd class="col-sm-9"> ${item.puntataMin}</dd>
		    	</dl>
		    	
		    	<dl class="row">
				  <dt class="col-sm-3 text-right">Data Creazione:</dt>
				  <dd class="col-sm-9"> ${item.dataCreazione}</dd>
		    	</dl>
		    	
		    	<dl class="row">
				  <dt class="col-sm-3 text-right">Proprietario:</dt>
				  <dd class="col-sm-9"> ${requestScope.proprietario}</dd>
		    	</dl>
		    	
		    	<dl class="row">
				  <dt class="col-sm-3 text-right">Giocatori:</dt>
				  <c:forEach var="utente" items="${requestScope.giocatori}">
				  <dd class="col-sm-9"> ${utente.username}</dd>
				  </c:forEach>
		    	</dl>
		    	
		    </div>
		    
		    <div class='card-footer'>
		        <a href="" class='btn btn-outline-secondary' style='width:100px'>
		            <i class='fa fa-chevron-left'></i> Indietro
		        </a>
		        
		        <a href="PrepareUpdateTavoloServlet?id=${item.id}" class='btn btn-outline-primary ml-2 mr-2' style='width:100px'>
		            <i class='fa fa-chevron-left'></i> Modifica
		        </a>
		        
		        <a href="PrepareDeleteTavoloServlet?id=${item.id}" class='btn btn-outline-secondary btn-outline-danger' style='width:100px'>
		            <i class='fa fa-chevron-left'></i> Rimuovi
		        </a>
		    </div>
		</div>	
	
	
	
	<!-- end main container -->	
	</main>
	<jsp:include page="../footer.jsp" />
	
</body>
</html>