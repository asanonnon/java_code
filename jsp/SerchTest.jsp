<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="productModel.ProductList,java.util.ArrayList,productModel.Product" %>
    <%
    ArrayList<Product> productList = (ArrayList<Product>)request.getAttribute("productList");
    if(productList!=null){
    	for(Product p : productList){
    	System.out.println("title:"+p.getProductTitle());
    	}
    }

    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<a href="/stunobue/CartViewServlet">カート</a>
<a href="/tunobue/Login">ログイン</a>
<a href="/stunobue/RankingServlet">ランキング</a>
<a href="/stunobue/TopServlet">Top</a>
<form action="/stunobue/SerchServlet" method="post" class="main">
	<div class="left">
		<input type="radio" name="category" value="すべて">すべて<br>
		<input type="radio" name="category" value="小説">小説<br>
		<input type="radio" name="category" value="ライトノベル">ライトノベル<br>
		<input type="radio" name="category" value="エッセイ">エッセイ<br>
		<input type="radio" name="category" value="漫画">漫画<br>
		<input type="radio" name="category" value="図鑑">図鑑<br>
		<input type="radio" name="category" value="実用書">実用書<br>
		<input type="radio" name="category" value="ビジネス書">ビジネス書<br>
		<input type="radio" name="category" value="趣味">趣味<br>
		<input type="radio" name="category" value="辞書・辞典">辞書・辞典<br>
		<input type="radio" name="category" value="新書">新書<br>
		<input type="radio" name="category" value="洋書">洋書<br><br><br>
</div>
<div class="right">
		<input type="radio" name="genre" value="すべて">すべて<br>
		<input type="radio" name="genre" value="ファンタジー">ファンタジー<br>
		<input type="radio" name="genre" value="ミステリーサスペンス">ミステリーサスペンス<br>
		<input type="radio" name="genre" value="ノンフィクション">ノンフィクション<br>
		<input type="radio" name="genre" value="エンターテイメント">エンターテイメント<br>
		<input type="radio" name="genre" value="アクション">アクション<br>
		<input type="radio" name="genre" value="恋愛">恋愛<br>
		<input type="radio" name="genre" value="日常">日常<br>
		<input type="radio" name="genre" value="SF">SF<br>
		<input type="radio" name="genre" value="歴史">歴史<br>
		<input type="radio" name="genre" value="スポーツ">スポーツ<br>
		<input type="radio" name="genre" value="コメディ">コメディ<br>
		<input type="radio" name="genre" value="医療">医療<br>
		<input type="radio" name="genre" value="サイエンス">サイエンス<br>
</div>
	<input type="submit">
</form>

<form action="/stunobue/SerchServlet" method="post">
	<input type="text" name="keyward">
	<input type="submit" value="検索">
</form>

<!--検索結果 -->
<%if(productList!=null){ %>
 <div class="items-area">
	<%for(Product p : productList){ %>
        <div class="item">
            <div class="title">
            	<a href="/stunobue/ProductDetailServlet?id=<%=p.getProductId() %>">
                	<h2><!--タイトル--><%=p.getProductTitle() %></h2>
            	</a>
            </div>
            <div class="img-price">
                <img src="book_img/<%=p.getPic01() %>" alt=""><!--画像-->
                <div class="price-etc-area">
                    <div class="price">
                        <!-- 値段 -->
                        <%=p.getPrice() %>
                    </div>
                    <div class="etc">
                        <!-- その他 -->
                        なんかしら
                    </div>
                </div>
            </div>
            <div class="synosis">
                <!-- あらすじ -->
                <%= p.getDescription() %>
            </div>
        </div>
	<%} %>
    </div>
<%} %>


<style>
.main{
	display: flex;
}
.items-area{}
.item{
    margin: 10px;
    width: 500px;
    border: 1px solid #ccc;
}
.title{
    margin: 10px;
    width: 100%;
}
.img-price{
    display: flex;
}
.img{
    width: 150px;
    height: 150px;
}
.price-etc-area{
    border: 1px solid #ccc;
}
.price{
    font-size: 25px;
}
.etc{}
.synosis{
    text-align: center;
}

</style>



</body>
</html>