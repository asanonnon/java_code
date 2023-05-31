package model;

import DAO.UserDAO;

public class UserEditLogic {
	public boolean execute(User user,User loginUser) {
		UserDAO dao = new UserDAO();
		return dao.upDate(user,loginUser);
	}
}
