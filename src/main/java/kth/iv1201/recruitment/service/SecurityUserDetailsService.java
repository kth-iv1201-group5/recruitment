package kth.iv1201.recruitment.service;

import kth.iv1201.recruitment.entity.Person;
import kth.iv1201.recruitment.entity.SecurityUserDetails;
import kth.iv1201.recruitment.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SecurityUserDetailsService implements UserDetailsService {

    @Autowired
    PersonRepository personRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Person person = personRepository.getUserByUsername(username);

        if (person == null) {
            throw new UsernameNotFoundException("Could not find user");
        }

        return new SecurityUserDetails(person);
    }
}
