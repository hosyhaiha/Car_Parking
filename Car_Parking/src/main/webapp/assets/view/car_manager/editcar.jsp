<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Add Car</title>
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
			<i class="fa fa-car" aria-hidden="true"></i>&nbsp; <a
				class="navbar-brand" href="#">Car</a>
		</div>
		<div>
			<a class="ml-auto" href="profile">Welcome ${user}</a>&emsp;&emsp;<a href="logout"><i
				class="fa fa-user-times" aria-hidden="true"></i> Logout</a>
		</div>
	</nav>
	<%--end navbar --%>

	<div class="container-fluid ">
		<div class="row">

			<%--sidebar--%>
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

			<%--form --%>
			<div class="col-md-10 px-5 pt-4">
				<h4>Edit Car</h4>
				<hr style="width: 100%;">

				<form action="<%=request.getContextPath()%>/editcar" name="addForm" method="post" onsubmit="return validateCar()">
					<div class="form-group row">
						<span class="col-md-3 col-form-label text-md-left"> <strong>License
								Plate</strong> <label>(*)</label></span>
						<div class="col-md-6">
							<input type="text" class="form-control" id="licenseplate"
								name="licenseplate" value="<%=request.getParameter("licensePlate")%>" readonly style="color: red;">
						</div>
					</div>

					<div class="form-group row">
						<span class="col-md-3 col-form-label text-md-left"> <strong>Car
								type</strong></span>
						<div class="col-md-6">
							<input type="text" class="form-control" id="cartype"
								name="cartype" placeholder="Enter car type" required="required" value="<%=request.getParameter("carType")%>">
						</div>
						<p class="col-md-3" id="cartypeerr" style="color: red;"></p>
					</div>

					<div class="form-group row">
						<span class="col-md-3 col-form-label text-md-left"><strong>Car
								color</strong></span>
						<div class="col-md-6">
							<input type="text" class="form-control" id="carcolor"
								name="carcolor" placeholder="Enter car color" required="required" value="<%=request.getParameter("carColor")%>">
						</div>
						<p class="col-md-3" id="carcolorerr" style="color: red;"></p>
					</div>

					<div class="form-group row">
						<span class="col-md-3 col-form-label text-md-left"><strong>Company</strong>
							<label>(*)</label></span>
						<div class="col-md-6">
							<select name="company" id="company" class="form-control">
								<c:forEach items="${CPN}" var="CPN">
									<option value="${CPN}" ${CPN == selectCPN ? 'selected="selected"' : ''}>${CPN}</option>
								</c:forEach>
							</select>
						</div>
					</div>

					<div class="form-group row">
						<span class="col-md-3 col-form-label text-md-left"><strong>Packing
								lot</strong> <label>(*)</label></span>
						<div class="col-md-6">
							<select name="pakinglot" id="pakinglot" class="form-control">
								<c:forEach items="${park}" var="park">
									<option value="${park.parkID}" ${park.parkID == selectPKL ? 'selected="selected"' : ''}">${park.parkName}</option>
								</c:forEach>
							</select>
						</div>
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
		function validateCar() {
			var cartype = document.addForm.cartype.value;
			var carcolor = document.addForm.carcolor.value;
			if(cartype.match(/^ *$/) !== null){
				document.getElementById("cartypeerr").innerHTML = "Car type is invalid";
				alert("Car type is invalid");
				document.addForm.cartype.value ='';
				return false;
			}else if(carcolor.match(/^ *$/) !== null){
				document.getElementById("carcolorerr").innerHTML = "Car color is invalid";
				alert("Car color is invalid");
				document.addForm.carcolor.value ='';
				return false;
			}else{
				return true;
			}
		}
	</script>
</body>
</html>
