


function searchName() {
	var search = document.getElementById("search").value;
	var select = document.getElementById("select").value;
	$.ajax({

		url: "/Car_Parking/searchemployee",
		type: "get", //send it through get method
		data: {

			ename: search,
			eselect: select,
		},
		success: function(data) {
			var row = document.getElementById("content-search");
			row.innerHTML = data;
		},
		error: function(xhr) {
			//Do Something to handle error
		}
	});

}


function btnSearchPrevious() {
	var index = document.getElementById("pageSearchCurrentIndex").value;
	var search = document.getElementById("searchValue").value;
	var select = document.getElementById("selectValue").value;
	if (index > 1) {
		index--;
	}
	$.ajax({
		url: "/Car_Parking/pagingsearch",
		type: "get", //send it through get method
		data: {
			pageIndex: index,
			esearch: search,
			eselect: select,
		},
		success: function(data) {
			var row = document.getElementById("content-search");
			row.innerHTML = data;
		},
		error: function(xhr) {
			//Do Something to handle error
		}
	});

}


function btnSearchNext() {
	var maxPage = document.getElementById("maxSearchPage").value;
	var index = document.getElementById("pageSearchCurrentIndex").value;
	var search = document.getElementById("searchValue").value;
	var select = document.getElementById("selectValue").value;
	if (index < maxPage) {
		index++;
	}
	$.ajax({
		url: "/Car_Parking/pagingsearch",
		type: "get", //send it through get method
		data: {
			pageIndex: index,
			esearch: search,
			eselect: select,
		},
		success: function(data) {
			var row = document.getElementById("content-search");
			row.innerHTML = data;
		},
		error: function(xhr) {
			//Do Something to handle error
		}
	});

}

function btnPrevious() {
	var maxPage = document.getElementById("maxPage").value;
	var index = document.getElementById("pageCurrentIndex").value;
	if (index > 1) {
		index--;
	}
	for (let i = 1; i <= maxPage; i++) {
		document.getElementById("currentPage".concat(i)).style.backgroundColor = "White";
	}
	$.ajax({
		url: "/Car_Parking/paging",
		type: "get", //send it through get method
		data: {
			pageIndex: index,
		},
		success: function(data) {
			var row = document.getElementById("content-employee");
			row.innerHTML = data;
			document.getElementById("currentPage".concat(index)).style.backgroundColor = "#a7fff9";
		},
		error: function(xhr) {
			//Do Something to handle error
		}
	});

}

function btnNext() {
	var maxPage = document.getElementById("maxPage").value;
	var index = document.getElementById("pageCurrentIndex").value;
	if (index < maxPage) {
		index++;
	}
	for (let i = 1; i <= maxPage; i++) {
		document.getElementById("currentPage".concat(i)).style.backgroundColor = "White";
	}

	$.ajax({
		url: "/Car_Parking/paging",
		type: "get", //send it through get method
		data: {
			pageIndex: index
		},
		success: function(data) {
			var row = document.getElementById("content-employee");
			row.innerHTML = data;
			document.getElementById("currentPage".concat(index)).style.backgroundColor = "#a7fff9";
		},
		error: function(xhr) {
			//Do Something to handle error
		}
	});

}

function pagingEmployee(pageNum) {

	var index = document.getElementById("currentPage".concat(pageNum)).textContent;
	var maxPage = document.getElementById("maxPage").value;
	for (let i = 1; i <= maxPage; i++) {
		document.getElementById("currentPage".concat(i)).style.backgroundColor = "White";
	}
	$.ajax({
		url: "/Car_Parking/paging",
		type: "get", //send it through get method
		data: {
			pageIndex: index
		},
		success: function(data) {
			var row = document.getElementById("content-employee");
			row.innerHTML = data;
			document.getElementById("currentPage".concat(pageNum)).style.backgroundColor = "#a7fff9";
		},
		error: function(xhr) {
			//Do Something to handle error
		}
	});
}

function pagingEmployeeSearch(pageNum) {

	var index = document.getElementById("currentSearchPage".concat(pageNum)).textContent;
	var search = document.getElementById("searchValue").value;
	var select = document.getElementById("selectValue").value;
	$.ajax({
		url: "/Car_Parking/pagingsearch",
		type: "get", //send it through get method
		data: {
			pageIndex: index,
			esearch: search,
			eselect: select,
		},
		success: function(data) {
			var row = document.getElementById("content-search");
			row.innerHTML = data;
		},
		error: function(xhr) {
			//Do Something to handle error
		}
	});
}


function resetButton() {
	document.getElementById('fullname').value = ''
	document.getElementById('phone').value = ''
	document.getElementById('dateofbirth').value = ''
	document.getElementById("address").value = '';
	document.getElementById('email').value = ''
	document.getElementById('account').value = ''
	document.getElementById('password').value = ''
}


// START TRIP _ DO QUANG QUY

function searchDestination() {
	var search = document.getElementById("search").value;
	var date = document.getElementById("searchdate").value;
	var dateTo = document.getElementById("searchdateTo").value;
	$.ajax({
		url: "/Car_Parking/searchtrip",
		type: "get", //send it through get method
		data: {
			esearch: search,
			edate: date,
			dateTo: dateTo,
		},
		success: function(data) {
			var row = document.getElementById("tripcontent-search");
			row.innerHTML = data;
		},
		error: function(xhr) {
			//Do Something to handle error
		}
	});
}

function btnPreviousTrip() {
	var index = document.getElementById("pageTripCurrentIndex").value;
	var maxPage = document.getElementById("maxPage").value;
	if (index > 1) {
		index--;
	}
	for (let i = 1; i <= maxPage; i++) {
		document.getElementById("currentPage".concat(i)).style.backgroundColor = "White";
	}

	$.ajax({
		url: "/Car_Parking/trippaging",
		type: "get", //send it through get method
		data: {
			pageIndex: index
		},
		success: function(data) {
			var row = document.getElementById("content-trip");
			row.innerHTML = data;
			document.getElementById("currentPage".concat(index)).style.backgroundColor = "#a7fff9";
		},
		error: function(xhr) {
			//Do Something to handle error
		}
	});

}

function btnNextTrip() {
	var maxPage = document.getElementById("maxPage").value;
	var index = document.getElementById("pageTripCurrentIndex").value;
	if (index < maxPage) {
		index++;
	}
	for (let i = 1; i <= maxPage; i++) {
		document.getElementById("currentPage".concat(i)).style.backgroundColor = "White";
	}

	$.ajax({
		url: "/Car_Parking/trippaging",
		type: "get", //send it through get method
		data: {
			pageIndex: index
		},
		success: function(data) {
			var row = document.getElementById("content-trip");
			row.innerHTML = data;
			document.getElementById("currentPage".concat(index)).style.backgroundColor = "#a7fff9";
		},
		error: function(xhr) {
			//Do Something to handle error
		}
	});

}

function pagingTrip(pageNum) {
	var index = document.getElementById("currentPage".concat(pageNum)).textContent;
	var maxPage = document.getElementById("maxPage").value;
	$.ajax({
		url: "/Car_Parking/trippaging",
		type: "get", //send it through get method
		data: {
			pageIndex: index
		},
		success: function(data) {
			var row = document.getElementById("content-trip");
			row.innerHTML = data;
			document.getElementById("currentPage".concat(pageNum)).style.backgroundColor = "#a7fff9";
		},
		error: function(xhr) {
			//Do Something to handle error
		}
	});
}

function btnSearchPreviousTrip() {
	var index = document.getElementById("pageSearchCurrentIndex").value;
	var search = document.getElementById("search").value;
	var date = document.getElementById("searchdate").value;
	var dateTo = document.getElementById("searchdateTo").value;
	if (index > 1) {
		index--;
	}
	$.ajax({
		url: "/Car_Parking/pagingsearchtrip",
		type: "get", //send it through get method
		data: {
			pageIndex: index,
			esearch: search,
			edate: date,
			dateTo: dateTo,
		},
		success: function(data) {
			var row = document.getElementById("tripcontent-search");
			row.innerHTML = data;
		},
		error: function(xhr) {
			//Do Something to handle error
		}
	});

}


function btnSearchNextTrip() {
	var maxPage = document.getElementById("maxSearchPage").value;
	var index = document.getElementById("pageSearchCurrentIndex").value;
	var search = document.getElementById("search").value;
	var date = document.getElementById("searchdate").value;
	var dateTo = document.getElementById("searchdateTo").value;
	if (index < maxPage) {
		index++;
	}
	$.ajax({
		url: "/Car_Parking/pagingsearchtrip",
		type: "get", //send it through get method
		data: {
			pageIndex: index,
			esearch: search,
			edate: date,
			dateTo: dateTo,
		},
		success: function(data) {
			var row = document.getElementById("tripcontent-search");
			row.innerHTML = data;
		},
		error: function(xhr) {
			//Do Something to handle error
		}
	});

}

function pagingTripSearch(pageNum) {
	var index = document.getElementById("currentSearchPage".concat(pageNum)).textContent;
	var search = document.getElementById("search").value;
	var date = document.getElementById("searchdate").value;
	var dateTo = document.getElementById("searchdateTo").value;
	$.ajax({
		url: "/Car_Parking/pagingsearchtrip",
		type: "get", //send it through get method
		data: {
			pageIndex: index,
			esearch: search,
			edate: date,
			dateTo: dateTo,
		},
		success: function(data) {
			var row = document.getElementById("tripcontent-search");
			row.innerHTML = data;
		},
		error: function(xhr) {
			//Do Something to handle error
		}
	});
}

// END TRIP _ DO QUANG QUY


// START TICKET _ NGUYEN TRONG DUY AN
function searchTicket() {
	var search = document.getElementById("search").value;
	var filterby = document.getElementById("filterby").value;
	$.ajax({
		url: "/Car_Parking/searchticket",
		type: "get", //send it through get method
		data: {
			ename: search,
			eselect: filterby,
		},
		success: function(data) {
			var row = document.getElementById("content-search-ticket");
			row.innerHTML = data;
		},
		error: function(xhr) {
			//Do Something to handle error
		}
	});
}

function pagingTicket(pageNum) {

	var index = document.getElementById("currentPage".concat(pageNum)).textContent;
	var maxPage = document.getElementById("maxPage").value;
	for (let i = 1; i <= maxPage; i++) {
		document.getElementById("currentPage".concat(i)).style.backgroundColor = "White";
	}
	$.ajax({
		url: "/Car_Parking/pagingticket",
		type: "get", //send it through get method
		data: {
			pageIndex: index
		},
		success: function(data) {
			var row = document.getElementById("content-ticket");
			row.innerHTML = data;
			document.getElementById("currentPage".concat(pageNum)).style.backgroundColor = "#a7fff9";
		},
		error: function(xhr) {
			//Do Something to handle error
		}
	});
}

function btnTicketPrevious() {
	var maxPage = document.getElementById("maxPage").value;
	var index = document.getElementById("pageCurrentIndex").value;
	if (index > 1) {
		index--;
	}
	for (let i = 1; i <= maxPage; i++) {
		document.getElementById("currentPage".concat(i)).style.backgroundColor = "White";
	}
	$.ajax({
		url: "/Car_Parking/pagingticket",
		type: "get", //send it through get method
		data: {
			pageIndex: index,
		},
		success: function(data) {
			var row = document.getElementById("content-ticket");
			row.innerHTML = data;
			document.getElementById("currentPage".concat(index)).style.backgroundColor = "#a7fff9";
		},
		error: function(xhr) {
			//Do Something to handle error
		}
	});

}

function btnTicketNext() {
	var maxPage = document.getElementById("maxPage").value;
	var index = document.getElementById("pageCurrentIndex").value;
	if (index < maxPage) {
		index++;
	}
	for (let i = 1; i <= maxPage; i++) {
		document.getElementById("currentPage".concat(i)).style.backgroundColor = "White";
	}

	$.ajax({
		url: "/Car_Parking/pagingticket",
		type: "get", //send it through get method
		data: {
			pageIndex: index
		},
		success: function(data) {
			var row = document.getElementById("content-ticket");
			row.innerHTML = data;
			document.getElementById("currentPage".concat(index)).style.backgroundColor = "#a7fff9";
		},
		error: function(xhr) {
			//Do Something to handle error
		}
	});

}

function pagingTicketSearch(pageNum) {

	var index = document.getElementById("currentSearchPage".concat(pageNum)).textContent;
	var search = document.getElementById("searchValueTicket").value;
	var select = document.getElementById("selectValueTicket").value;
	$.ajax({
		url: "/Car_Parking/pagingsearchticket",
		type: "get", //send it through get method
		data: {
			pageIndex: index,
			ename: search,
			eselect: select,
		},
		success: function(data) {
			var row = document.getElementById("content-search-ticket");
			row.innerHTML = data;
		},
		error: function(xhr) {
			//Do Something to handle error
		}
	});
}

function btnSearchTicketPrevious() {
	var index = document.getElementById("pageSearchCurrentIndex").value;
	var search = document.getElementById("searchValueTicket").value;
	var select = document.getElementById("selectValueTicket").value;
	if (index > 1) {
		index--;
	}
	$.ajax({
		url: "/Car_Parking/pagingsearchticket",
		type: "get", //send it through get method
		data: {
			pageIndex: index,
			ename: search,
			eselect: select,
		},
		success: function(data) {
			var row = document.getElementById("content-search-ticket");
			row.innerHTML = data;
		},
		error: function(xhr) {
			//Do Something to handle error
		}
	});

}


function btnSearchTicketNext() {
	var maxPage = document.getElementById("maxSearchPage").value;
	var index = document.getElementById("pageSearchCurrentIndex").value;
	var search = document.getElementById("searchValueTicket").value;
	var select = document.getElementById("selectValueTicket").value;
	if (index < maxPage) {
		index++;
	}
	$.ajax({
		url: "/Car_Parking/pagingsearchticket",
		type: "get", //send it through get method
		data: {
			pageIndex: index,
			ename: search,
			eselect: select,
		},
		success: function(data) {
			var row = document.getElementById("content-search-ticket");
			row.innerHTML = data;
		},
		error: function(xhr) {
			//Do Something to handle error
		}
	});

}
// END TICKET _ NGUYEN TRONG DUY AN

// START OFFICE _ NGUYEN TRONG DUY AN

function searchOfficeBooking() {
	var search = document.getElementById("searchOffice").value;
	var filterby = document.getElementById("filterbyOffice").value;
	$.ajax({
		url: "/Car_Parking/searchoffice",
		type: "get", //send it through get method
		data: {
			enameOffice: search,
			eselectOffice: filterby,
		},
		success: function(data) {
			var row = document.getElementById("content-search-office");
			row.innerHTML = data;
		},
		error: function(xhr) {
			//Do Something to handle error
		}
	});
}

function pagingOfficeBooking(pageNum) {

	var index = document.getElementById("currentPage".concat(pageNum)).textContent;
	var maxPage = document.getElementById("maxPage").value;
	for (let i = 1; i <= maxPage; i++) {
		document.getElementById("currentPage".concat(i)).style.backgroundColor = "White";
	}
	$.ajax({
		url: "/Car_Parking/pagingofficebooking",
		type: "get", //send it through get method
		data: {
			pageIndex: index
		},
		success: function(data) {
			var row = document.getElementById("content-office-booking");
			row.innerHTML = data;
			document.getElementById("currentPage".concat(pageNum)).style.backgroundColor = "#a7fff9";
		},
		error: function(xhr) {
			//Do Something to handle error
		}
	});
}

function btnBookingPrevious() {
	var maxPage = document.getElementById("maxPage").value;
	var index = document.getElementById("pageOfficeCurrentIndex").value;
	if (index > 1) {
		index--;
	}
	for (let i = 1; i <= maxPage; i++) {
		document.getElementById("currentPage".concat(i)).style.backgroundColor = "White";
	}
	$.ajax({
		url: "/Car_Parking/pagingofficebooking",
		type: "get", //send it through get method
		data: {
			pageIndex: index,
		},
		success: function(data) {
			var row = document.getElementById("content-office-booking");
			row.innerHTML = data;
			document.getElementById("currentPage".concat(index)).style.backgroundColor = "#a7fff9";
		},
		error: function(xhr) {
			//Do Something to handle error
		}
	});

}

function btnOfficeNext() {
	var maxPage = document.getElementById("maxPage").value;
	var index = document.getElementById("pageOfficeCurrentIndex").value;
	if (index < maxPage) {
		index++;
	}
	for (let i = 1; i <= maxPage; i++) {
		document.getElementById("currentPage".concat(i)).style.backgroundColor = "White";
	}

	$.ajax({
		url: "/Car_Parking/pagingofficebooking",
		type: "get", //send it through get method
		data: {
			pageIndex: index
		},
		success: function(data) {
			var row = document.getElementById("content-office-booking");
			row.innerHTML = data;
			document.getElementById("currentPage".concat(index)).style.backgroundColor = "#a7fff9";
		},
		error: function(xhr) {
			//Do Something to handle error
		}
	});

}

function pagingOfficeSearch(pageNum) {

	var index = document.getElementById("currentSearchPage".concat(pageNum)).textContent;
	var search = document.getElementById("searchValue").value;
	var select = document.getElementById("selectValue").value;
	$.ajax({
		url: "/Car_Parking/pagingsearchoffice",
		type: "get", //send it through get method
		data: {
			pageIndex: index,
			enameOffice: search,
			eselectOffice: select,
		},
		success: function(data) {
			var row = document.getElementById("content-search-office");
			row.innerHTML = data;
		},
		error: function(xhr) {
			//Do Something to handle error
		}
	});
}

function btnSearchOfficePrevious() {
	var index = document.getElementById("pageSearchCurrentIndex").value;
	var search = document.getElementById("searchValue").value;
	var select = document.getElementById("selectValue").value;
	if (index > 1) {
		index--;
	}
	$.ajax({
		url: "/Car_Parking/pagingsearchoffice",
		type: "get", //send it through get method
		data: {
			pageIndex: index,
			enameOffice: search,
			eselectOffice: select,
		},
		success: function(data) {
			var row = document.getElementById("content-search-office");
			row.innerHTML = data;
		},
		error: function(xhr) {
			//Do Something to handle error
		}
	});

}


function btnSearchOfficeNext() {
	var maxPage = document.getElementById("maxSearchPage").value;
	var index = document.getElementById("pageSearchCurrentIndex").value;
	var search = document.getElementById("searchValue").value;
	var select = document.getElementById("selectValue").value;
	if (index < maxPage) {
		index++;
	}
	$.ajax({
		url: "/Car_Parking/pagingsearchoffice",
		type: "get", //send it through get method
		data: {
			pageIndex: index,
			enameOffice: search,
			eselectOffice: select,
		},
		success: function(data) {
			var row = document.getElementById("content-search-office");
			row.innerHTML = data;
		},
		error: function(xhr) {
			//Do Something to handle error
		}
	});

}

// END OFFICE _ NGUYEN TRONG DUY AN

// START form _ Hai Ha
function searchPark() {
	var search = document.getElementById("search").value;
	var select = document.getElementById("filterby").value;
	$.ajax({
		url: "/Car_Parking/searchParking",
		type: "post", //send it through get method
		data: {
			ename: search,
			eselect: select,
		},
		success: function(data) {
			var row = document.getElementById("content-search");
			row.innerHTML = data;
		},
		//	error: function(xhr) {
		//Do Something to handle error
		//	}
	});
}

function searchCar() {
	var search = document.getElementById("search").value;
	var select = document.getElementById("filterby").value;
	$.ajax({
		url: "/Car_Parking/searchCar",
		type: "post", //send it through get method
		data: {
			ename: search,
			eselect: select,
		},
		success: function(data) {
			var row = document.getElementById("content-search");
			row.innerHTML = data;
		},
		//	error: function(xhr) {
		//Do Something to handle error
		//	}
	});
}

function pagingCar(pageNum) {

	var index = document.getElementById("currentPage".concat(pageNum)).textContent;
	$.ajax({
		url: "/Car_Parking/pagingCar",
		type: "get", //send it through get method
		data: {
			pageIndex: index
		},
		success: function(data) {
			var row = document.getElementById("abc");
			row.innerHTML = data;
		},
		error: function(xhr) {
			//Do Something to handle error
		}
	});
}

function btnPreviousCar() {
	var index = document.getElementById("pageCurrentIndex").value;
	if (index > 1) {
		index--;
	}
	$.ajax({
		url: "/Car_Parking/pagingCar",
		type: "get", //send it through get method
		data: {
			pageIndex: index,
		},
		success: function(data) {
			var row = document.getElementById("abc");
			row.innerHTML = data;
		},
		error: function(xhr) {
			//Do Something to handle error
		}
	});

}

function btnNextCar() {
	var maxPage = document.getElementById("maxPage").value;
	var index = document.getElementById("pageCurrentIndex").value;
	if (index < maxPage) {
		index++;
	}
	$.ajax({
		url: "/Car_Parking/pagingCar",
		type: "get", //send it through get method
		data: {
			pageIndex: index
		},
		success: function(data) {
			var row = document.getElementById("abc");
			row.innerHTML = data;
		},
		error: function(xhr) {
			//Do Something to handle error
		}
	});

}

function pagingCarSearch(pageNum) {

	var index = document.getElementById("currentSearchPage".concat(pageNum)).textContent;
	var search = document.getElementById("searchValue").value;
	var select = document.getElementById("selectValue").value;
	$.ajax({
		url: "/Car_Parking/pagingSearchCar",
		type: "get", //send it through get method
		data: {
			pageIndex: index,
			esearch: search,
			eselect: select,
		},
		success: function(data) {
			var row = document.getElementById("content-search");
			row.innerHTML = data;
		},
		error: function(xhr) {
			//Do Something to handle error
		}
	});
}

function pagingParkSearch(pageNum) {

	var index = document.getElementById("currentSearchPage".concat(pageNum)).textContent;
	var search = document.getElementById("searchValue").value;
	var select = document.getElementById("selectValue").value;
	$.ajax({
		url: "/Car_Parking/pagingSearchPark",
		type: "get", //send it through get method
		data: {
			pageIndex: index,
			esearch: search,
			eselect: select,
		},
		success: function(data) {
			var row = document.getElementById("content-search");
			row.innerHTML = data;
		},
		error: function(xhr) {
			//Do Something to handle error
		}
	});
}

function btnSearchCarPrevious() {
	var index = document.getElementById("pageSearchCurrentIndex").value;
	var search = document.getElementById("searchValue").value;
	var select = document.getElementById("selectValue").value;
	if (index > 1) {
		index--;
	}
	$.ajax({
		url: "/Car_Parking/pagingSearchCar",
		type: "get", //send it through get method
		data: {
			pageIndex: index,
			esearch: search,
			eselect: select,
		},
		success: function(data) {
			var row = document.getElementById("content-search");
			row.innerHTML = data;
		},
		error: function(xhr) {
			//Do Something to handle error
		}
	});

}


function btnSearchCarNext() {
	var maxPage = document.getElementById("maxSearchPage").value;
	var index = document.getElementById("pageSearchCurrentIndex").value;
	var search = document.getElementById("searchValue").value;
	var select = document.getElementById("selectValue").value;
	if (index < maxPage) {
		index++;
	}
	$.ajax({
		url: "/Car_Parking/pagingSearchCar",
		type: "get", //send it through get method
		data: {
			pageIndex: index,
			esearch: search,
			eselect: select,
		},
		success: function(data) {
			var row = document.getElementById("content-search");
			row.innerHTML = data;
		},
		error: function(xhr) {
			//Do Something to handle error
		}
	});

}

function pagingPark(pageNum) {

	var index = document.getElementById("currentPage".concat(pageNum)).textContent;
	$.ajax({
		url: "/Car_Parking/pagingPark",
		type: "get", //send it through get method
		data: {
			pageIndex: index
		},
		success: function(data) {
			var row = document.getElementById("abc");
			row.innerHTML = data;
		},
		error: function(xhr) {
			//Do Something to handle error
		}
	});
}

function btnPreviousPark() {
	var index = document.getElementById("pageCurrentIndex").value;
	if (index > 1) {
		index--;
	}
	$.ajax({
		url: "/Car_Parking/pagingPark",
		type: "get", //send it through get method
		data: {
			pageIndex: index,
		},
		success: function(data) {
			var row = document.getElementById("abc");
			row.innerHTML = data;
		},
		error: function(xhr) {
			//Do Something to handle error
		}
	});

}

function btnNextPark() {
	var maxPage = document.getElementById("maxPage").value;
	var index = document.getElementById("pageCurrentIndex").value;
	if (index < maxPage) {
		index++;
	}
	$.ajax({
		url: "/Car_Parking/pagingPark",
		type: "get", //send it through get method
		data: {
			pageIndex: index
		},
		success: function(data) {
			var row = document.getElementById("abc");
			row.innerHTML = data;
		},
		error: function(xhr) {
			//Do Something to handle error
		}
	});

}

function btnSearchParkPrevious() {
	var index = document.getElementById("pageSearchCurrentIndex").value;
	var search = document.getElementById("searchValue").value;
	var select = document.getElementById("selectValue").value;
	if (index > 1) {
		index--;
	}
	$.ajax({
		url: "/Car_Parking/pagingSearchPark",
		type: "get", //send it through get method
		data: {
			pageIndex: index,
			esearch: search,
			eselect: select,
		},
		success: function(data) {
			var row = document.getElementById("content-search");
			row.innerHTML = data;
		},
		error: function(xhr) {
			//Do Something to handle error
		}
	});

}


function btnSearchParkNext() {
	var maxPage = document.getElementById("maxSearchPage").value;
	var index = document.getElementById("pageSearchCurrentIndex").value;
	var search = document.getElementById("searchValue").value;
	var select = document.getElementById("selectValue").value;
	if (index < maxPage) {
		index++;
	}
	$.ajax({
		url: "/Car_Parking/pagingSearchPark",
		type: "get", //send it through get method
		data: {
			pageIndex: index,
			esearch: search,
			eselect: select,
		},
		success: function(data) {
			var row = document.getElementById("content-search");
			row.innerHTML = data;
		},
		error: function(xhr) {
			//Do Something to handle error
		}
	});

}


// End of Hai Ha
