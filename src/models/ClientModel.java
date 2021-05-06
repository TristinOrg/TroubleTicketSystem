/*
 * author 	: Tristin Wen
 * date 	: 2021/05/05
 */
package models;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import Dao.DBConnect;
import controllers.ClientController;

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
			
			problemList.clear();
			while(rs.next())
			{
				Problem problem = new Problem();
				problem.SetId(rs.getInt("ID"));
				problem.SetDesc(rs.getString("Description"));
				problem.SetDate(rs.getString("Date"));
		
				problemList.add(problem);
			}
			
			connect().close();
		} 
		catch (SQLException se) 
		{
			se.printStackTrace();
		}
		return problemList;
	}
	
	
	public boolean SubmitNewProblem(int id,String description) 
	{
		try 
		{
			String sql = "INSERT INTO t_wen_problems(ID,UserID,Description,Date)Values(?,?,?,?)";
		    ppstmt = connect().prepareStatement(sql);
		    
		    ppstmt.setInt(1, id);
		    ppstmt.setInt(2,ClientController.userid);
		    ppstmt.setString(3, description);
	
		    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			ppstmt.setString(4,df.format(new java.util.Date()));
			
			int rs = ppstmt.executeUpdate();
			return rs>0;
		} 
		catch (SQLException se) 
		{
			se.printStackTrace();
		}
		
		return false;
	}
}
