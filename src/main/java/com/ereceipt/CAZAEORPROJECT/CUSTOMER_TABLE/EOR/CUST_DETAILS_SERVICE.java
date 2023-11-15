package com.ereceipt.CAZAEORPROJECT.CUSTOMER_TABLE.EOR;

import com.ereceipt.CAZAEORPROJECT.exception.UserAlreadyExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CUST_DETAILS_SERVICE implements CUST_DETAILS_IMP  {
    private final CUST_DETAILS_REPOSITORY cust_details_repository;

    @Override
    public CUST_RECORDS add(CUST_RECORDS cust_records) {
        Optional<CUST_RECORDS>custRecords = cust_details_repository.findByCusNo(cust_records.getCusNo());
        if(custRecords.isPresent()){
            throw new UserAlreadyExistsException("CUSTOMER ALREADY EXIST" );
        }
        return this.cust_details_repository.save(cust_records);
    }

    @Override
    public List<CUST_RECORDS> getALLcustomer() {
        return null;
    }

    @Override
    public void delete(String cus_no) {

    }


    public CUST_RECORDS getUser(Integer cusNo) {
        return cust_details_repository.findByCusNo(cusNo).get();
    }

    @Override
    public CUST_RECORDS update(CUST_RECORDS cusNo) {
        return null;
    }
    public boolean isCustomerExisting(Integer cusNo){
        return cust_details_repository.findByCusNo(cusNo).isPresent();

    }


}
