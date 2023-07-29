package com.dosoroBazar.register.dao;


import com.dosoroBazar.register.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    private static final String SQL_USERNAME = """
            SELECT * FROM register WHERE email = ?
            """;

    public User getUserEmailIdDetails(String emailId) {
        try {
            RowMapper<User> rowMapper = (resultSet, rowNum) -> {
                User user = new User();
                user.setFirstName(resultSet.getString("firstName"));
                user.setLastName(resultSet.getString("lastName"));
                user.setEmailId(resultSet.getString("email"));
                user.setPhoneNo(resultSet.getString("phone_number"));
                user.setPassword(resultSet.getString("password"));
                return user;
            };

            return jdbcTemplate.queryForObject(SQL_USERNAME, new Object[]{emailId}, rowMapper);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}
