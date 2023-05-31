package productModel;

import java.util.ArrayList;

import DAO.SerchDAO;

public class SerchLogic {

	public ArrayList<Product> execute(ArrayList<Product> productList,Serch serch) {
		SerchDAO dao = new SerchDAO();
		if(serch.getKeyward()==null) {
			//カテゴリーとジャンル検索
			System.out.println("genre");
			return dao.select(productList,serch);

		}else {
			//キーワード検索
			System.out.println("keyward");
			return dao.keyward(productList,serch);
		}
	}
}
