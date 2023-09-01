/*
 *  imReading API
 * Copyright (c) 2023 Marisabel Munoz
 * This project is licensed under the terms of the MIT License.
 * For more information, please see the https://opensource.org/license/mit/.
 */

package nl.marisabel.imReadingAPI;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

@SpringBootTest
class ImReadingApiApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void cleanUpDatabase() {
		String jdbcUrl = "jdbc:mysql://localhost:3306/your_database"; // Replace with your database URL
		String username = "your_username"; // Replace with your MySQL username
		String password = "your_password"; // Replace with your MySQL password

		try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {
			String tableToWipe = "your_table_name"; // Replace with the name of the table you want to wipe
			String sql = "TRUNCATE TABLE " + tableToWipe;

			try (Statement statement = connection.createStatement()) {
				statement.executeUpdate(sql);
				System.out.println("Table " + tableToWipe + " has been wiped successfully!");
			} catch (SQLException e) {
				System.err.println("Error wiping table: " + e.getMessage());
			}
		} catch (SQLException e) {
			System.err.println("Connection failed: " + e.getMessage());
		}
	}
}



