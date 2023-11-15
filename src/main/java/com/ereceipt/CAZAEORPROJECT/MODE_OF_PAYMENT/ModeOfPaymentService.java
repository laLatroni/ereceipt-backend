package com.ereceipt.CAZAEORPROJECT.MODE_OF_PAYMENT;

import com.ereceipt.CAZAEORPROJECT.CUSTOMER_TABLE.EOR.CUST_RECORDS;
import com.ereceipt.CAZAEORPROJECT.exception.UserAlreadyExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ModeOfPaymentService implements ModeOfPaymentImp {

    private final ModeOfPaymentRepo modeOfPaymentRepo;

    @Override
    public ModeOfPayment addPayment(ModeOfPayment modeOfPayment) {
        Optional<ModeOfPayment> payment = modeOfPaymentRepo.findByModePayment(modeOfPayment.getModePayment());
        if (payment.isPresent()) {
            throw new UserAlreadyExistsException("MODE OF PAYMENT ALREADY EXIST");
        }
        return this.modeOfPaymentRepo.save(modeOfPayment);
    }

    public boolean ExistingBaYungMethod(Integer modePayment) {
        return modeOfPaymentRepo.findByModePayment(modePayment).isPresent();

    }
    public ModeOfPayment getUser(Integer modePayment) {
        return modeOfPaymentRepo.findByModePayment(modePayment).get();
    }

}
