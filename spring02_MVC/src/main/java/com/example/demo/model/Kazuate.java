package com.example.demo.model;

import java.util.Random;

public class Kazuate {

	private int answer;
	private String message;

	public Kazuate() {
		Random random = new Random();
		this.answer = random.nextInt(10); // 0～9 の乱数
	}

	public void checkAnswer(int guess) {
		if (answer == guess) {
			this.message = "あたり";
		} else if (answer > guess) {
			this.message = "もっと大きいよ";
		} else {
			this.message = "もっと小さいよ";
		}
	}

	public int getAnswer() {
		return answer;
	}

	public String getMessage() {
		return message;
	}

	@Override
	public String toString() {
		return "デバック用 [answer=" + answer + ", message=" + message + "]";
	}
}
