package com.dmalex.ordermanagementsystem.web.security;

import com.dmalex.ordermanagementsystem.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PersonDetailsService implements UserDetailsService {
    private final PersonDetailsFactory personDetailsFactory;
    private final PersonService personService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var person = personService.getByLogin(username);
        return personDetailsFactory.create(person);
    }
}
