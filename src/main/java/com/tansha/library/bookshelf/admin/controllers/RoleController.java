package com.tansha.library.bookshelf.admin.controllers;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tansha.library.bookshelf.admin.model.Role;
import com.tansha.library.bookshelf.admin.service.RoleService;

@Controller
@RequestMapping("rest")
@CrossOrigin(origins = "http://localhost:3000")
public class RoleController {
	
	@Autowired
	private RoleService roleService;
	@GetMapping("role/{id}")
	public ResponseEntity<Role> getRoleById(@PathVariable("id") Integer id) {
		Role role = roleService.getRoleById(id);
		return new ResponseEntity<Role>(role, HttpStatus.OK);
	}
	@GetMapping("roles")
	public ResponseEntity<List<Role>> getAllRoles() {
		
		List<Role> list = roleService.getAllRoles();
		return new ResponseEntity<List<Role>>(list, HttpStatus.OK);
	}
	@PostMapping("role")
	public ResponseEntity<Void> addRole(@RequestBody Role role) {
		
                boolean flag = roleService.addRole(role);
                if (flag == false) {
        	   return new ResponseEntity<Void>(HttpStatus.CONFLICT);
                }
                HttpHeaders headers = new HttpHeaders();
               // headers.setLocation(builder.path("/role/{id}").buildAndExpand(role.getId()).toUri());
                return new ResponseEntity("Role created successfully", HttpStatus.CREATED);
	}
	@PutMapping("role")
	public ResponseEntity<Role> updateRole(@RequestBody Role role) {
		roleService.updateRole(role);
		return new ResponseEntity<Role>(role, HttpStatus.OK);
	}
	@DeleteMapping("role/{id}")
	public ResponseEntity<Void> deleteRole(@PathVariable("id") Integer id) {
		roleService.deleteRole(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

}
