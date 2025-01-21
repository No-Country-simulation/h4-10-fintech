package com.iupi.fintech.repositories;

import com.iupi.fintech.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);
    User findByAuth0Id(String auth0Id);
}
