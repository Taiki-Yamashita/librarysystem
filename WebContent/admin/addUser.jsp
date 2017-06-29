<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>ユーザー登録</title>
		<link href="../css/styleOkada.css" rel="stylesheet" type="text/css">
	</head>
	<body>
		<h1>図書システム借りたいナ☆</h1>
		<h2>ユーザー登録画面</h2>

		<a href = "./manageUser">ユーザー管理画面</a><br>

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


		<form action="addUser" method="post">

			<label for="name">名前</label>*必須<br>
			<input name="name" value="${newUser.name}" id="name"/><br/>

			<label for="loginId">ログインID</label>*必須<br>
			<input name="loginId" value="${newUser.loginId}" id="loginId"/><br/>

			<label for="password">パスワード</label>*必須<br>
			<input name="password" type = "password" value="${newUser.password}" id="password"/><br/>

			<label for="address">住所</label>*必須<br>
			<input name="address" value="${newUser.address}" id="address"/><br/>

			<label for="tel">電話番号</label><br>
			<input name="tel" value="${newUser.tel}" id="tel"/><br/>

			<label for="mail">メールアドレス</label><br>
			<input name="mail"  value="${newUser.mail}"  id="mail"/><br/>

			<label for="libraryId">最寄り図書館</label>*必須<br>
			<select name="libraryId" >
				<option value="0">選択してください</option>
				<c:forEach items="${libraries}" var="library">
					<c:if test="${newUser.libraryId == library.id }">
						<option selected value="${library.id}">${library.name } </option>
					</c:if>
					<c:if test="${newUser.libraryId != library.id }">
						<option  value="${library.id}">${library.name } </option>
					</c:if>
				</c:forEach>
			</select>


			<input type="submit" value="登録" />
		</form>
	</body>
</html>