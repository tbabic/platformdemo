package com.bytepoet.undp.platformdemo;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.bytepoet.undp.platformdemo.domain.Event;
import com.bytepoet.undp.platformdemo.domain.Person;
import com.bytepoet.undp.platformdemo.domain.Person.Gender;
import com.bytepoet.undp.platformdemo.domain.Vehicle;
import com.bytepoet.undp.platformdemo.domain.Weapon;
import com.bytepoet.undp.platformdemo.repositories.EventRepository;
import com.bytepoet.undp.platformdemo.repositories.PersonRepository;
import com.bytepoet.undp.platformdemo.repositories.VehicleRepository;
import com.bytepoet.undp.platformdemo.repositories.WeaponRepository;

@SpringBootApplication
public class Application {

	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@Bean
	public CommandLineRunner initPersons(PersonRepository repository) {
		return (args) -> {
			// save a couple of customers
//			DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
//			
//			
			
			Person person = new Person();
			person.setFirstName("Ante");
			person.setLastName("Antić");
			person.setIdNumber("1234567890");
			person.setPassportId("0987654321");
			person.setParentFirstName("Otokar");
			person.setCity("Grad");
			person.setStreet("Ulica");
			person.setBirthDate(new Date(1000*3600*24*10));
			person.setStreetNumber("11");
			person.setCitizenship("Hrvatsko");
			person.setGender(Gender.MALE);
			person.setBirthPlace("RodMjesto");
			person.setCounty("Okrug");
			repository.save(person);
		};
	}
	
	@Bean
	public CommandLineRunner initVehicles(VehicleRepository repository) {
		return (args) -> {
		
			
			Vehicle vehicle = new Vehicle();
			vehicle.setRegistrationNumber("AB-123-C");
			vehicle.setIdNumber("V123");
			vehicle.setManufacturer("Audi");
			vehicle.setModel("A");
			vehicle.setOwnerId(1L);
			vehicle.setRegistrationCountry("Hrvatska");
			
			repository.save(vehicle);
		};
	}
	
	@Bean
	public CommandLineRunner initWeapons(WeaponRepository repository) {
		return (args) -> {

			Weapon weapon = new Weapon();
			weapon.setIdNumber("W123");
			weapon.setManufacturer("HK");
			weapon.setModel("A");
			weapon.setOwnerId(1L);
			weapon.setCalibar("25mm");
			weapon.setType("pištolj");
			weapon.setOriginCountry("Hrvatska");
			weapon.setWeaponClass("KLasa");
			
			repository.save(weapon);
		};
	}
	
	@Bean
	public CommandLineRunner initEvent(EventRepository repository) {
		return (args) -> {

			Event event = new Event();
			event.setLocation("lokacija");
			event.setLocation("lokacijaIme");
			Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
			cal.set(Calendar.HOUR_OF_DAY, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			cal.set(Calendar.MILLISECOND, 0);
			
			event.setDate(cal.getTime());
			event.setCriminalActType("kraÄ‘a");
			event.setOrganizationalUnit("ustrojstvenaJedinica");
			
			repository.save(event);
		};
	}
}
