package com.example.demo.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class WithSuperClass extends SuperDao {

	private PreparedStatement ps;

	public int countCars() {

		int ret = 0;

		connect();

		String SQL = "SELECT count(*) FROM cars";
		try {
			ps = con.prepareStatement(SQL);

			ResultSet rs = ps.executeQuery();

			rs.next();

			ret = rs.getInt("count(*)");

		} catch (SQLException e) {
			System.err.println(e);
		} finally {
			close();
		}
		return ret;
	}

	public static void main(String[] args) {
		WithSuperClass wsc = new WithSuperClass();
		System.out.println(wsc.countCars());
	}
}
