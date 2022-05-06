<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Employee List</title>
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
			<a class="ml-auto" href="profile">Welcome ${user}</a>&emsp;&emsp;<a
				href="logout"><i class="fa fa-user-times" aria-hidden="true"></i>
				Logout</a>
		</div>
	</nav>
	<%--end navbar --%>

	<div class="container-fluid ">
		<div class="row">

			<%--sidebar --%>
			<div class="col-md-2 px-0 bg-light vh-100">
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
			</div>
			<%--end sidebar --%>

			<div class="col-md-10 px-5 pt-4">
				<h4>Employee list</h4>
				<hr style="width: 100%;">

				<form action="">
					<div class="input-group col-md-12 ml-auto">
						<input class="form-control" type="search"
							placeholder="User Search" id="search"> &nbsp;&nbsp; 
							<span class="pt-2"><strong>FilterBy</strong></span>&nbsp;&nbsp;
						<i class="fa fa-filer"></i> <select id="select"
							class="custom-select col-sm-2">
							<option selected value="employeeName" id="employeeName">Name</option>
							<option value="employeeAddress" id="employeeAddress">Address</option>
							<option value="department" id="department">Department</option>
						</select>
						<button type="button" class="btn btn-primary"
							onclick="searchName()">Search</button>
					</div>
					<br>
					<div id="content-search">
						<table  class="table table-bordered">
							<thead>
								<tr>
									<th>ID</th>
									<th>Name</th>
									<th>DateOfBirth</th>
									<th>Address</th>
									<th>Phone</th>
									<th>Department</th>
									<th>Action</th>
								</tr>
							</thead>
							<tbody id = "content-employee">
								<c:forEach items="${employee}" var="e">
									<input type="hidden" name="pageCurrentIndex"
										id="pageCurrentIndex" value="${pageCurrentIndex}">

									<tr>
										<td>${e.employeeID}</td>
										<td>${e.employeeName}</td>
										<td>${e.employeeBirthdate}</td>
										<td>${e.employeeAddress}</td>
										<td>${e.employeePhone}</td>
										<td>${e.department}</td>
										<td><a href="employeedetails?id=${e.employeeID}"
											class="link-primary"><i class="fa fa-search" aria-hidden="true"></i> View </a></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						<nav aria-label="paging">
							<input type="hidden" name="maxPage" id="maxPage"
								value="${maxPage}">
							<ul class="pagination">
								<li class="page-item"><a class="page-link"
									onclick="btnPrevious()" href="#">Previous</a></li>
								<c:forEach begin="1" end="${maxPage}" var="i">
									<li class="page-item" aria-current="page"><a
										class="page-link" id="currentPage${i}"
										onclick="pagingEmployee(${i})" href="#">${i}</a></li>
								</c:forEach>
								<li class="page-item"><a class="page-link"
									onclick="btnNext()" href="#">Next</a></li>
							</ul>
						</nav>
					</div>

				</form>

			</div>
		</div>
	</div>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/assets/javascript/button.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/assets/javascript/script.js"></script>
</body>
</html>