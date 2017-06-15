<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>ユーザー登録</title>
	</head>

	<body>
		<h1>ユーザー登録画面</h1>

		<c:if test="${ not empty errorMessages }">
			<c:forEach items="${errorMessages}" var="message">
				<c:out value="${message}"/>
			</c:forEach>

			<c:remove var="errorMessages" scope="session"/>
		</c:if>

		<a href = "./manage">管理画面</a>

		<form action="addUser" method="post">

			<label for="name">名前</label><br>
			<input name="name" value="${newUser.name}" id="name"/><br/>

			<label for="loginId">ログインID</label><br>
			<input name="loginId" value="${newUser.loginId}" id="loginId"/><br/>

			<label for="password">パスワード</label><br>
			<input name="password" value="${newUser.password}" id="password"/><br/>

			<label for="checkPassword">パスワード（確認）</label><br>
			<input name="checkPassword" value="${newUser.checkPassword}" id="checkPassword"/><br/>

			<label for="address">住所</label><br>
			<input name="address" value="${newUser.address}" id="address"/><br/>

			<label for="tel">電話番号</label><br>
			<input name="tel" value="${newUser.tel}" id="tel"/><br/>

			<label for="mail">メールアドレス</label><br>
			<input name="mail"  value="${newUser.mail}"  id="mail"/><br/>

			<label for="libraryId">最寄り図書館</label><br>
			<input name="libraryId"  value="${newUser.libraryId}"  id="libraryId"/><br/>

			<input type="submit" value="登録" />
		</form>
	</body>
</html>