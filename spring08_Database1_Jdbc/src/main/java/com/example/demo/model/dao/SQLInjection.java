package com.example.demo.model.dao;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class SQLInjection extends SuperDao {

	public static String SQL = "SELECT * FROM customers where customer_id = ";

	PreparedStatement ps = null;
	ResultSet rs = null;

	public void getACustomer() {

		try {
			connect();
			System.out.println("customer_idを整数値で指定してください");
			try (Scanner sc = new Scanner(System.in)) {
				String customer_id = sc.nextLine();
				SQL += customer_id;
			}

			ps = con.prepareStatement(SQL);

			rs = ps.executeQuery();
			while (rs.next()) {
				System.out.print(rs.getInt("customer_id") + ":");
				System.out.print(rs.getString("name") + ":");
				System.out.print(rs.getString("mail") + ":");
				System.out.print(rs.getString("mobile") + ":");
				System.out.println(rs.getString("pass"));
			}
		} catch (SQLException e) {
			System.err.println("データベースへの接続時に問題が発生しました。");
			System.err.println(e);
		} finally {
			close();
		}
	}

	public static void main(String[] args) {
		SQLInjection sqlInjection = new SQLInjection();
		sqlInjection.getACustomer();
	}
}
