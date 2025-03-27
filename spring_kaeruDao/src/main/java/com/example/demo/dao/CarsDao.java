package com.example.demo.dao;

import java.sql.PreparedStatement; // SQL文を実行する際のパラメータ付きステートメント
import java.sql.ResultSet; // SQL実行結果の集合を表すクラス
import java.sql.SQLException; // SQLに関する例外を扱うクラス
import java.util.ArrayList;
import java.util.List;

import com.example.demo.model.CarDto;

/**
 * CarsDaoクラス
 * 
 * CarBeanやCarsBeanを使ってデータベースの「cars」テーブルを操作するためのDAO（Data Access Object）。
 * SuperDaoを継承して、データベース接続(connect)・切断(close)を行いつつ、 各種CRUD（Create, Read, Update,
 * Delete）メソッドを実装している。
 * 
 * 主なメソッド： - countCarsBean() : テーブルの車の総数を取得 - getCarsBean() : 全件の車データを取得 -
 * getACar(id) : car_id指定で1台分の車情報を取得 - getSortedCarsBean() : あるカラムで昇順または降順に並べて取得
 * - searchCarsBean() : あいまい検索により複数の車情報を取得 - addACar() : 新しい車のデータをINSERT -
 * updateACar() : 既存の車データをUPDATE
 * 
 * テスト用に main メソッドを持ち、単独起動での動作確認も可能。
 */
public class CarsDao extends SuperDao {

	/**
	 * countCarsBeanメソッド： carsテーブルに登録されている車の数をカウントして返す。
	 * 
	 * @return テーブルに登録されている車の総数
	 */
	public int countCarsBean() {

		// カウント結果を保持するための変数（初期値0）
		int ret = 0;

		// DB接続を開始
		connect();

		// 車の総数を求めるSQL
		String sql = "SELECT count(*) FROM cars";

		// try-with-resources で自動的にリソースをクローズ
		try (PreparedStatement ps = con.prepareStatement(sql)) {

			// SQLを実行し、結果をResultSetに格納
			ResultSet rs = ps.executeQuery();

			// カーソルを1レコード目に移動
			rs.next();

			// 集計関数 count(*) の結果を取得
			ret = rs.getInt("count(*)");

		} catch (SQLException e) {
			// SQL例外が発生したらエラー内容を出力
			System.err.println(e);
		}

		// DB切断
		close();

		// カウント結果を返す
		return ret;
	}

	/**
	 * getCarsBeanメソッド： carsテーブルの全レコードを取得し、CarsBeanに格納して返す。
	 * 
	 * @return CarsBean 全車両データをまとめたオブジェクト
	 */
	public List<CarDto> getCarsList() {
		List<CarDto> carsList = new ArrayList<>();
		connect();
		String sql = "SELECT * FROM cars";

		try (PreparedStatement ps = con.prepareStatement(sql)) {
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				CarDto car = new CarDto();
				car.setCarId(rs.getInt("car_id"));
				car.setName(rs.getString("name"));
				car.setPrice(rs.getInt("price"));
				car.setDeletedAt(rs.getString("deleted_at"));
				carsList.add(car);
			}
		} catch (SQLException e) {
			System.err.println(e);
		}
		close();
		return carsList;
	}

	/**
	 * getACarメソッド： 指定されたcar_idに該当する車両データを1件取得してCarBeanで返す。
	 * 
	 * @param id 取得したい車のID
	 * @return 1台分の車情報を格納したCarBean
	 */
	public CarDto getACar(int id) {

		// 返却用のCarBeanを生成
		CarDto car = new CarDto();

		// DB接続
		connect();

		// car_idを条件に指定したレコードを1件取得
		String sql = "select * from cars where car_id = ?";

		try (PreparedStatement ps = con.prepareStatement(sql)) {
			// プレースホルダに引数のidをセット
			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();

			// 該当レコードが存在する場合のみCarBeanにセット
			if (rs.next()) {
				car.setCarId(rs.getInt("car_id"));
				car.setName(rs.getString("name"));
				car.setPrice(rs.getInt("price"));
				car.setDeletedAt(rs.getString("deleted_at"));
			}

		} catch (SQLException e) {
			System.err.println(e);
		}
		// DB切断
		close();

		// 取得したCarBeanを返す
		return car;
	}

	/**
	 * getSortedCarsBeanメソッド： 指定されたカラム（sortField）と昇降順（isAscending）に従って
	 * carsテーブルのレコードを並べ替えて取得し、CarsBeanとして返す。
	 * 
	 * @param sortField   並べ替えに使用するフィールド名（例: "price"）
	 * @param isAscending trueなら昇順、falseなら降順
	 * @return ソートされた車両データを格納したCarsBean
	 */
	public List<CarDto> getSortedCarsList(String sortField, boolean isAscending) {
		List<CarDto> carsList = new ArrayList<>();
		connect();

		if (!isValidSortField(sortField)) {
			throw new IllegalArgumentException("Invalid sort field: " + sortField);
		}

		String sortOrder = isAscending ? "ASC" : "DESC";
		String sql = "SELECT * FROM cars ORDER BY " + sortField + " " + sortOrder;

		try (PreparedStatement ps = con.prepareStatement(sql)) {
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				CarDto car = new CarDto();
				car.setCarId(rs.getInt("car_id"));
				car.setName(rs.getString("name"));
				car.setPrice(rs.getInt("price"));
				car.setDeletedAt(rs.getString("deleted_at"));
				carsList.add(car);
			}
		} catch (SQLException e) {
			System.err.println(e);
		}
		close();
		return carsList;
	}

	/**
	 * isValidSortFieldメソッド： 指定されたフィールド名が許可されているかどうかを判定する。
	 * 
	 * @param field 検証対象のフィールド名
	 * @return 許可されていれば true、されていなければ false
	 */
	private boolean isValidSortField(String field) {
		// 許可されたフィールドのリスト
		String[] validFields = { "car_id", "name", "price", "deleted_at" };

		// 配列内に指定のフィールドが含まれているかをチェック
		for (String validField : validFields) {
			if (validField.equals(field)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * searchCarsBeanメソッド： キーワード（keyword）を車名に含むデータをあいまい検索し、CarsBeanにまとめて返す。
	 * 
	 * @param keyword 検索キーワード
	 * @return キーワードを含む車両データのリストを保持したCarsBean
	 */
	public List<CarDto> searchCarsList(String keyword) {
		List<CarDto> carsList = new ArrayList<>();
		connect();
		String sql = "SELECT * FROM cars WHERE name LIKE ?";

		try (PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, "%" + keyword + "%");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				CarDto car = new CarDto();
				car.setCarId(rs.getInt("car_id"));
				car.setName(rs.getString("name"));
				car.setPrice(rs.getInt("price"));
				car.setDeletedAt(rs.getString("deleted_at"));
				carsList.add(car);
			}
		} catch (SQLException e) {
			System.err.println(e);
		}
		close();
		return carsList;
	}

	/**
	 * addACarメソッド： 新しい車の情報をcarsテーブルにINSERTする。
	 * 
	 * @param aCar 追加したい車情報（CarBean）
	 * @return INSERTによって影響を受けた行数（通常は1行か0行）
	 */
	public int addACar(CarDto aCar) {

		// 影響を受けた行数を記録する変数
		int ret = 0;

		// DB接続
		connect();

		// INSERT文：car_idはAUTO_INCREMENTの想定なので指定しない
		String sql = "INSERT INTO cars(name, price) VALUES( ?, ?);";

		try (PreparedStatement ps = con.prepareStatement(sql)) {

			// 車の名前、価格をパラメータに設定
			ps.setString(1, aCar.getName());
			ps.setInt(2, aCar.getPrice());

			// 実行し、影響行数を取得
			ret = ps.executeUpdate();

		} catch (SQLException e) {
			System.err.println(e);
		}

		close();

		// 追加できた行数を返す（通常1件か0件）
		return ret;
	}

	/**
	 * updateACarメソッド： 既存のレコードを更新（車の名称、価格、deleted_at）する。
	 * 
	 * @param aCar 更新したい車情報（CarBean）
	 * @return UPDATEによって影響を受けた行数（通常は1行か0行）
	 */
	public int updateACar(CarDto aCar) {

		int ret = 0;
		// DB接続
		connect();

		// car_id を指定してレコードを更新するSQL
		String sql = "UPDATE cars SET name = ?, price = ?, deleted_at = ? WHERE car_id = ?";

		try (PreparedStatement ps = con.prepareStatement(sql)) {

			// パラメータの設定：名前、価格、削除日時、ID の順
			ps.setString(1, aCar.getName());
			ps.setInt(2, aCar.getPrice());
			ps.setString(3, aCar.getDeletedAt());
			ps.setInt(4, aCar.getCarId());

			// 実行し、更新された行数を取得
			ret = ps.executeUpdate();

		} catch (SQLException e) {
			System.err.println(e);
		}

		close();

		// 更新行数を返す
		return ret;
	}

	/**
	 * メインメソッド（テスト用） 単体でこのクラスを起動して動作確認ができるようにする。 実行時の例： 1. 現在の車数(countCarsBean) 2.
	 * （コメントアウト解除すれば）全データ取得、1台取得、ソート、検索などを試せる 3. addACar や updateACar の実行結果を表示
	 * 
	 * @param args コマンドライン引数（未使用）
	 */
	public static void main(String[] args) {
		CarsDao carsdao = new CarsDao();

		// 例： 車の総数を表示
		System.out.println(carsdao.countCarsBean());
	}
}
