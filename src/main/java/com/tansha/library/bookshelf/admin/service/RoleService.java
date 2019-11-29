package com.tansha.library.bookshelf.admin.service;

import java.util.List;

import com.tansha.library.bookshelf.admin.model.Role;

public interface RoleService {
	List<Role> getAllRoles();
	Role getRoleById(int roleId);
    boolean addRole(Role role);
    void updateRole(Role role);
    void deleteRole(int roleId);
}
