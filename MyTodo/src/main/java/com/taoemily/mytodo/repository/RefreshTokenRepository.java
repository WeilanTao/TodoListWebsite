package com.taoemily.mytodo.repository;

import com.taoemily.mytodo.entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    @Query(value = "select * from Refreshtokens where refreshtoken_id=?1", nativeQuery = true)
    Optional<RefreshToken> findByRefreshToken(String refreshtoken);

    @Query(value= "delete from Refreshtokens where refreshtoken_id=?1", nativeQuery = true)
    void deleteByToken (String refreshtoken);

    @Override
    Optional<RefreshToken> findById(Long aLong);
}
