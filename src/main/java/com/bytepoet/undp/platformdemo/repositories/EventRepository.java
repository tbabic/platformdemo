package com.bytepoet.undp.platformdemo.repositories;


import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.bytepoet.undp.platformdemo.domain.Event;

public interface EventRepository extends CrudRepository<Event, Long> {

	@Query("SELECT e FROM Event e \n"
			+ "WHERE (e.date = ?1 OR CAST(?1 as date) = null) \n"
			+ "AND (upper(e.criminalActType) LIKE upper(CAST (?2 as string)) OR ?2 = null)")
	public List<Event> search(Date date, String criminalActType);
	
	@Query("SELECT e FROM Event e \n"
			+ "WHERE (e.date >= ?1 OR CAST(?1 as date) = null) \n"
			+ "AND (e.date < ?2 OR CAST(?2 as date) = null) \n"
			+ "AND (upper(e.criminalActType) LIKE upper(CAST (?3 as string)) OR ?3 = null)")
	public List<Event> search(Date startDate, Date endDate, String criminalActType);
	
	@Query("SELECT e FROM Event e \n"
			+ "WHERE e.date = ?1")
	public List<Event> search(Date date);
	
}
