package entity;

/**
 * 代办验车商户
 * 
 * @author Liting
 * 
 */
public class Merchants {
	private String picture_uri;
	private String phone;
	private String name;
	private double rating;
	private double price;

	/**
	 * 构造方法
	 * 
	 * @param picture_uri
	 *            :图片的网络路径,判断如果本地有缓存,读取本地,如果没有缓存,读取网络资源
	 * @param phone
	 *            :商户电话
	 * @param name
	 *            :商户名字
	 * @param rating
	 *            :商户评分
	 * @param price
	 *            :商户收费
	 */
	public Merchants(String picture_uri, String phone, String name,
			double rating, double price) {
		super();
		this.picture_uri = picture_uri;
		this.phone = phone;
		this.name = name;
		this.rating = rating;
		this.price = price;
	}

	public String getPicture_uri() {
		return picture_uri;
	}

	public String getPhone() {
		return phone;
	}

	public String getName() {
		return name;
	}

	public double getRating() {
		return rating;
	}

	public double getPrice() {
		return price;
	}

}
