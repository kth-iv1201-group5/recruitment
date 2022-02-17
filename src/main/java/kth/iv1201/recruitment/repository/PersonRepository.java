package kth.iv1201.recruitment.repository;

import kth.iv1201.recruitment.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * This object is used for communicating with the database.
 * It uses the JPARepository as extension as it provides a better ORM to the developer for fetching code.
 */
@Repository
@Transactional
public interface PersonRepository extends JpaRepository<Person, Integer> {

	/**
	 * This method is for fetching user by their input of username and password.
	 * This is used for authenticate the user, when user is entering the username and password.
	 *
	 * @param username User entered username.
	 * @param password User entered password.
	 * @return Person object which may contain information if found or return empty if the query was not successfully.
	 */
	@Query(value = "SELECT OBJECT(p) FROM Person p WHERE p.username = ?1 AND p.password = ?2")
	Person findByUsernameAndPassword(String username, String password);

	@Query(value = "SELECT p FROM Person p WHERE p.username = :username")
	public Person getUserByUsername(@Param("username") String username);
}
