package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bean.DBBean;

public class UserDao {
    private static Connection con= DBBean.getConn();
    private static PreparedStatement ps=null;
    private static ResultSet rs=null;
    public static boolean add_user(String userName,String phone,String password,String role) throws SQLException {
        String sql="insert into user(userName,phone,password,roleName) value (?,?,?,?)";
        boolean flag=false;
        ps=con.prepareStatement(sql);
        ps.setString(1,userName);
        ps.setString(2,phone);
        ps.setString(3,password);
        ps.setString(4,role);
        int count=ps.executeUpdate();
        if(count==1){
            flag=true;
        }
        else
            flag=false;
        return flag;
    }

}
