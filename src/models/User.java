package models;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class User {
	private SimpleIntegerProperty id;
	private SimpleBooleanProperty isAdmin;
	
	public void SetId(int id) {
		this.id = new SimpleIntegerProperty(id);
	}
	
	public void SetIsAdmin(boolean isAdmin){
		this.isAdmin = new SimpleBooleanProperty(isAdmin);
	}
	
	public int GetId() {
		return this.id.getValue();
	}
	
	public boolean GetIsAdmin() {
		return this.isAdmin.getValue();
	}
}
