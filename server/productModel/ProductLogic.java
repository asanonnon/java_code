package productModel;

import DAO.ProductDAO;

public class ProductLogic {
    public void Insert(Product product) {
        ProductDAO dao = new ProductDAO();
        dao.Insert(product);
    }

    public void Update(Product product) {
        ProductDAO dao = new ProductDAO();
        dao.Update(product);
    }

    public void Delete(Product product) {
        ProductDAO dao = new ProductDAO();
        dao.Delete(product);
    }
}