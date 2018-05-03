package utils;

public enum StudentSortBy {
	AGE("AGE"), FINAL_GRADE("FINAL_GRADE"), NAME("NAME");
	public String s;
	
	StudentSortBy(String s) {
		this.s = s;
	}
	public String toString() {
		return s;
	}
}
