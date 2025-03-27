package com.example.demo.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Placeholder extends SuperDao {

	public static final String SQL = "SELECT * FROM customers where customer_id = ?";

	PreparedStatement ps = null;
	ResultSet rs = null;

	public void getACustomer() {

		try {
			connect();
			System.out.println("customer_idを整数値で指定してください");

			String customer_id;
			try (Scanner sc = new Scanner(System.in)) {
				customer_id = sc.nextLine();
			}

			ps = con.prepareStatement(SQL);
			ps.setString(1, customer_id);

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
		Placeholder placeholder = new Placeholder();
		placeholder.getACustomer();
	}
}