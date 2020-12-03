<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="utils" class="it.solvingteam.pokeronline.util.Utils"/>
<!doctype html>
<html lang="it">
<head>
	<jsp:include page="../header.jsp" />
	<title>Partite</title>
	
	<!-- style per le pagine diverse dalla index -->
    <link href="./assets/css/global.css" rel="stylesheet">
    
</head>
<body>
	<jsp:include page="../navbar.jsp" />
	
	<main role="main" class="container">
	
	<jsp:include page="../outcome-message.jsp" />
		
		<a class="btn btn-primary" href="PrepareSearchPartitaServlet">Cerca</a>
		<div class='card'>
		    <div class='card-header'>
		        <h5>Partite</h5> 
		    </div>
		    
		        <div class='table-responsive'>
		            <table class='table table-striped ' >
		                <thead>
		                    <tr>
		                        <th>Id</th>
		                        <th>Nome</th>
		                        <th>Data Creazione</th>
		                        <th>Esperienza Minima</th>
		                        <th>Puntata Minima</th>
		                        <!-- <th>Giocatori</th>  -->
		                        <th>Azioni</th>
		                    </tr>
		                </thead>
		                <tbody>
		                	<c:forEach var = "item" items ="${requestScope.tavoli}">
		                    <tr >
		                        <td><c:out value = "${item.id}"/></td>
		                        <td><c:out value = "${item.nome}"/></td>
		                        <td>${utils.dateToString(item.dataCreazione)}</td>
		                        <td><c:out value = "${item.expMin}"/></td>
		                        <td><c:out value = "${item.puntataMin}"/></td>
		                        <!-- <td><c:forEach var = "giocatore" items ="item.giocatori"><c:out value = "giocatore.username>"/></c:forEach></td> -->
		                        <td>
									<a class="btn  btn-sm btn-outline-primary" href="GoToGameServlet?id=${item.id}">Gioca</a>
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