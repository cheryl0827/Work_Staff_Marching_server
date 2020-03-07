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

import dao.TUserDao;
import dao.UserDao;

public class UserChangeInformationServlet extends HttpServlet {


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
		String phone = req.getParameter("phone");
		String province=CodeExchange.ChineseCoding(req.getParameter("province"));
		String city=CodeExchange.ChineseCoding(req.getParameter("city"));
		String country=CodeExchange.ChineseCoding(req.getParameter("country"));
		String address=CodeExchange.ChineseCoding(req.getParameter("address"));
		String userID1=req.getParameter("userID");
		int userID=Integer.valueOf(userID1).intValue();
		Message message=new Message();
		try {
				if(UserDao.update_userphone(phone, userID)&&UserDao.update_useraddress(province, city, country, address, userID)){
					message.setCode(200);
					message.setMessage("修改个人信息成功"); 
					message.setData(null);
			    }
			else {
					message.setCode(-11);
					message.setMessage("修改个人信息失败");
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
