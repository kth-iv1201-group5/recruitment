package kth.iv1201.recruitment.entity;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

/**
 * Person entity Which contains information of Person object. It has annotation of <code>@Entity, @Data,
 *
 * @NoArgsConstructor, @AllArgsConstructor, @Builder, @Table</code> for better and consist readability.
 */
@Entity
@Data
@Builder
@Table(name = "person")
public class Person {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "person_id")
	private Integer id;
	private String name;
	private String surname;
	private String pnr;
	private String email;
	private String password;
	@OneToOne
	@JoinColumn(name = "role_id")
	private Role role;
	private String username;

	public Person() {
	}

	public Person(String name, String surname, String pnr, String email, String password, String username) {
		this.name = name;
		this.surname = surname;
		this.pnr = pnr;
		this.email = email;
		this.password = password;
		this.username = username;
	}

	public Person(Integer id, String name, String surname, String pnr, String email, String password, Role role,
	              String username) {
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.pnr = pnr;
		this.email = email;
		this.password = password;
		this.role = role;
		this.username = username;
	}

	public Person(Person person) {
		this.id = person.getId();
		this.name = person.getName();
		this.surname = person.getSurname();
		this.pnr = person.getPnr();
		this.email = person.getEmail();
		this.password = person.getPassword();
		this.role = person.getRole();
		this.username = person.getUsername();
	}

	public String getPnr() {
		return pnr;
	}

	public String getSurname() {
		return surname;
	}

	public String getName() {
		return name;
	}

	/**
	 * Returns the password of current object.
	 *
	 * @return String of password.
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Returns the username of current object.
	 *
	 * @return String of name and appended surname.
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Returns the full name of user.
	 *
	 * @return String of name and appended surname.
	 */
	public String getFullName() {
		return name + " " + surname;
	}

	/**
	 * Returns the email of user.
	 *
	 * @return String of email address.
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Updates the users password
	 *
	 * @param password Parameter used for updating object users password.
	 */
	public void updatePassword(String password) {
		this.password = password;
	}

	/**
	 * Returns the id of user.
	 *
	 * @return Integer of user id.
	 */
	public int getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * Returns the role of user.
	 *
	 * @return Role object of user.
	 */
	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
}
