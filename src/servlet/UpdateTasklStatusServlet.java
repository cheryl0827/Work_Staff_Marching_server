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

public class UpdateTasklStatusServlet extends HttpServlet {

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
		int taskID=Integer.valueOf(taskID1);
		int taskStatus=Integer.valueOf(req.getParameter("taskStatus"));
		int pingjiaStatus=Integer.valueOf(req.getParameter("pingjiaStatus"));
		Message message = new Message();
		try {
			if(TaskDao.update_usertaskStatus(taskID, taskStatus, pingjiaStatus)){
				message.setCode(200);
				message.setMessage("办理结束成功"); 
				message.setData(null);	

		}else {
			message.setCode(-11);
			message.setMessage("办理结束失败");
			message.setData(null);
		}
			
		} catch (SQLException e) {
			

			// TODO Auto-generated catch block
			e.printStackTrace();
			message.setCode(-11);
			message.setMessage("办理结束失败");
			message.setData(null);
		}
		out.print(JSON.toJSONString(message));
		
	}
}
