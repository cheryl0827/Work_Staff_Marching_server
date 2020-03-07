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

import dao.IdentifyCodeDao;
import dao.RecordDao;

public class AddRecordServlet extends HttpServlet {

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		PrintWriter out = resp.getWriter();
		String nextVisitTime = req.getParameter("nextVisitTime");
		String recordContent = CodeExchange.ChineseCoding(req.getParameter("recordContent"));
		String recordTime = req.getParameter("recordTime");
		String taskID1=req.getParameter("taskID");
		int taskID=Integer.valueOf(taskID1).intValue();
		Message message = new Message();
		try {
			   if (RecordDao.add_record(taskID, nextVisitTime, recordContent, recordTime)) {
				message.setCode(200);
				message.setMessage("获取验证码成功"); 
				message.setData(null);
			    }
			else {
				message.setCode(-11);
				message.setMessage("获取验证码失败");
				message.setData(null);
				
			}
			out.print(JSON.toJSONString(message));

		} catch (SQLException e) {
			

			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void init() throws ServletException {
		// Put your code here
	}

}
