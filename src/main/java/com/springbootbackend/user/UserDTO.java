package com.springbootbackend.user;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Long id;
    private String firstname;
    private String lastname;
    private String email;
}
