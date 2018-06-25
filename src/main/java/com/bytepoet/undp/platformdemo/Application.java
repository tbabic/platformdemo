package com.bytepoet.undp.platformdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
//	@Bean
//	public CommandLineRunner initPersons(PersonRepository repository) {
//		return (args) -> {
//			// save a couple of customers
////			DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
////			
////			
//			
//			Person person = new Person();
//			person.setFirstName("Ante");
//			person.setLastName("Antić");
//			
//			repository.save(person);
//		};
//	}
//	
//	@Bean
//	public CommandLineRunner initVehicles(VehicleRepository repository) {
//		return (args) -> {
//		
//			
//			Vehicle vehicle = new Vehicle();
//			vehicle.setRegistrationNumber("AB-123-C");
//			vehicle.setIdNumber("V123");
//			vehicle.setManufacturer("Audi");
//			vehicle.setModel("A");
//			vehicle.setOwnerId(1L);
//			
//			repository.save(vehicle);
//		};
//	}
//	
//	@Bean
//	public CommandLineRunner initWeapons(WeaponRepository repository) {
//		return (args) -> {
//
//			Weapon weapon = new Weapon();
//			weapon.setIdNumber("W123");
//			weapon.setManufacturer("HK");
//			weapon.setModel("A");
//			weapon.setOwnerId(1L);
//			
//			repository.save(weapon);
//		};
//	}
//	
//	@Bean
//	public CommandLineRunner initEvent(EventRepository repository) {
//		return (args) -> {
//
//			Event event = new Event();
//			event.setLocation("lokacija");
//			event.setLocation("lokacijaIme");
//			Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
//			cal.set(Calendar.HOUR_OF_DAY, 0);
//			cal.set(Calendar.MINUTE, 0);
//			cal.set(Calendar.SECOND, 0);
//			cal.set(Calendar.MILLISECOND, 0);
//			
//			event.setDate(cal.getTime());
//			event.setCriminalActType("krađa");
//			event.setOrganizationalUnit("ustrojstvenaJedinica");
//			
//			repository.save(event);
//		};
//	}
}
