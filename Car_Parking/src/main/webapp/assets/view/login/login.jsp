

<link
	href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<script
	src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script
	src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!------ Include the above in your HEAD tag ---------->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/login.css">
<body>
	<div id="login">
		<div class="container">
			<div id="login-row"
				class="row justify-content-center align-items-center">
				<div id="login-column" class="col-md-6">
					<div id="login-box" class="col-md-12">
						<form id="login-form" class="form" action="login" method="post">
							<h3 class="text-center text-info">Login</h3>
							<c:if class="message" test="${msg != null}">
								<h5>${msg}</h5>
							</c:if>
							<div class="form-group">
								<label for="username" class="text-info">Username:</label><br>
								<input type="text" name="account" id="account" value =""
									class="form-control">
							</div>
							<div class="form-group">
								<label for="password" class="text-info">Password:</label><br>
								<input type="password" name="password" id="password" value =""
									class="form-control">
							</div>
							<div class="form-group">

								<input type="submit" name="submit" class="btn btn-info btn-md"
									value="Login">
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
