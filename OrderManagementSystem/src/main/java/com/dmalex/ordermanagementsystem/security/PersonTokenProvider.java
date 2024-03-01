package com.dmalex.ordermanagementsystem.security;

import com.dmalex.ordermanagementsystem.domain.Role;
import com.dmalex.ordermanagementsystem.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PersonTokenProvider {
    private final UserDetailsService userDetailsService;
    private final PersonService personService;

    public String createPersonToken(final String login, final String password, final Role role) {
        return login + "_" + password + "_" + role;
    }

    public boolean isValidToken(final String token) {
        String[] args = token.split("_");
        if (args.length != 3 || !personService.isExists(args[0], args[1], Role.valueOf(args[2]))) {
            return false;
        }
        return true;
    }

    public Authentication getAuthentication(final String token) {
        String login = token.split("_")[0];
        UserDetails userDetails = userDetailsService.loadUserByUsername(login);
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }
}
