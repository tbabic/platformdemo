package com.bytepoet.undp.platformdemo.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
@Cacheable(false)
public class Event {
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private long number;
	private String location;
	private String locationName;
	private Date date;
	private String address;
	private ArrivingType arrivingType;
	private String gpsLatitude;
	private String gpsLongitude;
	private String criminalActType;
	private String organizationalUnit;
	
	private String description;
	
	
	@ManyToMany
	@JoinTable(
		    name = "event_person", 
		    joinColumns = { @JoinColumn(name = "eventId") }, 
		    inverseJoinColumns = { @JoinColumn(name = "personId") })
	private List<Person> persons;
	
	@ManyToMany
	@JoinTable(
		    name = "event_vehicle", 
		    joinColumns = { @JoinColumn(name = "eventId") }, 
		    inverseJoinColumns = { @JoinColumn(name = "vehicleId") })
	private List<Vehicle> vehicles;
	
	@ManyToMany
	@JoinTable(
		    name = "event_weapon", 
		    joinColumns = { @JoinColumn(name = "eventId") }, 
		    inverseJoinColumns = { @JoinColumn(name = "weaponId") })
	private List<Weapon> weapons;
	
	public enum ArrivingType {
		IN, OUT;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public ArrivingType getArrivingType() {
		return arrivingType;
	}

	public void setArrivingType(ArrivingType arrivingType) {
		this.arrivingType = arrivingType;
	}

	public String getGpsLatitude() {
		return gpsLatitude;
	}

	public void setGpsLatitude(String gpsLatitude) {
		this.gpsLatitude = gpsLatitude;
	}

	public String getGpsLongitude() {
		return gpsLongitude;
	}

	public void setGpsLongitude(String gpsLongitude) {
		this.gpsLongitude = gpsLongitude;
	}

	public String getOrganizationalUnit() {
		return organizationalUnit;
	}

	public void setOrganizationalUnit(String organizationalUnit) {
		this.organizationalUnit = organizationalUnit;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCriminalActType() {
		return criminalActType;
	}

	public void setCriminalActType(String criminalActType) {
		this.criminalActType = criminalActType;
	}

	public List<Person> getPersons() {
		return persons;
	}

	public void setPersons(List<Person> persons) {
		this.persons = persons;
	}

	public List<Vehicle> getVehicles() {
		return vehicles;
	}

	public void setVehicles(List<Vehicle> vehicles) {
		this.vehicles = vehicles;
	}

	public List<Weapon> getWeapons() {
		return weapons;
	}

	public void setWeapons(List<Weapon> weapons) {
		this.weapons = weapons;
	}

	public long getNumber() {
		return number;
	}

	public void setNumber(long number) {
		this.number = number;
	}
	
}
