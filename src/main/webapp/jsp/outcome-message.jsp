<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:forEach var="errorMessage" items="${errorMessages}">
<div
	class="alert alert-danger alert-dismissible fade show ${errorMessage==null?'d-none': ''}"
	role="alert">
	${errorMessage}
	<button type="button" class="close" data-dismiss="alert"
		aria-label="Close">
		<span aria-hidden="true">&times;</span>
	</button>
</div>
</c:forEach>

<div
	class="alert alert-success alert-dismissible fade show ${successMessage==null?'d-none': ''}"
	role="alert">
	${successMessage}
	<button type="button" class="close" data-dismiss="alert"
		aria-label="Close">
		<span aria-hidden="true">&times;</span>
	</button>
</div>

<!-- 
<div class="alert alert-danger alert-dismissible fade show d-none"
	role="alert">
	Esempio di operazione fallita!
	<button type="button" class="close" data-dismiss="alert"
		aria-label="Close">
		<span aria-hidden="true">&times;</span>
	</button>
</div>
<div class="alert alert-info alert-dismissible fade show d-none"
	role="alert">
	Aggiungere d-none nelle class per non far apparire
	<button type="button" class="close" data-dismiss="alert"
		aria-label="Close">
		<span aria-hidden="true">&times;</span>
	</button>
</div>
 -->
