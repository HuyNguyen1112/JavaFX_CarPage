package Car.fxml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class HomeController implements Initializable{
	
	@FXML
	private TableView<Cars> carsListTV;
	
	@FXML
	private TableColumn<Cars, String> carNameCol;
	
	@FXML
	private TableColumn<Cars, String> brandCol;
	@FXML
	private VBox imgLink;
	@FXML
	private HBox tools;
	@FXML
	private TextField imgTF;
	@FXML
	private TextField carNameTF;
	@FXML
	private TextField brandTF;
	@FXML
	private TextField priceTF;
	@FXML
	private TextField typeTF;
	@FXML
	private TextField powerTF;
	@FXML
	private TextArea detailTF;
	@FXML
	private Label welcomeMsg;
	@FXML
	private Label carNameLB;
	@FXML
	private Label brandLB;
	@FXML
	private Label priceLB;
	@FXML
	private Label typeLB;
	@FXML
	private Label powerLB;
	@FXML
	private Label detailLB;
	@FXML
	private ImageView imgView;
	@FXML
	private ImageView testView;
	@FXML
	private Button change;
	@FXML
	private Button add;
	@FXML
	private Button delete;
	@FXML
	private Button cancel;
	@FXML
	private User loginedUser;
	private int id;
	private String img;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		// Nội dung khi mở màn hình Home
		Platform.runLater(()->{
			int role= UserDAO.getUserRole(loginedUser.getEmail());
			welcomeMsg.setText("Xin chào " + loginedUser.getFullname());
			carNameTF.setVisible(false);
			carNameTF.setManaged(false);
			brandTF.setVisible(false);
			brandTF.setManaged(false);
			priceTF.setVisible(false);
			priceTF.setManaged(false);
			typeTF.setVisible(false);
			typeTF.setManaged(false);
			powerTF.setVisible(false);
			powerTF.setManaged(false);
			detailTF.setVisible(false);
			detailTF.setManaged(false);
			imgLink.setVisible(false);
			imgLink.setManaged(false);
			testView.setVisible(false);
			testView.setManaged(false);
			// Ẩn HBox nếu role khác 0
			if (role != 0) {
	            tools.setVisible(false); 
	            tools.setManaged(false);
	        }else {
	        	//nếu chưa ấn chọn xe thì sửa và xóa chưa hiển thị.
	        	change.setVisible(false);
				change.setManaged(false);
				delete.setVisible(false);
				delete.setManaged(false);
				cancel.setVisible(false);
				cancel.setManaged(false);
	        }
			
		});
		
//		// Bat cap
		carNameCol.setCellValueFactory(new PropertyValueFactory<Cars,String>("carName"));

		brandCol.setCellValueFactory(new PropertyValueFactory<Cars, String>("brand"));
		
		// Lấy ds User từ CSDL
		List<Cars> listCars = CarDAO.listAllCars();
		ObservableList<Cars> obsList =	FXCollections.observableArrayList(listCars);
		// Đưa vào bảng tableview
		carsListTV.setItems(obsList);
		
		
	}
//	
//	@FXML
	public void onClickRow() {
		change.setVisible(true);
		change.setManaged(true);
		delete.setVisible(true);
		delete.setManaged(true);
		Cars selectedCar = carsListTV.getSelectionModel().getSelectedItem();
		carNameLB.setText(selectedCar.getCarName());
		brandLB.setText(selectedCar.getBrand());
		priceLB.setText(selectedCar.getPrice() + "$");
		typeLB.setText(selectedCar.getType());
		powerLB.setText(selectedCar.getPower() + "hp");
		detailLB.setText(selectedCar.getDetail());
		id=selectedCar.getID();
		img=String.valueOf(selectedCar.getImgPath());
		// Dua anh vao ImageView
		FileInputStream input=null;
		try {
			if(!selectedCar.getImgPath().equals("")) {
				input = new FileInputStream(selectedCar.getImgPath());
				Image image = new Image(input);
				imgView.setImage(image);
				//đóng input ngay khi dùng xong để có thể delete file trong chức năng xóa
				try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
			}else {
				imgView.setImage(null);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	@FXML 
	public void addClick() {
		if(!carNameTF.isVisible()) {
			carsListTV.setDisable(true); //vô hiệu hóa danh sách trong quá trỉnh thêm
			// hiện các textField và ẩn các Label
			imgLink.setVisible(true);
			imgLink.setManaged(true);
			testView.setVisible(true);
			testView.setManaged(true);
			testView.setImage(null);
			imgView.setVisible(false);
			imgView.setManaged(false);
			carNameTF.setVisible(true);
			carNameTF.setManaged(true);
			brandTF.setVisible(true);
			brandTF.setManaged(true);
			priceTF.setVisible(true);
			priceTF.setManaged(true);
			typeTF.setVisible(true);
			typeTF.setManaged(true);
			powerTF.setVisible(true);
			powerTF.setManaged(true);
			detailTF.setVisible(true);
			detailTF.setManaged(true);
			carNameTF.setText("");
			brandTF.setText("");
			priceTF.setText("");
			typeTF.setText("");
			powerTF.setText("");
			detailTF.setText("");
			imgTF.setText("");
			carNameLB.setVisible(false);
			carNameLB.setManaged(false);
			brandLB.setVisible(false);
			brandLB.setManaged(false);
			priceLB.setVisible(false);
			priceLB.setManaged(false);
			typeLB.setVisible(false);
			typeLB.setManaged(false);
			powerLB.setVisible(false);
			powerLB.setManaged(false);
			detailLB.setVisible(false);
			detailLB.setManaged(false);
			change.setVisible(false);
			change.setManaged(false);
			delete.setVisible(false);
			delete.setManaged(false);
			cancel.setVisible(true);
			cancel.setManaged(true);
			add.setText("Lưu");
		//sau khi ấn lưu	
		}else {
			//kiểm tra texField tên có để trống không?
			if(carNameTF.getText().equals("")) {
				Alert alert = new Alert(AlertType.WARNING);
	        	alert.setTitle("ERROR!");
	        	alert.setContentText("Không được để trống tên xe!");
	        	alert.show();
	        	return;
			}
			if(!imgTF.getText().equals("")) {
				Path source=Paths.get(imgTF.getText());
				//kiểm tra trường hợp file có tồn tại trên máy.
				if (Files.exists(source)) {
					if(imgTF.getText().endsWith(".jpg")) {
						Path dest=Paths.get("img").toAbsolutePath().resolve(carNameTF.getText()+".jpg");
						try {
							byte[] data = Files.readAllBytes(source);
							//Tạo file ảnh "tên xe+.jpg" nếu chưa có, ghi dữ liệu từ file source lên file dest, nếu file đã tồn tại thì ghi đè dữ liệu lên dữ liệu cũ. 
				            Files.write(dest, data, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING); 
						} catch (Exception e) {
							// TODO: handle exception
						}
						imgTF.setText("img/"+carNameTF.getText()+".jpg");
					} else {
						Path dest=Paths.get("img").toAbsolutePath().resolve(carNameTF.getText()+".png");
						try {
							byte[] data = Files.readAllBytes(source);
				            Files.write(dest, data, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
						} catch (Exception e) {
							// TODO: handle exception
						}
						imgTF.setText("img/"+carNameTF.getText()+".png");
					}
		        } else {
		        	Alert alert = new Alert(AlertType.WARNING);
		        	alert.setTitle("ERROR!");
		        	alert.setContentText("Đường dẫn hình ảnh không tồn tại");
		        	alert.show();
		        	return;
		        }
			}
			carsListTV.setDisable(false);
			imgLink.setVisible(false);
			imgLink.setManaged(false);
			testView.setVisible(false);
			testView.setManaged(false);
			imgView.setVisible(true);
			imgView.setManaged(true);
			carNameLB.setVisible(true);
			carNameLB.setManaged(true);
			brandLB.setVisible(true);
			brandLB.setManaged(true);
			priceLB.setVisible(true);
			priceLB.setManaged(true);
			typeLB.setVisible(true);
			typeLB.setManaged(true);
			powerLB.setVisible(true);
			powerLB.setManaged(true);
			detailLB.setVisible(true);
			detailLB.setManaged(true);
			change.setVisible(true);
			change.setManaged(true);
			delete.setVisible(true);
			delete.setManaged(true);
			cancel.setVisible(false);
			cancel.setManaged(false);
			//Luu xe vao trong CSDL
			Cars car=new Cars(carNameTF.getText(),brandTF.getText(),priceTF.getText(),typeTF.getText(),powerTF.getText(),detailTF.getText(),imgTF.getText());
			boolean check= CarDAO.addCar(car);
			carNameCol.setCellValueFactory(new PropertyValueFactory<Cars,
					String>("carName"));
			brandCol.setCellValueFactory(
					new PropertyValueFactory<Cars, String>("brand"));
			// Lấy ds User từ CSDL
			List<Cars> listCars = CarDAO.listAllCars();
			ObservableList<Cars> obsList =	FXCollections.observableArrayList(listCars);
			carsListTV.setItems(obsList);
			//Hien thi xe moi luu sau khi an "Luu"
			for (Cars selectedCar : listCars) {
				if (selectedCar.getCarName().equals(car.getCarName())) {
					carsListTV.getSelectionModel().select(selectedCar);
					carNameLB.setText(selectedCar.getCarName());
					brandLB.setText(selectedCar.getBrand());
					priceLB.setText(selectedCar.getPrice() + "$");
					typeLB.setText(selectedCar.getType());
					powerLB.setText(selectedCar.getPower() + "hp");
					detailLB.setText(selectedCar.getDetail());
					id=selectedCar.getID();
					// Dua anh vao ImageView
					FileInputStream input=null;
					try {
						if(!selectedCar.getImgPath().equals("")) {
							input = new FileInputStream(selectedCar.getImgPath());
							Image image = new Image(input);
							imgView.setImage(image);
							try {
			                    input.close();
			                } catch (IOException e) {
			                    e.printStackTrace();
			                }
						}else {
							imgView.setImage(null);
						}
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				}
			}
			add.setText("Thêm");
			carNameTF.setVisible(false);
			carNameTF.setManaged(false);
			brandTF.setVisible(false);
			brandTF.setManaged(false);
			priceTF.setVisible(false);
			priceTF.setManaged(false);
			typeTF.setVisible(false);
			typeTF.setManaged(false);
			powerTF.setVisible(false);
			powerTF.setManaged(false);
			detailTF.setVisible(false);
			detailTF.setManaged(false);
		}
	}
	
	public void DeleteClick() {
		//hiển thị thông báo xóa xe
		Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Xác nhận xóa");
        alert.setHeaderText("Xóa xe " + carNameLB.getText());
        alert.setContentText("Bạn có chắc muốn xóa xe này ra khỏi danh sách không?");
        ButtonType yes = new ButtonType("Có", ButtonData.OK_DONE);
        ButtonType no = new ButtonType("Không", ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(yes,no);
        Optional<ButtonType> result = alert.showAndWait();
        //nếu ấn có, xe sẽ được xóa khỏi danh sách
        if (result.isPresent() && result.get() == yes) {
	    	Cars car=new Cars(id);
			boolean check= CarDAO.deleteCar(car);
			carNameCol.setCellValueFactory(new PropertyValueFactory<Cars,
					String>("carName"));
	
			brandCol.setCellValueFactory(
					new PropertyValueFactory<Cars, String>("brand"));
			// Lấy ds User từ CSDL
			List<Cars> listCars = CarDAO.listAllCars();
			ObservableList<Cars> obsList =	FXCollections.observableArrayList(listCars);
			// Đưa vào bảng tableview
			carsListTV.setItems(obsList);
			carNameLB.setText("");
			brandLB.setText("");
			priceLB.setText("");
			typeLB.setText("");
			powerLB.setText("");
			detailLB.setText("");
			imgView.setImage(null);
			delete.setVisible(false);
			delete.setManaged(false);
			change.setVisible(false);
			change.setManaged(false);
			//xóa file ảnh khỏi máy
			if(img.endsWith(".jpg")) {
				try {
					Files.deleteIfExists(Paths.get("img/"+carNameTF.getText()+".jpg"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				try {
					Files.deleteIfExists(Paths.get("img/"+carNameTF.getText()+".png"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		//nếu ấn không, quay trở lại, xe không bị xóa	
        } else {
            return;
        }
        
	}
	
	public void changeInforClick() {	
		if(!carNameTF.isVisible()) {
			carsListTV.setDisable(true);
			imgLink.setVisible(true);
			imgLink.setManaged(true);
			testView.setVisible(true);
			testView.setManaged(true);
			testView.setImage(imgView.getImage());
			imgView.setVisible(false);
			imgView.setManaged(false);
			carNameTF.setVisible(true);
			carNameTF.setManaged(true);
			brandTF.setVisible(true);
			brandTF.setManaged(true);
			priceTF.setVisible(true);
			priceTF.setManaged(true);
			typeTF.setVisible(true);
			typeTF.setManaged(true);
			powerTF.setVisible(true);
			powerTF.setManaged(true);
			detailTF.setVisible(true);
			detailTF.setManaged(true);
			imgTF.setText(img);
			carNameTF.setText(carNameLB.getText());
			brandTF.setText(brandLB.getText());
			priceTF.setText(priceLB.getText().substring(0, priceLB.getText().length()-1));//xóa kí tự $
			typeTF.setText(typeLB.getText());
			powerTF.setText(powerLB.getText().substring(0, powerLB.getText().length()-2));//xóa kí tự hp
			detailTF.setText(detailLB.getText());
			carNameLB.setVisible(false);
			carNameLB.setManaged(false);
			brandLB.setVisible(false);
			brandLB.setManaged(false);
			priceLB.setVisible(false);
			priceLB.setManaged(false);
			typeLB.setVisible(false);
			typeLB.setManaged(false);
			powerLB.setVisible(false);
			powerLB.setManaged(false);
			detailLB.setVisible(false);
			detailLB.setManaged(false);
			add.setVisible(false);
			add.setManaged(false);
			delete.setVisible(false);
			delete.setManaged(false);
			cancel.setVisible(true);
			cancel.setManaged(true);
			change.setText("Lưu");
		}else {
			if(carNameTF.getText().equals("")) {
				Alert alert = new Alert(AlertType.WARNING);
	        	alert.setTitle("ERROR!");
	        	alert.setContentText("Không được để trống tên xe!");
	        	alert.show();
	        	return;
			}
			if(!imgTF.getText().equals("")) {
				Path source=Paths.get(imgTF.getText());
				if (Files.exists(source)) {
					if(imgTF.getText().endsWith(".jpg")) {
						Path dest=Paths.get("img").toAbsolutePath().resolve(carNameTF.getText()+".jpg");
						try {
							byte[] data = Files.readAllBytes(source);
				            Files.write(dest, data, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
//							Files.copy(source, dest, StandardCopyOption.REPLACE_EXISTING);
						} catch (Exception e) {
							// TODO: handle exception
						}
						//ở sửa có thêm trường hợp đổi tên xe nên sau khi sử dụng câu lệnh Files.write ở trên có trường hợp file với tên cũ còn tồn tại, nên em dùng câu lệnh xóa file
						if(!carNameTF.getText().equals(carNameLB.getText())) {
							try {
								Files.deleteIfExists(Paths.get("img/"+carNameLB.getText()+".jpg"));
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						imgTF.setText("img/"+carNameTF.getText()+".jpg");
					} else {
						Path dest=Paths.get("img").toAbsolutePath().resolve(carNameTF.getText()+".png");
						try {
							byte[] data = Files.readAllBytes(source);
				            Files.write(dest, data, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
						} catch (Exception e) {
							// TODO: handle exception
						}
						if(!carNameTF.getText().equals(carNameLB.getText())) {
							try {
								Files.deleteIfExists(Paths.get("img/"+carNameLB.getText()+".png"));
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						imgTF.setText("img/"+carNameTF.getText()+".png");
					}
		        } else {
		        	Alert alert = new Alert(AlertType.WARNING);
		        	alert.setTitle("ERROR!");
		        	alert.setContentText("Đường dẫn hình ảnh không tồn tại");
		        	alert.show();
		        	return;
		        }
			}
			carsListTV.setDisable(false);
			imgLink.setVisible(false);
			imgLink.setManaged(false);
			testView.setVisible(false);
			testView.setManaged(false);
			imgView.setVisible(true);
			imgView.setManaged(true);
			carNameLB.setVisible(true);
			carNameLB.setManaged(true);
			brandLB.setVisible(true);
			brandLB.setManaged(true);
			priceLB.setVisible(true);
			priceLB.setManaged(true);
			typeLB.setVisible(true);
			typeLB.setManaged(true);
			powerLB.setVisible(true);
			powerLB.setManaged(true);
			detailLB.setVisible(true);
			detailLB.setManaged(true);
			add.setVisible(true);
			add.setManaged(true);
			delete.setVisible(true);
			delete.setManaged(true);
			cancel.setVisible(false);
			cancel.setManaged(false);
			change.setText("Sửa");
			carNameTF.setVisible(false);
			carNameTF.setManaged(false);
			brandTF.setVisible(false);
			brandTF.setManaged(false);
			priceTF.setVisible(false);
			priceTF.setManaged(false);
			typeTF.setVisible(false);
			typeTF.setManaged(false);
			powerTF.setVisible(false);
			powerTF.setManaged(false);
			detailTF.setVisible(false);
			detailTF.setManaged(false);
			Cars car=new Cars(id,carNameTF.getText(),brandTF.getText(),priceTF.getText(),typeTF.getText(),powerTF.getText(),detailTF.getText(),imgTF.getText());
			boolean check= CarDAO.ChangeInfor(car);
			carNameCol.setCellValueFactory(new PropertyValueFactory<Cars,
					String>("carName"));
			brandCol.setCellValueFactory(
					new PropertyValueFactory<Cars, String>("brand"));
			// Lấy ds User từ CSDL
			List<Cars> listCars = CarDAO.listAllCars();
			ObservableList<Cars> obsList =	FXCollections.observableArrayList(listCars);
			// Đưa vào bảng tableview
			carsListTV.setItems(obsList);
			//Xuất hiện thông tin xe ngay sau khi ấn lưu
			for (Cars selectedCar : listCars) {
				if (selectedCar.getCarName().equals(car.getCarName())) {
					carsListTV.getSelectionModel().select(selectedCar);
					carNameLB.setText(selectedCar.getCarName());
					brandLB.setText(selectedCar.getBrand());
					priceLB.setText(selectedCar.getPrice() + "$");
					typeLB.setText(selectedCar.getType());
					powerLB.setText(selectedCar.getPower() + "hp");
					detailLB.setText(selectedCar.getDetail());
					id=selectedCar.getID();
					// Dua anh vao ImageView
					FileInputStream input=null;
					try {
						if(!selectedCar.getImgPath().equals("")) {
							input = new FileInputStream(selectedCar.getImgPath());
							Image image = new Image(input);
							imgView.setImage(image);
							try {
			                    input.close();
			                } catch (IOException e) {
			                    e.printStackTrace();
			                }
						}else {
							imgView.setImage(null);
						}
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				}
			}
		}	
	}
	
	public void cancelClick() {
		carsListTV.setDisable(false);
		imgLink.setVisible(false);
		imgLink.setManaged(false);
		imgView.setVisible(true);
		imgView.setManaged(true);
		testView.setVisible(false);
		testView.setManaged(false);
		carNameLB.setVisible(true);
		carNameLB.setManaged(true);
		brandLB.setVisible(true);
		brandLB.setManaged(true);
		priceLB.setVisible(true);
		priceLB.setManaged(true);
		typeLB.setVisible(true);
		typeLB.setManaged(true);
		powerLB.setVisible(true);
		powerLB.setManaged(true);
		detailLB.setVisible(true);
		detailLB.setManaged(true);
		add.setVisible(true);
		add.setManaged(true);
		change.setVisible(true);
		change.setManaged(true);
		delete.setVisible(true);
		delete.setManaged(true);
		cancel.setVisible(false);
		cancel.setManaged(false);
		add.setText("Thêm");
		change.setText("Sửa");
		carNameTF.setVisible(false);
		carNameTF.setManaged(false);
		brandTF.setVisible(false);
		brandTF.setManaged(false);
		priceTF.setVisible(false);
		priceTF.setManaged(false);
		typeTF.setVisible(false);
		typeTF.setManaged(false);
		powerTF.setVisible(false);
		powerTF.setManaged(false);
		detailTF.setVisible(false);
		detailTF.setManaged(false);
	}
	
	public void fileChooserClick() {
		FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Chọn ảnh");
        fileChooser.getExtensionFilters().addAll(
        		 new FileChooser.ExtensionFilter("JPG", "*.jpg"),
        	     new FileChooser.ExtensionFilter("PNG", "*.png")
            );
        Stage stage = (Stage) imgTF.getScene().getWindow();
        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
        	imgTF.setText(file.getAbsolutePath());
        	Image image = new Image(file.toURI().toString());
            testView.setImage(image);
        }
	}
	
	@FXML
	public void onClickExit() {
		welcomeMsg.getScene().getWindow().hide();
	}

	public User getLoginedUser() {
		return loginedUser;
	}

	public void setLoginedUser(User loginedUser) {
		this.loginedUser = loginedUser;
	}
	
	public void onClickReturnLogin () {
		try {
			 FXMLLoader fxmlLoader = new FXMLLoader(getClass()
	                   .getResource("LoginGUI.fxml"));	
			
			 // Đọc file fxml và vẽ giao diện.
	         Parent root = fxmlLoader.load();
			// Thêm layout vào Scene
			Scene scene = new Scene(root);
			// Thêm Scene vào Stage
			Stage homeStage = new Stage();
			homeStage.setScene(scene);
			
			// Hiển thị Stage
			homeStage.setTitle("Login");
			homeStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
		welcomeMsg.getScene().getWindow().hide();
	}
	
}
