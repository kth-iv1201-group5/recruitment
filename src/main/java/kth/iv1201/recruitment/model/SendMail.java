package kth.iv1201.recruitment.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

/**
 * Component use for sending email to client.
 */
@Component
public class SendMail {

	private Properties properties;
	private String userName;
	private String password;

	public SendMail() {
		initProperties();
	}

	/**
	 * Constructor for this object
	 */
	public SendMail(@Value(value = "${smtp.from.user}") String username,
	                @Value(value = "${smtp.from.password}") String password) {
		initProperties();
		this.userName = username;
		this.password = password;
	}

	public SendMail(String username) {
		initProperties();
		this.userName = username;
	}

	private void initProperties() {
		this.properties = new Properties();
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "587");
	}

	/**
	 * Sends a email with the new password.
	 *
	 * @param toEmail  Recipient
	 * @param password New password
	 *
	 * @throws Exception Failed to send a email.
	 */
	public void sendEmailOfNewPassword(String toEmail, String password) throws Exception {
		try {
			Message message = createNewPasswordRequestMessage(toEmail, password);
			Transport.send(message);
		} catch (MessagingException e) {
			throw new Exception("Creating email message was made wrong.");
		}
	}

	private Message createNewPasswordRequestMessage(String toEmail, String password) throws MessagingException {
		Message message = new MimeMessage(getSession());
		message.setFrom(new InternetAddress(userName, false));
		message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
		message.setSubject("[IV1201] Your password have been reset!");
		message.setContent("<p>You have accessed for a new password.</p>\n\n<p>Your new password is: " + "<b>" + password + "</b>.</p>" + "\n\n<p>Thank you for using our services.</p>", "text/html");
		message.setSentDate(new Date());
		return message;
	}

	private Session getSession() {
		return Session.getInstance(this.properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(userName, password);
			}
		});
	}

	public void sendEmailOfNewAccount(String toEmail, String username) throws Exception {
		try {
			Message message = createNewAccountMessage(toEmail, username);
			Transport.send(message);
		} catch (MessagingException e) {
			throw new Exception("Creating email message was made wrong.");
		}

	}

	private Message createNewAccountMessage(String toEmail, String username) throws MessagingException {
		Message message = new MimeMessage(getSession());
		message.setFrom(new InternetAddress(userName, false));
		message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
		message.setSubject("[IV1201] Welcome to Yellow Lund!");
		message.setContent("<h1>Thank you for your registration!</h1>" +
				"<p>You can now log in to the account with the username: <b>" + username + "</b></p>", "text/html");
		message.setSentDate(new Date());
		return message;
	}
}