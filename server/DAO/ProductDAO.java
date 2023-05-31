package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import productModel.Product;

public class ProductDAO {
    private final String JDBC_URL = "jdbc:mysql:3306//localhost:/tunobue?characterEncoding=UTF-8&serverTimezone=JST";
    private final String DB_USER = "root";
    private final String DB_PASS = "";

    //全商品情報取得
    public List<Product> findAll() {
        List<Product> productList = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
            String sql = "SELECT * FROM product ORDER BY PRODUCT_ID DESC";
            PreparedStatement pStmt = conn.prepareStatement(sql);
            ResultSet rs = pStmt.executeQuery();

            while (rs.next()) {
                Product product = new Product();
                product.setProductId(rs.getInt("PRODUCT_ID"));
                product.setProductTitle(rs.getString("PRODUCT_TITLE"));
                product.setCategory(rs.getInt("CATEGORY"));
                product.setGenre(rs.getInt("GENRE"));
                product.setPublisher(rs.getInt("PUBLISHER"));
                product.setDescription(rs.getString("DESCRIPTION"));
                product.setReview(rs.getString("REVIEW"));
                product.setMovie(rs.getString("MOVIE"));
                product.setPrice(rs.getInt("PRICE"));
                product.setStock(rs.getInt("STOCK"));
                product.setPic01(rs.getString("PICTURE"));
                product.setPageView(rs.getInt("PAGE_VIEW"));
                product.setProductDate(rs.getTimestamp("PRODUCT_DATE"));
                productList.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productList;
    }

    //新商品登録
    public void Insert(Product product) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
            String sql = "INSERT INTO`puroduct`(`PRODUCT_TITLE`,`CATEGORY_ID`, `GENRE_ID`, `PUBLISHER_ID`,`DISCRIPTION`, `REVIEW`, `MOVIE`, `PRICE`, `STOCK`, `PICTURE`, `PAGE_VIEW`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, product.getProductTitle());
            pStmt.setInt(2, product.getCategory());
            pStmt.setInt(3, product.getGenre());
            pStmt.setInt(4, product.getPublisher());
            pStmt.setString(5, product.getDescription());
            pStmt.setString(6, product.getReview());
            pStmt.setString(7, product.getMovie());
            pStmt.setInt(8, product.getPrice());
            pStmt.setInt(9, product.getStock());
            pStmt.setString(10, product.getProductId() + "_001.jpg");
            pStmt.setInt(11, product.getPageView());
            pStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //登録済み商品更新
    public void Update(Product product) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
            String sql = "UPDATE product_info SET PRODUCT_TITLE=?, CATEGORY=?, GENRE=?, PUBLISHER=?, DESCRIPTION=?, REVIEW=?, MOVIE=?, PRICE=?, STOCK=? WHERE PRODUCT_ID=?";
            PreparedStatement pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, product.getProductTitle());
            pStmt.setInt(2, product.getCategory());
            pStmt.setInt(3, product.getGenre());
            pStmt.setInt(4, product.getPublisher());
            pStmt.setString(5, product.getDescription());
                    pStmt.setString(6, product.getReview());
                    pStmt.setString(7, product.getMovie());
                    pStmt.setInt(8, product.getPrice());
                    pStmt.setInt(9, product.getStock());
                    pStmt.setInt(10, product.getProductId());
                    pStmt.executeUpdate();
                    } catch (SQLException e) {
                    e.printStackTrace();
                }
     }

    //商品削除
    public void Delete(Product product) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
            String sql = "DELETE FROM product_info WHERE PRODUCT_ID=?";
            PreparedStatement pStmt = conn.prepareStatement(sql);
            pStmt.setInt(1, product.getProductId());
            pStmt.executeUpdate();
                    } catch (SQLException e) {
                    e.printStackTrace();
                }
    }
}