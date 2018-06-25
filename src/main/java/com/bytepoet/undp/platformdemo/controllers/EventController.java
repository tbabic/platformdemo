package com.bytepoet.undp.platformdemo.controllers;


import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bytepoet.undp.platformdemo.domain.Event;
import com.bytepoet.undp.platformdemo.repositories.EventRepository;
import com.bytepoet.undp.platformdemo.services.PrintService;

@RestController
@RequestMapping("/events")
public class EventController {

	@Autowired
	private EventRepository eventRepository;
	
	@Autowired
	private PrintService printService;
	
	@RequestMapping(path = {"","/"}, method = RequestMethod.POST, consumes="application/json", produces="application/json")
	public Event saveEvent(@RequestBody Event event) {
		return eventRepository.save(event);
	}
	
	@RequestMapping(path = {"/{id}"}, method = RequestMethod.GET, consumes="application/json", produces="application/json")
	public Event getEvent(@PathVariable(value="id") long id) {
		return eventRepository.findById(id).get();
	}
	
	@RequestMapping(path = "/{id}/print/{document}", method = RequestMethod.GET)
	public byte[] printEvent(@PathVariable("id") long eventId, @PathVariable("document") Document document) throws IOException {
		Event event = eventRepository.findById(eventId).get();
		return printService.print(event, document);
	}
	
	@RequestMapping(path = "/search", method = RequestMethod.GET, produces="application/json")
	public List<Event> findEvent(@RequestParam(value="date", required=false) String dateString, 
			@RequestParam(value="criminalActType", required=false) String criminalActType) {
		Date date;
		if(StringUtils.isBlank(dateString) && StringUtils.isBlank(criminalActType)) {
			return new ArrayList<>();
		}
		if(StringUtils.isBlank(dateString)) {
			date = null;
		} else {
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			try {
				date = df.parse(dateString);
			} catch (ParseException e) {
				throw new RuntimeException(e);
			}
		}
		
		if(StringUtils.isBlank(criminalActType)) {
			criminalActType = null;
		} else {
			criminalActType = criminalActType.toUpperCase();
		}

		java.sql.Date startSqlDate = new java.sql.Date(date.getTime());
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, 1);
		java.sql.Date endSqlDate = new java.sql.Date(cal.getTimeInMillis());
		List<Event> events = eventRepository.search(startSqlDate, endSqlDate, criminalActType);
		for (Event event : events) {
			event.getPersons().size();
			event.getVehicles().size();
			event.getWeapons().size();
		}
		
		return events;
		
		
	}
	
}
