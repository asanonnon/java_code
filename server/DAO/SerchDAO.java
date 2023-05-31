package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

import model.User;
import productModel.Cart;
import productModel.Product;
import productModel.Serch;

public class SerchDAO {
	String JDBC_URL ="jdbc:mysql://localhost:8889/tunobue?characterEncoding=UTF-8&serverTimezone=JST";
	String DB_USER ="root";
	String DB_PASS = "root";

	    //検索
	    public ArrayList<Product> select(ArrayList<Product> productList,Serch serch) {
	    	//ジャンル、カテゴリー検索
	        try {
	            Class.forName("com.mysql.jdbc.Driver");
	        } catch (ClassNotFoundException e) {
	            e.printStackTrace();
	        }

	        try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
	        	String sql = "SELECT  p.PRODUCT_ID,p.PRODUCT_TITLE,c.CATEGORY_NAME,g.GENRE_NAME,p.`PUBLISHER`,p.`DISCRIPTION`,p.`REVIEW`,p.`MOVIE`,p.`PRICE`,p.`STOCK`,p.`PAGE_VIEW`,p.`PICTURE`,p.`PRODUCT_DATE` FROM puroduct as p JOIN category as c on c.CATEGORY_ID = p.CATEGORY_ID join  genre as g on  g.GENRE_ID = p.GENRE_ID where c.CATEGORY_NAME like ? and g.GENRE_NAME like ? ";
	            PreparedStatement pStmt = conn.prepareStatement(sql);
	            pStmt.setString(1,serch.getCategory());
	            pStmt.setString(2,serch.getGenre());
	            ResultSet rs = pStmt.executeQuery();

	            while (rs.next()) {
	                Product product = new Product();
	                product.setProductId(rs.getInt("PRODUCT_ID"));
	                product.seｔTitle(rs.getString("PRODUCT_TITLE"));
	                product.setCategory(rs.getString("CATEGORY_NAME"));
	                product.setGenre(rs.getString("GENRE_NAME"));
	                product.setPublisher(rs.getString("PUBLISHER"));
	                product.setDescription(rs.getString("DISCRIPTION"));
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
	            return productList;
	        }
	        return productList;
	    }

	    public ArrayList<Product> keyward(ArrayList<Product> productList ,Serch search) {
	    	//キーワード検索

	        try {
	            Class.forName("com.mysql.jdbc.Driver");
	        } catch (ClassNotFoundException e) {
	            e.printStackTrace();
	        }

	        try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
	            String sql = "SELECT  p.PRODUCT_ID,p.PRODUCT_TITLE,c.CATEGORY_NAME,g.GENRE_NAME,p.`PUBLISHER`,p.`DISCRIPTION`,p.`REVIEW`,p.`MOVIE`,p.`PRICE`,p.`STOCK`,p.`PAGE_VIEW`,p.`PICTURE`,p.`PRODUCT_DATE` FROM puroduct as p JOIN category as c on c.CATEGORY_ID = p.CATEGORY_ID join  genre as g on  g.GENRE_ID = p.GENRE_ID where p.PRODUCT_TITLE like ? or c.CATEGORY_NAME like ? or g.GENRE_NAME like ? ";
	            PreparedStatement pStmt = conn.prepareStatement(sql);
	            System.out.println(search.getKeyward());
	            pStmt.setString(1,search.getKeyward());
	            pStmt.setString(2,search.getKeyward());
	            pStmt.setString(3,search.getKeyward());
	            ResultSet rs = pStmt.executeQuery();

	            while (rs.next()) {
	                Product product = new Product();
	                product.setProductId(rs.getInt("PRODUCT_ID"));
	                product.seｔTitle(rs.getString("PRODUCT_TITLE"));
	                product.setCategory(rs.getString("CATEGORY_NAME"));
	                product.setGenre(rs.getString("GENRE_NAME"));
	                product.setPublisher(rs.getString("PUBLISHER"));
	                product.setDescription(rs.getString("DISCRIPTION"));
	                product.setReview(rs.getString("REVIEW"));
	                product.setMovie(rs.getString("MOVIE"));
	                product.setPrice(rs.getInt("PRICE"));
	                product.setStock(rs.getInt("STOCK"));
	                product.setPic01(rs.getString("PICTURE"));
	                product.setPageView(rs.getInt("PAGE_VIEW"));
	                product.setProductDate(rs.getTimestamp("PRODUCT_DATE"));
	                System.out.println("title:"+product.getProductTitle());
	                productList.add(product);
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	            return productList;
	        }
	        return productList;
	    }

	    public boolean detail(Product product) {
	    	//商品詳細

	        try {
	            Class.forName("com.mysql.jdbc.Driver");
	        } catch (ClassNotFoundException e) {
	            e.printStackTrace();
	        }

	        try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
	            String sql = "SELECT  p.PRODUCT_ID,p.PRODUCT_TITLE,c.CATEGORY_NAME,g.GENRE_NAME,p.`PUBLISHER`,p.`DISCRIPTION`,p.`REVIEW`,p.`MOVIE`,p.`PRICE`,p.`STOCK`,p.`PAGE_VIEW`,p.`PICTURE`,p.`PRODUCT_DATE` FROM puroduct as p JOIN category as c on c.CATEGORY_ID = p.CATEGORY_ID join  genre as g on  g.GENRE_ID = p.GENRE_ID where p.PRODUCT_ID=?";
	            PreparedStatement pStmt = conn.prepareStatement(sql);

	            String str = Integer.valueOf(product.getProductId()).toString();

	            pStmt.setString(1,str);
	            ResultSet rs = pStmt.executeQuery();

	            while (rs.next()) {
	                product.setProductId(rs.getInt("PRODUCT_ID"));
	                product.seｔTitle(rs.getString("PRODUCT_TITLE"));
	                product.setCategory(rs.getString("CATEGORY_NAME"));
	                product.setGenre(rs.getString("GENRE_NAME"));
	                product.setPublisher(rs.getString("PUBLISHER"));
	                product.setDescription(rs.getString("DISCRIPTION"));
	                product.setReview(rs.getString("REVIEW"));
	                product.setMovie(rs.getString("MOVIE"));
	                product.setPrice(rs.getInt("PRICE"));
	                product.setStock(rs.getInt("STOCK"));
	                product.setPic01(rs.getString("PICTURE"));
	                product.setPageView(rs.getInt("PAGE_VIEW"));
	                product.setProductDate(rs.getTimestamp("PRODUCT_DATE"));

	            }

	            String sql2 = "update `puroduct` set PAGE_VIEW = PAGE_VIEW + 1 where PRODUCT_ID=?";
	            PreparedStatement pStmt2 = conn.prepareStatement(sql2);

	            pStmt2.setString(1,str);
	            pStmt2.executeUpdate();


	        } catch (SQLException e) {
	            e.printStackTrace();
	            return false;
	        }
	        	return true;
	    }


	    //購入処理
	    public boolean buy(Cart cart,User user) {
	        try {
	            Class.forName("com.mysql.jdbc.Driver");
	        } catch (ClassNotFoundException e) {
	            e.printStackTrace();
	        }

	        try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

	        	for(Product p : cart.getCartList()) {
	        		//購入履歴の記入
		            String sql = "INSERT INTO `buy`(`USER_ID`, `PRODUCT_ID`, `BUY_QTY`) VALUES (?,?,?)";
		            PreparedStatement pStmt = conn.prepareStatement(sql);

		            int i =0;

		            pStmt.setInt(1,user.getUserId());
		            pStmt.setInt(2,p.getProductId());
		            pStmt.setInt(3,cart.getProductCount().get(i));
		            int rs = pStmt.executeUpdate();
		            if(rs == 0) {
		            	System.err.println("更新失敗");
		            	return false;
		            }

		            //productの在庫の更新
		            String sql2 = "UPDATE `puroduct` SET `STOCK`= `STOCK`-? WHERE ?";
		            PreparedStatement pStmt2 = conn.prepareStatement(sql2);

		            pStmt2.setInt(1,cart.getProductCount().get(i));
		            pStmt2.setInt(2,p.getProductId());
		            pStmt2.executeUpdate();

		            i++;
	        	}


	        } catch (SQLException e) {
	            e.printStackTrace();
	            return false;
	        }
	        	return true;
	    }

	    //ランキング
	    public boolean ranking(ArrayList<Product> ranking) {


	        try {
	            Class.forName("com.mysql.jdbc.Driver");
	        } catch (ClassNotFoundException e) {
	            e.printStackTrace();
	        }

	        try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {


	        		//購入履歴の記入
	        	String sql = "SELECT  p.PRODUCT_ID,p.PRODUCT_TITLE,c.CATEGORY_NAME,g.GENRE_NAME,p.`PUBLISHER`,p.`DISCRIPTION`,p.`REVIEW`,p.`MOVIE`,p.`PRICE`,p.`STOCK`,p.`PAGE_VIEW`,p.`PICTURE`,p.`PRODUCT_DATE` FROM puroduct as p JOIN category as c on c.CATEGORY_ID = p.CATEGORY_ID join  genre as g on  g.GENRE_ID = p.GENRE_ID order by  p.PAGE_VIEW desc limit 10";
		            PreparedStatement pStmt = conn.prepareStatement(sql);

		            //select実行
		           ResultSet  rs = pStmt.executeQuery();
		            while (rs.next()) {
		            	Product product = new Product();
		                product.setProductId(rs.getInt("PRODUCT_ID"));
		                product.seｔTitle(rs.getString("PRODUCT_TITLE"));
		                product.setCategory(rs.getString("CATEGORY_NAME"));
		                product.setGenre(rs.getString("GENRE_NAME"));
		                product.setPublisher(rs.getString("PUBLISHER"));
		                product.setDescription(rs.getString("DISCRIPTION"));
		                product.setReview(rs.getString("REVIEW"));
		                product.setMovie(rs.getString("MOVIE"));
		                product.setPrice(rs.getInt("PRICE"));
		                product.setStock(rs.getInt("STOCK"));
		                product.setPic01(rs.getString("PICTURE"));
		                product.setPageView(rs.getInt("PAGE_VIEW"));
		                product.setProductDate(rs.getTimestamp("PRODUCT_DATE"));
		                ranking.add(product);

		                System.out.println("ランキングに追加されました");
		            }



	        } catch (SQLException e) {
	            e.printStackTrace();
	            return false;
	        }
	        	return true;
	    }

	    //Top画面
	    public boolean randomTop(ArrayList<Product> top) {


	        try {
	            Class.forName("com.mysql.jdbc.Driver");
	        } catch (ClassNotFoundException e) {
	            e.printStackTrace();
	        }

	        try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

	        	//商品数の取得
	        	int i = 0;//商品数保存場所
	        	String sql = "SELECT count(*) FROM `puroduct`";
	        	PreparedStatement pStmt = conn.prepareStatement(sql);
	        	ResultSet rs = pStmt.executeQuery();
	        	while(rs.next()){
	        		i = rs.getInt("count(*)");
	        	}
	        	System.out.println(i);

	        	//チェックしながらランダムな数値を格納する
	        	HashSet<Integer> set = new HashSet<>();
	        	int r = 0;
	        	while(r < 5) {
	        		Random rand = new Random();
	        		int randInt = rand.nextInt(i)+1;
	        		if(!set.add(randInt)) {
	        			System.out.println(randInt+":は重複");
	        		}else{
	        			System.out.println(randInt+":を格納しました");
	        			r++;
	        		}
	        	}
	        	System.out.println(set);


	        	//ランダムで５点を選択
	        	String sql2 = "SELECT  p.PRODUCT_ID,p.PRODUCT_TITLE,c.CATEGORY_NAME,g.GENRE_NAME,p.`PUBLISHER`,p.`DISCRIPTION`,p.`REVIEW`,p.`MOVIE`,p.`PRICE`,p.`STOCK`,p.`PAGE_VIEW`,p.`PICTURE`,p.`PRODUCT_DATE` FROM puroduct as p JOIN category as c on c.CATEGORY_ID = p.CATEGORY_ID join  genre as g on  g.GENRE_ID = p.GENRE_ID where p.PRODUCT_ID=? or p.PRODUCT_ID=? or p.PRODUCT_ID=? or p.PRODUCT_ID=? or p.PRODUCT_ID=? limit 5";
		        PreparedStatement pStmt2 = conn.prepareStatement(sql2);
		        i = 1;
		        for(int s : set) {
		        	//setの値を順に格納
		        	pStmt2.setInt(i,s);
		        	System.out.print("NO."+i );
		        	System.out.println("リスト:"+s );
		        	i++;
		        }


		            //select実行
		           ResultSet  rs2 = pStmt2.executeQuery();
		            while (rs2.next()) {
		            	Product product = new Product();
		                product.setProductId(rs2.getInt("PRODUCT_ID"));
		                product.seｔTitle(rs2.getString("PRODUCT_TITLE"));
		                product.setCategory(rs2.getString("CATEGORY_NAME"));
		                product.setGenre(rs2.getString("GENRE_NAME"));
		                product.setPublisher(rs2.getString("PUBLISHER"));
		                product.setDescription(rs2.getString("DISCRIPTION"));
		                product.setReview(rs2.getString("REVIEW"));
		                product.setMovie(rs2.getString("MOVIE"));
		                product.setPrice(rs2.getInt("PRICE"));
		                product.setStock(rs2.getInt("STOCK"));
		                product.setPic01(rs2.getString("PICTURE"));
		                product.setPageView(rs2.getInt("PAGE_VIEW"));
		                product.setProductDate(rs2.getTimestamp("PRODUCT_DATE"));
		                top.add(product);

		                System.out.println("topに追加されました");
		            }



	        } catch (SQLException e) {
	            e.printStackTrace();
	            return false;
	        }
	        	return true;
	    }

}
