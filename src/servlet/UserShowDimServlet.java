package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.CodeExchange;
import bean.TMessage;
import bean.UserBean;

import com.alibaba.fastjson.JSON;

import dao.UserDao;

public class UserShowDimServlet extends HttpServlet {
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
		String roleName = CodeExchange.ChineseCoding(req.getParameter("roleName"));
		String registerStatus1 = req.getParameter("registerStatus");
		int registerStatus=Integer.valueOf(registerStatus1).intValue();
		String name=CodeExchange.ChineseCoding(req.getParameter("name"));
	    TMessage<List<UserBean>> message=new TMessage<List<UserBean>>();
		try {
			List<UserBean> userBean=new ArrayList<UserBean>();
			userBean=UserDao.user_SelectDim(registerStatus, roleName, name);
			if(userBean!=null && userBean.size()>0){
				message.setCode(200);
				message.setMessage("获取用户信息");
				message.setData(userBean);
				out.print(JSON.toJSONString(message));
			}
			else{
				message.setCode(-11);
				message.setMessage("获取用户数据失败，没有相关的工作用户信息");
				message.setData(null);
				out.print(JSON.toJSONString(message));
				
			}
		} catch (SQLException e) {
			

			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		
		
	}
}
