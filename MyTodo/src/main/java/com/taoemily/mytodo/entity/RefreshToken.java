package com.taoemily.mytodo.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;

@Data
@NoArgsConstructor
@Entity
@Table(name="refreshtokens")
@JsonIgnoreProperties(ignoreUnknown = false)
public class RefreshToken {
    @Id
    @Column(name = "refreshtoken_id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long refreshtokenId;

    @OneToOne
    @JoinColumn(name = "user")
    private UserEntity user;

    @Column(name="refresh_token", nullable= false, unique = true)
    private String refeshToken;

    @Column(name="expiry_date", nullable = false)
    private Instant expiryDate;


    public RefreshToken(UserEntity user, String refeshToken, Instant expiryDate) {
        this.user = user;
        this.refeshToken = refeshToken;
        this.expiryDate = expiryDate;
    }
}
