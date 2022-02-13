package kth.iv1201.recruitment.repository;

import kth.iv1201.recruitment.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface PersonRepository extends JpaRepository<Person, Integer> {

	@Query(value = "SELECT OBJECT(p) FROM Person p WHERE p.username = ?1 AND p.password = ?2")
	Person findByUsernameAndPassword(String username, String password);
}
