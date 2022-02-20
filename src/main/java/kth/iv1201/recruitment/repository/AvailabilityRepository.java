package kth.iv1201.recruitment.repository;

import kth.iv1201.recruitment.entity.Availability;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * This object is used for communicating with the database.
 * It uses the JPARepository as extension as it provides a better ORM to the developer for fetching code.
 */
@Repository
public interface AvailabilityRepository extends JpaRepository<Availability, Integer> {
	/**
	 * This method is for fetching availabilities by their person id.
	 * This will list all possible availabilities in from the database
	 *
	 * @param id Fetching information with id
	 * @return List of availabilities.
	 */
	@Query(value = "SELECT OBJECT(a) FROM Availability a WHERE a.personId = ?1 ORDER BY a.toDate DESC")
	List<Availability> findAllByPersonId(Integer id);
}
