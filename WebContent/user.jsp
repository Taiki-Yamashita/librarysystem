
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

<p>ユーザー情報</p>


	<table>
		<tr>
			<th>ユーザー</th>
			<th>ユーザーID</th>
			<th>登録図書館</th>
			<th>ポイント</th>
		</tr>


	<c:forEach items="${users}" var="user">
		<c:if test="${user.id == loginUser.id }">
			<tr>
				<td>${user.name }</td>
				<td>${user.id }</td>
				<td>
					<c:forEach items="${ libraries}" var="library">
						<c:if test="${library.id == user.libraryId}">${library.name}</c:if>
					</c:forEach>
				</td>
				<td>${user.point }</td>
			</tr>
		</c:if>
	</c:forEach>

	</table>

	<!-- 貸出 -->
	<p>貸出一覧</p>
	<table>
		<tr>
			<th>本の名前</th>
			<th>貸出日</th>
			<th>期限</th>
			<th>貸出図書館</th>

		</tr>
		<c:forEach items="${circulations}" var="circulation">

			<c:if test="${circulation.userId == loginUser.id && circulation.returning == 0 && circulation.lending ==1}">
				<tr>
					<td>
						<c:forEach items="${books}" var="book">
							<c:if test="${book.id == circulation.bookId}">${book.name}</c:if>
						</c:forEach>
					</td>
					<td>
						<c:out value="${circulation.lentDate }"></c:out>
					</td>
					<td>
						<c:out value="${circulation.limitedDate }"></c:out>
					</td>

					<td>
						<c:forEach items="${ libraries}" var="library">
							<c:if test="${library.id == circulation.libraryId}">${library.name}</c:if>
						</c:forEach>
					</td>

				</tr>
			</c:if>
		</c:forEach>
	</table>


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
						<c:forEach items="${books}" var="book">
							<c:if test="${book.id == reservation.bookId}">${reservation.bookName}</c:if>

						</c:forEach>
					</td>
					<td>
						<c:forEach items="${ libraries}" var="library">
							<c:if test="${library.id == reservation.libraryId}">${library.name}</c:if>
						</c:forEach>
					</td>
					<td>${reservation.reservedDate}</td>
					<td>
						<form action = "cancelingBook" method = "post">
							<input type = "hidden" name = "bookId" value = "${reservation.bookId}" >
							<input type = "hidden" name ="bookName" value= "${reservation.bookName}" >
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

