package com.ereceipt.CAZAEORPROJECT.CUSTOMER_TABLE.EOR;
import com.ereceipt.CAZAEORPROJECT.ERECEIPT_DTO.EOR.TRANSACTION;
import com.fasterxml.jackson.annotation.*;
import com.sun.corba.se.spi.ior.ObjectId;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;


@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "CUSTOMER_RECORDS")
public class CUST_RECORDS {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cusNo;


    private String fnames;
    private String lnames;
    private String cus_email;
    private String cus_gender;
    private String creationdate;
    private String cus_address;

    public CUST_RECORDS( Integer cusNo, String fnames, String lnames, String cus_email, String cus_gender, String creationdate, String cus_address) {

        this.cusNo = cusNo;
        this.fnames = fnames;
        this.lnames = lnames;
        this.cus_email = cus_email;
        this.cus_gender = cus_gender;
        this.creationdate = creationdate;
        this.cus_address = cus_address;

    }

    @JsonIgnore
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "cusNo")
    @OneToMany(mappedBy = "customer")
    private List<TRANSACTION>transactions;

    public Integer getCusNo() {
        return cusNo;
    }

    public String getFnames() {
        return fnames;
    }

    public String getLnames() {
        return lnames;
    }

    public String getCus_email() {
        return cus_email;
    }

    public String getCus_gender() {
        return cus_gender;
    }

    public String getCreationdate() {
        return creationdate;
    }

    public String getCus_address() {
        return cus_address;
    }
}
