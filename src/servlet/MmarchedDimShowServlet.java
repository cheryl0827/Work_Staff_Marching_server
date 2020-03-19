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

import bean.CodeExchange;
import bean.TMessage;
import bean.TaskBean;

import com.alibaba.fastjson.JSON;

import dao.TaskDao;

public class MmarchedDimShowServlet extends HttpServlet {


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
		String marchingStatus1 = req.getParameter("marchingStatus");
		int marchingStatus=Integer.valueOf(marchingStatus1).intValue();
		String content=CodeExchange.ChineseCoding(req.getParameter("context"));
		TMessage message = new TMessage();
		try {
			List<TaskBean> taskBean=new ArrayList<TaskBean>();
			taskBean=TaskDao.worktask_MarchedSelectDim(marchingStatus, content);
			if(taskBean!=null && taskBean.size()>0){
				message.setCode(200);
				message.setMessage("查询诉求任务的匹配成功"); 
				message.setData(taskBean);	
				}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			message.setCode(-11);
			message.setMessage("查询诉求任务的匹配失败,没有相关的诉求任务");
			message.setData(null);
		}
		out.print(JSON.toJSONString(message));
		
	}

}