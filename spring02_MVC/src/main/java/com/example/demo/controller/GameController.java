package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.model.Kazuate;

@Controller
public class GameController {

	// GET リクエストで /game にアクセスされた場合の処理
	@GetMapping("/game")
	public String playGame(String guess, Model model) {
		Kazuate kazuate = new Kazuate();
		
		int answer = Integer.parseInt(guess);
		kazuate.checkAnswer(answer);

		// モデルにオブジェクトを追加してビューに渡す
		model.addAttribute("k", kazuate);
		return "result"; // src/main/resources/templates/result.html を返す
	}
}
