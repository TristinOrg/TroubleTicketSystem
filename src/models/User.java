package models;

public class User {
	private int id;
	private boolean isAdmin;
	
	public void SetId(int id) {
		this.id = id;
	}
	
	public void SetIsAdmin(boolean isAdmin){
		this.isAdmin = isAdmin;
	}
	
	public int GetId() {
		return this.id;
	}
	
	public boolean GetIsAdmin() {
		return this.isAdmin;
	}
}
