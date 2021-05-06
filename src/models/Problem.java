package models;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Problem {
	private SimpleIntegerProperty id;
	private SimpleIntegerProperty userId;
	private SimpleStringProperty desc;
	private SimpleStringProperty date;
	
	public Problem() 
	{
		id 		= new SimpleIntegerProperty();
		userId 	= new SimpleIntegerProperty();
		desc 	= new SimpleStringProperty();
		date 	= new SimpleStringProperty();
	}
	
	public void SetId(int id) {
		this.id.set(id);
	}
	
	public void SetUserId(int userId) {
		this.userId.set(userId);
	}
	
	public void SetDesc(String desc) {
		this.desc.set(desc);
	}
	
	public void SetDate(String date) {
		this.date.set(date);
	}
	
	public int GetId() {
		return this.id.get();
	}
	
	public int GetUserId() {
		return this.userId.get();
	}
	
	public String GetDesc() {
		return this.desc.get();
	}
	
	public String GetDate() {
		return this.date.get();
	}
}
