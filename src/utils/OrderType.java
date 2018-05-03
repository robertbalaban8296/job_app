package utils;

public enum OrderType {
	ASC("ASC"), DESC("DESC");
	public String s;
	OrderType(String s) {
		this.s = s;
	}
	
	public String toString() {
		return s;
	}
}
