package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.DBBean;
import bean.EstimateBean;
import bean.TaskBean;

public class EstimateDao {
	  private static Connection con= DBBean.getConn();
	    private static PreparedStatement ps=null;
	    private static ResultSet rs=null;
	    //诉求任务评价的增加
	    public static boolean add_estimate(int community,int urgent,int psychology,int organization,int analyse,int law,int taskID) throws SQLException {
	        String sql="insert into estimate(community,urgent,psychology,organization,analyse,law,taskID) value (?,?,?,?,?,?,?)";
	        boolean flag=false;
	        ps=con.prepareStatement(sql);
	        ps.setInt(1,community);
	        ps.setInt(2,urgent);
	        ps.setInt(3,psychology);
	        ps.setInt(4, organization);
	        ps.setInt(5,analyse);
	        ps.setInt(6,law);
	        ps.setInt(7, taskID);
	        int count=ps.executeUpdate();
	        if(count==1){
	            flag=true;
	        }
	        else
	            flag=false;
	        return flag;
	    }
	    //诉求任务评价的查看
	     public static EstimateBean Estimate_show(int taskID) throws SQLException {
	    	 String sql="select * from estimate where taskID=?";
	    	 EstimateBean estimaneBean=null;
	    	 ps=con.prepareStatement(sql);
	    	 ps.setInt(1,taskID);
	    	 rs=ps.executeQuery();
	    	 if(rs!=null){
	    		 while(rs.next()){
	    			 estimaneBean=new EstimateBean();
	    			 estimaneBean.setTaskID(rs.getInt("taskID"));	    		
	    			 estimaneBean.setCommunity(rs.getInt("community"));
	    			 estimaneBean.setUrgent(rs.getInt("urgent"));
	    			 estimaneBean.setPsychology(rs.getInt("psychology"));
	    			 estimaneBean.setOrganization(rs.getInt("organization"));
	    			 estimaneBean.setAnalyse(rs.getInt("analyse"));
	    			 estimaneBean.setLaw(rs.getInt("law"));
	    			
	    		 }
	    	 }	
	    	 return estimaneBean;
	     }	

}
