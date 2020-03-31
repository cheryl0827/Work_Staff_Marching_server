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

import bean.RecordBean;
import bean.TMessage;
import bean.TaskBean;

import com.alibaba.fastjson.JSON;

import dao.RecordDao;
import dao.TaskDao;

public class WorkUserTaskShowServlet extends HttpServlet {
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
		String workuserNo = req.getParameter("workuserNo");

	    TMessage<List<TaskBean>> message=new TMessage<List<TaskBean>>();
		try {
			List<TaskBean> taskBean=new ArrayList<TaskBean>();
			taskBean=TaskDao.taskEnd_Select(workuserNo);
			if(taskBean!=null && taskBean.size()>0){
				message.setCode(200);
				message.setMessage("获取工作人员处理的诉求任务成功");
				message.setData(taskBean);
				out.print(JSON.toJSONString(message));
			}
			else{
				message.setCode(-11);
				message.setMessage("获取工作人员处理的诉求任务失败，该工作人员未处理诉求任务！");
				message.setData(null);
				out.print(JSON.toJSONString(message));
				
			}
		} catch (SQLException e) {
			

			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		
		
	}
}
