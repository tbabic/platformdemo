package com.bytepoet.undp.platformdemo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

	@RequestMapping("/")
	public String index() {
		return "index";
	}
	
	@RequestMapping("/pretraga/dogadjaji")
	public String searchEvent() {
		return "search/event.html";
	}
	
	@RequestMapping("/pretraga/osobe")
	public String searchPerson() {
		return "search/person.html";
	}
	
	@RequestMapping("/pretraga/vozila")
	public String searchVehicle() {
		return "search/vehicle.html";
	}
	
	@RequestMapping("/pretraga/oruzja")
	public String searchWeapon() {
		return "search/weapon.html";
	}
	
	@RequestMapping("/unos/dogadjaji")
	public String insertEvent() {
		return "insert/event.html";
	}
	
	@RequestMapping("/unos/osobe")
	public String insertPerson() {
		return "insert/person.html";
	}
	
	@RequestMapping("/unos/vozila")
	public String insertVehicle() {
		return "insert/vehicle.html";
	}
	
	@RequestMapping("/unos/oruzja")
	public String insertWeapon() {
		return "insert/weapon.html";
	}
	
	@RequestMapping("/pregled/dogadjaj/*")
	public String viewEvent() {
		return "view/event.html";
	}
	
	@RequestMapping("/pregled/osoba/*")
	public String viewPerson() {
		return "view/person.html";
	}
	
	@RequestMapping("/pregled/vozilo/*")
	public String viewVehicle() {
		return "view/vehicle.html";
	}
	
	@RequestMapping("/pregled/oruzje/*")
	public String viewWeapon() {
		return "view/weapon.html";
	}
}
