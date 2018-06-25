package com.bytepoet.undp.platformdemo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.bytepoet.undp.platformdemo.domain.Weapon;

public interface WeaponRepository extends CrudRepository<Weapon, Long> {

	@Query("SELECT w FROM Weapon w \n"
			+ "WHERE (upper(w.idNumber) LIKE upper(CAST (?1 as string)) OR ?1 = null) \n"
			+ "AND (upper(w.manufacturer) LIKE upper(CAST (?2 as string)) OR ?2 = null) \n"
			+ "AND (upper(w.model) LIKE upper(CAST (?3 as string)) OR ?3 = null)")
	public List<Weapon> search(String idNumber, String manufacturer, String model);
	
	
}
