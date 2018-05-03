package persistence;

import java.sql.*;
import java.util.*;
import model.*;
import service.DatabaseService;
import utils.*;

public class StudentDao {
	
	public int addStudent(Student s) {
		try (
			Connection con = DatabaseService.getConnection();
			PreparedStatement stm = con.prepareStatement(
					new StringBuilder("INSERT INTO ")
					.append(Tables.STUDENT.str)
					.append(" (CNP, NAME, SURNAME ,AGE, FINAL_GRADE) ")
					.append("VALUES (?,?,?,?,?)")
					.toString());
			) {
			return new StatementBuilder(stm)
					.setLong(s.getCnp())
					.setString(s.getName())
					.setString(s.getSurname())
					.setInt(s.getAge())
					.setDouble(s.getFinalGrade())
					.build()
					.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public List<Faculty> getStudentFaculties(Student s) {
		return ResultSetHelper.getFacultiesFromResultSet(
				new StringBuilder("SELECT ")
				.append("faculty.id, faculty.name, faculty.address ")
				.append("FROM ")
				.append(Tables.STUDENT.str)
				.append(" join ")
				.append(Tables.STUDENT_FACULTY)
				.append(" on ")
				.append("(student.cnp=student_faculty.cnp) ")
				.append("join ")
				.append(Tables.FACULTY.str)
				.append(" on ")
				.append("(faculty.id=student_faculty.id) ")
				.append("WHERE student.cnp=")
				.append(s.getCnp())
				.toString());
	}
	
	public int removeStudent(Student s) {
		return removeStudent(s.getCnp());
	}
	
	public int removeStudent(long cnp) {
			return StatementHelper.executeUpdate(
					new StringBuilder("DELETE FROM ")
					.append(Tables.STUDENT.str)
					.append(" WHERE cnp=")
					.append(cnp)
					.toString());
	}
	
	public List<Student> getAllStudents() {
		return ResultSetHelper.getStudentsFromResultSet
				(StringHelper
				.append("SELECT * FROM ", Tables.STUDENT.str));
	}
	
	public List<Student> getStudentsOrderBy(StudentSortBy sort, OrderType order) {
		return ResultSetHelper.getStudentsFromResultSet
				(new StringBuilder("SELECT * FROM ")
				.append(Tables.STUDENT.str)
				.append(" ORDER by ")
				.append(sort.toString())
				.append(" ")
				.append(order.toString())
				.toString());
	}
}
