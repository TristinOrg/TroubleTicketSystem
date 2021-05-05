/*
 * author 	: Tristin Wen
 * date 	: 2021/05/05
 */
package models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Dao.DBConnect;

public class LoginModel extends DBConnect {
	
	private User user;
	public LoginModel() {
		user = new User();
	}
	
	public int GetId(){
		if(user == null) 
		{
			return -1;	
		}		
		return user.GetId();
	}
	
	public boolean isAdmin()
	{
		if (user==null) {
			return false;
		}
		return user.GetIsAdmin();
	}

		
	public Boolean getCredentials(String username, String password){
           
        	String query = "SELECT * FROM t_wen_users WHERE uname = ? and passwd = ?;";
            try(PreparedStatement stmt = connect().prepareStatement(query)) {
               stmt.setString(1, username);
               stmt.setString(2, password);
               ResultSet rs = stmt.executeQuery();
                if(rs.next()) { 
                 
                	user.SetId(rs.getInt("id"));
                	user.SetIsAdmin(rs.getBoolean("admin"));
                	return true;
               	}
             }catch (SQLException e) {
            	e.printStackTrace();   
             }
			return false;
    }
}
