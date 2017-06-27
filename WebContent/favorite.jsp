<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>お気に入り</title>
</head>
<body>
	<a href = "./">トップ</a>
	<a href = "./search">検索</a>
	<a href = "./require">本のリクエスト</a>
	<a href = "./admin/manage">管理画面</a>
	<c:if test="${not empty favorites}">
		<table>
			<tr>
				<th>書籍名</th>
				<th>著者</th>
				<th>出版社</th>
				<th>カテゴリー</th>
				<th>種類</th>
				<th>図書館名</th>
				<th>ISBNID</th>
				<th>状態</th>
				<th>予約</th>
			</tr>
			<c:forEach items="${favorites}" var="favorite">
				<c:if test = "${loginUser.id == favorite.userId}">
					<tr>
						<td><c:out value="${favorite.bookName}" /></td>
						<td><c:out value="${favorite.author}" /></td>
						<td><c:out value="${favorite.publisher}" /></td>
						<td><c:out value="${favorite.category}" /></td>
						<td><c:out value="${favorite.type}" /></td>
						<td><c:out value="${favorite.libraryName}" /></td>
						<td><c:out value="${favorite.isbnId}" /></td>
						<td>
							<c:if test = "${favorite.keeping != 1 && favorite.lending != 1 &&
							 favorite.reserving != 1 && favorite.disposing != 1}">棚なう</c:if>
							<c:if test = "${favorite.keeping == 1}">保管中</c:if>
							<c:if test = "${favorite.lending == 1}">貸出中</c:if>
							<c:if test = "${favorite.reserving == 1}">予約中</c:if>
							<c:if test = "${favorite.disposing == 1}">整理中</c:if>
						</td>
						<td>
							<c:if test="${favorite.reserving == 1}">
								<form action = "reservingBook" method = "post">
									<input type = "hidden" name = "bookId" value = "${favorite.bookId}" >
									<input type = "hidden" id = "libraryId" name = "libraryId" value = "${favorite.libraryId}" >
									<input type = "hidden" id = "${loginUser.id}" name = "userId" value = "${loginUser.id}"  >
									<input type = "hidden" name = "num" value =1>
									<input type = "hidden" name = "reservation" value="${book.id}">
									<input type = "hidden" name = "fromFavorite" value = "1" />
								</form>
							</c:if>
							<c:if test="${favorite.reserving != 1}">予約済み</c:if>
						</td>
						<td>
							<form action="./delete" method="POST">
								<!-- ログインユーザーのID -->
								<input type="hidden" value="${favorite.userId}" name="favoriteUserId">
								<input type="hidden" value="${favorite.bookId}" name="favoriteBookId">
								<input type="submit"  value="お気に入り削除" />
							</form>
						</td>
					</tr>
				</c:if>


			</c:forEach>
		</table>
	</c:if>
	<c:if test="${empty favorites}">
	 	<h1>お気に入り未登録です</h1>
	</c:if>
</body>
</html>