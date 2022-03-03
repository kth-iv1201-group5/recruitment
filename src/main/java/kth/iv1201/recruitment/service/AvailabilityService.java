package kth.iv1201.recruitment.service;

import kth.iv1201.recruitment.entity.Availability;
import kth.iv1201.recruitment.repository.AvailabilityRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * This is our service layer for fetching information from <code>Availability</code> database layer.
 */
@Service
public class AvailabilityService {

	private static final Logger logger = LoggerFactory.getLogger(AvailabilityService.class);
	private final AvailabilityRepository availabilityRepository;

	/**
	 * Construction injection.
	 * <p>This is our way to inject dependencies to the object. This form of injection is called constructor injection.
	 * Instead of using <code>@Autowired</code>, field inject, which is a security cons. We are relying on construction inject for better testability and immutability.</p>
	 *
	 * @param availabilityRepository Repository which can fetch data from database.
	 */
	public AvailabilityService(AvailabilityRepository availabilityRepository) {
		this.availabilityRepository = availabilityRepository;
	}

	/**
	 * Returns a list which can be filled with found availabilities or empty list.
	 *
	 * @param id Person id.
	 * @return list of availabilities.
	 */
	public List<Availability> findAllByPersonId(Integer id) {
		logger.info("Server is fetching availabilities from database.");
		logger.info("Server successfully fetched availabilities from database.");
		return availabilityRepository.findAllByPersonId(id);
	}
}
