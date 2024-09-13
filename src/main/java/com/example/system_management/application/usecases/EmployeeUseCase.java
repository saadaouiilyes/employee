package com.example.system_management.application.usecases;
import com.example.system_management.adapters.out.EmployeeDAOAdapter;
import com.example.system_management.infrastructure.exceptions.EmployeeAlreadyExistsException;
import com.example.system_management.infrastructure.exceptions.EmployeeNotFoundException;


import com.example.system_management.application.dao.EmployeeDAO;
import com.example.system_management.application.dto.NewEmployeeDto;
import com.example.system_management.domain.Employee;

import io.micrometer.common.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class EmployeeUseCase {
    private static final Logger logger = LoggerFactory.getLogger(EmployeeUseCase.class);
    private final EmployeeDAO employeeDAO;
    private final EmployeeDAOAdapter employeeDAOAdapter;

    @Transactional
    public Employee saveEmployee(NewEmployeeDto newEmployeeDto) {
        logger.info("Attempting to save new employee: {}", newEmployeeDto.getFirstName());

        if (StringUtils.isBlank(newEmployeeDto.getFirstName()) || StringUtils.isBlank(newEmployeeDto.getLastName())) {
            throw new IllegalArgumentException("First name and last name are required");
        }

        employeeDAO.findEmployeeByName(newEmployeeDto.getFirstName()).ifPresent(e -> {
            logger.warn("Employee with name {} already exists", newEmployeeDto.getFirstName());
            throw new EmployeeAlreadyExistsException("Employee already exists");
        });

        Employee savedEmployee = employeeDAO.saveEmployee(newEmployeeDto);
        logger.info("Employee saved successfully: {}", savedEmployee.getId());
        return savedEmployee;
    }
    public List<Employee> getAllEmployees(){
            return employeeDAO.findAllEmployees();
        }

    public void updateEmployee(long id, NewEmployeeDto dto) {
        // Retrieve the existing employee by ID
        Employee existingEmployee = employeeDAOAdapter.findEmployeeById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        // Now call the DAO adapter's updateEmployee method to update the employee
        employeeDAOAdapter.updateEmployee(existingEmployee, dto);
    }
    public Employee getEmployeeByName(String employeeName){
        return employeeDAO.findEmployeeByName(employeeName).orElseThrow(
                () -> new EmployeeNotFoundException("Employee not found"));
    }
    public void deleteEmployee(Long employeeId) throws EmployeeNotFoundException {
        // Check if the employee exists by ID
        Employee existingEmployee = employeeDAOAdapter.findEmployeeById(employeeId)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found with ID: " + employeeId));

        // Delete the employee
        employeeDAO.deleteEmployee(existingEmployee);
    }
}



