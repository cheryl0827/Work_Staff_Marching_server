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

import bean.MarchingBean;
import bean.TMessage;

import com.alibaba.fastjson.JSON;

import dao.MarchingDao;

public class MarchedShowServlet extends HttpServlet {

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
		  TMessage<List<MarchingBean>> message=new TMessage<List<MarchingBean>>();
			try {
				List<MarchingBean> marchingBean=new ArrayList<MarchingBean>();
				marchingBean=MarchingDao.Marching_SelectAll();
				if(marchingBean!=null && marchingBean.size()>0){
				message.setCode(200);
				message.setMessage("查询诉求任务的匹配成功"); 
				message.setData(marchingBean);	
				}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			message.setCode(-11);
			message.setMessage("查询诉求任务的匹配失败");
			message.setData(null);
		}
		out.print(JSON.toJSONString(message));
		
	}

}
