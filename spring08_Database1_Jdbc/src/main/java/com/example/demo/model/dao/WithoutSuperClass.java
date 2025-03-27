package com.example.demo.model.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class WithoutSuperClass {

	private static final String CONNECT_STRING = "jdbc:mysql://localhost:3306/sip_a?characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true";
	private static final String USER_ID = "newuser";
	private static final String PASSWORD = "0";
	private static Connection con = null;
	private static PreparedStatement ps = null;

	public int countCars() {
		try {
			con = DriverManager.getConnection(CONNECT_STRING, USER_ID, PASSWORD);
			System.out.println("データベースへの接続に成功しました。");
		} catch (SQLException e) {
			System.err.println("データベースへの接続時に問題が発生しました。");
			System.err.println(e);
		}

		int ret = 0;

		String SQL = "SELECT count(*) FROM cars";
		try {
			ps = con.prepareStatement(SQL);

			ResultSet rs = ps.executeQuery();

			rs.next();

			ret = rs.getInt("count(*)");

		} catch (SQLException e) {
			System.err.println(e);
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				if (con != null) {
					con.close();
				}
				System.out.println("データベースからの切断に成功しました。");
			} catch (SQLException e) {
				System.err.println("データベースからの切断時に問題が発生しました。");
				System.err.println(e);
			}
		}
		return ret;
	}

	public static void main(String[] args) {
		WithoutSuperClass wosc = new WithoutSuperClass();
		System.out.println(wosc.countCars());
	}
}