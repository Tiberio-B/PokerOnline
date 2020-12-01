<!doctype html>
<html lang="it">
  <head>
    
    <jsp:include page="./header.jsp" />
    
    <!-- Custom styles for this template -->
    <link href="./assets/css/global.css" rel="stylesheet">
    <style type="text/css">
    	body {
		  padding-top: 3.5rem;
		}	
    </style>
    
    <title>Poker Online</title>
  </head>
  <body>
  
	<jsp:include page="./navbar.jsp"></jsp:include>
  
	<main role="main">

	  <!-- Main jumbotron for a primary marketing message or call to action -->
	  <div class="jumbotron" >
	    <div class="container">
	      <h1 class="display-3">Benvenuto/a ${sessionScope.utente.nome}</h1>
	      <br/><br/>
	    <p><a class="btn btn-primary btn-lg" href="PrepareSearchTavoloServlet"
	    	role="button" ${sessionScope.specialPlayerPriviledges?'disabled':''}
								aria-disabled="${sessionScope.specialPlayerPriviledges}">Gestione Tavoli &raquo;</a></p>
	    <p><a class="btn btn-primary btn-lg" href="PlayManagementServlet" role="button"  ${sessionScope.playerPriviledges?'disabled':''}
								aria-disabled="${sessionScope.playerPriviledges}">Gestione Partite &raquo;</a></p>
	    <p><a class="btn btn-primary btn-lg" href="ListUtentiServlet" role="button"  ${sessionScope.adminPriviledges?'disabled':''}
								aria-disabled="${sessionScope.adminPriviledges}">Gestione Utenti &raquo;</a></p>
	    </div>    
	  </div>
	
	</main>
	
	<jsp:include page="./footer.jsp" />
  </body>
</html>