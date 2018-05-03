package utils;

public final class StringHelper {
	public static String append(String s1, String s2) {
		return new StringBuilder(s1)
				.append(s2).
				toString();
	}
}
