package kth.iv1201.recruitment.service;

import kth.iv1201.recruitment.config.CustomPasswordEncoder;
import kth.iv1201.recruitment.entity.ChangePasswordForm;
import kth.iv1201.recruitment.entity.Person;
import kth.iv1201.recruitment.entity.ResetPasswordToken;
import kth.iv1201.recruitment.entity.Role;
import kth.iv1201.recruitment.repository.PersonRepository;
import kth.iv1201.recruitment.repository.ResetPasswordRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

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
	private final ResetPasswordRepository resetPasswordRepository;
	private final CustomPasswordEncoder passwordEncoder;

	/**
	 * Construction injection.
	 * <p>This is our way to inject dependencies to the object. This form of injection is called constructor injection.
	 * Instead of using <code>@Autowired</code>, field inject, which is a security cons. We are relying on construction
	 * inject for better testability and immutability.</p>
	 *
	 * @param personRepository Construction injection from <code>PersonRepository</code>
	 */
	public PersonService(PersonRepository personRepository, ResetPasswordRepository resetPasswordRepository,
	                     CustomPasswordEncoder passwordEncoder) {
		this.personRepository = personRepository;
		this.resetPasswordRepository = resetPasswordRepository;
		this.passwordEncoder = passwordEncoder;
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

	/**
	 * Creates data with person entity and token saved to database.
	 *
	 * @param email User email.
	 *
	 * @return ResetPasswordToken object with person and token.
	 */
	@Transactional(isolation = Isolation.SERIALIZABLE)
	public ResetPasswordToken createNewPasswordForUser(String email) {
		logger.info("Fetching user by email");
		Person person = personRepository.findByEmail(email);
		String token = UUID.randomUUID().toString();
		ResetPasswordToken resetPasswordToken = new ResetPasswordToken(person, token);
		logger.info("Saving ResetPasswordToken object with person and token.");
		resetPasswordRepository.save(resetPasswordToken);
		logger.info("Saved ResetPasswordToken object.");
		return resetPasswordToken;
	}

	/**
	 * Check if email is taken.
	 *
	 * @param person Person entity.
	 *
	 * @return Checks if the email is taken.
	 */
	@Transactional(isolation = Isolation.SERIALIZABLE)
	public boolean isEmailTaken(Person person) {
		return personRepository.findByEmail(person.getEmail()) != null;
	}

	/**
	 * Check if username is taken
	 *
	 * @param person Person entity.
	 *
	 * @return Checks if the username is taken.
	 */
	@Transactional(isolation = Isolation.SERIALIZABLE)
	public boolean isUsernameTaken(Person person) {
		return personRepository.findByUsername(person.getUsername()) != null;
	}

	/**
	 * Creates a new account.
	 *
	 * @param person Person
	 *
	 * @return new account.
	 */
	@Transactional(isolation = Isolation.SERIALIZABLE)
	public Person createAccount(Person person) {
		person.setRole(new Role(2, "applicant"));
		int idNumber = Math.toIntExact(personRepository.count()) + 1;
		person.setId(idNumber);
		Person temp = new Person(person);
		temp.updatePassword(passwordEncoder.encode(person.getPassword()));
		personRepository.saveAndFlush(temp);
		return person;
	}

	/**
	 * Validate entered token.
	 *
	 * @param token Client entered token
	 *
	 * @return boolean if token is valid.
	 */
	@Transactional(isolation = Isolation.SERIALIZABLE)
	public boolean validateToken(String token) {
		Timestamp currentDateAndTime = new Timestamp(System.currentTimeMillis());
		return resetPasswordRepository.isValidToken(token, currentDateAndTime);
	}

	/**
	 * Save new entered password to database, with encrypted with BCrypt password encoder.
	 *
	 * @param form  Fulfilled form with password
	 * @param token Form token.
	 */
	@Transactional(isolation = Isolation.SERIALIZABLE)
	public void saveNewPassword(ChangePasswordForm form, String token) {
		logger.info("Fetching ResetPasswordRepository by token.");
		Person person = resetPasswordRepository.findByToken(token);
		logger.info("Update person with new encrypted password.");
		personRepository.updateWithNewPassword(person.getId(),
				passwordEncoder.encode(form.getPassword()));
		logger.info("Update token in ResetPasswordRepository.");
		resetPasswordRepository.updateExpireDate(token);
		logger.info("Transaction complete.");
	}
}
