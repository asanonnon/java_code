package productModel;

import java.io.Serializable;
import java.util.ArrayList;

public class ProductList implements Serializable{
	private ArrayList<Product> Array = new ArrayList<>();

	public void setList(Product product) {
		this.Array.add(product);
	}
	public ArrayList<Product> getList() {return Array;}
}
