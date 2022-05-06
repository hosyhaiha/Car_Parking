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
				class="navbar-brand" href="#">Employee</a>
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
							href="addemployee"><i class="fa fa-plus" aria-hidden="true"></i>&nbsp;&nbsp;
							Add Employee</a>
					</div>
				</div>
			</aside>
			<%--end sidebar --%>

			<%--form --%>
			<div class="col-md-10 px-5 pt-4 ">
				<form action="${pageContext.request.contextPath}/editemployee"
					method="post">
					<h4>Employee Details</h4>
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
								disabled value="<%=e.getPassword()%>">
						</div>
					</div>

					<div class="form-group row">
						<span class="col-md-3 col-form-label text-md-left"><strong>Department</strong>
							<label>(*)</label></span>
						<div class="col-md-6">

							<select required="required" name="department"
								class="form-control">
								<%
								List<Department> dep = employeeDao.getDepartment();
								%>

								<option value="<%=e.getDepartment()%>"><%=e.getDepartment()%></option>
								<%
								for (Department d : dep) {
									if (!d.getDepartment().equalsIgnoreCase((String) request.getAttribute("department"))) {
								%>

								<option value="<%=d.getDepartment()%>"><%=d.getDepartment()%></option>
								<%
								}
								}
								%>


							</select> <br>

							<button type="button" class="btn btn-primary"
								onclick="history.back()">
								<i class="fa fa-backward"></i>&nbsp;Back
							</button>

							<a style="text-decoration: none;" href="deleteemployee?id=${id}">
								<input type="button" class="btn btn-danger" value="Delete"
								onclick="return confirm('Are you sure want to DELETE this emoployee ?');">&nbsp;
							</a> <input type="submit" class="btn btn-success" value="Update">&nbsp;

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