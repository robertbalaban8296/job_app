package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Student implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3603601714202546670L;
	private int age;
	private String name, surname;
	private long cnp;
	private double finalGrade;
	private List<Faculty> faculties;
	
	public Student() {
		faculties = new ArrayList<>();
	}
	
	public Student(List<Faculty> faculties) {
		setFaculties(faculties);
	}
	@Override
	public boolean equals(Object o) {
		Student s = (Student) o;
		return s.cnp == this.cnp;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getCnp() {
		return cnp;
	}
	public void setCnp(long cnp) {
		this.cnp = cnp;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public double getFinalGrade() {
		return finalGrade;
	}
	public void setFinalGrade(double finalGrade) {
		this.finalGrade = finalGrade;
	}
	@Override
	public String toString() {
		return new StringBuilder(name)
				.append(" ")
				.append(cnp)
				.append(" ")
				.append(age)
				.toString();
	}
	public List<Faculty> getFaculties() {
		return faculties;
	}
	public void setFaculties(List<Faculty> faculties) {
		this.faculties = faculties;
	}
	public boolean addFaculty(Faculty f) {
		return faculties.add(f);
	}
	public boolean removeFaculty(Faculty f) {
		return faculties.remove(f);
	}
	public boolean removeFaculty(int id) {
		Optional<Faculty> f = faculties
				.stream()
				.filter((fs) -> fs.getID() == id)
				.findFirst();
		return f.isPresent() ? removeFaculty(f.get()) : false;
	}
}
