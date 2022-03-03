package kth.iv1201.recruitment.service;

import kth.iv1201.recruitment.entity.CompetenceProfile;
import kth.iv1201.recruitment.repository.CompetenceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * This is our service layer for fetching information from <code>Competence</code> database layer.
 */
@Service
public class CompetenceService {
	private static final Logger logger = LoggerFactory.getLogger(CompetenceService.class);
	private final CompetenceRepository competenceRepository;

	/**
	 * Construction injection.
	 * <p>This is our way to inject dependencies to the object. This form of injection is called constructor injection.
	 * Instead of using <code>@Autowired</code>, field inject, which is a security cons. We are relying on construction inject for better testability and immutability.</p>
	 *
	 * @param competenceRepository Repository which can fetch data from database.
	 */
	public CompetenceService(CompetenceRepository competenceRepository) {
		this.competenceRepository = competenceRepository;
	}

	/**
	 * Returns a list which can be filled with found competence profile or empty list.
	 *
	 * @param id Person id.
	 * @return list of competence profiles.
	 */
	public List<CompetenceProfile> findAllByPersonId(Integer id) {
		logger.info("Server is fetching competences profiles from database.");
		logger.info("Server successfully fetched competences profiles from database.");
		return competenceRepository.findAllByPersonId(id);
	}
}
