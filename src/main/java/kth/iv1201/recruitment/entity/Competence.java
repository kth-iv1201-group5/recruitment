package kth.iv1201.recruitment.entity;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Competence entity Which contains information of Competence object. It has annotation of <code>@Entity, @Data,
 *
 * @NoArgsConstructor, @AllArgsConstructor, @Builder, @Table</code> for better and consist readability.
 */
@Entity
@Data
@Builder
@Table(name = "competence")
public class Competence {
	@Id
	@Column(name = "competence_id")
	private Integer id;
	private String name;

	/**
	 * Empty constructor
	 */
	public Competence() {
	}

	/**
	 * Constructor with all attributes.
	 *
	 * @param id   Competence id
	 * @param name Competence name
	 */
	public Competence(Integer id, String name) {
		this.id = id;
		this.name = name;
	}

	/**
	 * Return competence id
	 *
	 * @return id.
	 */
	public Integer getId() {
		return id;
	}
}
