package kth.iv1201.recruitment.repository;

import kth.iv1201.recruitment.entity.CompetenceProfile;
import kth.iv1201.recruitment.entity.CompetenceTranslation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * This object is used for communicating with the database. It uses the JPARepository as extension as it provides a
 * better ORM to the developer for fetching code.
 */
@Repository
public interface CompetenceRepository extends JpaRepository<CompetenceProfile, Integer> {
	/**
	 * This method is for fetching competence profile by their person id. This will list all possible competence
	 * profile
	 * in from the database
	 *
	 * @param id Fetching information with id
	 *
	 * @return List of competence profile.
	 */
	@Query(value = "SELECT OBJECT(c) FROM CompetenceProfile c WHERE c.personId = ?1")
	List<CompetenceProfile> findAllByPersonId(Integer id);

	/**
	 * Select the swedish translation of competence.
	 *
	 * @param id Competence id
	 *
	 * @return Translated competence
	 */
	@Query(value = "SELECT OBJECT(c) FROM CompetenceTranslation c WHERE c.id = :id and c.language = 'sv'")
	CompetenceTranslation findByLanguageSV(Integer id);
}
