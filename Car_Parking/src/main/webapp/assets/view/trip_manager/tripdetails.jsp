<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Add Trip</title>
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
			<i class="fa fa-plane" aria-hidden="true"></i>&nbsp; <a
				class="navbar-brand" href="#">Trip</a>
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
					<button class="dropdown-btn ">
						<i class="fa fa-plane" aria-hidden="true"></i>&nbsp; Trip
						Manager&nbsp;<i class="fa fa-caret-down"></i>
					</button>
					<div class="dropdown-container">
						<a
							href="triplist"><i
							class="fa fa-list" aria-hidden="true"></i>&nbsp;&nbsp;Trip List</a> <a
							href="addtrip"><i
							class="fa fa-plus" aria-hidden="true"></i>&nbsp;&nbsp;Add Trip</a>
					</div>
				</div>

				<div class="sidenav bg-light">
					<button class="dropdown-btn ">
						<i class="fa fa-id-card" aria-hidden="true"></i>&nbsp; Ticket
						Manager&nbsp;<i class="fa fa-caret-down"></i>
					</button>
					<div class="dropdown-container">
						<a href="ticketlist"><i class="fa fa-list" aria-hidden="true"></i>&nbsp;&nbsp;Ticket
							List</a> <a href="addticket"><i class="fa fa-plus" aria-hidden="true"></i>&nbsp;&nbsp;Add
							Ticket</a>
					</div>
				</div>
			</div>
			<%--end sidebar --%>

			<div class="col-md-10 px-5 pt-4">
				<h4>Trip Details</h4>
				<hr style="width: 100%;">
				<form action="<%=request.getContextPath()%>/tripdetails" method="post" 
					name="updateForm" onsubmit="return validateUpdate()">
					<input type="hidden" id="tripID" name="tripID" value="${tripID}">
					<div class="form-group row">
						<span class="col-md-3 col-form-label text-md-left"> <strong>Destination</strong>
							<label>(*)</label></span>
						<div class="col-md-6">
							<input type="text" class="form-control" id="destination"
								value="${destination}" required="required" name="destination"
								maxlength="50">
						</div>
						<p class="col-md-3" id="destinationError" style="color: red;"></p>
					</div>

					<div class="form-group row">
						<span class="col-md-3 col-form-label text-md-left"> <strong>Departure
								time</strong> <label>(*)</label></span>
						<div class="col-md-6">
							<input type="time" class="form-control" id="departuretime"
								value="${departuretime}" required="required"
								name="departuretime" maxlength="50">
						</div>
					</div>

					<div class="form-group row">
						<span class="col-md-3 col-form-label text-md-left"><strong>Driver</strong>
							<label>(*)</label></span>
						<div class="col-md-6">
							<input type="text" class="form-control" id="driver" name="driver"
								value="${driver}" maxlength="50" required="required">
						</div>
						<p class="col-md-3" id="driverError" style="color: red;"></p>
					</div>

					<div class="form-group row">
						<span class="col-md-3 col-form-label text-md-left"><strong>Car
								type</strong> <label>(*)</label></span>
						<div class="col-md-6">
							<input type="text" class="form-control" id="cartype"
								value="${cartype}" required="required" name="cartype"
								maxlength="50">
						</div>
						<p class="col-md-3" id="cartypeError" style="color: red;"></p>
					</div>

					<div class="form-group row">
						<span class="col-md-3 col-form-label text-md-left"><strong>Maximum
								online ticket number</strong> <label>(*)</label></span>
						<div class="col-md-6">
							<input type="number" class="form-control" id="maxnumberticket"
								name="maxnumberticket" maxlength="50" value="${maxnumberticket}"
								required="required">
						</div>
						<p class="col-md-3" id="ticketnumberError" style="color: red;"></p>
					</div>

					<div class="form-group row">
						<span class="col-md-3 col-form-label text-md-left"><strong>Departure
								date</strong> <label>(*)</label></span>
						<div class="col-md-6">
							<input type="date" class="form-control" id="departuredate"
								name="departuredate" value="${departuredate}"
								required="required">
						</div>
					</div>

					<div class="form-group row">
						<span class="col-md-3 col-form-label text-md-left"><strong>Booked
								Ticket Number</strong></span>
						<div class="col-md-6">
							<input type="number" class="form-control" id="bookedticketnumber"
								required="required" name="bookedticketnumber" maxlength="50"
								value="${bookedticketnumber}">
						</div>
						<p class="col-md-3" id="bookedticketError" style="color: red;"></p>
					</div>

					<div class="form-group row ">
						<span class="col-md-3 col-form-label text-md-left"></span>
						<div class="col-md-6">
							<div id="error"></div>
							<button type="button" class="btn btn-warning"
								onclick="history.back()">
								<i class="fa fa-thumbs-down" aria-hidden="true"></i>&nbsp;Cancel
							</button>
							<button type="submit" class="btn btn-success">
								<i class="fa fa-thumbs-up" aria-hidden="true"></i>&nbsp;Update
							</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		function validateUpdate() {
			var destination = document.updateForm.destination.value;
			var driver = document.updateForm.driver.value;
			var cartype = document.updateForm.cartype.value;
			var numberticket = document.updateForm.maxnumberticket.value;
			var bookedticket = document.updateForm.bookedticketnumber.value;
			if (destination.match(/^ *$/) !== null) {
				document.getElementById("destinationError").innerHTML = "Destination is invalid";
				alert("Destination is invalid");
				document.updateForm.destination.value = '';
				return false;
			} else if (driver.match(/^ *$/) !== null) {
				document.getElementById("driverError").innerHTML = "Driver is invalid";
				alert("Driver is invalid");
				document.updateForm.driver.value = '';
				document.getElementById("destinationError").innerHTML = "";
				return false;
			} else if (cartype.match(/^ *$/) !== null) {
				document.getElementById("cartypeError").innerHTML = "Cartype is invalid";
				alert("Cartype is invalid");
				document.updateForm.cartype.value = '';
				document.getElementById("destinationError").innerHTML = "";
				document.getElementById("driverError").innerHTML = "";
				return false;
			} else if (isNaN(numberticket) || numberticket <= 0 
					|| numberticket.match(/^ *$/) !== null) {
				document.getElementById("tickenumbertError").innerHTML = "Ticket Number must be Numeric value only";
				alert("Maximum online ticket number is invalid");
				document.updateForm.maxnumberticket.value = '';
				document.getElementById("destinationError").innerHTML = "";
				document.getElementById("driverError").innerHTML = "";
				document.getElementById("cartypeError").innerHTML = "";
				return false;
			} else if (isNaN(bookedticket) || bookedticket < 0 || bookedticket > numberticket
					|| bookedticket.match(/^ *$/) !== null) {
				document.getElementById("bookedticketError").innerHTML = " Booked Ticket must be Numeric value only";
				alert("Booked ticket number is invalid");
				document.updateForm.bookedticketnumber.value = '';
				document.getElementById("destinationError").innerHTML = "";
				document.getElementById("driverError").innerHTML = "";
				document.getElementById("cartypeError").innerHTML = "";
				document.getElementById("ticketnumberError").innerHTML = "";
				return false;
			}else {
				return true;
			}
		}
	</script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/assets/javascript/script.js"></script>
</body>
</html>

