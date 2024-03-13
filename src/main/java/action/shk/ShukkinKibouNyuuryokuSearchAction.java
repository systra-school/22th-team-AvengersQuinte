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

public class ShukkinKibouNyuuryokuSearchAction extends ShukkinKibouAbstractAction {

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

		String shainId = loginUserDto.getShainId();
		req.setAttribute("shainId", shainId);

		// フォーム
		ShukkinKibouNyuuryokuForm shukkinKibouNyuuryokuForm = (ShukkinKibouNyuuryokuForm) form;

		// 対象年月
		String yearMonth = shukkinKibouNyuuryokuForm.getYearMonth();

		// ロジック生成
		ShukkinKibouLogic shukkinKibouLogic = new ShukkinKibouLogic();

		// 対象年月の月情報を取得する。
		List<DateBean> dateBeanList = CommonUtils.getDateBeanList(yearMonth);

		// 出勤希望日を取得する
		List<List<ShukkinKibouKakuninDto>> kakuninDtoListList = shukkinKibouLogic
				.getShukkinKibouKakuninDtoList(yearMonth);
		List<ShukkinKibouNyuuryokuBean> shukkinKibouNyuuryokuBeanList = new ArrayList<ShukkinKibouNyuuryokuBean>();

//		// シフトIDを取得する
//		Map<String, List<ShukkinKibouNyuuryokuDto>> ShukkinKibouDtoMap = ShukkinKibouLogic.getShukkinKibouDtoMap(yearMonth, true);
//
//		List<ShukkinKibouNyuuryokuBean> ShukkinKibouNyuuryokuBeanList = new ArrayList<ShukkinKibouNyuuryokuBean>();
//

		// セレクトボックスの取得
		ComboListUtilLogic comboListUtils = new ComboListUtilLogic();
		// シフト情報
		Map<String, String> shiftCmbMap = comboListUtils.getComboShift(true);
		// 年月の情報
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

	private List<ShukkinKibouNyuuryokuBean> dtoToBean(List<List<ShukkinKibouKakuninDto>> kakuninDtoListList,
			LoginUserDto loginUserDto)
			throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {

		// valueでエラーになるからコメントアウト
		// List<List<ShukkinKibouKakuninDto>> collection = kakuninDtoListList.values();
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

			// とりあえず復活
			int index = 0;
			int listSize = kakuninDtoList.size();

			for (int i = 0; i < methods.length; i++) {

				// "setShiftIdXX" のメソッドを動的に実行する
				if (methods[i].getName().startsWith("setShiftId") && listSize > index) {

					ShukkinKibouKakuninDto shukkinKibouKakuninDto = kakuninDtoList.get(index);

					// メソッドの実行
					methods[i].invoke(shukkinKibouNyuuryokuBean, shukkinKibouKakuninDto.getKibouShiftId());

					shainId = shukkinKibouKakuninDto.getShainId();
					shainName = shukkinKibouKakuninDto.getShainName();

					index++;

				}
			}

			shukkinKibouNyuuryokuBean.setShainId(shainId);
			shukkinKibouNyuuryokuBean.setShainName(shainName);
			shukkinKibouNyuuryokuBean.setRegistFlg(true);

			shukkinKibouKakuninBeanList.add(shukkinKibouNyuuryokuBean);

		}
		return shukkinKibouKakuninBeanList;
	}

}