package com.iupi.fintech.utils.iol;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IolUserRepository extends JpaRepository<IolUser, String> {


}
