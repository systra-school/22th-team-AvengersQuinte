package action.shk;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import business.dto.LoginUserDto;
import business.dto.shk.ShukkinKibouKakuninDto;
import business.dto.shk.ShukkinKibouNyuuryokuDto;
import business.logic.comparator.MethodComparator;
import business.logic.shk.ShukkinKibouLogic;
import business.logic.utils.CheckUtils;
import business.logic.utils.ComboListUtilLogic;
import business.logic.utils.CommonUtils;
import constant.CommonConstant;
import constant.RequestSessionNameConstant;
import form.common.DateBean;
import form.shk.ShukkinKibouNyuuryokuBean;
import form.shk.ShukkinKibouNyuuryokuForm;

public class ShukkinKibouNyuuryokuRegistAction extends ShukkinKibouAbstractAction {

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest req,
			HttpServletResponse res) throws Exception {

		log.info(new Throwable().getStackTrace()[0].getMethodName());

		// フォワードキー
		String forward = CommonConstant.SUCCESS;

		// セッション
		HttpSession session = req.getSession();

		// ログインユーザ情報をセッションより取得
		LoginUserDto loginUserDto = (LoginUserDto) session
				.getAttribute(RequestSessionNameConstant.SESSION_CMN_LOGIN_USER_INFO);
		// 社員IDを取得する。
		String shainId = loginUserDto.getShainId();
		req.setAttribute("shainId", shainId);

		// フォーム
		ShukkinKibouNyuuryokuForm shukkinKibouNyuuryokuForm = (ShukkinKibouNyuuryokuForm) form;

		// 画面のリスト情報
		List<ShukkinKibouNyuuryokuBean> shukkinKibouNyuuryokuBeanList = shukkinKibouNyuuryokuForm
				.getShukkinKibouNyuuryokuBeanList();

		// 対象年月
		// String yearMonth = CommonUtils.getFisicalDay(CommonConstant.yearMonthNoSl);
		String yearMonth = shukkinKibouNyuuryokuForm.getYearMonth();
		// ロジック生成
		ShukkinKibouLogic shukkinKibouLogic = new ShukkinKibouLogic();

		// 対象年月の月情報を取得する。
		List<DateBean> dateBeanList = CommonUtils.getDateBeanList(yearMonth);

		// 出勤希望日を取得する
		List<List<ShukkinKibouKakuninDto>> kakuninDtoListList = shukkinKibouLogic
				.getShukkinKibouKakuninDtoList(yearMonth);
		// 他のクラスでは宣言されているが、このクラスだと名前が被っているので一旦コメントアウト
		// List<ShukkinKibouNyuuryokuBean> shukkinKibouNyuuryokuBeanList = new
		// ArrayList<ShukkinKibouNyuuryokuBean>();

		// フォームデータをDtoに変換する
		List<List<ShukkinKibouNyuuryokuDto>> nyuuryokuDtoListList = this.formToDto(shukkinKibouNyuuryokuBeanList,
				dateBeanList);

		// 登録・更新処理
		shukkinKibouLogic.registKibouShift(nyuuryokuDtoListList, loginUserDto);

		// シフトIDを取得する
		// 参考にしているものではMapになっているけど、オリジナルでやってみる
		// Map<String,List<TsukibetsuShiftDto>> tsukibetsuShiftDtoMap =
		// tsukibetsuShiftLogic.getTsukibetsuShiftDtoMap(yearMonth, true);

		// セレクトボックスの取得
		ComboListUtilLogic comboListUtils = new ComboListUtilLogic();
		Map<String, String> shiftCmbMap = comboListUtils.getComboShift(true);
		Map<String, String> yearMonthCmbMap = comboListUtils.getComboYearMonth(
				CommonUtils.getFisicalDay(CommonConstant.yearMonthNoSl), 3, ComboListUtilLogic.KBN_YEARMONTH_NEXT,
				false);

		// ③ShukkinKibouLogic.getShukkinKibouKakuninDtoList()を呼び出す

		if (CheckUtils.isEmpty(kakuninDtoListList)) {
			// データなし
			forward = CommonConstant.NODATA;
		} else {
			// データあり
			shukkinKibouNyuuryokuBeanList = dtoToBean(kakuninDtoListList, loginUserDto);

			// フォームにデータをセットする
			shukkinKibouNyuuryokuForm.setShiftCmbMap(shiftCmbMap);
			shukkinKibouNyuuryokuForm.setYearMonthCmbMap(yearMonthCmbMap);
			shukkinKibouNyuuryokuForm.setShukkinKibouNyuuryokuBeanList(shukkinKibouNyuuryokuBeanList);
			shukkinKibouNyuuryokuForm.setDateBeanList(dateBeanList);
			shukkinKibouNyuuryokuForm.setYearMonth(yearMonth);

		}
		return mapping.findForward(forward);

	}

//		if (CheckUtils.isEmpty(tsukibetsuShiftDtoMap)) {
//			// データなし
//			ShukkinKibouNyuuryokuBean shukkinKibouNyuuryokuBean = new ShukkinKibouNyuuryokuBean();
//			shukkinKibouNyuuryokuBean.setShainId(loginUserDto.getShainId());
//			shukkinKibouNyuuryokuBean.setShainName(loginUserDto.getShainName());
//			shukkinKibouNyuuryokuBean.setRegistFlg(true);
//
//			shukkinKibouNyuuryokuBeanList.add(shukkinKibouNyuuryokuBean);
//		} else {
//			// データあり
//			shukkinKibouNyuuryokuBeanList = dtoToBean(tsukibetsuShiftDtoMap, loginUserDto);
//		}
//
//		// フォームにデータをセットする
//		shukkinKibouNyuuryokuForm.setShiftCmbMap(shiftCmbMap);
//		shukkinKibouNyuuryokuForm.setYearMonthCmbMap(yearMonthCmbMap);
//		shukkinKibouNyuuryokuForm.setShukkinKibouNyuuryokuBeanList(shukkinKibouNyuuryokuBeanList);
//		shukkinKibouNyuuryokuForm.setDateBeanList(dateBeanList);
//		shukkinKibouNyuuryokuForm.setYearMonth(yearMonth);
//		// ページング用
//		shukkinKibouNyuuryokuForm.setOffset(0);
//		shukkinKibouNyuuryokuForm.setCntPage(1);
//		shukkinKibouNyuuryokuForm.setMaxPage(CommonUtils.getMaxPage(tsukibetsuShiftDtoMap.size(), SHOW_LENGTH));
//
//		return mapping.findForward(forward);
//	}

	private List<ShukkinKibouNyuuryokuBean> dtoToBean(List<List<ShukkinKibouKakuninDto>> kakuninDtoListList,
			LoginUserDto loginUserDto)
			throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		List<ShukkinKibouNyuuryokuBean> shukkinKibouKakuninBeanList = new ArrayList<ShukkinKibouNyuuryokuBean>();

		// 社員IDを変数に入れる
		String loginUserShainId = loginUserDto.getShainId();

// 社員分のループ
		for (List<ShukkinKibouKakuninDto> kakuninDtoList : kakuninDtoListList) {

// 実行するオブジェクトの生成
			ShukkinKibouNyuuryokuBean shukkinKibouNyuuryokuBean = new ShukkinKibouNyuuryokuBean();

// メソッドの取得
			Method[] methods = shukkinKibouNyuuryokuBean.getClass().getMethods();

// ソートを行う
			Comparator<Method> asc = new MethodComparator();
			Arrays.sort(methods, asc); // 配列をソート

// 社員名
			String shainId = "";
			String shainName = "";

			// int index = 0;

			// for (int i = 0; i < methods.length; i++) {
			for (ShukkinKibouKakuninDto kibouKakuninDto : kakuninDtoList) {

//// "setShiftIdXX" のメソッドを動的に実行する
//				if (methods[i].getName().startsWith("setShiftId") == (LoginUserDto.getShainId())) {
//					if (index < kakuninDtoList.size()) {
//// Dtoのリストのサイズ以上のとき
//						ShukkinKibouKakuninDto kibouKakuninDto = kakuninDtoList.get(index);
//
//						shainId = kibouKakuninDto.getShainId();
//						shainName = kibouKakuninDto.getShainName();
//
//						methods[i].invoke(shukkinKibouNyuuryokuBean, kibouKakuninDto.getKibouShiftSymbol());
//						
//						
//						//登録フラグ：データがあるとき
//						shukkinKibouNyuuryokuBean.setRegistFlg(true);
//
//						
//
//					} else {
//// データなしの場合はハイフン
//						methods[i].invoke(shukkinKibouNyuuryokuBean, "-");
//						//登録フラグ：データがないとき
//						shukkinKibouNyuuryokuBean.setRegistFlg(false);
//					}
				// index++;

				// ログインしているユーザーのIDとDTOの社員IDが一致する場合のみ処理を行う。
				if (loginUserShainId.equals(kibouKakuninDto.getShainId())) {
					shainId = kibouKakuninDto.getShainId();
					shainName = kibouKakuninDto.getShainName();
					shukkinKibouNyuuryokuBean.setRegistFlg(true);
					shukkinKibouNyuuryokuBean.setShainId(shainId);
					shukkinKibouNyuuryokuBean.setShainName(shainName);

					// ログインユーザーが見つかったらループを抜ける
					break;
				}
				// ログインユーザーが見つからなかった場合の処理
				if (shainId.isEmpty()) {
					// データがない場合はハイフン
					for (Method method : methods) {
						if (method.getName().startsWith("setShiftId")) {
							method.invoke(shukkinKibouNyuuryokuBean, "-");
						}
					}
					shukkinKibouNyuuryokuBean.setRegistFlg(false);

				}
			}
			shukkinKibouKakuninBeanList.add(shukkinKibouNyuuryokuBean);
		}

		return shukkinKibouKakuninBeanList;
	}

	// formToDtoメソッド、戻り値は1か月分の出勤希望Dtoリスト
	private List<List<ShukkinKibouNyuuryokuDto>> formToDto(
			List<ShukkinKibouNyuuryokuBean> shukkinKibouNyuuryokuBeanList, List<DateBean> dateBeanList)
			throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {

		// 戻り値・・・とりあえず例に沿った形にしておく
		List<List<ShukkinKibouNyuuryokuDto>> shukkinKibouNyuuryokuDtoListList = new ArrayList<List<ShukkinKibouNyuuryokuDto>>();

		for (ShukkinKibouNyuuryokuBean shukkinKibouNyuuryokuBean : shukkinKibouNyuuryokuBeanList) {

			List<ShukkinKibouNyuuryokuDto> shukkinKibouNyuuryokuDtoList = new ArrayList<ShukkinKibouNyuuryokuDto>();

			// 登録フラグ
			boolean registFlg = shukkinKibouNyuuryokuBean.getRegistFlg();
			if (!registFlg) {
				continue;
			}

			// メソッドの取得
			Method[] methods = shukkinKibouNyuuryokuBean.getClass().getMethods();

			// ソートを行う
			Comparator<Method> asc = new MethodComparator();
			Arrays.sort(methods, asc); // 配列をソート

			int listSize = dateBeanList.size();
			int index = 0;

			for (int i = 0; i < methods.length; i++) {
				// "getShiftIdXX" のメソッドを動的に実行する
				if (methods[i].getName().startsWith("getShiftId") && index < listSize) {
					String yearMonthDay = "";

					// 対象年月取得
					yearMonthDay = dateBeanList.get(index).getYearMonthDay();

					ShukkinKibouNyuuryokuDto shukkinKibouNyuuryokuDto = new ShukkinKibouNyuuryokuDto();
					String shiftId = (String) methods[i].invoke(shukkinKibouNyuuryokuBean);

					if (CommonConstant.BLANK_ID.equals(shiftId)) {
						// 空白が選択されている場合
						shiftId = null;
					}

					// *******Dtoなのかどうか明日確認する。
					shukkinKibouNyuuryokuDto.setKibouShiftId(shiftId);
					shukkinKibouNyuuryokuDto.setShainId(shukkinKibouNyuuryokuBean.getShainId());
					shukkinKibouNyuuryokuDto.setYearMonthDay(yearMonthDay);
					// ********レジストフラッグのままなのかtrueをいれるのか確認
					shukkinKibouNyuuryokuDto.setRegistFlg(registFlg);

					shukkinKibouNyuuryokuDtoList.add(shukkinKibouNyuuryokuDto);

					index++;
				}
			}
			shukkinKibouNyuuryokuDtoListList.add(shukkinKibouNyuuryokuDtoList);
		}
		return shukkinKibouNyuuryokuDtoListList;

	}

//	private List<List<ShukkinKibouNyuuryokuDto>>  formToDto (List<ShukkinKibouNyuuryokuBean> shukkinKibouNyuuryokuBeanList
//            ,LoginUserDto loginUserDto) throws IllegalArgumentException,
//    IllegalAccessException,
//    InvocationTargetException {
//		
//		//戻り値
//		List<List<ShukkinKibouNyuuryokuDto>> shukkinKibouNyuuryokuDtoListList = new ArrayList<List<ShukkinKibouNyuuryokuDto>>();
//		
//		for(ShukkinKibouNyuuryokuBean shukkinKibouNyuuryokuBean : shukkinKibouNyuuryokuBeanList) {
//			
//			List<ShukkinKibouNyuuryokuDto> shukkinKibouNyuuryokuDtoList = new ArrayList<ShukkinKibouNyuuryokuDto>();
//		
//		//登録フラグ
//		boolean registFlg = shukkinKibouNyuuryokuBean.getRegistFlg();
//		if(!registFlg) {
//			continue;
//		}
//		
//		//メソッドの取得
//		Method[] methods = shukkinKibouNyuuryokuBean.getClass().getMethods();
//		
//		//ソートを行う
//		Comparator<Method> asc = new MethodComparator();
//		Arrays.sort(methods, asc);//配列をソート
//		
//		//int listSize = dateBeanList.size();
//		//int index = 0;
//		
////		for(int i = 0; i < methods.length; i++) {
////			//"getShiftIdXX"のメソッドを動的に実行する。
////			if(methods[i].getName().startsWith("getShiftId") && index < listSize) {
////				String yeatMonthDay = "";
////				
////				//対象年月取得
////				yeatMonthDay = dateBeanList.get(index).getYearMonthDay();
////				
////				ShukkinKibouNyuuryokuDto shukkinKibouNyuuryokuDto = new ShukkinKibouNyuuryokuDto();
////				String shiftId = (String) methods[i].invoke(shukkinKibouNyuuryokuBean);
////				
////				if(CommonConstant.BLANK_ID.equals(shiftId)) {
////					//空白が選択されている場合
////					shiftId = null;
////				}
////				
////				shukkinKibouNyuuryokuDto.setShiftId(shiftId);
////				shukkinKibouNyuuryokuDto.setShainId(shukkinKibouNyuuryokuBean.getShainId());
////				
////			}
////		}
////				
////		}
//		
//		return null;
//		
//	}

}
