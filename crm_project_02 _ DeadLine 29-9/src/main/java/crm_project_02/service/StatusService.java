package crm_project_02.service;

import java.util.List;

import crm_project_02.entity.Status;
import crm_project_02.repository.StatusRepository;

public class StatusService {
	private StatusRepository statusR = new StatusRepository();
	
	public List<Status> getAllStatus(){
		return statusR.listStatus();
	}
}
