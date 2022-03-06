package kth.iv1201.recruitment.service;

import kth.iv1201.recruitment.entity.Availability;
import kth.iv1201.recruitment.repository.AvailabilityRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * This is our service layer for fetching information from <code>Availability</code> database layer.
 *
 * <p>
 * Transaction is made for every method calls. Rollbacks, are made when a exception is caught or thrown. Propagation,
 * Spring suspends the current transaction if it exists, and then creates a new one.
 * </p>
 */
@Service
@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
public class AvailabilityService {

	private static final Logger logger = LoggerFactory.getLogger(AvailabilityService.class);
	private final AvailabilityRepository availabilityRepository;

	/**
	 * Construction injection.
	 * <p>This is our way to inject dependencies to the object. This form of injection is called constructor injection.
	 * Instead of using <code>@Autowired</code>, field inject, which is a security cons. We are relying on construction
	 * inject for better testability and immutability.</p>
	 *
	 * @param availabilityRepository Repository which can fetch data from database.
	 */
	public AvailabilityService(AvailabilityRepository availabilityRepository) {
		this.availabilityRepository = availabilityRepository;
	}

	/**
	 * Returns a list which can be filled with found availabilities or empty list.
	 *
	 * <p>
	 * The transaction is only made on transactions that are already committed. The isolation level is the highest
	 * level
	 * which prevents the concurrency side effects.
	 * </p>
	 *
	 * @param id Person id.
	 *
	 * @return list of availabilities.
	 */
	@Transactional(isolation = Isolation.SERIALIZABLE)
	public List<Availability> findAllByPersonId(Integer id) {
		logger.info("Server is fetching availabilities from database.");
		logger.info("Server successfully fetched availabilities from database.");
		return availabilityRepository.findAllByPersonId(id);
	}
}
