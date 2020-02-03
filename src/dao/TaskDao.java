package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bean.DBBean;

public class TaskDao {
	  private static Connection con= DBBean.getConn();
	    private static PreparedStatement ps=null;
	    private static ResultSet rs=null;
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
}
