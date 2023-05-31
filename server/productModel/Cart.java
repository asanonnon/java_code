package productModel;

import java.io.Serializable;
import java.util.ArrayList;

public class Cart implements Serializable {
	private ArrayList<Product> cartList = new ArrayList<>();
	private ArrayList<Integer> productCount = new ArrayList<>();
	private int totalPrice;

	//メソッド
	public void calc() {
		//合計金額を算出
		System.out.println(" ");
		System.out.println("カート計算");
		this.totalPrice = 0;
		int i = 0;

		for(Product p : this.cartList) {

			this.totalPrice += p.getPrice() * this.productCount.get(i);
			System.out.println("count:" + this.productCount.get(i));
			System.out.println("price:" + p.getPrice());
			i++;
		}
		System.out.println("合計金額"+this.totalPrice);
		System.out.println(" ");
	}

	//set,get
	public void setList(Product product,int count) {
		//重複チェックして追加する
		int i = 0;
		boolean boo = false;
		for(Product p :cartList) {
			if(p.getProductId() == product.getProductId()) {
				this.productCount.set(i,productCount.get(i) + count);

				System.out.println("重複");
				boo = true;
			}
			i++;
		}
		if(boo == false) {
			this.cartList.add(product);
			this.productCount.add(count);
		}
	}
	public ArrayList<Product> getCartList(){return cartList;}
	public ArrayList<Integer> getProductCount(){return productCount;}
	public int getTotalPrice() {return totalPrice;}


}
