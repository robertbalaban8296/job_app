package utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import service.DatabaseService;

public class StatementHelper {
	public static int executeUpdate(Connection con, String sql) {
		try (PreparedStatement stm = con.prepareStatement(sql)){
			return stm.executeUpdate();
		} catch (SQLException e) {
			if (!StringHelper.append("DROP DATABASE ",
					DatabaseService.DB_NAME).equals(sql)) {
				e.printStackTrace();
			}
		}
		return 0;
	}
	
	public static int executeUpdate(String sql) {
		try (Connection con = DatabaseService.getConnection()) {
			return executeUpdate(con, sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
}
