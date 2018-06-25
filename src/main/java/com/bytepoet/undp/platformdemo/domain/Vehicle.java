package com.bytepoet.undp.platformdemo.domain;

import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Cacheable(false)
public class Vehicle {

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private String idNumber;
	private String registrationNumber;
	private String registrationCountry;
	private String manufacturer;
	private String model;
	@ManyToOne
	@JoinColumn(name="owner_id")
	private Person owner;
	@ManyToMany(mappedBy="vehicles")
	@JsonIgnore
	private List<Event> events;
	
	private String indicator;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getIdNumber() {
		return idNumber;
	}
	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}
	public String getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public Person getOwner() {
		return owner;
	}
	public void setOwner(Person owner) {
		this.owner = owner;
	}
	public String getRegistrationNumber() {
		return registrationNumber;
	}
	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}
	public String getRegistrationCountry() {
		return registrationCountry;
	}
	public void setRegistrationCountry(String registrationCountry) {
		this.registrationCountry = registrationCountry;
	}
	@Transient
	public String getOwnerFullName() {
		if (owner == null) {
			return null;
		}
		return owner.getFirstName() + " " + owner.getLastName();
	}
	@Transient
	public Long getOwnerId() {
		return owner != null ?owner.getId() : null;
	}
	public void setOwnerId(Long ownerId) {
		if (ownerId == null) {
			return;
		}
		if (owner == null) {
			owner = new Person();
		}
		owner.setId(ownerId);
	}
	

	public boolean isEventExists() {
		return events != null && events.size() > 0;
	}
	public String getIndicator() {
		return indicator;
	}
	public void setIndicator(String indicator) {
		this.indicator = indicator;
	}
	
	
}
