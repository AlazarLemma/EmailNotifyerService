package com.example.mongopersistance.dto2;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class UserCreation {

    private String userName;
    private String email;
//    private String firstName;
//    private String lastName;
//    private Address address;
//    private Payment payment;
    private Role role;


}
