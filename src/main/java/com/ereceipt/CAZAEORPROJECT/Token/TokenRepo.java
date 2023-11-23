package com.ereceipt.CAZAEORPROJECT.Token;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TokenRepo extends JpaRepository<Tokens,Integer> {
    @Query(
      "select t from Tokens t inner join User u " +
      "on t.user.id = u.id " +
      "where u.id = :userId and (t.expired = false or t.revoked = false)")

    List<Tokens> findAllValidTokenByUser(Integer userId);

    Optional<Tokens> findByToken(String token);

}
