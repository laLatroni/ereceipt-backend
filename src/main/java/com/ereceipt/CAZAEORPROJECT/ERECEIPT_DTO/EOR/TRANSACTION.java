package com.ereceipt.CAZAEORPROJECT.ERECEIPT_DTO.EOR;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.ereceipt.CAZAEORPROJECT.CUSTOMER_TABLE.EOR.CUST_RECORDS;

import com.ereceipt.CAZAEORPROJECT.MODE_OF_PAYMENT.ModeOfPayment;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Component
@Table(name = "EOR_TRANSACTION_TABLE")
public class TRANSACTION {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String or_number;
    @NotEmpty(message = "Name may not be empty")
    @Size(min = 2, max = 32, message = "Name must be between 2 and 32 characters long")
    private String names;
    private String cus_email;
    private String dates;
    private Integer modePayment;
    private String amount;
    private Integer cusNo;
    @Temporal(TemporalType.DATE)
    private Date time_Stamp;



    @JsonIdentityReference(alwaysAsId = true)
    @ManyToOne
    @JoinColumn(name = "cusNo",updatable = false,insertable = false)
    private CUST_RECORDS customer;




    @JsonIdentityReference(alwaysAsId = true)
    @ManyToOne
    @JoinColumn(name = "modePayment",updatable = false,insertable = false)
    private ModeOfPayment payment;
    }




