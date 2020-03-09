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

import bean.MarchingBean;
import bean.TMessage;
import bean.UserBean;

import com.alibaba.fastjson.JSON;

import dao.MarchingDao;
import dao.UserDao;

public class ShowTaskWorkUserServlet extends HttpServlet {

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
		String taskID=req.getParameter("taskID");
		TMessage<List<UserBean>> message=new TMessage<List<UserBean>>();
		try {
				List<UserBean> userBean=new ArrayList<UserBean>();
				userBean=MarchingDao.Show_workuserNo(taskID);
				if(userBean!=null && userBean.size()>0){
				message.setCode(200);
				message.setMessage("查询用户信息成功"); 
				message.setData(userBean);	
				}
				else{
					message.setCode(-11);
					message.setMessage("查询诉求任务的匹配失败");
					message.setData(null);
				}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			message.setCode(-11);
			message.setMessage("查询诉求任务的匹配失败");
			message.setData(null);
		}
		out.print(JSON.toJSONString(message));
		
	}
	}
