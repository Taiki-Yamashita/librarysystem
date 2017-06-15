<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>予約管理</title>
</head>
<body>
	<p>予約管理</p>
	<p><a href="./manage">管理画面</a></p>
	<table>
		<tr><th>予約者</th><th>本</th><th>本の名前</th><th>受取図書館</th><th>貸し出し予定日</th></tr>
		<c:forEach items="${reservations}" var="reservation">
			<tr>
				<td>${reservation.userId}</td>
				<td>${reservation.bookId}</td>
				<td>${reservation.bookName }</td>
				<td>${reservation.libraryId}</td>
				<td>貸し出し予定日</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>