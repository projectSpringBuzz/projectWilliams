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
			
		</div>
	</nav>

	<div class="container">

		<form method="POST" action="" class="form-signin">
	        <h2 class="form-heading">Log in</h2>
	        
	        <c:if test="${not empty error}">
			  <div class="errorblock">
			   Your login attempt was not successful, try again.
			  </div>
 			</c:if>
	
	        <div class="form-group">
	            <input name="username" type="text" class="form-control" placeholder="Username" autofocus="true"/>
	            <input name="password" type="password" class="form-control" placeholder="Password"/>
	            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
	            <br>
	            <button class="btn btn-lg btn-primary btn-block" type="submit">Log In</button>
	        </div>

    	</form>

	</div>

	<script src="/webjars/jquery/jquery.min.js"></script>
	<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>

</body>

</html>