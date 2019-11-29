package com.tansha.library.bookshelf.admin.service.impl;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tansha.library.bookshelf.admin.model.Area;
import com.tansha.library.bookshelf.admin.repository.AreaRepository;
import com.tansha.library.bookshelf.admin.service.AreaService;

@Service
public class AreaServiceImpl implements AreaService {
	@Autowired
	private AreaRepository areaRepository;
	@Override
	public Area getAreaById(int areaId) {
		Area obj = areaRepository.findByAreaID(areaId);
		return obj;
	}	
	@Override
	public List<Area> getAllAreas(){
		
		List<Area> list = new ArrayList<>();
		
		areaRepository.findAll().forEach(e -> list.add(e));
		return list;
	}
	@Override
	public synchronized boolean addArea(Area area){

	        List<Area> list = areaRepository.findByAreaOrPincode(area.getArea(),area.getPincode()); 	
                if (list.size() > 0) {
    	           return false;
                } else {
    	        areaRepository.save(area);
    	        return true;
       }
	}
	@Override
	public void updateArea(Area area) {
		areaRepository.save(area);
	}
	@Override
	public void deleteArea(int areaId) {
		areaRepository.delete(getAreaById(areaId));
	}
	
}
