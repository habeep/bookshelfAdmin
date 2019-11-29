package com.tansha.library.bookshelf.admin.service;


import com.tansha.library.bookshelf.admin.model.User;
import com.tansha.library.bookshelf.admin.repository.UserRepository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.stereotype.Service;

@Service("userService")
public class UserService {

    private UserRepository userRepository;
    
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserService(UserRepository userRepository,
                       
                       BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public User findUserByEmail(String email) {
        return userRepository.findByEmailId(email);
    }
    
    /* public String getPassword(String email) {
         User usr = userRepository.findByEmailId(email);
         return bCryptPasswordDecoder(usr.getPassword()
    } */
    
    
    
	public Optional findUserByResetToken(String resetToken) {
		return userRepository.findByResetToken(resetToken);
	}


    public User saveUser(User user) {
    	
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setIsActive(1);
        //Role userRole = roleRepository.findByRole("ADMIN");
       // user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        return userRepository.save(user);
    }
    
    public User updateUser(User user) {
    	
        user.setIsActive(1);
        //Role userRole = roleRepository.findByRole("ADMIN");
       // user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        return userRepository.save(user);
    }

}