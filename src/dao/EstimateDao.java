package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.DBBean;
import bean.EstimateBean;
import bean.MarchingBean;
import bean.TaskBean;
import bean.WorkuserEvaluatingIndicatorBean;

public class EstimateDao {
	  private static Connection con= DBBean.getConn();
	    private static PreparedStatement ps=null;
	    private static ResultSet rs=null;
	    //诉求任务评价的增加
	    public static boolean add_estimate(int community,int urgent,int psychology,int organization,int analyse,int law,String workuserNo,String taskID) throws SQLException {
	        String sql="insert into estimate(community,urgent,psychology,organization,analyse,law,marchingID) value (?,?,?,?,?,?,?)";
	        boolean flag=false;
	        int marchingID=MarchingDao.show_msrchingid(workuserNo, taskID);
	        ps=con.prepareStatement(sql);
	        ps.setInt(1,community);
	        ps.setInt(2,urgent);
	        ps.setInt(3,psychology);
	        ps.setInt(4, organization);
	        ps.setInt(5,analyse);
	        ps.setInt(6,law);
	        ps.setInt(7, marchingID);
	        int count=ps.executeUpdate();
	        if(count==1){
	            flag=true;
	        }
	        else
	            flag=false;
	        return flag;
	    }
	   
	    //工作人员的评价显示
	    public static EstimateBean select_workuser(String workuserNo,String taskID) throws SQLException {
	    	EstimateBean estimaneBean=null;
	    	String sql="select * from estimate where marchingID=?";
	    	 int marchingID=MarchingDao.show_msrchingid(workuserNo, taskID);
	        ps=con.prepareStatement(sql);
	        ps.setInt(1, marchingID);
	        rs=ps.executeQuery();
	        if(rs!=null){
	        	if(rs.next()){
	        	//flag=true;
	        	 estimaneBean=new EstimateBean();
	        	 //estimaneBean.setWorkuserNo(rs.getString("workuserNo"));    		
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
	  
	   //根据工号显示诉求任务评价信息的平均值
	     public static EstimateBean estimateAvg_show(String workuserNo) throws SQLException {
	    	 String sql="select * from marching m,estimate e where m.workuserNo=? and e.marchingID=m.marchingID";
	    	 EstimateBean estimaneBean=null;
	    	 ps=con.prepareStatement(sql);
	    	 ps.setString(1,workuserNo);
	    	 rs=ps.executeQuery();
	    	 int count=0;
	    	 float community=0,urgent=0,psychology=0,organization=0,analyse=0,law=0;
	    	 if(rs!=null){
	    		 while(rs.next()){
	    			 estimaneBean=new EstimateBean();
	    			   community=community+rs.getInt("community");
		    			urgent=urgent+rs.getInt("urgent");
		    			psychology=psychology+rs.getInt("psychology");
		    			organization=organization+rs.getInt("organization");
		    			law=law+rs.getInt("law");
		    			analyse=analyse+rs.getInt("analyse");
		    			count=count+1;
	    			
	    		 }
	    	 }
	    	 if(count!=0){
		         estimaneBean=new EstimateBean();
		    	 estimaneBean.setCommunity((int)(community/count));
		    	 estimaneBean.setAnalyse((int)(analyse/count));
		    	 estimaneBean.setOrganization((int)(organization/count));
		    	 estimaneBean.setLaw((int)(law/count));
		    	 estimaneBean.setPsychology((int)(psychology/count));
		    	 estimaneBean.setUrgent((int)(urgent/count));
		    	 }
	    	 return estimaneBean;
	     }	
	    
}
