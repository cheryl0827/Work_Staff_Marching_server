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
import bean.UserBean;

public class MarchingDao {
	 private static Connection con= DBBean.getConn();
	 private static PreparedStatement ps=null;
	 private static ResultSet rs=null;
	 //计算工作人员完成的诉求任务数量
	    public static int calculate_usertasks(String workuserNo) throws SQLException {
	        String sql="select * from marching where workuserNo=?";
	        boolean flag=false;
	        ps=con.prepareStatement(sql);
	        ps.setString(1,workuserNo);
	        int count=0;
	        rs=ps.executeQuery();
	    	 if(rs!=null){
	    		 while(rs.next()){
	    		 count=count+1;	 
	    		 }
	    		 }
	    	 return count;
	    }	
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
    //显示所有的匹配信息
    public static List<MarchingBean> Marching_SelectAll() throws SQLException{
      	 List<MarchingBean>list=new ArrayList<MarchingBean>();
      	 String sql="select * from marching order by marchingTime desc";
      	 ps=con.prepareStatement(sql);
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
    //查看匹配的taskID对应的工作人员工号
    public static List<UserBean> Show_workuserNo(String taskID) throws SQLException {
        String sql="select * from marching where taskID=?";
        List<UserBean>list=new ArrayList<UserBean>();
        boolean flag=false;
        ps=con.prepareStatement(sql);
        ps.setString(1,taskID);
        String workuserNo = null;
        rs=ps.executeQuery();
    	 if(rs!=null){
    		 while(rs.next()){		 
    			 list.add(UserDao.select_Userinformation(rs.getString("workuserNo")));			 	 
    		 }
    		 }
    	 return  list;
    }	
}
