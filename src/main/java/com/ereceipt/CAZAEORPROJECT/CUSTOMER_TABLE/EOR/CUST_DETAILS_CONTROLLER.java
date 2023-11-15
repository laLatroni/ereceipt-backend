package com.ereceipt.CAZAEORPROJECT.CUSTOMER_TABLE.EOR;

import com.ereceipt.CAZAEORPROJECT.ERECEIPT_DTO.EOR.TRANSACTION;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CUST_DETAILS_CONTROLLER {

    @Autowired
    private final CUST_DETAILS_SERVICE custDetailsService;


    @PostMapping("/add")
    public ResponseEntity<CUST_RECORDS> add(@RequestBody CUST_RECORDS cust_records){
        return ResponseEntity.ok(custDetailsService.add(cust_records));
    }


    @GetMapping(value = "/{cus_no}")
    public CUST_RECORDS getByid(@PathVariable ("cus_no") Integer cus_no){

            return custDetailsService.getUser(cus_no);
    }
}
