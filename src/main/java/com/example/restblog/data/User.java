package com.example.restblog.data;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {
    public enum Role {USER, ADMIN}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column
    String username;
    @Column
    String email;
    @Column
    String password;
    @Column
    LocalDate createdAt;
    @Column
    Role role;
//    @OneToMany
//    Collection<Post> posts;
}
