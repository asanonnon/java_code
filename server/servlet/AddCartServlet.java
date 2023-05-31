package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.ProductDetailLogic;
import model.User;
import productModel.Cart;
import productModel.Product;

/**
 * Servlet implementation class AddCartServlet
 */
@WebServlet("/AddCartServlet")
public class AddCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//カートに追加する動作

		//強制送還
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("loginUser");
		System.out.println(user);
		if(user == null) {
			System.out.println("ログインしていない");
			RequestDispatcher d = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
			d.forward(request, response);
		}else {

			System.out.println("/AddCartServlet");
			request.setCharacterEncoding("UTF-8");


			//カートの中があるか確認
			Cart cart = (Cart)session.getAttribute("cart");
			if(cart == null) {
				cart = new Cart();
			}

			//parameter取得,変形 インスタンス格納 idだけのproduct
			System.out.println("parameter取得");

			String countParam = request.getParameter("countParam");

			System.out.println("countParam:"+countParam);
			int	count = Integer.parseInt(countParam);

			String id = request.getParameter("id");
			System.out.println("id:"+id);

			//インスタンス作成
			Product product = new Product();
			int	productId = Integer.parseInt(id);

	        product.setProductId(productId);


			//product作成
			System.out.println("product作成");
			ProductDetailLogic logic = new ProductDetailLogic();
			logic.execute(product);

			System.out.println("商品:"+product.getProductTitle());

			//productをリストに保存
			System.out.println("リスト保存");
			cart.setList(product,count);
			cart.calc();
			//セッション保存
			session.setAttribute("cart", cart);
			System.out.println("カート件数："+cart.getCartList().size());

			String purchase = request.getParameter("purchase");
			System.out.println(purchase);
			//フォワード
			if(purchase != null) {
				response.sendRedirect("/tunobue/CartViewServlet");
			}else{
				RequestDispatcher d = request.getRequestDispatcher("/ProductDetailServlet?id="+id);
				d.forward(request,response);
			}
		}
	}

}
