package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.PurchaseLogic;
import model.User;
import productModel.Cart;

/**
 * Servlet implementation class PurchaseServlet
 */
@WebServlet("/PurchaseServlet")
public class PurchaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//確認画面に飛ぶ
		RequestDispatcher d = request.getRequestDispatcher("/WEB-INF/jsp/PurchaseComfim.jsp");
		d.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//セッションの取得
		HttpSession session = request.getSession();
		Cart cart = (Cart)session.getAttribute("cart");
		User user = (User)session.getAttribute("loginUser");

		//購入処理起動
		PurchaseLogic logic = new PurchaseLogic();
		boolean buyConti = logic.execute(cart,user);

		//購入成功したらインスタンスを初期化
		if(buyConti == true) {
			System.out.println("購入処理完了");
			cart = new Cart();
		}
		//セッション保存
		session.setAttribute("cart", cart);

		//フォワード
		RequestDispatcher d = request.getRequestDispatcher("/WEB-INF/jsp/PurchaseResult.jsp");
		d.forward(request, response);
	}

}
