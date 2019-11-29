package com.tansha.library.bookshelf.admin.repository;

import java.util.Optional;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tansha.library.bookshelf.admin.model.User;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmailId(String emailId);
    User findById(int userId);
    
    public Optional<User> findByResetToken(String resetToken);
    
   @Query("SELECT user.name,user.pincode,user.emailId,user.phoneNumber,user.houseNumber,user.street,user.locality,user.city FROM User user WHERE user.pincode = ?1")
   List<Object[]> getUserBasedOnPincode(int areaId);
   
//   @Query("SELECT * FROM User.id WHERE * NOT IN (SELECT * FROM BookBorrow.userID)")
//   List<Object[]> getOnlyRegisterUser();
}  