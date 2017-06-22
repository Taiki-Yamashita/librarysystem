<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>リクエスト</title>
</head>
<body>
	<a href = "./">トップ</a>

	<h1>本のリクエスト</h1>

		<c:if test="${ not empty errorMessages }">
			<c:forEach items="${errorMessages}" var="message">
				<c:out value="${message}"/>
			</c:forEach>

			<c:remove var="errorMessages" scope="session"/>
		</c:if>

		<form action="require" method="post">

			<label for="userName">申請者</label>
			<c:out value="${loginUser.name}"></c:out><br/>
			<input type="hidden" name="userName" value="${loginUser.name}">

			<label for="bookName">書籍名</label>
			<input name="bookName" value="${newRequire.bookName}"/>*必須<br/>

			<label for="author">著者</label>
			<input name="author" value="${newRequire.author}"/><br/>

			<label for="publisher">出版社</label>
			<input name="publisher"  value="${newRequire.publisher}"/><br/>

			<input type="submit" value="申請" />
		</form>
</body>
</html>