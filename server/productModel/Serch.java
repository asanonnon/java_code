package productModel;

import java.io.Serializable;

public class Serch implements Serializable{
	//フィールド
	private String category;
	private String genre;
	private String keyward;
	//コンストラクタ
	public Serch() {}
	//カテゴリ、ジャンル検索のコンストラクタ
	public Serch(String category, String genre) {
		this.category = "%"+category+"%";
		this.genre = "%"+genre+"%";
	}
	//キーワード検索のコンストラクタ
	public Serch(String keyward) {
		this.keyward = "%"+keyward+"%";
	}

	//getter/setter

	public String getCategory() {return category;}
	public String getGenre() {return genre;}
	public String getKeyward() {return keyward;}
}
