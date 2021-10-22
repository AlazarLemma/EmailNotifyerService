package com.example.mongopersistance.repository;

import com.example.mongopersistance.domain.UserCancledOrder;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCancledRepository extends MongoRepository<UserCancledOrder,String> {

}
