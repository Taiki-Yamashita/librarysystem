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
<body>
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
	<a class="right" href = "./logout">ログアウト</a>
	<br/>
		<table border="1" class="center">
			<tr>
				<td><a href = "./">トップ</a></td>
				<td><a href = "./search">検索</a></td>
				<td><a href = "./ranking">ランキング</a></td>
				<td><a href = "./user">マイページ</a></td>
			</tr>
		</table>

	<div class="center">
		<form action="renewPassword" method="post">
			<input type="hidden" value="${loginUser.id}" id="id" name="id"/>

			<label for="loginId">ログインID</label><br>
			<input name="loginId"  value="${loginUser.loginId}" id="loginId" /><br/>

			<label for="newPassword">新しいパスワード</label><br>
			<input name="newPassword" type="password" value="" id="newPassword" placeholder="未記入は変更なし"/><br/>

			<label for="confirmedPassword">確認用</label><br>
			<input name="confirmedPassword" type="password" value="" id="confirmedPassword" placeholder="未記入は変更なし"/><br/>

			<input type="submit" value="更新" />
		</form>
	</div>
</body>
</html>