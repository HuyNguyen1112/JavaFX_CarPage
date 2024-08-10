package Car.fxml;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
	
	public static int getUserRole(String email) {
	    	int role=-1;
	    	String jdbcURL = "jdbc:ucanaccess://lib/QLNS.accdb";
			String jdbcUser = "";
			String jdbcPass = "";
			
			try {
				Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
				Connection con = DriverManager.getConnection(jdbcURL, jdbcUser, jdbcPass);
	            String sql = "SELECT role FROM tbluser WHERE email = ?";
	            PreparedStatement ps = con.prepareStatement(sql);
				ps.setString(1, email);
				ResultSet rs = ps.executeQuery();		
	            if (rs.next()) {
	                role = rs.getInt("role");
	            }
	        } 
			catch (SQLException e) {
	            e.printStackTrace();
	        } catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        return role;
	    }
	
	public static List<User> listAllUser(){
		List<User> listUser = new ArrayList<>();
		
		String jdbcURL = "jdbc:ucanaccess://lib/QLNS.accdb";
		String jdbcUser = "";
		String jdbcPass = "";
		
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			Connection con = DriverManager.getConnection(jdbcURL, jdbcUser, jdbcPass);
			String sql = "Select * from tbluser";
			Statement ps = con.createStatement();
			
			ResultSet rs = ps.executeQuery(sql);
			
			while(rs.next()) {
				User user = new User();
				user.setEmail(rs.getString("email"));
				user.setFullname(rs.getString("fullname"));
				user.setImgPath(rs.getString("imgPath"));
				
				// Them vao danh sach
				listUser.add(user);
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
		
		return listUser;
	}
	
	public static User checkUser(String email, String password) {
		User user = null;
		
		String jdbcURL = "jdbc:ucanaccess://lib/QLNS.accdb";
		String jdbcUser = "";
		String jdbcPass = "";
		
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			Connection con = DriverManager.getConnection(jdbcURL, jdbcUser, jdbcPass);
			String sql = "Select * from tbluser Where email = ? and password =?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, email);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				user = new User();
				user.setEmail(email);
				user.setFullname(rs.getString("fullname"));
			}
			con.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Ko thấy driver");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		
		return user;
	}
	
//	public static boolean addUser(User user) {
//		
//		String jdbcURL = "jdbc:ucanaccess://lib/QLNS.accdb";
//		String jdbcUser = "";
//		String jdbcPass = "";
//		
//		boolean inSertedUser = false;
//		
//		try {
//			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
//			Connection con = DriverManager.getConnection(jdbcURL, jdbcUser, jdbcPass);
//			String sql = "INSERT INTO tbluser (email,fullname) VALUES (?,?)";
//			PreparedStatement ps = con.prepareStatement(sql);
//			ps.setString(1, user.getEmail());
//			ps.setString(2, user.getFullname());
//			
//			inSertedUser=ps.executeUpdate()>0;
//			
//			con.close();
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			System.out.println("Ko thấy driver");
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		return inSertedUser;
//	}
//	
//	public static boolean deleteUser(User user) {
//		
//		String jdbcURL = "jdbc:ucanaccess://lib/QLNS.accdb";
//		String jdbcUser = "";
//		String jdbcPass = "";
//		
//		boolean deletedUser = false;
//		
//		try {
//			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
//			Connection con = DriverManager.getConnection(jdbcURL, jdbcUser, jdbcPass);
//			String sql = "Delete from tbluser where email=?";
//			PreparedStatement ps = con.prepareStatement(sql);
//			ps.setString(1, user.getEmail());
//			
//			deletedUser=ps.executeUpdate()>0;
//			
//			con.close();
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			System.out.println("Ko thấy driver");
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		return deletedUser;
//	}
//
//	public static boolean renameUser(User user) {
//	
//		String jdbcURL = "jdbc:ucanaccess://lib/QLNS.accdb";
//		String jdbcUser = "";
//		String jdbcPass = "";
//		
//		boolean renameUser = false;
//		
//		try {
//			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
//			Connection con = DriverManager.getConnection(jdbcURL, jdbcUser, jdbcPass);
//			String sql = "Update tbluser set fullname=? where email=?";
//			PreparedStatement ps = con.prepareStatement(sql);
//			ps.setString(2, user.getEmail());
//			ps.setString(1, user.getFullname());
//			renameUser=ps.executeUpdate()>0;
//			
//			con.close();
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			System.out.println("Ko thấy driver");
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		return renameUser;
//	}
}
