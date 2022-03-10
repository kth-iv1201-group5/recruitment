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
 * Competence entity with translated names.
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "competence_translation")
public class CompetenceTranslation {
	@Id
	@Column(name = "competence_id")
	private Integer id;
	private String language;
	private String name;

	/**
	 * Return competence object with the translated text.
	 *
	 * @return new object of competence with translated values.
	 */
	public Competence toCompetence() {
		return new Competence(id, name);
	}
}
