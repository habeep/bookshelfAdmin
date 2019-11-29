package com.tansha.library.bookshelf.admin.service;

import java.util.List;

import com.tansha.library.bookshelf.admin.model.Area;

public interface AreaService {
	List<Area> getAllAreas();
	Area getAreaById(int areaId);
    boolean addArea(Area area);
    void updateArea(Area area);
    void deleteArea(int areaId);
}
