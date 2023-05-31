package model;

import DAO.UserDAO;

public class UserResisterLogic {
	public boolean execute(User user) {
		UserDAO dao = new UserDAO();
		return dao.Creat(user);

	}
}
