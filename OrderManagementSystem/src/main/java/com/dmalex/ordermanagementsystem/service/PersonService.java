package com.dmalex.ordermanagementsystem.service;

import com.dmalex.ordermanagementsystem.domain.Person;
import com.dmalex.ordermanagementsystem.domain.Role;
import com.dmalex.ordermanagementsystem.web.dto.PersonDto;

public interface PersonService {
    Person create(final PersonDto personDto);
    Person getByLogin(final String login);
    Person getById(final Long id);

    boolean isExists(final String login, final String password, final Role role);
    boolean isExists(final Long id);
}
