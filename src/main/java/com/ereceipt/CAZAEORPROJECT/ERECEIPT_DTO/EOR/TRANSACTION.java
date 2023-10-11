package com.ereceipt.CAZAEORPROJECT.ERECEIPT_DTO.EOR;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
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
    @GeneratedValue
    private Integer id;


    private String or_number;
    private String names;
    private String cus_email;
    private String dates;
    private String mode_payment;
    private String amount;
    private String customer_no;

    @CreationTimestamp
    private String time_Stamp;

    }


