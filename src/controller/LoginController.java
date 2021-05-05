package controller;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
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
	  
	  
}
