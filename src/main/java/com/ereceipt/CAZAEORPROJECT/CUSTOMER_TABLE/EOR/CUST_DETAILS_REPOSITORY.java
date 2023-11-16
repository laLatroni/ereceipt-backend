package com.ereceipt.CAZAEORPROJECT.CUSTOMER_TABLE.EOR;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface CUST_DETAILS_REPOSITORY extends JpaRepository<CUST_RECORDS,Integer> {
    Optional<CUST_RECORDS> findByCusNo (Integer cusNo);

}
