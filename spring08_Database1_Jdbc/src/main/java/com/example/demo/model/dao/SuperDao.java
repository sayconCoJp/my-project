package com.example.demo.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Daoクラス - 各daoの基底クラス データベースへの接続、切断、クエリ実行など各dao共通の機能を実装する
 * 
 * @author Say Consulting Group
 * @version 1.0.0
 */
public class SuperDao {

	/** DB接続オブジェクト */
	protected Connection con = null;

	/** DB接続情報 */
	private static final String DB_URI = "jdbc:mysql://localhost:3306/sip_a?characterEncoding=utf8&"
			+ "useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true";
	private static final String DB_USER = "newuser";
	private static final String DB_PASS = "0";

	/**
	 * connectメソッド データベースへの接続を開始する
	 */
	protected void connect() {
		try {
			// データベース接続用ドライバをロード
			// javaSE8から不要な処理となったが、Tomcatを利用する場合は必要なので、記述
			Class.forName("com.mysql.cj.jdbc.Driver");
			// ドライバを用いてデータベースへの接続を開く
			con = DriverManager.getConnection(DB_URI, DB_USER, DB_PASS);
			System.out.println("データベースへの接続に成功しました。");
		} catch (SQLException e) {
			String errMsg = "データベースへの接続時に問題が発生しました。";
			System.err.println(errMsg);
			System.err.println(e);
		} catch (ClassNotFoundException e) {
			String errMsg = "データベース接続用クラスが見つかりません。";
			System.err.println(errMsg);
			System.err.println(e);
		}
	}

	/**
	 * closeメソッド DBとの接続を切断する 各オブジェクトを再初期化する
	 */
	protected void close() {
		try {
			if (con != null) {
				con.close();
				con = null;
			}
			System.out.println("データベースからの切断に成功しました。");
		} catch (SQLException e) {
			String errMsg = "データベースからの切断時に問題が発生しました。";
			System.err.println(errMsg);
			System.err.println(e);
		}
	}
}