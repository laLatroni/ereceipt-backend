package com.ereceipt.CAZAEORPROJECT.MODE_OF_PAYMENT;

import com.ereceipt.CAZAEORPROJECT.ERECEIPT_DTO.EOR.TRANSACTION;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;

import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "PAYMENT_TABLE")

public class ModeOfPayment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer modePayment;
    private String modePaymentName;

    public ModeOfPayment(Integer modePayment, String modePaymentName) {
        this.modePayment = modePayment;
        this.modePaymentName = modePaymentName;
    }

    @JsonIgnore
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "modePayment")
    @OneToMany(mappedBy = "modePayment", cascade = CascadeType.ALL)
    private List<TRANSACTION> transactions;

    public Integer getModePayment() {
        return modePayment;
    }

    public String getModePaymentName() {
        return modePaymentName;
    }
}