package kth.iv1201.recruitment.service;

import kth.iv1201.recruitment.entity.Person;
import kth.iv1201.recruitment.entity.ResetPasswordToken;
import kth.iv1201.recruitment.model.SendMail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * This is our service layer for sending email to user.
 *
 * <p>
 * Transaction is made for every method calls. Rollbacks, are made when a exception is caught or thrown. Propagation,
 * Spring suspends the current transaction if it exists, and then creates a new one.
 * </p>
 */
@Service
@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
public class EmailService {

	private final Logger logger = LoggerFactory.getLogger(EmailService.class);
	private final PersonService personService;

	@Value(value = "${smtp.from.user}")
	private String userName;
	@Value(value = "${smtp.from.password}")
	private String password;

	/**
	 * Constructor for sending email.
	 *
	 * @param personService Person service usage.
	 */
	public EmailService(PersonService personService) {
		this.personService = personService;
	}

	/**
	 * Sending a email to user via user input with link to page to change password.
	 *
	 * @param email User email.
	 * @param url   Current URL with DNS and path
	 *
	 * @return true if successful or false if failed.
	 */
	@Transactional(isolation = Isolation.SERIALIZABLE)
	public boolean sendNewPasswordRequest(String email, String url) {
		SendMail mail = new SendMail(userName, password);
		try {
			logger.info("Starting SMTP service...");
			ResetPasswordToken resetPasswordToken = personService.createNewPasswordForUser(email);
			mail.sendEmailOfNewPassword(resetPasswordToken.getPerson().getEmail(), resetPasswordToken.getToken(), url);
		} catch (Exception e) {
			logger.error("Failed to send email.");
			return false;
		}
		logger.info("Transaction is complete!");
		return true;
	}

	/**
	 * Send email about newly created account
	 *
	 * @param person Person entity.
	 */
	public void sendNewAccount(Person person) {
		SendMail mail = new SendMail(userName, password);
		try {
			mail.sendEmailOfNewAccount(person.getEmail(), person.getUsername());
		} catch (Exception e) {
			logger.error("Failed to send email.");
		}
	}
}
