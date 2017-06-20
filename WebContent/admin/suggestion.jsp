<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>本紹介フォーム</title>
</head>
<body>
	<a href = "./manage">トップ</a>

	<h1>本申請画面</h1>

		<c:if test="${ not empty errorMessages }">
			<c:forEach items="${errorMessages}" var="message">
				<c:out value="${message}"/>
			</c:forEach>

			<c:remove var="errorMessages" scope="session"/>
		</c:if>

		<form action="suggestion" method="post">
			<label for="userId">利用者番号（ユーザーID）</label>
			<input type = "text" name = "userId" id = "userId">

			<label for="bookId">本のID</label>
			<input type = "text" name = "bookId" id = "bookId">

			<input type = "submit" value = "登録" />
		</form>
</body>
</html>