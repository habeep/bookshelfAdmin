package com.tansha.library.bookshelf.admin.service.impl;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tansha.library.bookshelf.admin.model.Role;
import com.tansha.library.bookshelf.admin.repository.RoleRepository;
import com.tansha.library.bookshelf.admin.service.RoleService;

@Service
public class RolesServiceImpl implements RoleService {
	@Autowired
	private RoleRepository roleRepository;
	@Override
	public Role getRoleById(int roleId) {
		Role obj = roleRepository.findById(roleId).get(0);
		return obj;
	}	
	@Override
	public List<Role> getAllRoles(){
		
		List<Role> list = new ArrayList<>();
		roleRepository.findAll().forEach(e -> list.add(e));
		return list;
	}
	@Override
	public synchronized boolean addRole(Role role){

	        Optional<Role> list = roleRepository.findByName(role.getName()); 	
                if (list.isPresent()) {
    	           return false;
                } else {
    	        roleRepository.save(role);
    	        return true;
       }
	}
	@Override
	public void updateRole(Role role) {
		roleRepository.save(role);
	}
	@Override
	public void deleteRole(int roleId) {
		roleRepository.delete(getRoleById(roleId));
	}
}
