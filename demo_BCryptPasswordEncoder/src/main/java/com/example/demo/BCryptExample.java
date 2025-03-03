package com.example.demo;

import java.util.Scanner;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCryptExample {

	public static void main(String[] args) {


			Scanner scanner = new Scanner(System.in);

			for (int i = 0; i < 3; i++) {
				BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
				// 1. パスワードの入力
			System.out.print("登録するパスワードを入力してください: ");
			String rawPassword = scanner.nextLine();

			// 2. パスワードのハッシュ化
			String hashedPassword = encoder.encode(rawPassword);
			System.out.println("ハッシュ化されたパスワード: " + hashedPassword);

			// 3. 確認のためのパスワード入力
			System.out.print("ログイン時のパスワードを入力してください: ");
			String loginPassword = scanner.nextLine();

			// 4. ハッシュと比較して認証
			if (encoder.matches(loginPassword, hashedPassword)) {
				System.out.println("認証成功！パスワードが一致しました。");
			} else {
				System.out.println("認証失敗！パスワードが間違っています。");
			}

		}
			scanner.close();
	}
}
