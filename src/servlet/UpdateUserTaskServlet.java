package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Message;

import com.alibaba.fastjson.JSON;

import dao.TaskDao;

public class UpdateUserTaskServlet extends HttpServlet {

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
		String taskID1=req.getParameter("taskID");
		int taskID=Integer.valueOf(taskID1).intValue();
		int taskStatus=Integer.valueOf(req.getParameter("taskStatus")).intValue();
		Message message = new Message();
		try {
			if(TaskDao.update_taskStatus(taskID, taskStatus)){
				message.setCode(200);
				message.setMessage("诉求任务审核成功"); 
				message.setData(null);	

		}} catch (SQLException e) {
			

			// TODO Auto-generated catch block
			e.printStackTrace();
			message.setCode(-11);
			message.setMessage("诉求任务审核失败");
			message.setData(null);
		}
		out.print(JSON.toJSONString(message));
		
	}

}
