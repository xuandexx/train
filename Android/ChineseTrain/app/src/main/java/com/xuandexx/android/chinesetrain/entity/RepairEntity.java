package entity;

//维修保养
public class RepairEntity {

	private String icon;// 图片
	private String shop_name;// 名称
	private String shop_distance;// 距离
	private String shop_latitude_longitude;// 经纬度
	private String shop_daaress;// 地址
	private String shop_level;// 等级

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getShop_name() {
		return shop_name;
	}

	public void setShop_name(String shop_name) {
		this.shop_name = shop_name;
	}

	public String getShop_distance() {
		return shop_distance;
	}

	public void setShop_distance(String shop_distance) {
		this.shop_distance = shop_distance;
	}

	public String getShop_latitude_longitude() {
		return shop_latitude_longitude;
	}

	public void setShop_latitude_longitude(String shop_latitude_longitude) {
		this.shop_latitude_longitude = shop_latitude_longitude;
	}

	public String getShop_daaress() {
		return shop_daaress;
	}

	public void setShop_daaress(String shop_daaress) {
		this.shop_daaress = shop_daaress;
	}

	public String getShop_level() {
		return shop_level;
	}

	public void setShop_level(String shop_level) {
		this.shop_level = shop_level;
	}
}
