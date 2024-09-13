package com.example.system_management.application.dto;

import lombok.Getter;

@Getter
public class NewEmployeeDto {
    // Standard getters and setters
    private final String firstName;
    private final String lastName;
    private final String email;
    private  final Float salary;

    public NewEmployeeDto(String firstName , String lastName , String email , Float salary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.salary = salary;


    }


}
