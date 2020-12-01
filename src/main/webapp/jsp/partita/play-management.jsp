<!doctype html>
<html lang="it">
<head>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../header.jsp" />

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

	<jsp:include page="../navbar.jsp"></jsp:include>


	<main role="main">

		<!-- Main jumbotron for a primary marketing message or call to action -->
		<div class="jumbotron">
			<div class="container">

				<jsp:include page="../outcome-message.jsp" />

				<h4 class="display-3">Esperienza: ${sessionScope.utente.exp}</h4>
				<h4 class="display-3">Credito: ${sessionScope.utente.credito}</h4>
				<br /> <br />

				<form class="form" action="CompraCreditiServlet" method="post">

					<div class="form-row">

						<div class="form-group col-md-2">
							<button class="btn btn-lg btn-primary btn-block" type="submit">Compra
								Crediti</button>
						</div>

						<div class="form-group col-md-2">
							<input type="number" id="crediti" name="crediti"
								class="form-control" placeholder="Quantità crediti" autofocus>
						</div>

					</div>

				</form>

				<p>

					<c:choose>
					<c:set var = "partita" scope = "session" value = "${utente.partita}"/>
						<c:when test="${not empty partita}">
							<a class="btn btn-primary btn-lg" href="GoToLastGameServlet&id=${partita.id}"
								role="button">Vai all'ultima Partita &raquo;</a>
						</c:when>
						<c:otherwise>
							<a class="btn btn-primary btn-lg"
								href="PrepareSearchPartiteServlet"
								role="button">Cerca Partite &raquo;</a>
						</c:otherwise>
					</c:choose>
				</p>
			</div>
		</div>

	</main>

	<jsp:include page="../footer.jsp" />
</body>
</html>