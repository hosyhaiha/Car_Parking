<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Add Parking Lot</title>
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
</head>
<body>

	<%--navbar --%>
	<nav class="navbar navbar-light bg-light">
		<div>
			<i class="fa fa-location-arrow" aria-hidden="true"></i>&nbsp; <a
				class="navbar-brand" href="#">Parking lot</a>
		</div>
		<div>
			<a class="ml-auto" href="profile">Welcome ${user}</a>&emsp;&emsp;<a href="logout"><i
				class="fa fa-user-times" aria-hidden="true"></i> Logout</a>
		</div>
	</nav>
	<%--end navbar --%>

	<div class="container-fluid ">
		<div class="row">

			<%--sidebar --%>
			<div class="col-md-2 px-0 bg-light vh-100">
				<div class="sidenav bg-light">
					<a><i class="fa fa-cogs" aria-hidden="true"></i>&emsp;Car Park
						Manager</a>
					<hr>
					<button class="dropdown-btn ">
						<i class="fa fa-car" aria-hidden="true"></i>&nbsp; Car
						Manager&nbsp;<i class="fa fa-caret-down"></i>
					</button>
					<div class="dropdown-container">
						<a href="carlist"><i class="fa fa-list" aria-hidden="true"></i>&nbsp;&nbsp;Car
							List</a> <a href="addcar"><i class="fa fa-plus" aria-hidden="true"></i>&nbsp;&nbsp;Add
							Car</a>
					</div>
				</div>

				<div class="sidenav bg-light">
					<button class="dropdown-btn ">
						<i class="fa fa-cogs" aria-hidden="true"></i>&nbsp; Booking Office
						Manager&nbsp;<i class="fa fa-caret-down"></i>
					</button>
					<div class="dropdown-container">
						<a href="officelist"><i class="fa fa-list" aria-hidden="true"></i>&nbsp;&nbsp;Booking
							Office List</a> <a href="addoffice"><i class="fa fa-plus"
							aria-hidden="true"></i>&nbsp;&nbsp;Add Booking Office</a>
					</div>
				</div>

				<div class="sidenav bg-light">
					<button class="dropdown-btn ">
						<i class="fa fa-location-arrow" aria-hidden="true"></i>&nbsp;
						Parking Lot Manager&nbsp;<i class="fa fa-caret-down"></i>
					</button>
					<div class="dropdown-container">
						<a href="parkinglist"><i class="fa fa-list" aria-hidden="true"></i>&nbsp;&nbsp;Parking
							Lot List</a> <a href="addparking"><i class="fa fa-plus"
							aria-hidden="true"></i>&nbsp;&nbsp;Add Parking Lot</a>
					</div>
				</div>
			</div>
			<%--end sidebar --%>

			<div class="col-md-10 px-5 pt-4">
				<h4>Add Parking lot</h4>
				<hr style="width: 100%;">

				<form action="<%=request.getContextPath()%>/editparking"
					method="post" name="addForm" onsubmit="return validatePark()">
					<input name="id" value="<%=request.getParameter("id")%>"
						style="display: none">
					<div class="form-group row">
						<span class="col-md-3 col-form-label text-md-left"> <strong>Parking
								name</strong> <label>(*)</label></span>
						<div class="col-md-6">
							<input type="text" class="form-control" id="parkinglotname"
								name="parkinglotname" placeholder="Enter parking lot name"
								value="<%=request.getParameter("parkName")%>" required="required">
						</div>
						<p class="col-md-3" id="parkinglotnameerr" style="color: red;"></p>
					</div>

					<div class="form-group row">
						<span class="col-md-3 col-form-label text-md-left"> <strong>Place</strong>
							<label>(*)</label></span>
						<div class="col-md-6">
							<select name="place" id="place" class="form-control">
								<c:forEach items="${Place}" var="place">
									<option value="${place}">${place}</option>
								</c:forEach>
							</select>
						</div>
					</div>

					<div class="form-group row">
						<span class="col-md-3 col-form-label text-md-left"><strong>Area</strong>
							<label>(*)</label></span>
						<div class="col-md-6">
							<input type="number" class="form-control" id="area" name="area"
								placeholder="Enter area"
								value="<%=request.getParameter("parkArea")%>" required="required">
						</div>
						<div class="col-md-1">
							<p>
								<strong>(m2)</strong>
							</p>
						</div>
					</div>
					<p class="col-md-3" id="areaerr" style="color: red;"></p>
					<div class="form-group row">
						<span class="col-md-3 col-form-label text-md-left"><strong>Price</strong>
							<label>(*)</label></span>
						<div class="col-md-6">
							<input type="number" class="form-control" id="price" name="price"
								placeholder="Enter price"
								value="<%=request.getParameter("parkPrice")%>" required="required">
						</div>
						<div class="col-md-1">
							<p>
								<strong>(VND)</strong>
							</p>
						</div>
						<p class="col-md-3" id="priceerr" style="color: red;"></p>
					</div>

					<div class="form-group row">
						<span class="col-md-3 col-form-label text-md-left"> <strong>Status</strong>
							<label>(*)</label></span>
						<div class="col-md-6">
							<select name="status" id="status" class="form-control">
								<option value="Blank">Blank</option>
								<option value="Full">Full</option>
							</select>
						</div>
						<br> <br> <br>
					</div>

					<div class="form-group row ">
						<span class="col-md-3 col-form-label text-md-left"></span>
						<div class="col-md-6">
							<button type="reset" class="btn btn-warning">
								<i class="fa fa-undo" aria-hidden="true"></i>&nbsp;Reset
							</button>
							<button type="submit" class="btn btn-success">
								<i class="fa fa-plus" aria-hidden="true"></i>&nbsp;Update
							</button>
						</div>
					</div>
				</form>

			</div>
		</div>
	</div>

	<script type="text/javascript"
		src="${pageContext.request.contextPath}/assets/javascript/script.js"></script>

	<script>
		function validatePark() {
			var parkinglotname = document.addForm.parkinglotname.value;
			var area = document.addForm.area.value;
			var price = document.addForm.price.value;
			if (parkinglotname.match(/^ *$/) !== null) {
				document.getElementById("parkinglotnameerr").innerHTML = "Parking lot name plates is invalid";
				alert("Parking lot name is invalid");
				document.addForm.parkinglotname.value = '';
				return false;
			} else if (area.match(/^ *$/) !== null) {
				document.getElementById("areaerr").innerHTML = "Area is invalid";
				alert("Area is invalid");
				document.addForm.area.value = '';
				return false;
			} else if (price.match(/^ *$/) !== null) {
				document.getElementById("priceerr").innerHTML = "Price is invalid";
				alert("Price is invalid");
				document.addForm.price.value = '';
				return false;
			} else {
				return true;
			}
		}
	</script>
</body>
</html>
