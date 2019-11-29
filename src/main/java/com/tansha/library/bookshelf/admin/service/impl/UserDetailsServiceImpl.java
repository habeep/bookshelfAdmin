package com.tansha.library.bookshelf.admin.service.impl;

import com.tansha.library.bookshelf.admin.model.Role;
import com.tansha.library.bookshelf.admin.model.Staff;
import com.tansha.library.bookshelf.admin.repository.RoleRepository;
import com.tansha.library.bookshelf.admin.repository.StaffRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
    @Autowired
    private StaffRepository staffRepository;
    
    @Autowired
    private RoleRepository roleRepository;
    
    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String emailId) throws UsernameNotFoundException {
        Staff staff = staffRepository.findByEmailId(emailId);
        //user.setNumberofChildrens(0);

	
	List<Role> roles =  roleRepository.findById(staff.getRoleId());
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        //grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        for (Role role : roles){
        	
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
        } 

        return new org.springframework.security.core.userdetails.User(staff.getEmailId(), staff.getPassword(), grantedAuthorities);
        //return new org.springframework.security.core.userdetails.User(user.getEmailId(), user.getPassword(),null);
    }
}
