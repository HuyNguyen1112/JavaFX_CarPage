package Car.fxml;

public class User {
	private String email;
	private String fullname;
	private String imgPath;
	
	public User() {}
	
	public User(String email, String fullname) {
		this.email = email;
		this.fullname = fullname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return email + "-" + fullname;
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
	
	
	
}
