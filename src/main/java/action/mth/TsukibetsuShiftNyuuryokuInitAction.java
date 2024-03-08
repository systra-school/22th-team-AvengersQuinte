/**
 * ファイル名：TsukibetsuShiftNyuuryokuInitAction.java
 *
 * 変更履歴
 * 1.0  2010/09/04 Kazuya.Naraki
 */
package action.mth;

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

/**
 * 説明：月別シフト入力初期表示アクションクラス
 * @author naraki
 */
public class TsukibetsuShiftNyuuryokuInitAction extends TsukibetsuShiftNyuuryokuAbstractAction{

    /**
     * 説明：月別シフト入力初期表示アクションクラス
     *
     * @param mapping アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @return アクションフォワード
     * @author naraki
     */
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest req, HttpServletResponse res) throws Exception {

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
		//String yearMonth = shukkinKibouNyuuryokuForm.getYearMonth();
		
		 // ロジック生成
		ShukkinKibouLogic shukkinKibouLogic = new ShukkinKibouLogic();

		// 対象年月の月情報を取得する。
		List<DateBean> dateBeanList = CommonUtils.getDateBeanList(yearMonth);

		//白石くんのに合わせるためにコメントアウトしておく
//		// 出勤希望日を取得する
//		List<List<ShukkinKibouKakuninDto>> kakuninDtoListList = shukkinKibouLogic
//				.getShukkinKibouKakuninDtoList(yearMonth);
		// 他のクラスでは宣言されているが、このクラスだと名前が被っているので一旦コメントアウト
		// List<ShukkinKibouNyuuryokuBean> shukkinKibouNyuuryokuBeanList = new
		// ArrayList<ShukkinKibouNyuuryokuBean>();

		// フォームデータをDtoに変換する
		//List<List<ShukkinKibouNyuuryokuDto>> nyuuryokuDtoListList = this.formToDto(shukkinKibouNyuuryokuBeanList,
		//		dateBeanList);

		// 登録・更新処理
		//shukkinKibouLogic.registKibouShift(nyuuryokuDtoListList, loginUserDto);

		//シフトIDを取得する
		List<List<ShukkinKibouKakuninDto>> kakuninDtoListList = shukkinKibouLogic.getShukkinKibouKakuninDtoList(yearMonth);
		
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
    /**
     * DtoからBeanへ変換する
     * @param tsukibetsuShiftDtoMap
     * @param loginUserDto
     * @return 一覧に表示するリスト
     * @author naraki
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     */
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
			shukkinKibouNyuuryokuBean.setRegistFlg(false);

			shukkinKibouKakuninBeanList.add(shukkinKibouNyuuryokuBean);

		}
		return shukkinKibouKakuninBeanList;
	}


}
