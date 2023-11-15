package com.ereceipt.CAZAEORPROJECT.MODE_OF_PAYMENT;

import com.ereceipt.CAZAEORPROJECT.CUSTOMER_TABLE.EOR.CUST_RECORDS;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ModeOfPaymentRepo extends JpaRepository<ModeOfPayment,Integer> {
    Optional<ModeOfPayment> findByModePayment (Integer modePayment);

}
