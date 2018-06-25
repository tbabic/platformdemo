package com.bytepoet.undp.platformdemo.controllers;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bytepoet.undp.platformdemo.domain.Vehicle;
import com.bytepoet.undp.platformdemo.repositories.VehicleRepository;

@RestController
@RequestMapping("/vehicles")
public class VehicleController {

	@Autowired
	private VehicleRepository vehicleRepository;
	
	@RequestMapping(path = {"","/"}, method = RequestMethod.POST, consumes="application/json", produces="application/json")
	public Vehicle savePerson(@RequestBody Vehicle person) {
		return vehicleRepository.save(person);
	}
	
	@RequestMapping(path = {"/{id}"}, method = RequestMethod.GET, consumes="application/json", produces="application/json")
	public Vehicle getVehicle(@PathVariable(value="id") long id) {
		return vehicleRepository.findById(id).get();
	}
	
	@RequestMapping(path = "/search", method = RequestMethod.GET, produces="application/json")
	public List<Vehicle> findVehicle(@RequestParam(value="registrationCountry", required=false) String registrationCountry, 
			@RequestParam(value="registrationNumber", required=false) String registrationNumber,
			@RequestParam(value="idNumber", required=false) String idNumber) {
		
		if(StringUtils.isBlank(registrationCountry) && StringUtils.isBlank(registrationNumber) && StringUtils.isBlank(idNumber)) {
			return new ArrayList<>();
		}
		if(StringUtils.isBlank(registrationCountry)) {
			registrationCountry = null;
		}
		if(StringUtils.isBlank(registrationNumber)) {
			registrationNumber = null;
		}
		if(StringUtils.isBlank(idNumber)) {
			idNumber = null;
		}
		
		return vehicleRepository.search(registrationCountry, registrationNumber, idNumber);
	}
	
}
