<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html class="h-100">
	<head>
		<jsp:include page="../header.jsp" />
		<meta charset="ISO-8859-1">
		<title>Insert title here</title>
	</head>
	<body class="d-flex flex-column h-100">
	
		<jsp:include page="../navbar.jsp"></jsp:include>
	 
	
		<!-- Begin page content -->
		<main class="flex-shrink-0">
		  <div class="container">
		  
		  		<div class="alert alert-success alert-dismissible fade show  ${successMessage==null?'d-none':'' }" role="alert">
				  ${successMessage}
				  <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close" ></button>
				</div>
				<div class="alert alert-danger alert-dismissible fade show  ${errorMessage==null?'d-none':'' }" role="alert">
				  ${errorMessage}
				  <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close" ></button>
				</div>
				<div class="alert alert-danger alert-dismissible fade show d-none" role="alert">
				  Esempio di operazione fallita!
				  <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close" ></button>
				</div>
				<div class="alert alert-info alert-dismissible fade show d-none" role="alert">
				  Aggiungere d-none nelle class per non far apparire
				   <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close" ></button>
				</div>
		  
		  
		  
		  		<div class='card'>
				    <div class='card-header'>
				        <h5>Lista dei risultati</h5> 
				    </div>
				    <div class='card-body'>
				    	<a class="btn btn-primary " href="PrepareInsertRegistaServlet">Add New</a>
				    
				        <div class='table-responsive'>
				            <table class='table table-striped ' >
				                <thead>
				                    <tr>
			                         	<th>Nome</th>
				                        <th>Cognome</th>
				                        <th>Username</th>
				                        <th>Data Creazione</th>
				                        <th>Azioni</th>
				                    </tr>
				                </thead>
				                <tbody>
				                	<c:forEach items="${utenteSearch }" var="utenteItem">
										<tr>
											<td>${utenteItem.nome }</td>
											<td>${utenteItem.cognome }</td>
											<td>${utenteItem.username }</td>
											<td><fmt:formatDate type = "date" value = "${utenteItem.dateCreated }" /></td>
											<td>
												<a class="btn  btn-sm btn-outline-secondary" href="ExecuteVisualizzaRegistaServlet?idRegista=${registaItem.id }">Visualizza</a>
												<a class="btn  btn-sm btn-outline-primary ml-2 mr-2" href="PrepareUpdateRegistaServlet?idRegista=${registaItem.id }">Edit</a>
												<a class="btn btn-outline-danger btn-sm" href="${pageContext.request.contextPath }/users/PrepareDeleteRegistaServlet?idRegista=${registaItem.id }">Delete</a>
											</td>
										</tr>
									</c:forEach>
				                </tbody>
				            </table>
				        </div>
				   
					<!-- end card-body -->			   
			    </div>
			<!-- end card -->
			</div>	
		 
		   
		 <!-- end container -->  
		  </div>
		  
		</main>
		
		<!-- Footer -->
		<jsp:include page="../footer.jsp" />
	
	</body>
</html>