package servlet;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.ProductDAO;
import productModel.Product;

@WebServlet("/ProductServlet")
//商品の登録の処理
public class ProductServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ProductServlet() {
        super();
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        ProductDAO dao = new ProductDAO();
        List<Product> productList = dao.findAll();
        request.setAttribute("productList", productList);

        //int lastProductId = dao.getLastProductId(); 作業途中
        //int newProductId = lastProductId + 1;
        //request.setAttribute("newProductId", newProductId);

        RequestDispatcher dispatcher = request.getRequestDispatcher("ProductRegister.jsp");
        dispatcher.forward(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String productIdStr = request.getParameter("productId");
        int productId = 0;
        if(productIdStr != null && !productIdStr.isEmpty()) {
            productId = Integer.parseInt(productIdStr);
        }

        String categoryStr = request.getParameter("category");
        int category = 0;
        if(categoryStr != null && !categoryStr.isEmpty()) {
            category = Integer.parseInt(categoryStr);
        }

        String genreStr = request.getParameter("genre");
        int genre = 0;
        if(genreStr != null && !genreStr.isEmpty()) {
            genre = Integer.parseInt(genreStr);
        }

        String priceStr = request.getParameter("price");
        int price = 0;
        if(priceStr != null && !priceStr.isEmpty()) {
            price = Integer.parseInt(priceStr);
        }

        String publisherStr = request.getParameter("price");
        int publisher = 0;
        if(publisherStr != null && !publisherStr.isEmpty()) {
            price = Integer.parseInt(publisherStr);
        }

        String stockStr = request.getParameter("stock");
        int stock = 0;
        if(stockStr != null && !stockStr.isEmpty()) {
            stock = Integer.parseInt(stockStr);
        }

        //入力したパラメーターを取得
        String productTitle = request.getParameter("productTitle");
        String description = request.getParameter("description");
        String review = request.getParameter("review");
        String movie = request.getParameter("movie");
        String pic01 = request.getParameter("pic01");

        String pageViewParam = request.getParameter("pageView");
        int pageView = 0;
        if (pageViewParam != null) {
            pageView = Integer.parseInt(pageViewParam);
        }

        Timestamp productDate = new Timestamp(new java.util.Date().getTime());

        ProductDAO productDao = new ProductDAO();
        Product product = new Product(productId, productTitle, category, genre, publisher, description, review, movie, price, stock, pic01, pageView, productDate);

        String action = request.getParameter("action");
        if (action.equals("insert")) {
            productDao.Insert(product);
        } else if (action.equals("update")) {
            productDao.Update(product);
        }else if (action.equals("delete")) {
            productDao.Delete(product);
        }

        //リクエストに保存
        List<Product> productList = productDao.findAll();
        request.setAttribute("productList", productList);

        //フォワード
        RequestDispatcher dispatcher = request.getRequestDispatcher("ProductRegister.jsp");
        dispatcher.forward(request, response);
    }

}
