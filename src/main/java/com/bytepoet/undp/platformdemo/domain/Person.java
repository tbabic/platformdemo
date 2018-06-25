package com.bytepoet.undp.platformdemo.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Cacheable(false)
public class Person {

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	private String idNumber;
	private String passportId;
	

	private String firstName;
	private String lastName;	
	
	private String parentFirstName;
	
	private Date birthDate;
	private String birthPlace;
	
	private String city;
	private String street;
	private String streetNumber;
	private String county;
	private Gender gender;
	
	private String citizenship;
	
	private String indicator;
	
	@ManyToMany(mappedBy="persons")
	@JsonIgnore
	private List<Event> events;
	
	
	
	public enum Gender {
		MALE, FEMALE;
	}
	

	public long getId() {
		return id;
	}



	public void setId(long id) {
		this.id = id;
	}



	public String getPassportId() {
		return passportId;
	}



	public void setPassportId(String passportId) {
		this.passportId = passportId;
	}



	public String getParentFirstName() {
		return parentFirstName;
	}



	public void setParentFirstName(String parentFirstName) {
		this.parentFirstName = parentFirstName;
	}



	public String getBirthPlace() {
		return birthPlace;
	}



	public void setBirthPlace(String birthPlace) {
		this.birthPlace = birthPlace;
	}



	public String getCity() {
		return city;
	}



	public void setCity(String city) {
		this.city = city;
	}



	public String getStreet() {
		return street;
	}



	public void setStreet(String street) {
		this.street = street;
	}



	public String getStreetNumber() {
		return streetNumber;
	}



	public void setStreetNumber(String streetNumber) {
		this.streetNumber = streetNumber;
	}



	public String getCounty() {
		return county;
	}



	public void setCounty(String county) {
		this.county = county;
	}



	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}



	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}



	public void setLastName(String lastName) {
		this.lastName = lastName;
	}



	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}



	public String getIdNumber() {
		return idNumber;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public Date getBirthDate() {
		return birthDate;
	}



	public Gender getGender() {
		return gender;
	}



	public void setGender(Gender gender) {
		this.gender = gender;
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



	public String getCitizenship() {
		return citizenship;
	}



	public void setCitizenship(String citizenship) {
		this.citizenship = citizenship;
	}
	
	
	
}
