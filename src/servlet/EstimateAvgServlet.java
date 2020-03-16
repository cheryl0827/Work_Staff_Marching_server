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

import com.alibaba.fastjson.JSON;

import dao.EstimateDao;

public class EstimateAvgServlet extends HttpServlet {

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
		String workuserNo=req.getParameter("workuserNo");
		TMessage message = new TMessage();
		try {
		   // if(UserDao.update_workevaluatingStatus(workevaluatingStatus, userID)){
			EstimateBean estimateBean=EstimateDao.estimateAvg_show(workuserNo);
			    message.setCode(200);
				message.setMessage("查询评价信息的平均值成功"); 
				message.setData(estimateBean);	//}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			message.setCode(-11);
			message.setMessage("查询评价信息的平均值失败");
			message.setData(null);
		}
		out.print(JSON.toJSONString(message));
		
	}
	}
