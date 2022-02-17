package kth.iv1201.recruitment.entity;

import java.util.List;

public class Summary {
	private final Person person;
	private final List<Availability> availabilities;
	private final Integer id;
	private final List<CompetenceProfile> competences;


	public Summary(Integer id, Person person, List<Availability> availabilities, List<CompetenceProfile> competences) {
		this.id = id;
		this.person = person;
		this.availabilities = availabilities;
		this.competences = competences;
	}

	public Integer getId() {
		return id;
	}

	public Person getPerson() {
		return person;
	}

	public List<Availability> getAvailabilities() {
		return availabilities;
	}

	@Override
	public String toString() {
		return "Summary{" + "person=" + person.toString() + ", availabilities=" + availabilities.toString() + ", id=" + id + '}';
	}

	public List<CompetenceProfile> getCompetences() {
		return competences;
	}
}
