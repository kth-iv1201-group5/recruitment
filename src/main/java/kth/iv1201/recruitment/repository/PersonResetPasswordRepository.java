package kth.iv1201.recruitment.repository;

import kth.iv1201.recruitment.entity.ResetPasswordToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;

@Repository
public interface PersonResetPasswordRepository extends JpaRepository<ResetPasswordToken, Integer> {

	/**
	 * Find entity by token.
	 *
	 * @param token client token
	 *
	 * @return Object of ResetPasswordToken entity
	 */
	@Query(value = "SELECT OBJECT(t) FROM ResetPasswordToken t WHERE t.token = :token")
	ResetPasswordToken findByToken(String token);

	/**
	 * Update the expire date in entity with token.
	 *
	 * @param token       client token
	 * @param currentTime current time
	 */
	@Modifying
	@Query(value = "UPDATE ResetPasswordToken t SET t.expireDate = :currentTime WHERE t.token = :token ")
	void updateExpireDate(String token, Timestamp currentTime);
}
