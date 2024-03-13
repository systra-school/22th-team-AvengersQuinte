package action.shk;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;

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
		String yearMonth = CommonUtils.getFisicalDay(CommonConstant.yearMonthNoSl);
		// String yearMonth = shukkinKibouNyuuryokuForm.getYearMonth();

		// ロジック生成
		ShukkinKibouLogic shukkinKibouLogic = new ShukkinKibouLogic();

		// 対象年月の月情報を取得する。
		List<DateBean> dateBeanList = CommonUtils.getDateBeanList(yearMonth);

		// 白石くんのに合わせるためにコメントアウトしておく
//		// 出勤希望日を取得する
//		List<List<ShukkinKibouKakuninDto>> kakuninDtoListList = shukkinKibouLogic
//				.getShukkinKibouKakuninDtoList(yearMonth);
		// 他のクラスでは宣言されているが、このクラスだと名前が被っているので一旦コメントアウト
		// List<ShukkinKibouNyuuryokuBean> shukkinKibouNyuuryokuBeanList = new
		// ArrayList<ShukkinKibouNyuuryokuBean>();

		// フォームデータをDtoに変換する
		List<List<ShukkinKibouNyuuryokuDto>> nyuuryokuDtoListList = this.formToDto(shukkinKibouNyuuryokuBeanList,
				dateBeanList);

		// 登録・更新処理
		shukkinKibouLogic.registKibouShift(nyuuryokuDtoListList, loginUserDto);

		// シフトIDを取得する
		List<List<ShukkinKibouKakuninDto>> kakuninDtoListList = shukkinKibouLogic
				.getShukkinKibouKakuninDtoList(yearMonth);

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
			ShukkinKibouNyuuryokuBean shukkinKibouNyuuryokuBean = new ShukkinKibouNyuuryokuBean();
			shukkinKibouNyuuryokuBean.setShainId(loginUserDto.getShainId());
			shukkinKibouNyuuryokuBean.setShainName(loginUserDto.getShainName());
			shukkinKibouNyuuryokuBean.setRegistFlg(false);

			shukkinKibouNyuuryokuBeanList.add(shukkinKibouNyuuryokuBean);

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

	private List<ShukkinKibouNyuuryokuBean> dtoToBean(List<List<ShukkinKibouKakuninDto>> kakuninDtoListList,
			LoginUserDto loginUserDto)
			throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {

		List<ShukkinKibouNyuuryokuBean> shukkinKibouKakuninBeanList = new ArrayList<ShukkinKibouNyuuryokuBean>();

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
			String d = "";
			String d2 = "";
			String c = "";
			String c2 = "";

			// とりあえず復活
			int index = 0;
			int listSize = kakuninDtoList.size();

			for (int i = 0; i < methods.length; i++) {

				// "setShiftIdXX" のメソッドを動的に実行する
				if (methods[i].getName().startsWith("setShiftId") && listSize > index) {

					ShukkinKibouKakuninDto shukkinKibouKakuninDto = kakuninDtoList.get(index);

					if (Objects.nonNull(shukkinKibouKakuninDto.getKibouShiftId())) {
						d = methods[i].getName();
						d2 = d.substring(10);
						c = shukkinKibouKakuninDto.getYearMonthDay();
						c2 = c.substring(6);
						if (!d2.equals(c2)) {
							continue;
						}
					}
					// メソッドの実行
					methods[i].invoke(shukkinKibouNyuuryokuBean, shukkinKibouKakuninDto.getKibouShiftId());
                    
                    shainId = shukkinKibouKakuninDto.getShainId();
                    shainName = shukkinKibouKakuninDto.getShainName();

                    index ++;
				}
				}

					shukkinKibouNyuuryokuBean.setShainId(shainId);
					shukkinKibouNyuuryokuBean.setShainName(shainName);
					shukkinKibouNyuuryokuBean.setRegistFlg(true);

					shukkinKibouKakuninBeanList.add(shukkinKibouNyuuryokuBean);

				}
			
		return shukkinKibouKakuninBeanList;
	}

	// formToDtoメソッド、戻り値は1か月分の出勤希望Dtoリスト
	private List<List<ShukkinKibouNyuuryokuDto>> formToDto(
			List<ShukkinKibouNyuuryokuBean> shukkinKibouNyuuryokuBeanList, List<DateBean> dateBeanList)
			throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {

		// 戻り値・・・とりあえず例に沿った形にしておく
		List<List<ShukkinKibouNyuuryokuDto>> kibouDtoListList = new ArrayList<List<ShukkinKibouNyuuryokuDto>>();

		for (ShukkinKibouNyuuryokuBean shukkinKibouNyuuryokuBean : shukkinKibouNyuuryokuBeanList) {

			List<ShukkinKibouNyuuryokuDto> shukkinKibouNyuuryokuDtoList = new ArrayList<ShukkinKibouNyuuryokuDto>();
			// Dao

//			// 登録フラグ
//			boolean registFlg = shukkinKibouNyuuryokuBean.isRegistFlg();
//			if (!registFlg) {
//				continue;
//			}

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
					String kibouShiftId = (String) methods[i].invoke(shukkinKibouNyuuryokuBean);

					if (CommonConstant.BLANK_ID.equals(kibouShiftId)) {
						// 空白が選択されている場合
						kibouShiftId = null;
					}

					shukkinKibouNyuuryokuDto.setKibouShiftId(kibouShiftId);
					shukkinKibouNyuuryokuDto.setShainId(shukkinKibouNyuuryokuBean.getShainId());
					shukkinKibouNyuuryokuDto.setYearMonthDay(yearMonthDay);
					shukkinKibouNyuuryokuDtoList.add(shukkinKibouNyuuryokuDto);

					index++;
				}
			}
			kibouDtoListList.add(shukkinKibouNyuuryokuDtoList);
		}
		return kibouDtoListList;

	}

}
