package kth.iv1201.recruitment.entity;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Availability entity Which contains information of Availability object. It has annotation of <code>@Entity, @Data,
 *
 * @NoArgsConstructor, @AllArgsConstructor, @Builder, @Table</code> for better and consist readability.
 */
@Entity
@Data
@Builder
@Table(name = "role")
public class Role {
	@Id
	@Column(name = "role_id")
	private Integer id;
	private String name;

	public Role() {
	}

	public Role(Integer id, String name) {
		this.id = id;
		this.name = name;
	}

	/**
	 * Returns name of role.
	 *
	 * @return String of role name.
	 */
	public String getName() {
		return name;
	}

	public int getId() {
		return id;
	}
}
