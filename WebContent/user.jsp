
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
	<p>マイページ管理画面</p>

	<a href = "./">トップ</a>
	<a href = "./search">検索</a>
	<a href = "./favorite">お気に入り</a>
	<a href = "./require">本のリクエスト</a>
	<a href = "./admin/manage">管理画面</a>
	<a href = "./introduction">本の紹介</a>

	<p>予約一覧</p>
	<table>
		<tr>
			<th>本の名前</th>
			<th>受取図書館</th>
			<th>予約日</th>
			<th>キャンセル</th>
		</tr>
		<c:forEach items="${reservations}" var="reservation">
			<c:if test="${reservation.userId == loginUser.id && reservation.canceling == 0}">
				<tr>
					<td>
						<c:forEach items="books" var="book">
							<c:if test="${book.id == reservation.bookId}">${book.name}</c:if>
						</c:forEach>
					</td>
					<td>
						<c:forEach items="libraries" var="library">
							<c:if test="${library.id == reservation.libraryId}">${library.name}</c:if>
						</c:forEach>
					</td>
					<td>${reservation.reservedDate}</td>
					<td>
						<form action = "cancelingBook" method = "post">
							<input type = "hidden" name = "bookId" value = "${reservation.bookId}" >
							<input type = "hidden" id = "libraryId" name = "libraryId" value = "${reservation.libraryId }" >
							<input type = "hidden" name = "time" value = "${reservation.reservedDate }">
							<c:if test="${reservation.canceling == 0 }">
								<input type = "hidden" name = "num" value =1>
								<input type = "submit" value = "キャンセル" />
							</c:if>
						</form>
					</td>
				</tr>
			</c:if>
		</c:forEach>
	</table>
</body>
</html>

