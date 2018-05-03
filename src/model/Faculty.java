package model;

import java.io.Serializable;
import java.util.*;
import java.util.Optional;

public class Faculty implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6834224949560422553L;
	private int ID;
	private String name, address;
	private List<Student> students;
	
	public Faculty() {
		students = new ArrayList<>();
	}
	
	public Faculty(String name, String address) {
		setName(name);
		setAddress(address);
	}
	
	public Faculty(int ID, String name, String address) {
		setID(ID);
		setAddress(address);
		setName(name);
		students = new ArrayList<>();
	}
	
	public Faculty(List<Student> students) {
		setStudents(students);
	}
	@Override
	public boolean equals(Object o) {
		Faculty f = (Faculty) o;
		return f.getID() == this.getID();
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public List<Student> getStudents() {
		return students;
	}
	public void setStudents(List<Student> students) {
		this.students = students;
	}
	public boolean addStudent(Student s) {
		return students.add(s);
	}
	public boolean removeStudent(Student s) {
		return students.remove(s);
	}
	public boolean removeStudent(long cnp) {
		Optional<Student> s = students
				.stream()
				.filter((ss) -> ss.getCnp() == cnp)
				.findFirst();
		return s.isPresent() ? removeStudent(s.get()) : false;
	}
	@Override
	public String toString() {
		return new StringBuilder(name)
				.append(" ")
				.append(ID)
				.append(" ")
				.append(address)
				.toString();
	}
}
