package com.springbootbackend.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {

    private Long id;
    private String firstname;
    private String lastname;
    private String email;
    private String oldPassword;
    private String newPassword;
}
