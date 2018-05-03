package utils;

public enum Tables {
	STUDENT("STUDENT"), FACULTY("FACULTY"), STUDENT_FACULTY("STUDENT_FACULTY");
	
	public String str;
	
	Tables(String str) {
		this.str = str;
	}
}
