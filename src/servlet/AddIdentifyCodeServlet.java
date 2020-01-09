package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import com.alibaba.fastjson.JSON;

import bean.Message;
import dao.IdentifyCodeDao;

public class AddIdentifyCodeServlet extends HttpServlet {

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
		String phone = req.getParameter("phone");
		String indentifyCode = String.valueOf((Math.random() * 9 + 1) * 100000);// ����6λ������֤��
		Message message = new Message();
		try {
			if (IdentifyCodeDao.add_indentifyCode(indentifyCode, phone)) {
				message.setCode(200);// 200������֤���ѳɹ�
				message.setMessage("��֤���ѷ���"); // ���ظ�ǰ̨
				message.setData(indentifyCode);// ������֤��
			} else {
				message.setCode(-11);// -11������֤���ȡʧ��
				message.setMessage("��֤���ȡʧ�ܣ������»�ȡ"); // ���ظ�ǰ̨
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
