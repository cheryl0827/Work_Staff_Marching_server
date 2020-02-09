package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Message;
import bean.TMessage;
import bean.UserBean;

import com.alibaba.fastjson.JSON;

import dao.EstimateDao;
import dao.TaskDao;
import dao.UserDao;

public class TaskAddServlet extends HttpServlet {

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
		String taskAdress = req.getParameter("taskAdress");
		String taskCatagery=req.getParameter("taskCatagery");
		String taskDetaiAdress=req.getParameter("taskDetaiAdress");
		String taskContent=req.getParameter("taskContent");
		String taskTime=req.getParameter("taskTime");
		String userID1=req.getParameter("userID");
		int userID=Integer.valueOf(userID1).intValue();
		Message message = new Message();
		try {
			if(TaskDao.add_task(taskAdress, taskCatagery, taskContent, taskTime, userID, taskDetaiAdress)){
			   // UserBean userBean=UserDao.select_userlogin(phone, password, rolename);
				message.setCode(200);
				message.setMessage("诉求任务填写成功"); 
				message.setData(null);
				}		

		} catch (SQLException e) {
			

			// TODO Auto-generated catch block
			e.printStackTrace();
			message.setCode(-11);
			message.setMessage("诉求任务填写失败");
			message.setData(null);
		}
		out.print(JSON.toJSONString(message));
		
	}
}
