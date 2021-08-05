package com.taoemily.mytodo.repository;

import com.taoemily.mytodo.entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    @Query(value = "select * from Refreshtokens where refresh_token=?1", nativeQuery = true)
    Optional<RefreshToken> findByRefreshToken(String refreshtoken);

    @Transactional
    @Modifying
    @Query("delete from RefreshToken refreshtokens where refreshtokens.refeshToken=?1")
    void deleteByToken (String refreshtoken);

    @Override
    Optional<RefreshToken> findById(Long aLong);
}
