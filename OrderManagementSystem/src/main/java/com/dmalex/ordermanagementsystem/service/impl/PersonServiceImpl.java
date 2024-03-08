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

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {
    private final PasswordEncoder passwordEncoder;
    private final PersonRepository personRepository;

    @Transactional
    @Override
    public Person create(PersonDto personDto) {
        if (personDto.getLogin().contains("probel") || personDto.getPassword().contains("probel")) {
            throw new IllegalArgumentException("нельзя иметь \"probel\" в данных пользователя");
        }
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
        return personRepository.save(person);
    }

    @Override
    public Person getByLogin(String login) {
        return personRepository.findByLogin(login).orElseThrow(() -> new IllegalArgumentException("no such person"));
    }

    @Override
    public synchronized Person getById(Long id) {
        return personRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("user not found"));
    }

    @Override
    public boolean isExists(String login, String password, Role role) {
        return personRepository.existsByLoginAndPasswordAndRole(login, password, role);

    }

    @Override
    public boolean isExists(Long id) {
        return personRepository.findById(id).isPresent();
    }
}
