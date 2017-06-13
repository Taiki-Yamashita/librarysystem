<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>リクエスト</title>
</head>
<body>
	<a href = "./top">トップ</a>
	<a href = "./">戻る</a>

	<h1>本申請画面</h1>

		<c:if test="${ not empty errorMessages }">
			<c:forEach items="${errorMessages}" var="message">
				<c:out value="${message}"/>
			</c:forEach>

			<c:remove var="errorMessages" scope="session"/>
		</c:if>

		<a href = "./">トップ</a>

		<form action="require" method="post">

			<label for="userName">申請者</label>
			<input name="userName" value="${newRequire.userName}" id="userName"/><br/>

			<label for="bookName">書名</label>
			<input name="bookName" value="${newRequire.bookName}" id="bookName"/><br/>

			<label for="author">著者</label>
			<input name="author" value="${newRequire.author}" id="author"/><br/>

			<label for="publisher">出版社</label>
			<input name="publisher"  value="${newRequire.publisher}"  id="publisher"/><br/>

			<input type="submit" value="登録" />
		</form>
</body>
</html>