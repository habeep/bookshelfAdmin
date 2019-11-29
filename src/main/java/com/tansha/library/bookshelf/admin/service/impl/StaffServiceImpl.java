package com.tansha.library.bookshelf.admin.service.impl;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.tansha.library.bookshelf.admin.model.Staff;
import com.tansha.library.bookshelf.admin.repository.StaffRepository;

import com.tansha.library.bookshelf.admin.service.StaffService;


@Service
public class StaffServiceImpl implements StaffService {
	@Autowired
	private StaffRepository staffRepository;
	 private BCryptPasswordEncoder bCryptPasswordEncoder;
	 @Autowired
	    public StaffServiceImpl(StaffRepository staffRepository,
	                       
	                       BCryptPasswordEncoder bCryptPasswordEncoder) {
	        this.staffRepository = staffRepository;
	        
	        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	    }
	 
	@Override
	public Staff getStaffById(int staffId) {
		Staff obj = staffRepository.findById(staffId).get();
		return obj;
	}
	
	
	
	@Override
	public List<Staff> getAllStaffs(){
		
		List<Staff> list = new ArrayList<>();
		staffRepository.findAll().forEach(e -> list.add(e));
		return list;
	}
	@Override
	public synchronized boolean addStaff(Staff staff){

	        Staff list = staffRepository.findByEmailId(staff.getEmailId()); 	
                if (list != null ) {
    	           return false;
                } else {
                	staff.setPassword(bCryptPasswordEncoder.encode(staff.getPassword()));
                	staff.setIsActive(1);	
    	        staffRepository.save(staff);
    	        return true;
       }
	}
	@Override
	public void updateStaff(Staff staff) {
		staffRepository.save(staff);
	}
	
	@Override
	public void updateStaffPassword(Staff staff) {
		staff.setPassword(bCryptPasswordEncoder.encode(staff.getPassword()));
    	staff.setIsActive(1);	
		staffRepository.save(staff);
	}
	
	@Override
	public void deleteStaff(int staffId) {
		staffRepository.delete(getStaffById(staffId));
	}
}
