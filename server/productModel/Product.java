package productModel;

import java.io.Serializable;
import java.sql.Timestamp;

public class Product implements Serializable {
	private int productId;
	private String productTitle;
	private String category;
	private String genre;
	private String publisher;
	private String description;
	private String review;
	private String movie;
	private int price;
	private int stock;
	private String pic01;
	private int pageView;
	private Timestamp productDate;
	private String author;

	public Product() {}

    public Product(int productId, String productTitle, String category, String genre, String publisher, String description, String review, String movie, int price, int stock, String pic01, int pageView, Timestamp productDate,String author) {
        this.productId = productId;
        this.productTitle = productTitle;
        this.category = category;
        this.genre = genre;
        this.publisher = publisher;
        this.description = description;
        this.review = review;
        this.movie = movie;
        this.price = price;
        this.stock = stock;
        this.pic01 = productId + "_001.jpg";
        this.pageView = pageView;
        this.productDate = productDate;
        this.author = author;
    }

    //setter.getter
    public int getProductId() {	return productId;}
	public void setProductId(int productId) {this.productId = productId;}
	public String getProductTitle() {return productTitle;}
    public void setProductTitle(String title) {
    	String[] str = title.split("/");
		this.productTitle = str[0];
    }
    public String getCategory() {return category;}
    public void setCategory(String category) {this.category = category;}
    public String getGenre() {return genre;}
    public void setGenre(String genre) {this.genre = genre;}
    public String getPublisher() {return publisher;}
    public void setPublisher(String publisher) {this.publisher = publisher;}
    public String getDescription() {return description;}
    public void setDescription(String description) {this.description = description;}
    public String getReview() {return review;}
    public void setReview(String review) {this.review = review;}
    public String getMovie() {return movie;}
    public void setMovie(String movie) {this.movie = movie;}
    public int getPrice() {return price;}
    public void setPrice(int price) {this.price = price;}
    public int getStock() {return stock;}
    public void setStock(int stock) {this.stock = stock;}
    public String getPic01() {return pic01;}
    public void setPic01(String pic01) {this.pic01 = pic01;}
	public int getPageView() {return pageView;}
	public void setPageView(int pageView) {this.pageView = pageView;}
	public Timestamp getProductDate() {return productDate;}
	public void setProductDate(Timestamp productDate) {this.productDate = productDate;}
	public String getAuthor() {return author;}
	public void setAuthor(String title) {
		String[]str = title.split("/");
		this.author = str[1];
		}

	public void seｔTitle(String title) {
		String[]str = title.split("/");
		this.author = str[1];
		this.productTitle = str[0];
		}


}
