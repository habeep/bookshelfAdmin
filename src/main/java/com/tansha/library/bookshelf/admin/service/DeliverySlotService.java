package com.tansha.library.bookshelf.admin.service;

import java.util.List;

import com.tansha.library.bookshelf.admin.model.DeliverySlot;

public interface DeliverySlotService {
	List<DeliverySlot> getAllDeliverySlots();
	DeliverySlot getDeliverySlotById(int deliverySlotId);
    boolean addDeliverySlot(DeliverySlot deliverySlot);
    void updateDeliverySlot(DeliverySlot deliverySlot);
    void deleteDeliverySlot(int deliverySlotId);
}
