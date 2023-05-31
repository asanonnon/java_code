package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.User;

/**
 * Servlet implementation class CartViewServlet
 */
@WebServlet("/CartViewServlet")
public class CartViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//フォワード
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("loginUser");
		if(user == null) {
			System.out.println("ログインしていない");
			response.sendRedirect("/tunobue/Login");
		}else {
		System.out.println("Cart.jspへ");
		RequestDispatcher d = request.getRequestDispatcher("WEB-INF/jsp/Cart.jsp");
		d.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
