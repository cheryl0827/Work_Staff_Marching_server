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

public class UserTaskProportionServlet extends HttpServlet {

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
		
	    int community=Integer.valueOf(req.getParameter("community")).intValue();
	    int urgent=Integer.valueOf(req.getParameter("urgent")).intValue();
	    int psychology=Integer.valueOf(req.getParameter("psychology")).intValue();
	    int organization=Integer.valueOf(req.getParameter("organization")).intValue();
	    int analyse=Integer.valueOf(req.getParameter("analyse")).intValue();
	    int law=Integer.valueOf(req.getParameter("law")).intValue();
		Message message = new Message();
		try {
			if(TaskDao.add_taskproportion(taskAdress, taskCatagery, taskContent, taskDetaiAdress, community, urgent, psychology, organization, analyse, law)){
			   // UserBean userBean=UserDao.select_userlogin(phone, password, rolename);
				message.setCode(200);
				message.setMessage("诉求任务权重表填写成功"); 
				message.setData(null);
				}		

		} catch (SQLException e) {
			

			// TODO Auto-generated catch block
			e.printStackTrace();
			message.setCode(-11);
			message.setMessage("诉求任务权重表填写失败");
			message.setData(null);
		}
		out.print(JSON.toJSONString(message));
		
	}
}
