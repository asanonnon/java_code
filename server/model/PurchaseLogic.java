package model;

import DAO.SerchDAO;
import productModel.Cart;

public class PurchaseLogic{
	public boolean execute(Cart cart, User user)  {
		SerchDAO dao = new SerchDAO();
		return dao.buy(cart,user);
	}
}
