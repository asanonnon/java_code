package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ProductDetailLogic;
import productModel.Product;

/**
 * Servlet implementation class ProductDetailServlet
 */
@WebServlet("/ProductDetailServlet")
public class ProductDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//詳細画面
		Product product = new Product();
		request.setCharacterEncoding("UTF-8");
		String idParam = request.getParameter("id");

		int productIdStr;
		if(idParam != null && !idParam.isEmpty()) {
            productIdStr = Integer.parseInt(idParam);
            product.setProductId(productIdStr);
            System.out.println("id:"+productIdStr);
        }else {
        	System.out.println("パラメーター取れてない");
        	RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/SerchTest.jsp");
    		dispatcher.forward(request, response);
        }


		//インスタンス

		ProductDetailLogic detail = new ProductDetailLogic();
		detail.execute(product);

		//リクエスト保存
		request.setAttribute("product",product);

		//フォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/productDetail.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
