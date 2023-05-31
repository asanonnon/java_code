package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import productModel.Cart;

/**
 * Servlet implementation class CartRemoveServlet
 */
@WebServlet("/CartRemoveServlet")
public class CartRemoveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//カートの数値に注文数を変更する
		//０にしたらそれをリストから排除する
		//セッションの保存
		HttpSession session = request.getSession();
		Cart cart = (Cart)session.getAttribute("cart");

		//パラメーターの取得（リスト番号、変更値）
		String listNumStr = request.getParameter("listNum");
		int listNum = 0;
		if(listNumStr != null || listNumStr.equals("")) {
			listNum = Integer.parseInt(listNumStr);
		}
		String volumeStr = request.getParameter("volume");
		int volume = 0;
		if(volumeStr != null || volumeStr.equals("")) {
			volume = Integer.parseInt(volumeStr);
		}
//		System.out.println("変更前:"+cart.getProductCount().get(listNum));

		//変更処理

		cart.getProductCount().set(listNum,volume);
		//	0を選択されたらリストから排除
		if(volume == 0) {
			cart.getProductCount().remove(listNum);
			cart.getCartList().remove(listNum);
			System.out.println("CartList:"+cart.getCartList());
			System.out.println("CartList:"+cart.getProductCount());
		}


		cart.calc();

		//session保存
		session.setAttribute("cart", cart);

		//フォワード
		RequestDispatcher d = request.getRequestDispatcher("/WEB-INF/jsp/Cart.jsp");
		d.forward(request, response);
	}

}
