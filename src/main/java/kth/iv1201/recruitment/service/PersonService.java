package kth.iv1201.recruitment.service;

import kth.iv1201.recruitment.entity.Person;
import kth.iv1201.recruitment.repository.PersonRepository;
import org.springframework.stereotype.Service;

/**
 * This is our service layer for fetching information from <code>Person</code> database layer.
 */
@Service
public class PersonService {

	private final PersonRepository personRepository;

	/**
	 * Construction injection.
	 * <p>This is our way to inject dependencies to the object. This form of injection is called constructor injection.
	 * Instead of using <code>@Autowired</code>, field inject, which is a security cons. We are relying on construction inject for better testability and immutability.</p>
	 *
	 * @param personRepository Construction injecton from <code>PersonRepository</code>
	 */
	public PersonService(PersonRepository personRepository) {
		this.personRepository = personRepository;
	}

	/**
	 * Authenticate the user with parameter of username and password coming from the forms.
	 * <p>This method makes use of <code>PersonRepository</code> to fetch information from the database.</p>
	 *
	 * @param username Username of user.
	 * @param password Password of user
	 * @return Upon successful it will return a object with filled fields and if not successful it will return empty object.
	 */
	public Person authenticate(String username, String password) {
		return personRepository.findByUsernameAndPassword(username, password);
	}

	/**
	 * Return Person information if found or return empty object.
	 *
	 * @param id Person Id.
	 * @return Information of person.
	 */
	public Person findById(Integer id) {
		return personRepository.findById(id).isPresent() ? personRepository.getById(id) : new Person();
	}

	/**
   * TODO CHANGE THIS
	 * This method returns a list of all applicants
	 * @return ArrayList<Person>
	 */
	public ArrayList<Person>listApplicants() {

		ArrayList<Person> applicants = new ArrayList<Person>();

		try {
			PreparedStatement statement = dbHandler.prepareStatement("Select * from person where role_id =2");
			ResultSet result = statement.executeQuery();


			while (result.next()) {
				Person person = new Person();
				person.setId(result.getInt("person_id"));
				person.setName(result.getString("name"));
				person.setSurname(result.getString("surname"));
				person.setPnr(result.getString("pnr"));
				person.setEmail(result.getString("email"));
				person.setPassword(result.getString("password"));
				person.setUsername(result.getString("username"));
				applicants.add(person);
			}
		} catch (PSQLException e) {
			System.out.println(e.getMessage()); // TODO Add better exception.
			return applicants;

		} catch (SQLException e) {
			System.out.println(e.getMessage()); // TODO Add better exception.
			return applicants;
		}
		return applicants;
	}
	}

