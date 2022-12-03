<%@ page isErrorPage="true" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>User Page</title>

<link rel="stylesheet" href="css/layout.css" />
</head>
<body><c:if test="${type=='Moderator'}">
	<div class="wrapper row0">
		<nav id="mainav" class="hoc clear">

			<ul class="clear">
				<li><a href="FrontController?command=MODERATOR_PAGE">Home</a></li>
				<li><a href="FrontController?command=ALL_BIKE">Bikes</a></li>
				<li><a href="FrontController?command=OPEN_PAGE_CREATE_BIKE">Create
						a bike </a></li>
				<li><a href="FrontController?command=OPEN_PAGE_CREATE_ADDRESS">Create
						a point address </a></li>
				<li><a href="FrontController?command=GET_TRIP">Trip </a></li>
				<li><a href="FrontController?command=GET_ADDRESS">Point
						address </a></li>
				<li><a href="FrontController?command=logout">Exit</a></li>
			</ul>

		</nav>
	</div>

	<div class="wrapper row1">
		<header id="header" class="hoc clear">

			<h1>Clients</h1>



		</header>
	</div>

	<div class="wrapper row3">
		<main class="hoc container clear">
			<!-- main body -->


			<table class="table">

				<tr>
					<th>Type</th>
					<th>Name</th>

					<th>Login</th>

					<th>Block</th>


				</tr>


				<c:forEach var="clientList" items="${clientList}">
					<tr>
						<td>${clientList.name}</td>
						<td>${clientList.type}</td>
						<td>${clientList.login}</td>
						<td>${clientList.block}</td>
						<td><a
							href="FrontController?command=BLOCK&id=${clientList.id}&blockFlag=${clientList.block}">Block</a></td>




					</tr>
				</c:forEach>
			
			</table>
	</div>

	<div class="clear"></div>
	</main>
	</div>




</body></c:if>
</html>