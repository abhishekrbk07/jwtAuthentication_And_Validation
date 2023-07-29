package com.dosoroBazar.databaseTest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootTest
public class DatabaseConnectionTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void testDatabaseConnection() {
        try {
            jdbcTemplate.execute("SELECT 1");
            System.out.println("Database connection test passed. Connection is successful.");
        } catch (Exception e) {
            System.err.println("Database connection test failed. Unable to connect to the database.");
            e.printStackTrace();
        }
    }
}
