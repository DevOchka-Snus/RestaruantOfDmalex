package com.dmalex.ordermanagementsystem.security;

import com.dmalex.ordermanagementsystem.domain.Person;
import com.dmalex.ordermanagementsystem.domain.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PersonDetailsFactory {
    public PersonDetails create(final Person person) {
        return new PersonDetails(
                person.getLogin(),
                person.getPassword(),
                mapToGrantedAuthorities(person.getRole())
        );
    }

    private List<GrantedAuthority> mapToGrantedAuthorities(Role role) {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }
}
