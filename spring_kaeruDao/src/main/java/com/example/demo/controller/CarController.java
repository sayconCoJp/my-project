package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.dao.CarsDao;
import com.example.demo.model.CarDto;

/**
 * 車に関連するリクエストを処理するコントローラクラス。
 */
@Controller
public class CarController {

	private final CarsDao carsDao;

	/**
	 * CarControllerのコンストラクタ。CarsDaoを初期化する。
	 */
	public CarController() {
		this.carsDao = new CarsDao();
	}

	/**
	 * 車の一覧を表示する。
	 *
	 * @param model ビューに渡すデータを保持するオブジェクト
	 * @return 車の一覧を表示するビュー名
	 */
	@GetMapping("/")
	public String showCars(Model model) {
		List<CarDto> carsList = carsDao.getCarsList();
		model.addAttribute("carsList", carsList);
		return "main";
	}

	/**
	 * 車のリストを指定された順序で並び替えて表示する。
	 *
	 * @param order 並び替え順序（デフォルトは昇順）
	 * @param model ビューに渡すデータを保持するオブジェクト
	 * @return 並び替えた車の一覧を表示するビュー名
	 */
	@GetMapping("/order")
	public String orderCars(@RequestParam(defaultValue = "asc") String order, Model model) {
		boolean isAscending = !order.equals("desc");
		List<CarDto> carsList = carsDao.getSortedCarsList("price", isAscending);
		model.addAttribute("carsList", carsList);
		return "main";
	}

	/**
	 * 検索クエリに基づいて車を検索し、その結果を表示する。
	 *
	 * @param query 検索キーワード
	 * @param model ビューに渡すデータを保持するオブジェクト
	 * @return 検索結果を表示するビュー名
	 */
	@GetMapping("/search")
	public String searchCars(@RequestParam String query, Model model) {
		List<CarDto> carsList = carsDao.searchCarsList(query);
		model.addAttribute("carsList", carsList);
		return "main";
	}
}
