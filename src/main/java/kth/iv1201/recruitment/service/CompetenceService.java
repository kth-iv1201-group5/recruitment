package kth.iv1201.recruitment.service;

import kth.iv1201.recruitment.entity.CompetenceProfile;
import kth.iv1201.recruitment.repository.CompetenceRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * This is our service layer for fetching information from <code>Competence</code> database layer.
 *
 * <p>
 * Transaction is made for every method calls. Rollbacks, are made when a exception is caught or thrown. Propagation,
 * Spring suspends the current transaction if it exists, and then creates a new one.
 * </p>
 */
@Service
@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
public class CompetenceService {
	private final CompetenceRepository competenceRepository;

	/**
	 * Construction injection.
	 * <p>This is our way to inject dependencies to the object. This form of injection is called constructor injection.
	 * Instead of using <code>@Autowired</code>, field inject, which is a security cons. We are relying on construction
	 * inject for better testability and immutability.</p>
	 *
	 * @param competenceRepository Repository which can fetch data from database.
	 */
	public CompetenceService(CompetenceRepository competenceRepository) {
		this.competenceRepository = competenceRepository;
	}

	/**
	 * Returns a list which can be filled with found competence profile or empty list.
	 *
	 * <p>
	 * The transaction is only made on transactions that are already committed. The isolation level is the highest
	 * level
	 * which prevents the concurrency side effects.
	 * </p>
	 *
	 * @param id Person id.
	 *
	 * @return list of competence profiles.
	 */
	@Transactional(isolation = Isolation.SERIALIZABLE)
	public List<CompetenceProfile> findAllByPersonId(Integer id) {
		return competenceRepository.findAllByPersonId(id);
	}
}
