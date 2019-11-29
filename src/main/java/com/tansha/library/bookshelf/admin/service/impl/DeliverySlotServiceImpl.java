package com.tansha.library.bookshelf.admin.service.impl;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tansha.library.bookshelf.admin.model.DeliverySlot;
import com.tansha.library.bookshelf.admin.repository.DeliverySlotRepository;
import com.tansha.library.bookshelf.admin.service.DeliverySlotService;

@Service
public class DeliverySlotServiceImpl implements DeliverySlotService {
	@Autowired
	private DeliverySlotRepository deliverySlotRepository;
	@Override
	public DeliverySlot getDeliverySlotById(int deliverySlotId) {
		DeliverySlot obj = deliverySlotRepository.findBySlotID(deliverySlotId);
		return obj;
	}	
	@Override
	public List<DeliverySlot> getAllDeliverySlots(){
		
		List<DeliverySlot> list = new ArrayList<>();
		
		deliverySlotRepository.findAll().forEach(e -> list.add(e));
		return list;
	}
	@Override
	public synchronized boolean addDeliverySlot(DeliverySlot deliverySlot){

	        int count = deliverySlotRepository.checkDeliverySlot(deliverySlot.getDateOfdelivery(),deliverySlot.getStaffID(),deliverySlot.getAreaID(),deliverySlot.getDeliveryTimeFrom(),deliverySlot.getDeliveryTimeTill()); 	
                if (count > 0) {
    	           return false;
                } else {
                	
    	        deliverySlotRepository.save(deliverySlot);
    	        return true;
       }
	}

	@Override
	public void updateDeliverySlot(DeliverySlot deliverySlot) {
		deliverySlotRepository.save(deliverySlot);
	}
	@Override
	public void deleteDeliverySlot(int deliverySlotId) {
		deliverySlotRepository.delete(getDeliverySlotById(deliverySlotId));
	}
	
}
