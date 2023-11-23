package com.ereceipt.CAZAEORPROJECT.Token;


import javax.persistence.*;

import com.ereceipt.CAZAEORPROJECT.User.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Tokens{

    @Id
    @GeneratedValue
    public Integer id;

    @Column(unique = true)
    public String token;

    @Enumerated(EnumType.STRING)
    public TokenType tokenType = TokenType.Bearer;

    public boolean revoked;

    public boolean expired;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "user_id")
        public User user;
}



