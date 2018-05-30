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
						<li><a href="#"
							onclick="document.forms['logoutForm'].submit()"> <span
								class="glyphicon glyphicon-log-out"></span>
						</a></li>
					</c:if>
					<li class="active"><a href="#">Details</a></li>
				</ul>
			</div>
		</div>
	</nav>

	<div class="container">

		<c:choose>
			<c:when test="${not empty error}">
				<div class="errorblock">
					<h2 class="form-heading">${error}</h2>
				</div>
			</c:when>
			<c:otherwise>
				<c:forEach items="${list}" var="order">
					<p>
						<button class="btn btn-primary" type="button"
							data-toggle="collapse"
							data-target=".multi-collapse-${order.orderID}"
							aria-expanded="false"
							aria-controls="multiCollapseExample1-${order.orderID} multiCollapseExample2-${order.orderID}">
							View Order</button>
					</p>

					<div
						class="panel panel-primary collapse multi-collapse-${order.orderID}"
						id="multiCollapseExample1-${order.orderID}">
						<div class="panel-heading">
							<h3 class="panel-title">
								<span class="glyphicon glyphicon-shopping-cart"></span> Products
							</h3>
						</div>
						<div class="panel-body">
							<c:forEach items="${order.details}" var="detail">
								<div class="radio">
									<label> ${detail.productName}
										 <c:forEach
											end="${detail.rating}" begin="1" step="1" varStatus="loop">
											<a href="#" data-rating="${loop.index}"
												data-product="${detail.productName}" data-order="${detail.orderID}"
												class="updateRating"> <span data-product="${detail.productName}"
												data-order="${detail.orderID}" data-rating="${loop.index}"
												class="glyphicon glyphicon-star"></span>
											</a>
										</c:forEach> <c:if test="${detail.rating < 5}">
											<c:forEach end="5" begin="${detail.rating+1}" step="1"
												varStatus="loop">
												<a href="#" data-rating="${loop.index}"
													data-product="${detail.productName}" data-order="${detail.orderID}"
													class="updateRating"> <span data-product="${detail.productName}"
													data-order="${detail.orderID}" data-rating="${loop.index}"
													class="glyphicon glyphicon-star-empty"></span>
												</a>
											</c:forEach>
										</c:if>
									</label>
								</div>
							</c:forEach>
						</div>
					</div>

					<div
						class="panel panel-primary collapse multi-collapse-${order.orderID}"
						id="multiCollapseExample2-${order.orderID}">
						<div class="panel-heading">
							<h3 class="panel-title">
								<span class="glyphicon glyphicon-list-alt"></span> Notes
							</h3>
						</div>
						<div class="panel-body">
							<c:forEach items="${order.notes}" var="note">
								<div class="form-group">
									<textarea class="form-control" rows="3" disabled>${note.notes}</textarea>
								</div>
							</c:forEach>
						</div>
					</div>

				</c:forEach>
			</c:otherwise>
		</c:choose>

	</div>

	<script src="/webjars/jquery/jquery.min.js"></script>
	<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
	<script src="/js/app.js"></script>

</body>

</html>