package utils;

import java.util.List;

import model.Faculty;
import model.Student;

public class FacultyBuilder {
	private Faculty f;
	
	public FacultyBuilder() {
		f = new Faculty();
	}
	
	public FacultyBuilder(Faculty f) {
		this.f = f;
	}
	
	public FacultyBuilder withId(int id) {
		f.setID(id);
		return this;
	}
	
	public FacultyBuilder withName(String name) {
		f.setName(name);
		return this;
	}
	
	public FacultyBuilder withAddress(String address) {
		f.setAddress(address);
		return this;
	}
	
	public FacultyBuilder withStudents(List<Student> students) {
		f.setStudents(students);
		return this;
	}
	
	public FacultyBuilder withStudent(Student s) {
		f.addStudent(s);
		return this;
	}
	
	public Faculty build() {
		return f;
	}
}
