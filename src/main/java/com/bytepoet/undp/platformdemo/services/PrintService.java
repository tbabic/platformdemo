package com.bytepoet.undp.platformdemo.services;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.stereotype.Service;

import com.bytepoet.undp.platformdemo.controllers.Document;
import com.bytepoet.undp.platformdemo.domain.Event;
import com.bytepoet.undp.platformdemo.domain.Event.ArrivingType;
import com.bytepoet.undp.platformdemo.domain.Person;
import com.bytepoet.undp.platformdemo.domain.Person.Gender;
import com.bytepoet.undp.platformdemo.domain.Vehicle;
import com.bytepoet.undp.platformdemo.domain.Weapon;

@Service
public class PrintService {

	public byte[] print(Event event, Document document) throws IOException {
		if (document == Document.FORM) {
			return printForm(event);
		}
		if (document == Document.ZOP) {
			return printZop(event);
		}
		if (document == Document.ZKP) {
			return printZkp(event);
		}
		if (document == Document.CRIM) {
			return printCrim(event);
		}
		throw new RuntimeException("Unrecognized document");
	}
	
	private byte[] printCrim(Event event) throws IOException {
		ClassLoader classLoader = getClass().getClassLoader();
		InputStream istream = classLoader.getResourceAsStream(Document.CRIM.getFileName());
		XWPFDocument docx = new XWPFDocument(istream);
		
		List<TextReplacer> replacers = Arrays.asList(
				new TextReplacer("<<Ustrojstvena jedinica>>", event.getOrganizationalUnit()),
				new TextReplacer("<<broj>>", event.getNumber()),
				new TextReplacer("<<Datum>>", event.getDate()),
				new TextReplacer("<<prezime>>", event.getPersons().get(0).getLastName()),
				new TextReplacer("<<ime>>", event.getPersons().get(0).getFirstName()),
				new TextReplacer("<<datum rođenja>>", event.getPersons().get(0).getBirthDate()),
				new TextReplacer("<<mjesto rodenja>>", event.getPersons().get(0).getBirthPlace()),
				new TextReplacer("<<roditelj>>", event.getPersons().get(0).getParentFirstName()),
				new TextReplacer("<<mjesto stanovanja>>", event.getPersons().get(0).getCity()),
				new TextReplacer("<<ulica>>", event.getPersons().get(0).getStreet()),
				new TextReplacer("<<ulbroj>>", event.getPersons().get(0).getStreetNumber()),
				new TextReplacer("<<opstina>>", event.getPersons().get(0).getCounty()),
				new TextReplacer("<<broj pi>>", event.getPersons().get(0).getPassportId()),
				new TextReplacer("<<jmbg>>", event.getPersons().get(0).getIdNumber()),
				new TextReplacer("<<NAPOMENA>>", event.getDescription())
				);
		
		replacers.stream().forEach(r -> r.replace(docx));

		
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		docx.write(outputStream);
		return outputStream.toByteArray();
	}
	
	private byte[] printZop(Event event) throws IOException {
		ClassLoader classLoader = getClass().getClassLoader();
		InputStream istream = classLoader.getResourceAsStream(Document.ZOP.getFileName());
		XWPFDocument docx = new XWPFDocument(istream);
		
		StringBuilder eventWeapons = new StringBuilder("");
		int idx = 0;
		for (Weapon weapon : event.getWeapons()) {
			idx++;
			eventWeapons.append(idx + ". ").append(". ");
			eventWeapons.append(weapon.getModel()).append(". ");
			eventWeapons.append(weapon.getManufacturer()).append(". ");
			eventWeapons.append(weapon.getType()).append(". ");
			eventWeapons.append(weapon.getIdNumber()).append("\n");
		}
		
		List<TextReplacer> replacers = Arrays.asList(
				new TextReplacer("<<ustrojstvena jedinica>>", event.getOrganizationalUnit()),
				new TextReplacer("<<broj>>", event.getNumber()),
				new TextReplacer("<<datum>>", event.getDate()),
				new TextReplacer("<<prezime>>", event.getPersons().get(0).getLastName()),
				new TextReplacer("<<ime>>", event.getPersons().get(0).getFirstName()),
				new TextReplacer("<<datum rođenja>>", event.getPersons().get(0).getBirthDate()),
				new TextReplacer("<<mjesto rodenja>>", event.getPersons().get(0).getBirthPlace()),
				new TextReplacer("<<roditelj>>", event.getPersons().get(0).getParentFirstName()),
				new TextReplacer("<<mjesto stanovanja>>", event.getPersons().get(0).getCity()),
				new TextReplacer("<<ulica>>", event.getPersons().get(0).getStreet()),
				new TextReplacer("<<ulbroj>>", event.getPersons().get(0).getStreetNumber()),
				new TextReplacer("<<opstina>>", event.getPersons().get(0).getCounty()),
				new TextReplacer("<<broj pi>>", event.getPersons().get(0).getPassportId()),
				new TextReplacer("<<jmbg>>", event.getPersons().get(0).getIdNumber()),
				new TextReplacer("<<rbr>>.  <<naziv>>  <<vrsta>>  <<tip>>  <<serijski_broj>>", eventWeapons.toString()),
				new TextReplacer("<<rbr>>", idx)
				);
		
		replacers.stream().forEach(r -> r.replace(docx));

		
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		docx.write(outputStream);
		return outputStream.toByteArray();
	}
	
	private byte[] printZkp(Event event) throws IOException {
		ClassLoader classLoader = getClass().getClassLoader();
		InputStream istream = classLoader.getResourceAsStream(Document.ZKP.getFileName());
		XWPFDocument docx = new XWPFDocument(istream);
		
		StringBuilder eventWeapons = new StringBuilder("");
		int idx = 0;
		for (Weapon weapon : event.getWeapons()) {
			idx++;
			eventWeapons.append(idx + ". ").append(". ");
			eventWeapons.append(weapon.getModel()).append(". ");
			eventWeapons.append(weapon.getManufacturer()).append(". ");
			eventWeapons.append(weapon.getType()).append(". ");
			eventWeapons.append(weapon.getIdNumber()).append("\n");
		}
		
		List<TextReplacer> replacers = Arrays.asList(
				new TextReplacer("<<ustrojstvena jedinica>>", event.getOrganizationalUnit()),
				new TextReplacer("<<broj>>", event.getNumber()),
				new TextReplacer("<<datum>>", event.getDate()),
				new TextReplacer("<<prezime>>", event.getPersons().get(0).getLastName()),
				new TextReplacer("<<ime>>", event.getPersons().get(0).getFirstName()),
				new TextReplacer("<<datum rođenja>>", event.getPersons().get(0).getBirthDate()),
				new TextReplacer("<<mjesto rodenja>>", event.getPersons().get(0).getBirthPlace()),
				new TextReplacer("<<roditelj>>", event.getPersons().get(0).getParentFirstName()),
				new TextReplacer("<<mjesto stanovanja>>", event.getPersons().get(0).getCity()),
				new TextReplacer("<<ulica>>", event.getPersons().get(0).getStreet()),
				new TextReplacer("<<ulbroj>>", event.getPersons().get(0).getStreetNumber()),
				new TextReplacer("<<opstina>>", event.getPersons().get(0).getCounty()),
				new TextReplacer("<<broj pi>>", event.getPersons().get(0).getPassportId()),
				new TextReplacer("<<jmbg>>", event.getPersons().get(0).getIdNumber()),
				new TextReplacer("<<rbr>>.  <<naziv>>  <<vrsta>>  <<tip>>  <<serijski_broj>>", eventWeapons.toString()),
				new TextReplacer("<<rbr>>", idx)
				);

		replacers.stream().forEach(r -> r.replace(docx));

		
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		docx.write(outputStream);
		return outputStream.toByteArray();
	}
	
	
	
	
	private byte[] printForm(Event event) throws IOException {
		ClassLoader classLoader = getClass().getClassLoader();
		InputStream istream = classLoader.getResourceAsStream(Document.FORM.getFileName());
		XWPFDocument docx = new XWPFDocument(istream);
		
		String arrivingType = event.getArrivingType() == null ? "" 
				:(event.getArrivingType() == ArrivingType.IN ? "Ulaz" : "Izlaz");
		
		Vehicle vehicle = new Vehicle();
		if (CollectionUtils.isNotEmpty(event.getVehicles())) {
			vehicle = event.getVehicles().get(0);
		}
		
		Person vehicleOwner = new Person();
		if (vehicle.getOwner() != null) {
			vehicleOwner = vehicle.getOwner();
		}
		
		Person weaponOwner = new Person();
		if (CollectionUtils.isNotEmpty(event.getWeapons()) && event.getWeapons().get(0).getOwner() != null) {
			weaponOwner = event.getWeapons().get(0).getOwner();
		}
		
		Person participant = new Person();
		if (CollectionUtils.isNotEmpty(event.getPersons())) {
			participant = event.getPersons().get(0);
		}
		
		List<TextReplacer> replacers = Arrays.asList(
				new TextReplacer("<<ustrojstvena jedinica>>", event.getOrganizationalUnit()),
				new TextReplacer("<<datum today>>", new Date()),
				new TextReplacer("<<broj predmeta>>", event.getNumber()),
				new TextReplacer("<<mjesto>>", event.getLocationName()),
				new TextReplacer("<<naziv>>", event.getWeapons().get(0).getModel()),
				new TextReplacer("<<vrsta>>", event.getWeapons().get(0).getWeaponClass()),
				new TextReplacer("<<proizvodac>>", event.getWeapons().get(0).getManufacturer()),
				new TextReplacer("<<tip>>", event.getWeapons().get(0).getType()),
				new TextReplacer("<<serijski broj>>", event.getWeapons().get(0).getIdNumber()),
				new TextReplacer("<<kalibar>>", event.getWeapons().get(0).getCalibar()),
				new TextReplacer("<<zemljaporijekla>>", event.getWeapons().get(0).getOriginCountry()),
				new TextReplacer("<<datum>>", event.getDate()),
				new TextReplacer("<<naziv naseljenog mjesta>>", event.getLocationName()),
				new TextReplacer("<<x>>", event.getGpsLongitude()),
				new TextReplacer("<<y>>", event.getGpsLatitude()),
				new TextReplacer("<ulaz/izlaz>>", arrivingType),
				new TextReplacer("<ul>", (event.getArrivingType() == ArrivingType.IN) ? "DA" : "NE"),
				new TextReplacer("<iz>", (event.getArrivingType() == ArrivingType.OUT) ? "DA" : "NE"),
				new TextReplacer("<<tip protupravnog djela>>", event.getCriminalActType()),
				new TextReplacer("<<broj predmenta>>", event.getId()),
				new TextReplacer("<<jmbg>>", weaponOwner.getIdNumber()),
				new TextReplacer("<<roditelj>>", weaponOwner.getParentFirstName()),
				new TextReplacer("<<ime>>", weaponOwner.getFirstName()),
				new TextReplacer("<<prezime>>", weaponOwner.getLastName()),
				new TextReplacer("<<spol>>", gender(weaponOwner)),
				new TextReplacer("<<mjesto>>", weaponOwner.getCity()),
				new TextReplacer("<<ulica>>", weaponOwner.getStreet()),
				new TextReplacer("<<ulbroj>>", weaponOwner.getStreetNumber()),
				new TextReplacer("<<datum rodenja>>", weaponOwner.getBirthDate()),
				new TextReplacer("<<drzavljanstvo>>", weaponOwner.getCitizenship()),
				new TextReplacer("<<broj_pi>>", weaponOwner.getPassportId()),
				new TextReplacer("<<marka>>", vehicle.getManufacturer()),
				new TextReplacer("<<tip>>", vehicle.getModel()),
				new TextReplacer("<<registracija>>", vehicle.getRegistrationNumber()),
				new TextReplacer("<<uin>>", vehicle.getIdNumber()),
				
				new TextReplacer("<<voziloime>>", vehicleOwner.getFirstName()),
				new TextReplacer("<<voziloprez>>", vehicleOwner.getLastName()),
				new TextReplacer("<<vozilombg>>", vehicleOwner.getIdNumber()),
				new TextReplacer("<<sudionikrod>>", vehicleOwner.getBirthDate()),
				new TextReplacer("<<vozilodrzv>>", vehicleOwner.getCitizenship()),
				new TextReplacer("<<vozilomjesto>>", vehicleOwner.getCity()),
				new TextReplacer("<<voziloulica>>", vehicleOwner.getStreet()),
				new TextReplacer("<<voziloulbr>>", vehicleOwner.getStreetNumber()),
				
				new TextReplacer("<<sudionikime>>", participant.getFirstName()),
				new TextReplacer("<<sudionikotac>>", participant.getParentFirstName()),
				new TextReplacer("<<sudionikprez>>", participant.getLastName()),
				new TextReplacer("<<sudionikmbg>>", participant.getIdNumber()),
				new TextReplacer("<<sudionikrod>>", participant.getBirthDate()),
				new TextReplacer("<<sudionikdrz>>", participant.getCitizenship()),
				new TextReplacer("<<sudionik_id>>", participant.getPassportId())
				);

		replacers.stream().forEach(r -> r.replace(docx));

		
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		docx.write(outputStream);
		return outputStream.toByteArray();
	}
	
	
	private String gender(Person person) {
		if (person.getGender() == Gender.MALE) {
			return "Muško";
		}
		if (person.getGender() == Gender.FEMALE) {
			return "Žensko";
		} else {
			return "Ostalo";
		}
	}
}
