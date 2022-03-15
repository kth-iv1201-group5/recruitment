package kth.iv1201.recruitment.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CustomPasswordEncoder {

	@Value(value = "${password.encoder.strength}")
	private int BCRYPT_PASSWORD_STRENGTH;

	/**
	 * @return initialized password encoder with a hidden strength.
	 */
	public PasswordEncoder init() {
		return new BCryptPasswordEncoder(BCRYPT_PASSWORD_STRENGTH);
	}

	/**
	 * Encodes the incoming raw password.
	 *
	 * @param password Raw password which wants to be encoded.
	 *
	 * @return the encoded password.
	 */
	public String encode(String password) {
		return init().encode(password);
	}

	/**
	 * Checks if the raw password is matching the encoded password.
	 *
	 * @param rawPassword     Client written password
	 * @param encodedPassword Database encoded password
	 *
	 * @return Boolean value of the matched password.
	 */
	public boolean matches(String rawPassword, String encodedPassword) {
		return init().matches(rawPassword, encodedPassword);
	}
}
