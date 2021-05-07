/*
 * author 	: Tristin Wen
 * date 	: 2021/05/05
 */
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
	
	public void setId(int id) {
		this.id.set(id);
	}
	
	public void setUserId(int userId) {
		this.userId.set(userId);
	}
	
	public void setDesc(String desc) {
		this.desc.set(desc);
	}
	
	public void setDate(String date) {
		this.date.set(date);
	}
	
	public int getId() {
		return this.id.get();
	}
	
	public int getUserId() {
		return this.userId.get();
	}
	
	public String getDesc() {
		return this.desc.get();
	}
	
	public String getDate() {
		return this.date.get();
	}
}
