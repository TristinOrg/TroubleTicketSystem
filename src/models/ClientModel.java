/*
 * author 	: Tristin Wen
 * date 	: 2021/05/05
 */
package models;
import java.util.ArrayList;

import Dao.DBConnect;

public class ClientModel extends DBConnect {
	private ArrayList <Problem> problemLst = new ArrayList<Problem>();
    
	public ArrayList <Problem> GetProblemList() 
	{
		return this.problemLst;
	}
}
