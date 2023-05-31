package model;

import DAO.SerchDAO;
import productModel.Product;

public class ProductDetailLogic {
	public boolean execute(Product product) {
		SerchDAO dao = new SerchDAO();
		return dao.detail(product);
	}
}
