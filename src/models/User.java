package models;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class User {
	private SimpleIntegerProperty id;
	private StringProperty userName;
	private SimpleStringProperty password;
	private SimpleBooleanProperty isAdmin;
	
	public void SetId(int id) {
		this.id = new SimpleIntegerProperty(id);
	}
	
	public void SetUserName(String userName)
	{
		this.userName = new SimpleStringProperty(userName);
	}
	
	
	public void SetPassword(String password)
	{
		this.password = new SimpleStringProperty(password);
	}
	
	public void SetIsAdmin(boolean isAdmin){
		this.isAdmin = new SimpleBooleanProperty(isAdmin);
	}
	
	public int GetId() {
		return this.id.getValue();
	}
	
	public String GetPassword()
	{
		return this.password.getValue();
	}
	
	public String GetUserName()
	{
		return this.userName.getValue();
	}
	
	public boolean GetIsAdmin() {
		return this.isAdmin.getValue();
	}
}
