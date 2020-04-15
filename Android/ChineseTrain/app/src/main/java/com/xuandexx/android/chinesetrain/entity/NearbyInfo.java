package entity;

/**
 * 附近的洗车店信息
 * 
 * @author 聂浩
 * @version V1.0 创建时间：2014-10-22 上午10:46:35
 */
public class NearbyInfo {
	private String longitude;// 经度
	private String latitude;// 纬度
	private String name;
	private String picUrl;// 图片的路径
	private String distance;// 距离
	private float Score;// 分数
	private String map;// 查看地图
	private String address;// 洗车店地址

	public String getMap() {
		return map;
	}

	public void setMap(String map) {
		this.map = map;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public String getDistance() {
		return distance;
	}

	public void setDistance(String distance) {
		this.distance = distance;
	}

	public float getScore() {
		return Score;
	}

	public void setScore(float score) {
		Score = score;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
