package com.taoemily.mytodo.entity;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "users")
@JsonIgnoreProperties(ignoreUnknown = false)
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
//@JsonIdentityReference(alwaysAsId = true)
@Proxy(lazy = false)
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false, unique = true)
    private Long id;

    @Column(name = "user_name", nullable = false)
    private String username;

    //    @JsonIgnore
    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "is_admin", nullable = false)
    private Boolean isAdmin;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    @JsonIgnore
//    @JsonIdentityReference(alwaysAsId = false)
    //TODO 换一个user_id?
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private List<Todo> todoList = new ArrayList<>();

    //    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    //按理说这个的意思书在UserEntity这张表中， 会有refreshtoken这一栏， 指向RefreshToken这张表里面的userId这一栏
    @JoinColumn(name = "refreshtoken", referencedColumnName = "user")
    private String refreshtoken;

    public UserEntity(String username, String password, String email, Boolean isAdmin) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.isAdmin = isAdmin;
    }

    @Override
    public String toString() {
        return super.toString();
    }


}
