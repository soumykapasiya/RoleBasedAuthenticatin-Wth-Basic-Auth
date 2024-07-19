package com.kapasiya.springsecurity.entity;

import lombok.*;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class User
{
    String username;
    String password;
    String email;
}
