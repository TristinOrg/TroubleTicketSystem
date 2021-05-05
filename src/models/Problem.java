package models;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Problem {
	private SimpleIntegerProperty id;
	private SimpleStringProperty desc;
	private SimpleStringProperty date;
	
	public void SetId(int id) {
		this.id = new SimpleIntegerProperty(id);
	}
	
	public void SetDesc(String desc) {
		this.desc = new SimpleStringProperty(desc);
	}
	
	public void SetDate(String date) {
		this.date = new SimpleStringProperty(date);
	}
	
	public int GetId() {
		return this.id.getValue();
	}
	
	public String GetDesc() {
		return this.desc.getValue();
	}
	
	public String GetDate() {
		return this.date.getValue();
	}
}
