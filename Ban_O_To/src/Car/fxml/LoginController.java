package Car.fxml;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {
	@FXML
	private Label errorL;
	
	@FXML
	private TextField emailTf;
	
	@FXML
	private TextField passwordTf;
	
	@FXML
	private String fullname=null;
	
	@FXML
	public void onClickLoginBtn() {
		
		// Lấy user ứng với email trong csdl
		User user = UserDAO.checkUser(emailTf.getText(), passwordTf.getText());
		if(user != null) {
			fullname=user.getFullname();
			// Đóng cửa sổ đăng ký
			emailTf.getScene().getWindow().hide();
			
			// Mở ra cửa sổ Home và truyền thông tin User đăng ký cho cửa sổ Home
			goHomeScreen(new User(emailTf.getText(), fullname));
			
			
		}else {
			errorL.setText("Email hoặc mật khẩu sai");
		}
	}
	
	public void goHomeScreen(User loginedUser) {
		try {
			 FXMLLoader fxmlLoader = new FXMLLoader(getClass()
	                   .getResource("HomeGUI.fxml"));	
			
			 // Đọc file fxml và vẽ giao diện.
	         Parent root = fxmlLoader.load();
	         
	         // Lấy ra HomeController để truyền dữ liệu vào
	         HomeController hc = fxmlLoader.getController();
	         hc.setLoginedUser(loginedUser);
			
			// Thêm layout vào Scene
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("Home.css").toExternalForm());
			// Thêm Scene vào Stage
			Stage homeStage = new Stage();
			homeStage.setScene(scene);
			
			// Hiển thị Stage
			homeStage.setTitle("Home");
			homeStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
