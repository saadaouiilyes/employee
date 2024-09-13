package com.example.system_management.application.dao;

import com.example.system_management.application.dto.NewEmployeeDto;
import com.example.system_management.domain.Employee;

import java.util.List;
import java.util.Optional;


public interface EmployeeDAO {


    Optional<Employee> findEmployeeById(long id);

    Optional<Employee> findEmployeeByName(String firstName);

    List<Employee> findAllEmployees();

    Employee saveEmployee (NewEmployeeDto employee);

    void updateEmployee(Employee newEmployee, NewEmployeeDto newEmployeeDto);

    void deleteEmployee (Employee oldEmployee);



}
