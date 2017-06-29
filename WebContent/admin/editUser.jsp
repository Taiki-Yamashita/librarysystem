<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ユーザーの編集</title>
<link href="css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
	<h1>図書システム借りたいナ☆</h1>
	<h2>ユーザー登録画面</h2>
		<c:if test="${ not empty errorMessages }">
			<c:forEach items="${errorMessages}" var="message">
				<c:out value="${message}"/>
			</c:forEach>
			<c:remove var="errorMessages" scope="session"/>
		</c:if>

<a href = "manage">管理画面</a>


<form action="editUser"method="post"><br />

	<input name="id" type="hidden" value="${editUser.id}"/>

	<label for="loginId">ログインID(半角英数字6～20文字)</label><br>
	<input name="loginId" id="loginId" value="${editUser.loginId }"/><br />

	<label for="password">パスワード(半角英数字6～20文字)</label><br>
	<input name="password" type="password" id="password" /><br />

	<label for="name">名前</label><br>
	<input name="name" id="name"  value="${editUser.name }"/><br />

	<label for="address">住所</label><br>
	<input name="address" id="address" value="${editUser.address }"/><br />

		<label for="tel">TEL</label><br>
	<input name="tel" id="tel" value="${editUser.tel }"/><br />

	<label for="mail">メールアドレス</label><br>
	<input name="mail" id="mail" value="${editUser.mail }"/><br />

	<label for="point">ポイント</label><br>
	<input name="point" id="point" value="${editUser.point }"/><br />


		<label for="libraryId">図書館</label><br>
		<select name="libraryId">
			<c:forEach items="${libraries}" var="library">
				<c:if test="${editUser.libraryId == library.id }">
					<option selected value="${library.id}">${library.name } </option>
				</c:if>
				<c:if test="${editUser.libraryId != library.id }">
					<option  value="${library.id}">${library.name } </option>
				</c:if>
			</c:forEach>
		</select>

		<br>

		<label for="registerDate">登録日（更新日）</label><br>
		<input type="hidden" name="registerDate" id="registerDate" value="${editUser.registerDate }"/>
		<fmt:parseDate var="date" value="${editUser.registerDate}" pattern="yyyy-MM-dd HH:mm:ss" />
		<fmt:formatDate pattern = "yyyy年MM月dd日" value = "${date}" />
		<button type="submit" name="post2" >編集</button>
</form>
<form action="renewUser" method="post">
	<input type="hidden" name=renewDate >
	<input type="hidden" name=renewUserId value="${editUser.id}">
	<input type="hidden" name=renewUserLoginId value="${editUser.loginId}">
	<button type="submit" name="renew" >期限更新</button>
</form>
</body>
</html>