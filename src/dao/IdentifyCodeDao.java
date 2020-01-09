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
  public static boolean select_indentifyCode(String indentifyCode,String phone){
	  return false;
  }
  
  
  
}
