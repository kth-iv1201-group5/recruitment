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

	public Person toPerson() {
		return new Person(firstname, lastname, ssn, email, password, username);
	}

	public boolean isPasswordValid() {
		return password.equals(rePassword);
	}

	private boolean isValidLength(String text) {
		return text.length() >= 2 && text.length() <= 30;
	}

	public boolean isFirstNameLengthValid() {
		return isValidLength(firstname);
	}

	public boolean isLastNameValid() {
		return isValidLength(lastname);
	}

	public boolean isUsernameValid() {
		return isValidLength(username);
	}

	public boolean isEmailValid() {
		return email.contains("@");
	}

	public boolean isSSNValid() {
		return ssn.matches("^\\d{6}(?:\\d{2})?[-\\s]?\\d{4}$");
	}

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
