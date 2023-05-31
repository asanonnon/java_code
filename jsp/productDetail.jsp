<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="model.User,productModel.Product,java.util.ArrayList,productModel.Serch,productModel.Cart"%>
    <%
        User user = (User)session.getAttribute("loginUser");
        Cart cart = (Cart)session.getAttribute("cart");
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
    <title>商品詳細画面</title>
    <!--▼cssの読み込み-->
    <link rel="stylesheet" type="text/css" href="https://unpkg.com/sanitize.css"><!-- リセット -->
    <link rel="stylesheet" href="css/style.css">
    <!-- 検索部分のcss取り込み -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <!-- アラートのjs取り込み -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/2.1.2/sweetalert.min.js"></script>
    <!--▼JQueryの読み込み-->
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

        /* サニタイジング化 */
        function sanitize(str){
            return String(str)
            .replace(/&/g,"&amp;")
            .replace(/</g,"&lt;")
            .replace(/>/g,"&gt;")
            .replace(/"/g,"&quot;")
        }

        /* カートに入れるクリック */
        function cart_alert() {
        	console.log(document.sold_form.countParam.value);
            if (document.sold_form.countParam.value == "") {
                swal("数量を選択してください");
            } else {

                document.sold_form.submit();
            }
        }

        /* 今すぐ購入クリック */
        function cart_now() {
            var elm = document.sold_form
            var countParameter = "";
            var id = "<%=product.getProductId() %>";
            if (elm.countParam.value == "") {
                swal("数量を選択してください");
            } else {
            	document.sold_form.action="/tunobue/AddCartServlet?" + "id=" + id +"&countParamr=" + countParameter+"&purchase=str";
            	document.sold_form.method="post";
            	document.sold_form.submit();
                // idと購入数のパラメータを持ってカートへ飛ぶ
                // id = (javaにてidパラメータ入力)

            }
        }
    </script>
</head>
<body id="top">
    <!-- ヘッダー ---------------------------------------------------------------------------------------------->
    <header>
        <!-- -----ヘッダーインクルードする -->
        <img class="header_background" src="img/header_background2.jpeg">
        <a href="/tunobue/TopServlet"><img class="header_logo" src="img/mainlogo.png" alt="角笛書店"></a><!-- path入力 /////////-->
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
<main id="productDetail" class="trigger">

    <div class="item_pickup">
        <img src="book_img/<%=product.getPic01()%>" alt="akashiya"><!-- srcとalt入力 ///////////////////////////-->
        <div class="item_pickup_text">
            <h2>かたみ歌</h2><!-- タイトルのみ入力 ///////////////////////////-->
            <p>著：<%=product.getAuthor()%>/ <%=product.getPublisher()%></p><!-- 著者のみと出版社入力 //////////////////-->
            <h3>あらすじ</h3>
            <p class="synopsis">
                <%=product.getDescription()%>
            </p><!-- あらすじ入力 //////////////////-->
        </div>
        <!--***** if文にて在庫ある場合"soldArea"表示 *****-->
        <div class="soldArea">
            <form name="sold_form" action="/tunobue/AddCartServlet?id=<%=product.getProductId() %>" method="post"><!-- method入力 /////////-->
                <p>¥<%=product.getPrice()%></p><!-- 価格入力 //////////////////-->
                <div>
                    <p class="howmany">数量：</p>
                    <input type="number" name="countParam" min="1" max="3"><!-- max数入力,nameつけてない //////-->
                </div>
                <button type="button" class="custom-btnB btn_cartin" onclick="cart_alert();"><span>カートに入れる</span></button>
            </form>
            <button type="button" class="custom-btnB btn_now" onclick="cart_now()"><span>今すぐ購入</span></button>
        </div>
        <!--******************************************-->

        <!--***** if文にて在庫ない場合"soldoutArea"表示 *****-->
        <%if(product.getStock() <= 0){%>
        <div class="soldoutArea">
            <div class="soldout">在庫切れ</div>
        </div>
        <%}%>
        <!--*********************************************-->
    </div>
    <a href="#"><button type="button" class="custom-btn btn_gosearch" onclick="history.back(); return false;">検索一覧へ戻る</button></a>
</main>
    <!-- フッター ---------------------------------------------------------------------------------------------->
    <footer class="trigger footerA">
        <p class="sign">&copy;角笛書店</p>
        <a href="/tunobue/TopServlet"><!-- path入力 //////////////--><img class="footer_logo" src="img/sublogo.png"></a>
    </footer>
</body>
</html>