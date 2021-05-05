/*
 * author 	: Tristin Wen
 * date 	: 2021/05/05
 */
package controllers;

import applications.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import models.LoginModel;

public class LoginController {

	  @FXML private TextField txtUserName;
	  @FXML private TextField txtPassword;
	  @FXML private Button btnSubmit;
	  @FXML private Text txtErrorMsg;
	  
	  LoginModel model;
	  public LoginController() {
		  model = new LoginModel();
	  }
	  
	  public void Login() { 
		  
		  txtErrorMsg.setText("");
		  
		  String username = this.txtUserName.getText();
		  String password = this.txtPassword.getText();

		  if (username == null || username.trim().equals("")) {
			  txtErrorMsg.setText("Username Cannot be empty or spaces");
			  return;
		  }
		  if (password == null || password.trim().equals("")) {
			  txtErrorMsg.setText("Password Cannot be empty or spaces");
			  return;
		  }
		  if (username == null || username.trim().equals("") && (password == null || password.trim().equals(""))) {
			  txtErrorMsg.setText("User name / Password Cannot be empty or spaces");
			  return;
		  }
	  }
	  
	  public void checkCredentials(String username, String password) {
			Boolean isValid = model.getCredentials(username, password);
			if (!isValid) {
				txtErrorMsg.setText("User does not exist!");
				return;
			}
			try {
				AnchorPane root;
				if (model.isAdmin() && isValid) {
					// If user is admin, inflate admin view

					root = (AnchorPane) FXMLLoader.load(getClass().getResource("/views/AdminView.fxml"));
					Main.stage.setTitle("Admin View");

				} else {
					// If user is customer, inflate customer view

					root = (AnchorPane) FXMLLoader.load(getClass().getResource("/views/ClientView.fxml"));
					// ***Set user ID acquired from db****
					int user_id = model.getId();  
					ClientController.setUserid(user_id);
					Main.stage.setTitle("Client View");
				}

				Scene scene = new Scene(root);
				Main.stage.setScene(scene);

			} catch (Exception e) {
				System.out.println("Error occured while inflating view: " + e);
			}

		}
	  
}
