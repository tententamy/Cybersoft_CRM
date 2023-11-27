package crm_project_02.service;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import crm_project_02.entity.Job;
import crm_project_02.repository.JobRepository;
import crm_project_02.repository.Job_Status_UsersRepository;
import crm_project_02.repository.Project_UsersRepository;

public class JobService {
	
	private JobRepository jobR = new JobRepository();
	private Job_Status_UsersRepository job_status_usersR = new Job_Status_UsersRepository(); 
	private Project_UsersRepository project_usersR = new Project_UsersRepository(); 
	
	public List<Job> getAllJob() throws SQLException {
		return jobR.showJob();
	}
	
	public boolean checkJob(String jobName, int id_user, int id_project){
		int count = jobR.checkJobIsExist(jobName, id_user, id_project);
		return count>0;
	}
	
	public boolean createNewJob(int id_project, String name,int id_user,int id_job, Date startDate, Date endDate) throws SQLException {
		
		int count1 = jobR.insert(name, startDate, endDate, id_project);
		id_job = jobR.getNewId();
		int count2 = job_status_usersR.insert(id_user, id_job);
		int count3 = project_usersR.insert(id_user, id_project, startDate);
		
		return count3>0&& count2>0 &&count1>0;
	}
	
	public boolean deleteJob(int id_job) {
		int count1 = job_status_usersR.deleteJob_Status_UserById(id_job);
		int count2 = jobR.deleteJobById(id_job);
		return count1>0 && count2>0;
	}
	
	public Job searchJob(int id_job) {
		return jobR.searchById(id_job);
	}
	
	public boolean updateJob(int id_job, int id_status) {
		int count = job_status_usersR.updateJob_Status_UserById(id_job, id_status);
		return count>0;
	}
}
