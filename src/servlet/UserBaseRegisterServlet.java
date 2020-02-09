package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

import dao.UserDao;
import bean.Message;

public class UserBaseRegisterServlet extends HttpServlet {

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		resp.setContentType("text/html");
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		PrintWriter out = resp.getWriter();
		String phone = req.getParameter("phone");
		String password=req.getParameter("password");
		String userName=req.getParameter("userName");
		String roleName=req.getParameter("roleName");
		String sex=req.getParameter("sex");
		String workuserNo=req.getParameter("workuserNo");
		System.out.print(workuserNo);
		Message message=new Message();//传给前台
		try {
			if(UserDao.add_user(userName, phone, password, roleName,sex,workuserNo)){
				message.setCode(200);//成功
				message.setData(null);//传数据	
				message.setMessage("注册成功");
			}
			else {
				message.setCode(-11);// 失败
				message.setData(null);
				message.setMessage("注册失败");//传提醒
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
