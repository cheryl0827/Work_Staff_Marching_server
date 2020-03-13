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

import dao.RecordDao;
import dao.UserDao;

public class AddWorkUserTaskNtmberServlet extends HttpServlet {
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
		String workuserNo=req.getParameter("workuserNo");
		int maxTaskNumber=Integer.valueOf(req.getParameter("maxTaskNumber")).intValue();
//		System.out.print("a"+workuserNo);
//		System.out.print("b"+maxTaskNumber);
		Message message = new Message();
		try {
			   if (UserDao.add_maxTaskNumber(maxTaskNumber, workuserNo)) {
				message.setCode(200);
				message.setMessage("增加用户的最大任务数成功"); 
				message.setData(null);
			    }
			else {
				message.setCode(-11);
				message.setMessage("增加用户的最大任务数失败");
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
