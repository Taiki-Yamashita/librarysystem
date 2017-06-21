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
	<p><a href="./manageBook">管理画面</a></p>
	<table>
		<tr><th>予約者ID</th>
			<th>予約者</th>
			<th>本の名前</th>
			<th>受取図書館</th>
			<th>予約日</th>
		</tr>
		<c:forEach items="${reservations}" var="reservation">
			<tr>
				<td>${reservation.userId}</td>
				<td>
					<c:forEach items="${users}" var="user">
						<c:if test="${reservation.userId ==user.id}">
							<option value="${user.id}">${user.name}</option>
						</c:if>
					</c:forEach>
				</td>
				<td>${reservation.bookName}</td>
				<td>
					<c:forEach items="${libraries}" var="library">
						<c:if test="${reservation.libraryId ==library.id}">
							<option value="${library.id}">${library.name}</option>
						</c:if>
					</c:forEach>
				</td>
				<td>${reservation.reservedDate }</td>


			</tr>
		</c:forEach>
	</table>
</body>
</html>