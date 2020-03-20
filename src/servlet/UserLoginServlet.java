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
import bean.TMessage;
import bean.UserBean;

import com.alibaba.fastjson.JSON;

import dao.IdentifyCodeDao;
import dao.UserDao;

public class UserLoginServlet extends HttpServlet {
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
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		PrintWriter out = resp.getWriter();
		String phone = req.getParameter("phone");
		
		String password=req.getParameter("password");
		String rolename=CodeExchange.ChineseCoding(req.getParameter("usertype"));
		TMessage message = new TMessage();
		try {
			if(UserDao.select_userloginregister(phone, password, rolename)==2){
			    UserBean userBean=UserDao.select_userlogin(phone, password, rolename);
			    userBean.setUserPicture(req.getRequestURL().toString().replace(req.getServletPath(), "")
                        + "/image/" +userBean.getUserPicture());
				message.setCode(200);
				message.setMessage("登录成功"); 
				message.setData(userBean);
				}		

		} catch (SQLException e) {
			

			// TODO Auto-generated catch block
			e.printStackTrace();
			message.setCode(-11);
			message.setMessage("登录失败");
			message.setData(null);
		}
		out.print(JSON.toJSONString(message));
		
	}
	}
