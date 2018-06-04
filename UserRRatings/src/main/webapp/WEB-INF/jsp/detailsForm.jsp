<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<head>

<!-- Access the bootstrap Css like this, Spring boot will handle the resource mapping automcatically -->
<link rel="stylesheet" type="text/css"
	href="/webjars/bootstrap/css/bootstrap.min.css" />
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
							<input type="hidden" name="${_csrf.parameterName}"
								value="${_csrf.token}" />
						</form>
						<li class="active"><a href="${contextPath}/form"> <span>form</span>
						</a></li>
						<li><a href="#"
							onclick="document.forms['logoutForm'].submit()"> <span
								class="glyphicon glyphicon-log-out"></span>
						</a></li>
					</c:if>
					<li><a href="${contextPath}/">Details</a></li>
				</ul>
			</div>
		</div>
	</nav>

	<div class="container">
		<div class="row">
			<h3>Orders details</h3>
			<div class="form-group">
				<label for="phoneNumber"> Phone Number</label> <input type="text"
					id="phoneNumber" value="" placeholder="enter phone number"
					class="form-control" />
			</div>
		</div>
		<div class="row">
			<div class="panel panel-primary">

				<div class="panel-heading">
					<h3 class="panel-title">
						<span class="glyphicon glyphicon-shopping-cart"></span> Products
					</h3>
				</div>

				<div class="panel-body">
					<div class="form-group">
						<label for="product1" class="control-label col-xs-2">
							Product 1: </label>
						<div class="col-xs-8">
							<input type="text" name="product1" value="" class="form-control"
								placeholder="product name" />
						</div>
						<div class="col-xs-2">
							<input type="number" name="stars1" class="form-control"
								placeholder="number of stars" min="0" max="5" />
						</div>
					</div>
					<div class="form-group">
						<label for="product2" class="control-label col-xs-2">
							Product 2: </label>
						<div class="col-xs-8">
							<input type="text" name="product2" value="" class="form-control"
								placeholder="product name" />
						</div>
						<div class="col-xs-2">
							<input type="number" name="stars2" class="form-control"
								placeholder="number of stars" min="0" max="5" />
						</div>
					</div>
					<div class="form-group">
						<label for="product3" class="control-label col-xs-2">
							Product 3: </label>
						<div class="col-xs-8">
							<input type="text" name="product3" value="" class="form-control"
								placeholder="product name" />
						</div>
						<div class="col-xs-2">
							<input type="number" name="stars3" class="form-control"
								placeholder="number of stars" min="0" max="5" />
						</div>
					</div>
				</div>
			</div>

			<div class="panel panel-primary">
				<div class="panel-heading">
					<h3 class="panel-title">
						<span class="glyphicon glyphicon-list-alt"></span> Notes
					</h3>
				</div>
				<div class="panel-body">
					<div class="form-group">
						<textarea class="form-control" rows="3" id="notas"></textarea>
					</div>
				</div>
			</div>

			<div class="form-group">
				<div class="col-xs-offset-5 col-xs-7">
					<button id="btnSubmit" type="button" class="btn btn-primary">Submit</button>
				</div>
			</div>
		</div>
	</div>

	<script src="/webjars/jquery/jquery.min.js"></script>
	<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
	<script src="js/form.js"></script>
</body>

</html>