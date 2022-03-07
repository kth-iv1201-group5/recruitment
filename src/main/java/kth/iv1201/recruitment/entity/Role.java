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
 * Availability entity Which contains information of Availability object. It has annotation of <code>@Entity, @Data,
 *
 * @NoArgsConstructor, @AllArgsConstructor, @Builder, @Table</code> for better and consist readability.
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "role")
public class Role {
	@Id
	@Column(name = "role_id")
	private Integer id;
	private String name;

	/**
	 * Returns name of role.
	 *
	 * @return String of role name.
	 */
	public String getName() {
		return name;
	}
}
