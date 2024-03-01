package com.dmalex.ordermanagementsystem.service.impl;

import com.dmalex.ordermanagementsystem.data.PersonRepository;
import com.dmalex.ordermanagementsystem.domain.Person;
import com.dmalex.ordermanagementsystem.domain.Role;
import com.dmalex.ordermanagementsystem.service.PersonService;
import com.dmalex.ordermanagementsystem.web.dto.PersonDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {
    private final PasswordEncoder passwordEncoder;
    private final PersonRepository personRepository;

    @Transactional
    @Override
    public Person create(PersonDto personDto) {
        if (personRepository.findByLogin(personDto.getLogin()).isPresent()) {
            throw new IllegalArgumentException("person already exists");
        }
        if (!personDto.getPassword().equals(personDto.getPasswordConfirmation())) {
            throw new IllegalStateException("password and password confirmation do not match");
        }
        Person person = new Person();
        person.setLogin(personDto.getLogin());
        person.setPassword(passwordEncoder.encode(personDto.getPassword()));
        person.setRole(personDto.getRole());
        personRepository.save(person);
        return person;
    }

    @Override
    public Person getByLogin(String login) {
        return personRepository.findByLogin(login).orElseThrow(() -> new IllegalArgumentException("no such person"));
    }

    @Override
    public Person getById(Long id) {
        return personRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("user not found"));
    }

    @Override
    public boolean isExists(String login, String password, Role role) {
        return personRepository.existsByLoginAndPasswordAndRole(login, passwordEncoder.encode(password), role);
    }

    @Override
    public boolean isExists(Long id) {
        return personRepository.findById(id).isPresent();
    }
}
