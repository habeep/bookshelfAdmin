package com.tansha.library.bookshelf.admin.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;

import com.tansha.library.bookshelf.admin.model.Role;


import org.springframework.data.repository.CrudRepository;
//import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
//import org.springframework.data;
//import org.springframework.boot.autoconfigur
@Transactional
public interface RoleRepository extends CrudRepository<Role,String> {

	Optional<Role> findByName(String name);
	List<Role> findById(int id);
	List<Role> findByIsActive(int isActive);
	
	 
}
