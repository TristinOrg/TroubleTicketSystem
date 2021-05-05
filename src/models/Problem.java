package models;

public class Problem {
	private int id;
	private String desc;
	private String date;
	
	public void SetId(int id) {
		this.id = id;
	}
	
	public void SetDesc(String desc) {
		this.desc = desc;
	}
	
	public void SetDate(String date) {
		this.date = date;
	}
	
	public int GetId() {
		return this.id;
	}
	
	public String GetDesc() {
		return this.desc;
	}
	
	public String GetDate() {
		return this.desc;
	}
}
