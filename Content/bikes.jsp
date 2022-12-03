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
				</ul>

			</nav>
		</div>

		<div class="wrapper row1">
			<header id="header" class="hoc clear">

				<h1>
					<a>Bikes</a>
				</h1>

			</header>
		</div>

		<div class="wrapper row3">
			<main class="hoc container clear">

				<div class="content">
					<table class="table">

						<tr>
							<th>Bike</th>
							<th>Address</th>
							<th>Start time</th>

						</tr>

						<c:forEach var="tripList" items="${tripList}">
							<form method="POST" action="FrontController">
							<tr>

								<td>${tripList.bikeId}</td>
								<td>${tripList.address}</td>
								<td>${tripList.startTime}</td>
								<c:if test="${block!='BLOCKED'}">
									<td><c:if test="${tripList.startTime == null}">
											<a href="FrontController?command=drive&id=${tripList.id}">Lease</a>

										</c:if> <c:if test="${tripList.startTime != null}">
										<input type="hidden" name="bikeId" value=${tripList.bikeId} />
										<input type="hidden" name="id" value=${tripList.id} />
											<input type="hidden" name="command" value="unLease" />
											<input class="btn btn-primary" type="submit" value="Unlease" />
											
											<select name="addressList">
												<c:forEach var="addressList" items="${addressList}">
													<option>${addressList.address}</option>
												</c:forEach>
											</select>
										</c:if></td>
								</c:if>



							</tr>
							</form>
						</c:forEach>
						</form>
					</table>

				</div>

				<div class="clear"></div>
			</main>
		</div>
	</c:if>
</html>