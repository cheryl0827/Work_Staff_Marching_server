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
import bean.UserBean;

public class UserDao {
    private static Connection con= DBBean.getConn();
    private static PreparedStatement ps=null;
    private static ResultSet rs=null;
    //用户注册
    public static boolean add_user(String userName,String phone,String password,String role,String sex,String workuserNo) throws SQLException {
        String sql="insert into user(userName,phone,password,roleName,sex,workuserNo) value (?,?,?,?,?,?)";
        boolean flag=false;
        ps=con.prepareStatement(sql);
        ps.setString(1,userName);
        ps.setString(2,phone);
        ps.setString(3,password);
        ps.setString(4,role);
        ps.setString(5,sex);
        ps.setString(6, workuserNo);
        int count=ps.executeUpdate();
        if(count==1){
            flag=true;
        }
        else
            flag=false;
        return flag;
    }
    //用户注册的查看
    public static boolean add_userSelect(String phone,String roleName) throws SQLException {
        String sql="select * from user where phone=? and roleName=?";
        boolean flag=true;
        ps=con.prepareStatement(sql);
        ps.setString(1,phone);
        ps.setString(2, roleName);
        rs=ps.executeQuery();
        if(rs.next()){
            flag=false;          
        }
        return flag;
        
    }
    //查询登录用户的信息
    public static UserBean select_userlogin(String phone,String password,String usertype) throws SQLException {
        UserBean userBean=null;
    	String sql="select * from user where phone=? and password=? and roleName=?";
        //boolean flag=false;
        ps=con.prepareStatement(sql);
        ps.setString(1, phone);
        ps.setString(2, password);
        ps.setString(3, usertype);
        rs=ps.executeQuery();
        if(rs!=null){
        	if(rs.next()){
        	//flag=true;
        	userBean=new UserBean();
        	userBean.setUserID(rs.getInt("userID"));
        	userBean.setUserName(rs.getString("userName"));
        	userBean.setIndentificationCard(rs.getString("indentificationCard"));
        	userBean.setPhone(rs.getString("phone"));
        	userBean.setCountry(rs.getString("country"));
        	userBean.setAddress(rs.getString("address"));
        	userBean.setRoleName(rs.getString("roleName"));
        	userBean.setPassword(rs.getString("password"));
        	userBean.setRegisterStatus(rs.getInt("registerStatus"));
        	userBean.setCity(rs.getString("city"));
        	userBean.setProvince(rs.getString("province"));
        	userBean.setWorkuserNo(rs.getString("workuserNo"));
        	userBean.setUserPicture(rs.getString("userPicture"));
        	//userBean.setWorkStatus(rs.getInt("workStatus"));      	
        	}
        }
  	return userBean;   
    }
    //登录的查询
    public static int select_userloginregister(String phone,String password,String usertype) throws SQLException {
    	String sql="select * from user where phone=? and password=? and roleName=?";
        //boolean flag=false;
    	int registerstatus=1;
        ps=con.prepareStatement(sql);
        ps.setString(1, phone);
        ps.setString(2, password);
        ps.setString(3, usertype);
        rs=ps.executeQuery();
        if(rs!=null){
        	if(rs.next()){
        	registerstatus=rs.getInt("registerStatus");	
        	}
        	}
        return registerstatus;
    }
    //根据userID修改普通用户的密码
    public static boolean update_userpassword(String password,int userID) throws SQLException {
		 String sql="update user set password=? where  userID=?";
	        boolean flag=false;
	        ps=con.prepareStatement(sql);
	        ps.setString(1,password); 
	        ps.setInt(2, userID);
	        int count=ps.executeUpdate();
	        if(count==1){
	            flag=true;
	        }
	        else
	            flag=false;
	        return flag;
	    }
    //根据userID修改普通用户的手机号码
    public static boolean update_userphone(String phone,int userID) throws SQLException {
		 String sql="update user set phone=? where  userID=?";
	        boolean flag=false;
	        ps=con.prepareStatement(sql);
	        ps.setString(1,phone); 
	        ps.setInt(2, userID);
	        int count=ps.executeUpdate();
	        if(count==1){
	            flag=true;
	        }
	        else
	            flag=false;
	        return flag;
	    }
    //根据userID修改普通用户的省市县和详细地址
    public static boolean update_useraddress(String province,String city,String country,String address,int userID) throws SQLException {
		 String sql="update user set province=?,city=?,country=?,address=? where userID=?";
	        boolean flag=false;
	        ps=con.prepareStatement(sql);
	        ps.setString(1,province); 
	        ps.setString(2,city); 
	        ps.setString(3,country); 
	        ps.setString(4,address);
	        ps.setInt(5, userID);
	        int count=ps.executeUpdate();
	        if(count==1){
	            flag=true;
	        }
	        else
	            flag=false;
	        return flag;
	    }
  //根据工号查询用户姓名
    public static String select_userName(String workuserNo) throws SQLException {
    	String sql="select * from user where workuserNo=?";
    	String userName = null;
        ps=con.prepareStatement(sql);
        ps.setString(1,workuserNo);
        rs=ps.executeQuery();
        if(rs!=null){
        	if(rs.next()){
        		userName=rs.getString("userName");	
        	}
        	}
        return userName;
    }
  //显示所有用户信息
    public static List<UserBean> user_Select(int registerStatus,String roleName) throws SQLException{
   	 List<UserBean>list=new ArrayList<UserBean>();
   	 String sql="select * from user where registerStatus=? and roleName=?";
   	 ps=con.prepareStatement(sql);
   	 ps.setInt(1, registerStatus);
   	 ps.setString(2, roleName);
   	 rs=ps.executeQuery();
   	 if(rs!=null){
   		 while(rs.next()){
   			 UserBean userBean=new UserBean();
   			 userBean.setUserID(rs.getInt("userID"));
   			 userBean.setUserName(rs.getString("userName"));
   			 userBean.setIndentificationCard(rs.getString("indentificationCard"));
   			 userBean.setPhone(rs.getString("phone"));
   			 userBean.setCountry(rs.getString("country"));
	   		 userBean.setAddress(rs.getString("address"));
	   		 userBean.setRoleName(rs.getString("roleName"));
	   		 userBean.setRegisterStatus(rs.getInt("registerStatus"));
	   		 userBean.setCity(rs.getString("city"));
	   		 userBean.setProvince(rs.getString("province"));
	   		 userBean.setWorkuserNo(rs.getString("workuserNo"));
	   		 userBean.setSex(rs.getString("sex"));
	   		 userBean.setMaxTaskNumber(rs.getInt("maxTaskNumber"));
	   		 //userBean.setWorkStatus(rs.getInt("workStatus"));
	   		// userBean.setWorkevaluatingStatus(rs.getInt("workevaluatingStatus"));
   			 list.add(userBean);
   		 }
   	 }	
   	 return list;
    }	 
    //根据userID和用户角色审核用户信息
    public static boolean update_useraudit(int registerStatus,int userID) throws SQLException {
		 String sql="update user set registerStatus=? where  userID=?";
	        boolean flag=false;
	        ps=con.prepareStatement(sql);
	        ps.setInt(1,registerStatus); 
	        ps.setInt(2, userID);
	        int count=ps.executeUpdate();
	        if(count==1){
	            flag=true;
	        }
	        else
	            flag=false;
	        return flag;
	    }
    //查询用户的基本信息
    public static UserBean select_userinformation(String workuserNo) throws SQLException {
        UserBean userBean=null;
    	String sql="select * from user where workuserNo=?";
        //boolean flag=false;
        ps=con.prepareStatement(sql);
        ps.setString(1, workuserNo); 
        rs=ps.executeQuery();
        if(rs!=null){
        	if(rs.next()){
        	//flag=true;
        	userBean=new UserBean();
        	userBean.setUserID(rs.getInt("userID"));
        	userBean.setUserName(rs.getString("userName"));
        	userBean.setIndentificationCard(rs.getString("indentificationCard"));
        	userBean.setPhone(rs.getString("phone"));
        	userBean.setCountry(rs.getString("country"));
        	userBean.setAddress(rs.getString("address"));
        	userBean.setRoleName(rs.getString("roleName"));
        	userBean.setPassword(rs.getString("password"));
        	userBean.setRegisterStatus(rs.getInt("registerStatus"));
        	userBean.setCity(rs.getString("city"));
        	 userBean.setSex(rs.getString("sex"));
        	userBean.setProvince(rs.getString("province"));
        	userBean.setWorkuserNo(rs.getString("workuserNo"));
        	//userBean.setWorkStatus(rs.getInt("workStatus"));  
        	userBean.setMaxTaskNumber(rs.getInt("maxTaskNumber"));
        	}
        }
  	return userBean;   
    }
    //查询用户的基本信息
    public static UserBean select_Userinformation(int userID) throws SQLException {
        UserBean userBean=null;
    	String sql="select * from user where userID=?";
        //boolean flag=false;
        ps=con.prepareStatement(sql);
        ps.setInt(1, userID); 
        rs=ps.executeQuery();
        if(rs!=null){
        	if(rs.next()){
        	//flag=true;
        	userBean=new UserBean();
        	userBean.setUserID(rs.getInt("userID"));
        	userBean.setUserName(rs.getString("userName"));
        	userBean.setIndentificationCard(rs.getString("indentificationCard"));
        	userBean.setPhone(rs.getString("phone"));
        	userBean.setCountry(rs.getString("country"));
        	userBean.setAddress(rs.getString("address"));
        	userBean.setRoleName(rs.getString("roleName"));
        	userBean.setPassword(rs.getString("password"));
        	userBean.setRegisterStatus(rs.getInt("registerStatus"));
        	userBean.setCity(rs.getString("city"));
        	 userBean.setSex(rs.getString("sex"));
        	userBean.setProvince(rs.getString("province"));
        	userBean.setWorkuserNo(rs.getString("workuserNo"));
        	//userBean.setWorkStatus(rs.getInt("workStatus"));      	
        	}
        }
  	return userBean;   
    }
    //根据工号修改工作人员的工作状态
    public static boolean update_userWorkStatus(String workuserNo,int workStatus) throws SQLException {
		 String sql="update user set workStatus=? where workuserNo=?";
	        boolean flag=false;
	        ps=con.prepareStatement(sql);
	        ps.setInt(1,workStatus); 
	        ps.setString(2, workuserNo);
	        int count=ps.executeUpdate();
	        if(count==1){
	            flag=true;
	        }
	        else
	            flag=false;
	        return flag;
	    }
    //根据工号查询工作人员的查看
    public static UserBean workuserInformation_show(String workuserNo) throws SQLException {
   	 String sql="select * from user where workuserNo=?";
   	 UserBean userBean=null;
   	 ps=con.prepareStatement(sql);
   	 ps.setString(1, workuserNo);
   	 rs=ps.executeQuery();
   	 if(rs!=null){
   		 while(rs.next()){
   			 userBean=new UserBean();
   			userBean.setUserID(rs.getInt("userID"));
        	userBean.setUserName(rs.getString("userName"));
        	userBean.setIndentificationCard(rs.getString("indentificationCard"));
        	userBean.setPhone(rs.getString("phone"));
        	userBean.setCountry(rs.getString("country"));
        	userBean.setAddress(rs.getString("address"));
        	userBean.setRoleName(rs.getString("roleName"));
        	userBean.setPassword(rs.getString("password"));
        	userBean.setRegisterStatus(rs.getInt("registerStatus"));
        	userBean.setCity(rs.getString("city"));
        	 userBean.setSex(rs.getString("sex"));
        	userBean.setProvince(rs.getString("province"));
        	userBean.setWorkuserNo(rs.getString("workuserNo"));
        	//userBean.setWorkStatus(rs.getInt("workStatus"));  
   		 }
   	 }	
   	 return userBean;
    }
    //查询管理员用户的信息
    public static UserBean select_Adminuser(int userID) throws SQLException {
    	UserBean userBean=null;
    	String sql="select * from user where userID=?";
        //boolean flag=false;
        ps=con.prepareStatement(sql);
        ps.setInt(1, userID);
        rs=ps.executeQuery();
        if(rs!=null){
        	if(rs.next()){
        	//flag=true;
        	userBean=new UserBean();
        	userBean.setUserID(rs.getInt("userID"));
        	userBean.setUserName(rs.getString("userName"));
        	userBean.setIndentificationCard(rs.getString("indentificationCard"));
        	userBean.setPhone(rs.getString("phone"));
        	userBean.setCountry(rs.getString("country"));
        	userBean.setAddress(rs.getString("address"));
        	userBean.setRoleName(rs.getString("roleName"));
        	userBean.setPassword(rs.getString("password"));
        	userBean.setRegisterStatus(rs.getInt("registerStatus"));
        	userBean.setCity(rs.getString("city"));
        	userBean.setProvince(rs.getString("province"));
        	userBean.setWorkuserNo(rs.getString("workuserNo"));
        	//userBean.setWorkStatus(rs.getInt("workStatus")); 
        	
        	}
        }
  	return userBean;   
    }
    //查看工作人员是否处于空闲阶段
    public static int Show_workStatus(String workuserNo) throws SQLException {
        String sql="select * from user where workuserNo=?";
        ps=con.prepareStatement(sql);
        ps.setString(1,workuserNo);
        int remainTaskNumber = 0;
        rs=ps.executeQuery();
    	 if(rs!=null){
    		 while(rs.next()){
    			 remainTaskNumber=rs.getInt("remainTaskNumber"); 
    		 }
    		 }
    	 return remainTaskNumber;
    }	
    //模糊查询用户信息
    public static List<UserBean> user_SelectDim(int registerStatus,String roleName,String name) throws SQLException{
   	 List<UserBean>list=new ArrayList<UserBean>();
   	 String sql="select * from user where registerStatus=? and roleName=? and userName like ?";
   	 ps=con.prepareStatement(sql);
   	 ps.setInt(1, registerStatus);
   	 ps.setString(2, roleName);
   	 ps.setString(3,"%"+name+"%");
   	 rs=ps.executeQuery();
   	 if(rs!=null){
   		 while(rs.next()){
   			 UserBean userBean=new UserBean();
   			 userBean.setUserID(rs.getInt("userID"));
   			 userBean.setUserName(rs.getString("userName"));
   			 userBean.setIndentificationCard(rs.getString("indentificationCard"));
   			 userBean.setPhone(rs.getString("phone"));
   			 userBean.setCountry(rs.getString("country"));
	   		 userBean.setAddress(rs.getString("address"));
	   		 userBean.setRoleName(rs.getString("roleName"));
	   		 userBean.setRegisterStatus(rs.getInt("registerStatus"));
	   		 userBean.setCity(rs.getString("city"));
	   		 userBean.setProvince(rs.getString("province"));
	   		 userBean.setWorkuserNo(rs.getString("workuserNo"));
	   		 userBean.setSex(rs.getString("sex"));
	   		 //userBean.setWorkStatus(rs.getInt("workStatus"));
	   		// userBean.setWorkevaluatingStatus(rs.getInt("workevaluatingStatus"));
   			 list.add(userBean);
   		 }
   	 }	
   	 return list;
    }	 
    //模糊查询用户信息
    public static List<UserBean> userPhone_SelectDim(String name,String roleName) throws SQLException{
   	 List<UserBean>list=new ArrayList<UserBean>();
   	 String sql="select * from user where userName like ? and roleName=?";
   	 ps=con.prepareStatement(sql);
   	 ps.setString(1,"%"+name+"%");
   	 ps.setString(2, roleName);
   	 rs=ps.executeQuery();
   	 if(rs!=null){
   		 while(rs.next()){
   			 UserBean userBean=new UserBean();
   			 userBean.setUserID(rs.getInt("userID"));
   			 userBean.setUserName(rs.getString("userName"));
   			 userBean.setIndentificationCard(rs.getString("indentificationCard"));
   			 userBean.setPhone(rs.getString("phone"));
   			 userBean.setCountry(rs.getString("country"));
	   		 userBean.setAddress(rs.getString("address"));
	   		 userBean.setRoleName(rs.getString("roleName"));
	   		 userBean.setRegisterStatus(rs.getInt("registerStatus"));
	   		 userBean.setCity(rs.getString("city"));
	   		 userBean.setProvince(rs.getString("province"));
	   		 userBean.setWorkuserNo(rs.getString("workuserNo"));
	   		 userBean.setSex(rs.getString("sex"));
	   		 //userBean.setWorkStatus(rs.getInt("workStatus"));
	   		// userBean.setWorkevaluatingStatus(rs.getInt("workevaluatingStatus"));
   			 list.add(userBean);
   		 }
   	 }	
   	 return list;
    }	 
    //查询用户的基本信息
    public static UserBean select_Userinformation(String workuserNo) throws SQLException {
    	 //List<UserBean>list=new ArrayList<UserBean>();
    	String sql="select * from user where workuserNo=?";
        //boolean flag=false;
    	UserBean userBean=null;
        ps=con.prepareStatement(sql);
        ps.setString(1, workuserNo); 
        rs=ps.executeQuery();
        if(rs!=null){
        	while(rs.next()){
        	//flag=true;
        	 userBean=new UserBean();
        	userBean.setUserID(rs.getInt("userID"));
        	userBean.setUserName(rs.getString("userName"));
        	userBean.setIndentificationCard(rs.getString("indentificationCard"));
        	userBean.setPhone(rs.getString("phone"));
        	userBean.setCountry(rs.getString("country"));
        	userBean.setAddress(rs.getString("address"));
        	userBean.setRoleName(rs.getString("roleName"));
        	userBean.setPassword(rs.getString("password"));
        	userBean.setRegisterStatus(rs.getInt("registerStatus"));
        	userBean.setCity(rs.getString("city"));
        	 userBean.setSex(rs.getString("sex"));
        	userBean.setProvince(rs.getString("province"));
        	userBean.setWorkuserNo(rs.getString("workuserNo"));
        	//userBean.setWorkStatus(rs.getInt("workStatus"));   
        	//list.add(userBean);
        	}
        }
  	return userBean;   
    }
    //增加工作人员处理的最大任务数
    public static boolean add_maxTaskNumber(int maxTaskNumber,String workuserNo) throws SQLException {
        String sql="update user set maxTaskNumber=?,remainTaskNumber=? where workuserNo=?";
        boolean flag=false;
        int handleStatus=1;
        int coun=MarchingDao.calculate_workusertasks(workuserNo,handleStatus);
        int remainTaskNumber=maxTaskNumber-coun;
        ps=con.prepareStatement(sql);
        ps.setInt(1,maxTaskNumber);
        ps.setInt(2,remainTaskNumber);
        ps.setString(3,workuserNo);
        int count=ps.executeUpdate();
        if(count==1){
            flag=true;
        }
        else
            flag=false;
        return flag;
    }
  //根据工号查询工作人员的剩余能处理的任务数
    public static int select_userTaskNumber(String workuserNo) throws SQLException {
    	String sql="select * from user where workuserNo=?";
    	int remainTaskNumber = 0;
        ps=con.prepareStatement(sql);
        ps.setString(1,workuserNo);
        rs=ps.executeQuery();
        if(rs!=null){
        	if(rs.next()){
        		remainTaskNumber=rs.getInt("remainTaskNumber");	
        	}
        	}
        return remainTaskNumber;
    }
  //根据工号修改工作人员的剩余能处理的任务数
    public static boolean select_workuserTaskNumber(int remainTaskNumber,String workuserNo) throws SQLException {
    	String sql="update user set remainTaskNumber=? where workuserNo=?";
    	//int remainTaskNumber = 0;
        ps=con.prepareStatement(sql);
        ps.setInt(1, remainTaskNumber);
        ps.setString(2,workuserNo);
        boolean flag=false;
        int count=ps.executeUpdate();
        if(count==1){
            flag=true;
        }
        else
            flag=false;
        return flag;
       
    }
  //根据工号修改工作人员的剩余能处理的任务数
    public static boolean update_userTaskNumber(String workuserNo) throws SQLException {
    	String sql="update user set remainTaskNumber=? where workuserNo=?";
    	boolean flag=false;
    	int c=UserDao.select_userTaskNumber(workuserNo);
    	int remainTaskNumber=c+1;
        ps=con.prepareStatement(sql);
        ps.setInt(1,remainTaskNumber);
        ps.setString(2,workuserNo);
        int count=ps.executeUpdate();
        if(count==1){
            flag=true;
        }
        else
            flag=false;
        return flag;
    }
    
  //根据工号修改工作人员的剩余能处理的任务数(匹配的时候)
    public static boolean update_workuserTaskNumber(String workuserNo) throws SQLException {
    	String sql="update user set remainTaskNumber=? where workuserNo=?";
    	boolean flag=false;
    	//int handleStatus=1;
//        int coun=MarchingDao.calculate_workusertasks(workuserNo,handleStatus);
        int c=UserDao.select_userTaskNumber(workuserNo);
//        System.out.println("c=="+c);
//        System.out.println("count==="+coun);
    	int remainTaskNumber=c-1;
    	System.out.println("remainTaskNumber=="+remainTaskNumber);
        ps=con.prepareStatement(sql);
        ps.setInt(1,remainTaskNumber);
        ps.setString(2,workuserNo);
        int count=ps.executeUpdate();
        if(count==1){
            flag=true;
        }
        else
            flag=false;
        return flag;
    }
    //根据工号显示诉求任务评价信息的平均值
    public static boolean update_workUserTaskNumber(int taskID) throws SQLException {
   	 String sql="select * from marching m,user u where m.workuserNo=u.workuserNo and m.taskID=?";
   	 //EstimateBean estimaneBean=null;
   	 boolean flag=false;
   	 ps=con.prepareStatement(sql);
   	 ps.setInt(1,taskID);
   	 rs=ps.executeQuery(); 
   	 if(rs!=null){
   		 while(rs.next()){
   			 String workuserNo=rs.getString("workuserNo");
   			 int remainTaskNumber=rs.getInt("remainTaskNumber");
   			 remainTaskNumber=remainTaskNumber+1;
   			 if(UserDao.select_workuserTaskNumber(remainTaskNumber, workuserNo))
   				 flag=true;
   			 else flag=flag;	
   		 }
   	 }
   	 System.out.println("userDao");
   	 System.out.println(flag);
   	 return flag;
   	 
    }	
    //更改头像
    public static boolean update_picture(int userID,String picture) throws SQLException {
    	String sql="update user set userPicture=? where userID=?";
    	boolean flag=false;
        ps=con.prepareStatement(sql);
        ps.setString(1,picture);
        ps.setInt(2,userID);
        int count=ps.executeUpdate();
        if(count==1){
            flag=true;
        }
        else
            flag=false;
        return flag;
    }
    
    
}
