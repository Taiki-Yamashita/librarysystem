<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>パスワード変更画面</title>
</head>
<body>
	<a href = "./">トップ</a>
	<h1>パスワード変更画面</h1>
	<h2>パスワードが未入力の場合、現在のパスワードで更新されます</h2>
	<a href = "./">トップ</a>
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

	<form action="renewPassword" method="post">
		<input type="hidden" value="${loginUser.id}" id="id" name="id"/>

		<label for="loginId">ログインID</label><br>
		<input name="loginId"  value="${editUser.loginId}" id="loginId"/><br/>

		<label for="password">パスワード</label><br>
		<input name="password" type="password" value="${editUser.password}" id="password"/><br/>

		<label for="confirmedPassword">パスワード（確認用）</label><br>
		<input name="confirmedPassword" type="password" value="${editUser.password}" id="confirmedPassword"/><br/>

		<input type="submit" value="更新" />
	</form>
</body>
</html>