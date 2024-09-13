package com.example.system_management.adapters.out.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@EnableJdbcRepositories(basePackages = "com.example.system_management.adapters.out.repositories")

public class JbdcRepositoryConfig {
    // DataSource Bean: Configures the connection to the PostgreSQL database
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/crudapp");
        dataSource.setUsername("postgres");
        dataSource.setPassword("si0770");

        return dataSource;
    }

    // JdbcTemplate Bean: Used to execute SQL queries and operations on the database
    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    // You can add other beans here, e.g., TransactionManager, etc.
    @Bean
    public PlatformTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
}
