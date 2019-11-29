package com.tansha.library.bookshelf.admin.repository;



import java.time.OffsetDateTime;
import java.util.Date;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.tansha.library.bookshelf.admin.model.Book;
import com.tansha.library.bookshelf.admin.model.BookCategory;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
//import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
//import org.springframework.data;
//import org.springframework.boot.autoconfigur
@Transactional
public interface BookRepository extends CrudRepository<Book,String> {

	
	List<Book> findByBookTitle(String bookTitle);
	Book findByBookId(int bookId);
	List<Book> findByBookTitleOrIsbncode(String bookTitle,String bookISBN );
	List<Book> findByAuthorIDIn(List<Integer> authordIds); 
	List<Book> findByPublisherIDIn(List<Integer> publisherIds); 
	List<Book> findByLanguageIdIn(List<Integer> languageIds); 
	
	 @Modifying
	    @Query(
	            value = "TRUNCATE tbl_books_details",
	            nativeQuery = true
	    )
	    void truncateBooks();
	
	@Query("SELECT book from Book book where book.bookId = ?1 AND book.isBookBorrowed=0 AND book.isLost = 0")
	Book findOne(int bookId);
	//List<Book> findByBookTitleOrIsbncodeOrAuthorIDOrLanguageIdOrLongTitleOrPublisherIDOrPublisherID(String bookTitle,String bookISBN,int authorId,int languageId,String longTitle,int publisherId);
	List<Book> findByIsbncode(String isbnCode);
	List<Book> findByIsFeaturedBook(int featuredBookFlag);
	
	//SELECT book from Book book LEFT JOIN Author author ON book.authorId = author.authorId LEFT JOIN Publisher publisher ON book.publisherId=publisher.publisherId where book.isFeaturedBook = ?1 AND book.isBookBorrowed=0 GROUP BY book.isbncode
	
	@Query("SELECT author.authorName,publisher.publisherName,book.bookId,book.bookTitle,book.isbncode,readingLevels.readingLevel,readingLevels.readingLevelId from Book book, Author author,Publisher publisher,ReadingLevel  readingLevels where book.publisherID=publisher.publisherId AND book.authorID = author.authorID AND book.readingLevelId = readingLevels.readingLevelId AND book.isFeaturedBook = ?1 AND book.isBookBorrowed=0")
	List<Object[]> getFeaturedBooks(int featuredBookFlag);
	
	@Query("select book.bookId,book.bookTitle, publisher.publisherName,authors.authorName,bookCategory.categoryName,book.isbncode,readingLevels.readingLevel,readingLevels.readingLevelId from Book book " + 
			"inner join  Publisher publisher " + 
			"	on book.publisherID=publisher.publisherId " + 
			"inner join Author  authors " + 
			"	ON book.authorID = authors.authorID " + 
			" inner join ReadingLevel  readingLevels " + 
			"	ON book.readingLevelId = readingLevels.readingLevelId " + 
			"inner join Language languages " + 
			"	on book.languageId = languages.languageID " +  
			"inner join BookCategory  bookCategory " + 
			"	ON bookCategory.categoryId = book.categoryID " + 
			"where (book.longTitle like %?1% OR book.bookTitle like %?1% OR publisher.publisherName like %?1% OR readingLevels.readingLevel like %?1% OR book.publishedYear like %?1% " + 
			"OR authors.authorName like %?1% OR languages.language like %?1% OR bookCategory.categoryName like %?1% ) " + 
			"AND book.isBookBorrowed = 0 AND book.isActive = 1 AND book.isLost = 0 group by book.isbncode ")
	List<Object[]> searchBooks(String searchStr);
	
	
	@Query("select book.bookId,book.bookTitle, publisher.publisherName,authors.authorName,bookCategory.categoryName,book.isbncode,readingLevels.readingLevel,readingLevels.readingLevelId  from Book book " + 
			" inner join  Publisher publisher " + 
			"	on book.publisherID=publisher.publisherId " + 
			" inner join Author  authors " + 
			"	ON book.authorID = authors.authorID " + 
			" inner join ReadingLevel  readingLevels " + 
			"	ON book.readingLevelId = readingLevels.readingLevelId " + 
			" inner join Language languages " + 
			"	on book.languageId = languages.languageID " +  
			" inner join BookCategory  bookCategory " + 
			"	ON bookCategory.categoryId = book.categoryID " + 
			"where ( book.publisherID = ?1 OR book.categoryID = ?2 OR book.languageId = ?3 OR  readingLevels.readingLevelId = ?4)" + 
			//"OR authors.authorName like %?1% OR languages.language like %?1% OR bookCategory.categoryName like %?1% ) " + 
			"AND book.isBookBorrowed = 0 AND book.isActive = 1 AND book.isLost = 0 group by book.isbncode ")
	List<Object[]> selectSearchBooks(int publisherId,int bookCategoryId,int languageId,int readingLevelList); 
	
	@Query("select book.bookId,book.bookTitle, publisher.publisherName,authors.authorName,bookCategory.categoryName,languages.language,book.isbncode,readingLevels.readingLevel,readingLevels.readingLevelId  from Book book " + 
			"inner join  Publisher publisher " + 
			"	on book.publisherID=publisher.publisherId " + 
			"inner join Author  authors " + 
			"	ON book.authorID = authors.authorID " + 
			" inner join ReadingLevel  readingLevels " + 
			"	ON book.readingLevelId = readingLevels.readingLevelId " + 
			" inner join Language languages " + 
			"	on book.languageId = languages.languageID " +  
			" inner join BookCategory  bookCategory " + 
			"	ON bookCategory.categoryId = book.categoryID " + 
			" where book.bookId = ?1  " + 
			"AND book.isBookBorrowed = 0 AND book.isActive = 1 AND book.isLost = 0 group by book.isbncode ")
	List<Object[]> bookDetails(int bookId);
	
	@Query("select book.bookId,book.bookTitle, publisher.publisherName,authors.authorName,bookCategory.categoryName,languages.language,book.isbncode,readingLevels.readingLevel,readingLevels.readingLevelId,book.longTitle,book.description  from Book book " + 
			"inner join  Publisher publisher " + 
			"	on book.publisherID=publisher.publisherId " + 
			"inner join Author  authors " + 
			"	ON book.authorID = authors.authorID " + 
			" inner join ReadingLevel  readingLevels " + 
			"	ON book.readingLevelId = readingLevels.readingLevelId " + 
			" inner join Language languages " + 
			"	on book.languageId = languages.languageID " +  
			" inner join BookCategory  bookCategory " + 
			"	ON bookCategory.categoryId = book.categoryID " + 
			" where book.bookId = ?1  " + 
			"AND book.isBookBorrowed = 1 AND book.isActive = 1 AND book.isLost = 0 group by book.isbncode ")
	List<Object[]> borrowBookDetails(int bookId);
	
	
	@Query("select book.bookId,book.bookTitle,book.isbncode,publisher.publisherName,authors.authorName,bookCategory.categoryName,readingLevels.readingLevel,languages.language,book.publishedYear,book.isBookBorrowed,book.isLost,book.isActive,readingLevels.readingLevelId,book.longTitle,book.description  from Book book " + 
			"inner join  Publisher publisher " + 
			"	on book.publisherID=publisher.publisherId " + 
			"inner join Author  authors " + 
			"	ON book.authorID = authors.authorID " + 
			" inner join ReadingLevel  readingLevels " + 
			"	ON book.readingLevelId = readingLevels.readingLevelId " + 
			" inner join Language languages " + 
			"	on book.languageId = languages.languageID " +  
			" inner join BookCategory  bookCategory " + 
			"	ON bookCategory.categoryId = book.categoryID " + 
			" where  " + 
			" book.isLost = 0 ")
	List<Object[]> allBooks();
	
	@Query("SELECT book from Book book where (bookTitle = ?1 OR ISBNCODE = ?2) AND isBookBorrowed = 1")
	List<Book> findByBookTitleOrIsbncodeAndIsBookBorrowed(String bookTitle,String isbnCode);
	
	@Query("SELECT book from Book book where (bookTitle = ?1 OR ISBNCODE = ?2) AND isBookBorrowed = 0")
	List<Book> findByBookTitleOrIsbncodeAndIsBookNotBorrowed(String bookTitle,String isbnCode);
	//List<Book> findByISBNCODE(String isbnCode);
	
	@Query("select book.bookId,book.bookTitle,book.isbncode,publisher.publisherName,authors.authorName,bookCategory.categoryName,readingLevels.readingLevel,languages.language,book.publishedYear,book.isBookBorrowed,book.isLost,book.isActive,readingLevels.readingLevelId,book.longTitle,book.description  from Book book " + 
			"inner join  Publisher publisher " + 
			"	on book.publisherID=publisher.publisherId " + 
			"inner join Author  authors " + 
			"	ON book.authorID = authors.authorID " + 
			" inner join ReadingLevel  readingLevels " + 
			"	ON book.readingLevelId = readingLevels.readingLevelId " + 
			" inner join Language languages " + 
			"	on book.languageId = languages.languageID " +  
			" inner join BookCategory  bookCategory " + 
			"	ON bookCategory.categoryId = book.categoryID " + 
			" where  " + 
			" book.isActive = 1 AND book.isLost = 0 ORDER BY book.bookId ASC ")
	List<Object[]> stockBooks();
	
	@Query("select book.bookId,book.bookTitle, publisher.publisherName,authors.authorName,bookCategory.categoryName,languages.language,book.isbncode,book.longTitle,book.description,readingLevels.readingLevel  from Book book " + 
			"inner join  Publisher publisher " + 
			"	on book.publisherID=publisher.publisherId " + 
			"inner join Author  authors " + 
			"	ON book.authorID = authors.authorID " + 
			" inner join Language languages " + 
			"	on book.languageId = languages.languageID " +  
			" inner join BookCategory  bookCategory " + 
			"	ON bookCategory.categoryId = book.categoryID " + 
			" inner join ReadingLevel  readingLevels " + 
			"	ON book.readingLevelId = readingLevels.readingLevelId " + 
			" WHERE "+
			"  book.createdAt BETWEEN :startDate  AND :tillDate AND book.isActive = 1 ORDER BY book.createdAt ASC")
	List<Object[]> dateBasedStockList(@Param("startDate")Date fromDate,@Param("tillDate")Date tillDate);
	
	@Query("select book.bookId,book.bookTitle, publisher.publisherName,authors.authorName,bookCategory.categoryName,languages.language,book.isbncode,book.longTitle,book.description,readingLevels.readingLevel  from Book book " + 
			"inner join  Publisher publisher " + 
			"	on book.publisherID=publisher.publisherId " + 
			"inner join Author  authors " + 
			"	ON book.authorID = authors.authorID " + 
			" inner join Language languages " + 
			"	on book.languageId = languages.languageID " +  
			" inner join BookCategory  bookCategory " + 
			"	ON bookCategory.categoryId = book.categoryID " +  
			" inner join ReadingLevel  readingLevels " + 
			"	ON book.readingLevelId = readingLevels.readingLevelId " + 
			" WHERE "+
			"  book.isBookBorrowed=0 AND book.isActive = 1")
	List<Object[]> bookInHand();
	
//	@Query("select book.bookId,book.bookTitle, publisher.publisherName,authors.authorName,bookCategory.categoryName,languages.language,book.isbncode,book.longTitle,book.description,readingLevels.readingLevel  from Book book " + 
//			"inner join  Publisher publisher " + 
//			"	on book.publisherID=publisher.publisherId " + 
//			"inner join Author  authors " + 
//			"	ON book.authorID = authors.authorID " + 
//			" inner join Language languages " + 
//			"	on book.languageId = languages.languageID " +  
//			" inner join BookCategory  bookCategory " + 
//			"	ON bookCategory.categoryId = book.categoryID " +  
//			" inner join ReadingLevel  readingLevels " + 
//			"	ON book.readingLevelId = readingLevels.readingLevelId " + 
//			" WHERE "+
//			"  book.isBookBorrowed=1 AND book.isActive = 1")
//	List<Object[]> booksOut();
	
	@Query("select book.bookId,book.bookTitle, publisher.publisherName,authors.authorName,bookCategory.categoryName,book.isbncode,readingLevels.readingLevel,readingLevels.readingLevelId,languages.language  from Book book " + 
			" inner join  Publisher publisher " + 
			"	on book.publisherID=publisher.publisherId " + 
			" inner join Author  authors " + 
			"	ON book.authorID = authors.authorID " + 
			" inner join ReadingLevel  readingLevels " + 
			"	ON book.readingLevelId = readingLevels.readingLevelId " + 
			" inner join Language languages " + 
			"	on book.languageId = languages.languageID " +  
			" inner join BookCategory  bookCategory " + 
			"	ON bookCategory.categoryId = book.categoryID " + 
			"where ( book.publisherID = ?1 OR book.languageId = ?2 OR book.authorID = ?3 OR book.readingLevelId = ?4)" + 
			//"OR authors.authorName like %?1% OR languages.language like %?1% OR bookCategory.categoryName like %?1% ) " + 
			"AND book.isBookBorrowed = 0 AND book.isActive = 1 AND book.isLost = 0 ")
	List<Object[]> individualStockBooks(int publisherId,int languageId,int authorId,int redingLevelId);
	
	@Query("SELECT book.bookId from Book book where book.isActive = 1")
	List<Integer> getBookPrintId(); 
}
