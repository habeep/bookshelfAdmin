package com.tansha.library.bookshelf.admin.service.impl;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tansha.library.bookshelf.admin.model.Subscription;
import com.tansha.library.bookshelf.admin.repository.SubscriptionRepository;
import com.tansha.library.bookshelf.admin.repository.UserSubscriptionRepository;
import com.tansha.library.bookshelf.admin.service.SubscriptionService;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {
	@Autowired
	private SubscriptionRepository subscriptionRepository;
	
	@Autowired
	private UserSubscriptionRepository userSubscriptionRepository;
	@Override
	public Subscription getSubscriptionById(int subscriptionId) {
		Subscription obj = subscriptionRepository.findBySubcId(subscriptionId).get(0);
		return obj;
	}	
	@Override
	public List<Subscription> getAllSubscriptions(){
		
		List<Subscription> list = new ArrayList<>();
		subscriptionRepository.findAll().forEach(e -> list.add(e));
		return list;
	}
	
	@Override
	public List<Object[]> getAllRequestedUserSubscriptions(){
		
		List<Object[]> list = new ArrayList<Object[]>();
		userSubscriptionRepository.getCashPaymentPendingUsers().forEach(e -> list.add(e));
		return list;
	}
	
	@Override
	public synchronized boolean addSubscription(Subscription subscription){

	        List<Subscription> list = subscriptionRepository.findBySubscriptionName(subscription.getSubscriptionName()); 	
                if (list.size() > 0) {
    	           return false;
                } else {
    	        subscriptionRepository.save(subscription);
    	        return true;
       }
	}
	@Override
	public void updateSubscription(Subscription subscription) {
		System.out.println("subscription string value >>>> "+subscription.toString());
		subscriptionRepository.save(subscription);
	}
	@Override
	public void deleteSubscription(int subscriptionId) {
		subscriptionRepository.delete(getSubscriptionById(subscriptionId));
	}
}
