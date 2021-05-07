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
		return user.getId();
	}
	
	public boolean isAdmin()
	{
		if (user==null) {
			return false;
		}
		return user.getIsAdmin();
	}

		
	public Boolean getCredentials(String username, String password){    
        	String query = "SELECT * FROM t_wen_users WHERE UserName =? and Password =?";
            try(PreparedStatement stmt = connect().prepareStatement(query)) {
               stmt.setString(1, username);
               stmt.setString(2, password);
               ResultSet rs = stmt.executeQuery();
                if(rs.next()) {        
                	user.setId(rs.getInt("ID"));
                	user.setIsAdmin(rs.getBoolean("IsAdmin"));
                	return true;
               	}
             }catch (SQLException e) {
            	e.printStackTrace();   
             }
			return false;
    }
}
