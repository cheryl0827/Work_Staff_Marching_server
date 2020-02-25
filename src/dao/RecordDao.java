package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bean.DBBean;

public class RecordDao {
	 private static Connection con= DBBean.getConn();
	 private static PreparedStatement ps=null;
	 private static ResultSet rs=null;
	 //工作人员办理记录的增加
	  public static boolean add_record(int taskID,String nextVisitTime,String recordContent,String recordTime) throws SQLException {
	        String sql="insert into record(taskID,nextVisitTime,recordContent,recordTime) value (?,?,?,?)";
	        boolean flag=false;
	        ps=con.prepareStatement(sql);
	        ps.setInt(1,taskID);
	        ps.setString(2,nextVisitTime);
	        ps.setString(3,recordContent);
	        ps.setString(4,recordTime);
	        int count=ps.executeUpdate();
	        if(count==1){
	            flag=true;
	        }
	        else
	            flag=false;
	        return flag;
	    }
}
