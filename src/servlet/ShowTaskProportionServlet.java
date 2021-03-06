package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.TMessage;
import bean.TaskBean;

import com.alibaba.fastjson.JSON;

import dao.TaskDao;

public class ShowTaskProportionServlet extends HttpServlet {


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
		int taskID=Integer.valueOf(req.getParameter("taskID")).intValue();
		TMessage message = new TMessage();
		try {
		   // if(UserDao.update_workevaluatingStatus(workevaluatingStatus, userID)){
			TaskBean taskBean=TaskDao.admintask_Select(taskID);
				message.setCode(200);
				message.setMessage("查询诉求任务的权重成功"); 
				message.setData(taskBean);	//}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			message.setCode(-11);
			message.setMessage("查询诉求任务的权重失败");
			message.setData(null);
		}
		out.print(JSON.toJSONString(message));
		
	}
	}
