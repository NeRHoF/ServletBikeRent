<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<title>User Page</title>

<link rel="stylesheet" href="css/layout.css" />
</head>



<div class="wrapper row1">
	<header id="header" class="hoc clear"> </header>
</div>
<div class="wrapper row3">
	<main class="hoc container clear">

		<div class="content">

			<div id="gallery">
				<div class="wrapper row1">
		<header id="header" class="hoc clear">

			<h1>
				<a>Bike</a>
			</h1>

		</header>
	</div>
				
					<form class="style" name="loginForm" method="POST"
						action="FrontController">
						<center>
							<input type="hidden" name="command" value="Login" /> Login:<br />
							<input id="name" type="text1" class="form-control" name="login"
								value="" /> <br />Password:<br /> <input class="form-control"
								type="password" name="password" value="" /> 
							${loginerror} ${wrongAction} ${nullPage}<br /> <input
								class="btn btn-primary" type="submit" value="Log in" />
						</center>
					</form>
			</div>

		</div>

		<div class="clear"></div>
	</main>
</div>

</html>