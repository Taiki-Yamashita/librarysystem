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
		<link href="./css/styleOkada.css" rel="stylesheet" type="text/css">
		<link href="./css/styleTaiki.css" rel="stylesheet" type="text/css">
		<link href="./css/styleKuniyoshi.css" rel="stylesheet" type="text/css">
	</head>
	<body>


		<h1>図書システムかりたいナ☆</h1>
		<h2>${loginUser.name}さんのお気に入り</h2>

		<input type="button" onclick="location.href='./logout'"value="ログアウト">

		<table border="1">
			<tr>
				<td><input type="button" onclick="location.href='./'"value="トップ"></td>
				<td><input type="button" onclick="location.href='./search'"value="検索"></td>
				<td><input type="button" onclick="location.href='./ranking'"value="ランキング"></td>
				<td><input type="button" onclick="location.href='./user'"value="マイページ"></td>
				</tr>
		</table>

		<c:forEach items="${favorites}" var="favorite">
			<c:if test="${favorite.userId == loginUser.id}">
				<c:set var="flag" value="1" />
			</c:if>
		</c:forEach>

		<c:if test="${ not empty errorMessages }">
			<c:forEach items="${errorMessages}" var="message">
				<font color="#ff0000"><c:out value="${message}" /></font><br>
			</c:forEach>
		</c:if>

		<c:if test="${not empty favorites && flag == '1'}">
			<table border="2" class="manage">
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
					<th>削除</th>
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
								<c:if test = "${favorite.disposing == 1}">整理中</c:if>
							</td>
							<td>
								<c:if test="${favorite.reserving == 0}">
									<form action = "reservingBook" method = "post">
										<input type = "hidden" name = "bookId" value = "${favorite.bookId}" >
										<input type = "hidden" id = "libraryId" name = "libraryId" value = "${favorite.libraryId}" >
										<input type = "hidden" id = "${loginUser.id}" name = "userId" value = "${loginUser.id}"  >
										<input type = "hidden" name = "num" value =1>
										<input type = "hidden" name = "reservation" value="${book.id}">
										<input type = "hidden" name = "fromFavorite" value = "1" />
										<c:if test="${not empty reservationMax}"><input type = "hidden" name = "reservationMax" value = "1" /></c:if>

										<c:forEach items="${circulationList}" var="circulationBook">
											<c:if test="${favorite.bookId == circulationBook.bookId &&
													circulationBook.userId == loginUser.id && circulationBook.lending == 1}">
												<c:set var="lendingFlag" value="1" />
											</c:if>
										</c:forEach>
										<c:if test="${not empty lendingFlag}"><input type="hidden" name="lendingFlag" value="1"></c:if>
										<c:remove var="lendingFlag" />

										<input class="reserve" type = "submit" value = "予約">
									</form>
								</c:if>
								<c:if test="${favorite.reserving != 0}">予約済み</c:if>
							</td>
							<td>
								<form action="./delete" method="POST">
									<!-- ログインユーザーのID -->
									<input type="hidden" value="${favorite.userId}" name="favoriteUserId">
									<input type="hidden" value="${favorite.bookId}" name="favoriteBookId">
									<input class="delete" type="submit"  value="解除" />
								</form>
							</td>
						</tr>
					</c:if>


				</c:forEach>
			</table>
		</c:if>
		<c:if test="${empty flag}">
		 	<h1>お気に入り未登録です</h1>
		</c:if>

		<c:remove var="flag"/>
		<!-- エラーメッセージ -->
		<c:remove var="errorMessages" scope="session"/>
	</body>
</html>