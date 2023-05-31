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
import productModel.RankingLogic;

/**
 * Servlet implementation class RankingServlet
 */
@WebServlet("/RankingServlet")
public class RankingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//インスタンスの作成
		ArrayList<Product> ranking = new  ArrayList<>();
		RankingLogic logic = new RankingLogic();
		boolean boo = logic.execute(ranking);
		
		System.out.println(ranking);
		
		if(boo == false) {
			System.out.println("リストに追加されていません topに飛びます");
			RequestDispatcher d = request.getRequestDispatcher("/TopServlet");
			d.forward(request,response);
		}
		
		//リクエスト保存
		request.setAttribute("ranking",ranking);
		
		//フォワード
		RequestDispatcher d = request.getRequestDispatcher("/WEB-INF/jsp/ranking.jsp");
		d.forward(request,response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
