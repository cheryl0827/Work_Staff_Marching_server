
package bean;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBBean {
    public static Connection conn = null;

    public static Connection getConn() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String driver_url = "jdbc:mysql://localhost:3306/wsm?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull";
            conn = DriverManager.getConnection(driver_url, "root", "");
            if (conn != null) {
               System.out.print("连接成功");

            } else {
            	System.out.print("连接失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }
    public static void closeAll(Connection conn, PreparedStatement ps) {
        System.out.println("数据库断开连接");
        if (conn != null) {
            try {

                conn.close();

            } catch (SQLException e) {

                e.printStackTrace();

            }

        }

        if (ps != null) {

            try {

                ps.close();

            } catch (SQLException e) {

                e.printStackTrace();

            }

        }

    }


    public static void closeAll(Connection conn, PreparedStatement ps,
                                ResultSet rs) {

        if (conn != null) {

            try {

                conn.close();

            } catch (SQLException e) {

                e.printStackTrace();

            }

        }

        if (ps != null) {

            try {

                ps.close();

            } catch (SQLException e) {

                e.printStackTrace();

            }

        }

        if (rs != null) {

            try {

                rs.close();

            } catch (SQLException e) {

                e.printStackTrace();

            }

        }

    }

}
