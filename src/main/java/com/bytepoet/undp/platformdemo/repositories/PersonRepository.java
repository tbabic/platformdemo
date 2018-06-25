package com.bytepoet.undp.platformdemo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.bytepoet.undp.platformdemo.domain.Person;

public interface PersonRepository extends CrudRepository<Person, Long> {


	@Query("SELECT p FROM Person p \n"
			+ "WHERE (upper(p.firstName) LIKE upper(CAST (?1 as string)) OR ?1 = null) \n"
			+ "AND (upper(p.lastName) LIKE upper(CAST (?2 as string)) OR ?2 = null) \n"
			+ "AND (upper(p.idNumber) LIKE upper(CAST (?3 as string)) OR upper(p.passportId) LIKE upper(CAST (?3 as string)) OR ?3 = null)")
	public List<Person> search(String firstName, String lastName, String idNumberOrPassportId);
}
