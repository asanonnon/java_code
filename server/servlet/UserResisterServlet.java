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
import model.UserResisterLogic;

/**
 * Servlet implementation class UserResisterServlet
 */
@WebServlet("/UserResisterServlet")
public class UserResisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		//アカウント登録画面に移動
		RequestDispatcher d = request.getRequestDispatcher("/WEB-INF/jsp/UserResister.jsp");
		d.forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//postから受け取ったパラメーターをインスタンスに保存していろんなところに送りA
		//入力されたパラメーターの取得
		request.setCharacterEncoding("UTF-8");
		String UserName = request.getParameter("userName");
		String UserPass = request.getParameter("pass");
		String Name = request.getParameter("name");
		String Address = request.getParameter("address");
		String Mail = request.getParameter("mail");

		//自分の情報をインスタンスに保存
		User user = new User(UserName,Name,UserPass,Address,Mail);

		//パスワードが等しいか判定（trueかfalseか）
		UserResisterLogic resister = new UserResisterLogic();
		boolean isLogin = resister.execute(user);

		System.out.println(user.getUserName());
		System.out.println(user.getName());
		System.out.println(user.getUserPass());
		System.out.println(user.getAddress());
		System.out.println(user.getMail());

		//ログインができたらセッションに保存する
		if(isLogin) {
		HttpSession session = request.getSession();
		session.setAttribute("loginUser", user);
		}

		//フォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/UserResisterResult.jsp");
		dispatcher.forward(request, response);
	}

}
