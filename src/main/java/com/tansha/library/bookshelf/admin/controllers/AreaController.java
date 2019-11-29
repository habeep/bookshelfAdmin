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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.tansha.library.bookshelf.admin.model.Area;
import com.tansha.library.bookshelf.admin.repository.AreaRepository;
import com.tansha.library.bookshelf.admin.service.impl.AreaServiceImpl;

@Controller
public class AreaController {

	@Autowired
	private AreaServiceImpl areaService;
	@Autowired
	private AreaRepository areaRepository;
	/*
	 * @RequestMapping(value={ "/getareabyid"}, method = RequestMethod.GET) public
	 * ModelAndView getAreaById(@PathVariable("id") Integer id) { ModelAndView
	 * modelAndView = new ModelAndView(); Area area = areaService.getAreaById(id);
	 * return modelAndView; }
	 */

	@RequestMapping(value = { "/managearea" }, method = RequestMethod.GET)
	public ModelAndView getAllAreas() {
		ModelAndView modelAndView = new ModelAndView();
		List<Area> list = areaService.getAllAreas();
		modelAndView.setViewName("manageareas");
		modelAndView.addObject("area", list);
		return modelAndView;
	}

	@RequestMapping(value = { "/addarea" }, method = RequestMethod.GET)
	public ModelAndView addArea() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("addarea");
		return modelAndView;
	}

	@RequestMapping(value = { "/addarea" }, method = RequestMethod.POST)
	public ModelAndView addArea(@RequestParam("area") final String areaName,
			@RequestParam("pincode") final int pincode) {
		ModelAndView modelAndView = new ModelAndView();
		Area area = new Area();
		area.setArea(areaName);
		area.setPincode(pincode);
		int isExist = areaRepository.findByCustomQuery(pincode, 0);	
		if (isExist == 0) {
			areaService.addArea(area);
			modelAndView.setViewName("redirect:/managearea");
		} else {
			modelAndView.addObject("errorMsg", "Pincode is already available");
			modelAndView.setViewName("addarea");
		}

		return modelAndView;
	}

	@RequestMapping(value = "/updatearea/{areaId}", method = RequestMethod.GET)
	public ModelAndView updateReligion(@PathVariable final int areaId) {

		ModelAndView modelandview = new ModelAndView();
		Area area = areaRepository.findByAreaID(areaId);
		if (area != null) {
			modelandview.addObject("area", area.getArea());
			modelandview.addObject("pincode", area.getPincode());
			modelandview.addObject("areaId", area.getAreaID());
			modelandview.setViewName("updatearea");
		} else {
			modelandview.setViewName("redirect:/managearea");
		}
		return modelandview;
	}

	@RequestMapping(value = "/updatearea", method = RequestMethod.POST)
	public ModelAndView updateReligion(@RequestParam("areaId") final int areaId,
			@RequestParam("area") final String areaName, @RequestParam("pincode") final int pincode) {

		ModelAndView modelandview = new ModelAndView();
		System.out.println("the area id is"+areaId);
		Area area = areaRepository.findByAreaID(areaId);
		int isExist = areaRepository.findByCustomQuery(pincode, areaId);
		if (area != null && isExist == 0) {
			area.setArea(areaName);
			area.setPincode(pincode);
			areaRepository.save(area);

			modelandview.setViewName("redirect:/managearea");
		} else {
			modelandview.addObject("errorMsg", "Pincode is already available");
			modelandview.addObject("area", area.getArea());
			modelandview.addObject("pincode", area.getPincode());
			modelandview.addObject("areaId", area.getAreaID());
			modelandview.setViewName("updatearea");
		}
		return modelandview;
	}

	@RequestMapping(value = "/deletearea/{areaId}/{activeFlag}", method = RequestMethod.GET)
	public ModelAndView deleteReligion(@PathVariable final int areaId, @PathVariable final int activeFlag) {
		ModelAndView modelandview = new ModelAndView();
		Area area = areaRepository.findByAreaID(areaId);
		if (area != null) {
			area.setIsActive(activeFlag);
			areaRepository.save(area);
			modelandview.setViewName("redirect:/managearea");
		} else {
			modelandview.setViewName("redirect:/managearea");
		}
		return modelandview;
	}

}
