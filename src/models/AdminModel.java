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
				user.SetId(rs.getInt("ID"));
				user.SetUserName(rs.getString("UserName"));
				user.SetPassword(rs.getString("Password"));
				user.SetIsAdmin(rs.getBoolean("IsAdmin"));
				System.out.println("ID: "+user.GetId()+" UserName: "+user.GetUserName()+" Password: "+user.GetPassword()+" IsAdmin: "+user.GetIsAdmin());
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
				sql = "INSERT IGNORE INTO t_wen_users(ID,UserName,Password,IsAdmin)VALUES(?,?,?,?)";
			}
			else if(isDelete) 
			{
				sql = "DELETE FROM t_wen_users WHERE ID =?";
			}
			else 
			{
				sql = "UPDATE t_wen_users set Password=?,IsAdmin=? where ID=?";
			}
			
			ppstmt = connect().prepareStatement(sql);
			
			if (isAdd)
			{
				ppstmt.setInt(1, userId);
				ppstmt.setString(2,userName);
				ppstmt.setString(3,password);
				ppstmt.setBoolean(4, isAdmin);
			}
			else if(isDelete)
			{
				ppstmt.setInt(1, userId);
			}
			else 
			{
				ppstmt.setString(1, password);
				ppstmt.setBoolean(2, isAdmin);
				ppstmt.setInt(3, userId);
			}
			
			int rs = ppstmt.executeUpdate();	
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
				problem.SetId(rs.getInt("ID"));
				problem.SetUserId(rs.getInt("UserID"));
				problem.SetDesc(rs.getString("Description"));
				problem.SetDate(rs.getString("Date"));
				System.out.println("ID: "+problem.GetId()+" UserID: "+problem.GetUserId()+" Description: "+problem.GetDesc()+" Date: "+problem.GetDate());
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
				sql = "INSERT IGNORE INTO t_wen_problems(ID,UserID,Description,Date)VALUES(?,?,?,?)";
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
				ppstmt.setInt(1, id);
				ppstmt.setInt(2,userId);
				ppstmt.setString(3,description);
				
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				ppstmt.setString(4,df.format(new java.util.Date()));
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
			return rs > 0;
		} 
		catch (SQLException se)
		{
			se.printStackTrace();
		}
		return false;
	}
}