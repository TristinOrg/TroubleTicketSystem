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

public class ClientModel extends DBConnect {
	
	private Statement stmt;
	private PreparedStatement ppstmt;
	
	private ArrayList <Problem> problemList = new ArrayList<Problem>();
    
	public ArrayList <Problem> GetProblemList() 
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
				problem.SetDesc(rs.getString("Description"));
				problem.SetDate(rs.getString("Date"));
		
				problemList.add(problem);
			}
		} 
		catch (SQLException se) 
		{
			se.printStackTrace();
		}
		return problemList;
	}
}
