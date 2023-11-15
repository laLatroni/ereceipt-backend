package com.ereceipt.CAZAEORPROJECT.ERECEIPT_DTO.EOR;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TRANSACTION_REPOSITORY extends JpaRepository<TRANSACTION, Integer> {


}
