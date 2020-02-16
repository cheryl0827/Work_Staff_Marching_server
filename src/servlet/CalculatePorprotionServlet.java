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

import bean.TMessage;
import bean.TaskBean;
import bean.WorkuserEvaluatingIndicatorBean;

import com.alibaba.fastjson.JSON;

import dao.TaskDao;
import dao.WorkUserEvaluatingIndicatorDao;

public class CalculatePorprotionServlet extends HttpServlet {
		// TODO Auto-generated method stub
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
		int taskStatus=3;
		try {
			List<WorkuserEvaluatingIndicatorBean> workuserEvaluatingIndicatorBean=new ArrayList<WorkuserEvaluatingIndicatorBean>();
			workuserEvaluatingIndicatorBean=WorkUserEvaluatingIndicatorDao.selectAll_workuser();
			List<TaskBean> taskBean=new ArrayList<TaskBean>();
			taskBean=TaskDao.task_Porprotion(taskStatus);
			//List list=new ArrayList();
			float count;
			float community=(float) 0.001;
			float urgent=(float) 0.002;
			float psychology=(float) 0.001;
			float organization=(float) 0.003;
			float analyse=(float) 0.001;
			float law=(float) 0.002;
			out.print(taskBean.size());
			float[][] moduleList=new float[taskBean.size()][workuserEvaluatingIndicatorBean.size()];
			for(int i = 0; i < taskBean.size(); i++){
				for(int j=0;j<workuserEvaluatingIndicatorBean.size();j++)
				{
					count=taskBean.get(i).getCommunity()*workuserEvaluatingIndicatorBean.get(j).getCommunity()*community+
							taskBean.get(i).getUrgent()*workuserEvaluatingIndicatorBean.get(j).getUrgent()*urgent+
							taskBean.get(i).getPsychology()*workuserEvaluatingIndicatorBean.get(j).getPsychology()*psychology+
							taskBean.get(i).getOrganization()*workuserEvaluatingIndicatorBean.get(j).getOrganization()*organization+
							taskBean.get(i).getAnalyse()*workuserEvaluatingIndicatorBean.get(j).getAnalyse()*analyse+
							taskBean.get(i).getLaw()*workuserEvaluatingIndicatorBean.get(j).getLaw()*law;
					 moduleList[i][j]=count;
					 //list.add(count);
				}
				
				
			}	
			for(int i = 0; i < taskBean.size(); i++){
				for(int j=0;j<workuserEvaluatingIndicatorBean.size();j++){
					out.println(moduleList[i][j]);
				}
				}
		
			

		} catch (SQLException e) {
			// TODO Auto-generated catch block
		
		}
		
		
	}
	}
	
