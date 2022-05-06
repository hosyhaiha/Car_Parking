package fa.training.entity;

import java.sql.Date;
import java.sql.Time;

public class Trip {
	private int tripID;
	private int bookedTicketNumber;
	private String carType;
	private Date departureDate;
	private Time departureTime;
	private String destination;
	private String driver;
	private int maximumOnlineTicketNumber;

	public Trip() {
	}

	public Trip(int tripID, int bookedTicketNumber, String carType, Date departureDate, Time departureTime,
			String destination, String driver, int maximumOnlineTicketNumber) {
		super();
		this.tripID = tripID;
		this.bookedTicketNumber = bookedTicketNumber;
		this.carType = carType;
		this.departureDate = departureDate;
		this.departureTime = departureTime;
		this.destination = destination;
		this.driver = driver;
		this.maximumOnlineTicketNumber = maximumOnlineTicketNumber;
	}

	public int getTripID() {
		return tripID;
	}

	public void setTripID(int tripID) {
		this.tripID = tripID;
	}

	public int getBookedTicketNumber() {
		return bookedTicketNumber;
	}

	public void setBookedTicketNumber(int bookedTicketNumber) {
		this.bookedTicketNumber = bookedTicketNumber;
	}

	public String getCarType() {
		return carType;
	}

	public void setCarType(String carType) {
		this.carType = carType;
	}

	public Date getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(Date departureDate) {
		this.departureDate = departureDate;
	}

	public Time getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(Time departureTime) {
		this.departureTime = departureTime;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public int getMaximumOnlineTicketNumber() {
		return maximumOnlineTicketNumber;
	}

	public void setMaximumOnlineTicketNumber(int maximumOnlineTicketNumber) {
		this.maximumOnlineTicketNumber = maximumOnlineTicketNumber;
	}
}

