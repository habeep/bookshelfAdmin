package com.tansha.library.bookshelf.admin.repository;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.tansha.library.bookshelf.admin.model.Subscription;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
//import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
//import org.springframework.data;
//import org.springframework.boot.autoconfigur
@Transactional
public interface SubscriptionRepository extends CrudRepository<Subscription,String> {

	List<Subscription> findBySubscriptionName(String subscriptionName);
	List<Subscription> findBySubcId(int subc_ID);
	List<Subscription> findByIsActive(int isActive);
	
	@Query("SELECT subscription from Subscription subscription where subscription.isActive = 1 GROUP BY subscriptionName")
			
	List<Object[]> getAllActiveSubscriptions();
	
	@Query("SELECT subscription from Subscription subscription where subscription.isActive = 1 GROUP BY noofMonths")
	
	List<Object[]> getAllActiveSubscriptionMonths();
	
	
	@Query("SELECT subscriptions.subcId,subscriptions.subscriptionName,subscriptions.subscriptionDescription,subscriptions.subscriptionSummary,subscriptions.amount,"
			+ " subscriptionRule.ruleId,subscriptionRule.noofMonths,subscriptionRule.maxNumberofBooks,subscriptionRule.maxNumberofDelivery from Subscription subscriptions"
			+ " INNER JOIN  SubscriptionRule subscriptionRule ON subscriptionRule.ruleId = subscriptions.subcId"
			+ " WHERE  subscriptions.isActive = 1 AND subscriptionRule.isActive = 1")
	List<Object[]> getAllSubscriptions();
	
	@Query("SELECT subscriptions.subcId,subscriptions.subscriptionName,subscriptions.subscriptionDescription,subscriptions.subscriptionSummary,subscriptions.amount,"
			+ " subscriptionRule.ruleId,subscriptionRule.noofMonths,subscriptionRule.maxNumberofBooks,subscriptionRule.maxNumberofDelivery from Subscription subscriptions"
			+ " INNER JOIN  SubscriptionRule subscriptionRule ON subscriptionRule.ruleId = subscriptions.subcId"
			+ " WHERE  subscriptions.isActive = 1 AND subscriptionRule.isActive = 1 AND subscriptions.subcId=?1")
	List<Object[]> getSubscriptionsById(int subcId);
	 
}
