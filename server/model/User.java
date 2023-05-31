package model;

import java.io.Serializable;

//フィールド
public class User implements Serializable {
	private int UserId;
	private String UserName;
	private String Name;
	private String UserPass;
	private String Mail;
	private String Address;


//コンストラクタ
	public User() {}
	public User(String UserName,String UserPass) {
		this.UserName = UserName;
		this.UserPass = UserPass;
	}

	public void setUser(int UserId,String Mail,String Address,String Name) {
		this.UserId = UserId;
		this.Mail = Mail;
		this.Address = Address;
		this.Name = Name;
	}
	//新規登録に使うやつ
	public User(String UserName,String Name, String UserPass ,String Address,String Mail) {
		this.UserName = UserName;
		this.UserPass = UserPass;
		this.Mail = Mail;
		this.Address = Address;
		this.Name = Name;
	}
	//getter
	public int getUserId() {return UserId;}
	public String getUserName() {return UserName;}
	public String getUserPass() {return UserPass;}
	public String getMail() {return Mail;}
	public String getAddress() {return Address;}
	public String getName() {return Name;}
	//setter
	public void setUserId(int Id) {this.UserId = Id;}
	public void setUserName(String UserName) {this.UserName = UserName;}
	public void setUserPass(String UserPass) {this.UserPass = UserPass;}
	public void setMail(String Mail) {this.Mail = Mail;}
	public void setAddress(String Address) {this.Address = Address;}
	public void setName(String Name) {this.Name = Name;}

	//メソッド
	public void nullUserName(Object s,String str) {if(s.equals(""))this.UserName = str;}
	public void nullName(Object s,String str) {if(s.equals(""))this.Name = str;}
	public void nullUserPass(Object s,String str) {if(s.equals(""))this.UserPass = str;}
	public void nullAddress(Object s,String str) {if(s.equals(""))this.Address = str;}
	public void nullMail(Object s,String str) {if(s.equals(""))this.Mail = str;}
}
