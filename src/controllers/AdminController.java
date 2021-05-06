/*
 * author 	: Tristin Wen
 * date 	: 2021/05/05
 */
package controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import models.AdminModel;
import models.Problem;
import models.User;

public class AdminController implements Initializable {

	@FXML private Button btnViewAccounts;
	@FXML private Button btnUpdateAccount;
	@FXML private Button btnViewProblems;
	@FXML private Button btnUpdateProblem;
	
	@FXML private Group groupViewAccounts;
	@FXML private TableView<User> tableViewAccount;
	@FXML private TableColumn<User, Integer> colAccountID;
	@FXML private TableColumn<User, String>  colAccountUserName;
	@FXML private TableColumn<User, String>  colAccountPassword;
	@FXML private TableColumn<User, Boolean> colAccountIsAdmin;
	
	@FXML private Group groupUpdateAccounts;
	@FXML private TextField txtAccountID;
	@FXML private TextField txtAccountUserName;
	@FXML private TextField txtAccountPassword;
	@FXML private CheckBox checkAccountIsAdmin;
	@FXML private CheckBox checkAccountIsAdd;
	@FXML private CheckBox checkAccountIsDelete;
	@FXML private Button btnAccountUpdate;
	
	@FXML private Group groupViewProblems;
	@FXML private TableView<Problem> tableViewProblem;
	@FXML private TableColumn<Problem, Integer> colProblemID;
	@FXML private TableColumn<Problem, String>  colProblemDesc;
	@FXML private TableColumn<Problem, String>	colProblemDate;
	
	@FXML private Group groupUpdateProblem;
	@FXML private TextField txtProblemID;
	@FXML private TextField txtProblemDesc;
	@FXML private CheckBox checkProblemIsAdd;
	@FXML private CheckBox checkProblemIsDelet;
	@FXML private Button btnProblemUpdate;
	
	@FXML private Text txtErrorMsg;
	
	AdminModel model;
	public AdminController() {
		model = new AdminModel();
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		groupViewAccounts.setVisible(false);
		groupViewProblems.setVisible(false);
		groupUpdateAccounts.setVisible(false);
		groupUpdateProblem.setVisible(false);
	}
	
	public void OnBtnViewAccountsClicked() {
		groupViewAccounts.setVisible(true);
		groupViewProblems.setVisible(false);
		groupUpdateAccounts.setVisible(false);
		groupUpdateProblem.setVisible(false);
		
		ArrayList<User> userList = model.GetAccountsData();
		if (userList!=null && userList.size()>0) 
		{
	     	colAccountID.setCellValueFactory(new PropertyValueFactory<User,Integer>("id"));
	     	colAccountUserName.setCellValueFactory(new PropertyValueFactory<User, String>("userName"));
	     	colAccountPassword.setCellValueFactory(new PropertyValueFactory<User, String>("password"));
	     	colAccountIsAdmin.setCellValueFactory(new PropertyValueFactory<User, Boolean>("isAdmin"));
	     	
	     	tableViewAccount.getItems().addAll(userList);
		}
	}
	
	public void OnBtnUpdateAccountClicked() {
		groupViewAccounts.setVisible(false);
		groupViewProblems.setVisible(false);
		groupUpdateAccounts.setVisible(true);
		groupUpdateProblem.setVisible(false);
	}
	
	public void OnBtnViewProblemsClicked() {
		groupViewAccounts.setVisible(false);
		groupViewProblems.setVisible(true);
		groupUpdateAccounts.setVisible(false);
		groupUpdateProblem.setVisible(false);
	}
	
	public void OnBtnUpdateProblemClicked() {
		groupViewAccounts.setVisible(false);
		groupViewProblems.setVisible(false);
		groupUpdateAccounts.setVisible(false);
		groupUpdateProblem.setVisible(true);
	}
	
	public void OnBtnAccountUpdateClicked() {
		
	}
	
	public void OnBtnProblemUpdateClicked() {
		
	}
}
