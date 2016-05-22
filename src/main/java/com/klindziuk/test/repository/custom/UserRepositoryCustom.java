package com.klindziuk.test.repository.custom;

import java.util.List;

import com.klindziuk.test.model.User;

public interface UserRepositoryCustom {

    List<User> findByFirstnameOrLastname(String firstname, String lastname);

}