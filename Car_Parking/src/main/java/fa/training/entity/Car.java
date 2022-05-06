package fa.training.entity;

public class Car {
	String licensePlate;
	String carColor;
	String carType;
	String company;
	int parkID;
	String parkName;
	
	public Car() {
		// TODO Auto-generated constructor stub
	}
	public Car(String licensePlate, String carColor, String carType, String company, int parkID) {
		super();
		this.licensePlate = licensePlate;
		this.carColor = carColor;
		this.carType = carType;
		this.company = company;
		this.parkID = parkID;
	}
	
	public Car(String licensePlate, String carColor, String carType, String company, int parkID, String parkName) {
		super();
		this.licensePlate = licensePlate;
		this.carColor = carColor;
		this.carType = carType;
		this.company = company;
		this.parkID = parkID;
		this.parkName = parkName;
	}
	public String getLicensePlate() {
		return licensePlate;
	}
	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}
	public String getCarColor() {
		return carColor;
	}
	public void setCarColor(String carColor) {
		this.carColor = carColor;
	}
	public String getCarType() {
		return carType;
	}
	public void setCarType(String carType) {
		this.carType = carType;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public int getParkID() {
		return parkID;
	}
	public void setParkID(int parkID) {
		this.parkID = parkID;
	}
	public String getParkName() {
		return parkName;
	}
	public void setParkName(String parkName) {
		this.parkName = parkName;
	}
	
	
	
}
