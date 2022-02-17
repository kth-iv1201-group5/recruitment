package kth.iv1201.recruitment.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "competence_profile")
public class CompetenceProfile {
	@Id
	@Column(name = "competence_profile_id")
	private Integer id;
	@Column(name = "person_id")
	private Integer personId;
	@OneToOne
	@JoinColumn(name = "competence_id")
	private Competence competence;
	@Column(name = "years_of_experience")
	private Double yearOfExperience;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}
