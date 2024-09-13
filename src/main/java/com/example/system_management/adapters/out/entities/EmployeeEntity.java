package com.example.system_management.adapters.out.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Version;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "employee")
@Getter
@Setter
public class EmployeeEntity {

        @Id
        private Long id;

        private String firstName;

        private String lastName;

        private String email;

        private Float salary;

        @Version
        private Integer version;

        // No-args constructor (required by JPA)


        // Constructor with all fields except version
        public EmployeeEntity(Long id, String firstName, String lastName, String email, Float salary) {
                this.id = id;
                this.firstName = firstName;
                this.lastName = lastName;
                this.email = email;
                this.salary = salary;
                this.version = 0; // default version
        }
}
