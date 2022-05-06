<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Ticket List</title>
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
			<i class="fa fa-id-card" aria-hidden="true"></i>&nbsp; <a
				class="navbar-brand" href="#">Ticket</a>
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
					<button class="dropdown-btn ">
						<i class="fa fa-plane" aria-hidden="true"></i>&nbsp; Trip
						Manager&nbsp;<i class="fa fa-caret-down"></i>
					</button>
					<div class="dropdown-container">
						<a href="triplist"><i class="fa fa-list" aria-hidden="true"></i>&nbsp;&nbsp;Trip
							List</a> <a href="addtrip"><i class="fa fa-plus"
							aria-hidden="true"></i>&nbsp;&nbsp;Add Trip</a>
					</div>
				</div>

				<div class="sidenav bg-light">
					<button class="dropdown-btn ">
						<i class="fa fa-id-card" aria-hidden="true"></i>&nbsp; Ticket
						Manager&nbsp;<i class="fa fa-caret-down"></i>
					</button>
					<div class="dropdown-container">
						<a href="ticketlist"><i class="fa fa-list" aria-hidden="true"></i>&nbsp;&nbsp;Ticket
							List</a> <a href="addticket"><i class="fa fa-plus"
							aria-hidden="true"></i>&nbsp;&nbsp;Add Ticket</a>
					</div>
				</div>
			</div>
			<%--end sidebar --%>

			<div class="col-md-10 px-5 pt-4">
				<h4>Ticket list</h4>
				<hr style="width: 100%;">

				<form action="">
					<div class="input-group col-md-12 ml-auto">

						<input class="form-control" type="text" placeholder="Search"
							value="" id="search"> &nbsp;&nbsp; <span class="pt-2"><strong>FilterBy</strong></span>&nbsp;&nbsp;
						<select id="filterby" class="custom-select col-sm-2">
							<option selected value="ticket.customerName">Customer
								Name</option>
							<option value="ticket.licensePlate">License Plate</option>
						</select>
						<button type="button" class="btn btn-primary"
							onclick="searchTicket()">Search</button>
						&nbsp;&nbsp;
					</div>
					<br>
					<div id="content-search-ticket">
						<table class="table table-bordered">
							<thead>
								<tr>
									<th>No</th>
									<th>Trip</th>
									<th>License plate</th>
									<th>Customer</th>
									<th>Booking time</th>
									<th>Action</th>
								</tr>
							</thead>
							<tbody id="content-ticket">
								<%
								int count = 0;
								%>
								<c:forEach items="${listTicket}" var="x">
									<input type="hidden" name="pageCurrentIndex"
										id="pageCurrentIndex" value="${pageCurrentIndex}">
									<tr>
										<td><%=++count%></td>
										<td>${x.tripName}</td>
										<td>${x.licensePlate}</td>
										<td>${x.customerName}</td>
										<td>${x.bookingTime}</td>
										<td><a href="deleteticket?id=${x.ticketID}"
											onclick="return confirm('Are you sure you want to delete this item?');"><i
												class="fa fa-trash" aria-hidden="true"></i>Delete</a> <a
											href="detailticket?id=${x.ticketID}">View</a></td>
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
										onclick="btnTicketPrevious()" href="#">Previous</a></li>
									<c:forEach begin="1" end="${maxPage}" var="i">
										<li class="page-item" aria-current="page"><a
											class="page-link" id="currentPage${i}"
											onclick="pagingTicket(${i})" href="#">${i}</a></li>
									</c:forEach>
									<li class="page-item"><a class="page-link"
										onclick="btnTicketNext()" href="#">Next</a></li>
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
