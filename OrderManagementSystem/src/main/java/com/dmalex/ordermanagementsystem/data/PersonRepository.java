package com.dmalex.ordermanagementsystem.data;

import com.dmalex.ordermanagementsystem.domain.Person;
import com.dmalex.ordermanagementsystem.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Long> {
    Optional<Person> findByLogin(final String login);
    Boolean existsByLoginAndPasswordAndRole(final String login, final String password, final Role role);
}
