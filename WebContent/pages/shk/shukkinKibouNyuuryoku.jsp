<!-- shukkinKibouNyuuryoku.jsp -->
<%@page import="constant.CommonConstant.DayOfWeek"%>
<%@page import="business.logic.utils.CheckUtils"%>
<%@page import="form.common.DateBean"%>
<%@page import="java.util.List"%>
<%@page import="form.shk.ShukkinKibouNyuuryokuForm"%>
<%
/**
 * ファイル名：shukkinKibouNyuuryoku.jsp
 *
 * 変更履歴
 * 1.0  2010/09/13 Kazuya.Naraki
 */
%>
<%@page contentType="text/html; charset=Shift_JIS" pageEncoding="Shift_JIS"%>
<%@ page import="constant.RequestSessionNameConstant"%>
<%@ page import="constant.CommonConstant"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>

<bean:size id="dateBeanListSize" name="shukkinKibouNyuuryokuForm"  property="dateBeanList"/>
<bean:define id="offset" name="shukkinKibouNyuuryokuForm" property="offset" />
<bean:define id="color" value="" type="java.lang.String"/>
<bean:define id="showLength" value="18" type="java.lang.String"/>
<html>
<head>
    <meta http-equiv="Pragma" content="no-cache">
    <meta http-equiv="Cache-Control" content="no-cache">
    <meta http-equiv="Expires" content="Thu, 01 Dec 1994 16:00:00 GMT">
    <script type="text/javascript" src="/kikin/pages/js/common.js"></script>
    <script type="text/javascript" src="/kikin/pages/js/checkCommon.js"></script>
    <script type="text/javascript" src="/kikin/pages/js/message.js"></script>
    <script type="text/javascript" language="Javascript1.1">
    
    /**
     * 検索
     */
    function submitSearch() {
        doSubmit('/kikin/shukkinKibouNyuuryokuSearch.do');
    }
    
    /*
    * 登録
    */
   function submitRegist() {
       // サブミット
       doSubmit('/kikin/shukkinKibouNyuuryokuRegist.do');
   }
    
    /**
     * サブウィンドウを開く
     */
    function openWindow(){
        window.open("/kikin/shiftHanrei.do?param=", null, "menubar=no, toolbar=no, scrollbars=auto, resizable=yes, width=520px, height=650px");
    }
    
    function openKakunin(){
        window.open("/kikin/shukkinKibouKakuninSearch.do?param=", null, "menubar=no, toolbar=no, scrollbars=auto, resizable=yes, width=520px, height=650px");
    }
    
    </script>
    <title>出勤希望入力画面</title>

    <link href="/kikin/pages/css/common.css" rel="stylesheet" type="text/css" />
  </head>
 <body>
    <div id="wrapper">
      <div id="header">
        <table>
          <tr>
            <td id="headLeft">
              <input value="戻る" type="button" class="smlButton"  onclick="doSubmit('/kikin/shukkinKibouNyuuryokuBack.do')" />
            </td>
            <td id="headCenter">
              出勤希望入力
            </td>
            <td id="headRight">
              <input value="ログアウト" type="button" class="smlButton"  onclick="logout()" />
            </td>
          </tr>
        </table>
      </div>
       <div id="gymBody" style="overflow: hidden;">
        <div style="margin-left:10px;">
          <html:form action="/shukkinKibouNyuuryokuInit" >
            表示年月：
            <html:select name="shukkinKibouNyuuryokuForm" property="yearMonth" onchange="submitSearch()">
            <html:optionsCollection name="shukkinKibouNyuuryokuForm"
                                    property="yearMonthCmbMap"
                                    value="key"
                                    label="value"/>
            </html:select>
   
            <div>
            <table width="1200px" cellpadding="0" cellspacing="0">
                <tr>
                  <td width="150px" valign="top">
                    <table border="1" cellpadding="0" cellspacing="0">
                      <tr class="tblHeader">
                        <td width="150px" align="center">
                          &nbsp;
                        </td>
                        </tr>
                      <tr class="tblHeader">
                        <td width="150px" align="center">
                        社員名
                        </td>
                      </tr>
                      
                      <%--社員名表示 --%>
                      <logic:iterate offset="offset" length="<%=showLength %>" id="shukkinKibouNyuuryokuBeanList" name="shukkinKibouNyuuryokuForm" property="shukkinKibouNyuuryokuBeanList">
                      <logic:equal name="shukkinKibouNyuuryokuBeanList" property="shainId" value='<%= String.valueOf(request.getAttribute("shainId")) %>'>
                      
                        <logic:notEmpty name="shukkinKibouNyuuryokuBeanList" property="shainId">
                        
                        	<tr>
                        		<td><bean:write name="shukkinKibouNyuuryokuBeanList" property="shainName" /></td>
                        	</tr>
                        
                        </logic:notEmpty>
                        </logic:equal>
                      </logic:iterate>
                      
                      
                    </table>
                  </td>
                  
                  <td>
                    <div style="overflow-x: auto;overflow-y: hidden; width:1067px;height: 100%; text-align:center;">
                      <table class="tblHeader" border="1" cellpadding="0" cellspacing="0">
                        <tr>
                          <td width="40px" align="center" valign="middle">
                            1
                          </td>
                          <td width="40px" align="center" valign="middle">
                            2
                          </td>
                          <td width="40px" align="center" valign="middle">
                            3
                          </td>
                          <td width="40px" align="center" valign="middle">
                            4
                          </td>
                          <td width="40px" align="center" valign="middle">
                            5
                          </td>
                          <td width="40px" align="center" valign="middle">
                            6
                          </td>
                          <td width="40px" align="center" valign="middle">
                            7
                          </td>
                          <td width="40px" align="center" valign="middle">
                            8
                          </td>
                          <td width="40px" align="center" valign="middle">
                            9
                          </td>
                          <td width="40px" align="center" valign="middle">
                            10
                          </td>
                          <td width="40px" align="center" valign="middle">
                            11
                          </td>
                          <td width="40px" align="center" valign="middle">
                            12
                          </td>
                          <td width="40px" align="center" valign="middle">
                            13
                          </td>
                          <td width="40px" align="center" valign="middle">
                            14
                          </td>
                          <td width="40px" align="center" valign="middle">
                            15
                          </td>
                          <td width="40px" align="center" valign="middle">
                            16
                          </td>
                          <td width="40px" align="center" valign="middle">
                            17
                          </td>
                          <td width="40px" align="center" valign="middle">
                            18
                          </td>
                          <td width="40px" align="center" valign="middle">
                            19
                          </td>
                          <td width="40px" align="center" valign="middle">
                            20
                          </td>
                          <td width="40px" align="center" valign="middle">
                            21
                          </td>
                          <td width="40px" align="center" valign="middle">
                            22
                          </td>
                          <td width="40px" align="center" valign="middle">
                            23
                          </td>
                          <td width="40px" align="center" valign="middle">
                            24
                          </td>
                          <td width="40px" align="center" valign="middle">
                            25
                          </td>
                          <td width="40px" align="center" valign="middle">
                            26
                          </td>
                          <td width="40px" align="center" valign="middle">
                            27
                          </td>
                          <% if (dateBeanListSize >= 28) { %>
                          <td width="40px" align="center" valign="middle">
                            28
                          </td>
                          <% } %>
                          <% if (dateBeanListSize >= 29) { %>
                          <td width="40px" align="center" valign="middle">
                            29
                          </td>
                          <% } %>
                          <% if (dateBeanListSize >= 30) { %>
                          <td width="40px" align="center" valign="middle">
                            30
                          </td>
                          <% } %>
                          <% if (dateBeanListSize == 31) { %>
                          <td width="40px" align="center" valign="middle">
                            31
                          </td>
                          <% } %>
                        </tr>
                        
                        <tr class="tblHeader">
                          <logic:iterate id="dateBeanList" name="shukkinKibouNyuuryokuForm" property="dateBeanList">
                          <bean:define id="youbiEnum" name="dateBeanList" property="youbiEnum"/>
                              <%
                              if (DayOfWeek.SATURDAY.equals(youbiEnum)) {
                                  color = "fontBlue";
                              } else if (DayOfWeek.SUNDAY.equals(youbiEnum)) {
                                  color = "fontRed";
                              } else {
                                  color = "fontBlack";
                              }
                              %>

                              <td width="40px" align="center" valign="middle" class="<%=color %>">
                                <bean:write property="youbi" name="dateBeanList"/>
                              </td>
                          </logic:iterate>
                        </tr>
                        
                         
                         <logic:iterate offset="offset" length="<%=showLength %>" id="shukkinKibouNyuuryokuBeanList" name="shukkinKibouNyuuryokuForm" property="shukkinKibouNyuuryokuBeanList">
                         
                         <logic:equal name="shukkinKibouNyuuryokuBeanList" property="shainId" value='<%= String.valueOf(request.getAttribute("shainId")) %>'>
                         
                         <html:hidden name="shukkinKibouKakuninBeanList" property="registFlg" value="true" indexed="true"/>
                          <tr class="tblBody">
                          
                          
                          <td width="40px" align="center" valign="middle">
                            <html:select property="shiftId01" name="shukkinKibouNyuuryokuBeanList" indexed="true">
                            <html:optionsCollection name="shukkinKibouNyuuryokuForm"
                                                    property="shiftCmbMap"
                                                    value="key"
                                                    label="value"/>
                            </html:select>
                          </td>
                          
                          <td width="40px" align="center" valign="middle">
                            <html:select property="shiftId02" name="shukkinKibouNyuuryokuBeanList" indexed="true">
                            <html:optionsCollection name="shukkinKibouNyuuryokuForm"
                                                    property="shiftCmbMap"
                                                    value="key"
                                                    label="value"/>
                            </html:select>
                          </td>
                          
                          <td width="40px" align="center" valign="middle">
                            <html:select property="shiftId03" name="shukkinKibouNyuuryokuBeanList" indexed="true">
                            <html:optionsCollection name="shukkinKibouNyuuryokuForm"
                                                    property="shiftCmbMap"
                                                    value="key"
                                                    label="value"/>
                            </html:select>
                          </td>
                          <td width="40px" align="center" valign="middle">
                            <html:select property="shiftId04" name="shukkinKibouNyuuryokuBeanList" indexed="true">
                            <html:optionsCollection name="shukkinKibouNyuuryokuForm"
                                                    property="shiftCmbMap"
                                                    value="key"
                                                    label="value"/>
                            </html:select>
                          </td>
                          <td width="40px" align="center" valign="middle">
                            <html:select property="shiftId05" name="shukkinKibouNyuuryokuBeanList" indexed="true">
                            <html:optionsCollection name="shukkinKibouNyuuryokuForm"
                                                    property="shiftCmbMap"
                                                    value="key"
                                                    label="value"/>
                            </html:select>
                          </td>
                          <td width="40px" align="center" valign="middle">
                            <html:select property="shiftId06" name="shukkinKibouNyuuryokuBeanList" indexed="true">
                            <html:optionsCollection name="shukkinKibouNyuuryokuForm"
                                                    property="shiftCmbMap"
                                                    value="key"
                                                    label="value"/>
                            </html:select>
                          </td>
                          <td width="40px" align="center" valign="middle">
                            <html:select property="shiftId07" name="shukkinKibouNyuuryokuBeanList" indexed="true">
                            <html:optionsCollection name="shukkinKibouNyuuryokuForm"
                                                    property="shiftCmbMap"
                                                    value="key"
                                                    label="value"/>
                            </html:select>
                          </td>
                          <td width="40px" align="center" valign="middle">
                            <html:select property="shiftId08" name="shukkinKibouNyuuryokuBeanList" indexed="true">
                            <html:optionsCollection name="shukkinKibouNyuuryokuForm"
                                                    property="shiftCmbMap"
                                                    value="key"
                                                    label="value"/>
                            </html:select>
                          </td>
                          <td width="40px" align="center" valign="middle">
                            <html:select property="shiftId09" name="shukkinKibouNyuuryokuBeanList" indexed="true">
                            <html:optionsCollection name="shukkinKibouNyuuryokuForm"
                                                    property="shiftCmbMap"
                                                    value="key"
                                                    label="value"/>
                            </html:select>
                          </td>
                          <td width="40px" align="center" valign="middle">
                            <html:select property="shiftId10" name="shukkinKibouNyuuryokuBeanList" indexed="true">
                            <html:optionsCollection name="shukkinKibouNyuuryokuForm"
                                                    property="shiftCmbMap"
                                                    value="key"
                                                    label="value"/>
                            </html:select>
                          </td>
                          <td width="40px" align="center" valign="middle">
                            <html:select property="shiftId11" name="shukkinKibouNyuuryokuBeanList" indexed="true">
                            <html:optionsCollection name="shukkinKibouNyuuryokuForm"
                                                    property="shiftCmbMap"
                                                    value="key"
                                                    label="value"/>
                            </html:select>
                          </td>
                          <td width="40px" align="center" valign="middle">
                            <html:select property="shiftId12" name="shukkinKibouNyuuryokuBeanList" indexed="true">
                            <html:optionsCollection name="shukkinKibouNyuuryokuForm"
                                                    property="shiftCmbMap"
                                                    value="key"
                                                    label="value"/>
                            </html:select>
                          </td>
                          <td width="40px" align="center" valign="middle">
                            <html:select property="shiftId13" name="shukkinKibouNyuuryokuBeanList" indexed="true">
                            <html:optionsCollection name="shukkinKibouNyuuryokuForm"
                                                    property="shiftCmbMap"
                                                    value="key"
                                                    label="value"/>
                            </html:select>
                          </td>
                          <td width="40px" align="center" valign="middle">
                            <html:select property="shiftId14" name="shukkinKibouNyuuryokuBeanList" indexed="true">
                            <html:optionsCollection name="shukkinKibouNyuuryokuForm"
                                                    property="shiftCmbMap"
                                                    value="key"
                                                    label="value"/>
                            </html:select>
                          </td>
                          <td width="40px" align="center" valign="middle">
                            <html:select property="shiftId15" name="shukkinKibouNyuuryokuBeanList" indexed="true">
                            <html:optionsCollection name="shukkinKibouNyuuryokuForm"
                                                    property="shiftCmbMap"
                                                    value="key"
                                                    label="value"/>
                            </html:select>
                          </td>
                          <td width="40px" align="center" valign="middle">
                            <html:select property="shiftId16" name="shukkinKibouNyuuryokuBeanList" indexed="true">
                            <html:optionsCollection name="shukkinKibouNyuuryokuForm"
                                                    property="shiftCmbMap"
                                                    value="key"
                                                    label="value"/>
                            </html:select>
                          </td>
                          <td width="40px" align="center" valign="middle">
                            <html:select property="shiftId17" name="shukkinKibouNyuuryokuBeanList" indexed="true">
                            <html:optionsCollection name="shukkinKibouNyuuryokuForm"
                                                    property="shiftCmbMap"
                                                    value="key"
                                                    label="value"/>
                            </html:select>
                          </td>
                          <td width="40px" align="center" valign="middle">
                            <html:select property="shiftId18" name="shukkinKibouNyuuryokuBeanList" indexed="true">
                            <html:optionsCollection name="shukkinKibouNyuuryokuForm"
                                                    property="shiftCmbMap"
                                                    value="key"
                                                    label="value"/>
                            </html:select>
                          </td>
                          <td width="40px" align="center" valign="middle">
                            <html:select property="shiftId19" name="shukkinKibouNyuuryokuBeanList" indexed="true">
                            <html:optionsCollection name="shukkinKibouNyuuryokuForm"
                                                    property="shiftCmbMap"
                                                    value="key"
                                                    label="value"/>
                            </html:select>
                          </td>
                          <td width="40px" align="center" valign="middle">
                            <html:select property="shiftId20" name="shukkinKibouNyuuryokuBeanList" indexed="true">
                            <html:optionsCollection name="shukkinKibouNyuuryokuForm"
                                                    property="shiftCmbMap"
                                                    value="key"
                                                    label="value"/>
                            </html:select>
                          </td>
                          <td width="40px" align="center" valign="middle">
                            <html:select property="shiftId21" name="shukkinKibouNyuuryokuBeanList" indexed="true">
                            <html:optionsCollection name="shukkinKibouNyuuryokuForm"
                                                    property="shiftCmbMap"
                                                    value="key"
                                                    label="value"/>
                            </html:select>
                          </td>
                          <td width="40px" align="center" valign="middle">
                            <html:select property="shiftId22" name="shukkinKibouNyuuryokuBeanList" indexed="true">
                            <html:optionsCollection name="shukkinKibouNyuuryokuForm"
                                                    property="shiftCmbMap"
                                                    value="key"
                                                    label="value"/>
                            </html:select>
                          </td>
                          <td width="40px" align="center" valign="middle">
                            <html:select property="shiftId23" name="shukkinKibouNyuuryokuBeanList" indexed="true">
                            <html:optionsCollection name="shukkinKibouNyuuryokuForm"
                                                    property="shiftCmbMap"
                                                    value="key"
                                                    label="value"/>
                            </html:select>
                          </td>
                          <td width="40px" align="center" valign="middle">
                            <html:select property="shiftId24" name="shukkinKibouNyuuryokuBeanList" indexed="true">
                            <html:optionsCollection name="shukkinKibouNyuuryokuForm"
                                                    property="shiftCmbMap"
                                                    value="key"
                                                    label="value"/>
                            </html:select>
                          </td>
                          <td width="40px" align="center" valign="middle">
                            <html:select property="shiftId25" name="shukkinKibouNyuuryokuBeanList" indexed="true">
                            <html:optionsCollection name="shukkinKibouNyuuryokuForm"
                                                    property="shiftCmbMap"
                                                    value="key"
                                                    label="value"/>
                            </html:select>
                          </td>
                          <td width="40px" align="center" valign="middle">
                            <html:select property="shiftId26" name="shukkinKibouNyuuryokuBeanList" indexed="true">
                            <html:optionsCollection name="shukkinKibouNyuuryokuForm"
                                                    property="shiftCmbMap"
                                                    value="key"
                                                    label="value"/>
                            </html:select>
                          </td>
                          <td width="40px" align="center" valign="middle">
                            <html:select property="shiftId27" name="shukkinKibouNyuuryokuBeanList" indexed="true">
                            <html:optionsCollection name="shukkinKibouNyuuryokuForm"
                                                    property="shiftCmbMap"
                                                    value="key"
                                                    label="value"/>
                            </html:select>
                          </td>
                          
                           <% if (dateBeanListSize >= 28) { %>
                          <td width="40px" align="center" valign="middle">
                            <html:select property="shiftId28" name="shukkinKibouNyuuryokuBeanList" indexed="true">
                            <html:optionsCollection name="shukkinKibouNyuuryokuForm"
                                                    property="shiftCmbMap"
                                                    value="key"
                                                    label="value"/>
                            </html:select>
                          </td>
                          <% } %>
                          <% if (dateBeanListSize >= 29) { %>
                          <td width="40px" align="center" valign="middle">
                            <html:select property="shiftId29" name="shukkinKibouNyuuryokuBeanList" indexed="true">
                            <html:optionsCollection name="shukkinKibouNyuuryokuForm"
                                                    property="shiftCmbMap"
                                                    value="key"
                                                    label="value"/>
                            </html:select>
                          </td>
                          <% } %>
                          <% if (dateBeanListSize >= 30) { %>
                          <td width="40px" align="center" valign="middle">
                            <html:select property="shiftId30" name="shukkinKibouNyuuryokuBeanList" indexed="true">
                            <html:optionsCollection name="shukkinKibouNyuuryokuForm"
                                                    property="shiftCmbMap"
                                                    value="key"
                                                    label="value"/>
                            </html:select>
                          </td>
                          <% } %>
                          <% if (dateBeanListSize >= 31) { %>
                          <td width="40px" align="center" valign="middle">
                            <html:select property="shiftId31" name="shukkinKibouNyuuryokuBeanList" indexed="true">
                            <html:optionsCollection name="shukkinKibouNyuuryokuForm"
                                                    property="shiftCmbMap"
                                                    value="key"
                                                    label="value"/>
                            </html:select>
                          </td>
                          <% } %>
                          
                          </tr>
                          </logic:equal>
                         
                        </logic:iterate>
                        
                      </table>
                    </div>
                  </td>
                </tr>
              </table>
            </div>
          </html:form>
          <div style="margin-left:50px;">
            <input value="凡例表示" type="button" class="lngButton" onclick="openWindow()" />
          </div>
        </div>
      </div>
      <div id="footer">
      
        <table>
          <tr>
            <td id="footLeft">
            </td>
            <td id="footCenter" style="text-align: right;">
            
            <input value="出勤希望日参照" type="button" class="lngButton" onclick="openKakunin()" />

            </td>
            <td id="footRight">
              <input value="登録" type="button" class="smlButton" onclick="submitRegist()" />
            </td>
          </tr>
        </table>
        
      </div>
    </div>
  </body>
</html>
          
          
            
