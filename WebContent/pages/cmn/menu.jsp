<!-- menu.jsp -->
<%@page contentType="text/html; charset=Shift_JIS"
	pageEncoding="Shift_JIS"%>
<%@ page import="constant.RequestSessionNameConstant"%>
<%@ page import="constant.CommonConstant"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>


<html>
<head>
<meta http-equiv="Pragma" content="no-cache">
<meta http-equiv="Cache-Control" content="no-cache">
<meta http-equiv="Expires" content="Thu, 01 Dec 1994 16:00:00 GMT">
<html:javascript formName="loginForm" />
<script type="text/javascript" src="/kikin/pages/js/common.js"></script>
<script type="text/javascript" src="/kikin/pages/js/checkCommon.js"></script>
<script type="text/javascript" src="/kikin/pages/js/message.js"></script>

<title>メニュー画面</title>
<link href="/kikin/pages/css/common.css" rel="stylesheet"
	type="text/css" />
</head>
<body>
	<div id="wrapper">
		<div id="header">
			<table>
				<tr>
					<td id="headLeft"></td>
					<td id="headCenter"><logic:equal
							name="<%=RequestSessionNameConstant.SESSION_CMN_LOGIN_USER_INFO%>"
							property="kengenId"
							value="<%=CommonConstant.Kengen.KANRISYA.getId()%>">
                メニュー(管理者)
            </logic:equal> <logic:equal
							name="<%=RequestSessionNameConstant.SESSION_CMN_LOGIN_USER_INFO%>"
							property="kengenId"
							value="<%=CommonConstant.Kengen.IPPAN.getId()%>">
                メニュー(一般)
            </logic:equal></td>
					<td id="headRight"><input value="ログアウト" type="button"
						class="smlButton smlButton-border-shadow smlButton-border-shadow--radius"
						onclick="logout()" /></td>
				</tr>
			</table>
		</div>
		<div id="gymBody">

			<logic:equal
				name="<%=RequestSessionNameConstant.SESSION_CMN_LOGIN_USER_INFO%>"
				property="kengenId"
				value="<%=CommonConstant.Kengen.KANRISYA.getId()%>">
				<div class="menuBlock">

					<html:form action="/tsukibetsuShiftKakuninInit">
						<input type="submit" value="月別シフト確認"
							class="bigButton bigButton--yellow bigButton--cubic" />
					</html:form>
					<html:form action="/hibetsuShiftInit">
						<input type="submit" value="日別シフト確認"
							class="bigButton bigButton--yellow bigButton--cubic" />
					</html:form>
				</div>

				<div class="menuBlock">
					<html:form action="/kinmuJissekiKakuninInit">
						<input type="submit" value="勤務実績確認"
							class="bigButton bigButton--yellow bigButton--cubic" />
					</html:form>
					<html:form action="/kinmuJissekiNyuryokuKakuninInit">
						<input type="submit" value="勤務実績入力"
							class="bigButton bigButton--yellow bigButton--cubic" />
					</html:form>
				</div>

				<div class="menuBlock">
					<html:form action="/shukkinKibouKakuninInit">
						<input type="submit" value="出勤希望日確認"
							class="bigButton bigButton--yellow bigButton--cubic" />
					</html:form>
					<html:form action="/tsukibetsuShiftNyuuryokuInit">
						<input type="submit" value="月別シフト入力"
							class="bigButton bigButton--yellow bigButton--cubic" />
					</html:form>
				</div>


				<div class="menuBlock">
					<html:form action="/shainMstMnt">
						<input type="submit" value="社員マスタメンテナンス"
							class="bigButton bigButton--yellow bigButton--cubic" />
					</html:form>
					<html:form action="/shiftMstMnt">
						<input type="submit" value="シフトマスタメンテナンス"
							class="bigButton bigButton--yellow bigButton--cubic" />
					</html:form>
					<html:form action="/kihonShiftInit">
						<input type="submit" value="基本シフト登録"
							class="bigButton bigButton--yellow bigButton--cubic" />
					</html:form>
				</div>

			</logic:equal>

			<logic:equal
				name="<%=RequestSessionNameConstant.SESSION_CMN_LOGIN_USER_INFO%>"
				property="kengenId" value="<%=CommonConstant.Kengen.IPPAN.getId()%>">
				<div class="menuBlock">
					<html:form action="/tsukibetsuShiftKakuninInit">
						<input type="submit" value="月別シフト確認"
							class="bigButton bigButton--yellow bigButton--cubic" />
					</html:form>
					<html:form action="/hibetsuShiftInit">
						<input type="submit" value="日別シフト確認"
							class="bigButton bigButton--yellow bigButton--cubic" />
					</html:form>
				</div>
				<div class="menuBlock">
					<html:form action="/kinmuJissekiNyuryokuKakuninInit">
						<input type="submit" value="勤務実績入力"
							class="bigButton bigButton--yellow bigButton--cubic" />
					</html:form>
				</div>

				<div class="menuBlock">
					<html:form action="/shukkinKibouNyuuryokuInit">
						<input type="submit" value="希望出勤日入力"
							class="bigButton bigButton--yellow bigButton--cubic" />
					</html:form>
				</div>

				<div class="menuBlock">
					<html:form action="/kihonShiftKakuninInit">
						<input type="submit" value="基本シフト確認"
							class="bigButton bigButton--yellow bigButton--cubic" />
					</html:form>
				</div>

			</logic:equal>

			<div class="chii_gif">
				<img src="https://i.makeagif.com/media/8-19-2022/qbznMF.gif"
					alt="My awesome animated image" width="1170" height="350">
			</div>
		</div>


		<ul class="chii">
			<li class="chii2"><a href="https://www.anime-chiikawa.jp/"
				target="_blank"> <img
					src="https://chiikawa-biyori.com/wp-content/uploads/2021/11/wallpaper.jpg"
					alt="CHII" width="296" height="140" decoding="async"
					class="ls-is-cached lazyloaded is-loaded">
			</a>
				<noscript>
					"<img
						src="https://chiikawa-biyori.com/wp-content/uploads/2021/11/wallpaper.jpg"
						alt="CHII" width="296" height="140" data-eio="1">"
				</noscript></li>

			<li class="chii2"><a href="https://www.anime-chiikawa.jp/"
				target="_blank"> <img
					src="https://chiikawa-biyori.com/wp-content/uploads/2021/11/wallpaper.jpg"
					alt="CHII" width="296" height="140" decoding="async"
					class="ls-is-cached lazyloaded is-loaded">
			</a>
				<noscript>
					"<img
						src="https://chiikawa-biyori.com/wp-content/uploads/2021/11/wallpaper.jpg"
						alt="CHII" width="296" height="140" data-eio="1">"
				</noscript></li>

			<li class="chii2"><a href="https://www.anime-chiikawa.jp/"
				target="_blank"> <img
					src="https://chiikawa-biyori.com/wp-content/uploads/2021/11/wallpaper.jpg"
					alt="CHII" width="296" height="140" decoding="async"
					class="ls-is-cached lazyloaded is-loaded">
			</a>
				<noscript>
					"<img
						src="https://chiikawa-biyori.com/wp-content/uploads/2021/11/wallpaper.jpg"
						alt="CHII" width="296" height="140" data-eio="1">"
				</noscript></li>

			<li class="chii2"><a href="https://www.anime-chiikawa.jp/"
				target="_blank"> <img
					src="https://chiikawa-biyori.com/wp-content/uploads/2021/11/wallpaper.jpg"
					alt="CHII" width="296" height="140" decoding="async"
					class="ls-is-cached lazyloaded is-loaded">
			</a>
				<noscript>
					"<img
						src="https://chiikawa-biyori.com/wp-content/uploads/2021/11/wallpaper.jpg"
						alt="CHII" width="296" height="140" data-eio="1">"
				</noscript></li>

			<li class="chii2"><a href="https://www.anime-chiikawa.jp/"
				target="_blank"> <img
					src="https://chiikawa-biyori.com/wp-content/uploads/2021/11/wallpaper.jpg"
					alt="CHII" width="296" height="140" decoding="async"
					class="ls-is-cached lazyloaded is-loaded">
			</a>

				<noscript>
					"<img
						src="https://chiikawa-biyori.com/wp-content/uploads/2021/11/wallpaper.jpg"
						alt="CHII" width="296" height="140" data-eio="1">"
				</noscript></li>

			<li class="chii2"><a href="https://www.anime-chiikawa.jp/"
				target="_blank"> <img
					src="https://chiikawa-biyori.com/wp-content/uploads/2021/11/wallpaper.jpg"
					alt="CHII" width="296" height="140" decoding="async"
					class="ls-is-cached lazyloaded is-loaded">
			</a>
				<noscript>
					"<img
						src="https://chiikawa-biyori.com/wp-content/uploads/2021/11/wallpaper.jpg"
						alt="CHII" width="296" height="140" data-eio="1">"
				</noscript></li>

			<li class="chii2"><a href="https://www.anime-chiikawa.jp/"
				target="_blank"> <img
					src="https://chiikawa-biyori.com/wp-content/uploads/2021/11/wallpaper.jpg"
					alt="CHII" width="296" height="140" decoding="async"
					class="ls-is-cached lazyloaded is-loaded">
			</a>
				<noscript>
					"<img
						src="https://chiikawa-biyori.com/wp-content/uploads/2021/11/wallpaper.jpg"
						alt="CHII" width="296" height="140" data-eio="1">"
				</noscript></li>

			<li class="chii2"><a href="https://www.anime-chiikawa.jp/"
				target="_blank"> <img
					src="https://chiikawa-biyori.com/wp-content/uploads/2021/11/wallpaper.jpg"
					alt="CHII" width="296" height="140" decoding="async"
					class="ls-is-cached lazyloaded is-loaded">
			</a>
				<noscript>
					"<img
						src="https://chiikawa-biyori.com/wp-content/uploads/2021/11/wallpaper.jpg"
						alt="CHII" width="296" height="140" data-eio="1">"
				</noscript></li>

			<li class="chii2"><a href="https://www.anime-chiikawa.jp/"
				target="_blank"> <img
					src="https://chiikawa-biyori.com/wp-content/uploads/2021/11/wallpaper.jpg"
					alt="CHII" width="296" height="140" decoding="async"
					class="ls-is-cached lazyloaded is-loaded">
			</a>
				<noscript>
					"<img
						src="https://chiikawa-biyori.com/wp-content/uploads/2021/11/wallpaper.jpg"
						alt="CHII" width="296" height="140" data-eio="1">"
				</noscript></li>

			<li class="chii2"><a href="https://www.anime-chiikawa.jp/"
				target="_blank"> <img
					src="https://chiikawa-biyori.com/wp-content/uploads/2021/11/wallpaper.jpg"
					alt="CHII" width="296" height="140" decoding="async"
					class="ls-is-cached lazyloaded is-loaded">
			</a>
				<noscript>
					"<img
						src="https://chiikawa-biyori.com/wp-content/uploads/2021/11/wallpaper.jpg"
						alt="CHII" width="296" height="140" data-eio="1">"
				</noscript></li>

			<li class="chii2"><a href="https://www.anime-chiikawa.jp/"
				target="_blank"> <img
					src="https://chiikawa-biyori.com/wp-content/uploads/2021/11/wallpaper.jpg"
					alt="CHII" width="296" height="140" decoding="async"
					class="ls-is-cached lazyloaded is-loaded">
			</a>
				<noscript>
					"<img
						src="https://chiikawa-biyori.com/wp-content/uploads/2021/11/wallpaper.jpg"
						alt="CHII" width="296" height="140" data-eio="1">"
				</noscript></li>

			<li class="chii2"><a href="https://www.anime-chiikawa.jp/"
				target="_blank"> <img
					src="https://chiikawa-biyori.com/wp-content/uploads/2021/11/wallpaper.jpg"
					alt="CHII" width="296" height="140" decoding="async"
					class="ls-is-cached lazyloaded is-loaded">
			</a>
				<noscript>
					"<img
						src="https://chiikawa-biyori.com/wp-content/uploads/2021/11/wallpaper.jpg"
						alt="CHII" width="296" height="140" data-eio="1">"
				</noscript></li>

			<li class="chii2"><a href="https://www.anime-chiikawa.jp/"
				target="_blank"> <img
					src="https://chiikawa-biyori.com/wp-content/uploads/2021/11/wallpaper.jpg"
					alt="CHII" width="296" height="140" decoding="async"
					class="ls-is-cached lazyloaded is-loaded">
			</a>
				<noscript>
					"<img
						src="https://chiikawa-biyori.com/wp-content/uploads/2021/11/wallpaper.jpg"
						alt="CHII" width="296" height="140" data-eio="1">"
				</noscript></li>

			<li class="chii2"><a href="https://www.anime-chiikawa.jp/"
				target="_blank"> <img
					src="https://chiikawa-biyori.com/wp-content/uploads/2021/11/wallpaper.jpg"
					alt="CHII" width="296" height="140" decoding="async"
					class="ls-is-cached lazyloaded is-loaded">
			</a>
				<noscript>
					"<img
						src="https://chiikawa-biyori.com/wp-content/uploads/2021/11/wallpaper.jpg"
						alt="CHII" width="296" height="140" data-eio="1">"
				</noscript></li>

			<li class="chii2"><a href="https://www.anime-chiikawa.jp/"
				target="_blank"> <img
					src="https://chiikawa-biyori.com/wp-content/uploads/2021/11/wallpaper.jpg"
					alt="CHII" width="296" height="140" decoding="async"
					class="ls-is-cached lazyloaded is-loaded">
			</a>
				<noscript>
					"<img
						src="https://chiikawa-biyori.com/wp-content/uploads/2021/11/wallpaper.jpg"
						alt="CHII" width="296" height="140" data-eio="1">"
				</noscript></li>

			<li class="chii2"><a href="https://www.anime-chiikawa.jp/"
				target="_blank"> <img
					src="https://chiikawa-biyori.com/wp-content/uploads/2021/11/wallpaper.jpg"
					alt="CHII" width="296" height="140" decoding="async"
					class="ls-is-cached lazyloaded is-loaded">
			</a>
				<noscript>
					"<img
						src="https://chiikawa-biyori.com/wp-content/uploads/2021/11/wallpaper.jpg"
						alt="CHII" width="296" height="140" data-eio="1">"
				</noscript></li>

			<li class="chii2"><a href="https://www.anime-chiikawa.jp/"
				target="_blank"> <img
					src="https://chiikawa-biyori.com/wp-content/uploads/2021/11/wallpaper.jpg"
					alt="CHII" width="296" height="140" decoding="async"
					class="ls-is-cached lazyloaded is-loaded">
			</a>
				<noscript>
					"<img
						src="https://chiikawa-biyori.com/wp-content/uploads/2021/11/wallpaper.jpg"
						alt="CHII" width="296" height="140" data-eio="1">"
				</noscript></li>

			<li class="chii2"><a href="https://www.anime-chiikawa.jp/"
				target="_blank"> <img
					src="https://chiikawa-biyori.com/wp-content/uploads/2021/11/wallpaper.jpg"
					alt="CHII" width="296" height="140" decoding="async"
					class="ls-is-cached lazyloaded is-loaded">
			</a>
				<noscript>
					"<img
						src="https://chiikawa-biyori.com/wp-content/uploads/2021/11/wallpaper.jpg"
						alt="CHII" width="296" height="140" data-eio="1">"
				</noscript></li>

			<li class="chii2"><a href="https://www.anime-chiikawa.jp/"
				target="_blank"> <img
					src="https://chiikawa-biyori.com/wp-content/uploads/2021/11/wallpaper.jpg"
					alt="CHII" width="296" height="140" decoding="async"
					class="ls-is-cached lazyloaded is-loaded">
			</a>
				<noscript>
					"<img
						src="https://chiikawa-biyori.com/wp-content/uploads/2021/11/wallpaper.jpg"
						alt="CHII" width="296" height="140" data-eio="1">"
				</noscript></li>

			<li class="chii2"><a href="https://www.anime-chiikawa.jp/"
				target="_blank"> <img
					src="https://chiikawa-biyori.com/wp-content/uploads/2021/11/wallpaper.jpg"
					alt="CHII" width="296" height="140" decoding="async"
					class="ls-is-cached lazyloaded is-loaded">
			</a>
				<noscript>
					"<img
						src="https://chiikawa-biyori.com/wp-content/uploads/2021/11/wallpaper.jpg"
						alt="CHII" width="296" height="140" data-eio="1">"
				</noscript></li>

			<li class="chii2"><a href="https://www.anime-chiikawa.jp/"
				target="_blank"> <img
					src="https://chiikawa-biyori.com/wp-content/uploads/2021/11/wallpaper.jpg"
					alt="CHII" width="296" height="140" decoding="async"
					class="ls-is-cached lazyloaded is-loaded">
			</a>
				<noscript>
					"<img
						src="https://chiikawa-biyori.com/wp-content/uploads/2021/11/wallpaper.jpg"
						alt="CHII" width="296" height="140" data-eio="1">"
				</noscript></li>

			<li class="chii2"><a href="https://www.anime-chiikawa.jp/"
				target="_blank"> <img
					src="https://chiikawa-biyori.com/wp-content/uploads/2021/11/wallpaper.jpg"
					alt="CHII" width="296" height="140" decoding="async"
					class="ls-is-cached lazyloaded is-loaded">
			</a>
				<noscript>
					"<img
						src="https://chiikawa-biyori.com/wp-content/uploads/2021/11/wallpaper.jpg"
						alt="CHII" width="296" height="140" data-eio="1">"
				</noscript></li>

			<li class="chii2"><a href="https://www.anime-chiikawa.jp/"
				target="_blank"> <img
					src="https://chiikawa-biyori.com/wp-content/uploads/2021/11/wallpaper.jpg"
					alt="CHII" width="296" height="140" decoding="async"
					class="ls-is-cached lazyloaded is-loaded">
			</a>
				<noscript>
					"<img
						src="https://chiikawa-biyori.com/wp-content/uploads/2021/11/wallpaper.jpg"
						alt="CHII" width="296" height="140" data-eio="1">"
				</noscript></li>

			<li class="chii2"><a href="https://www.anime-chiikawa.jp/"
				target="_blank"> <img
					src="https://chiikawa-biyori.com/wp-content/uploads/2021/11/wallpaper.jpg"
					alt="CHII" width="296" height="140" decoding="async"
					class="ls-is-cached lazyloaded is-loaded">
			</a>
				<noscript>
					"<img
						src="https://chiikawa-biyori.com/wp-content/uploads/2021/11/wallpaper.jpg"
						alt="CHII" width="296" height="140" data-eio="1">"
				</noscript></li>

			<li class="chii2"><a href="https://www.anime-chiikawa.jp/"
				target="_blank"> <img
					src="https://chiikawa-biyori.com/wp-content/uploads/2021/11/wallpaper.jpg"
					alt="CHII" width="296" height="140" decoding="async"
					class="ls-is-cached lazyloaded is-loaded">
			</a>
				<noscript>
					"<img
						src="https://chiikawa-biyori.com/wp-content/uploads/2021/11/wallpaper.jpg"
						alt="CHII" width="296" height="140" data-eio="1">"
				</noscript></li>
		</ul>
		<div id="footer">
			<table>
				<tr>
					<td id="footLeft"></td>
					<td id="footCenter"></td>
					<td id="footRight"></td>
				</tr>
			</table>
		</div>
	</div>
</html>
	<footer>
		<ul class="footer-menu">
			<li>home ｜</li>
			<li>about ｜</li>
			<li>service ｜</li>
			<li>Contact Us</li>
		</ul>
		<p>All rights reserved by dmmwebcampmedia.</p>
	</footer>
</body>