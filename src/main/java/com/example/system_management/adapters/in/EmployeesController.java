package com.example.system_management.adapters.in;


import com.example.system_management.application.dto.NewEmployeeDto;
import com.example.system_management.application.usecases.EmployeeUseCase;
import com.example.system_management.domain.Employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employees")
@CrossOrigin
public class EmployeesController {

    private final EmployeeUseCase employeeUseCase;
    @Autowired
    public EmployeesController(EmployeeUseCase employeeUseCase) {
        this.employeeUseCase = employeeUseCase;
    }


    @GetMapping
    public ResponseEntity<?> getAllEmployees(){

        return ResponseEntity.ok(employeeUseCase.getAllEmployees());
    }

    @GetMapping("/{First_Name}")
    public ResponseEntity<?> getEmployeeByName(@PathVariable("First_Name") String First_Name){
        return ResponseEntity.ok(employeeUseCase.getEmployeeByName(First_Name));
    }

    @PostMapping
    public ResponseEntity<?> saveEmployee(@RequestBody NewEmployeeDto newEmployeeDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(employeeUseCase.saveEmployee(newEmployeeDto));
    }

    @PutMapping
    public ResponseEntity<Void> updateEmployee(@RequestBody Employee employee) {
        NewEmployeeDto dto = convertToNewEmployeeDto(employee);
        employeeUseCase.updateEmployee(employee.getId(), dto);
        return ResponseEntity.noContent().build();
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        employeeUseCase.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }


    private NewEmployeeDto convertToNewEmployeeDto(Employee employee) {
        return new NewEmployeeDto(
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail(),
                employee.getSalary()

                // Add other fields as necessary
        );
    }



}
