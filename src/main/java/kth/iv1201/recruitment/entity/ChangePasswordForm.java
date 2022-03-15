package kth.iv1201.recruitment.entity;

import lombok.*;

import javax.validation.constraints.NotNull;

@ToString
@NoArgsConstructor
@Setter
@Getter
@Data
public class ChangePasswordForm {

	@NotNull
	private final String token;

	@NotNull
	private String password;

	@NotNull
	private String rePassword;

	/**
	 * Constructor for form
	 *
	 * @param token current token for form.
	 */
	public ChangePasswordForm(String token) {
		this.token = token;
	}

	/**
	 * Validate if password is the same as re-entered password.
	 *
	 * @return true if equal otherwise false.
	 */
	public boolean isPasswordValid() {
		return password.equals(rePassword);
	}

	/**
	 * Returns password of form.
	 *
	 * @return password of current form.
	 */
	public String getPassword() {
		return password;
	}
}
