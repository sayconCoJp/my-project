package com.example.demo.model.dto;

import java.io.Serializable; // オブジェクトのバイト列化（シリアライズ）を可能にするインターフェース
import java.time.LocalDateTime; // 日付と時刻を扱うためのクラス

public class CarDto implements Serializable {

	/** 車のID（データベースの主キーを想定） */
	private int carId;

	/** 車の名前またはモデル名 */
	private String name;

	/** 車の価格（整数型で金額を格納） */
	private int price;

	/**
	 * 論理削除された日時を文字列として格納。 DBのカラムが date/datetime であっても、 プログラム側で文字列として管理する場合がある。 null
	 * や空文字の場合は「未削除」を表す想定。
	 */
	private String deletedAt;

	/**
	 * デフォルトコンストラクタ（引数なし） JavaBeans ルールに従い、 new CarsDto() のように引数無しで生成可能にする。
	 */
	public CarDto() {
	}

	/**
	 * フィールドをまとめて初期化するコンストラクタ。
	 * 
	 * @param carId     車のID
	 * @param name      車の名前
	 * @param price     車の価格
	 * @param deletedAt 論理削除された日時（未削除の場合はnullや空文字）
	 */
	public CarDto(int carId, String name, int price, String deletedAt) {
		this.carId = carId;
		this.name = name;
		this.price = price;
		this.deletedAt = deletedAt;
	}

	/**
	 * 車のIDを取得するメソッド。
	 * 
	 * @return carId
	 */
	public int getCarId() {
		return carId;
	}

	/**
	 * 車のIDを設定するメソッド。
	 * 
	 * @param carId セットしたいID
	 */
	public void setCarId(int carId) {
		this.carId = carId;
	}

	/**
	 * 車の名前を取得するメソッド。
	 * 
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * 車の名前を設定するメソッド。
	 * 
	 * @param name セットしたい車の名称
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 車の価格を取得するメソッド。
	 * 
	 * @return price
	 */
	public int getPrice() {
		return price;
	}

	/**
	 * 車の価格を設定するメソッド。
	 * 
	 * @param price セットしたい価格
	 */
	public void setPrice(int price) {
		this.price = price;
	}

	/**
	 * 論理削除された日時を取得するメソッド。
	 * 
	 * @return deletedAt 物理削除されず、論理削除された場合は日時文字列を格納
	 */
	public String getDeletedAt() {
		return deletedAt;
	}

	/**
	 * 論理削除された日時を設定するメソッド。
	 * 
	 * @param deletedAt 論理削除した日時
	 */
	public void setDeletedAt(String deletedAt) {
		this.deletedAt = deletedAt;
	}

	/**
	 * デバッグやログ出力などでオブジェクト内容を簡単に把握できるよう、 フィールドの値を文字列として整形して返す。
	 * 
	 * @return CarsDtoのフィールド情報を整形した文字列
	 */
	@Override
	public String toString() {
		return "CarsDto [carId=" + carId + ", name=" + name + ", price=" + price + ", deletedAt=" + deletedAt + "]";
	}

	/**
	 * テスト用のメインメソッド。 単体で実行した際、CarsDtoオブジェクトを生成して内容を標準出力に表示する。
	 * 
	 * @param args コマンドライン引数（未使用）
	 */
	public static void main(String[] args) {

		// 日付部分をString化して渡す（例としてLocalDateTimeを利用）
		CarDto car = new CarDto(16, "hustler", 200, LocalDateTime.now().toLocalDate().toString());
		// 生成したCarsDtoをコンソールに表示して確認
		System.out.println(car);
	}
}
