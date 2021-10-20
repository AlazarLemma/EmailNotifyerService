package com.example.mongopersistance.repository;


import com.example.mongopersistance.domain.Email;
import com.example.mongopersistance.dto2.PaymentStatus;
import com.example.mongopersistance.dto2.PaymentToDo;
import com.example.mongopersistance.dto2.PaymentToDoStatus;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Repository;

import java.awt.geom.QuadCurve2D;
import java.util.List;
import java.util.stream.Stream;

@Repository
public interface EmailRepository extends MongoRepository<Email, String> {


    List<Email> findByPaymentStatus(String status);
}
