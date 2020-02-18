package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.PSA;
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
			int a=taskBean.size();//任务的数据条数
			int b=workuserEvaluatingIndicatorBean.size(); //工作人员的数据条数
			int c=a*b; 
			int count;
			float e=(float) 0.01;	//任务权重的转换，转换为1
			int[]task1=new int[a];  //a个任务的id存放
			int[]workuserEvaluatingIndicator1=new int[b]; //b个工作人员id存放
			int[]task=new int[c];  //c个任务的id存放
			int[]workuserEvaluatingIndicator=new int[c]; //c个工作人员id存放
			int[][] moduleList=new int[a][b]; //任务乘以工作人员的值
			int [][]array=new int[c][c];//构建c*c的矩阵
			//计算每个任务对应每个人有的专有指标数组，比如：{{1,2}，{3,4}}
			for(int i = 0; i < a; i++){
				task1[i]=taskBean.get(i).getTaskID();
				for(int j=0;j < b;j++)
				{
					workuserEvaluatingIndicator1[j]=workuserEvaluatingIndicatorBean.get(j).getUserID();
					count=(int) (taskBean.get(i).getCommunity()*workuserEvaluatingIndicatorBean.get(j).getCommunity()*e+
							taskBean.get(i).getUrgent()*workuserEvaluatingIndicatorBean.get(j).getUrgent()*e+
							taskBean.get(i).getPsychology()*workuserEvaluatingIndicatorBean.get(j).getPsychology()*e+
							taskBean.get(i).getOrganization()*workuserEvaluatingIndicatorBean.get(j).getOrganization()*e+
							taskBean.get(i).getAnalyse()*workuserEvaluatingIndicatorBean.get(j).getAnalyse()*e+
							taskBean.get(i).getLaw()*workuserEvaluatingIndicatorBean.get(j).getLaw()*e);
//					BigDecimal bb = new BigDecimal(count); 
//					float f1 = bb.setScale(0, BigDecimal.ROUND_HALF_UP).floatValue();
					moduleList[i][j]=count;
					
				}	
			}
			//计算a乘以b行的矩阵，比如：{{1,2，1，2}，{3,4,3,4}，{1,2，1，2}，{3,4,3,4}}
			 for(int i=0;i<c;i++){
		    	  System.out.println("");
		    	  for(int j = 0;j<c;j++){
		    		  int x=i%a;
		    		  int y=j%b;
		    		  array[i][j]=100-moduleList[x][y];
		    	  }
		      }
			//调用psa生成一个长度为c的一维数组，对应的匹配结果
			 PSA psa1=new PSA();
			 PSA.num1=array;
			 int[] optimal=new int[c];
			 optimal=psa1.bsss(PSA.num1);			 
//
//			 for(int i=0;i<c;i++){
//		    	  System.out.println("");
//		    	  for(int j = 0;j<c;j++){
//		    		  System.out.print(array[i][j]+"  ");
//		    	  }
//		      }
			 //生成长度为c的一位数组，对应的是task的id
			 for(int i=0;i<c;i++){
				 int f=i%a;
				 task[i]=task1[f];
			 } 
			 //生成长度为c的一位数组，对应的是workuserEvaluatingIndicator的id
			 for(int i=0;i<c;i++){
				 int f=i%b;
				 workuserEvaluatingIndicator[i]=workuserEvaluatingIndicator1[f]; 
			 }
//			 System.out.println("");
//			 System.out.println("任务id ");
//			 for(int i=0;i<c;i++){
//				  System.out.print(task[i]+"  "); 
//			 }
//			 System.out.println("");
//			 System.out.println("工作id ");
//			 for(int i=0;i<c;i++){
//				  System.out.print(workuserEvaluatingIndicator[i]+"  "); 
//			 }
			

		} catch (SQLException e) {
			// TODO Auto-generated catch block
		
		}
		
		
	}
	}
	
