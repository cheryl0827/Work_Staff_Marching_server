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

public class UserRegisterServlet extends HttpServlet {

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
		String password=req.getParameter("password");
		String userName=CodeExchange.ChineseCoding(req.getParameter("userName"));
		String roleName=CodeExchange.ChineseCoding(req.getParameter("roleName"));
		String sex=CodeExchange.ChineseCoding(req.getParameter("sex"));
		//String workuserNo=req.getParameter("workuserno");
		String indentificationCard=req.getParameter("indentificationCard");
		String province=CodeExchange.ChineseCoding(req.getParameter("province"));
		String city=CodeExchange.ChineseCoding(req.getParameter("city"));
		String country=CodeExchange.ChineseCoding(req.getParameter("country"));
		String address=CodeExchange.ChineseCoding(req.getParameter("address"));
		Message message=new Message();
		try {
			if(TUserDao.update_user(userName, phone, password, roleName, sex, indentificationCard, province, city, country, address)){
				message.setCode(200);//成功
				message.setData(null);//传数据	
				message.setMessage("普通用户注册更新成功");
			}
			else {
				message.setCode(-11);// 失败
				message.setData(null);
				message.setMessage("普通用户注册更新失败");//传提醒
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
