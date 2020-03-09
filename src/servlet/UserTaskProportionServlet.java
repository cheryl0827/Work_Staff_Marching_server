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
		String taskAdress =CodeExchange.ChineseCoding(req.getParameter("taskAdress"));
		String taskCatagery=CodeExchange.ChineseCoding(req.getParameter("taskCatagery"));
		String taskDetaiAdress=CodeExchange.ChineseCoding(req.getParameter("taskDetaiAdress"));
		String taskContent=CodeExchange.ChineseCoding(req.getParameter("taskContent"));
		String community=req.getParameter("community");
		String urgent=req.getParameter("urgent");
		String psychology=req.getParameter("psychology");
		String organization=req.getParameter("organization");
		String analyse=req.getParameter("analyse");
		String law=req.getParameter("law");
		//System.out.println(taskAdress+taskCatagery+taskDetaiAdress+taskContent+community+urgent+psychology+organization+analyse+law);
//		int community=0;
//	  // int community=Integer.valueOf(req.getParameter("community"));
//	    int urgent=Integer.valueOf(req.getParameter("urgent"));
//	    int psychology=Integer.valueOf(req.getParameter("psychology"));
//	    int organization=Integer.valueOf(req.getParameter("organization"));
//	    int analyse=Integer.valueOf(req.getParameter("analyse"));
//	    int law=Integer.valueOf(req.getParameter("law"));
		Message message = new Message();
		try {
			if(TaskDao.add_taskproportion(taskAdress, taskCatagery, taskContent, taskDetaiAdress, community, urgent, psychology, organization, analyse, law)){
			   // UserBean userBean=UserDao.select_userlogin(phone, password, rolename);
				message.setCode(200);
				message.setMessage("诉求任务权重表填写成功"); 
				message.setData(null);
				}	
			else{
				message.setCode(-11);
				message.setMessage("诉求任务权重表填写失败");
				message.setData(null);	
			}

		} catch (SQLException e) {
			
			message.setCode(-11);
			message.setMessage("诉求任务权重表填写失败");
			message.setData(null);
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		out.print(JSON.toJSONString(message));
		
	}
}
