package service;

import java.sql.DriverManager;
import java.sql.SQLException;
import utils.StatementHelper;
import utils.StringHelper;
import utils.Tables;

import java.sql.*;

public abstract class DatabaseService {

	public static final String DB_NAME = "my_app";
	private static final String URL = "jdbc:mysql://localhost/";
	private static final String FULL_URL = StringHelper.append(URL, DB_NAME);
	private static final String USER = "root";
	private static final String PASS = "";
	private static boolean isDbInit = false;
	
	private static Connection initDb() throws SQLException {
		Connection con = DriverManager.getConnection(URL, USER, PASS);
		
		createDB(con);
		con.close();
		con = DriverManager.getConnection(FULL_URL, USER, PASS);
		createTables(con);
		isDbInit = true;
		return con;
	}
	
	public static Connection getConnection() {
		try {
			if (!isDbInit) {
				return initDb();
			} else {
				return DriverManager.getConnection(FULL_URL, USER, PASS);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static boolean checkCredentials(String user, String pass) {
		return user.equals(USER) && pass.equals(PASS);
	}
	
	private static void createDB(Connection con) throws SQLException {
		StatementHelper.executeUpdate
			(con, StringHelper.append("DROP DATABASE ", DB_NAME));
		StatementHelper.executeUpdate
			(con, StringHelper.append("CREATE DATABASE ", DB_NAME));
	}
	
	private static void createTables(Connection con) {
//		Thread t1 = new Thread(() -> createFacultyTable(con));
//		Thread t2 = new Thread(() -> createStudentTable(con));
//
//		t1.start();
//		t2.start();
//		try {
//			t1.join();
//			t2.join();
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
		createFacultyTable(con);
		createStudentTable(con);
		createJunctionTable(con);
	}
	
	private static void createJunctionTable(Connection con) {
		StatementHelper.executeUpdate(con,
			new StringBuilder("CREATE TABLE ")
			.append(Tables.STUDENT_FACULTY.str)
			.append(" (CNP BIGINT(20) NOT NULL, ")
			.append("ID INT NOT NULL, ")
			.append("PRIMARY KEY (CNP, ID), ")
			.append("FOREIGN KEY (CNP) ")
			.append("REFERENCES ")
			.append(Tables.STUDENT)
			.append(" (CNP) ")
			.append("ON DELETE CASCADE, ")
			.append("FOREIGN KEY (ID) ")
			.append("REFERENCES ")
			.append(Tables.FACULTY)
			.append(" (ID) ")
			.append("ON DELETE CASCADE)")
			.toString());
	}
	
	private static void createStudentTable(Connection con) {
		StatementHelper.executeUpdate(con,
			new StringBuilder("CREATE TABLE ")
			.append(Tables.STUDENT.str)
			.append(" (CNP BIGINT(20) not NULL, ")
			.append(" NAME VARCHAR(50), ")
			.append("SURNAME VARCHAR(50), ")
			.append("AGE INTEGER, ")
			.append("FINAL_GRADE DOUBLE, ")
			.append("PRIMARY KEY ( CNP ))")
			.toString());
	}
	
	private static void createFacultyTable(Connection con) {
		StatementHelper.executeUpdate(con, 
			new StringBuilder("CREATE TABLE ")
			.append(Tables.FACULTY.str)
			.append(" (ID INT not NULL AUTO_INCREMENT, ")
			.append("NAME VARCHAR(50), ")
			.append("ADDRESS VARCHAR(50), ")
			.append("PRIMARY KEY ( ID ))")
			.toString());
	}
}
