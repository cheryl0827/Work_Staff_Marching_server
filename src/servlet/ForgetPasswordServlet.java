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

import dao.IdentifyCodeDao;

public class ForgetPasswordServlet extends HttpServlet {
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
		String phone = req.getParameter("phone");
		String password=req.getParameter("password");
		Message message = new Message();
		try {
			   if (IdentifyCodeDao.update_userpasswordbyphone(phone,password)){
				message.setCode(200);
				message.setMessage("找回密码成功"); 
				message.setData(null);
			    }
			else {
				message.setCode(200);
				message.setMessage("");
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
