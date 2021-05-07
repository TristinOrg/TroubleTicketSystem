/*
 * author 	: Tristin Wen
 * date 	: 2021/05/05
 */
package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import applications.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import models.AdminModel;
import models.ClientModel;
import models.Problem;
import models.User;

public class AdminController implements Initializable {

	@FXML private Button btnViewAccounts;
	@FXML private Button btnUpdateAccount;
	@FXML private Button btnViewProblems;
	@FXML private Button btnUpdateProblem;
	@FXML private Button btnLogout;
	
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
	@FXML private TableColumn<Problem, Integer> colProblemUserID;
	@FXML private TableColumn<Problem, String>  colProblemDesc;
	@FXML private TableColumn<Problem, String>	colProblemDate;
	
	@FXML private Group groupUpdateProblem;
	@FXML private TextField txtProblemID;
	@FXML private TextField txtProblemDesc;
	@FXML private CheckBox checkProblemIsAdd;
	@FXML private CheckBox checkProblemIsDelete;
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
		txtErrorMsg.setText("");
		
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
		txtErrorMsg.setText("");
		
		ArrayList<Problem> problemList = model.GetProblemData();
		if (problemList!=null && problemList.size()>0) 
		{
	     	colProblemID.setCellValueFactory(new PropertyValueFactory<Problem,Integer>("id"));
	     	colProblemUserID.setCellValueFactory(new PropertyValueFactory<Problem, Integer>("userId"));
	     	colProblemDesc.setCellValueFactory(new PropertyValueFactory<Problem, String>("desc"));
	     	colProblemDate.setCellValueFactory(new PropertyValueFactory<Problem, String>("date"));
	     	
	     	tableViewProblem.getItems().setAll(problemList);
		}
	}
	
	public void OnBtnUpdateProblemClicked() {
		groupViewAccounts.setVisible(false);
		groupViewProblems.setVisible(false);
		groupUpdateAccounts.setVisible(false);
		groupUpdateProblem.setVisible(true);
	}
	
	public void OnBtnLogoutClicked() 
	{
		AnchorPane root = null;
		try {
			root = (AnchorPane) FXMLLoader.load(getClass().getResource("/views/LoginView.fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Scene scene 	= new Scene(root);
		Main.stage.setTitle("Login View");
		Main.stage.setScene(scene);
	}
	
	public void OnBtnAccountUpdateClicked() {
		String id 			= txtAccountID.getText();
		String pwd 			= txtAccountPassword.getText();
		String userName 	= txtAccountUserName.getText();
		boolean isAdd 		= checkAccountIsAdd.isSelected();
		boolean isDelete 	= checkAccountIsDelete.isSelected();
		boolean isAdmin  	= checkAccountIsAdmin.isSelected();
		boolean isSucceed 	= model.UpdateAccount(Integer.parseInt(id), userName, pwd, isAdd, isDelete, isAdmin);
	    System.out.println("IsSucced "+isSucceed);
		txtErrorMsg.setText(isSucceed?"Update Succeed":"Update failed");
	}
	
	public void OnBtnProblemUpdateClicked() {
		String id 			= txtProblemID.getText();
		String description 	= txtProblemDesc.getText();
		boolean isAdd 		= checkProblemIsAdd.isSelected();
		boolean isDelete 	= checkProblemIsDelete.isSelected();
		boolean isSucceed 	= model.UpdateProblem(Integer.parseInt(id),ClientController.userid, description, isAdd, isDelete);
	    txtErrorMsg.setText(isSucceed?"Update Succeed":"Update failed");
	    System.out.println("IsSucceed "+isSucceed);
	}
}
