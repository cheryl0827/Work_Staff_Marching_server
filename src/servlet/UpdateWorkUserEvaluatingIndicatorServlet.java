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

import dao.WorkUserEvaluatingIndicatorDao;

public class UpdateWorkUserEvaluatingIndicatorServlet extends HttpServlet {
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
		int community=Integer.valueOf(req.getParameter("community")).intValue();
		int urgent=Integer.valueOf(req.getParameter("urgent")).intValue();
		int psychology=Integer.valueOf(req.getParameter("psychology")).intValue();
		int organization=Integer.valueOf(req.getParameter("organization")).intValue();
		int analyse=Integer.valueOf(req.getParameter("analyse")).intValue();
		int law=Integer.valueOf(req.getParameter("law")).intValue();
		String workuserNo=req.getParameter("workuserNo");
		Message message = new Message();
		try {
			if(WorkUserEvaluatingIndicatorDao.update_workuser(community, urgent, psychology, organization, analyse, law, workuserNo)){
			   // UserBean userBean=UserDao.select_userlogin(phone, password, rolename);
				message.setCode(200);
				message.setMessage("工作人员指标修改成功"); 
				message.setData(null);
				}		

		} catch (SQLException e) {
			

			// TODO Auto-generated catch block
			e.printStackTrace();
			message.setCode(-11);
			message.setMessage("工作人员指标修改失败");
			message.setData(null);
		}
		out.print(JSON.toJSONString(message));
		
	}
}
