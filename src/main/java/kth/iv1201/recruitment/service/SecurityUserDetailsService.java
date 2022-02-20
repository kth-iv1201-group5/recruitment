package kth.iv1201.recruitment.service;

import kth.iv1201.recruitment.entity.Person;
import kth.iv1201.recruitment.entity.SecurityUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SecurityUserDetailsService implements UserDetailsService {

	/**
	 * Autowired service of person service class.
	 */
	@Autowired
	PersonService personService;

	/**
	 * Load User by Username
	 *
	 * @param username User entered username.
	 *
	 * @return new instance of Security User Details.
	 *
	 * @throws UsernameNotFoundException Username could not be found.
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Person person = personService.findByUsername(username);
		if (person == null) {
			throw new UsernameNotFoundException("Could not find user");
		}
		return new SecurityUserDetails(person);
	}
}
