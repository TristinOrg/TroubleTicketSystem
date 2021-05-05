/*
 * author 	: Tristin Wen
 * date 	: 2021/05/05
 */
package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import models.ClientModel;

public class ClientController implements Initializable  {
	static int userid;
	
	@FXML private TextField txtDesc;
	@FXML private Button btnSubmit;
	@FXML private Text txtErrorMsg;
	  
	ClientModel model;
	
	public ClientController() {
	  model = new ClientModel();
	}
	  
	public static void setUserid(int user_id) {
		userid = user_id;
		System.out.println("Welcome id " + userid);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
}
