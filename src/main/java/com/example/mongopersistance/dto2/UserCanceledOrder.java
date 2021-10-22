package com.example.mongopersistance.dto2;

import lombok.Data;

@Data
public class UserCanceledOrder {
     //canceled
    private Order orderId;
    private User user;

}
