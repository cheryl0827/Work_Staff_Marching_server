package bean;

public class RecordBean {
	private int recordID;
	private int taskID;
    private String nextVisitTime;
    private String recordContent;
    private String recordTime;
    private String workuserNo;
    private String userName;
	public String getWorkuserNo() {
		return workuserNo;
	}
	public void setWorkuserNo(String workuserNo) {
		this.workuserNo = workuserNo;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getRecordID() {
		return recordID;
	}
	public void setRecordID(int recordID) {
		this.recordID = recordID;
	}
	public int getTaskID() {
		return taskID;
	}
	public void setTaskID(int taskID) {
		this.taskID = taskID;
	}
	public String getNextVisitTime() {
		return nextVisitTime;
	}
	public void setNextVisitTime(String nextVisitTime) {
		this.nextVisitTime = nextVisitTime;
	}
	public String getRecordContent() {
		return recordContent;
	}
	public void setRecordContent(String recordContent) {
		this.recordContent = recordContent;
	}
	public String getRecordTime() {
		return recordTime;
	}
	public void setRecordTime(String recordTime) {
		this.recordTime = recordTime;
	}
    
    

}
