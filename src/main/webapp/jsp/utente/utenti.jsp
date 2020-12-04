<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="it">
<head>
	<jsp:include page="../header.jsp" />
	<title>Tavoli</title>
	
	<!-- style per le pagine diverse dalla index -->
    <link href="../assets/css/global.css" rel="stylesheet">
    
</head>
<body>
	<jsp:include page="../navbar.jsp" />
	
	<main role="main" class="container">
	
	<jsp:include page="../outcome-message.jsp" />
		
		<div class='card'>
		    <div class='card-header'>
		        <h5>Utenti che soddisfano i parametri della ricerca</h5> 
		    </div>
		    
		        <div class='table-responsive'>
		            <table class='table table-striped ' >
		                <thead>
		                    <tr>
		                        <th>Id</th>
		                        <th>Nome</th>
		                        <th>Cognome</th>
		                        <th>Username</th>
		                        <th>Esperienza</th>
		                        <th>Credito</th>
		                        <th>Stato</th>
		                        <th>Ruolo</th>
		                        <th>Data Registrazione</th>
		                        <th>Azioni</th>
		                    </tr>
		                </thead>
		                <tbody>
		                	<c:forEach var = "item" items ="${requestScope.utenti}">
		                    <tr >
		                        <td><c:out value = "${item.id}"/></td>
		                        <td><c:out value = "${item.nome}"/></td>
		                        <td><c:out value = "${item.cognome}"/></td>
		                        <td><c:out value = "${item.username}"/></td>
		                        <td><c:out value = "${item.exp}"/></td>
		                        <td><c:out value = "${item.credito}"/></td>
		                        <td><c:out value = "${item.stato}"/></td>
		                        <td><c:out value = "RUOLO"/></td>
		                        <td><c:out value = "${item.dataRegistrazione}"/></td>
		                        <!-- <td><c:forEach var = "giocatore" items ="item.giocatori"><c:out value = "giocatore.username>"/></c:forEach></td> -->
		                        <td>
									<a class="btn  btn-sm btn-outline-secondary" href="ShowUtenteServlet?id=${item.id}">Visualizza</a>
									<a class="btn  btn-sm btn-outline-primary ml-2 mr-2" href="PrepareUpdateUtenteServlet?id=${item.id}">Modifica</a>
									
									<c:if test="${item.stato == 'CREATO' || item.stato == 'INATTIVO'}">
									<a class="btn btn-outline-danger btn-sm" href="ActivateUtenteServlet?id=${item.id}">Attiva</a>
									</c:if>
									<c:if test="${item.stato == 'ATTIVO'}">
									<a class="btn btn-outline-danger btn-sm" href="DeactivateUtenteServlet?id=${item.id}">Disattiva</a>
									</c:if>
									
								</td>
		                    </tr>
		                    </c:forEach>
		                </tbody>
		            </table>
		        </div>
		   
			<!-- end card-body -->			   
		    </div>	
		
	
	<!-- end container -->	
	</main>
	<jsp:include page="../footer.jsp" />
	
</body>
</html>