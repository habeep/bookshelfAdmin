package com.tansha.library.bookshelf.admin.service;

import java.util.List;

import com.tansha.library.bookshelf.admin.model.Staff;

public interface StaffService {
	List<Staff> getAllStaffs();
	//List<Staff> getAllStaffDetails();
	Staff getStaffById(int staffId);
    boolean addStaff(Staff staff);
    void updateStaff(Staff staff);
    void updateStaffPassword(Staff staff);
    void deleteStaff(int staffId);
}
