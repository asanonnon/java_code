<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.User,productModel.Product,java.util.ArrayList,productModel.Cart"%>
<%
	User user = (User)session.getAttribute("loginUser");
	Cart cart = (Cart)session.getAttribute("cart");
	ArrayList<Product> prductList = (ArrayList<Product>)request.getAttribute("productList");
	ArrayList<Product> top = (ArrayList<Product>)request.getAttribute("top");
	ArrayList<Product> ranking = (ArrayList<Product>)request.getAttribute("ranking");
	Product	product = (Product)request.getAttribute("product");

	System.out.println(top);
%>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>トップ画面</title>
    <!-- css取り込み -->
    <link rel="stylesheet" type="text/css" href="https://unpkg.com/sanitize.css"><!-- リセット -->
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <!-- 検索部分のcss取り込み -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <!-- HEROスライドのcss(CDN) -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/@splidejs/splide@4.0.7/dist/css/splide.min.css">
    <!-- HEROスライドのjs取り込み -->
    <script src="https://cdn.jsdelivr.net/npm/@splidejs/splide@latest/dist/js/splide.min.js"></script>
    <!-- jquery取り込み -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
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

        /* フェードインのjs */
        $(function () {
            // Right
            const fade_bottom = 250; // 画面下からどの位置でフェードさせるか(px)
            const fade_move = 100; // どのぐらい要素を動かすか(px)
            const fade_time = 800; // フェードの時間(ms)
            // Left
            const fade_moveL = -100; // どのぐらい要素を動かすか(px)

            // フェード前のcssを定義 Right
            $(".fade-sideRight").css({
                opacity: 0,
                transform: "translateX(" + fade_move + "px)",
                transition: fade_time + "ms",
            });
            // フェード前のcssを定義 Left
            $(".fade-sideLeft").css({
                opacity: 0,
                transform: "translateX(" + fade_moveL + "px)",
                transition: fade_time + "ms",
            });

            // スクロールまたはロードするたびに実行
            $(window).on("scroll load", function () {
                const scroll_top = $(this).scrollTop();
                const scroll_bottom = scroll_top + $(this).height();
                const fade_position = scroll_bottom - fade_bottom;
                $(".fade-sideLeft, .fade-sideRight").each(function () {
                const this_position = $(this).offset().top;
                if (fade_position > this_position) {
                    $(this).css({
                    opacity: 1,
                    transform: "translateX(0)",
                    });
                }
                });
            });
        });
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
            <form target="_blank" name="keyward" action="/tunobue/SerchServlet" method="post"><!-- pathとmethod入力 //////////////-->
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
                            <div><label><input type="radio" name="category"  value="すべて">全て</label></div>
                            <div><label><input type="radio" name="category" value="小説" >小説</label></div>
                            <div><label><input type="radio" name="category" value="ライトノベル">ライトノベル</label></div>
                            <div><label><input type="radio" name="category"value="エッセイ">エッセイ</label></div>
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
    <!-- メイン ------------------------------------------------------------------------------------------------>
    <main id="index" class="trigger">
        <!-- HEROスライド ================-->
        <div class="splide">
            <div class="splide__track">
                <ul class="splide__list">
                    <li class="splide__slide"><img src="img/main_img1.jpg" alt="">
                        <div class="splide-content" id="s00">
                            <h2>春が二階から降りてきた。</h2><br>
                            <p>重力ピエロ：伊坂幸太郎</p>
                        </div>
                    </li>
                    <li class="splide__slide"><img src="img/main_img3.jpg" alt="">
                        <div class="splide-content" id="s01">
                            <h2>春が二階から降りてきた。</h2><br>
                            <p>重力ピエロ：伊坂幸太郎</p>
                        </div>
                    </li>
                    <li class="splide__slide"><img src="img/main_img4.jpg" alt="">
                        <div class="splide-content" id="s02">
                            <h2>春が二階から降りてきた。</h2><br>
                            <p>重力ピエロ：伊坂幸太郎</p>
                        </div>
                    </li>
                    <li class="splide__slide"><img src="img/main_img5.jpg" alt="">
                        <div class="splide-content" id="s03">
                            <h2>春が二階から降りてきた。</h2><br>
                            <p>重力ピエロ：伊坂幸太郎</p>
                        </div>
                    </li>
                </ul>
            </div>
        </div>
        <!-- 一部商品 ====================-->
        <div class="index_itemArea">
        <!-- ------------------------------------------------------------------- -->
        <%
        int i = 1;
        for(Product p :top){
        %>
            <%if(i %2 ==0){%>
	            <div class="index_item<%=i%>">
	                <a href="/tunobue/ProductDetailServlet?id=<%=p.getProductId()%>"><!-- action入力 (商品詳細へ飛ぶ)///////////////////////////-->
	                    <img class="fade-sideRight" src="book_img/<%=p.getPic01() %>" alt=""><!--srcとalt入力 ///////////////////////////-->
	                </a>
	            </div>
            <%}else{ %>

            <div class="index_item<%=i%>">
                <a href="/tunobue/ProductDetailServlet?id=<%=p.getProductId()%>"><!-- action入力 (商品詳細へ飛ぶ)///////////////////////////-->
                    <img class="fade-sideLeft" src="book_img/<%=p.getPic01() %>" alt=""><!--srcとalt入力 ///////////////////////////-->
                </a>
            </div>
            <%} %>

        <%i++;} %>
            <!-- -------------------------------------- -->
            <!-- この後4回繰り返し //////////////////////////////////////////////////////////////////////--->

        </div>
    </main>
    <!-- フッター ---------------------------------------------------------------------------------------------->
    <footer class="trigger">
        <p class="sign">&copy;角笛書店</p>
        <a href="/tunobue/TopServlet"><!-- path入力 //////////////--><img class="footer_logo" src="img/sublogo.png"></a>
    </footer>
    <!-- HEROスライドのjs -->
    <script>
        var splide = new Splide( '.splide', {
            type   : 'loop',
            padding: '5rem',
            with: '80%',
            autoplay: true,
            interval: 6000,
            speed: 4000,
            pauseOnFocus: true,
        });

        splide.mount();
    </script>
</body>
</html>