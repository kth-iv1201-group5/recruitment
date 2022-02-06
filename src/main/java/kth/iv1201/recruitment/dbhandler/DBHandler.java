package kth.iv1201.recruitment.dbhandler;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Component
public class DBHandler {

	private final Connection connection;

	public DBHandler(
			@Value("${spring.datasource.url}")
					String jdbcUrl,
			@Value("${spring.datasource.username}")
					String jdbcUsername,
			@Value("${spring.datasource.password}")
					String jdbcPassword
	) throws SQLException {
		this.connection = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword);
	}

	public PreparedStatement prepareStatement(String query) throws SQLException {
		return connection.prepareStatement(query);
	}
}
