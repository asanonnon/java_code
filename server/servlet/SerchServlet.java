

package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import productModel.Product;
import productModel.Serch;
import productModel.SerchLogic;

/**
 * Servlet implementation class SerchServlet
 */
@WebServlet("/SerchServlet")

public class SerchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		RequestDispatcher d = request.getRequestDispatcher("/WEB-INF/jsp/Search.jsp");
//		d.forward(request, response);
//	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//パラメーターの取得
		//ジャンルとカテゴリーの値
		request.setCharacterEncoding("UTF-8");
		String category = request.getParameter("category");
		String genre = request.getParameter("genre");
		//キーワード検索の値
		String keyward = request.getParameter("keyward");

		if(category != null) {
		if(category.equals("すべて")) {
			category = "";
		}}
		if(genre != null && category == null && keyward == null) {
			category = "";
		}
		if(genre != null) {
		if(genre.equals("すべて")) {
			genre = "";
		}}
		if(genre == null && category != null && keyward == null) {
			genre = "";
		}
		if(genre == null && category == null && keyward == null) {
			genre = "";
			category = "";
		}
		System.out.println("category:"+category);
		System.out.println("genre:"+genre);




		//インスタンスの生成
		Serch search;
		ArrayList<Product> productList = new ArrayList<>();
		SerchLogic Logic = new SerchLogic();

		if(keyward == null) {

			search = new Serch(category,genre);
		}else {
			search = new Serch(keyward);
		}
		//リストの完成
		productList = Logic.execute(productList,search);
		for(Product p : productList) {
		System.out.print(p.getProductTitle()+"：");
		}

		//リクエスト保存
		request.setAttribute("productList",productList);
		request.setAttribute("search",search);

		//フォワード
		RequestDispatcher d = request.getRequestDispatcher("/WEB-INF/jsp/SearchResult.jsp");
		d.forward(request, response);



	}

}
