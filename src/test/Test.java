package test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;
import model.Faculty;
import model.Student;
import persistence.FacultyDao;
import persistence.StudentDao;
import persistence.Student_FacultyDao;
import utils.StudentBuilder;

public class Test {
//	public static void main(String []argv) {
//		runExample();
//	}
	public static void runExample() {
		StudentDao sd = new StudentDao();
		FacultyDao fd = new FacultyDao();
		List<Student> students;
		List<Faculty> faculties;
		
		generateTestFaculties(3);
		generateTestStudents(10);
		students = sd.getAllStudents();
		faculties = fd.getAllFaculties();
		enrollStudentsAtFacuties(students, faculties);
	}
	
	private static void enrollStudentsAtFacuties(List<Student> s, List<Faculty> f) {
		ThreadLocalRandom tlr = ThreadLocalRandom.current();
		Student_FacultyDao sfd = new Student_FacultyDao();
		
		s.forEach(ss -> {
			int i = 0, fi;
			List<Integer> l = new ArrayList<>();
		
			while (i < 2) {
				fi = tlr.nextInt(3);
				if (!l.contains(fi)) {
					i++;
					l.add(fi);
					ss.addFaculty(f.get(fi));
				}
			}
			sfd.insert(ss);
		});
	}

	private static void generateTestStudents(int numOfStudents) {
		StudentDao sd = new StudentDao();
		ThreadLocalRandom tlr = ThreadLocalRandom.current();
		
		for (int i = 0; i < numOfStudents; i++) {
			sd.addStudent(new StudentBuilder()
					.withName("Name" + i)
					.withAge(tlr.nextInt(18, 25))
					.withSurname("Surname" + i)
					.withCnp(1234 + i)
					.withFinalgrade(new BigDecimal(
							((double)((int)(tlr.nextDouble(5.00, 10.00)
									* 1000)) / 1000))
							.setScale(2, BigDecimal.ROUND_HALF_UP)
							.doubleValue())
					.build());
		}
	}
	
	private static void generateTestFaculties(int numOfFaculties) {
		FacultyDao fd = new FacultyDao();
		
		for (int i = 1; i <= numOfFaculties; i++) {
			fd.addFaculty(new Faculty("Faculty" + i, "Address" + i));
		}
	}
}
