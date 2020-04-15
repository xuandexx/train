package entity;

public class SearchInfo {
	private String name;
	private String phone;
	private int Latitude;//纬度
	private int Longitude;//经度
	public int getLatitude() {
		return Latitude;
	}
	public void setLatitude(int latitude) {
		Latitude = latitude;
	}
	public int getLongitude() {
		return Longitude;
	}
	public void setLongitude(int longitude) {
		Longitude = longitude;
	}
	public SearchInfo(String name, String phone, int latitude, int longitude,
			String address) {
		super();
		this.name = name;
		this.phone = phone;
		Latitude = latitude;
		Longitude = longitude;
		this.address = address;
	}
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	private String address;
}
