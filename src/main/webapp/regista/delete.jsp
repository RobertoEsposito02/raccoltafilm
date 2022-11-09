<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
			  
			  		<div class='card'>
					    <div class='card-header'>
					        <h5>Visualizza dettaglio</h5>
					    </div>
					    
					
					    <div class='card-body'>
					    	<dl class="row">
							  <dt class="col-sm-3 text-right">Id:</dt>
							  <dd class="col-sm-9">${deleteRegistaAttr.id}</dd>
					    	</dl>
					    	
					    	<dl class="row">
							  <dt class="col-sm-3 text-right">Titolo:</dt>
							  <dd class="col-sm-9">${deleteRegistaAttr.nome}</dd>
					    	</dl>
					    	
					    	<dl class="row">
							  <dt class="col-sm-3 text-right">Genere:</dt>
							  <dd class="col-sm-9">${deleteRegistaAttr.cognome}</dd>
					    	</dl>
					    	
					    	<dl class="row">
							  <dt class="col-sm-3 text-right">Nickname:</dt>
							  <dd class="col-sm-9">${deleteRegistaAttr.nickName}</dd>
					    	</dl>
					    	
					    	<dl class="row">
							  <dt class="col-sm-3 text-right">Data Di Nascita:</dt>
							  <dd class="col-sm-9"><fmt:formatDate type = "date" value = "${deleteRegistaAttr.dataDiNascita}" /></dd>
					    	</dl>
					    	
					    	<dl class="row">
								  <dt class="col-sm-3 text-right">Sesso:</dt>
								  <dd class="col-sm-9">${deleteRegistaAttr.sesso}</dd>
							   	</dl>
							     	
					    <!-- end card body -->
					    </div>
					    
					    <div class='card-footer'>
					   
					        <form method="post" action="${pageContext.request.contextPath }/admin/ExecuteDeleteRegistaServlet">
					        <button type="submit" name="deleteSubmit" value="deleteSubmit" id="deleteSubmit" class="btn btn-danger">Elimina</button>
					        <input type="hidden" name="idRegista" value="${deleteRegistaAttr.id}">
					        
					        <a href="${pageContext.request.contextPath}/ExecuteListRegistaServlet" class='btn btn-outline-secondary' style='width:80px'>
					            <i class='fa fa-chevron-left'></i> Back
					        </a>
					        </form>
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