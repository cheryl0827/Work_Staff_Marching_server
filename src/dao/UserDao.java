package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bean.DBBean;
import bean.UserBean;

public class UserDao {
    private static Connection con= DBBean.getConn();
    private static PreparedStatement ps=null;
    private static ResultSet rs=null;
    //用户注册
    public static boolean add_user(String userName,String phone,String password,String role,String sex,String workuserNo ) throws SQLException {
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
        	userBean.setWorkStatus(rs.getInt("workStatus"));
        	
        	
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
   
}
