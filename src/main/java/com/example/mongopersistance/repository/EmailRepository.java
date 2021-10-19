package com.example.mongopersistance.repository;


import com.example.mongopersistance.domain.Email;
import com.example.mongopersistance.dto2.PaymentToDo;
import com.example.mongopersistance.dto2.PaymentToDoStatus;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailRepository extends MongoRepository<Email, String> {

}
