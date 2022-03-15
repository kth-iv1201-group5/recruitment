package kth.iv1201.recruitment.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "person_reset_token")
public class ResetPasswordToken {
	@Id
	private Integer id;
	private String token;

	@OneToOne(targetEntity = Person.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "person_id", nullable = false)
	private Person person;

	@Column(name = "expire_date")
	private Timestamp expireDate;

	/**
	 * Constructor with empty arguments
	 */
	public ResetPasswordToken() {
	}

	/**
	 * Constructor with person and token arguments.
	 *
	 * @param person Person entity.
	 * @param token  Form token.
	 */
	public ResetPasswordToken(Person person, String token) {
		this.person = person;
		this.token = token;
	}

	/**
	 * @return current person entity.
	 */
	public Person getPerson() {
		return person;
	}

	/**
	 * @return current token.
	 */
	public String getToken() {
		return token;
	}

	/**
	 * @return the expire timestamp
	 */
	public Timestamp getExpireDate() {
		return expireDate;
	}
}
