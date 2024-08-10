package Car.fxml;

public class Cars {
	private int id;
	private String carName;
	private String type;
	private String power;
	private String price;
	private String detail;
	private String imgPath;
	private String brand;
	
	public Cars() {}
	public Cars(String carName, String brand, String price, String type, String power, String detail, String imgPath) {
		this.carName = carName;
		this.brand = brand;
		this.type = type;
		this.power = power;
		this.price = price;
		this.detail = detail;
		this.imgPath = imgPath;
	}
	public Cars(int id, String carName, String brand, String price, String type, String power, String detail, String imgPath) {
		this.id=id;
		this.carName = carName;
		this.brand = brand;
		this.type = type;
		this.power = power;
		this.price = price;
		this.detail = detail;
		this.imgPath = imgPath;
	}
	public Cars(int id) {
		this.id=id;
	}
	
	public int getID() {
		return id;
	}

	public void setID(int id) {
		this.id = id;
	}

	public String getCarName() {
		return carName;
	}

	public void setCarName(String carName) {
		this.carName = carName;
	}

	public String getPower() {
		return power;
	}

	public void setPower(String power) {
		this.power = power;
	}
	
	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}
	
	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;;
	}
	
	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
}
