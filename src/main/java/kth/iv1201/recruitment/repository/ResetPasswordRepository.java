package kth.iv1201.recruitment.repository;

import kth.iv1201.recruitment.entity.Person;
import kth.iv1201.recruitment.entity.ResetPasswordToken;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.Timestamp;

@Repository
public class ResetPasswordRepository {

	private final PersonResetPasswordRepository personResetPasswordRepository;
	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * Constructor injecting personResetPasswordRepository
	 *
	 * @param personResetPasswordRepository Constructor injecting
	 */
	public ResetPasswordRepository(PersonResetPasswordRepository personResetPasswordRepository) {
		this.personResetPasswordRepository = personResetPasswordRepository;
	}

	/**
	 * Save object to database, token and person_id
	 *
	 * @param resetPasswordToken contains token and person entity.
	 */
	public void save(ResetPasswordToken resetPasswordToken) {
		entityManager.createNativeQuery("INSERT INTO public.person_reset_token (token, person_id) VALUES (?, ?)").setParameter(1, resetPasswordToken.getToken()).setParameter(2, resetPasswordToken.getPerson().getId()).executeUpdate();
	}

	/**
	 * Check if token is valid
	 *
	 * @param token   client token.
	 * @param current current timestamp
	 *
	 * @return Boolean status if timestamp is valid.
	 */
	public boolean isValidToken(String token, Timestamp current) {
		ResetPasswordToken resetPasswordToken = personResetPasswordRepository.findByToken(token);
		return current.before(resetPasswordToken.getExpireDate());
	}

	/**
	 * Find Person from PersonResetPassword Table by token.
	 *
	 * @param token current token.
	 *
	 * @return Person entity.
	 */
	public Person findByToken(String token) {
		return personResetPasswordRepository.findByToken(token).getPerson();
	}

	/**
	 * Update ExpireDate
	 *
	 * @param token current token.
	 */
	public void updateExpireDate(String token) {
		personResetPasswordRepository.updateExpireDate(token, new Timestamp(System.currentTimeMillis()));
	}
}
