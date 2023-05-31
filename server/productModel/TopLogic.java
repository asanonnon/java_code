package productModel;

import java.util.ArrayList;

import DAO.SerchDAO;

public class TopLogic {

	public boolean execute(ArrayList<Product> top) {
		SerchDAO dao = new SerchDAO();
		return dao.randomTop(top);
	}
}
