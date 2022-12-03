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

			<h1>New bike</h1>



		</header>
	</div>

	<div class="wrapper row3">
		<main class="hoc container clear">
			<!-- main body -->
			<form method="POST" action="FrontController">
				<br>Address <br> <select name="addressList">
					<c:forEach var="addressList" items="${addressList}">
						<option>${addressList.address}</option>
					</c:forEach>
				</select> <br> <input type="hidden" name="command" value="CREATE_bike" />
				<input class="btn btn-primary" type="submit" value="Create" />
			</form>
	</div>

	<div class="clear"></div>





</body></c:if>
</html>