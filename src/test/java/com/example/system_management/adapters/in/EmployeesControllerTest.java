package com.example.system_management.adapters.in;

import com.example.system_management.application.dto.NewEmployeeDto;
import com.example.system_management.application.usecases.EmployeeUseCase;
import com.example.system_management.domain.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


public class EmployeesControllerTest {

    @Mock
    private EmployeeUseCase employeeUseCase;

    @InjectMocks
    private EmployeesController employeesController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllEmployees() {
        List<Employee> employees = Arrays.asList(new Employee(), new Employee());
        when(employeeUseCase.getAllEmployees()).thenReturn(employees);

        ResponseEntity<?> response = employeesController.getAllEmployees();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(employees, response.getBody());
    }

    @Test
    void getEmployeeByName() {
        String firstName = "John";
        Employee employee = new Employee();
        when(employeeUseCase.getEmployeeByName(firstName)).thenReturn(employee);

        ResponseEntity<?> response = employeesController.getEmployeeByName(firstName);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(employee, response.getBody());
    }

    @Test
    void saveEmployee() {
        NewEmployeeDto dto = new NewEmployeeDto("John", "Doe", "john@example.com", 50000.0F);
        Employee savedEmployee = new Employee();
        when(employeeUseCase.saveEmployee(dto)).thenReturn(savedEmployee);

        ResponseEntity<?> response = employeesController.saveEmployee(dto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(savedEmployee, response.getBody());
    }

    @Test
    void updateEmployee() {
        Employee employee = new Employee();
        employee.setId(1L);
        employee.setFirstName("John");
        employee.setLastName("Doe");
        employee.setEmail("john@example.com");
        employee.setSalary(60000.0F);

        ResponseEntity<Void> response = employeesController.updateEmployee(employee);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(employeeUseCase).updateEmployee(eq(1L), any(NewEmployeeDto.class));
    }

    @Test
    void deleteEmployee() {
        Employee employee = new Employee();
        employee.setId(1L);

        ResponseEntity<Void> response = employeesController.deleteEmployee(employee.getId());

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(employeeUseCase).deleteEmployee(employee.getId());
    }
}
