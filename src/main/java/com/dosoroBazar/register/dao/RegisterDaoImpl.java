package com.dosoroBazar.register.dao;

import com.dosoroBazar.register.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;



@Repository
public class RegisterDaoImpl implements RegisterDao {
@Autowired
private JdbcTemplate jdbcTemplate;

     private static final String REGISTER_PHONE_VALIDATION_SQL= """
             SELECT Count(phone_number) FROM register WHERE phone_number = ?
             """;

    private static final String REGISTER_EMAIL_ID_VALIDATION_SQL= """
             SELECT Count(email) FROM register WHERE email = ?
             """;
     private static final String SAVE_DETAILS_SQL = """
             INSERT INTO Register
            (firstName, lastName, phone_number, email, password)
            VALUES
            (?,?,?,?,?)
            """;

    public void saveDetails(User user) {
        jdbcTemplate.update(SAVE_DETAILS_SQL,
                user.getFirstName(),
                user.getLastName(),
                user.getPhoneNo(),
                user.getEmailId(),
                user.getPassword());
        //        Map<String, Object> parameters = new HashMap<>();
//        parameters.put("firstName", registerParameter.getFirstName());
//        parameters.put("lastName", registerParameter.getLastName());
//        parameters.put("phoneNumber", registerParameter.getPhoneNo());
//        parameters.put("email", registerParameter.getEmailId());
//        parameters.put("password", registerParameter.getPassword());
    }

  public boolean getUserPhoneNoDetails(String phoneNumber){
      int count = jdbcTemplate.queryForObject(REGISTER_PHONE_VALIDATION_SQL, Integer.class, phoneNumber);
      if(count > 0){
          return true;
      }
     return false;
  }

    public boolean getUserEmailIdDetails(String emailId){
        int count = jdbcTemplate.queryForObject(REGISTER_EMAIL_ID_VALIDATION_SQL, Integer.class, emailId);
        if(count > 0){
            return true;
        }
        return false;
    }
}
