package kth.iv1201.recruitment.service;

import kth.iv1201.recruitment.entity.Availability;
import kth.iv1201.recruitment.entity.CompetenceProfile;
import kth.iv1201.recruitment.entity.Person;
import kth.iv1201.recruitment.entity.Summary;
import kth.iv1201.recruitment.repository.AvailabilityRepository;
import kth.iv1201.recruitment.repository.CompetenceRepository;
import kth.iv1201.recruitment.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SummaryService {

	private final PersonRepository personRepository;
	private final CompetenceRepository competenceRepository;
	private final AvailabilityRepository availabilityRepository;

	public SummaryService(PersonRepository personRepository, CompetenceRepository competenceRepository, AvailabilityRepository availabilityRepository) {
		this.personRepository = personRepository;
		this.competenceRepository = competenceRepository;
		this.availabilityRepository = availabilityRepository;
	}

	public Summary getSummaryOfPersonBy(Integer person_id) {
		Person person = personRepository.findById(person_id).isPresent() ? personRepository.getById(person_id) : new Person();
		List<Availability> availabilities = availabilityRepository.findAllByPersonId(person_id);
		List<CompetenceProfile> competences = competenceRepository.findAllByPersonId(person_id);
		Summary summary = new Summary(person_id, person, availabilities, competences);
		return summary;
	}
}
