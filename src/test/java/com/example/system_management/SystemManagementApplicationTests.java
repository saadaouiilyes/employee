package com.example.system_management;

import com.example.system_management.domain.Employee;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class EmployeeTest {

	@Test
	void testEmployeeCreation() {
		// Create an Employee object
		Employee employee = new Employee();

		// Test that the fields are correctly set
		assertEquals(1, employee.getId());
		assertEquals("achraf", employee.getFirstName());
		assertEquals("kallel", employee.getLastName());
		assertEquals("achrafkallel58@gmail.com", employee.getEmail());
		assertEquals(1000, employee.getSalary());
	}
}
