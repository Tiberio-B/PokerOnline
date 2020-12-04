<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!-- navbar -->
<nav class="navbar navbar-expand-md navbar-dark fixed-top bg-primary">

	<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExampleDefault" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
       <span class="navbar-toggler-icon"></span>
     </button>

  <div class="collapse navbar-collapse" id="navbarsExampleDefault">
    <ul class="navbar-nav mr-auto">
      <li class="nav-item active">
        <a class="nav-link" href="HomepageServlet">Home <span class="sr-only">(current)</span></a>
      </li>
      
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="dropdown01" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Menu</a>
        <div class="dropdown-menu" aria-labelledby="dropdown01">
          <a class="dropdown-item" href="PrepareSearchTavoloServlet">Gestione Tavoli</a>
          <a class="dropdown-item" href="PlayManagementServlet">Gestione Partite</a>
          <c:if test="${sessionScope.adminPriviledges}">
          <a class="dropdown-item" href="PrepareSearchUtenteServlet">Gestione Utenti</a>
          </c:if>
        </div>
      </li>
      
      <c:if test="${sessionScope.utente != null}">
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="dropdown02" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
        ${sessionScope.utente.nome} ${sessionScope.utente.cognome} <br/>
        @${sessionScope.utente.username}
        </a>
        <div class="dropdown-menu" aria-labelledby="dropdown02">
          <a class="dropdown-item" href="LogoutServlet">Logout</a>
        </div>
      </li>
      </c:if>
    
    </ul>
   
  </div>
  
   
  
  
</nav>
