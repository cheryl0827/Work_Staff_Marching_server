package dao;



import java.sql.Connection;


import java.sql.PreparedStatement;
import java.sql.ResultSet;




import java.sql.SQLException;

import bean.DBBean;

public class IdentifyCodeDao {
  private static Connection con=DBBean.getConn();
  private static PreparedStatement ps=null;
  private static ResultSet rs=null;
  public static boolean add_indentifyCode(String indentifyCode,String phone) throws SQLException{
	 //con=DBBean.getConn();
	  String sql="insert into indentifycode(phone,indentifyCode) value (?,?)";
	  boolean flag=false;
	  ps=con.prepareStatement(sql);
	  ps.setString(1, phone);
	  ps.setString(2,indentifyCode);
	  int count=ps.executeUpdate();
	  if(count==1){
		  flag=true;
	  }
	  else
		  flag=false;
	  return flag;	     	  
  }
  public static boolean select_user(String phone) throws SQLException {
      String sql="select * from user where phone=?";
      boolean flag=false;
      ps=con.prepareStatement(sql);
      ps.setString(1, phone);
      rs=ps.executeQuery();
      if(rs!=null){
      	if(rs.next()){
      	flag=true;}
      }
	return flag;   
  }

 public static boolean update_userpassword(String phone,String password) throws SQLException {
			 String sql="update user set password=? where  phone=?";
		        boolean flag=false;
		        ps=con.prepareStatement(sql);
		        ps.setString(1,password); 
		        ps.setString(2,phone); 
		        int count=ps.executeUpdate();
		        if(count==1){
		            flag=true;
		        }
		        else
		            flag=false;
		        return flag;
		    }

  public static boolean select_indentifyCode(String indentifyCode,String phone){
	  return false;
  }
  
  
  
}
