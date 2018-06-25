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
public class Weapon {

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private String idNumber;
	private String manufacturer;
	private String model;
	private String type;
	private String weaponClass;
	private String originCountry;
	private String calibar;
	private String otherIdAttributes;
	
	@ManyToOne
	@JoinColumn(name="owner_id")
	private Person owner;
	
	@ManyToMany(mappedBy="weapons")
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getWeaponClass() {
		return weaponClass;
	}
	public void setWeaponClass(String weaponClass) {
		this.weaponClass = weaponClass;
	}
	public String getOriginCountry() {
		return originCountry;
	}
	public void setOriginCountry(String originCountry) {
		this.originCountry = originCountry;
	}
	public String getCalibar() {
		return calibar;
	}
	public void setCalibar(String calibar) {
		this.calibar = calibar;
	}
	public String getOtherIdAttributes() {
		return otherIdAttributes;
	}
	public void setOtherIdAttributes(String otherIdAttributes) {
		this.otherIdAttributes = otherIdAttributes;
	}
	
	
	
}
