package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.CodeExchange;
import bean.Message;

import com.alibaba.fastjson.JSON;

import dao.UserDao;

public class UserChangeAdressServlet extends HttpServlet {

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("text/html");
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		PrintWriter out = resp.getWriter();
		String province= req.getParameter("province");
		String city=req.getParameter("city");
		String country=req.getParameter("country");
		String address=req.getParameter("address");
		String userID1=req.getParameter("userID");
		int userID=Integer.valueOf(userID1).intValue();
		Message message=new Message();
		try {
				if(UserDao.update_useraddress(province, city, country, address, userID)){
					message.setCode(200);
					message.setMessage("修改地址成功"); 
					message.setData(null);
			    }
			else {
					message.setCode(-11);
					message.setMessage("修改地址失败");
					message.setData(null);
				
			}				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		out.print(JSON.toJSONString(message));	
		
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
