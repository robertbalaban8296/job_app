package utils;

import model.Student;

public class StudentBuilder {
	private Student s;
	
	public StudentBuilder() {
		s = new Student();
	}
	
	public StudentBuilder(Student s) {
		this.s = s;
	}
	
	public StudentBuilder withName(String name) {
		s.setName(name);
		return this;
	}
	
	public StudentBuilder withSurname(String surname) {
		s.setSurname(surname);
		return this;
	}
	
	public StudentBuilder withAge(int age) {
		s.setAge(age);
		return this;
	}
	
	public StudentBuilder withCnp(long cnp) {
		s.setCnp(cnp);
		return this;
	}
	
	public StudentBuilder withFinalgrade(double finalgrade) {
		s.setFinalGrade(finalgrade);
		return this;
	}
	
	public Student build() {
		return s;
	}
}
