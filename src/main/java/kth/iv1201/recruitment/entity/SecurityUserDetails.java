package kth.iv1201.recruitment.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;

/**
 * User details used by Security layer.
 */
public class SecurityUserDetails implements UserDetails {

	private final Person person;

	/**
	 * Constructor
	 *
	 * @param person Entity of Person.
	 */
	public SecurityUserDetails(Person person) {
		this.person = person;
	}

	/**
	 * Collection of authorities.
	 *
	 * @return collection of authorities
	 */
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		SimpleGrantedAuthority authority = new SimpleGrantedAuthority("1");
		return Arrays.asList(authority);
	}

	/**
	 * Return password of the user.
	 *
	 * @return Return password of the user.
	 */
	@Override
	public String getPassword() {
		return person.getPassword();
	}

	/**
	 * Return username of the user.
	 *
	 * @return Return username of the user.
	 */
	@Override
	public String getUsername() {
		return person.getUsername();
	}

	/**
	 * Default value to the account being non expired.
	 *
	 * @return true
	 */
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	/**
	 * Default value to the account being non locked.
	 *
	 * @return true
	 */
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	/**
	 * Default value to the credentials being non expired.
	 *
	 * @return true
	 */
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	/**
	 * Enabling...
	 *
	 * @return true
	 */
	@Override
	public boolean isEnabled() {
		return true;
	}
}