package utils;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import service.DatabaseService;

public class StatementBuilder {
	private PreparedStatement stm;
	private int i;
	
	public StatementBuilder(String sql) throws SQLException {
		stm = DatabaseService.getConnection().prepareStatement(sql);
		i = 1;
	}
	
	public StatementBuilder(PreparedStatement stm) {
		this.stm = stm;
		i = 1;
	}
	
	public StatementBuilder setInt(int val) throws SQLException {
		stm.setInt(i++, val);
		return this;
	}
	
	public StatementBuilder setDouble(double val) throws SQLException {
		stm.setDouble(i++, val);
		return this;
	}
	
	public StatementBuilder setLong(long val) throws SQLException {
		stm.setLong(i++, val);
		return this;
	}
	
	public StatementBuilder setString(String val) throws SQLException {
		stm.setString(i++, val);
		return this;
	}
	
	public PreparedStatement build() {
		return stm;
	}
}
