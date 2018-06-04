<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<head>

<!-- Access the bootstrap Css like this, Spring boot will handle the resource mapping automcatically -->
<link rel="stylesheet" type="text/css" href="/webjars/bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="/css/main.css" />

</head>
<body>

	<nav class="navbar navbar-inverse withoutborder">
		<div class="container">

			<div class="navbar-header">
				<a class="navbar-brand active" href="/">Maddox</a>
			</div>
			
			<div id="navbar" class="collapse navbar-collapse">
				<ul class="nav navbar-nav">
					<c:if test="${pageContext.request.userPrincipal.name != null}">
				        <form id="logoutForm" method="POST" action="${contextPath}/logout">
				            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
				        </form>
				        <li class="active"><a href="${contextPath}/form"> <span>form</span>
						</a></li>
				        <li>
				        	<a href="#" onclick="document.forms['logoutForm'].submit()">
				        		<span class="glyphicon glyphicon-log-out"></span>
				        	</a>
				        </li>
    				</c:if>
    				<li><a href="${contextPath}/">Details</a></li>
				</ul>
			</div>
			
		</div>
	</nav>

	<div class="container">
		<h2>Index Page</h2>
	
	    <div class="form-group">
	    	<input id="phoneNumber" name="phoneNumber" type="text" class="form-control" placeholder="phone number" autofocus="true"/>
	    	<br>
	    	<button class="btn btn-lg btn-primary btn-block btnSearch" type="submit">Search</button>
	    </div>
	</div>

	<script src="/webjars/jquery/jquery.min.js"></script>
	<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
	<script src="/js/index.js"></script>
</body>

</html>