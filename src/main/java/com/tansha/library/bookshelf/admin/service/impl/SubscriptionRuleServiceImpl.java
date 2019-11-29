package com.tansha.library.bookshelf.admin.service.impl;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tansha.library.bookshelf.admin.model.SubscriptionRule;
import com.tansha.library.bookshelf.admin.repository.SubscriptionRuleRepository;
import com.tansha.library.bookshelf.admin.service.SubscriptionRuleService;

@Service
public class SubscriptionRuleServiceImpl implements SubscriptionRuleService {
	@Autowired
	private SubscriptionRuleRepository subscriptionRuleRepository;
	@Override
	public SubscriptionRule getSubscriptionRuleById(int subscriptionRuleId) {
		SubscriptionRule obj = subscriptionRuleRepository.findByRuleId(subscriptionRuleId).get(0);
		return obj;
	}	
	@Override
	public List<SubscriptionRule> getAllSubscriptionRules(){
		
		List<SubscriptionRule> list = new ArrayList<>();
		subscriptionRuleRepository.findAll().forEach(e -> list.add(e));
		return list;
	}
	@Override
	public synchronized boolean addSubscriptionRule(SubscriptionRule subscriptionRule){

	        List<SubscriptionRule> list = subscriptionRuleRepository.findByRuleName(subscriptionRule.getRuleName()); 	
                if (list.size() > 0) {
    	           return false;
                } else {
                	subscriptionRule.setIsActive(1);
    	        subscriptionRuleRepository.save(subscriptionRule);
    	        return true;
       }
	}
	@Override
	public void updateSubscriptionRule(SubscriptionRule subscriptionRule) {
		subscriptionRuleRepository.save(subscriptionRule);
	}
	@Override
	public void deleteSubscriptionRule(int subscriptionRuleId) {
		subscriptionRuleRepository.delete(getSubscriptionRuleById(subscriptionRuleId));
	}
}
