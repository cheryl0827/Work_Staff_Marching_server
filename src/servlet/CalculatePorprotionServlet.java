package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
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

import dao.MarchingDao;
import dao.TaskDao;
import dao.UserDao;
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
		String Number=req.getParameter("Number");//诉求任务所需要的工作人员数量
		String adminID=req.getParameter("adminID");//匹配人员的工作id
		String marchingTime=req.getParameter("marchingTime");//匹配时间
		
		String all_id=req.getParameter("all_id");//获取前台需要匹配的诉求任务的taskID
		String[] taskids = all_id.split(",");//要匹配的诉求任务的taskID的数组，注意这个数组是String类型的
		List<WorkuserEvaluatingIndicatorBean> workuserEvaluatingIndicatorBean=new ArrayList<WorkuserEvaluatingIndicatorBean>();
		try {
			workuserEvaluatingIndicatorBean=WorkUserEvaluatingIndicatorDao.select_FreeWorkuser();//查询有空闲的工作人员的评价指标值
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		String workuserNo;
		int taskLength=taskids.length;//诉求任务数组的长度
		int peopleLength=workuserEvaluatingIndicatorBean.size();//工作人员的长度
		int CLength=taskLength*peopleLength;//最后矩阵的行数和列数
		
		int[]workUserRemainTaskNumber=new int[peopleLength];
		int[]WorkuserEvaluatingIndicators=new int[peopleLength]; //peopleLength个工作人员工号的存放数组
		int[]WorkuserEvaluatingIndicator=new int[CLength]; //CLength个工作人员工号的存放数组
		String[]Task=new String[CLength];  //CLength个诉求任务的taskID存放数组
	
		int[][] ModuleList=new int[taskLength][peopleLength]; //诉求任务乘以工作人员的综合指标值
		int [][]Array=new int[CLength][CLength];//构建CLength*CLength的矩阵
		
		int Count;//计算诉求任务对应每个工作人员的一个综合指标
		float E=(float) 0.01;	//任务权重的转换，转换为1
		
		for(int i=0;i<taskLength;i++){//计算taskLength行，peopleLength列的矩阵，每个诉求任务对应每个工作人员的一个综合指标值，比如{{1,2}，{3,4}}
				TaskBean taskBean = null;
				try {
					taskBean = TaskDao.task_porprotion(taskids[i]);//查询诉求任务的评价指标值
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				for(int j=0;j<peopleLength;j++){
					WorkuserEvaluatingIndicators[j]=Integer.valueOf(workuserEvaluatingIndicatorBean.get(j).getWorkuserNo()).intValue();
					workuserNo=workuserEvaluatingIndicatorBean.get(j).getWorkuserNo();
					try {
						workUserRemainTaskNumber[j]=UserDao.Show_workStatus(workuserNo);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					Count=(int) (taskBean.getCommunity()*workuserEvaluatingIndicatorBean.get(j).getCommunity()*E+
							taskBean.getUrgent()*workuserEvaluatingIndicatorBean.get(j).getUrgent()*E+
							taskBean.getPsychology()*workuserEvaluatingIndicatorBean.get(j).getPsychology()*E+
							taskBean.getOrganization()*workuserEvaluatingIndicatorBean.get(j).getOrganization()*E+
							taskBean.getAnalyse()*workuserEvaluatingIndicatorBean.get(j).getAnalyse()*E+
							taskBean.getLaw()*workuserEvaluatingIndicatorBean.get(j).getLaw()*E);
					        ModuleList[i][j]=Count;
					}			
			}
		
		//计算CLength*CLength的矩阵，比如：{{1,2，1，2}，{3,4,3,4}，{1,2,1,2}，{3,4,3,4}}
		 for(int i=0;i<CLength;i++){
	    	  for(int j = 0;j<CLength;j++){
	    		  int x=i%taskLength;
	    		  int y=j%peopleLength;
	    		  Array[i][j]=100-ModuleList[x][y];
	    	  }
	      }
			 //生成长度为CLength的一维数组，对应的是诉求任务的taskID
			 for(int i=0;i<CLength;i++){
				 int f=i%taskLength;
				 Task[i]=taskids[f];
			 } 
			 //生成长度为CLength的一位数组，对应的是工作人员的工号
			 for(int i=0;i<CLength;i++){
				 int f=i%peopleLength;
				 WorkuserEvaluatingIndicator[i]=WorkuserEvaluatingIndicators[f]; 
			 }
		 
			//调用psa生成一个长度为CLength的一维数组，对应的匹配结果
			 PSA psa1=new PSA();
			 PSA.num1=Array;
			 int[] optimal=new int[CLength];
			 optimal=psa1.bsss(PSA.num1);
			 
			 //增加匹配信息调用MarchingDao.add_marching(workuserNo, adminID, taskID, marchingTime)
			 //匹配消息后工作人员信息修改UserDao.update_workuserTaskNumber(workuserNo)
			 
			 
			 
			 
			 //显示长度为peopleLength的一位数组，对应的是工作人员的还能处理的任务数
			 System.out.println();
			 System.out.print("工作人员的还能处理的任务数:");
			 for(int i=0;i<peopleLength;i++){
				 System.out.print(workUserRemainTaskNumber[i]+"  ");
			 }
			 //显示长度为peopleLength的一位数组，对应的是工作人员的工号
			 System.out.println();
			 System.out.print("原始的工作人员的工号:");
			 for(int i=0;i<peopleLength;i++){
				 System.out.print( WorkuserEvaluatingIndicators[i]+"  ");
			 }
			 //显示长度为peopleLength的一位数组，对应的是诉求任务的taskID
			 System.out.println();
			 System.out.print("原始的诉求任务的taskID:");
			 for(int i=0;i<taskLength;i++){
				 System.out.print( taskids[i]+"  ");
			 }
		   //行代表诉求任务，列代表工作人员，显示生成的taskLength行，peopleLength列的矩阵
		    System.out.println();
		    System.out.print("原始taskLength行，peopleLength列的矩阵：(行代表诉求任务，列代表工作人员)");
			for(int i=0;i<taskLength;i++){
				System.out.println();
				for(int j=0;j<peopleLength;j++){
					System.out.print(ModuleList[i][j]+"  ");
				}
			}
			//计算CLength*CLength的矩阵，比如：{{1,2，1，2}，{3,4,3,4}，{1,2,1,2}，{3,4,3,4}}
			 System.out.println();
			 System.out.print("整合后的CLength行，CLength列的矩阵：(行代表诉求任务，列代表工作人员)");
			 for(int i=0;i<CLength;i++){
				 System.out.println();
		    	  for(int j = 0;j<CLength;j++){
		    		  System.out.print(Array[i][j]+"  ");
		    	  }
		      }
		  //调用psa获取到的匹配结果一维数组
			 System.out.println();
			 System.out.print("调用psa获取到的匹配结果：");
			 for(int i=0;i<CLength;i++){
		    		System.out.print(optimal[i]+"  ");
		    	  }
		      
			 //显示长度为CLength的一维数组，对应的是诉求任务的taskID
			 System.out.println();
			 System.out.print("整合后诉求任务的taskID:(对应矩阵的行)");
			 for(int i=0;i<CLength;i++){
				 System.out.print(Task[i]+"  ");
			 } 
			 //显示长度为CLength的一位数组，对应的是工作人员的工号
			 System.out.println();
			 System.out.print("整合后的工作人员的工号:(对应矩阵的列)");
			 for(int i=0;i<CLength;i++){
				 System.out.print(WorkuserEvaluatingIndicator[i]+"  ");
			 }
			 
			
			
		 
		
		
		

	}
}
	
