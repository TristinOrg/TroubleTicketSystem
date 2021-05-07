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
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import models.ClientModel;
import models.Problem;

public class ClientController implements Initializable  {
	public static int userid;
	
	@FXML private Button btnDescribe;
	@FXML private Button btnProblemList;
	@FXML private Button btnLogout;
	
	@FXML private Group groupDescribe;
	@FXML private TextField txtDesc;
	@FXML private Button btnSubmit;
	@FXML private Text txtErrorMsg;
	
	@FXML private Group groupProblemList;
	@FXML private TableView<Problem> tableList;
	@FXML private TableColumn<Problem, Integer> colID;
	@FXML private TableColumn<Problem, Integer> colUserID;
	@FXML private TableColumn<Problem, String> colDesc;
	@FXML private TableColumn<Problem, String> colDate;
	
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
		groupDescribe.setVisible(true);
		groupProblemList.setVisible(false);
		txtErrorMsg.setText("");
		txtDesc.setText("");
	}
	
	//btnDescribe click event
    public void OnBtnDescribeClicked() {
    	groupDescribe.setVisible(true);
		groupProblemList.setVisible(false);
    }
    
    //btnProblemList click event
    public void OnBtnProblemListClicked() {
     	groupDescribe.setVisible(false);
     	groupProblemList.setVisible(true);
     	
     	colID.setCellValueFactory(new PropertyValueFactory<Problem,Integer>("id"));
    	colUserID.setCellValueFactory(new PropertyValueFactory<Problem,Integer>("userId"));
     	colDesc.setCellValueFactory(new PropertyValueFactory<Problem, String>("desc"));
     	colDate.setCellValueFactory(new PropertyValueFactory<Problem, String>("date"));
     	
     	ArrayList<Problem> problemList = model.GetProblemList();
     	if(problemList == null)
     	{
     		System.out.println("Problem list is null");
     		return;
     	}
     	
     	if(problemList.size() == 0)
     	{
     		System.out.println("The size of the problem list is 0");
     		return;
     	}
     	tableList.getItems().setAll(problemList);
    }
    
    //btnLogout click event
    public void	OnBtnLogoutClicked() {
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
    
    //btnSubmit click event
    public void OnBtnSubmitClicked() {
    	String desc = txtDesc.getText();
		boolean isSucced = model.SubmitNewProblem(ClientController.userid, desc);
		System.out.println("IsSucceed "+isSucced);
		txtErrorMsg.setText(isSucced?"Submit succed":"Submit failed");
    }
}