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

import com.bytepoet.undp.platformdemo.domain.Person;
import com.bytepoet.undp.platformdemo.repositories.PersonRepository;

@RestController
@RequestMapping("/persons")
public class PersonController {

	@Autowired
	private PersonRepository personRepository;
	
	@RequestMapping(path = {"","/"}, method = RequestMethod.POST, consumes="application/json", produces="application/json")
	public Person savePerson(@RequestBody Person person) {
		return personRepository.save(person);
	}
	
	@RequestMapping(path = {"/{id}"}, method = RequestMethod.GET, consumes="application/json", produces="application/json")
	public Person getPerson(@PathVariable(value="id") long id) {
		return personRepository.findById(id).get();
	}
	
	@RequestMapping(path = "/search", method = RequestMethod.GET, produces="application/json")
	public List<Person> findPerson(@RequestParam(value="firstName", required=false) String firstName, 
			@RequestParam(value="lastName", required=false) String lastName,
			@RequestParam(value="idNumber", required=false) String idNumber) {
		
		if(StringUtils.isBlank(firstName) && StringUtils.isBlank(lastName) && StringUtils.isBlank(idNumber)) {
			return new ArrayList<>();
		}
		if(StringUtils.isBlank(firstName)) {
			firstName = null;
		}
		if(StringUtils.isBlank(lastName)) {
			lastName = null;
		}
		if(StringUtils.isBlank(idNumber)) {
			idNumber = null;
		}
		
		
		return personRepository.search(firstName, lastName, idNumber);
		
		
	}
	
}
