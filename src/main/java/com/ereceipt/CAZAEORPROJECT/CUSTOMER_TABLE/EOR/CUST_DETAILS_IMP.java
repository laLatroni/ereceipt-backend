package com.ereceipt.CAZAEORPROJECT.CUSTOMER_TABLE.EOR;

import java.util.List;

public interface CUST_DETAILS_IMP {
    CUST_RECORDS add(CUST_RECORDS custRecords);
    List<CUST_RECORDS> getALLcustomer();
    void delete(String cus_no);
    CUST_RECORDS getUser(Integer cus_no);
    CUST_RECORDS update(CUST_RECORDS custRecords);
}
