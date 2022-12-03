<%@ page isErrorPage="true" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>User Page</title>

<link rel="stylesheet" href="css/layout.css" />
</head><c:if test="${type=='Admin'}">
<body>
	<div class="wrapper row0">
		<nav id="mainav" class="hoc clear">

			<ul class="clear">
				<li><a href="FrontController?command=ADMIN_PAGE">Home</a></li>
				<li><a href="FrontController?command=OPEN_PAGE_CREATE_CLIENT">Create
						a client </a></li>
				<li><a href="FrontController?command=logout">Exit</a></li>
			</ul>
		</nav>
	</div>

	<div class="wrapper row1">
		<header id="header" class="hoc clear">

			<h1>New client</h1>



		</header>
	</div>

	<div class="wrapper row3">
		<main class="hoc container clear">
			<!-- main body -->
			<form method="POST" action="FrontController">
				Name<br> <input type="text" name="name" value="">
				<br> Login<br> <input type="text"
					name="login" value=""> <br> Password<br> <input
					type="text" name="password" value=""> <br>
				<br>Type <br> <select name="typeList">
					<c:forEach var="typeList" items="${typeList}">
						<option>${typeList.name}</option>
					</c:forEach>
				</select> <br> <input type="hidden" name="command" value="CREATE_CLIENT" />
				<input class="btn btn-primary" type="submit" value="Create" />
			</form>
	</div>

	<div class="clear"></div>





</body></c:if>
</html>