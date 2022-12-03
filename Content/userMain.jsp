<%@ page isErrorPage="true" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>User Page</title>

<link rel="stylesheet" href="css/layout.css" />
</head>
<c:if test="${type=='User'}">
	<body>

		<div class="wrapper row0">
			<nav id="mainav" class="hoc clear">

				<ul class="clear">
					<li><a href="FrontController?command=user_page">Home</a></li>
					<li><a href="FrontController?command=get_bikes">Bikes </a></li>
					<li><a href="FrontController?command=logout">Exit</a></li>
				</ul>

			</nav>
		</div>

		<div class="wrapper row1">
			<header id="header" class="hoc clear">

				<h1>
					<a>Point adress</a>
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

							<c:forEach var="addressList" items="${addressList}">

								<li class="one_quarter">
									<h3 class="nospace btmspace-10">
										
											<a
												href="FrontController?command=select_bike&id=${addressList.id}">${addressList.address}
											</a>
									
									</h3>
								</li>
							</c:forEach>
						</figure>
					</div>

				</div>

				<div class="clear"></div>
			</main>
		</div>
</c:if>
</html>