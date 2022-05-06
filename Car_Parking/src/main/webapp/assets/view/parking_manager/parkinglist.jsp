<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Parking Lot List</title>
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
			<i class="fa fa-location-arrow" aria-hidden="true"></i>&nbsp; <a
				class="navbar-brand" href="#">Parking lot</a>
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
					<a><i class="fa fa-cogs" aria-hidden="true"></i>&emsp;Car Park
						Manager</a>
					<hr>
					<button class="dropdown-btn ">
						<i class="fa fa-car" aria-hidden="true"></i>&nbsp; Car
						Manager&nbsp;<i class="fa fa-caret-down"></i>
					</button>
					<div class="dropdown-container">
						<a href="carlist"><i class="fa fa-list" aria-hidden="true"></i>&nbsp;&nbsp;Car
							List</a> <a href="addcar"><i class="fa fa-plus"
							aria-hidden="true"></i>&nbsp;&nbsp;Add Car</a>
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
				<h4>Parking lot list</h4>
				<hr style="width: 100%;">

				<form action="">
					<div class="input-group col-md-8 ml-auto">
						<div class="btn btn-outline-secondary">
							<i class="fa fa-search"></i>
						</div>
						<input class="form-control" placeholder="Parking lot Search"
							id="search"> &nbsp;&nbsp;
						<div class="btn btn-outline-secondary">
							<i class="fa fa-filer"></i>Filter By
						</div>
						<select name="filterby" id="filterby" class="form-control">
							<option value="ParkID">ID</option>
							<option value="parkName">Parking lot</option>
							<option value="parkPlace">Place</option>
							<option value="parkArea">Area</option>
							<option value="parkPrice">Price</option>
							<option value="parkStatus">Status</option>
						</select>
						<button type="button" class="btn btn-primary"
							onclick="searchPark()">Search</button>
					</div>
					<br>
					<div id="content-search">
						<table class="table table-bordered">
							<thead>
								<tr>
									<th>ID</th>
									<th>Parking lot</th>
									<th>Place</th>
									<th>Area</th>
									<th>Price</th>
									<th>Status</th>
									<th>Action</th>
								</tr>
							</thead>
							<tbody id="abc">
								<c:forEach items="${list}" var="p">
									<input type="hidden" name="pageCurrentIndex"
										id="pageCurrentIndex" value="${pageCurrentIndex}">
									<tr>
										<td>${p.parkID}</td>
										<td>${p.parkName}</td>
										<td>${p.parkPlace}</td>
										<td>${p.parkArea}</td>
										<td>${p.parkPrice}</td>
										<td>${p.parkStatus}</td>
										<td><a
											href="editparking?id=${p.parkID}&parkName=${p.parkName}&parkPlace=${p.parkPlace}
										&parkArea=${p.parkArea}&parkPrice=${p.parkPrice}&parkStatus=${p.parkStatus}"><i
												class="fa fa-edit"></i>Edit</a> </span> <a
											href="deleteparking?id=${p.parkID}"
											onclick="return confirm('Are you sure you want to delete this item?');"><i class="fa fa-trash" aria-hidden="true"></i>Delete</a>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						<nav aria-label="paging">
							<input type="hidden" name="maxPage" id="maxPage"
								value="${maxPage}">
							<ul class="pagination">
								<li class="page-item"><a class="page-link"
									onclick="btnPreviousPark()" href="#">Previous</a></li>
								<c:forEach begin="1" end="${maxPage}" var="i">
									<li class="page-item" aria-current="page"><a
										class="page-link" id="currentPage${i}"
										onclick="pagingPark(${i})" href="#">${i}</a></li>
								</c:forEach>
								<li class="page-item"><a class="page-link"
									onclick="btnNextPark()" href="#">Next</a></li>
							</ul>
						</nav>
					</div>
				</form>

			</div>
		</div>
	</div>

	<script type="text/javascript"
		src="${pageContext.request.contextPath}/assets/javascript/button.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/assets/javascript/script.js"></script>
</body>
</html>
