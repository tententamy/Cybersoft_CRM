package crm_project_02.service;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import crm_project_02.entity.Project;
import crm_project_02.repository.ProjectRepository;
import crm_project_02.repository.Project_UsersRepository;

public class ProjectService {
	ProjectRepository projectR = new ProjectRepository();
	Project_UsersRepository project_userR= new Project_UsersRepository();
	
	public List<Project> getAllProject() throws SQLException {
		return projectR.Show();
	}

	public boolean deleteProject(int id) {
//		int count1 = project_userR.deleteProjectById(id);
		int count2 = projectR.deleteProjectById(id);
		return count2>0;
	}
	
	public Project getProjectById(int id_project) {
		return projectR.search(id_project);
	}
	
	public boolean addProject(String name, Date dateStart, Date dateEnd) {
		int count = projectR.insert(name, dateStart, dateEnd);
		return count>0;
	}
	
	public boolean updateProject(String name, Date dateStart, Date dateEnd, int id_project) {
		int count = projectR.update(name, dateStart, dateEnd, id_project);
		return count>0;
	}
}
