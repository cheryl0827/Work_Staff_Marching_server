package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.PSA;
import bean.Message;
import bean.TMessage;
import bean.TaskBean;
import bean.WorkuserEvaluatingIndicatorBean;

import com.alibaba.fastjson.JSON;
import com.mysql.jdbc.StringUtils;

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
		String adminID=req.getParameter("adminID");//匹配人员的工作id
		String marchingTime=req.getParameter("marchingTime");//匹配时间
		int marchingStatus=2;//匹配状态
		
		Message message=new Message();
		String all_id=req.getParameter("all_id");//获取前台需要匹配的诉求任务的taskID
		String[] taskids = all_id.split(",");//要匹配的诉求任务的taskID的数组，注意这个数组是String类型的
		
		String workuserNo;
		int taskLength=taskids.length;//诉求任务数组的长度
		String[][] task=new String[taskLength][2];//诉求任务和诉求任务所需要的工作人员数量
		List<WorkuserEvaluatingIndicatorBean> workuserEvaluatingIndicatorBean=new ArrayList<WorkuserEvaluatingIndicatorBean>();
		try {
			workuserEvaluatingIndicatorBean=WorkUserEvaluatingIndicatorDao.select_FreeWorkuser(taskLength);//查询有空闲(剩余处理的数是大于所有处理的任务数量）的工作人员的评价指标值
		    if(workuserEvaluatingIndicatorBean!=null&&workuserEvaluatingIndicatorBean.size()>0)
		    {
			int peopleLength=workuserEvaluatingIndicatorBean.size();//工作人员的长度
			int CLength=taskLength*peopleLength;//最后矩阵的行数和列数
			
			//int[][]workUserRemainTaskNumber=new int[peopleLength][2];//每个工作人员还能做的剩余任务数量
			String[]WorkuserEvaluatingIndicators=new String[peopleLength]; //peopleLength个工作人员工号的存放数组
			String[]WorkuserEvaluatingIndicator=new String[CLength]; //CLength个工作人员工号的存放数组
			String[]Task=new String[CLength];  //CLength个诉求任务的taskID存放数组
		
			int[][] ModuleList=new int[taskLength][peopleLength]; //诉求任务乘以工作人员的综合指标值
			int [][]Array=new int[CLength][CLength];//构建CLength*CLength的矩阵
			
			int Count;//计算诉求任务对应每个工作人员的一个综合指标
			float E=(float) 0.01;	//任务权重的转换，转换为1
			
			for(int i=0;i<taskLength;i++){//计算taskLength行，peopleLength列的矩阵，每个诉求任务对应每个工作人员的一个综合指标值，比如{{1,2}，{3,4}}
					TaskBean taskBean = null;
					try {
						taskBean = TaskDao.task_porprotion(taskids[i]);//查询诉求任务的评价指标值
						task[i][0]=String.valueOf(taskBean.getTaskID());
						task[i][1]=taskBean.getWorkUserNumber();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					for(int j=0;j<peopleLength;j++){
						
						WorkuserEvaluatingIndicators[j]=workuserEvaluatingIndicatorBean.get(j).getWorkuserNo();
						workuserNo=workuserEvaluatingIndicatorBean.get(j).getWorkuserNo();
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
				 String as[][]=new String[CLength][2];//用一个数组记录[任务id][工作人员工号]
				 for(int i=0;i<optimal.length-1-1;i++)
				 {
					 as[i][0]=Task[optimal[i]];
					 as[i][1]=WorkuserEvaluatingIndicator[optimal[i+1]];
				 }
				 as[CLength-1][0]=Task[optimal[0]];
				 as[CLength-1][1]=WorkuserEvaluatingIndicator[optimal[CLength-1]];
				 int temp = 0;  //用来记录去重后的数组长度和给去重后的数组作为下标索引
			     String[][] NoRrepeat = new String[CLength][2];  //去重后的数组
			     for (int i = 0; i < CLength; i++) {  //遍历原始数组as[任务id][工作人员工号]    	
		             boolean isTrue = true;
		              for (int j = i + 1; j < CLength; j++) {  //将原数组的数据逐一比较 
		                   if (as[i][0] == as[j][0]&&as[i][1] == as[j][1]) {  //如果发现有重复元素，改变标记状态并结束内层循环
		                    isTrue = false;
		                    break;
		                }
		            }
		            if (isTrue) {
		            	NoRrepeat[temp][0] = as[i][0];
		            	NoRrepeat[temp][1] = as[i][1];
		                temp++;
		            }
		        	
		        }
			     String[][] taskMarchings = new String[temp][2]; //任务id匹配次数的数组
			     int arr[] = new int[taskMarchings.length];//匹配去重后任务id的存储
			     for(int i=0;i<NoRrepeat.length;i++){
			    	 if(NoRrepeat[i][0]!=null)
			    	 arr[i] = Integer.valueOf(NoRrepeat[i][0]);
				      }
			     Map<Integer, Integer> map = new HashMap<Integer, Integer>();
				    for (int i = 0; i < arr.length; i++) {
				        if (map.get(arr[i]) != null) {
				            map.put(arr[i], map.get(arr[i]) + 1);
				        } else if(arr[i]!=0){
				            map.put(arr[i], 1);
				        }
				    }
				    Set<Integer> keyset=map.keySet();
				    Iterator<Integer> it=keyset.iterator();
				    int c=0;
				    while (it.hasNext()) {
				    	Integer key=it.next();
				        Integer value=map.get(key);
				        c++;
				        taskMarchings[c-1][0]=String.valueOf(key);
				        taskMarchings[c-1][1]=String.valueOf(value);
				    }
				    
//				    String[][] workMarchings = new String[temp][2]; //工作人员工号匹配次数的数组
//				     int arr1[] = new int[workMarchings.length];//匹配去重后工作人员工号的存储
//				     for(int i=0;i<NoRrepeat.length;i++){
//				    	 if(NoRrepeat[i][0]!=null)
//				    	 arr1[i] = Integer.valueOf(NoRrepeat[i][1]);
//					      }
//				     Map<Integer, Integer> map1 = new HashMap<Integer, Integer>();
//					    for (int i = 0; i < arr1.length; i++) {
//					        if (map1.get(arr1[i]) != null) {
//					            map1.put(arr1[i], map1.get(arr1[i]) + 1);
//					        } else if(arr1[i]!=0){
//					            map1.put(arr1[i], 1);
//					        }
//					    }
//					    Set<Integer> keyset1=map1.keySet();
//					    Iterator<Integer> it1=keyset1.iterator();
//					    int cc=0;
//					    while (it1.hasNext()) {
//					    	Integer key1=it1.next();
//					        Integer value1=map1.get(key1);
//					        cc++;
//					        workMarchings[cc-1][0]=String.valueOf(key1);
//					        workMarchings[cc-1][1]=String.valueOf(value1);
//					    }
					    
					    String[][] b = new String[NoRrepeat.length][2]; //最终结果
						String[] x; //每个id对应NoRrepeat中的工号,用于存储NoRrepeat中每个id的工号
						int cq; //x的计数器
						int q = 0;
						String[][] result = new String[ModuleList.length][2];//得到重组后的taskId，worknum数组
						String[] worknum=new String[peopleLength];//工作人员工号数组
						for(int i = 0; i<c; i++){
							worknum = WorkuserEvaluatingIndicators;
							x= new  String[worknum.length];
							cq = 0;
							for(int y=0;y<task.length;y++){
								if(taskMarchings[i][0].equals(task[y][0])){
									if(Integer.valueOf(taskMarchings[i][1]) > Integer.valueOf(task[y][1])){
										//匹配次数大于num就去删除元素，然后赋值到新数组
										for (int j = 0; j<NoRrepeat.length;j++){
											//判断id是否相同，相同则取出来 
											if(NoRrepeat[j][0] != null && NoRrepeat[j][0].equals(taskMarchings[i][0])){
												x[cq] = NoRrepeat[j][1];
												cq++;
											}
										}
										//拿到x后先得到重组的taskId，worknum数组 result
										for(int k = 0; k<taskids.length; k++){
											if(taskids[k].equals(taskMarchings[i][0])){
												result = selectSort(ModuleList[k],worknum);
												break;
											}
										}
										int d = 0;
										//删除多余的元素
										for(int m =0;m<result.length;m++){
											for(int l = 0;l<x.length;l++){
												if(x[l]!=null && result[m][1]!=null && result[m][1].equals(x[l])){						
													b[q][0] = taskMarchings[i][0];
													b[q][1] = x[l];
													q++;
													d++;
													if (d>= Integer.valueOf(task[y][1])){
														//数据到要求的个数时就跳出循环
														break;
													}
												}
											}
											if (d>= Integer.valueOf(task[y][1])){
												//数据到要求的个数时就跳出循环
												break;
											}
										}
									}else{
										//否则就把元素取出来赋值到新数组.
										for(int n = 0; n<NoRrepeat.length; n++){
											//判断id是否相同，相同赋值
											if(NoRrepeat[n][1]!=null && NoRrepeat[n][0].equals(taskMarchings[i][0])){
												System.out.println("NoRrepeat[n][0] == "+NoRrepeat[n][0]);
												System.out.println("NoRrepeat[n][1] == "+NoRrepeat[n][1]);
			//									x[cq] = NoRrepeat[n][1];
												b[q][0] = taskMarchings[i][0];
												b[q][1] = NoRrepeat[n][1];
												q++;
											}
										}
									}
								}
							}
							
						}
						boolean addflag=false;
						 for(int i=0;i<b.length;i++){//增加匹配信息
				        	   if(b[i][0]!=null){
				        		   try {
									addflag=MarchingDao.add_marching(b[i][1], adminID, b[i][0], marchingTime);
								} catch (SQLException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}  
				        	   }
				           }
						 boolean updateflag=false;
						 for(int i=0;i<peopleLength;i++){//匹配消息后工作人员的信息修改
							 try {
								 updateflag=UserDao.update_workuserTaskNumber(WorkuserEvaluatingIndicators[i]);
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						 }
						 boolean updateTaskflag=false;
						 for(int i=0;i<taskLength;i++){//每个task被匹配后要修改的task状态
							 try {
								updateTaskflag= TaskDao.update_taskMarchingStatus(Integer.valueOf(taskids[i]), marchingStatus);
							} catch (NumberFormatException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						 }
						 if(addflag&&updateflag&&updateTaskflag){
							 	message.setCode(200);
						        message.setMessage("匹配工作人员和诉求任务信息成功");
						    	message.setData(null);
						        out.print(JSON.toJSONString(message)); 
						 }
						 if(!(addflag&&updateflag&&updateTaskflag)){
							 	message.setCode(-11);
						        message.setMessage("匹配工作人员和诉求任务信息失败");
						    	message.setData(null);
						        out.print(JSON.toJSONString(message)); 
						 }
	
					    
	
	            
				//计算CLength*CLength的矩阵，比如：{{1,2，1，2}，{3,4,3,4}，{1,2,1,2}，{3,4,3,4}}
				 System.out.println();
				 System.out.print("整合后的CLength行，CLength列的矩阵：(行代表诉求任务，列代表工作人员)(Array)");
				 for(int i=0;i<CLength;i++){
					 System.out.println();
			    	  for(int j = 0;j<CLength;j++){
			    		  System.out.print(Array[i][j]+"  ");
			    	  }
			      }
			     //调用psa获取到的匹配结果一维数组
				 System.out.println();
				 System.out.println("调用psa获取到的匹配结果：(optimal)");
				 for(int i=0;i<CLength;i++){
			    		System.out.print(optimal[i]+"  ");
			    	  }
			      
				 //显示长度为CLength的一维数组，对应的是诉求任务的taskID
				 System.out.println();
				 System.out.println("整合后诉求任务的taskID:(对应矩阵的行)(Task)");
				 for(int i=0;i<CLength;i++){
					 System.out.print(Task[i]+"  ");
				 } 
				 
				 //显示长度为CLength的一位数组，对应的是工作人员的工号
				 System.out.println();
				 System.out.println("整合后的工作人员的工号:(对应矩阵的列)(WorkuserEvaluatingIndicator,String[Clength])");
				 for(int i=0;i<CLength;i++){
					 System.out.print(WorkuserEvaluatingIndicator[i]+"  ");
				 }
				 
				 //显示通过psa匹配后的结果
				 System.out.println();
			     System.out.print("匹配后的数组[任务id][工作人员工号]：(as)");
			     for(int i=0;i<CLength;i++){
			    	    System.out.println("");
			        	for(int j=0;j<2;j++){
			        	System.out.print(as[i][j]+" ");
			        	}
			        }
			      
				
			    	     
			     //显示长度为peopleLength的一位数组，对应的是工作人员的还能处理的任务数和工作号
				 System.out.println("诉求任务所需要的工作人员数量,String[peopleLength][2])");
				 for(int i=0;i<taskLength;i++){
					 System.out.println("taskid："+task[i][0]+"  需要的工作人员数量："+task[i][1]);				 
				 }
				
				 //显示诉求任务id被匹配的次数
				 System.out.println();
				 System.out.println("诉求任务被匹配的次数：(taskMarchings,长度为c,String[temp][2])");
			     for(int i=0;i<c;i++){
			    	 System.out.println("诉求任务id："+taskMarchings[i][0]+"  匹配次数："+taskMarchings[i][1]);
			     }
			     //显示去掉重复后的数组[任务id][工作人员工号]  
			     System.out.println();
			     System.out.println("匹配后去掉重复的数组[任务id][工作人员工号](NoRrepeat,长度为temp)：");
			     for(int i=0;i<temp;i++){   	
			        	System.out.println(NoRrepeat[i][0]+"   "+NoRrepeat[i][1]);
			        	
			        }
			     //显示诉求任务id被匹配的次数
			 
	//			 System.out.println("工作人员工号被匹配的次数：(workMarchings,长度为cc,String[temp][2])");
	//		     for(int i=0;i<cc;i++){
	//		    	 System.out.println("工作人员工号："+workMarchings[i][0]+"  匹配次数："+workMarchings[i][1]);
	//		     }
	//		     
			   //显示长度为peopleLength的一位数组，对应的是工作人员的工号
				 System.out.println();
				 System.out.println("原始的工作人员的工号(WorkuserEvaluatingIndicators,String[peopleLength]):");
				 for(int i=0;i<peopleLength;i++){
					 System.out.print( WorkuserEvaluatingIndicators[i]+"  ");
				 }
				 
				 //显示长度为peopleLength的一位数组，对应的是诉求任务的taskID
				 System.out.println();
				 System.out.println("原始的诉求任务的taskID(taskids):");
				 for(int i=0;i<taskLength;i++){
					 System.out.print( taskids[i]+"  ");
				 }
				 //行代表诉求任务，列代表工作人员，显示生成的taskLength行，peopleLength列的矩阵
				    System.out.println();
				    System.out.print("原始taskLength行，peopleLength列的矩阵：(行代表诉求任务，列代表工作人员)(ModuleList)");
					for(int i=0;i<taskLength;i++){
						System.out.println();
						for(int j=0;j<peopleLength;j++){
							System.out.print(ModuleList[i][j]+"  ");
						}
					}
				    System.out.println("最终的匹配结果：");
			           for(int i=0;i<b.length;i++){
			        	   if(b[i][0]!=null){
			        		   System.out.println(b[i][0]+"----------"+b[i][1]);  
			        	   }
						
			           }
		    
		    }
		    else{
		       	message.setCode(-11);
		        message.setMessage("请重新选择小于"+taskLength+"件的诉求任务，否则无空闲的用户进行匹配！");
		    	message.setData(null);
		        out.print(JSON.toJSONString(message));
		    }
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			message.setCode(-11);
	        message.setMessage("匹配工作人员和诉求任务信息失败");
	    	message.setData(null);
	        out.print(JSON.toJSONString(message)); 
			e1.printStackTrace();
		} 
			 
				

	}
	 public static String[][] selectSort(int arry[], String arry2[]) {
         //定义重组后的二维数组，从大到小排序
					String[][] result = new String[arry.length][2];
					//System.out.println("arry.length-----=="+arry.length);
					for (int i = 0; i < arry.length - 1; i++) {
						//System.out.println("arry"+i+"-----=="+arry[i]);
						for (int j = i + 1; j < arry.length; j++) {
							if (Integer.valueOf(arry[i]) < Integer.valueOf(arry[j])) {
								int temp = arry[i];
								String temp2 = arry2[i];
								arry[i] = arry[j];
								arry[j] = temp;  //交换行的数据大小，从大到小排序

								arry2[i] = arry2[j];
								arry2[j] = temp2;

							}

						}
					}

					for (int k = 0; k < arry.length; k++) {
						result[k][0] = String.valueOf(arry[k]);
						result[k][1] = arry2[k];
					}
					return result;
				}
	     
	     
}
	
