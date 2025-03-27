package com.example.demo.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDao extends SuperDao {

	// ログインメソッド。ユーザーIDとパスワードを受け取り、ログイン成功かどうかを返す
	public boolean login(String id, String pass) {
		boolean isSuccess = false;

		// ユーザーIDとパスワードに一致するレコードが存在するかをカウントするクエリ
		String query = "SELECT COUNT(*) FROM login_user WHERE login_id = ? AND password = ?";

		// データベースに接続する
		connect();

		// try-with-resourcesを使用してPreparedStatementとResultSetを自動的にクローズ
		try (PreparedStatement ps = con.prepareStatement(query)) {

			// クエリの最初のプレースホルダーにユーザーIDをセット
			ps.setString(1, id);
			// クエリの二番目のプレースホルダーにパスワードをセット
			ps.setString(2, pass);

			// クエリを実行して結果セットを取得
			ResultSet rs = ps.executeQuery();

			// 結果セットが存在し、最初のカラムの値が0より大きければログイン成功
			if (rs.next()) {
				isSuccess = rs.getInt("COUNT(*)") > 0;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// データベース接続を閉じる
			close();
		}

		// ログイン成功または失敗を返す
		return isSuccess;
	}
	
	public static void main(String[] args) {
		LoginDao loginDao = new LoginDao();
		boolean success = loginDao.login("imai", "p");
		System.out.println(success);
	}
}