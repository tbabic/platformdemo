package com.bytepoet.undp.platformdemo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.bytepoet.undp.platformdemo.domain.Vehicle;

public interface VehicleRepository extends CrudRepository<Vehicle, Long> {

	@Query("SELECT v FROM Vehicle v \n"
			+ "WHERE (upper(v.registrationCountry) LIKE upper(CAST (?1 as string)) OR ?1 = null) \n"
			+ "AND (upper(v.registrationNumber) LIKE upper(CAST (?2 as string)) OR ?2 = null) \n"
			+ "AND (upper(v.idNumber) LIKE upper(CAST (?3 as string)) OR ?3 = null)")
	public List<Vehicle> search(String registrationContry, String registrationNumber, String idNumber);
	
	
}
