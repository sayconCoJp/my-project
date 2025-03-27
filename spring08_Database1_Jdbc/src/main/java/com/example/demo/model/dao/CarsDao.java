package com.example.demo.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.example.demo.model.dto.CarDto;

public class CarsDao extends SuperDao {

	// メソッドの開始：車の数をカウントするメソッド
	public int countCars() {

		// カウントした車の数を保持するための整数変数retを初期化
		int ret = 0;

		// データベースに接続するメソッドを呼び出し
		connect();

		// 車の総数を取得するためのSQLクエリを定義
		String sql = "SELECT count(*) FROM cars";

		// try-with-resources文を使用してPreparedStatementを作成
		try (PreparedStatement ps = con.prepareStatement(sql)) {

			// クエリを実行し、結果をResultSetに格納
			ResultSet rs = ps.executeQuery();

			// ResultSetの最初の行に移動
			rs.next();

			// ResultSetから車の総数を取得してretに代入
			ret = rs.getInt("count(*)");

		} catch (SQLException e) {
			// SQL例外が発生した場合の処理
			// エラーメッセージをコンソールに出力
			System.err.println(e);
		}

		close();

		// 車の総数を返す
		return ret;
	}

	public List<CarDto> getCarList() {

		// CarDtoのリストを作成
		ArrayList<CarDto> carList = new ArrayList<>();

		// データベースに接続するメソッドを呼び出し
		connect();

		// 'cars'テーブルから全てのデータを選択するSQLクエリを定義
		String sql = "SELECT * FROM cars";

		try (PreparedStatement ps = con.prepareStatement(sql)) {

			// クエリを実行し、結果をResultSetに格納
			ResultSet rs = ps.executeQuery();

			// ResultSetを繰り返し処理し、各車のデータを取得
			while (rs.next()) {
				// CarDtoオブジェクトを生成
				CarDto car = new CarDto();

				// CarDtoにcar_id, name, price, deleted_atを設定
				car.setCarId(rs.getInt("car_id"));
				car.setName(rs.getString("name"));
				car.setPrice(rs.getInt("price"));
				car.setDeletedAt(rs.getString("deleted_at"));

				// 取得した車のデータをリストに追加
				carList.add(car);
			}
		} catch (SQLException e) {
			// SQL例外が発生した場合の処理
			System.err.println(e);
		}
		close();

		// 取得したリストを返す
		return carList;
	}

	// 特定のIDを持つ車の情報をデータベースから取得するメソッド
	public CarDto getCar(int id) {

		// CarDtoの新しいインスタンスを生成
		CarDto car = new CarDto();

		// データベースへの接続を行う
		connect();

		// 指定されたcar_idに基づいて車の情報を取得するSQLクエリ
		String sql = "SELECT * FROM cars WHERE car_id = ?";

		try (PreparedStatement ps = con.prepareStatement(sql)) {

			// クエリのパラメータ（car_id）を設定する
			ps.setInt(1, id);

			// クエリを実行し、結果をResultSetに格納する
			ResultSet rs = ps.executeQuery();

			// 結果セットにデータが存在する場合のみ処理を行う
			if (rs.next()) {
				// ResultSetから車の情報を取得し、CarDtoにセットする
				car.setCarId(rs.getInt("car_id"));
				car.setName(rs.getString("name"));
				car.setPrice(rs.getInt("price"));
				car.setDeletedAt(rs.getString("deleted_at"));
			}

		} catch (SQLException e) {
			// SQL例外が発生した場合、エラーをコンソールに出力する
			System.err.println(e);
		}
		close();

		// 取得した車の情報を含むCarDtoを返す
		return car;
	}

	// 任意のフィールドで昇順または降順に並べ替えるメソッド
	public List<CarDto> getSortedCarList(String sortField, boolean isAscending) {
		ArrayList<CarDto> carList = new ArrayList<>();

		connect();

		// sortFieldが有効なフィールド名であるか確認
		if (!isValidSortField(sortField)) {
			throw new IllegalArgumentException("Invalid sort field: " + sortField);
		}

		String sortOrder = isAscending ? "ASC" : "DESC";
		String sql = "SELECT * FROM cars ORDER BY " + sortField + " " + sortOrder;

		try (PreparedStatement ps = con.prepareStatement(sql)) {

			// クエリを実行し、結果をResultSetに格納
			ResultSet rs = ps.executeQuery();

			// ResultSetを繰り返し処理し、各車のデータを取得
			while (rs.next()) {
				// 一時的に車の情報を保持するCarDtoオブジェクトのインスタンスを生成
				CarDto car = new CarDto();

				// CarDtoにcar_id, name, price, deleted_atを設定
				car.setCarId(rs.getInt("car_id"));
				car.setName(rs.getString("name"));
				car.setPrice(rs.getInt("price"));
				car.setDeletedAt(rs.getString("deleted_at"));

				// 取得した車のデータをリストに追加
				carList.add(car);
			}
		} catch (SQLException e) {
			// SQL例外が発生した場合の処理
			System.err.println(e);
		}
		close();

		// 取得したリストを返す
		return carList;
	}
	
	// キーワードに基づいて車を検索し、その結果をリストに格納して返すメソッド
	public List<CarDto> searchCarList(String keyword) {

	    // CarDtoのリストを生成
	    List<CarDto> carList = new ArrayList<>();

	    // データベースへの接続を行う
	    connect();

	    // 指定されたキーワードに基づいて車の情報を検索するSQLクエリ
	    String sql = "SELECT * FROM cars WHERE name LIKE ?";

	    try (PreparedStatement ps = con.prepareStatement(sql)) {

	        // クエリのパラメータ（nameの検索キーワード）をあいまい検索で設定する
	        ps.setString(1, "%" + keyword + "%");

	        // クエリを実行し、結果をResultSetに格納する
	        ResultSet rs = ps.executeQuery();

	        // 結果セットの各行を繰り返し処理し、車の情報を取得する
	        while (rs.next()) {
	            // 一時的に車の情報を保持するCarDtoの新しいインスタンスを生成
	            CarDto car = new CarDto();

	            // ResultSetから車の情報を取得し、CarDtoにセットする
	            car.setCarId(rs.getInt("car_id"));
	            car.setName(rs.getString("name"));
	            car.setPrice(rs.getInt("price"));
	            car.setDeletedAt(rs.getString("deleted_at"));

	            // 取得した車の情報をリストに追加する
	            carList.add(car);
	        }
	    } catch (SQLException e) {
	        // SQL例外が発生した場合、エラーをコンソールに出力する
	        System.err.println(e);
	    }
	    close();

	    // 検索結果を含むリストを返す
	    return carList;
	}


	// 指定されたフィールド名が有効かどうかをチェックするメソッド
	private boolean isValidSortField(String field) {
		// 許可されたフィールド名のリスト
		List<String> validFields = Arrays.asList("car_id", "name", "price", "deleted_at");
		return validFields.contains(field);
	}
	
	// 新しい車のデータをデータベースに追加するメソッド
	public int addCar(CarDto car) {

	    // 返り値として使用する整数型変数を初期化
	    int ret = 0;

	    // データベースへの接続を行う
	    connect();

	    // 新しい車を追加するためのSQL文を定義
	    String sql = "INSERT INTO cars(name, price) VALUES(?, ?);";

	    try (PreparedStatement ps = con.prepareStatement(sql)) {

	        // PreparedStatementに車の名前と価格をセット
	        ps.setString(1, car.getName());
	        ps.setInt(2, car.getPrice());

	        // SQL文を実行し、処理された行数をretに代入
	        ret = ps.executeUpdate();

	    } catch (SQLException e) {
	        // SQL処理中に例外が発生した場合のエラーハンドリング
	        System.err.println(e);
	    }

	    close();

	    // 処理された行数を返す
	    return ret;
	}
	
	// データベース内の特定の車の情報を更新するためのメソッド
	public int updateCar(CarDto car) {
	    // 更新に成功したレコードの数を保持する変数を初期化
	    int ret = 0;

	    // データベースに接続
	    connect();

	    // 車の情報を更新するSQL文を定義
	    String sql = "UPDATE cars SET name = ?, price = ?, deleted_at = ? WHERE car_id = ?";

	    try (PreparedStatement ps = con.prepareStatement(sql)) {

	        // SQL文のパラメータを設定
	        ps.setString(1, car.getName()); // 車の名前
	        ps.setInt(2, car.getPrice()); // 価格
	        ps.setString(3, car.getDeletedAt()); // 削除日時
	        ps.setInt(4, car.getCarId()); // 車のID

	        // SQL文を実行し、影響を受けたレコードの数を取得
	        ret = ps.executeUpdate();

	    } catch (SQLException e) {
	        // SQL例外が発生した場合、エラーを出力
	        System.err.println(e);
	    }

	    close();

	    // 更新に成功したレコードの数を返す
	    return ret;
	}

	public static void main(String[] args) {
		CarsDao carsDao = new CarsDao();

		List<CarDto> carsList = new ArrayList<CarDto>();
		
		carsList = carsDao.searchCarList("ー");
		for (CarDto carDto : carsList) {
			System.out.println(carDto);
		}
		System.out.println(carsDao.getCar(2));
		
		CarDto car = new CarDto(43, "hustler", 200, LocalDateTime.now().toLocalDate().toString());
		carsDao.addCar(car);
		
		car = new CarDto(43, "jeep", 200, LocalDateTime.now().toLocalDate().toString());
		carsDao.updateCar(car);
		
	}
}
