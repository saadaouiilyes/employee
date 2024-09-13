package com.example.system_management.adapters.out;


import com.example.system_management.application.dao.EmployeeDAO;
import com.example.system_management.application.dto.NewEmployeeDto;
import com.example.system_management.adapters.out.entities.EmployeeEntity;
import com.example.system_management.adapters.out.repositories.EmployeesRepository;
import com.example.system_management.domain.Employee;


import lombok.Getter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;



@Component
public class EmployeeDAOAdapter implements EmployeeDAO {

    private final EmployeesRepository employeesRepository;
    @Getter
    private final JdbcTemplate jdbcTemplate;

    public EmployeeDAOAdapter(EmployeesRepository employeesRepository, JdbcTemplate jdbcTemplate) {
        this.employeesRepository = employeesRepository;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<Employee> findEmployeeById(long id) {
        // Use the repository to find the employee by ID, and map it to the domain Employee object
        return employeesRepository.findById( id)
                .map(this::mapToEmployee);  // Use the mapping method to convert from EmployeeEntity to Employee
    }

    @Override
    public Optional<Employee> findEmployeeByName(String name) {
        return employeesRepository.findEmployeeByName(name)
                .map(this::mapToEmployee);  // This assumes that findEmployeeByName returns EmployeeEntity
    }

    @Override
    public List<Employee> findAllEmployees() {
        return StreamSupport.stream(employeesRepository.findAll().spliterator(), false)
                .map(this::mapToEmployee)
                .toList();
    }
    @Override
    public Employee saveEmployee(NewEmployeeDto newEmployeeDto) {
        EmployeeEntity savedEntity  = employeesRepository.save(
                new EmployeeEntity(
                        null,
                         newEmployeeDto.getFirstName(),
                        newEmployeeDto.getLastName(),
                        newEmployeeDto.getEmail(),
                        newEmployeeDto.getSalary()
                )
        );
        return mapToEmployee(savedEntity);
    }




    @Override
    public void updateEmployee (Employee newEmployee , NewEmployeeDto newEmployeeDto){
        employeesRepository.save(
                new EmployeeEntity(
                        newEmployee.getId(),
                        newEmployeeDto.getFirstName(),
                        newEmployeeDto.getLastName(),
                        newEmployeeDto.getEmail(),
                        newEmployeeDto.getSalary()
                )
        );
    }
    @Override
    public void deleteEmployee (Employee oldEmployee){

        employeesRepository.deleteById(oldEmployee.getId());
    }

    private Employee mapToEmployee(EmployeeEntity entity) {
        return new Employee(
                // Assuming you have getters
        );
    }


}
