package crm_project_02.entity;

import java.sql.Date;

public class Job {
	private int id;
	private String jobName;
	private Project project;
	private User user;
	private Date startDate;
	private Date endDate;
	private Status status;
	
	
	public Job() {
		// TODO Auto-generated constructor stub
	}


	public Job(String jobName, Project project, User user, Date startDate, Date endDate, Status status) {
		super();
		this.jobName = jobName;
		this.project = project;
		this.user = user;
		this.startDate = startDate;
		this.endDate = endDate;
		this.status = status;
	}


	public String getJobName() {
		return jobName;
	}


	public void setJobName(String jobName) {
		this.jobName = jobName;
	}


	public Project getProject() {
		return project;
	}


	public void setProject(Project project) {
		this.project = project;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public Date getStartDate() {
		return startDate;
	}


	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}


	public Date getEndDate() {
		return endDate;
	}


	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}


	public Status getStatus() {
		return status;
	}


	public void setStatus(Status status) {
		this.status = status;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}
	
	
	
	
}
