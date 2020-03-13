package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.DBBean;
import bean.UserBean;
import bean.WorkuserEvaluatingIndicatorBean;

public class WorkUserEvaluatingIndicatorDao {
	 private static Connection con= DBBean.getConn();
	    private static PreparedStatement ps=null;
	    private static ResultSet rs=null;
	    //工作人员指标的增加
	    public static boolean add_workevaliatingindicator(int community,int urgent,int psychology,int organization,int analyse,int law,String workuserNo) throws SQLException {
	        String sql="insert into workevaluatingindicator(community,urgent,psychology,organization,analyse,law,workuserNo) value (?,?,?,?,?,?,?)";
	        boolean flag=false;
	        ps=con.prepareStatement(sql);
	        ps.setInt(1,community);
	        ps.setInt(2,urgent);
	        ps.setInt(3,psychology);
	        ps.setInt(4, organization);
	        ps.setInt(5,analyse);
	        ps.setInt(6,law);
	        ps.setString(7, workuserNo);
	        int count=ps.executeUpdate();
	        if(count==1){
	            flag=true;
	        }
	        else
	            flag=false;
	        return flag;
	    }
	    //工作人员指标信息的查看
	    public static WorkuserEvaluatingIndicatorBean select_workuser(String workuserNo) throws SQLException {
	        WorkuserEvaluatingIndicatorBean workUser=null;
	    	String sql="select * from workevaluatingindicator where workuserNo=?";
	        ps=con.prepareStatement(sql);
	        ps.setString(1, workuserNo);
	        rs=ps.executeQuery();
	        if(rs!=null){
	        	if(rs.next()){
	        	//flag=true;
	        	workUser=new WorkuserEvaluatingIndicatorBean();
	        	workUser.setWorkEvaluatingIndicatorID(rs.getInt("workEvaluatingIndicatorID"));
	        	workUser.setCommunity(rs.getInt("community"));
	        	workUser.setUrgent(rs.getInt("urgent"));
	        	workUser.setPsychology(rs.getInt("psychology"));
	        	workUser.setOrganization(rs.getInt("organization"));
	        	workUser.setAnalyse(rs.getInt("analyse"));
	        	workUser.setLaw(rs.getInt("law"));
	        	workUser.setWorkuserNo(rs.getString("workuserNo"));	   	
	        	}
	        }
	  	return workUser;   
	    }
	    //工作人员指标信息的删除
	    public static boolean delete_workuser(String workuserNo) throws SQLException {
	        WorkuserEvaluatingIndicatorBean workUser=null;
	    	String sql="delete from workevaluatingindicator where workuserNo=?";
	    	boolean flag=false;
	        ps=con.prepareStatement(sql);
	        ps.setString(1, workuserNo);
	        int count=ps.executeUpdate();
	        if(count==1){
	            flag=true;
	        }
	        else
	            flag=false;
	        return flag;
	    }
	  //工作人员指标信息的修改
	    public static boolean update_workuser(int community,int urgent,int psychology,int organization,int analyse,int law,String workuserNo) throws SQLException {
	        WorkuserEvaluatingIndicatorBean workUser=null;
	    	String sql="update workevaluatingindicator set community=?,urgent=?,psychology=?,organization=?,analyse=?,law=? where workuserNo=?";
	    	boolean flag=false;
	        ps=con.prepareStatement(sql);
	        ps.setInt(1,community);
	        ps.setInt(2,urgent);
	        ps.setInt(3,psychology);
	        ps.setInt(4, organization);
	        ps.setInt(5,analyse);
	        ps.setInt(6,law);
	        ps.setString(7, workuserNo);
	        int count=ps.executeUpdate();
	        if(count==1){
	            flag=true;
	        }
	        else
	            flag=false;
	        return flag;
	    }
	    //工作人员指标信息的查看
	    public static List<WorkuserEvaluatingIndicatorBean> selectAll_workuser() throws SQLException {
	    	List<WorkuserEvaluatingIndicatorBean> list=new ArrayList<WorkuserEvaluatingIndicatorBean>();
	    	String sql="select * from workevaluatingindicator";
	        ps=con.prepareStatement(sql);
	        rs=ps.executeQuery();
	        if(rs!=null){
	        	while(rs.next()){
	        	//flag=true;
	        	WorkuserEvaluatingIndicatorBean workUser=new WorkuserEvaluatingIndicatorBean();
	        	workUser.setWorkEvaluatingIndicatorID(rs.getInt("workEvaluatingIndicatorID"));
	        	workUser.setCommunity(rs.getInt("community"));
	        	workUser.setUrgent(rs.getInt("urgent"));
	        	workUser.setPsychology(rs.getInt("psychology"));
	        	workUser.setOrganization(rs.getInt("organization"));
	        	workUser.setAnalyse(rs.getInt("analyse"));
	        	workUser.setLaw(rs.getInt("law"));
	        	workUser.setWorkuserNo(rs.getString("workuserNo"));
	        	list.add(workUser);  	
	        	}
	        }
	  	return list;   
	    }
	    //有空闲的工作人员指标信息的查看
	    public static List<WorkuserEvaluatingIndicatorBean> select_FreeWorkuser() throws SQLException {
	    	List<WorkuserEvaluatingIndicatorBean> list=new ArrayList<WorkuserEvaluatingIndicatorBean>();
	    	String sql="select * from workevaluatingindicator";
	        ps=con.prepareStatement(sql);
	        rs=ps.executeQuery();
	        if(rs!=null){
	        	while(rs.next()){
	        	String workuserNo=rs.getString("workuserNo");
	        	if(UserDao.Show_workStatus(workuserNo)!=0){
	        		WorkuserEvaluatingIndicatorBean workUser=new WorkuserEvaluatingIndicatorBean();
		        	workUser.setWorkEvaluatingIndicatorID(rs.getInt("workEvaluatingIndicatorID"));
		        	workUser.setCommunity(rs.getInt("community"));
		        	workUser.setUrgent(rs.getInt("urgent"));
		        	workUser.setPsychology(rs.getInt("psychology"));
		        	workUser.setOrganization(rs.getInt("organization"));
		        	workUser.setAnalyse(rs.getInt("analyse"));
		        	workUser.setLaw(rs.getInt("law"));
		        	workUser.setWorkuserNo(rs.getString("workuserNo"));
		        	list.add(workUser);  	
	        	  }	
	        	}
	        }
	  	return list;   
	    }
	   
	   
}
