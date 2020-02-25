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

import bean.TMessage;
import bean.TaskBean;

import com.alibaba.fastjson.JSON;

import dao.TaskDao;

public class MarchingTaskShowServlet extends HttpServlet {

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
		String taskStatus1 = req.getParameter("taskStatus");
		int taskStatus=Integer.valueOf(taskStatus1).intValue();
		String marchingStatus1 = req.getParameter("marchingStatus");
		int marchingStatus=Integer.valueOf(marchingStatus1).intValue();
		String recordStatus1 = req.getParameter("recordStatus");
		TMessage message = new TMessage();
		try {
			List<TaskBean> taskBean=new ArrayList<TaskBean>();
			taskBean=TaskDao.worktaskSelect(taskStatus, marchingStatus);
			if(taskBean!=null && taskBean.size()>0){
				message.setCode(200);
				message.setMessage("获取用户数据成功");
				message.setData(taskBean);
				out.print(JSON.toJSONString(message));
			}
			else{
				message.setCode(-11);
				message.setMessage("获取用户数据失败");
				message.setData(null);
				
				out.print(JSON.toJSONString(message));
				
			}
		} catch (SQLException e) {
			

			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		
		
	}
}
