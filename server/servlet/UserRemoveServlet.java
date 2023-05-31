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
import model.UserRemoveLogic;

/**
 * Servlet implementation class UserRemoveServlet
 */
@WebServlet("/UserRemoveServlet")
public class UserRemoveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//フォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/UserRemove.jsp");
		dispatcher.forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//アカウント削除処理

		request.setCharacterEncoding("UTF-8");
		String yes = request.getParameter("yes");

		System.out.println(yes);

		if(yes != null) {
			//ログインしている情報を取得
			HttpSession session = request.getSession();
			User loginUser = (User)session.getAttribute("loginUser");

			//アカウント削除
			UserRemoveLogic remove = new UserRemoveLogic();
			boolean isLogin = remove.execute(loginUser);

			//ログアウト
			request.getSession().invalidate();


			//フォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/UserRemoveResult.jsp");
			dispatcher.forward(request, response);
		}else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/UserEditServlet");
			dispatcher.forward(request, response);
		}
	}
}




