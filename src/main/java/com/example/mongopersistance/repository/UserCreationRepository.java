package com.example.mongopersistance.repository;

import com.example.mongopersistance.dto2.UserCreation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserCreationRepository extends MongoRepository<UserCreation,String> {
}
