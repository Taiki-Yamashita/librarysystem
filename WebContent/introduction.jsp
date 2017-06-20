<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>本の紹介</title>
</head>
<body>
	<h2>みんなのオススメ本</h2>
	<a href = "./">トップ</a>

	<table>
		<tr><th>書籍名</th><th>推薦者</th><th>著者</th><th>出版社</th><th>予約</th></tr>
		<c:forEach items="${introductions}" var="introduction">
			<tr>
				<td>${introduction.bookName}</td>
				<td>${introduction.userName}</td>
				<td>${introduction.author}</td>
				<td>${introduction.publisher}</td>
				<td>
					<form action="admin/reservingBook" method="post">
						<input type="hidden" name="num" id="1" value="1">
						<input type="hidden" name="bookId" id="${introduction.bookId}" value="${introduction.bookId}">
						<input type="hidden" name="userId" id="${introduction.userId}" value="${introduction.bookId}">
						<input type="submit" value="予約">
					</form>
				</td>
			</tr>
		</c:forEach>
	</table>


</body>
</html>