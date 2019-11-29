package com.tansha.library.bookshelf.admin.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.tansha.library.bookshelf.admin.model.Area;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
//import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
//import org.springframework.data;
//import org.springframework.boot.autoconfigur
@Transactional
public interface AreaRepository extends CrudRepository<Area,String> {

	List<Area> findByArea(String area);
	Area findByAreaID(int areaID);
	List<Area> findByAreaOrPincode(String area,int pinCode);
	Area findByPincodeAndIsActive(int pincode,int isActive);
	Area findByAreaAndPincode(String area,int pincode);
	//List<Area> findByAreaAndPincode(String area,int pinCode);
	
	@Query("SELECT count(a) from Area a where pincode = ?1 AND areaID != ?2")
	int findByCustomQuery(int pincode,int areaId);
	
	
	 
}
