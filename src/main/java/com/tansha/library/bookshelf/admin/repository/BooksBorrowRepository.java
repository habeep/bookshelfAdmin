package com.tansha.library.bookshelf.admin.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;

import com.tansha.library.bookshelf.admin.model.Staff;
import com.tansha.library.bookshelf.admin.model.BookBorrow;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

//import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
//import org.springframework.data;
//import org.springframework.boot.autoconfigur
@Transactional
public interface BooksBorrowRepository extends CrudRepository<BookBorrow,String> {

	List<BookBorrow> findByUserID(int userId);
	List<BookBorrow> findByBookID(int bookId);
	List<BookBorrow> findBySlotID(int slotId);
	List<BookBorrow> findByUserIDAndBookID(int userId,int bookId);
	BookBorrow findByBorrowID(int BorrowID);
	
	/*@Modifying
	@Transactional
	@Query( "UPDATE BookBorrow bookborrow SET bookborrow.bookReturnStatus=1,bookborrow.actualDateofReturned=CURRENT_DATE() WHERE bookborrow.bookID=?1 AND bookborrow.userID=?2")
	public void updateUserBookReturnStatus(int userId, int bookId);
	*/
	
	@Query("SELECT bookBorrow from BookBorrow bookBorrow "
			+ " WHERE    bookBorrow.userID=?1 AND bookBorrow.bookReturnStatus = 0 AND bookBorrow.bookID=?2 ")
	BookBorrow getBookBorrowsForUser(int userId,int bookId);
	
	@Query("SELECT bookBorrow from BookBorrow bookBorrow "
			+ " WHERE    bookBorrow.userID=?1 AND bookBorrow.bookReturnStatus = ?2 AND MONTH(bookBorrow.requestedON) != MONTH(CURRENT_DATE()) ")
	List<BookBorrow> getActiveBookBorrowsForUser(int userId,int returnFlag);
	
	@Query("SELECT bookBorrow from BookBorrow bookBorrow "
			+ " WHERE    bookBorrow.userID=?1 AND bookBorrow.bookReturnStatus = 1 ")
	List<BookBorrow> getHistoryofBorrowsBooksListForUser(int userId);
	
	@Query("SELECT bookBorrow from BookBorrow bookBorrow "
			+ " WHERE    bookBorrow.userID=?1 AND MONTH(bookBorrow.requestedON) = MONTH(CURRENT_DATE()) " + 
			" AND YEAR(bookBorrow.requestedON) = YEAR(CURRENT_DATE())")
	List<BookBorrow> getCurrentMonthBookBorrowsListForUser(int userId);
	
	@Modifying
	@Transactional
	@Query(value="UPDATE tbl_borrow_master bookBorrow SET bookBorrow.returnRequestedON = NULL,bookBorrow.bookReturnStatus=0,bookBorrow.type_return=0,bookBorrow.bookUnDeliveryStatus=1,bookBorrow.unDeliveryReason = ?1 WHERE bookBorrow.borrowID = ?2 ", nativeQuery = true)
	void updatePickupUnDeliveredStatus(String undeliveryReason,int borrowId);
	
//	@Query("SELECT bookBorrow.orderId,user.emailId,bookBorrow.bookID,user.phoneNumber,user.houseNumber,user.street,user.locality,user.city,user.pincode,user.district,user.landmark,bookBorrow.type_return,bookBorrow.bookReturnStatus,bookBorrow.borrowID,bookBorrow.deliveredOn,bookBorrow.actualDateofReturned,user.name,book.bookTitle from BookBorrow bookBorrow "
//			+ "INNER JOIN User user ON user.id = bookBorrow.userID "
//			+ "INNER JOIN Book book ON book.bookId = bookBorrow.bookID "
//			+ " INNER JOIN  DeliverySlot ds ON ds.slotID = bookBorrow.slotID "
//			+ " WHERE bookBorrow.staffID = ?1 ")
//	List<Object[]> getMyAllocatedDeliveriesList(long staffId); bookBorrow.returnRequestedON = NULL
	
	@Query("SELECT bookBorrow.orderId,user.emailId,bookBorrow.bookID,user.phoneNumber,user.houseNumber,user.street,user.locality,bookBorrow.deliveredOn,bookBorrow.bookReturnStatus,bookBorrow.borrowID,bookBorrow.actualDateofReturned,user.name,book.bookTitle,bookBorrow.requestedON,ds.dateOfdelivery from BookBorrow bookBorrow "
			+ "INNER JOIN User user ON user.id = bookBorrow.userID "
			+ "INNER JOIN Book book ON book.bookId = bookBorrow.bookID "
			+ " INNER JOIN  DeliverySlot ds ON ds.slotID = bookBorrow.slotID "
			+ " WHERE bookBorrow.staffID = ?1 AND DATE(bookBorrow.createdAt) = NOW() ")
	List<Object[]> getStaffTodayReport(long staffId);
//	book id, name, delivery slot, employee id, delivery address, contact number
	@Query("SELECT bookBorrow.orderId,user.emailId,bookBorrow.bookID,user.phoneNumber,user.houseNumber,user.street,user.locality,bookBorrow.deliveredOn,bookBorrow.bookReturnStatus,bookBorrow.borrowID,bookBorrow.actualDateofReturned,user.name,book.bookTitle,bookBorrow.requestedON,ds.dateOfdelivery from BookBorrow bookBorrow "
			+ "INNER JOIN User user ON user.id = bookBorrow.userID "
			+ "INNER JOIN Book book ON book.bookId = bookBorrow.bookID "
			+ " INNER JOIN  DeliverySlot ds ON ds.slotID = bookBorrow.slotID "
			+ " WHERE DATE(bookBorrow.createdAt) <= (NOW() - 7) AND bookBorrow.staffID = ?1")
	List<Object[]> getStaffWeekReport(long staffId);
	
	@Query("SELECT bookBorrow.bookID,book.bookTitle,ds.slotID,staff.id,user.phoneNumber,user.houseNumber,user.street,user.locality,user.city,user.pincode,user.district,user.landmark,bookBorrow.type_return,bookBorrow.bookReturnStatus,bookBorrow.borrowID,bookBorrow.deliveredOn,bookBorrow.requestedON from BookBorrow bookBorrow "
			+ "INNER JOIN User user ON user.id = bookBorrow.userID "
			+ "INNER JOIN Staff staff ON staff.id = bookBorrow.staffID "
			+ "INNER JOIN Book book ON book.bookId = bookBorrow.bookID "
			+ " INNER JOIN  DeliverySlot ds ON ds.slotID = bookBorrow.slotID "
			+ " WHERE DATE(bookBorrow.requestedON) = CURRENT_DATE()")
	List<Object[]> getDeliveryToday();
	
	@Query("SELECT bookBorrow.bookID,book.bookTitle,ds.dateOfdelivery,staff.id,user.phoneNumber,user.houseNumber,user.street,user.locality,user.city,user.pincode,user.district,user.landmark,bookBorrow.type_return,bookBorrow.bookReturnStatus,bookBorrow.borrowID,bookBorrow.deliveredOn,bookBorrow.requestedON,bookBorrow.actualDateofReturned from BookBorrow bookBorrow "
			+ "INNER JOIN User user ON user.id = bookBorrow.userID "
			+ "INNER JOIN Staff staff ON staff.id = bookBorrow.staffID "
			+ "INNER JOIN Book book ON book.bookId = bookBorrow.bookID "
			+ " INNER JOIN  DeliverySlot ds ON ds.slotID = bookBorrow.slotID "
			+ " WHERE DATE(bookBorrow.returnRequestedON) =  CURRENT_DATE()")
	List<Object[]> getPickupToday();
	
	@Query("SELECT bookBorrow.bookID,book.bookTitle,ds.dateOfdelivery,staff.id,user.phoneNumber,user.houseNumber,user.street,user.locality,user.city,user.pincode,user.district,user.landmark,bookBorrow.type_return,bookBorrow.bookReturnStatus,bookBorrow.borrowID,bookBorrow.deliveredOn,bookBorrow.requestedON from BookBorrow bookBorrow "
			+ "INNER JOIN User user ON user.id = bookBorrow.userID "
			+ "INNER JOIN Staff staff ON staff.id = bookBorrow.staffID "
			+ "INNER JOIN Book book ON book.bookId = bookBorrow.bookID "
			+ " INNER JOIN  DeliverySlot ds ON ds.slotID = bookBorrow.slotID "
			+ " WHERE DATE(bookBorrow.requestedON) > CURRENT_DATE()ORDER BY bookBorrow.requestedON ASC")
	List<Object[]> getDeliveryNextDay();
	
	@Query("SELECT bookBorrow.bookID,book.bookTitle,ds.dateOfdelivery,staff.id,user.phoneNumber,user.houseNumber,user.street,user.locality,user.city,user.pincode,user.district,user.landmark,bookBorrow.type_return,bookBorrow.bookReturnStatus,bookBorrow.borrowID,bookBorrow.deliveredOn,bookBorrow.requestedON from BookBorrow bookBorrow "
			+ "INNER JOIN User user ON user.id = bookBorrow.userID "
			+ "INNER JOIN Staff staff ON staff.id = bookBorrow.staffID "
			+ "INNER JOIN Book book ON book.bookId = bookBorrow.bookID "
			+ " INNER JOIN  DeliverySlot ds ON ds.slotID = bookBorrow.slotID "
			+ " WHERE DATE(bookBorrow.returnRequestedON) > CURRENT_DATE()ORDER BY bookBorrow.actualDateofReturned ASC")
	List<Object[]> getPickupNextDay();
	
	
	@Query("SELECT bookBorrow.bookID,book.bookTitle,ds.dateOfdelivery,staff.id,user.phoneNumber,user.houseNumber,user.street,user.locality,user.city,user.pincode,user.district,user.landmark,bookBorrow.type_return,bookBorrow.bookReturnStatus,bookBorrow.borrowID,bookBorrow.deliveredOn,bookBorrow.requestedON,bookBorrow.unDeliveryReason,user.emailId from BookBorrow bookBorrow "
			+ "INNER JOIN User user ON user.id = bookBorrow.userID "
			+ "INNER JOIN Staff staff ON staff.id = bookBorrow.staffID "
			+ "INNER JOIN Book book ON book.bookId = bookBorrow.bookID "
			+ " INNER JOIN  DeliverySlot ds ON ds.slotID = bookBorrow.slotID "
			+ " WHERE bookBorrow.bookUnDeliveryStatus = 1 ORDER BY bookBorrow.actualDateofReturned ASC")
	List<Object[]> getundelivereditems();
	
	@Query("SELECT bookBorrow.orderId,user.emailId,bookBorrow.bookID,user.phoneNumber,user.houseNumber,user.street,user.locality,user.city,user.pincode,user.district,user.landmark,bookBorrow.type_return,bookBorrow.bookReturnStatus,bookBorrow.borrowID,bookBorrow.deliveredOn,bookBorrow.actualDateofReturned,user.name,book.bookTitle,bookBorrow.confirmBookReturnStatus from BookBorrow bookBorrow "
			+ "INNER JOIN User user ON user.id = bookBorrow.userID "
			+ "INNER JOIN Book book ON book.bookId = bookBorrow.bookID "
			+ " INNER JOIN  DeliverySlot ds ON ds.slotID = bookBorrow.slotID "
			+ " WHERE bookBorrow.type_return = 1 AND bookBorrow.bookReturnStatus=1 AND bookBorrow.confirmBookReturnStatus=0 AND bookBorrow.actualDateofReturned IS NOT NULL ")
	List<Object[]> confirmPickup();
	
	
	@Query("SELECT bookBorrow from BookBorrow bookBorrow group by bookBorrow.orderId ")
	List<BookBorrow> getMyAllocatedDeliveriesListGroupBy(long staffId);

	@Query("SELECT bookBorrow.orderId,user.emailId,bookBorrow.bookID,user.phoneNumber,user.houseNumber,user.street,user.locality,"
			+ "user.city,user.pincode,user.district,user.landmark,bookBorrow.type_return,"
			+ "bookBorrow.bookReturnStatus,bookBorrow.borrowID,bookBorrow.deliveredOn,bookBorrow.actualDateofReturned,user.name,"
			+ "book.bookTitle,ds.dateOfdelivery,ds.deliveryTimeFrom,ds.deliveryTimeTill,user.id,bookBorrow.returnRequestedON,bookBorrow.requestedON "
			+ "from BookBorrow bookBorrow "
			+ "INNER JOIN User user ON user.id = bookBorrow.userID "
			+ "INNER JOIN Book book ON book.bookId = bookBorrow.bookID "
			+ " INNER JOIN Publisher publisher ON book.publisherID = publisher.publisherId "
			+ " INNER JOIN  DeliverySlot ds ON ds.slotID = bookBorrow.slotID "
			+ " INNER JOIN  Area area ON area.areaID = ds.areaID "
			+ " WHERE bookBorrow.orderId = ?1 ")
	List<BookBorrow> getMyAllocatedDeliveriesListbyOrderId(long orderId);

	
	@Query("SELECT bookBorrow.orderId,user.emailId,bookBorrow.bookID,user.phoneNumber,user.houseNumber,user.street,user.locality,"
			+ "user.city,user.pincode,user.district,user.landmark,bookBorrow.type_return,"
			+ "bookBorrow.bookReturnStatus,bookBorrow.borrowID,bookBorrow.deliveredOn,bookBorrow.actualDateofReturned,user.name,"/*15*/
			+ "book.bookTitle,ds.dateOfdelivery,ds.deliveryTimeFrom,ds.deliveryTimeTill,user.id,bookBorrow.returnRequestedON,bookBorrow.requestedON,publisher.publisherName "
			+ "from BookBorrow bookBorrow "
			+ "INNER JOIN User user ON user.id = bookBorrow.userID "
			+ "INNER JOIN Book book ON book.bookId = bookBorrow.bookID "
			+ "INNER JOIN Publisher publisher ON book.publisherID = publisher.publisherId "
			+ " INNER JOIN  DeliverySlot ds ON ds.slotID = bookBorrow.slotID "
			+ " INNER JOIN  Area area ON area.areaID = ds.areaID "
			+ " WHERE bookBorrow.staffID = ?1 AND bookBorrow.orderId = ?2 AND bookBorrow.requestedON IS NOT NULL AND bookBorrow.deliveredOn IS NULL ")
	List<Object[]> getMyAllocatedDeliveryDetailsList(long staffId,long orderId);
	
	@Query("SELECT bookBorrow.orderId,user.emailId,bookBorrow.bookID,user.phoneNumber,user.houseNumber,user.street,user.locality,"
			+ "user.city,user.pincode,user.district,user.landmark,bookBorrow.type_return,"
			+ "bookBorrow.bookReturnStatus,bookBorrow.borrowID,bookBorrow.deliveredOn,bookBorrow.actualDateofReturned,user.name,"/*15*/
			+ "book.bookTitle,ds.dateOfdelivery,ds.deliveryTimeFrom,ds.deliveryTimeTill,user.id,bookBorrow.returnRequestedON,bookBorrow.requestedON,publisher.publisherName "
			+ "from BookBorrow bookBorrow "
			+ "INNER JOIN User user ON user.id = bookBorrow.userID "
			+ "INNER JOIN Book book ON book.bookId = bookBorrow.bookID "
			+ "INNER JOIN Publisher publisher ON book.publisherID = publisher.publisherId "
			+ " INNER JOIN  DeliverySlot ds ON ds.slotID = bookBorrow.slotID "
			+ " INNER JOIN  Area area ON area.areaID = ds.areaID "
			+ " WHERE bookBorrow.orderId = ?1 AND bookBorrow.requestedON IS NOT NULL AND bookBorrow.deliveredOn IS NULL ")
	List<Object[]> getOrderDeliveryDetailsListForAdmin(long orderId);
	
	
	@Query("SELECT bookBorrow.orderId,user.emailId,bookBorrow.bookID,user.phoneNumber,user.houseNumber,user.street,user.locality,"
			+ "user.city,user.pincode,user.district,user.landmark,bookBorrow.type_return,"
			+ "bookBorrow.bookReturnStatus,bookBorrow.borrowID,bookBorrow.deliveredOn,bookBorrow.actualDateofReturned,user.name,"/*15*/
			+ "book.bookTitle,ds.dateOfdelivery,ds.deliveryTimeFrom,ds.deliveryTimeTill,user.id,bookBorrow.returnRequestedON,bookBorrow.requestedON,publisher.publisherName "
			+ "from BookBorrow bookBorrow "
			+ "INNER JOIN User user ON user.id = bookBorrow.userID "
			+ "INNER JOIN Book book ON book.bookId = bookBorrow.bookID "
			+ "INNER JOIN Publisher publisher ON book.publisherID = publisher.publisherId "
			+ " INNER JOIN  DeliverySlot ds ON ds.slotID = bookBorrow.slotID "
			+ " INNER JOIN  Area area ON area.areaID = ds.areaID "
			+ " WHERE bookBorrow.staffID = ?1 AND bookBorrow.orderId = ?2 AND bookBorrow.returnRequestedON  IS NOT NULL AND bookBorrow.actualDateofReturned IS NULL AND bookBorrow.type_return = 1 ")
	List<Object[]> getMyAllocatedPickupDetailsList(long staffId,long orderId);

	
	@Query("SELECT bookBorrow.orderId,user.emailId,bookBorrow.bookID,user.phoneNumber,user.houseNumber,user.street,user.locality,"
			+ "user.city,user.pincode,user.district,user.landmark,bookBorrow.type_return,"
			+ "bookBorrow.bookReturnStatus,bookBorrow.borrowID,bookBorrow.deliveredOn,bookBorrow.actualDateofReturned,user.name,"
			+ "book.bookTitle,ds.dateOfdelivery,ds.deliveryTimeFrom,ds.deliveryTimeTill,user.id,bookBorrow.returnRequestedON,bookBorrow.requestedON "
			+ "from BookBorrow bookBorrow "
			+ "INNER JOIN User user ON user.id = bookBorrow.userID "
			+ "INNER JOIN Book book ON book.bookId = bookBorrow.bookID "
			+ " INNER JOIN  DeliverySlot ds ON ds.slotID = bookBorrow.slotID "
			+ " INNER JOIN  Area area ON area.areaID = ds.areaID "
			+ " WHERE bookBorrow.staffID = ?1 AND bookBorrow.requestedON IS NOT NULL AND bookBorrow.deliveredOn IS NULL group by bookBorrow.orderId ")
	List<Object[]> getMyAllocatedDeliveriesList(long staffId);
	
	@Query("SELECT bookBorrow.orderId,user.emailId,bookBorrow.bookID,user.phoneNumber,user.houseNumber,user.street,user.locality,"
			+ "user.city,user.pincode,user.district,user.landmark,bookBorrow.type_return,"
			+ "bookBorrow.bookReturnStatus,bookBorrow.borrowID,bookBorrow.deliveredOn,bookBorrow.actualDateofReturned,user.name,"
			+ "book.bookTitle,ds.dateOfdelivery,ds.deliveryTimeFrom,ds.deliveryTimeTill,user.id,bookBorrow.returnRequestedON,bookBorrow.requestedON,staff.emailId,publisher.publisherName "
			+ "from BookBorrow bookBorrow "
			+ " INNER JOIN  Staff staff ON staff.id = bookBorrow.staffID "			
			+ "INNER JOIN User user ON user.id = bookBorrow.userID "
			+ "INNER JOIN Book book ON book.bookId = bookBorrow.bookID "
			+ "INNER JOIN Publisher publisher ON book.publisherID = publisher.publisherId "
			+ " INNER JOIN  DeliverySlot ds ON ds.slotID = bookBorrow.slotID "
			+ " INNER JOIN  Area area ON area.areaID = ds.areaID "
			+ " WHERE bookBorrow.requestedON IS NOT NULL AND bookBorrow.deliveredOn IS NULL group by bookBorrow.orderId ")
	List<Object[]> getAdminUpcomingDeliveriesList();
	
	
	@Query("SELECT bookBorrow.orderId,user.emailId,bookBorrow.bookID,user.phoneNumber,user.houseNumber,user.street,user.locality,"
			+ "user.city,user.pincode,user.district,user.landmark,bookBorrow.type_return,"
			+ "bookBorrow.bookReturnStatus,bookBorrow.borrowID,bookBorrow.deliveredOn,bookBorrow.actualDateofReturned,user.name,"
			+ "book.bookTitle,ds.dateOfdelivery,ds.deliveryTimeFrom,ds.deliveryTimeTill,user.id,bookBorrow.returnRequestedON,bookBorrow.requestedON,publisher.publisherName "
			+ "from BookBorrow bookBorrow "
			+ "INNER JOIN User user ON user.id = bookBorrow.userID "
			+ "INNER JOIN Book book ON book.bookId = bookBorrow.bookID "
			+ "INNER JOIN Publisher publisher ON book.publisherID = publisher.publisherId "
			+ " INNER JOIN  DeliverySlot ds ON ds.slotID = bookBorrow.slotID "
			+ " INNER JOIN  Area area ON area.areaID = ds.areaID "
			+ " WHERE bookBorrow.staffID = ?1 AND bookBorrow.requestedON IS NOT NULL AND bookBorrow.deliveredOn IS NOT NULL")
	List<Object[]> getMyCompletedDeliveriesList(long staffId);
//	AND bookBorrow.confirmBookReturnStatus = 1 
	
	@Query("SELECT bookBorrow.orderId,user.emailId,bookBorrow.bookID,user.phoneNumber,user.houseNumber,user.street,user.locality,"
			+ "user.city,user.pincode,user.district,user.landmark,bookBorrow.type_return,"
			+ "bookBorrow.bookReturnStatus,bookBorrow.borrowID,bookBorrow.deliveredOn,bookBorrow.actualDateofReturned,user.name,"
			+ "book.bookTitle,ds.dateOfdelivery,ds.deliveryTimeFrom,ds.deliveryTimeTill,user.id,bookBorrow.returnRequestedON,bookBorrow.requestedON,publisher.publisherName "
			+ "from BookBorrow bookBorrow "
			+ "INNER JOIN User user ON user.id = bookBorrow.userID "
			+ "INNER JOIN Book book ON book.bookId = bookBorrow.bookID "
			+ "INNER JOIN Publisher publisher ON book.publisherID = publisher.publisherId "
			+ " INNER JOIN  DeliverySlot ds ON ds.slotID = bookBorrow.slotID "
			+ " INNER JOIN  Area area ON area.areaID = ds.areaID "
			+ " WHERE bookBorrow.staffID = ?1 AND bookBorrow.returnRequestedON  IS NOT NULL AND bookBorrow.actualDateofReturned IS NULL AND bookBorrow.type_return = 1  group by bookBorrow.orderId ")
	List<Object[]> getMyAllocatedPickupsList(long staffId);
	
	@Query("SELECT bookBorrow.orderId,user.emailId,bookBorrow.bookID,user.phoneNumber,user.houseNumber,user.street,user.locality,"
			+ "user.city,user.pincode,user.district,user.landmark,bookBorrow.type_return,"
			+ "bookBorrow.bookReturnStatus,bookBorrow.borrowID,bookBorrow.deliveredOn,bookBorrow.actualDateofReturned,user.name,"
			+ "book.bookTitle,ds.dateOfdelivery,ds.deliveryTimeFrom,ds.deliveryTimeTill,user.id,bookBorrow.returnRequestedON,bookBorrow.requestedON,publisher.publisherName "
			+ "from BookBorrow bookBorrow "
			+ "INNER JOIN User user ON user.id = bookBorrow.userID "
			+ "INNER JOIN Book book ON book.bookId = bookBorrow.bookID "
			+ "INNER JOIN Publisher publisher ON book.publisherID = publisher.publisherId "
			+ " INNER JOIN  DeliverySlot ds ON ds.slotID = bookBorrow.slotID "
			+ " INNER JOIN  Area area ON area.areaID = ds.areaID "
			+ " WHERE bookBorrow.staffID = ?1 AND bookBorrow.returnRequestedON  IS NOT NULL AND bookBorrow.actualDateofReturned IS NOT NULL AND bookBorrow.bookReturnStatus = 1 ")
	List<Object[]> getMyCompletedPickupsList(long staffId);
	
	@Query("select book.bookId,book.bookTitle, publisher.publisherName,authors.authorName,bookCategory.categoryName,languages.language,book.isbncode,book.longTitle,book.description,readingLevels.readingLevel,user.id  from Book book " + 
			"inner join  Publisher publisher " + 
			"	on book.publisherID=publisher.publisherId " + 
			"inner join Author  authors " + 
			"	ON book.authorID = authors.authorID " + 
			" inner join Language languages " + 
			"	on book.languageId = languages.languageID " +  
			" inner join BookBorrow  bookBorrow " +
			"   on bookBorrow.bookID =  book.bookId " +
			" inner join BookCategory  bookCategory " + 
			"	ON bookCategory.categoryId = book.categoryID " +  
			" inner join ReadingLevel  readingLevels " + 
			"	ON book.readingLevelId = readingLevels.readingLevelId " + 
			" inner join User user ON user.id = bookBorrow.userID " +
			" WHERE "+
			"  book.isBookBorrowed=1 AND book.isActive = 1")
	List<Object[]> booksOut();
}

