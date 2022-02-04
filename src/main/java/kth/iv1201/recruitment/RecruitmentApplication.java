package kth.iv1201.recruitment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RecruitmentApplication {

    public static void main(String[] args) {
        String jdbcUrl = "jdbc:postgresql://localhost:5432/template1";
        String username = "postgres";
        String password = " ";
        Connection conn = null;
        try  {

             conn = DriverManager.getConnection(jdbcUrl, username, password);
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
        SpringApplication.run(RecruitmentApplication.class, args);

        ResultSet result = null;
        try {

            result = conn.prepareStatement("Select * from person").executeQuery();
            while (result.next()) {
              
                String a = result.getString("name");
                System.out.println(a);
            }
            conn.commit();
        } catch (SQLException sqle) {
            
        }
        
    }

}
