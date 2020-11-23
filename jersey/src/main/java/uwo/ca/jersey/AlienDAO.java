package uwo.ca.jersey;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AlienDAO {

	Connection con = null;
	
	public AlienDAO() {
		
		String url = "jdbc:mysql://localhost:3306/neon?useSSL=false";
		String username = "root";
		String password = "root";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url, username, password);
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public List<Alien> getAliens(){
		List<Alien> aliens = new ArrayList<Alien>();
		String sql = "select * from alientable";
		
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			
			while (rs.next()) {
				Alien a = new Alien();
				a.setId(rs.getInt(1));
				a.setName(rs.getString(2));
				a.setPoints(rs.getInt(3));
				aliens.add(a);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return aliens;
	}
	
	public Alien getAlien(int id) {

		String sql = "select * from alientable where id = "+id;
		Alien a = new Alien();
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			
			if (rs.next()) {
				a.setId(rs.getInt(1));
				a.setName(rs.getString(2));
				a.setPoints(rs.getInt(3));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return a;
	}
	
	public void createAlien(Alien a1) {
		
		String sql = "insert into alientable values (?,?,?)";
		try {
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, a1.getId());
			st.setString(2, a1.getName());
			st.setInt(3, a1.getPoints());
			st.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
public void updateAlien(Alien a1) {
		
		String sql = "update alientable set name = ?, points = ? where id = ?";
		try {
			PreparedStatement st = con.prepareStatement(sql);
			
			st.setString(1, a1.getName());
			st.setInt(2, a1.getPoints());
			st.setInt(3, a1.getId());
			st.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
