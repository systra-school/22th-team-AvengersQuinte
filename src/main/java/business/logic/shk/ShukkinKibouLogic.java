/* 
* ファイル名：ShukkinKibouLogic.java
 *
 * 変更履歴
 * 1.0  2010/10/06 Kazuya.Naraki
 */
package business.logic.shk;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import business.db.dao.shk.ShukkinKibouDao;
import business.dto.LoginUserDto;
import business.dto.shk.ShukkinKibouKakuninDto;
import business.dto.shk.ShukkinKibouNyuuryokuDto;

/**
 * 説明：希望出勤日入力処理のロジック
 * 
 * @author naraki
 *
 */
public class ShukkinKibouLogic {

	/**
	 * 出勤希望確認画面に表示するリストを取得する。 戻り値・・・社員分の希望シフトリストのリスト
	 * 
	 * @param yearMonth 年月
	 * @return 出勤希望Dtoリストのリスト
	 * @author naraki
	 */
	public List<List<ShukkinKibouKakuninDto>> getShukkinKibouKakuninDtoList(String yearMonth) throws SQLException {

		// Dao
		ShukkinKibouDao dao = new ShukkinKibouDao();

		// シフト情報を取得する。
		List<List<ShukkinKibouKakuninDto>> kakuninDtoListList = dao.getShiftTblListList(yearMonth);

		return kakuninDtoListList;
	}
//    
//    public List<List<ShukkinKibouNyuuryokuDto>> getShukkinKibouNyuuryokuDtoList(String yearMonth) throws SQLException{
//
//        // Dao
//        ShukkinKibouDao dao = new ShukkinKibouDao();
//
//        // シフト情報を取得する。
//        List<List<ShukkinKibouNyuuryokuDto>> nyuuryokuDtoListList = dao.getShiftTblData(yearMonth);
//
//        return nyuuryokuDtoListList;
//    }

	public void registKibouShift(List<List<ShukkinKibouNyuuryokuDto>> nyuuryokuDtoListList, LoginUserDto loginUserDto)
			throws SQLException {
		// Dao
		ShukkinKibouDao dao = new ShukkinKibouDao();
		// コネクション
		Connection connection = dao.getConnection();

		// トランザクション処理
		connection.setAutoCommit(false);

		try {
			// 人数分のループ
			for (List<ShukkinKibouNyuuryokuDto> shukkinKibouNyuuryokuDtoList : nyuuryokuDtoListList) {
				// 日数分ループ
				for (ShukkinKibouNyuuryokuDto shukkinKibouNyuuryokuDto : shukkinKibouNyuuryokuDtoList) {

					// 社員ID
					String shainId = shukkinKibouNyuuryokuDto.getShainId();
					// 対象年月
					String yearMonthDay = shukkinKibouNyuuryokuDto.getYearMonthDay();

					//レコードの存在を確認する
					boolean isData = dao.isData(shainId, yearMonthDay);
					
					if(isData) {
						//更新
						dao.updateShiftTbl(shukkinKibouNyuuryokuDto, loginUserDto);
					}else {
						//登録
						dao.registShiftTbl(shukkinKibouNyuuryokuDto, loginUserDto);
					}
				}
			}

		} catch (SQLException e) {
			// ロールバック処理
			connection.rollback();
			// 切断
			connection.close();

			throw e;

		}

		// コミット
		connection.commit();
		// 切断
		connection.close();

	}
}
