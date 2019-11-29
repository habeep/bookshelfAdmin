package com.tansha.library.bookshelf.admin.service;

import java.util.List;

import com.tansha.library.bookshelf.admin.model.Subscription;

public interface SubscriptionService {
	List<Subscription> getAllSubscriptions();
	
	List<Object[]> getAllRequestedUserSubscriptions();
	Subscription getSubscriptionById(int subscriptionId);
    boolean addSubscription(Subscription subscription);
    void updateSubscription(Subscription subscription);
    void deleteSubscription(int subscriptionId);
}
