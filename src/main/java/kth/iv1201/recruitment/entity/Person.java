package kth.iv1201.recruitment.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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

	public String getPassword() {
		return password;
	}

	public String getUsername() {
		return username;
	}
}
