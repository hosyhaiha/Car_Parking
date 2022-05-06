<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Booking Office List</title>
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
			<i class="fa fa-shopping-cart" aria-hidden="true"></i>&nbsp; <a
				class="navbar-brand" href="#">Booking Office</a>
		</div>
		<div>
			<a class="ml-auto" href="profile">Welcome ${user}</a>&emsp;&emsp;<a href="logout"><i
				class="fa fa-user-times" aria-hidden="true"></i> Logout</a>
		</div>
	</nav>
	<%--end --%>

	<div class="container-fluid ">
		<div class="row">

			<%--sidebar --%>
			<div class="col-md-2 px-0 bg-light vh-100 vh-100">
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
							Lot List</a> <a href="addparking"><i class="fa fa-plus" aria-hidden="true"></i>&nbsp;&nbsp;Add
							Parking Lot</a>
					</div>
				</div>
			</div>
			<%--sidebar --%>

			<%--form --%>
			<div class="col-md-10 px-5 pt-4">
				<h4>Office list</h4>
				<hr style="width: 100%;">
				<form action="">
					<div class="input-group col-md-10 ml-auto">
						<input class="form-control" type="search"
							placeholder="Booking Office Search" id="searchOffice">
						&nbsp;&nbsp;
						<span class="pt-2"><strong>FilterBy</strong></span>&nbsp;&nbsp;
						<select id="filterbyOffice" class="custom-select col-sm-2">
							<option selected value="officeName">Office Name</option>
						</select>
						<button type="button" class="btn btn-primary"
							onclick="searchOfficeBooking()">Search</button>
					</div>
					<br>
					<div id="content-search-office">
						<table class="table table-bordered">
							<thead>
								<tr>
									<th>ID</th>
									<th>Booking office</th>
									<th>Trip</th>
									<th>Action</th>
								</tr>
							</thead>
							<tbody id="content-office-booking">
								<c:forEach items="${listBookingOffice}" var="x">
									<input type="hidden" name="pageOfficeCurrentIndex"
										id="pageOfficeCurrentIndex" value="${pageOfficeCurrentIndex}">
									<tr>
										<td>${x.officeID}</td>
										<td>${x.officeName}</td>
										<td>${x.tripName}</td>
										<td><a href="detailoffice?id=${x.officeID}">View</a></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						<div>
							<nav aria-label="paging">
								<input type="hidden" name="maxPage" id="maxPage"
									value="${maxPage}">
								<ul class="pagination">
									<li class="page-item"><a class="page-link"
										onclick="btnBookingPrevious()" href="#">Previous</a></li>
									<c:forEach begin="1" end="${maxPage}" var="i">
										<li class="page-item" aria-current="page"><a
											class="page-link" id="currentPage${i}"
											onclick="pagingOfficeBooking(${i})" href="#">${i}</a></li>
									</c:forEach>
									<li class="page-item"><a class="page-link"
										onclick="btnOfficeNext()" href="#">Next</a></li>
								</ul>
							</nav>
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
