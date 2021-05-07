/*
 * author 	: Tristin Wen
 * date 	: 2021/05/05
 */
package models;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import Dao.DBConnect;
import controllers.ClientController;

public class AdminModel extends DBConnect {
	
	Statement stmt;
	PreparedStatement ppstmt;
	private ArrayList<User> userList = new ArrayList<User>();
	private ArrayList<Problem> problemList = new ArrayList<Problem>();
	
	//get account table
	public ArrayList<User> GetAccountsData() 
	{
		ResultSet rs = null;
		try 
		{
			stmt 		= connect().createStatement();
			String sql 	= "SELECT * from t_wen_users";
			rs 			= stmt.executeQuery(sql);
			
			connect().close();
			
			userList.clear();
			while(rs.next())
			{
				User user = new User();
				user.setId(rs.getInt("ID"));
				user.setUserName(rs.getString("UserName"));
				user.setPassword(rs.getString("Password"));
				user.setIsAdmin(rs.getBoolean("IsAdmin"));
				//System.out.println("ID: "+user.getId()+" UserName: "+user.getUserName()+" Password: "+user.getPassword()+" IsAdmin: "+user.getIsAdmin());
				userList.add(user);
			}
		} 
		catch (SQLException se) 
		{
			se.printStackTrace();
		}
		return userList;
	}
	
	//Update Account
	public boolean UpdateAccount(int userId,String userName,String password,Boolean isAdd,boolean isDelete,Boolean isAdmin)
	{
		try
		{
			String sql = "";
			if (isAdd) 
			{
				sql = "INSERT INTO t_wen_problems(ID,UserID,Description,Date)Values(null,?,?,?)";
			}
			else if(isDelete) 
			{
				sql = "DELETE FROM t_wen_users WHERE ID =?";
			}
			else 
			{
				sql = "UPDATE t_wen_users set Password=?,IsAdmin=? where ID=? or UserName =?";
			}
			
			ppstmt = connect().prepareStatement(sql);
			
			if (isAdd)
			{
				ppstmt.setString(1,userName);
				ppstmt.setString(2,password);
				ppstmt.setBoolean(3, isAdmin);
			}
			else if(isDelete)
			{
				ppstmt.setInt(1, userId);
				ppstmt.setString(2, userName);
			}
			else 
			{
				ppstmt.setString(1, password);
				ppstmt.setBoolean(2, isAdmin);
				ppstmt.setInt(3, userId);
				ppstmt.setString(4, userName);
			}
			
			int rs = ppstmt.executeUpdate();
			if(isAdd && rs>0)
			{
			    System.out.println("insert new data: "+" userId: "+
			    					userId+" userName: "+
			    					userName+" isAdmin: "+
			    					isAdmin+" succeed");
			} 
			return rs > 0;
		} 
		catch (SQLException se)
		{
			se.printStackTrace();
		}
		return false;
	}
	
	//get account table
	public ArrayList<Problem> GetProblemData() 
	{
		ResultSet rs = null;
		try 
		{
			stmt 		= connect().createStatement();
			String sql 	= "SELECT * from t_wen_problems";
			rs 			= stmt.executeQuery(sql);
			
			connect().close();
			
			problemList.clear();
			while(rs.next())
			{
				Problem problem = new Problem();
				problem.setId(rs.getInt("ID"));
				problem.setUserId(rs.getInt("UserID"));
				problem.setDesc(rs.getString("Description"));
				problem.setDate(rs.getString("Date"));
				//System.out.println("ID: "+problem.getId()+" UserID: "+problem.getUserId()+" Description: "+problem.getDesc()+" Date: "+problem.getDate());
				problemList.add(problem);
			}
		} 
		catch (SQLException se) 
		{
			se.printStackTrace();
		}
		return problemList;
	}
		
	//Update Problem
	public boolean UpdateProblem(int id,int userId,String description,Boolean isAdd,boolean isDelete)
	{
		try
		{
			String sql = "";
			if (isAdd) 
			{
				sql = "INSERT INTO t_wen_problems(ID,UserID,Description,Date)VALUES(null,?,?,?)";
			}
			else if(isDelete) 
			{
				sql = "DELETE FROM t_wen_problems WHERE ID =?";
			}
			else 
			{
				sql = "UPDATE t_wen_problems set Description=? where ID=?";
			}
			
			ppstmt = connect().prepareStatement(sql);
			
			if (isAdd)
			{
				ppstmt.setInt(1,userId);
				ppstmt.setString(2,description);
				
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				ppstmt.setString(3,df.format(new java.util.Date()));
			}
			else if(isDelete)
			{
				ppstmt.setInt(1, id);
			}
			else 
			{
				ppstmt.setString(1, description);
				ppstmt.setInt(2, id);
			}
			
			int rs = ppstmt.executeUpdate();
			if(isAdd && rs>0)
			{
				if(rs>0)
				{
				    System.out.println("insert new data userId: "+
				    					ClientController.userid+" description: "+
				    					description+" succeed");
				} 
			}
			return rs > 0;
		} 
		catch (SQLException se)
		{
			se.printStackTrace();
		}
		return false;
	}
}