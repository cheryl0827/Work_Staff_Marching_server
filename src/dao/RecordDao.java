package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.DBBean;
import bean.MarchingBean;
import bean.RecordBean;

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
	//工作人员办理记录的显示
	  public static List<RecordBean> record_Select(int taskID) throws SQLException{
		   	 List<RecordBean>list=new ArrayList<RecordBean>();
		   	 String sql="select * from record where taskID=? order by nextVisitTime desc";
		   	 ps=con.prepareStatement(sql);
		   	 ps.setInt(1, taskID);
		   	 rs=ps.executeQuery();
		   	 if(rs!=null){
		   		 while(rs.next()){
		   			RecordBean recordBean=new RecordBean();
		   			recordBean.setRecordID(rs.getInt("recordID"));
		   			recordBean.setTaskID(rs.getInt("taskID"));
		   			recordBean.setNextVisitTime(rs.getString("nextVisitTime"));
		   			recordBean.setRecordContent(rs.getString("recordContent"));
		   			recordBean.setRecordTime(rs.getString("recordTime"));
		   			list.add(recordBean);
		   		 }
		   	 }	
		   	 return list;
		    }	         
}
