package crm_project_02.service;

import java.sql.SQLException;
import java.util.List;

import crm_project_02.entity.Role;
import crm_project_02.repository.RoleRepository;

//Service: chứa những class chuyên đi xử lý logic vho controller
//Cách đặt tên : Giống với controller: Ví dụ : RoleController => RoleService

//Đặt tên hàm: Đặt tên hàm ứng với lại chức năng sẽ làm trên giao diện/bên controller
public class RoleService {
	private RoleRepository roleR = new RoleRepository();
	
	public boolean addRole(String name, String desc) {
		int count = roleR.insert(name, desc);
		return count>0;
	}
	
	public List<Role> list() throws SQLException{
		return roleR.findAll();
	}
	
	public boolean deleteRole(int id) {
		int count = roleR.deleteRoleById(id);
		return count>0;
	}
	
	public Role getRoleById(int id_role) {
		return roleR.searchById(id_role);
	}
	
	public boolean updateRole(String name, String desc, int id_role) {
		int count =  roleR.update(name, desc, id_role);
		return count >0;
	}
	
	
}
