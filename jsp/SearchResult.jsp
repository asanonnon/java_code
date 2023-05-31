<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="model.User,productModel.Product,java.util.ArrayList,productModel.Serch"%>
    <%
        User user = (User)session.getAttribute("loginUser");
        ArrayList<Product> cart = (ArrayList<Product>)session.getAttribute("cart");
        ArrayList<Product> productList = (ArrayList<Product>)request.getAttribute("productList");
        ArrayList<Product> top = (ArrayList<Product>)request.getAttribute("top");
        ArrayList<Product> ranking = (ArrayList<Product>)request.getAttribute("ranking");
        Product	product = (Product)request.getAttribute("product");
        Serch search = (Serch)request.getAttribute("search");
    %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>検索結果詳細一覧</title>
    <!--▼cssの読み込み-->
    <link rel="stylesheet" type="text/css" href="https://unpkg.com/sanitize.css"><!-- リセット -->
    <link rel="stylesheet" href="css/style.css">
    <!-- 検索部分のcss取り込み -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <!--▼JQueryの読み込み-->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
    <!--ページネーションの取り込み-->
    <script type="text/javascript" src="paginathing.min.js"></script>
    <script>
        /* ハンバーガーメニューのjs */
        $(function() {
            $('.menu-trigger').click(function() {
                if (!$(this).hasClass('active')) {
                    $('.globalMenuSp').addClass('active');
                    document.getElementById("menu07").classList.add("active");
                } else {
                    $('.globalMenuSp').removeClass('active');
                    document.getElementById("menu07").classList.remove("active");
                }
            });
        });

        $(function() {
            $('.trigger').click(function() {
                if ($('.menu-trigger').hasClass('active')) {
                    $('.globalMenuSp').removeClass('active');
                    document.getElementById("menu07").classList.remove("active");
                }
            });
        });

        /* 検索のjs */
        $(function() {
            $('.search_icon').click(function() {
                if (!$('.searchMenu').hasClass('active')) {
                    $('.searchMenu').addClass('active');
                } else {
                    $('.searchMenu').removeClass('active');
                }
            });
        });

        $(function() {
            $('.trigger').click(function() {
                if ($('.searchMenu').hasClass('active')) {
                    $('.searchMenu').removeClass('active');
                }
            });
        });

        /* 検索キーワードのサニタイジング */
        function keyWord_sanitize() {
            console.log("OK");
            var elm = document.seach_keywordform;
            // 値とってくる
            var keyWordInput = elm.keyWord;
            // サニタイジング処理を実行
            var sanitizedKeyWord = sanitize(keyWordInput.value);
            // サニタイジングしたものをformのvalueに代入
            elm.keyWord.value = sanitizedKeyWord;
            // コンソールに変換内容表示
            console.log(sanitizedKeyWord);
            //
            document.seach_keywordform.submit();
        }

        function sanitize(str){
            return String(str)
            .replace(/&/g,"&amp;")
            .replace(/</g,"&lt;")
            .replace(/>/g,"&gt;")
            .replace(/"/g,"&quot;")
        }
    </script>
</head>
<body id="top">
    <!-- ヘッダー ---------------------------------------------------------------------------------------------->
    <header>
        <!-- -----ヘッダーインクルードする -->
        <img class="header_background" src="img/header_background2.jpeg">
        <a href="/TopServlet"><img class="header_logo" src="img/mainlogo.png" alt="角笛書店"></a><!-- path入力 /////////-->
        <!-- アイコン一覧 -->
        <div class="icon">
        <%if(user == null){ %>
            <a href="/tunobue/Login"><!-- path入力 //////--><img class="login_icon" src="img/login_icon.png" alt="ログイン"></a>
        <%} %>
            <img class="search_icon" src="img/search_icon.png" alt="検索">
            <a href="/tunobue/CartViewServlet"><!-- path入力 //////--><img class="cart_icon" src="img/cart_icon.png" alt="カート"></a>
        </div>
        <!-- 検索メニュー ============-->
        <div class="searchMenu">
            <form target="_blank" name="seach_keywordform" action="/tunobue/SerchServlet" method="post"><!-- pathとmethod入力 //////////////-->
                <ul>
                    <li>
                        <input class="keyWord" type="text" name="keyWord" placeholder="キーワード検索:タイトル、作者etc">
                        <input type="button" value="&#xf002;" onclick="keyWord_sanitize();">
                    </li>
            </form>
            <form action="/tunobue/SerchServlet" method="post"><!-- pathとmethod入力 //////////////-->
                <li>
                    <p class="search_item">カテゴリー</p>
                    <div class="category">
                        <div><label><input type="radio" name="category" value="すべて">全て</label></div>
                        <div><label><input type="radio" name="category" value="小説" >小説</label></div>
                        <div><label><input type="radio" name="category" value="ライトノベル">ライトノベル</label></div>
                        <div><label><input type="radio" name="category" value="エッセイ">エッセイ</label></div>
                        <div><label><input type="radio" name="category" value="漫画">漫画</label></div>
                        <div><label><input type="radio" name="category" value="図鑑" >図鑑</label></div>
                        <div><label><input type="radio" name="category" value="実用書">実用書</label></div>
                        <div><label><input type="radio" name="category" value="ビジネス書">ビジネス書</label></div>
                        <div><label><input type="radio" name="category" value="趣味">趣味</label></div>
                        <div><label><input type="radio" name="category" value="辞書・辞典" >辞書・事典</label></div>
                        <div><label><input type="radio" name="category" value="新書">新書</label></div>
                        <div><label><input type="radio" name="category" value="洋書">洋書</label></div>
                        <div><label><input type="radio" name="category"  value="その他">その他</label></div>
                    </div>
                </li>
                <li class="border">
                    <p class="search_item">ジャンル</p>
                    <div class="genre">
                        <div><label><input type="radio" name="genre" value="すべて">全て</label></div>
                        <div><label><input type="radio" name="genre" value="ファンタジー">ファンタジー</label></div>
                        <div><label><input type="radio" name="genre" value="ミステリーサスペンス">ミステリーサスペンス</label></div>
                        <div><label><input type="radio" name="genre" value="ノンフィクション">ノンフィクション</label></div>
                        <div><label><input type="radio" name="genre" value="エンターテイメント">エンターテイメント</label></div>
                        <div><label><input type="radio" name="genre" value="恋愛">恋愛</label></div>
                        <div><label><input type="radio" name="genre" value="日常">日常</label></div>
                        <div><label><input type="radio" name="genre" value="SF" >SF</label></div>
                        <div><label><input type="radio" name="genre" value="歴史" >歴史</label></div>
                        <div><label><input type="radio" name="genre" value="スポーツ" >スポーツ</label></div>
                        <div><label><input type="radio" name="genre" value="コメディ">コメディ</label></div>
                        <div><label><input type="radio" name="genre" value="医療">医療</label></div>
                        <div><label><input type="radio" name="genre" value="サイエンス">サイエンス</label></div>
                        <div><label><input type="radio" name="genre" value="その他" >その他</label></div>
                    </div>
                    <input class="search_radio" type="submit" value="カテゴリー・キーワード検索">
                </li>
            </ul>
        </form>
        </div>
        <%if(user == null){ %>
            <a href="/Login"><!-- path入力 //////--><p class="login_text"><!--ログイン--></p></a>
        <%}else{ %>
            <a href="/tunobue/UserEditServlet"><!-- path入力 //////--><p class="hello_name"><%=user.getName() %>さん</p><!-- 名前入力 ////////--></a>
        <%} %>
        <!-- ハンバーガーメニュー ==========-->
        <div class="hamburger">
            <button class="menu-trigger" id="menu07">
                <span></span>
                <span></span>
                <span></span>
            </button>
        </div>
        <nav class="globalMenuSp">
            <ul>
            <li><a href="/tunobue/TopServlet">TOP</a></li><!-- path入力 ////////-->
            <li><a href="/tunobue/RankingServlet">Ranking</a></li><!-- path入力 ////////-->
            <li><a href="/tunobue/ShopInfoServlet">Shop</a></li><!-- path入力 ////////-->
            <li><a href="/tunobue/UserEditServlet">Account</a></li><!-- path入力 ////////-->
            <li><a href="/tunobue/logoutServlet">Logout</a></li><!-- path入力 ////////-->
            </ul>
        </nav>
    </header>
    <main id="searchResult" class="trigger">
        <%if(search.getKeyward() == null){%>
        <h2><%=search.getCategory().replaceAll("%","")%>
        <%if(search.getCategory().equals("") || search.getGenre().equals("")){ %>/<%} %>
        <%=search.getGenre().replaceAll("%","")%>の検索結果</h2><!-- 検索内容入力 //////////-->
        <%}else{%>
            <h2><%=search.getKeyward().replaceAll("%","")%>の検索結果</h2>
        <%}%>
	<div class="item_search_page">

	 	<%
	 	int i = productList.size()/5+1;
	 	int e = 0;
	 	System.out.println(i);


	 	%>
	 	<%
	 	while(e < i){
	 	int a = 1;
	 	%>
	        <div class="item_search_list">
			<%
			for(Product p :productList){
		    %>
	            <div class="item_search_img">
	                <a href="/WEB-INF/jsp/ProductDetail.jsp?id=<%=p.getProductId()%>"><img src="book_img/<%=p.getPic01() %>" alt="akashiya"></a><!-- srcとalt入力 ///////////////////////////-->
	                <div class="detail">
	                    <p class="title"><%=p.getProductTitle() %></p><!-- タイトル入力 ///////////////////////////-->
	                    <p>著：<%=p.getAuthor() %></p><!-- 著者のみ入力 ///////////////////////////-->
	                    <p><%=p.getPrice() %>円</p><!-- 価格入力 ///////////////////////////-->
	                </div>
	            </div>
	            <%if(a % 5 == 0){ %>
	            </div>
        		<hr>
        		<%}else if(a == productList.size() && a%5!=0){%>
        		</div>
        		<hr>
        		<%} %>
			<%a++;}%>

		<%e++;} %>





	</div>
    </main>
    <!-- フッター ---------------------------------------------------------------------------------------------->
    <footer class="trigger">
        <p class="sign">&copy;角笛書店</p>
        <a href=""><!-- path入力 //////////////--><img class="footer_logo" src="img/sublogo.png"></a>
    </footer>
</body>
</html>