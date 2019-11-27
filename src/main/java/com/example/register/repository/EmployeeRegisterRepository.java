package com.example.register.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.register.domain.Employee;

@Repository
public interface EmployeeRegisterRepository extends MongoRepository<Employee, String> {

}	
