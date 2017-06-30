<%@ page language="java" contentType="text/html; charset=UTF-8"

    pageEncoding="UTF-8"%>
     <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>お知らせフォーム</title>
<link href="../css/styleOkada.css" rel="stylesheet" type="text/css">
<link href="../css/styleTaiki.css" rel="stylesheet" type="text/css">
<link href="../css/styleKuniyoshi.css" rel="stylesheet" type="text/css">
</head>

<body class="admin">
<p><a href = "./manage">管理画面</a></p>
	<h1>図書システム借りたいナ☆</h1>
	<h2>お知らせ投稿フォーム</h2>

	<div class="errorMessages">
		<c:if test="${ not empty errorMessages }">
			<c:forEach items="${errorMessages}" var="message">
				<c:out value="${message}"/>
			</c:forEach>
			<c:remove var="errorMessages" scope="session"/>
		</c:if>
	</div>

<div class="center">
	<form class = "notificationForm" action = "notification"method = "post"><br />

		<label for="libraryId">図書館</label><br>
		<select name="libraryId">
			<option value="0">選択してください</option>
				<c:forEach items="${libraries}" var="library">
				<c:if test="${newNotification.libraryId == library.id }">
						<option selected value="${library.id}">${library.name } </option>
					</c:if>
					<c:if test="${newNotification.libraryId != library.id }">
						<option  value="${library.id}">${library.name } </option>
					</c:if>
			</c:forEach>
		</select><br>
		タイトル<br>
		<textarea name="title" id="title" cols="100" rows="1" ><c:out value="${newNotification.title }"></c:out></textarea><br>
		投稿<br>
		<textarea name="message" id="message" cols="100" rows="5" ><c:out value="${newNotification.message }"></c:out></textarea><br>

		<input class="post" type="submit" value="投稿" />

	</form>
</div>
</body>
</html>