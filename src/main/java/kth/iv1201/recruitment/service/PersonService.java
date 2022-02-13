package kth.iv1201.recruitment.service;

import kth.iv1201.recruitment.dbhandler.DBHandler;
import kth.iv1201.recruitment.entity.Person;
import org.postgresql.util.PSQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Service
public class PersonService {

	@Autowired
	private DBHandler dbHandler;

	public Person authenticate(String username, String password) {
		try {
			PreparedStatement statement = dbHandler.prepareStatement("SELECT * FROM Person e WHERE e.username = ? AND e.password = ?;");
			statement.setString(1, username);
			statement.setString(2, password);
			ResultSet result = statement.executeQuery();
			Person person = new Person();
			if (result.wasNull()) {
				return new Person();
			}
			result.next();
			person.setId(result.getInt("person_id"));
			person.setName(result.getString("name"));
			person.setSurname(result.getString("surname"));
			person.setPnr(result.getString("pnr"));
			person.setEmail(result.getString("email"));
			person.setPassword(result.getString("password"));
			person.setRoleId(result.getInt("role_id"));
			person.setUsername(result.getString("username"));
			return person;
		} catch (PSQLException e) {
			System.out.println(e.getMessage()); // TODO Add better exception.
			return new Person();
		} catch (SQLException e) {
			System.out.println(e.getMessage()); // TODO Add better exception.
			return new Person();
		}
	}

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

