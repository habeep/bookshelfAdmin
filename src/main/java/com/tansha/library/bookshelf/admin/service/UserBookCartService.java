package com.tansha.library.bookshelf.admin.service;

import com.tansha.library.bookshelf.admin.exceptions.Err;
import com.tansha.library.bookshelf.admin.model.Book;
import com.tansha.library.bookshelf.admin.model.UserBookCart;

import java.util.List;

public interface UserBookCartService {

    /**
     * Adds the given book against the user to card
     *
     * @param ubc UserBookCart
     * @return true if add successful, false if failed
     */
    public Err addUserBookToCart(UserBookCart ubc);

    /**
     * To get the cart for a user
     *
     * @param userid int userid
     * @param isTypeReturn
     * @return List of UserBookCart
     */
    public List<UserBookCart> getUserCart(int userid, boolean isTypeReturn);

    /**
     * Clears the books for user from cart
     *
     * @param userid
     * @param isTypeReturn
     */
    void clearUserCart(int userid, boolean isTypeReturn);
    
    /**
     *  Clears the books for user from cart
    *
    * @param userid
    * @param isTypeReturn
    */
   void deleteUserBookFromCart(Integer userid,Integer bookId, boolean isTypeReturn);

   /**
    *  Clears the books for user from cart
   *
   * @param userid
   * @param isTypeReturn
   */
  void deleteUserBookFromCart(Integer userid,Integer bookId);
		  
    /**
     * @param userId
     * @param isTypeReturn
     * @return
     */
    List<Object[]> getUserBooks(int userId, boolean isTypeReturn);
}
