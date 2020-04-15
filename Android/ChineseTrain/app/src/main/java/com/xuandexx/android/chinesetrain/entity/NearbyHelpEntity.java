package entity;

/**
 * 收到的附近车友求助
 * 
 * @author Lampo
 * 
 */
public class NearbyHelpEntity {

	private String icon;
	private String name;
	private String sex;
	private String age;
	private String phone;
	private String distance;// 距离
	// 求助的主要内容
	private String theme;
	// 求助人自己填写的内容
	private String othertheme;

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getDistance() {
		return distance;
	}

	public void setDistance(String distance) {
		this.distance = distance;
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public String getOthertheme() {
		return othertheme;
	}

	public void setOthertheme(String othertheme) {
		this.othertheme = othertheme;
	}

}
