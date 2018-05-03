package utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Faculty;
import model.Student;
import service.DatabaseService;

public class ResultSetHelper {
	
	
	public static List<Student> getStudentsFromResultSet(String sql) {
		List<Student> students = new ArrayList<>();
	
		try (
			Connection con = DatabaseService.getConnection();
			PreparedStatement stm = con.prepareStatement(sql);
			ResultSet rs = stm.executeQuery();
			) {
			while (rs.next()) {
				students.add(new StudentBuilder()
						.withCnp(rs.getLong("CNP"))
						.withAge(rs.getInt("AGE"))
						.withName(rs.getString("NAME"))
						.withSurname(rs.getString("SURNAME"))
						.withFinalgrade(rs.getDouble("FINAL_GRADE"))
						.build());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return students;
	}
	
	public static List<Faculty> getFacultiesFromResultSet(String sql) {
		List<Faculty> faculties = new ArrayList<>();
		
		try (
			Connection con = DatabaseService.getConnection();
			PreparedStatement stm = con.prepareStatement(sql);
			ResultSet rs = stm.executeQuery();
			) {
			while (rs.next()) {
				faculties.add(new FacultyBuilder()
						.withId(rs.getInt("id"))
						.withName(rs.getString("name"))
						.withAddress(rs.getString("address"))
						.build());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return faculties;
	}
}
