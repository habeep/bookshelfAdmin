package com.tansha.library.bookshelf.admin.controllers;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.tansha.library.bookshelf.admin.model.Area;
import com.tansha.library.bookshelf.admin.service.impl.AreaServiceImpl;


@RestController
public class AreaRestController {
	
	@Autowired
	private AreaServiceImpl areaService;
	
	
	
	@RequestMapping(value = "/areas", method = RequestMethod.GET)
	public List<Area> getAreas(){
		List<Area> areas = areaService.getAllAreas();
		return areas;
		}
	
	
}
