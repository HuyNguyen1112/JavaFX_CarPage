package Car.fxml;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class CarMain extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			 // Đọc file fxml và vẽ giao diện.
	         Parent root = FXMLLoader.load(getClass()
	                   .getResource("LoginGUI.fxml"));
			
			// Thêm layout vào Scene
			Scene scene = new Scene(root);
			
			// Thêm Scene vào Stage
			primaryStage.setScene(scene);
			primaryStage.setTitle("Login");
			
			// Hiển thị Stage
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
