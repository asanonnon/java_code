package model;

import DAO.UserDAO;

public class UserRemoveLogic {
	public boolean execute(User user) {
		UserDAO dao = new UserDAO();
		return dao.Remove(user);
	}
}
