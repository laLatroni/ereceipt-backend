package com.ereceipt.CAZAEORPROJECT.MODE_OF_PAYMENT;


import com.ereceipt.CAZAEORPROJECT.CUSTOMER_TABLE.EOR.CUST_RECORDS;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RequestMapping("/api/v1/eor")
@RestController
public class ModeOfPaymentController {

    private final ModeOfPaymentService modeOfPaymentService;

    public ModeOfPaymentController(ModeOfPaymentService modeOfPaymentService) {
        this.modeOfPaymentService = modeOfPaymentService;
    }

    @PostMapping("/adds")
    public ResponseEntity<ModeOfPayment> add(@RequestBody ModeOfPayment modeOfPayments){
        return ResponseEntity.ok(modeOfPaymentService.addPayment(modeOfPayments));
    }
}
