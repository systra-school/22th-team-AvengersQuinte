/**
 * �t�@�C�����Fcheck.js
 * ���ʏ���
 *
 * �ύX����
 * 1.0  2010/09/10 Kazuya.Naraki
 */

/**
 *  ���O�A�E�g�{�^���T�u�~�b�g
 */
function logout() {
    document.forms[0].action = "/kikin/logout.do";
    document.forms[0].submit();
}

/**
 *  �߂�{�^���T�u�~�b�g
 */
function doSubmit(action) {
    document.forms[0].action = action;
    document.forms[0].submit();
}
