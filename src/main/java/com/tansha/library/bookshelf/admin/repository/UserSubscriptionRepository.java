package com.tansha.library.bookshelf.admin.repository;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.tansha.library.bookshelf.admin.model.UserSubscription;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
//import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
//import org.springframework.data;
//import org.springframework.boot.autoconfigur
@Transactional
public interface UserSubscriptionRepository extends CrudRepository<UserSubscription,String> {

//	List<UserSubscription> findById(int id);
	List<UserSubscription> findByUserId(int userId);
	UserSubscription findById(int id);
	
	
	//@Query("SELECT userSubscription from UserSubscription userSubscription where userSubscription.userId=?1 AND userSubscription.validTo > NOW() AND userSubscription.paymentStatus = 'success' ")
	@Query("SELECT subscriptions.subcId,subscriptions.subscriptionName,subscriptions.subscriptionDescription,subscriptions.subscriptionSummary,subscriptions.amount,"
			+ " subscriptionRule.ruleId,subscriptionRule.noofMonths,subscriptionRule.maxNumberofBooks,subscriptionRule.maxNumberofDelivery from Subscription subscriptions"
			+ " INNER JOIN  SubscriptionRule subscriptionRule ON subscriptionRule.ruleId = subscriptions.subcId"
			+ " INNER JOIN UserSubscription userSubscription ON userSubscription.subscriptionId = subscriptions.subcId "
			+ " WHERE  subscriptions.isActive = 1 AND subscriptionRule.isActive = 1 AND userSubscription.userId=?1 AND userSubscription.validTo >= NOW() ")
	List<Object[]> getUserSubscriptionDetails(int userId);
	
//	@Query("SELECT userSubscription.id,user.emailId,subscriptions.subscriptionName,userSubscription.createdAt,subscriptions.subscriptionSummary,subscriptions.amount,"
//			+ " subscriptionRule.ruleId,subscriptionRule.noofMonths,subscriptionRule.maxNumberofBooks,subscriptionRule.maxNumberofDelivery from Subscription subscriptions"
//			+ " INNER JOIN  SubscriptionRule subscriptionRule ON subscriptionRule.ruleId = subscriptions.subcId"
//			+ " INNER JOIN UserSubscription userSubscription ON userSubscription.subscriptionId = subscriptions.subcId "
//			+ " INNER JOIN  User user ON user.id = userSubscription.userId "
//			+ " WHERE  userSubscription.paymentStatus = 'success'ORDER BY userSubscription.createdAt ASC")
//	List<Object[]> getFullSubscripeUser();
	
	@Query("SELECT userSubscription.id,user.emailId,subscriptions.subscriptionName,userSubscription.createdAt,subscriptions.subscriptionSummary,subscriptions.amount,"
	+ " userSubscription.subscriptionId,user.id,user.name,user.houseNumber,user.street,user.city,user.pincode,user.phoneNumber from Subscription subscriptions"
//	+ " INNER JOIN  SubscriptionRule subscriptionRule ON subscriptionRule.ruleId = subscriptions.subcId"
	+ " INNER JOIN UserSubscription userSubscription ON userSubscription.subscriptionId = subscriptions.subcId "
	+ " INNER JOIN  User user ON user.id = userSubscription.userId "
	+ " WHERE  userSubscription.paymentStatus = 'success'ORDER BY userSubscription.createdAt DESC")
List<Object[]> getFullSubscripeUser();
	
	 
	
//	@Query("SELECT userSubscription.id,user.emailId,subscriptions.subscriptionName,userSubscription.createdAt,subscriptions.subscriptionSummary,subscriptions.amount,"
//			+ " userSubscription.subscriptionId from Subscription subscriptions"
////			+ " INNER JOIN  SubscriptionRule subscriptionRule ON subscriptionRule.ruleId = subscriptions.subcId"
//			+ " INNER JOIN UserSubscription userSubscription ON userSubscription.subscriptionId = subscriptions.subcId "
//			+ " INNER JOIN  User user ON user.id = userSubscription.userId "
//			+ " WHERE NOT userSubscription.paymentStatus = 'success'ORDER BY userSubscription.createdAt ASC")
//	List<Object[]> getOnlyRegisterUser();
	
	@Query("SELECT userSubscription.id,user.emailId,subscriptions.subscriptionName,userSubscription.createdAt,subscriptions.subscriptionSummary,subscriptions.amount,"
			+ " userSubscription.subscriptionId from Subscription subscriptions"
//			+ " INNER JOIN  SubscriptionRule subscriptionRule ON subscriptionRule.ruleId = subscriptions.subcId"
			+ " INNER JOIN UserSubscription userSubscription ON userSubscription.subscriptionId = subscriptions.subcId "
			+ " INNER JOIN  User user ON user.id = userSubscription.userId "
			+ " WHERE  userSubscription.subscriptionId = ?1")
	List<Object[]> reportPlanbaseUser(int subscriptionId); 
	
	@Query("SELECT userSubscription.id,user.emailId,user.phoneNumber,userSubscription.validTo,subscriptions.subscriptionName,userSubscription.createdAt,subscriptions.subscriptionSummary,subscriptions.amount,"
			+ " subscriptionRule.ruleId,subscriptionRule.noofMonths,subscriptionRule.maxNumberofBooks,subscriptionRule.maxNumberofDelivery from Subscription subscriptions"
			+ " INNER JOIN  SubscriptionRule subscriptionRule ON subscriptionRule.ruleId = subscriptions.subcId"
			+ " INNER JOIN UserSubscription userSubscription ON userSubscription.subscriptionId = subscriptions.subcId "
			+ " INNER JOIN  User user ON user.id = userSubscription.userId "
			+ " WHERE  DATE(userSubscription.validTo) < CURRENT_DATE()")
	List<Object[]> reportExpireUsers();
	
	@Query("SELECT userSubscription.id,user.emailId,user.name,user.phoneNumber,subscriptions.amount,subscriptions.subscriptionName,userSubscription.createdAt,subscriptions.subscriptionSummary,subscriptions.amount,"
			+ " subscriptions.noofMonths,subscriptions.maxNumberofBooks,"
			+ "subscriptions.maxNumberofDelivery,userSubscription.userId,userSubscription.subscriptionId,userSubscription.paymentStatus"
			+ " from Subscription subscriptions"
			//+ " INNER JOIN  SubscriptionRule subscriptionRule ON subscriptionRule.ruleId = subscriptions.subcId"
			+ " INNER JOIN UserSubscription userSubscription ON userSubscription.subscriptionId = subscriptions.subcId "
			+ " INNER JOIN  User user ON user.id = userSubscription.userId "
			+ " WHERE NOT userSubscription.paymentStatus = 'success' ORDER BY userSubscription.createdAt ASC")
	List<Object[]> getCashPaymentPendingUsers();
	
	@Query("SELECT userSubscription.id,user.emailId,subscriptions.subscriptionName,userSubscription.createdAt,subscriptions.subscriptionSummary,subscriptions.amount,"
			+ " subscriptionRule.ruleId,subscriptionRule.noofMonths,subscriptionRule.maxNumberofBooks,"
			+ "subscriptionRule.maxNumberofDelivery,userSubscription.userId,userSubscription.subscriptionId,subscriptions.amount,userSubscription.paymentStatus"
			+ " from Subscription subscriptions"
			+ " INNER JOIN  SubscriptionRule subscriptionRule ON subscriptionRule.ruleId = subscriptions.subcId"
			+ " INNER JOIN UserSubscription userSubscription ON userSubscription.subscriptionId = subscriptions.subcId "
			+ " INNER JOIN  User user ON user.id = userSubscription.userId "
			+ " WHERE NOT userSubscription.paymentStatus = 'success' ORDER BY userSubscription.createdAt ASC")
	List<Object[]> getPaymentpendingUsers();
	
	
	@Query("SELECT user.id,user.name,user.emailId,user.phoneNumber,user.createdAt,subscription.paymentStatus FROM User user LEFT JOIN UserSubscription subscription ON subscription.userId = user.id WHERE subscription.paymentStatus IS NULL OR  subscription.paymentStatus = 'pending' OR  subscription.paymentStatus = 'paybycash' ORDER BY user.createdAt DESC")
	List<Object[]> getOnlyRegisterUser();

}
