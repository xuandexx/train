package entity;

import java.util.List;


public class NearbyInfoResponse {
	private List<NearbyInfo> list;
	public List<NearbyInfo> getList(){
		return list;
	}
	public void setList(List<NearbyInfo> list){
		this.list =  list;
	}
}
