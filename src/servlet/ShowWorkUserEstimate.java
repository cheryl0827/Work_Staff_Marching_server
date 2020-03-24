package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.EstimateBean;
import bean.TMessage;
import bean.WorkuserEvaluatingIndicatorBean;

import com.alibaba.fastjson.JSON;

import dao.EstimateDao;
import dao.WorkUserEvaluatingIndicatorDao;

public class ShowWorkUserEstimate extends HttpServlet {
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
		//int workevaluatingStatus=2;
		String workuserNo=req.getParameter("workuserNo");
		String taskID=req.getParameter("taskID");
		TMessage message = new TMessage();
		try {
		   // if(UserDao.update_workevaluatingStatus(workevaluatingStatus, userID)){
			EstimateBean estimateBean=EstimateDao.select_workuser(workuserNo,taskID);
			    message.setCode(200);
				message.setMessage("查询工作用户评价信息成功"); 
				message.setData(estimateBean);	//}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			message.setCode(-11);
			message.setMessage("查询工作用户评价信息失败");
			message.setData(null);
		}
		out.print(JSON.toJSONString(message));
		
	}
	}
