package kth.iv1201.recruitment.repository;

import kth.iv1201.recruitment.entity.Availability;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AvailabilityRepository extends JpaRepository<Availability, Integer> {
	@Query(value = "SELECT OBJECT(a) FROM Availability a WHERE a.personId = ?1")
	List<Availability> findAllByPersonId(Integer person_id);
}
