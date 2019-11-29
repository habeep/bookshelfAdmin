package com.tansha.library.bookshelf.admin.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.tansha.library.bookshelf.admin.model.DeliverySlot;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
//import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
//import org.springframework.data;
//import org.springframework.boot.autoconfigur
@Transactional
public interface DeliverySlotRepository extends CrudRepository<DeliverySlot,String> {

	//List<DeliverySlot> findBySlotID();
	
	DeliverySlot findBySlotID(int slotId);
	
	List<DeliverySlot> findByAreaID(int areaId);
	
	@Query("SELECT ds from DeliverySlot ds where ds.dateOfdelivery >= CURDATE() ")
	List<DeliverySlot> getAllDeliveries();

	@Query("SELECT count(ds) from DeliverySlot ds where ds.dateOfdelivery = ?1 AND ds.staffID= ?2 AND ds.areaID = ?3 AND ds.deliveryTimeFrom = ?4 AND ds.deliveryTimeTill = ?5")
	int checkDeliverySlot(String dateOfDelivery, int staffId,int areaID,int deliveryTimeFrom,int deliveryTimeTill);
	
	@Query("SELECT ds from DeliverySlot ds where ds.dateOfdelivery >= CURDATE() AND ds.staffID= ?1  ")
	List<DeliverySlot> getStaffAllocatedDeliveries(long staffId);
	
	@Query("SELECT ds from DeliverySlot ds INNER JOIN Area area ON area.areaID=ds.areaID "
			+ " INNER JOIN User user ON user.pincode = area.pincode where user.id = ?1 AND ds.dateOfdelivery > NOW() ORDER by ds.dateOfdelivery ASC")
	List<DeliverySlot> getAllDeliveriesForUserArea(int userId);
	
	@Query("SELECT ds.slotID,staff.staffName,staff.emailId,ds.dateOfdelivery,ds.deliveryTimeFrom,ds.deliveryTimeTill,area.area,area.pincode,ds.isActive from DeliverySlot ds INNER JOIN Area area ON area.areaID=ds.areaID "
			+ " INNER JOIN Staff staff ON staff.id = ds.staffID where  ds.dateOfdelivery > NOW() ORDER by ds.dateOfdelivery ASC")
	List<Object[]> getAllDeliveriesSlots();
	
	@Query("SELECT ds.slotID,staff.staffName,staff.emailId,ds.dateOfdelivery,ds.deliveryTimeFrom,ds.deliveryTimeTill,area.area,area.pincode,ds.isActive from DeliverySlot ds INNER JOIN Area area ON area.areaID=ds.areaID "
			+ " INNER JOIN Staff staff ON staff.id = ds.staffID where staff.id = ?1 AND  ds.dateOfdelivery > NOW() ORDER by ds.dateOfdelivery ASC")
	List<Object[]> getStaffDeliveriesSlots(long staffId);
	
	
	
	 
	 
}
