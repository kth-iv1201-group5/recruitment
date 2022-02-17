package kth.iv1201.recruitment.repository;

import kth.iv1201.recruitment.entity.CompetenceProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompetenceRepository extends JpaRepository<CompetenceProfile, Integer> {
	@Query(value = "SELECT OBJECT(c) FROM CompetenceProfile c WHERE c.personId = ?1")
	List<CompetenceProfile> findAllByPersonId(Integer person_id);
}
