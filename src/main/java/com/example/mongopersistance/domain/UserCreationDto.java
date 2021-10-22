package com.example.mongopersistance.domain;

import com.example.mongopersistance.dto2.Role;
import lombok.Data;

@Data
public class UserCreationDto {
    private String userName;
    private String email;
    private Role role;
}
