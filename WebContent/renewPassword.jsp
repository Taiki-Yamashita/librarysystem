<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="./css/styleOkada.css" rel="stylesheet" type="text/css">
<link href="./css/styleTaiki.css" rel="stylesheet" type="text/css">
<link href="./css/styleKuniyoshi.css" rel="stylesheet" type="text/css">
<title>パスワード変更画面</title>
</head>
<body class="renewPassPage">
	<h1>パスワード変更画面</h1>
	<c:if test="${ not empty errorMessages }">
		<div class="errorMessages">
			<ul>
				<c:forEach items="${errorMessages}" var="message">
					<li><c:out value="${message}" />
				</c:forEach>
			</ul>
		</div>
		<c:remove var="errorMessages" scope="session"/>
	</c:if>
	<div class="subButton">
		<input type="button" onclick="location.href='./logout'"value="ログアウト">
	</div>
	<br/><br>
		<table class="menuBar">
			<tr>
				<td><input type="button" onclick="location.href='./'"value="トップ"></td>
				<td><input type="button" onclick="location.href='./search'"value="検索"></td>
				<td><input type="button" onclick="location.href='./ranking'"value="貸出/予約ランキング"></td>
				<td><input type="button" onclick="location.href='./user'"value="マイページ"></td>
			</tr>
		</table>

	<div class="center">
		<form class="passForm" action="renewPassword" method="post">
			<input type="hidden" value="${loginUser.id}" id="id" name="id"/>

			<label for="loginId">ログインID</label><br>
			<input name="loginId"  value="${loginUser.loginId}" id="loginId" /><br/>

			<label for="newPassword">新しいパスワード</label><br>
			<input name="newPassword" type="password" value="" id="newPassword" placeholder="未記入は変更なし"/><br/>

			<label for="confirmedPassword">確認用</label><br>
			<input name="confirmedPassword" type="password" value="" id="confirmedPassword" placeholder="未記入は変更なし"/><br/>

			<input class="new" type="submit" value="更新" />
		</form>
	</div>
</body>
</html>