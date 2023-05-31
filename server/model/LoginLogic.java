package model;

import DAO.UserDAO;



public class LoginLogic {

		public boolean execute(User user){
			//dao起動してインスタンスにログイン情報を入れる
			UserDAO dao = new UserDAO();
			return dao.findAll(user);


		}
}

