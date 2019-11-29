package com.tansha.library.bookshelf.admin.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;

import com.tansha.library.bookshelf.admin.model.Staff;
import com.tansha.library.bookshelf.admin.model.Role;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
//import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
//import org.springframework.data;
//import org.springframework.boot.autoconfigur
@Transactional
public interface StaffRepository extends CrudRepository<Staff,String> {

	Optional<Staff> findByStaffName(String staffName);
	Optional<Staff> findById(long id);
	//List<Staff> findByUsername(String Username);
	
	List<Staff> findByIsActive(int isActiveFlag);
	Staff findByEmailId(String emailId);
	//Optional<Staff> findByUsernameOrEmailId(String Username,String emailId);
	
	//Boolean existsByUsername(String username);
 
    Boolean existsByEmailId(String emailId);
    
    @Query("SELECT  s.id,s.staffName,s.emailId,s.mobileNumber,r.name,s.isActive from Staff s INNER JOIN Role r ON r.id= s.roleId")
	List<Object[]> getAllStaffDetails();
    
   
	
}
