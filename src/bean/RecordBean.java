package bean;

public class RecordBean {
	private int recordID;
	private int taskID;
    private String nextVisitTime;
    private String recordContent;
    private String recordTime;
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
