<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ランキング</title>
</head>
<body>

<a href="./">トップ</a>

<h2>人気ランキング</h2>

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



貸出件数ランキング<br>
<c:forEach  begin="1" end="20" step="1" varStatus="status" items="${circulations}" var="circulation">
	<c:out value="${status.index}"/>位<c:out value="${circulation.bookName }"></c:out>
		<form action = "reservingBook" method = "post">
			<input type = "hidden" name = "bookId" value = "${circulation.bookId}" >
			<input type = "hidden" id =  "${loginUser.libraryId}" name = "libraryId" value = "${loginUser.libraryId}" >
			<input type = "hidden" id = "${loginUser.id}" name = "userId" value = "${loginUser.id}"  >
			<input type = "hidden" name = "num" value =1>
			<input type = "hidden" name = "reservation" value="${book.id}">
			<input type = "hidden" name = "fromRanking" value = "1" >
			<input type = "submit" value = "予約" />
		</form>
</c:forEach>
<br>
予約件数ランキング<br>
<c:forEach  begin="0" end="20" step="1" varStatus="status" items="${reservations}" var="reservation">
	<c:out value="${status.count}"/>位<c:out value="${reservation.bookName }"></c:out>
		<form action = "reservingBook" method = "post">
			<input type = "hidden" name = "bookId" value = "${reservation.bookId}" >
			<input type = "hidden" id = "libraryId" name = "libraryId" value = "${book.libraryId}" >
			<input type = "hidden" id = "${loginUser.id}" name = "userId" value = "${loginUser.id}"  >
			<input type = "hidden" name = "num" value =1>
			<input type = "hidden" name = "reservation" value="${book.id}">
			<input type = "hidden" name = "fromRanking" value = "1" >
			<input type = "submit" value = "予約" />
		</form>
</c:forEach>

</body>
</html>