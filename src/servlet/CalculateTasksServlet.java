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
import dao.UserDao;

public class CalculateTasksServlet extends HttpServlet {

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
		String taskWorknumber=req.getParameter("workuserNo");
		Message message = new Message();
		int a=0;
			try {
				a=TaskDao.calculate_usertasks(taskWorknumber);
				message.setCode(200);
				message.setMessage("计算工作人员诉求任务的数量成功"); 
				message.setData(a+"");
				out.print(JSON.toJSONString(message));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				message.setCode(-11);
				message.setMessage("计算工作人员诉求任务的数量失败");
				message.setData(null);						
			    out.print(JSON.toJSONString(message));
				e.printStackTrace();
			}  						
	}

	
	public void init() throws ServletException {
		// Put your code here
	}

}
