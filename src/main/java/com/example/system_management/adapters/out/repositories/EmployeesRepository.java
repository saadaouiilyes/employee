package com.example.system_management.adapters.out.repositories;

import com.example.system_management.adapters.out.entities.EmployeeEntity;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface EmployeesRepository extends CrudRepository<EmployeeEntity,  Long> {
    //table name = employees
   @Query("select * from employee where firstName =: firstName")
    Optional<EmployeeEntity> findEmployeeByName( @Param("firstName") String firstName);

}
