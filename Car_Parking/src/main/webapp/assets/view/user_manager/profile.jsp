<%@page import="fa.training.entity.Employee"%>
<%@page import="fa.training.dao.EmployeeDAO"%>
<%@page import="fa.training.entity.Department"%>
<%@page import="java.util.List"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Employee</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.7.0/css/all.css"
	integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/style.css">
<body>

	<%--navbar --%>
	<nav class="navbar navbar-light bg-light">
		<div>
			<i class="fa fa-users" aria-hidden="true"></i>&nbsp; <a
				class="navbar-brand" href="#">Profile</a>
		</div>
		<div>
			<a class="ml-auto" href="profile">Welcome ${user}</a>&emsp;&emsp;<a
				href="logout"><i class="fa fa-user-times" aria-hidden="true"></i>
				Logout</a>
		</div>
	</nav>
	<%--end navbar --%>

	<div class="container-fluid ">
		<div class="row">
			<%--form --%>
			<div class="col-md-10 px-8 pt-4 ">
				<form action="${pageContext.request.contextPath}/profile"
					method="post">
					<h4>Profile Details</h4>
					<hr style="width: 100%;">

					<h5 style="color: red;">${msg}</h5>

					<input type="hidden" class="form-control" name="id" id="id"
						value="${id}">
					<%
					EmployeeDAO employeeDao = new EmployeeDAO();
					Employee e = employeeDao.getEmployeeByID(String.valueOf(request.getAttribute("id")));
					%>
					<div class="form-group row">
						<span class="col-md-3 col-form-label text-md-left"> <strong>Full
								Name</strong> <label>(*)</label></span>
						<div class="col-md-6">
							<input type="text" class="form-control" id="fullname"
								required="required" name="fullname"
								placeholder="Enter full name" value="<%=e.getEmployeeName()%>">
						</div>
					</div>

					<div class="form-group row">
						<span class="col-md-3 col-form-label text-md-left"> <strong>Phone
								number</strong> <label>(*)</label></span>
						<div class="col-md-6">
							<input type="text" class="form-control" id="phone" name="phone"
								required="required" value="<%=e.getEmployeePhone()%>"
								placeholder="Enter phone number">
						</div>
					</div>

					<div class="form-group row">

						<span class="col-md-3 col-form-label text-md-left"><strong>Date
								of birth</strong> <label>(*)</label></span>
						<div class="col-md-6">
							<input type="date" class="form-control" id="dateofbirth"
								required="required" name="dateofbirth" placeholder="dd/mm/yyyy"
								value="<%=e.getEmployeeBirthdate()%>">
						</div>
					</div>

					<div class="form-group row">
						<span class="col-md-3 col-form-label text-md-left"><strong>Sex</strong>
							<label>(*)</label></span>
						<div class="col-md-6">
							<span> <input
								<%=e.getSex().equalsIgnoreCase("M") ? "checked = 'checked'" : ""%>
								type="radio" name="sex" value="M" /> <strong>Male</strong> <input
								<%=e.getSex().equalsIgnoreCase("F") ? "checked = 'checked'" : ""%>
								type="radio" name="sex" value="F" /> <strong>Female</strong>
							</span>
						</div>
					</div>

					<div class="form-group row">
						<span class="col-md-3 col-form-label text-md-left"><strong>Address</strong></span>
						<div class="col-md-6">
							<input type="text" class="form-control" id="address"
								required="required" name="address" placeholder="Enter address"
								value="<%=e.getEmployeeAddress()%>">
						</div>
					</div>

					<div class="form-group row">
						<span class="col-md-3 col-form-label text-md-left"><strong>Email</strong></span>
						<div class="col-md-6">
							<input type="text" class="form-control" id="email" name="email"
								required="required" placeholder="Enter email"
								value="<%=e.getEmployeeEmail()%>">
						</div>
					</div>

					<div class="form-group row">
						<span class="col-md-3 col-form-label text-md-left"><strong>Account</strong>
							<label>(*)</label></span>
						<div class="col-md-6">
							<input type="text" class="form-control" id="account"
								required="required" name="account" placeholder="Enter account"
								disabled value="<%=e.getAccount()%>">
						</div>
					</div>

					<div class="form-group row">
						<span class="col-md-3 col-form-label text-md-left"><strong>Password</strong>
							<label>(*)</label></span>
						<div class="col-md-6">
							<input type="password" class="form-control" id="password"
								required="required" name="password" placeholder="Enter password"
								value="<%=e.getPassword()%>">
						</div>
					</div>

					<div class="form-group row">
						<span class="col-md-3 col-form-label text-md-left"><strong>Department</strong>
							<label>(*)</label></span>
						<div class="col-md-6">
							<input type="text" class="form-control" id="department"
								required="required" name="department" disabled
								value="<%=e.getDepartment()%>"> <br>
							<button type="button" class="btn btn-primary"
								onclick="history.back()">
								<i class="fa fa-backward"></i>&nbsp;Back
							</button>
							<input type="submit" class="btn btn-success" value="Update">&nbsp;

						</div>
					</div>
				</form>
			</div>
		</div>
	</div>

	<script type="text/javascript"
		src="${pageContext.request.contextPath}/assets/javascript/script.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/assets/javascript/button.js"></script>
</body>
</html>