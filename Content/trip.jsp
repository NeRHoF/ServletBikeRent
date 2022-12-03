<%@ page isErrorPage="true" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>User Page</title>

<link rel="stylesheet" href="css/layout.css" />
</head><c:if test="${type=='Moderator'}">
<body>
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

			<h1>Trips</h1>



		</header>
	</div>

	<div class="wrapper row3">
		<main class="hoc container clear">
			<!-- main body -->


			<table class="table">

				<tr>
					<th>Bike</th>
					<th>Client Name</th>

					<th>Start</th>

					<th>Finish</th>


				</tr>

				<c:forEach var="tripList" items="${tripList}">
					<tr>
						<td>${tripList.bikeId}</td>
						<td>${tripList.clientName}</td>
						<td>${tripList.startTime}</td>
						<td>${tripList.finishTime}</td>




					</tr>
				</c:forEach>
			</table>
	</div>

	<div class="clear"></div>
	</main>
	</div>




</body></c:if>
</html>