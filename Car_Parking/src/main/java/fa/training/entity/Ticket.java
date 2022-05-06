package fa.training.entity;

import java.sql.Time;

public class Ticket {
	private int ticketID;
	private Time bookingTime;
	private String customerName;
	private String licensePlate;
	private int tripID;
	private String tripName;
	
	public Ticket() {
		// TODO Auto-generated constructor stub
	}

	public Ticket(int ticketID, Time bookingTime, String customerName, String licensePlate, int tripID) {
		super();
		this.ticketID = ticketID;
		this.bookingTime = bookingTime;
		this.customerName = customerName;
		this.licensePlate = licensePlate;
		this.tripID = tripID;
	}
	
	public Ticket(int ticketID, Time bookingTime, String customerName, String licensePlate, int tripID,
			String tripName) {
		super();
		this.ticketID = ticketID;
		this.bookingTime = bookingTime;
		this.customerName = customerName;
		this.licensePlate = licensePlate;
		this.tripID = tripID;
		this.tripName = tripName;
	}

	public Time getBookingTime() {
		return bookingTime;
	}

	public void setBookingTime(Time bookingTime) {
		this.bookingTime = bookingTime;
	}

	public int getTicketID() {
		return ticketID;
	}

	public void setTicketID(int ticketID) {
		this.ticketID = ticketID;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getLicensePlate() {
		return licensePlate;
	}

	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}

	public int getTripID() {
		return tripID;
	}

	public void setTripID(int tripID) {
		this.tripID = tripID;
	}

	public String getTripName() {
		return tripName;
	}

	public void setTripName(String tripName) {
		this.tripName = tripName;
	}
	
	
}

