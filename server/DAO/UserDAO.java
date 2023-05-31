package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.User;

public class UserDAO {
	private final static String JDBC_URL ="jdbc:mysql://localhost:8889/tunobue?characterEncoding=UTF-8&serverTimezone=JST";
	private final static String DB_USER ="root";
	private final static String DB_PASS = "root";

	public boolean findAll(User user){
		//入力値と同じものを探してくる
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		//データベース接続
		try(Connection conn = DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS)){

			//select準備
			String sql = "select * from user where USER_NAME=? and USER_PASS=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1,user.getUserName());
			pStmt.setString(2,user.getUserPass());

			//select実行
			ResultSet rs = pStmt.executeQuery();


			//入力情報が存在すれば情報をインスタンスに挿入
			while(rs.next()) {
				int id = rs.getInt("USER_ID");
				String userName = rs.getString("USER_NAME");
				String pass = rs.getString("USER_PASS");
				String mail = rs.getString("MAIL");
				String address = rs.getString("ADDRESS");
				String name = rs.getString("NAME");

				user.setUserId(id);
				user.setUserName(userName);
				user.setUserPass(pass);
				user.setAddress(address);
				user.setMail(mail);
				user.setName(name);
			}

			//ログインしているか検査
			if(user.getUserId() == 0){
				return false;
			}


		}catch(SQLException e){
			e.printStackTrace();
			return false;
		}

		return true;
	}

	public boolean Creat(User user){
		//アカウント登録
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		//データベース接続
		try(Connection conn = DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS)){

			//insert文準備
			String sql = "INSERT INTO `user`(USER_NAME,NAME,USER_PASS,ADDRESS,MAIL) VALUES (?,?,?,?,?)";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1,user.getUserName());
			pStmt.setString(2,user.getName());
			pStmt.setString(3,user.getUserPass());
			pStmt.setString(4,user.getAddress());
			pStmt.setString(5,user.getMail());

			//insert文の実行
			int result = pStmt.executeUpdate();
			System.out.println(result);

			if(result != 1) {
				//インスタンス情報更新

				return false;

			}
			findAll(user);



		}catch(SQLException e){
			e.printStackTrace();
			return false;
		}

		return true;
	}

	public boolean upDate(User user,User loginUser){
		//アカウント登録
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		//データベース接続
		try(Connection conn = DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS)){

			System.out.println("update");

			//insert文準備
			String sql = "UPDATE `user` SET `USER_NAME`=?,`NAME`=?,`USER_PASS`=?,`ADDRESS`=?,`MAIL`=? WHERE USER_NAME=? AND USER_PASS=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			//変更後の情報
			pStmt.setString(1,user.getUserName());
			pStmt.setString(2,user.getName());
			pStmt.setString(3,user.getUserPass());
			pStmt.setString(4,user.getAddress());
			pStmt.setString(5,user.getMail());

			//変更前の情報4
			pStmt.setString(6,loginUser.getUserName());
			pStmt.setString(7,loginUser.getUserPass());

			//insert文の実行
			int result = pStmt.executeUpdate();
			System.out.println(result);

			if(result != 1) {
				//インスタンス情報更新

				return false;

			}
			findAll(user);



		}catch(SQLException e){
			e.printStackTrace();
			return false;
		}

		return true;
	}

	public boolean Remove(User user) {
		//アカウント削除
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		//データベース接続
		try(Connection conn = DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS)){

			System.out.println("update");

			//delete文準備
			String sql = "delete from user where USER_NAME=? and USER_PASS=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			//変更後の情報
			pStmt.setString(1,user.getUserName());
			pStmt.setString(2,user.getUserPass());


			//delete文の実行
			int result = pStmt.executeUpdate();





		}catch(SQLException e){
			e.printStackTrace();
			return false;
		}

		return true;
	}
}
