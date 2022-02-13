package kth.iv1201.recruitment.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Person entity
 * Which contains information of Person object.
 * It has annotation of <code>@Entity, @Data, @NoArgsConstructor, @AllArgsConstructor, @Builder, @Table</code> for better and consist readability.
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "person")
public class Person {
	@Id
	@Column(name = "person_id")
	private Integer id;
	private String name;
	private String surname;
	private String pnr;
	private String email;
	private String password;
	@Column(name = "role_id")
	private Integer role;
	private String username;

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
	 * @return String of username.
	 */
	public String getUsername() {
		return username;
	}
}
