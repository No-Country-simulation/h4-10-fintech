package com.iupi.fintech.repositories;

import com.iupi.fintech.models.User;
import com.iupi.fintech.models.rxFinanciera.RxFinanciera;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface RxFinancieraRepository extends JpaRepository<RxFinanciera, Long> {

   Optional<RxFinanciera> findByUser_UsuarioId(Long id);
}
