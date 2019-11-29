package com.tansha.library.bookshelf.admin.service;

import java.util.List;

import com.tansha.library.bookshelf.admin.model.SubscriptionRule;

public interface SubscriptionRuleService {
	List<SubscriptionRule> getAllSubscriptionRules();
	SubscriptionRule getSubscriptionRuleById(int subscriptionRuleId);
    boolean addSubscriptionRule(SubscriptionRule subscription);
    void updateSubscriptionRule(SubscriptionRule subscription);
    void deleteSubscriptionRule(int subscriptionRuleId);
}
