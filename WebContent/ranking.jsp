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

		<c:if test="${ not empty loginErrorMessages }">
			<c:forEach items="${loginErrorMessages}" var="message">
				<font color="#ff0000"><c:out value="${message}" /></font><br>
			</c:forEach>
		</c:if>

		貸出件数ランキング<br>
		<table>
			<tr>
				<th>順位</th><th>書籍</th><th>著者</th><th>出版社</th><th>カテゴリ</th>
				<th>種類</th><th>図書館</th><th>状態</th><th>ISBN</th><th>予約</th>
			</tr>
			<c:forEach  begin="0" end="19" step="1" varStatus="status" items="${circulations}" var="circulation">
				<tr>
					<td><c:out value="${status.count}"/>位</td>
					<td><c:out value="${circulation.bookName}"/></td>
					<td>
						<c:forEach items="${books}" var="book">
							<c:if test="${book.id == circulation.bookId}">
								<c:out value="${book.author}"/>
							</c:if>
						</c:forEach>
					</td>
					<td>
						<c:forEach items="${books}" var="book">
							<c:if test="${book.id == circulation.bookId}">
								<c:out value="${book.publisher}"/>
							</c:if>
						</c:forEach>
					</td>
					<td>
						<c:forEach items="${books}" var="book">
							<c:if test="${book.id == circulation.bookId}">
								<c:out value="${book.category}"/>
							</c:if>
						</c:forEach>
					</td>
					<td>
						<c:forEach items="${books}" var="book">
							<c:if test="${book.id == circulation.bookId}">
								<c:out value="${book.type}"/>
							</c:if>
						</c:forEach>
					</td>
					<td>
						<c:out value="${circulation.bookId}"/>
					</td>
					<td>
						<c:forEach items="${books}" var="book">
							<c:if test="${book.id == circulation.bookId}">
								<c:if test="${book.lending == 0}"><c:out value="棚保管中"/></c:if>
								<c:if test="${book.lending == 1}"><c:out value="貸出中"/></c:if>
							</c:if>
						</c:forEach>
					</td>
					<td>
						<c:forEach items="${books}" var="book">
							<c:if test="${book.id == circulation.bookId}">
								<c:out value="${book.isbnId}"/>
							</c:if>
						</c:forEach>
					</td>
					<td>


						<form action = "reservingBook" method = "post">
							<c:if test="${empty loginUser}">
							<input type="button" onclick="location.href='./login'"value="予約">
							</c:if>
							<c:if test="${not empty loginUser}">
							<c:forEach items="${isReservations}" var="reservation">
								<c:if test="${reservation.userId == loginUser.id && reservation.bookId
									== circulation.bookId &&reservation.canceling ==0}">
									<c:set var="data" value="0" />
								</c:if>
							</c:forEach>
							<c:if test="${data == 0}">予約済み</c:if>
							<c:if test="${data != 0}">
							<input type = "hidden" name = "bookId" value = "${circulation.bookId}" >
							<input type = "hidden" id =  "${loginUser.libraryId}" name = "libraryId" value = "${loginUser.libraryId}" >
							<input type = "hidden" id = "${loginUser.id}" name = "userId" value = "${loginUser.id}"  >
							<input type = "hidden" name = "num" value =1>
							<input type = "hidden" name = "reservation" value="${book.id}">
							<input type = "hidden" name = "fromRanking" value = "1" >
							<input type = "submit" value = "予約" />
							</c:if>
							<c:remove var="data" />
						</c:if>
						</form>

					</td>
					<td>
						<form action = "favorite" method = "post">
							<c:if test="${empty loginUser}">
								<input type="button" onclick="location.href='./login'"value="お気に入り">
							</c:if>
							<c:if test="${not empty loginUser}">
								<c:forEach items="${isFavorites}" var="favorite">
									<c:if test="${favorite.userId == loginUser.id && favorite.bookId
										== circulation.bookId }">
										<c:set var="data" value="0" />
									</c:if>
								</c:forEach>
								<c:if test="${data == 0}">お気に入り済み</c:if>
								<c:if test="${data != 0}">
									<input type="hidden" value="${loginUser.id}" name="userId">
									<input type="hidden" value="${circulation.bookId}" name="bookId">
									<input type="submit"  value="お気に入り" />
								</c:if>
								<c:remove var="data" />
							</c:if>
							<input type="hidden" value="1" name="num">
						</form>
					</td>
				</tr>
			</c:forEach>
		</table>

		<br>
		予約件数ランキング<br>
		<table>
			<tr>
				<th>順位</th><th>書籍</th><th>著者</th><th>出版社</th><th>カテゴリ</th>
				<th>種類</th><th>図書館</th><th>状態</th><th>ISBN</th><th>予約</th>
			</tr>
			<c:forEach  begin="0" end="19" step="1" varStatus="status" items="${reservations}" var="reservation">
				<tr>
					<td><c:out value="${status.count}"/>位</td>
					<td><c:out value="${reservation.bookName }"/></td>
					<td>
						<c:forEach items="${books}" var="book">
							<c:if test="${book.id == reservation.bookId}">
								<c:out value="${book.author}"/>
							</c:if>
						</c:forEach>
					</td>
					<td>
						<c:forEach items="${books}" var="book">
							<c:if test="${book.id == reservation.bookId}">
								<c:out value="${book.publisher}"/>
							</c:if>
						</c:forEach>
					</td>
					<td>
						<c:forEach items="${books}" var="book">
							<c:if test="${book.id == reservation.bookId}">
								<c:out value="${book.category}"/>
							</c:if>
						</c:forEach>
					</td>
					<td>
						<c:forEach items="${books}" var="book">
							<c:if test="${book.id == reservation.bookId}">
								<c:out value="${book.type}"/>
							</c:if>
						</c:forEach>
					</td>
					<td>
						図書館
					</td>
					<td>
					<c:forEach items="${books}" var="book">
					<c:if test="${book.id == reservation.bookId }">
					<c:if test="${book.keeping ==1}">保管中</c:if>
					<c:if test="${book.lending ==1}">貸出中</c:if>
					<c:if test="${book.disposing ==1}">整理中</c:if>
					</c:if>
					</c:forEach>
					</td>
					<td>
						<c:forEach items="${books}" var="book">
							<c:if test="${book.id == reservation.bookId}">
								<c:out value="${book.isbnId}"/>
							</c:if>
						</c:forEach>
					</td>
					<td>

						<form action = "reservingBook" method = "post">
							<c:if test="${empty loginUser}"><input type="hidden" name="notLoginRanking" value="1"></c:if>
							<input type = "submit" value = "予約" />
							<c:if test="${not empty loginUser}">
							<c:forEach items="${isReservations}" var="isReservation">
								<c:if test="${isReservation.userId == loginUser.id && isReservation.bookId
									== reservation.bookId &&isReservation.canceling ==0}">
									<c:set var="data" value="0" />
								</c:if>
							</c:forEach>
							<c:if test="${data == 0}">予約済み</c:if>
							<c:if test="${data != 0}">
							<input type = "hidden" name = "bookId" value = "${reservation.bookId}" >
							<input type = "hidden" id =  "${loginUser.libraryId}" name = "libraryId" value = "${loginUser.libraryId}" >
							<input type = "hidden" id = "${loginUser.id}" name = "userId" value = "${loginUser.id}"  >
							<input type = "hidden" name = "num" value =1>
							<input type = "hidden" name = "reservation" value="${book.id}">
							<input type = "hidden" name = "fromRanking" value = "1" >
							</c:if>
							<c:remove var="data" />
						</c:if>
						</form>

					</td>
					<td>
						<form action = "favorite" method = "post">
							<c:if test="${empty loginUser}"><input type="hidden" name="notLoginFavorite" value="1"></c:if>
							<input type = "submit" value = "お気に入り" />
							<c:if test="${not empty loginUser}">
								<c:forEach items="${isFavorites}" var="favorite">
									<c:if test="${favorite.userId == loginUser.id && favorite.bookId
										== reservation.bookId }">
										<c:set var="data" value="0" />
									</c:if>
								</c:forEach>
								<c:if test="${data == 0}">お気に入り済み</c:if>
								<c:if test="${data != 0}">
									<input type="hidden" value="${loginUser.id}" name="userId">
									<input type="hidden" value="${reservation.bookId}" name="bookId">
									<input type="submit"  value="お気に入り" />
								</c:if>
								<c:remove var="data" />
							</c:if>
							<input type="hidden" value="1" name="num">
						</form>
					</td>
				</tr>
			</c:forEach>
		</table>
	<c:remove var="errorMessages" scope="session"/>
		<c:remove var="loginErrorMessages" scope="session"/>

	</body>
</html>