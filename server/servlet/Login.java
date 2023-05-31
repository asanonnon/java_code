
package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.LoginLogic;
import model.User;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//ログインしている情報を取得
		HttpSession session = request.getSession();
		User loginUser = (User)session.getAttribute("loginUser");


		//ログイン（セッションに情報があれば）ログイン情報を表示
		//なければログイン画面に移動
		if(loginUser != null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/loginResult.jsp");
			dispatcher.forward(request, response);
		}else{
			//ログイン画面に飛ぶ
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
			dispatcher.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		//入力されたパラメーターの取得
		String UserName = request.getParameter("UserName");
		String UserPass = request.getParameter("UserPass");

		//自分の情報をインスタンスに保存
		User user = new User(UserName,UserPass);

		//パスワードが等しいか判定（trueかfalseか）
		LoginLogic loginLogic = new LoginLogic();
		boolean isLogin = loginLogic.execute(user);


//		System.out.println(user.getUserId());
//		System.out.println(user.getUserName());
//		System.out.println(user.getUserPass());
//		System.out.println(user.getAddress());
		System.out.println(isLogin);

		//ログインができたらセッションに保存する
		if(isLogin) {
		HttpSession session = request.getSession();
		session.setAttribute("loginUser", user);
		}

		//フォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/loginResult.jsp");
		dispatcher.forward(request, response);
	}

}
