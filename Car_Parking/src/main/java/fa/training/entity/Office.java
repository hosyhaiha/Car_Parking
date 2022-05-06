package fa.training.entity;

import java.sql.Date;

public class Office {
	private int officeID;
	private Date endContractDeadline;
	private String officeName;
	private String officePhone;
	private String officePlace;
	private int officePrice;
	private Date startContractDeadline;
	private int tripID;
	private String tripName;
	public Office() {
		// TODO Auto-generated constructor stub
	}

	public Office(int officeID, Date endContractDeadline, String officeName, String officePhone, String officePlace,
			int officePrice, Date startContractDeadline, int tripID) {
		super();
		this.officeID = officeID;
		this.endContractDeadline = endContractDeadline;
		this.officeName = officeName;
		this.officePhone = officePhone;
		this.officePlace = officePlace;
		this.officePrice = officePrice;
		this.startContractDeadline = startContractDeadline;
		this.tripID = tripID;
	}
	
	public Office(int officeID, Date endContractDeadline, String officeName, String officePhone, String officePlace,
			int officePrice, Date startContractDeadline, int tripID, String tripName) {
		super();
		this.officeID = officeID;
		this.endContractDeadline = endContractDeadline;
		this.officeName = officeName;
		this.officePhone = officePhone;
		this.officePlace = officePlace;
		this.officePrice = officePrice;
		this.startContractDeadline = startContractDeadline;
		this.tripID = tripID;
		this.tripName = tripName;
	}

	public int getOfficeID() {
		return officeID;
	}

	public void setOfficeID(int officeID) {
		this.officeID = officeID;
	}

	public Date getEndContractDeadline() {
		return endContractDeadline;
	}

	public void setEndContractDeadline(Date endContractDeadline) {
		this.endContractDeadline = endContractDeadline;
	}

	public String getOfficeName() {
		return officeName;
	}

	public void setOfficeName(String officeName) {
		this.officeName = officeName;
	}

	public String getOfficePhone() {
		return officePhone;
	}

	public void setOfficePhone(String officePhone) {
		this.officePhone = officePhone;
	}

	public String getOfficePlace() {
		return officePlace;
	}

	public void setOfficePlace(String officePlace) {
		this.officePlace = officePlace;
	}

	public int getOfficePrice() {
		return officePrice;
	}

	public void setOfficePrice(int officePrice) {
		this.officePrice = officePrice;
	}

	public Date getStartContractDeadline() {
		return startContractDeadline;
	}

	public void setStartContractDeadline(Date startContractDeadline) {
		this.startContractDeadline = startContractDeadline;
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
