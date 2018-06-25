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

import com.bytepoet.undp.platformdemo.domain.Weapon;
import com.bytepoet.undp.platformdemo.repositories.WeaponRepository;

@RestController
@RequestMapping("/weapons")
public class WeaponController {

	@Autowired
	private WeaponRepository weaponRepository;
	
	@RequestMapping(path = {"","/"}, method = RequestMethod.POST, consumes="application/json", produces="application/json")
	public Weapon savePerson(@RequestBody Weapon person) {
		return weaponRepository.save(person);
	}
	
	@RequestMapping(path = {"/{id}"}, method = RequestMethod.GET, consumes="application/json", produces="application/json")
	public Weapon getWeapon(@PathVariable(value="id") long id) {
		return weaponRepository.findById(id).get();
	}
	
	@RequestMapping(path = "/search", method = RequestMethod.GET, produces="application/json")
	public List<Weapon> findWeapon(@RequestParam(value="model", required=false) String model, 
			@RequestParam(value="manufacturer", required=false) String manufacturer,
			@RequestParam(value="idNumber", required=false) String idNumber) {
		
		if(StringUtils.isBlank(model) && StringUtils.isBlank(manufacturer) && StringUtils.isBlank(idNumber)) {
			return new ArrayList<>();
		}
		if(StringUtils.isBlank(model)) {
			model = null;
		}
		if(StringUtils.isBlank(manufacturer)) {
			manufacturer = null;
		}
		if(StringUtils.isBlank(idNumber)) {
			idNumber = null;
		}
		
		return weaponRepository.search(idNumber, manufacturer, model);
	}
	
}
