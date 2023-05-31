package productModel;

import java.util.ArrayList;

import DAO.SerchDAO;

public class RankingLogic {
	public boolean execute(ArrayList<Product> ranking) {
		SerchDAO dao = new SerchDAO();
		return dao.ranking(ranking);
	}
}
