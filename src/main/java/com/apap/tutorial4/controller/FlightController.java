package com.apap.tutorial4.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.apap.tutorial4.model.FlightModel;
import com.apap.tutorial4.model.PilotModel;
import com.apap.tutorial4.service.FlightService;
import com.apap.tutorial4.service.PilotService;

import java.util.List;

@Controller
public class FlightController {
	@Autowired
	private FlightService flightService;
	
	@Autowired
	private PilotService pilotService;
	
	@RequestMapping(value = "/flight/add/{licenseNumber}", method = RequestMethod.GET)
	private String add(@PathVariable(value = "licenseNumber") String licenseNumber, Model model) {
	FlightModel flight = new FlightModel();

	PilotModel pilot = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
	flight.setPilot(pilot);
	
	model.addAttribute("flight", flight);
	return "addFlight";
	}
	
	@RequestMapping(value = "/flight/add", method = RequestMethod.POST)
	private String addFlightSubmit(@ModelAttribute FlightModel flight) {
		flightService.addFlight(flight);
		return "add";
	}
	
	@RequestMapping(value = "/pilot/view", method = RequestMethod.GET)
	private String viewAllPilotJourney(@RequestParam(value = "licenseNumber") String licenseNumber, Model model) {
		PilotModel archive = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
		List<FlightModel> addNew = flightService.getFlightDetailByPilot(archive.getLicenseNumber());
		if(archive != null) {
			model.addAttribute("dataflight", addNew);
			model.addAttribute("datapilot", archive);
			return "view-pilot";
		}
		return "no-input";
	}
	
	@RequestMapping(value = "/flight/delete/{id}", method = RequestMethod.GET)
	private String deleteFlight(@PathVariable(value = "id") long id, Model model) {
		flightService.deleteById(id);
		return "delete";
	}
	
	@RequestMapping(value = "/flight/update/{id}", method = RequestMethod.GET)
	private String updateFlight(@PathVariable(value = "id") long id, Model model) {
		model.addAttribute("flight", new FlightModel());
		FlightModel oldFlight = flightService.getFlightDetailById(id);
		model.addAttribute("oldFlight", oldFlight);
		return "update-flight";
	}
	
	@RequestMapping(value = "/flight/update", method = RequestMethod.POST)
	private String updatePilotSubmit(@ModelAttribute FlightModel flight) {
		long id = flight.getId();
		flightService.updateFlight(id, flight);
		return "update";
	}
	
	@RequestMapping(value = "/flight/view", method = RequestMethod.GET)
	private String viewFlight(@RequestParam(value = "id") long id, Model model) {
		FlightModel flight = flightService.getFlightDetailById(id);
		model.addAttribute("flight", flight);
		return "view-flight";
	}
	
	

}

	
	


