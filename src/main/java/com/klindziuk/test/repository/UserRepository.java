package com.klindziuk.test.repository;

import com.klindziuk.test.model.User;
import com.klindziuk.test.repository.custom.UserRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer>, UserRepositoryCustom {

}