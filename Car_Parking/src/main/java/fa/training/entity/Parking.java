package fa.training.entity;

public class Parking {
	private int parkID;
	private int parkArea;
	private String parkName;
	private String parkPlace;
	private int parkPrice;
	private String parkStatus;

	public Parking() {
		// TODO Auto-generated constructor stub
	}

	public Parking(int parkID, int parkArea, String parkName, String parkPlace, int parkPrice, String parkStatus) {
		super();
		this.parkID = parkID;
		this.parkArea = parkArea;
		this.parkName = parkName;
		this.parkPlace = parkPlace;
		this.parkPrice = parkPrice;
		this.parkStatus = parkStatus;
	}

	public Parking(int parkArea, String parkName, String parkPlace, int parkPrice, String parkStatus) {
		super();
		this.parkArea = parkArea;
		this.parkName = parkName;
		this.parkPlace = parkPlace;
		this.parkPrice = parkPrice;
		this.parkStatus = parkStatus;
	}

	public int getParkID() {
		return parkID;
	}

	public void setParkID(int parkID) {
		this.parkID = parkID;
	}

	public int getParkArea() {
		return parkArea;
	}

	public void setParkArea(int parkArea) {
		this.parkArea = parkArea;
	}

	public String getParkName() {
		return parkName;
	}

	public void setParkName(String parkName) {
		this.parkName = parkName;
	}

	public String getParkPlace() {
		return parkPlace;
	}

	public void setParkPlace(String parkPlace) {
		this.parkPlace = parkPlace;
	}

	public int getParkPrice() {
		return parkPrice;
	}

	public void setParkPrice(int parkPrice) {
		this.parkPrice = parkPrice;
	}

	public String getParkStatus() {
		return parkStatus;
	}

	public void setParkStatus(String parkStatus) {
		this.parkStatus = parkStatus;
	}

}


