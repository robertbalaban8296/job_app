package persistence;

import model.Faculty;
import model.Student;
import utils.StatementHelper;

public class Student_FacultyDao {
	
	public void insert(Student s) {
		s.getFaculties()
			.forEach(e -> insert(s, e));
	}
	
	public void insert(Faculty f) {
		f.getStudents()
			.forEach(s -> insert(s, f));
	}
	
	public int insert(Student s, Faculty f) {
		return insert(s.getCnp(), f.getID());
	}
	
	public int insert(long cnp, int id) {
		return StatementHelper.executeUpdate(
				new StringBuilder("INSERT INTO ")
					.append("student_faculty")
					.append(" (CNP,")
					.append("ID)")
					.append("VALUES (")
					.append(cnp)
					.append(",")
					.append(id)
					.append(")")
					.toString());
 	}
	
	public void delete(Student s) {
		s.getFaculties()
			.forEach(f -> delete(s, f));
	}
	
	public void delete(Faculty f) {
		f.getStudents()
			.forEach(s -> delete(s, f));
	}
	
	public int delete(Student s, Faculty f) {
		return delete(s.getCnp(), f.getID());
	}
	
	public int delete(long cnp, int id) {
		return StatementHelper.executeUpdate(
				new StringBuilder("DELETE FROM ")
				.append("student_faculty")
				.append(" WHERE cnp=")
				.append(cnp)
				.toString());
	}
}
