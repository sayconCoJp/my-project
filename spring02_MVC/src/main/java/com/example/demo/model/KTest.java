package com.example.demo.model;

import java.util.Scanner;

public class KTest {
	public static void main(String[] args) {

		System.out.println("0-9の整数で数を当ててください!");
		Scanner sc = new Scanner(System.in);
		int guess = sc.nextInt();
		sc.close();

		Kazuate k = new Kazuate();
		k.checkAnswer(guess);

		System.out.println(k.getMessage());
		System.out.println(k);

	}
}
