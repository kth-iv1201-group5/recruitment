package kth.iv1201.recruitment.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.ui.Model;

import javax.validation.constraints.NotNull;

@ToString
@NoArgsConstructor
@Setter
@Getter
public class SignUpForm {

	@NotNull
	private String firstname;

	@NotNull
	private String lastname;

	@NotNull
	//@Pattern(regexp = "^\\d{6}(?:\\d{2})?[-\\s]?\\d{4}$", message = "{signup.msg.firstname}")
	private String ssn;

	@NotNull
	private String email;

	@NotNull
	private String username;

	@NotNull
	private String password;

	@NotNull
	private String rePassword;

	/**
	 * Convert the form to an Person entity.
	 *
	 * @return new Object of Person entity.
	 */
	public Person toPerson() {
		return new Person(firstname, lastname, ssn, email, password, username);
	}

	/**
	 * Validate if password is the same as re-entered password.
	 *
	 * @return true if equal otherwise false.
	 */
	private boolean isPasswordValid() {
		return password.equals(rePassword);
	}

	private boolean isValidLength(String text) {
		return text.length() >= 2 && text.length() <= 30;
	}

	/**
	 * Check if first name has a valid length.
	 *
	 * @return First name has valid length.
	 */
	private boolean isFirstNameLengthValid() {
		return isValidLength(firstname);
	}

	/**
	 * Check if last name has a valid length.
	 *
	 * @return Last name has valid length.
	 */
	private boolean isLastNameValid() {
		return isValidLength(lastname);
	}

	/**
	 * Check if username has a valid length.
	 *
	 * @return Username has valid length.
	 */
	private boolean isUsernameValid() {
		return isValidLength(username);
	}

	/**
	 * Does the email contain necessary characters.
	 *
	 * @return Is the email a valid input.
	 */
	private boolean isEmailValid() {
		return email.matches(".+\\@.+\\..+");
	}

	/**
	 * Regex validation for social security number.
	 *
	 * @return Checks if the ssn is valid.
	 */
	private boolean isSSNValid() {
		return ssn.matches("^\\d{6}(?:\\d{2})?[-\\s]?\\d{4}$");
	}

	/**
	 * Validate form.
	 *
	 * @param model Used for storing the invalid input form..
	 *
	 * @return Check if the form is valid.
	 */
	public boolean validate(Model model) {
		boolean isValid = true;
		if (!isFirstNameLengthValid()) {
			isValid = false;
			model.addAttribute("invalidFirstNameLength", true);
		}
		if (!isLastNameValid()) {
			isValid = false;
			model.addAttribute("invalidLastNameLength", true);
		}
		if (!isUsernameValid()) {
			isValid = false;
			model.addAttribute("invalidUsernameLength", true);
		}
		if (!isEmailValid()) {
			isValid = false;
			model.addAttribute("invalidEmail", true);
		}
		if (!isSSNValid()) {
			isValid = false;
			model.addAttribute("invalidSSN", true);
		}
		if (!isPasswordValid()) {
			isValid = false;
			model.addAttribute("invalidPassword", true);
		}
		return isValid;
	}
}
