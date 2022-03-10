package kth.iv1201.recruitment.service;

import kth.iv1201.recruitment.entity.Person;
import kth.iv1201.recruitment.entity.SecurityUserDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * This is our service layer for fetching information from <code>User details</code> at the security layer.
 */
@Service
public class SecurityUserDetailsService implements UserDetailsService {

	private final Logger logger = LoggerFactory.getLogger(SecurityUserDetailsService.class);
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
		logger.info("User is trying to authenticate");
		HttpServletRequest request =
				((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		Person person = personService.findByUsername(username);
		if (person == null) {
			logger.error("User failed to authenticate. Found nothing on the username.");
			throw new UsernameNotFoundException("Could not find user");
		}
		if (!new BCryptPasswordEncoder(10).matches(request.getParameter("password"), person.getPassword())) {
			logger.error("User failed to authenticate.");
			throw new UsernameNotFoundException("Wrong password");
		}
		logger.info("User data is found.");
		return new SecurityUserDetails(person);
	}
}
