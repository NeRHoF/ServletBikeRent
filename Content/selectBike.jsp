<%@ page isErrorPage="true" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>User Page</title>

<link rel="stylesheet" href="css/layout.css" />
</head>
<body>
	<c:if test="${type=='User'}">

		<div class="wrapper row0">
			<nav id="mainav" class="hoc clear">

				<ul class="clear">
					<li><a href="FrontController?command=user_page">Home</a></li>
					<li><a href="FrontController?command=get_bikes">Bikes </a></li>
					<li><a href="FrontController?command=logout">Exit</a></li>
			</nav>
		</div>

		<div class="wrapper row1">
			<header id="header" class="hoc clear">

				<h1>
					<a>Free bikes</a>
				</h1>

			</header>
		</div>

		<div class="wrapper row3">
			<main class="hoc container clear">

				<div class="content">

					<div id="gallery">
						<figure>
							<center>
								<header class="heading"></header>
							</center>
							<ul class="nospace clear">
								<c:forEach var="bikeList" items="${bikeList}">
									<li class="one_quarter first">
										<h3 class="nospace btmspace-10">
											
												<a href="#">Bike: ${bikeList.id} </a>
												<br>
												<br>
												<br><c:if test="${block != 'BLOCKED'}">
												<a href="FrontController?command=book&id=${bikeList.id}">Book
													a bike </a>
												<br>
												<br>
												<a href="FrontController?command=Lease&id=${bikeList.id}">Lease
													a bike </a>
											</c:if>
										</h3>


									</li>
								</c:forEach>

							</ul>

						</figure>
					</div>

				</div>

				<div class="clear"></div>
			</main>
		</div>
	</c:if>
</html>