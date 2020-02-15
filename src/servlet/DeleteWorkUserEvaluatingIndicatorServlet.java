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

public class DeleteWorkUserEvaluatingIndicatorServlet extends HttpServlet {
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
		
		int userID=Integer.valueOf(req.getParameter("userID")).intValue();
		Message message = new Message();
		try {
			if(WorkUserEvaluatingIndicatorDao.delete_workuser(userID)){
			   // UserBean userBean=UserDao.select_userlogin(phone, password, rolename);
				message.setCode(200);
				message.setMessage("工作人员指标删除成功"); 
				message.setData(null);
				}	
			else{
				message.setCode(-11);
				message.setMessage("工作人员指标删除失败");
				message.setData(null);
			}
			out.print(JSON.toJSONString(message));
		} catch (SQLException e) {
			

			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		
		
	}
}
