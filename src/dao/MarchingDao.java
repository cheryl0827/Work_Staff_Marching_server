package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.DBBean;
import bean.MarchingBean;
import bean.TaskBean;

public class MarchingDao {
	 private static Connection con= DBBean.getConn();
	 private static PreparedStatement ps=null;
	 private static ResultSet rs=null;
    //显示工作人员的匹配信息
    public static List<MarchingBean> Marching_Select(String workuserNo) throws SQLException{
   	 List<MarchingBean>list=new ArrayList<MarchingBean>();
   	 String sql="select * from marching where workuserNo=?;";
   	 ps=con.prepareStatement(sql);
   	 ps.setString(1, workuserNo);
   	 rs=ps.executeQuery();
   	 if(rs!=null){
   		 while(rs.next()){
   			MarchingBean marchingBean=new MarchingBean();
   			marchingBean.setMarchingID(rs.getInt("marchingID"));
   			marchingBean.setWorkuserNo(rs.getString("workuserNo"));
   			marchingBean.setAdminID(rs.getInt("adminID"));
   			marchingBean.setTaskID(rs.getInt("taskID"));
   			marchingBean.setMarchingTime(rs.getString("marchingTime"));
   			list.add(marchingBean);
   		 }
   	 }	
   	 return list;
    }	          
}
