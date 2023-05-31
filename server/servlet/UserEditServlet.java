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
import model.UserEditLogic;


/**
 * Servlet implementation class UserEditServlet
 */
@WebServlet("/UserEditServlet")
public class UserEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//アカウント編集画面に移動
		RequestDispatcher d = request.getRequestDispatcher("/WEB-INF/jsp/UserEdit.jsp");
		d.forward(request, response);
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				//ログインしている情報を取得
				HttpSession session = request.getSession();
				User loginUser = (User)session.getAttribute("loginUser");

				System.out.println(loginUser.getUserName());

				//入力されたパラメーターの取得
				request.setCharacterEncoding("UTF-8");
				String UserName = request.getParameter("userName");
				String UserPass = request.getParameter("pass");
				String Name = request.getParameter("name");
				String Address = request.getParameter("address");
				String Mail = request.getParameter("mail");

				//変更後の情報をインスタンスに保存
				User user = new User(UserName,Name,UserPass,Address,Mail);



				//nullだったらログインしている情報を入れる
				user.nullUserName(user.getUserName(),loginUser.getUserName());
				user.nullName(user.getName(),loginUser.getName());
				user.nullUserPass(user.getUserPass(),loginUser.getUserPass());
				user.nullAddress(user.getAddress(),loginUser.getAddress());
				user.nullMail(user.getMail(),loginUser.getMail());

				//パスワードが等しいか判定（trueかfalseか）
				UserEditLogic resister = new UserEditLogic();
				boolean isLogin = resister.execute(user,loginUser);

				//ログインができたらセッションに保存する
				if(isLogin) {
				session.setAttribute("loginUser", user);
				}

				//フォワード
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/UserEditResult.jsp");
				dispatcher.forward(request, response);
	}

}
