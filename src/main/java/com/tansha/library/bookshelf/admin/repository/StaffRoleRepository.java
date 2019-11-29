package com.tansha.library.bookshelf.admin.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.tansha.library.bookshelf.admin.model.StaffRole;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
//import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
//import org.springframework.data;
//import org.springframework.boot.autoconfigur
@Transactional
public interface StaffRoleRepository extends CrudRepository<StaffRole,String> {

	//StaffRole findByRole_id(int role_id);
	//StaffRole findByStaff_id(int staffId);
	
	
	
	 
}
