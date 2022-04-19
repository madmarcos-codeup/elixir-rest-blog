package com.example.restblog.data;

import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class User {
    public enum Role {USER, ADMIN}

    Long id;
    String username;
    String email;
    String password;
    LocalDate createdAt;
    Role role;
}
