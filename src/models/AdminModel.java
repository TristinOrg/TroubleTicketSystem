/*
 * author 	: Tristin Wen
 * date 	: 2021/05/05
 */
package models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Dao.DBConnect;

public class AdminModel extends DBConnect {
	
	Statement stmt;
	PreparedStatement ppstmt;
	private ArrayList<User> userList = new ArrayList<User>();
	
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
	public void UpdateAccount(int userId,String userName,String password,Boolean isAdd,Boolean isAdmin)
	{
		try
		{
			String sql = "";
			if (isAdd) 
			{
				sql = "INSERT INTO t_wen_users(ID,UserName,Password,IsAdmin)VALUES(?,?,?,?)";
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
			else 
			{
				ppstmt.setString(1, password);
				ppstmt.setBoolean(2, isAdmin);
				ppstmt.setInt(3, userId);
			}
			
			ppstmt.executeQuery();
			connect().close();
			
		} 
		catch (SQLException se)
		{
			se.printStackTrace();
		}

	}
}