package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bean.DBBean;

public class TUserDao {
	 private static Connection con= DBBean.getConn();
	    private static PreparedStatement ps=null;
	    private static ResultSet rs=null;
	    public static boolean update_user(String userName,String phone,String password,String role,String sex,String indentificationCard,String province,String city,String country,String address) throws SQLException {
	        String sql="update user set indentificationCard=?,province=?,city=?,country=?,address=? where userName=? and phone=? and password=? and roleName=? and sex=?";
	        boolean flag=false;
	        ps=con.prepareStatement(sql);
	        ps.setString(1,indentificationCard); 
	        ps.setString(2,province); 
	        ps.setString(3,city); 
	        ps.setString(4,country); 
	        ps.setString(5,address);
	        ps.setString(6,userName);
	        ps.setString(7,phone);
	        ps.setString(8,password);
	        ps.setString(9,role);
	        ps.setString(10,sex);
	        int count=ps.executeUpdate();
	        if(count==1){
	            flag=true;
	        }
	        else
	            flag=false;
	        return flag;
	    }	    
}
