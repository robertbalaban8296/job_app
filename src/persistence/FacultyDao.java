package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import model.Faculty;
import model.Student;
import service.DatabaseService;
import utils.ResultSetHelper;
import utils.StatementBuilder;
import utils.StatementHelper;
import utils.StringHelper;
import utils.Tables;

public class FacultyDao {

	public int addFaculty(Faculty f) {
		try (
			Connection con = DatabaseService.getConnection();
			PreparedStatement stm = con.prepareStatement(
					new StringBuilder("INSERT INTO ")
					.append(Tables.FACULTY.str)
					.append(" (ID, NAME, ADDRESS) ")
					.append("VALUES (?,?,?)")
					.toString());
			) {
			return new StatementBuilder(stm)
					.setInt(f.getID())
					.setString(f.getName())
					.setString(f.getAddress())
					.build()
					.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public List<Student> getFacultyStudents(Faculty f) {
		return ResultSetHelper.getStudentsFromResultSet(
				new StringBuilder("SELECT ")
				.append("student.cnp, student.name, student.surname, ")
				.append("student.age, student.final_grade ")
				.append("FROM ")
				.append(Tables.FACULTY)
				.append(" join ")
				.append(Tables.STUDENT_FACULTY)
				.append(" on ")
				.append("(faculty.id=student_faculty.id) ")
				.append("join ")
				.append(Tables.STUDENT)
				.append(" on ")
				.append("(student.cnp=student_faculty.cnp) ")
				.append("WHERE faculty.id=")
				.append(f.getID())
				.toString());
	}
	
	public List<Faculty> getAllFaculties() {
		return ResultSetHelper.getFacultiesFromResultSet(
				StringHelper.append
				("SELECT * FROM ", Tables.FACULTY.str));
	}
	
	public int removeFaculty(Faculty f) {
		return removeFaculty(f.getID());
	}
	
	public int removeFaculty(int id) {
		return StatementHelper.executeUpdate(new
				StringBuilder("DELETE FROM ")
				.append(Tables.FACULTY.str)
				.append(" WHERE id=")
				.append(id)
				.toString());
	}
}
