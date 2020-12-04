<!doctype html>
<html lang="it">
<head>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../header.jsp" />

<!-- Custom styles for this template -->
<link href="../assets/css/global.css" rel="stylesheet">
<style type="text/css">
body {
	padding-top: 3.5rem;
}
</style>

<title>Poker Online</title>
</head>
<body>

	<jsp:include page="../navbar.jsp"></jsp:include>


	<main role="main">

		<!-- Main jumbotron for a primary marketing message or call to action -->
		<div class="jumbotron">
			<div class="container">

				<jsp:include page="../outcome-message.jsp" />


				
				<div class='table-responsive'>
		            <table class='table table-striped ' >
		            <thead>
		                    <tr><c:out value = "Stai giocando con:"/></tr>
		                </thead>
		                <tbody>
		                	<c:forEach var = "giocatore" items ="${requestScope.partita.giocatori}">
		                    <tr >
		                        <td><c:out value = "${giocatore.id}"/></td>
		                        <td><c:out value = "${giocatore.nome}"/></td>
		                        <td><c:out value = "${giocatore.cognome}"/></td>
		                        <td><c:out value = "${giocatore.username}"/></td>
		                        <td><c:out value = "${giocatore.exp}"/></td>
		                        <td><c:out value = "${giocatore.credito}"/></td>
		                    </tr>
		                    </c:forEach>
		                </tbody>
		                </table>
		                <br/><br/>
				<h3 class="display-3">Esperienza: ${sessionScope.utente.exp}</h3>
				<h4 class="display-3">Credito: ${sessionScope.utente.credito}</h4>

				<br /> <br />
			
				<div class="form-group col-md-2">
				<a class="btn btn-primary btn-lg"
								href="PlayServlet"
								role="button">Gioca</a>
				</div>

				<div class="form-group col-md-2">
				<a class="btn btn-primary btn-lg"
								href="LeaveGameServlet"
								role="button">Lascia</a>
				</div>


			</div>
		</div>

	</main>

	<jsp:include page="../footer.jsp" />
</body>
</html>