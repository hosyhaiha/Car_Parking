<%@page import="fa.training.dao.EmployeeDAO"%>
<%@page import="fa.training.entity.Department"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Add Employee</title>
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
				class="navbar-brand" href="logout">Employee</a>
		</div>
		<div>
			<a class="ml-auto" href="profile">Welcome ${user}</a>&emsp;&emsp;<a href="logout"><i
				class="fa fa-user-times" aria-hidden="true"></i> Logout</a>
		</div>
	</nav>
	<%--end navbar --%>

	<div class="container-fluid ">
		<div class="row">

			<%--sidebbar --%>
			<aside class="col-md-2 px-0 bg-light vh-100">
				<div class="sidenav bg-light">
					<a><i class="fa fa-cogs" aria-hidden="true"></i>&emsp;Dashboard</a>
					<hr>
					<button class="dropdown-btn ">
						Employee Manager &nbsp; <i class="fa fa-caret-down"></i>
					</button>
					<div class="dropdown-container">
						<a href="employeelist"><i class="fa fa-list"
							aria-hidden="true"></i>&nbsp;&nbsp;Employee List</a> <a
							href="addemployee"><i class="fa fa-plus" aria-hidden="true"></i>&nbsp;&nbsp;Add
							Employee</a>
					</div>
				</div>
			</aside>
			<%--end sidebar --%>

			<%--form --%>
			<div class="col-md-10 px-5 pt-4 ">
				<h4>Add Employee</h4>
				<hr style="width: 100%;">

				<form action="${pageContext.request.contextPath}/addemployee"
					method="post">
					<h5 style="color: red;">${msg}</h5>
					<div class="form-group row">
						<span class="col-md-3 col-form-label text-md-left"> <strong>Full
								Name</strong> <label>(*)</label></span>
						<div class="col-md-6">
							<input type="text" class="form-control" id="fullname"
								required="required" name="fullname" placeholder="Enter full name">
						</div>
					</div>

					<div class="form-group row">
						<span class="col-md-3 col-form-label text-md-left"> <strong>Phone
								number</strong> <label>(*)</label></span>
						<div class="col-md-6">
							<input type="text" class="form-control" id="phone" name="phone"
								required="required" placeholder="Enter phone number">
						</div>
					</div>

					<div class="form-group row">
						<span class="col-md-3 col-form-label text-md-left"><strong>Date
								of birth</strong> <label>(*)</label></span>
						<div class="col-md-6">
							<input type="date" class="form-control" id="dateofbirth"
								required="required" name="dateofbirth" placeholder="dd/mm/yyyy">
						</div>
					</div>

					<div class="form-group row">
						<span class="col-md-3 col-form-label text-md-left"><strong>Sex</strong>
							<label>(*)</label></span>
						<div class="col-md-6">
							<span> <input checked="checked" type="radio" name="sex"
								value="M" /> <strong>Male</strong> <input type="radio"
								name="sex" value="F" /> <strong>Female</strong>
							</span>
						</div>
					</div>

					<div class="form-group row">
						<span class="col-md-3 col-form-label text-md-left"><strong>Address</strong></span>
						<div class="col-md-6">
							<input type="text" class="form-control" id="address"
								required="required" name="address" placeholder="Enter address">
						</div>
					</div>

					<div class="form-group row">
						<span class="col-md-3 col-form-label text-md-left"><strong>Email</strong></span>
						<div class="col-md-6">
							<input type="text" class="form-control" id="email" name="email"
								required="required" placeholder="Enter email">
						</div>
					</div>

					<div class="form-group row">
						<span class="col-md-3 col-form-label text-md-left"><strong>Account</strong>
							<label>(*)</label></span>
						<div class="col-md-6">
							<input type="text" class="form-control" id="account"
								required="required" name="account" placeholder="Enter account">
						</div>
					</div>

					<div class="form-group row">
						<span class="col-md-3 col-form-label text-md-left"><strong>Password</strong>
							<label>(*)</label></span>
						<div class="col-md-6">
							<input type="password" class="form-control" id="password"
								required="required" name="password" placeholder="Enter password">
						</div>
					</div>

					<div class="form-group row">
						<span class="col-md-3 col-form-label text-md-left"><strong>Department</strong>
							<label>(*)</label></span>
						<div class="col-md-6">
							<select required="required" name="department" class="form-control">

								<%
								EmployeeDAO employeeDao = new EmployeeDAO();
								List<Department> dep = employeeDao.getDepartment();
								%>
								<option disabled selected value>-- Select a department --
								</option>
								<%
								for (Department d : dep) {
								%>

								<option value="<%=d.getDepartment()%>"><%=d.getDepartment()%></option>
								<%
								}
								%>

							</select> <br>
							<button type="button" class="btn btn-primary" onclick="history.back()"> 
								<i class="fa fa-backward"></i>&nbsp;Back
							</button>
							<button type="button" class="btn btn-warning"
								onclick="resetButton()">
								<i class="fa fa-undo" aria-hidden="true"></i>&nbsp;Reset
							</button>
							<button type="submit" class="btn btn-success">
								<i class="fa fa-plus" aria-hidden="true"></i>&nbsp;Add
							</button>
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