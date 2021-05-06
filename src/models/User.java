package models;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class User {
	private SimpleIntegerProperty id;
	private SimpleStringProperty userName;
	private SimpleStringProperty password;
	private SimpleBooleanProperty isAdmin;
	
	public User() 
	{
	   id 		= new SimpleIntegerProperty();
	   userName = new SimpleStringProperty();
	   password = new SimpleStringProperty();
	   isAdmin  = new SimpleBooleanProperty();
	}
	
	public void SetId(int id) {
		this.id.set(id);
	}
	
	public void SetUserName(String userName)
	{
		this.userName.set(userName);
	}
	
	
	public void SetPassword(String password)
	{
		this.password.set(password);
	}
	
	public void SetIsAdmin(boolean isAdmin){
		this.isAdmin.set(isAdmin);
	}
	
	public int GetId() {
		return this.id.get();
	}
	
	public String GetPassword()
	{
		return this.password.get();
	}
	
	public String GetUserName()
	{
		return this.userName.get();
	}
	
	public boolean GetIsAdmin() {
		return this.isAdmin.get();
	}
}
