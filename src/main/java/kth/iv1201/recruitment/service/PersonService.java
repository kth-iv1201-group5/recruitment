package kth.iv1201.recruitment.service;

import kth.iv1201.recruitment.entity.Person;
import kth.iv1201.recruitment.repository.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * This is our service layer for fetching information from <code>Person</code> database layer.
 *
 * <p>
 * Transaction is made for every method calls. Rollbacks, are made when a exception is caught or thrown. Propagation,
 * Spring suspends the current transaction if it exists, and then creates a new one.
 * </p>
 */
@Service
@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
public class PersonService {

	private static final Logger logger = LoggerFactory.getLogger(PersonService.class);
	private final PersonRepository personRepository;

	/**
	 * Construction injection.
	 * <p>This is our way to inject dependencies to the object. This form of injection is called constructor injection.
	 * Instead of using <code>@Autowired</code>, field inject, which is a security cons. We are relying on construction
	 * inject for better testability and immutability.</p>
	 *
	 * @param personRepository Construction injection from <code>PersonRepository</code>
	 */
	public PersonService(PersonRepository personRepository) {
		this.personRepository = personRepository;
	}

	/**
	 * Authenticate the user with parameter of username and password coming from the forms.
	 * <p>This method makes use of <code>PersonRepository</code> to fetch information from the database.</p>
	 *
	 * <p>
	 * The transaction is only made on transactions that are already committed. The isolation level is the highest
	 * level
	 * which prevents the concurrency side effects.
	 * </p>
	 *
	 * @param username Username of user.
	 * @param password Password of user
	 *
	 * @return Upon successful it will return a object with filled fields and if not successful it will return empty
	 * object.
	 */
	@Transactional(isolation = Isolation.SERIALIZABLE)
	public Person authenticate(String username, String password) {
		return personRepository.findByUsernameAndPassword(username, password);
	}

	/**
	 * Return Person information if found or return empty object.
	 *
	 * <p>
	 * The transaction is only made on transactions that are already committed. The isolation level is the highest
	 * level
	 * which prevents the concurrency side effects.
	 * </p>
	 *
	 * @param id Person Id.
	 *
	 * @return Information of person.
	 */
	@Transactional(isolation = Isolation.SERIALIZABLE)
	public Person findById(Integer id) {
		logger.info("Server is fetching user from database.");
		if (!personRepository.existsById(id)) {
			logger.error("User by ID <" + id + "> is not found.");
			return new Person();
		}
		logger.info("Server successfully fetched user from database.");
		return personRepository.getById(id);
	}

	/**
	 * Find all possible applicants in database.
	 *
	 * @return List of applicants.
	 */
	@Transactional(isolation = Isolation.SERIALIZABLE)
	public List<Person> findAllApplicants() {
		logger.info("Server is fetching users from database.");
		logger.info("Server successfully fetched users from database.");
		return personRepository.findAllApplicants();
	}

	/**
	 * Fetch person information by entered username.
	 *
	 * <p>
	 * The transaction is only made on transactions that are already committed. The isolation level is the highest
	 * level
	 * which prevents the concurrency side effects.
	 * </p>
	 *
	 * @param username User entered username.
	 *
	 * @return Person information.
	 */
	@Transactional(isolation = Isolation.SERIALIZABLE)
	public Person findByUsername(String username) {
		logger.info("Server is fetching user from database.");
		Person person = personRepository.findByUsername(username);
		if (person == null) {
			logger.error("Failed to find user by username <" + username + ">.");
			return new Person();
		}
		logger.info("Server successfully fetched user from database.");
		return person;
	}
}
