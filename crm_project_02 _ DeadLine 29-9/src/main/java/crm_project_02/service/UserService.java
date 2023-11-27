package crm_project_02.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import crm_project_02.entity.Role;
import crm_project_02.entity.User;
import crm_project_02.repository.RoleRepository;
import crm_project_02.repository.UserRepository;
// strategy pattern
public class UserService {
	
	private UserRepository userR = new UserRepository();
	private RoleRepository roleR = new RoleRepository();
	
	public boolean addUser(String fullName, String password, String email, String phone, int id_role) {
		int count = userR.insert(fullName, password, email, phone, id_role);
		
		return count>0;
		
	}
	
	public List<User> getAllUsers() throws SQLException{
		return userR.userList();
		
	}
	
	public List<Role> getRole() throws SQLException {
		return roleR.findAll();
	}
	
	public boolean deleteUser(int id) {
		int count = userR.deleteById(id);
		return count>0;
	}
	
	public User getUserById(int id_user) {
		return userR.searchById(id_user);
	}
	
	public boolean updateUser(String fullName, String password, String email, String phone, int id_role, int id_user) {
		int count = userR.update(fullName, password, email, phone, id_role, id_user);
		return count>0;
	}
}
