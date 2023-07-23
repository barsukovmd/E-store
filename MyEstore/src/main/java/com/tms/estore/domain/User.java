package com.tms.estore.domain;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "users")
@NoArgsConstructor
@SuperBuilder
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class User extends BaseEntity implements Serializable {

    private String password;
    private String login;
    private String name;
    private String surname;
    private String email;
    private LocalDate birthday;
    @OneToMany(mappedBy = "user")
    private List<Order> orders;
    @OneToMany(mappedBy = "user")
    private List<Cart> carts;
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
}
