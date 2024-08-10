package Car.fxml;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CarDAO {
	public static List<Cars> listAllCars(){
		List<Cars> listCars = new ArrayList<>();
		
		String jdbcURL = "jdbc:ucanaccess://lib/QLNS.accdb";
		String jdbcUser = "";
		String jdbcPass = "";
		
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			Connection con = DriverManager.getConnection(jdbcURL, jdbcUser, jdbcPass);
			String sql = "Select * from tblcar";
			Statement ps = con.createStatement();
			
			ResultSet rs = ps.executeQuery(sql);
			
			while(rs.next()) {
				Cars car = new Cars();
				car.setCarName(rs.getString("carName"));
				car.setBrand(rs.getString("brand"));
				car.setPrice(rs.getString("price"));
				car.setType(rs.getString("type"));
				car.setPower(rs.getString("power"));
				car.setDetail(rs.getString("detail"));
				car.setImgPath(rs.getString("imgPath"));
				car.setID(rs.getInt("ID"));
				// Them vao danh sach
				listCars.add(car);
			}
			rs.close();
			ps.close();
			con.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Ko thấy driver");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		
		return listCars;
	}
	
	public static boolean addCar(Cars car) {
		
		String jdbcURL = "jdbc:ucanaccess://lib/QLNS.accdb";
		String jdbcUser = "";
		String jdbcPass = "";
		
		boolean inSertedCar = false;
		float priceNumber = 0;
		float powerNumber = 0;
		if (!car.getPrice().equals("")) {
			try {
				priceNumber = Float.parseFloat(car.getPrice());
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (!car.getPower().equals("")) {
			try {
				powerNumber = Float.parseFloat(car.getPower());
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			Connection con = DriverManager.getConnection(jdbcURL, jdbcUser, jdbcPass);
			String sql = "INSERT INTO tblcar (carName,brand,price,type,power,detail,imgPath) VALUES (?,?,?,?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, car.getCarName());
			ps.setString(2, car.getBrand());
			ps.setFloat(3, priceNumber);
			ps.setString(4, car.getType());
			ps.setFloat(5, powerNumber);
			ps.setString(6, car.getDetail());
			ps.setString(7, car.getImgPath());
			inSertedCar=ps.executeUpdate()>0;
			
			con.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Ko thấy driver");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return inSertedCar;
	}
	
	public static boolean deleteCar(Cars car) {
		
		String jdbcURL = "jdbc:ucanaccess://lib/QLNS.accdb";
		String jdbcUser = "";
		String jdbcPass = "";
		
		boolean deletedCar = false;
		
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			Connection con = DriverManager.getConnection(jdbcURL, jdbcUser, jdbcPass);
			String sql = "Delete from tblcar where ID=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, car.getID());
			
			deletedCar=ps.executeUpdate()>0;
			
			con.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Ko thấy driver");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return deletedCar;
	}

	public static boolean ChangeInfor(Cars car) {
	
		String jdbcURL = "jdbc:ucanaccess://lib/QLNS.accdb";
		String jdbcUser = "";
		String jdbcPass = "";
		
		boolean changeInfor = false;
		float priceNumber = 0;
		float powerNumber = 0;
		try {
			priceNumber = Float.parseFloat(car.getPrice());
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			powerNumber = Float.parseFloat(car.getPower());
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			Connection con = DriverManager.getConnection(jdbcURL, jdbcUser, jdbcPass);
			String sql = "Update tblcar set carName=?, brand=?, price=?, type=?, power=?, detail=?,imgPath=? where ID=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, car.getCarName());
			ps.setString(2, car.getBrand());
			ps.setFloat(3, priceNumber);
			ps.setString(4,car.getType());
			ps.setFloat(5, powerNumber);
			ps.setString(6, car.getDetail());
			ps.setString(7, car.getImgPath());
			ps.setInt(8, car.getID());
			changeInfor=ps.executeUpdate()>0;
			con.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Ko thấy driver");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return changeInfor;
	}
}
