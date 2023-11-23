package com.ereceipt.CAZAEORPROJECT.ERECEIPT_DTO.EOR;
import com.ereceipt.CAZAEORPROJECT.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TRANSACTION_REPOSITORY extends JpaRepository<TRANSACTION, Integer> {

    Optional<TRANSACTION> findByEmail(String email);


}
