package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.DBBean;
import bean.TaskBean;
import bean.WorkuserEvaluatingIndicatorBean;

public class TaskDao {
	  private static Connection con= DBBean.getConn();
	    private static PreparedStatement ps=null;
	    private static ResultSet rs=null;
	    //诉求任务的增加
	    public static boolean add_task(String taskAdress,String taskCatagery,String taskContent,String taskTime,int userID,String taskDetaiAdress ) throws SQLException {
	        String sql="insert into task(taskAdress,taskCatagery,taskContent,taskTime,userID,taskDetaiAdress) value (?,?,?,?,?,?)";
	        boolean flag=false;
	        ps=con.prepareStatement(sql);
	        ps.setString(1,taskAdress);
	        ps.setString(2,taskCatagery);
	        ps.setString(3,taskContent);
	        ps.setString(4,taskTime);
	        ps.setInt(5,userID);
	        ps.setString(6, taskDetaiAdress);
	        int count=ps.executeUpdate();
	        if(count==1){
	            flag=true;
	        }
	        else
	            flag=false;
	        return flag;
	    }
	    //诉求任务的权重更新
	    public static boolean add_taskproportion(String taskAdress,String taskCatagery,String taskContent,String taskDetaiAdress,int community,int urgent,int psychology,int organization,int analyse,int law) throws SQLException {
	        String sql="update task set community=?,urgent=?,psychology=?,organization=?,analyse=?,law=? where taskAdress=? and taskCatagery=? and taskContent=? and taskDetaiAdress=?";
	        boolean flag=false;
	        ps=con.prepareStatement(sql);
	        ps.setInt(1,community);
	        ps.setInt(2,urgent);
	        ps.setInt(3,psychology);
	        ps.setInt(4,organization);
	        ps.setInt(5,analyse);
	        ps.setInt(6, law);
	        ps.setString(7, taskAdress);
	        ps.setString(8, taskCatagery);
	        ps.setString(9, taskContent);
	        ps.setString(10, taskDetaiAdress);
	        int count=ps.executeUpdate();
	        if(count==1){
	            flag=true;
	        }
	        else
	            flag=false;
	        return flag;
	    }
	    //显示所有诉求任务
	     public static List<TaskBean> taskSelect(int taskStatus) throws SQLException{
	    	 List<TaskBean>list=new ArrayList<TaskBean>();
	    	 String sql="select * from task where taskStatus=? order by taskTime desc;";
	    	 ps=con.prepareStatement(sql);
	    	 ps.setInt(1, taskStatus);
	    	 rs=ps.executeQuery();
	    	 if(rs!=null){
	    		 while(rs.next()){
	    			 TaskBean taskBean=new TaskBean();
	    			 taskBean.setTaskID(rs.getInt("taskID"));
	    			 taskBean.setTaskAdress(rs.getString("taskAdress"));
	    			 taskBean.setTaskCatagery(rs.getString("taskCatagery"));
	    			 taskBean.setTaskContent(rs.getString("taskContent"));
	    			 taskBean.setTaskDetaiAdress(rs.getString("taskDetaiAdress"));
	    			 taskBean.setTaskTime(rs.getString("taskTime"));
	    			 taskBean.setTaskStatus(rs.getInt("taskStatus"));
	    			 taskBean.setTaskWorknumber(rs.getString("taskWorknumber"));
	    			 taskBean.setUserID(rs.getInt("userID"));
	    			 taskBean.setCommunity(rs.getInt("community"));
	    			 taskBean.setUrgent(rs.getInt("urgent"));
	    			 taskBean.setPsychology(rs.getInt("psychology"));
	    			 taskBean.setOrganization(rs.getInt("organization"));
	    			 taskBean.setAnalyse(rs.getInt("analyse"));
	    			 taskBean.setLaw(rs.getInt("law"));
	    			 list.add(taskBean);
	    		 }
	    	 }	
	    	 return list;
	     }	     
        //删除诉求任务	     
	     public static boolean delete_task(int taskID) throws SQLException {
		        String sql="delete from task where taskID=?";
		        boolean flag=false;
		        ps=con.prepareStatement(sql);
		        ps.setInt(1,taskID);		      
		        int count=ps.executeUpdate();
		        if(count==1){
		            flag=true;
		        }
		        else
		            flag=false;
		        return flag;
		    }   
	   //修改诉求任务
		    public static boolean update_task(String taskAdress,String taskCatagery,String taskContent,String taskTime,int taskID,String taskDetaiAdress) throws SQLException {
		        String sql="update task set taskAdress=?, taskCatagery=?,taskContent=?,taskTime=?,taskDetaiAdress=? where taskID=? ";
		        boolean flag=false;
		        ps=con.prepareStatement(sql);
		        ps.setString(1,taskAdress);
		        ps.setString(2,taskCatagery);
		        ps.setString(3,taskContent);
		        ps.setString(4,taskTime);
		        ps.setString(5, taskDetaiAdress);
		        ps.setInt(6, taskID);
		        int count=ps.executeUpdate();
		        if(count==1){
		            flag=true;
		        }
		        else
		            flag=false;
		        return flag;
		    }
		  //显示所有诉求任务
		     public static TaskBean task_Select(int taskID,int recordStatus) throws SQLException{
		    	 TaskBean taskBean=null;
		    	 String sql="select * from task where taskID=? and recordStatus=? order by taskTime desc";
		    	 ps=con.prepareStatement(sql);
		    	 ps.setInt(1, taskID);
		    	 ps.setInt(2, recordStatus);
		    	 rs=ps.executeQuery();
		    	 if(rs!=null){
		    		 while(rs.next()){
		    			 taskBean=new TaskBean();
		    			 taskBean.setTaskID(rs.getInt("taskID"));
		    			 taskBean.setTaskAdress(rs.getString("taskAdress"));
		    			 taskBean.setTaskCatagery(rs.getString("taskCatagery"));
		    			 taskBean.setTaskContent(rs.getString("taskContent"));
		    			 taskBean.setTaskDetaiAdress(rs.getString("taskDetaiAdress"));
		    			 taskBean.setTaskTime(rs.getString("taskTime"));
		    			 taskBean.setTaskStatus(rs.getInt("taskStatus"));
		    			 taskBean.setTaskWorknumber(rs.getString("taskWorknumber"));
		    			 taskBean.setUserID(rs.getInt("userID"));
		    			 taskBean.setCommunity(rs.getInt("community"));
		    			 taskBean.setUrgent(rs.getInt("urgent"));
		    			 taskBean.setPsychology(rs.getInt("psychology"));
		    			 taskBean.setOrganization(rs.getInt("organization"));
		    			 taskBean.setAnalyse(rs.getInt("analyse"));
		    			 taskBean.setLaw(rs.getInt("law"));
		 
		    		 }
		    	 }	
		    	 return taskBean;
		     }	     
		  //修改诉求任务的状态
		    public static boolean update_taskStatus(int taskID,int taskStatus) throws SQLException {
		        String sql="update task set taskStatus=? where taskID=? ";
		        boolean flag=false;
		        ps=con.prepareStatement(sql);
		        ps.setInt(1,taskStatus);
		        ps.setInt(2,taskID);
		        int count=ps.executeUpdate();
		        if(count==1){
		            flag=true;
		        }
		        else
		            flag=false;
		        return flag;
		    }
		    //诉求任务的权重显示
		     public static TaskBean admintask_Select(int taskID) throws SQLException{
		    	 TaskBean taskBean=null;
		    	 String sql="select * from task where taskID=?";
		    	 ps=con.prepareStatement(sql);
		    	 ps.setInt(1, taskID);
		    	 rs=ps.executeQuery();
		    	 if(rs!=null){
		    		 while(rs.next()){
		    			 taskBean=new TaskBean();
		    			 taskBean.setTaskID(rs.getInt("taskID"));
		    			 taskBean.setTaskAdress(rs.getString("taskAdress"));
		    			 taskBean.setTaskCatagery(rs.getString("taskCatagery"));
		    			 taskBean.setTaskContent(rs.getString("taskContent"));
		    			 taskBean.setTaskDetaiAdress(rs.getString("taskDetaiAdress"));
		    			 taskBean.setTaskTime(rs.getString("taskTime"));
		    			 taskBean.setTaskStatus(rs.getInt("taskStatus"));
		    			 taskBean.setTaskWorknumber(rs.getString("taskWorknumber"));
		    			 taskBean.setUserID(rs.getInt("userID"));
		    			 taskBean.setCommunity(rs.getInt("community"));
		    			 taskBean.setUrgent(rs.getInt("urgent"));
		    			 taskBean.setPsychology(rs.getInt("psychology"));
		    			 taskBean.setOrganization(rs.getInt("organization"));
		    			 taskBean.setAnalyse(rs.getInt("analyse"));
		    			 taskBean.setLaw(rs.getInt("law"));
		    		 }
		    	 }	
		    	 return taskBean;
		     }	     
		    //诉求任务的权重更新
		    public static boolean adminadd_taskproportion(int community,int urgent,int psychology,int organization,int analyse,int law,int taskID) throws SQLException {
		        String sql="update task set community=?,urgent=?,psychology=?,organization=?,analyse=?,law=? where taskID=?";
		        boolean flag=false;
		        ps=con.prepareStatement(sql);
		        ps.setInt(1,community);
		        ps.setInt(2,urgent);
		        ps.setInt(3,psychology);
		        ps.setInt(4,organization);
		        ps.setInt(5,analyse);
		        ps.setInt(6, law);
		        ps.setInt(7, taskID);
		        int count=ps.executeUpdate();
		        if(count==1){
		            flag=true;
		        }
		        else
		            flag=false;
		        return flag;
		    }
	     
		    //诉求任务的权重查看
		     public static List<TaskBean> task_Porprotion(int taskStatus) throws SQLException {
			     List<TaskBean> list=new ArrayList<TaskBean>();
		    	 String sql="select * from task where taskStatus=?";
		    	 ps=con.prepareStatement(sql);
		    	 ps.setInt(1,taskStatus);
		    	 rs=ps.executeQuery();
		    	 if(rs!=null){
		    		 while(rs.next()){
		    			 TaskBean taskBean=new TaskBean();
		    			 taskBean=new TaskBean();
		    			 taskBean.setTaskID(rs.getInt("taskID"));	    		
		    			 taskBean.setCommunity(rs.getInt("community"));
		    			 taskBean.setUrgent(rs.getInt("urgent"));
		    			 taskBean.setPsychology(rs.getInt("psychology"));
		    			 taskBean.setOrganization(rs.getInt("organization"));
		    			 taskBean.setAnalyse(rs.getInt("analyse"));
		    			 taskBean.setLaw(rs.getInt("law"));
		    			 list.add(taskBean);
		    		 }
		    	 }	
		    	 return list;
		     }	     
		     //显示未匹配的诉求任务
		     public static List<TaskBean> worktaskSelect(int taskStatus,int marchingStatus) throws SQLException{
		    	 List<TaskBean>list=new ArrayList<TaskBean>();
		    	 String sql="select * from task where taskStatus=? and marchingStatus=? order by taskTime desc;";
		    	 ps=con.prepareStatement(sql);
		    	 ps.setInt(1, taskStatus);
		    	 ps.setInt(2,marchingStatus);  	
		    	 rs=ps.executeQuery();
		    	 if(rs!=null){
		    		 while(rs.next()){
		    			 TaskBean taskBean=new TaskBean();
		    			 taskBean.setTaskID(rs.getInt("taskID"));
		    			 taskBean.setTaskAdress(rs.getString("taskAdress"));
		    			 taskBean.setTaskCatagery(rs.getString("taskCatagery"));
		    			 taskBean.setTaskContent(rs.getString("taskContent"));
		    			 taskBean.setTaskDetaiAdress(rs.getString("taskDetaiAdress"));
		    			 taskBean.setTaskTime(rs.getString("taskTime"));
		    			 taskBean.setTaskStatus(rs.getInt("taskStatus"));
		    			 taskBean.setTaskWorknumber(rs.getString("taskWorknumber"));
		    			 taskBean.setUserID(rs.getInt("userID"));
		    			 taskBean.setCommunity(rs.getInt("community"));
		    			 taskBean.setUrgent(rs.getInt("urgent"));
		    			 taskBean.setPsychology(rs.getInt("psychology"));
		    			 taskBean.setOrganization(rs.getInt("organization"));
		    			 taskBean.setAnalyse(rs.getInt("analyse"));
		    			 taskBean.setLaw(rs.getInt("law"));
		    			 list.add(taskBean);
		    		 }
		    	 }	
		    	 return list;
		     }	          
		     //修改诉求任务的记录状态
			    public static boolean update_recordStatus(int taskID,int recordStatus) throws SQLException {
			        String sql="update task set recordStatus=? where taskID=? ";
			        boolean flag=false;
			        ps=con.prepareStatement(sql);
			        ps.setInt(1,recordStatus);
			        ps.setInt(2,taskID);
			        int count=ps.executeUpdate();
			        if(count==1){
			            flag=true;
			        }
			        else
			            flag=false;
			        return flag;
			    }
			    //显示普通用户的所有诉求任务
			     public static List<TaskBean> task_UserSelect(int taskStatus,int userID,int marchingStatus,int pingjiaStatus) throws SQLException{
			    	 List<TaskBean>list=new ArrayList<TaskBean>();
			    	 TaskBean taskBean=null;
			    	 String sql="select * from task where taskStatus=? and userID=? and marchingStatus=? and pingjiaStatus=? order by taskTime desc";
			    	 ps=con.prepareStatement(sql);
			    	 ps.setInt(1,taskStatus);
			    	 ps.setInt(2,userID);
			    	 ps.setInt(3,marchingStatus);
			    	 ps.setInt(4,pingjiaStatus);
			    	 rs=ps.executeQuery();
			    	 if(rs!=null){
			    		 while(rs.next()){
			    			 taskBean=new TaskBean();
			    			 taskBean.setTaskID(rs.getInt("taskID"));
			    			 taskBean.setTaskAdress(rs.getString("taskAdress"));
			    			 taskBean.setTaskCatagery(rs.getString("taskCatagery"));
			    			 taskBean.setTaskContent(rs.getString("taskContent"));
			    			 taskBean.setTaskDetaiAdress(rs.getString("taskDetaiAdress"));
			    			 taskBean.setTaskTime(rs.getString("taskTime"));
			    			 taskBean.setTaskStatus(rs.getInt("taskStatus"));
			    			 taskBean.setTaskWorknumber(rs.getString("taskWorknumber"));
			    			 taskBean.setUserID(rs.getInt("userID"));
			    			 taskBean.setCommunity(rs.getInt("community"));
			    			 taskBean.setUrgent(rs.getInt("urgent"));
			    			 taskBean.setPsychology(rs.getInt("psychology"));
			    			 taskBean.setOrganization(rs.getInt("organization"));
			    			 taskBean.setAnalyse(rs.getInt("analyse"));
			    			 taskBean.setLaw(rs.getInt("law"));
			    			 taskBean.setMarchingStatus(rs.getInt("marchingStatus"));
			    			 taskBean.setPingjiaStatus(rs.getInt("pingjiaStatus"));
			    			 list.add(taskBean);
			    		 }
			    	 }	
			    	 return list;
			     }	     
			     //修改诉求任务的状态
				    public static boolean update_usertaskStatus(int taskID,int taskStatus,int pingjiaStatus) throws SQLException {
				        String sql="update task set taskStatus=?,pingjiaStatus=? where taskID=? ";
				        boolean flag=false;
				        ps=con.prepareStatement(sql);
				        ps.setInt(1,taskStatus);
				        ps.setInt(2, pingjiaStatus);
				        ps.setInt(3,taskID);
				        int count=ps.executeUpdate();
				        if(count==1){
				            flag=true;
				        }
				        else
				            flag=false;
				        return flag;
				    }			   
}
