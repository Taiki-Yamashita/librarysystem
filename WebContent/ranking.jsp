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
		<link href="./css/styleOkada.css" rel="stylesheet" type="text/css">
		<link href="./css/styleTaiki.css" rel="stylesheet" type="text/css">
		<link href="./css/styleKuniyoshi.css" rel="stylesheet" type="text/css">
	</head>
	<body>
		<h1>図書システムかりたいナ☆</h1>
		<h2>人気ランキング</h2>


		<c:if test="${empty loginUser}">
			<input type="button" onclick="location.href='./login'"value="ログイン">
		</c:if>

		<c:if test="${not empty loginUser}">
			<input type="button" onclick="location.href='./logout'"value="ログアウト">
		</c:if>
		<br>

		<table border="1" class="menuBar">
			<tr>
				<td><input type="button" onclick="location.href='./'"value="トップ"></td>
				<td><input type="button" onclick="location.href='./search'"value="検索"></td>
				<td><input type="button" onclick="location.href='./user'"value="マイページ"></td>
				<td><input type="button" onclick="location.href='./favorite'"value="お気に入り"></td>
			</tr>
		</table>

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
	<div class="errorMessages">
		<c:if test="${ not empty loginErrorMessages }">
			<c:forEach items="${loginErrorMessages}" var="message">
				<c:out value="${message}" /><br>
			</c:forEach>
		</c:if>
	</div>
		貸出件数ランキング<br>
		<c:if test="${not empty circulations }">
		<table border="2" class="manage">
			<tr>
				<th>順位</th><th>貸出数</th><th>書籍</th><th>著者</th><th>出版社</th><th>カテゴリ</th>
				<th>種類</th><th>図書館</th><th>状態</th><th>ISBN</th><th>予約</th><th>お気に入り</th>
			</tr>
			<c:forEach  begin="0" end="19" step="1" varStatus="status" items="${circulations}" var="circulation">
				<c:if test="${status.count ==1 }"><tr class="winner"></c:if>
				<c:if test="${status.count ==2 || status.count ==3}"><tr class="semiWinner"></c:if>
				<c:if test="${status.count >3}"><tr ></c:if>
					<td><c:out value="${status.count}"/>位</td>
					<td>
						<c:forEach items="${circulationCounts}" var="count" varStatus="statusCount">
							<c:if test="${status.count == statusCount.count}">
								<c:if test="${count !=-1}">
									<option value="${count}">${count}件</option>
								</c:if>
								<c:if test="${count ==-1}">
								0件
								</c:if>
							</c:if>
							</c:forEach>
					</td>
					<td>
						<c:forEach items="${books}" var="book">
							<c:if test="${book.id == circulation.bookId}">
								<c:out value="${book.name}"/>
							</c:if>
						</c:forEach>
					</td>
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
						<c:forEach items="${books}" var="book">
							<c:if test="${book.id == circulation.bookId}">
							<c:forEach items="${libraries }" var="library">
								<c:if test="${book.libraryId == library.id }">
								<c:out value="${library.name}"/>
								</c:if>
							</c:forEach>
							</c:if>
						</c:forEach>
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
								<input type="hidden" name="notLoginRanking" value="1">
								<input class="reserve" type = "submit" value = "予約" />
							</c:if>
							<c:if test="${not empty loginUser}">
								<c:forEach items="${isReservations}" var="reservation">
									<c:if test="${reservation.userId == loginUser.id && reservation.bookId
										== circulation.bookId && reservation.canceling ==0 && reservation.delivering ==0}">
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
									<c:if test="${not empty reservationMax}"><input type="hidden" name="reservationMax" value="1"></c:if>

									<c:forEach items="${circulationList}" var="circulationBook">
										<c:if test="${circulation.bookId == circulationBook.bookId &&
												circulationBook.userId == loginUser.id && circulationBook.lending == 1}">
											<c:set var="lendingFlag" value="1" />
										</c:if>
									</c:forEach>
									<c:if test="${not empty lendingFlag}"><input type="hidden" name="lendingFlag" value="1"></c:if>
									<c:remove var="lendingFlag" />

									<input class="reserve" type = "submit" value = "予約" />
								</c:if>
								<c:remove var="data" />
							</c:if>
						</form>
					</td>
					<td>
						<form action = "favorite" method = "post">
							<c:if test="${empty loginUser}">
								<input type="hidden" name="notLoginRanking" value="1">
								<input class="favorite" type = "submit" value = "お気に入り" />
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
									<input class="favorite" type="submit"  value="お気に入り" />
								</c:if>
								<c:remove var="data" />
							</c:if>
							<input type="hidden" value="1" name="num">
						</form>
					</td>
				</tr>
			</c:forEach>
		</table>
		</c:if>
		<c:if test="${empty circulations }">貸出がありません</c:if>

		<br>
		予約件数ランキング<br>
		<c:if test="${not empty reservations }">
		<table border="2" class="manage">
			<tr>
				<th>順位</th><th>予約数</th><th>書籍</th><th>著者</th><th>出版社</th><th>カテゴリ</th>
				<th>種類</th><th>図書館</th><th>状態</th><th>ISBN</th><th>予約</th><th>お気に入り</th>
			</tr>
			<c:forEach  begin="0" end="19" step="1" varStatus="status" items="${reservations}" var="reservation">
				<c:if test="${status.count ==1 }"><tr class="winner"></c:if>
				<c:if test="${status.count ==2 || status.count ==3}"><tr class="semiWinner"></c:if>
				<c:if test="${status.count >3}"><tr ></c:if>
					<td><c:out value="${status.count}"/>位</td>
					<td>
							<c:forEach items="${reservationCounts}" var="count" varStatus="statusCount">
								<c:if test="${status.count == statusCount.count}">
									<c:if test="${count !=-1}">
										<option value="${count}">${count}件</option>
									</c:if>
									<c:if test="${count ==-1}">
									0件
									</c:if>
								</c:if>
							</c:forEach>
					</td>
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
						<c:forEach items="${books}" var="book">
							<c:if test="${book.id == reservation.bookId}">
							<c:forEach items="${libraries }" var="library">
								<c:if test="${book.libraryId == library.id }">
								<c:out value="${library.name}"/>
								</c:if>
							</c:forEach>
							</c:if>
						</c:forEach>
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


							<c:if test="${empty loginUser}"><input type="hidden" name="notLoginRanking" value="1">
							<input class="reserve" type = "submit" value = "予約" />
							</c:if>
							<c:if test="${not empty loginUser}">
							<c:forEach items="${isReservations}" var="isReservation">
								<c:if test="${isReservation.userId == loginUser.id && isReservation.bookId
									== reservation.bookId && isReservation.canceling == 0 && isReservation.delivering == 0}">
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
								<c:if test="${not empty reservationMax}"><input type="hidden" name="reservationMax" value="1"></c:if>

								<c:forEach items="${circulationList}" var="circulationBook">
									<c:if test="${circulation.bookId == circulationBook.bookId &&
											circulationBook.userId == loginUser.id && circulationBook.lending == 1}">
										<c:set var="lendingFlag" value="1" />
									</c:if>
								</c:forEach>
								<c:if test="${not empty lendingFlag}"><input type="hidden" name="lendingFlag" value="1"></c:if>
								<c:remove var="lendingFlag" />

								<input class="reserve" type = "submit" value = "予約" />
							</c:if>
							<c:remove var="data" />
						</c:if>
						</form>
						</td>
						<td>
						<form action = "favorite" method = "post">
							<c:if test="${empty loginUser}"><input type="hidden" name="notLoginRanking" value="1">
							<input class="favorite" type = "submit" value = "お気に入り" />
							</c:if>
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
									<input class="favorite" type="submit"  value="お気に入り" />
								</c:if>
								<c:remove var="data" />
							</c:if>
							<input type="hidden" value="1" name="num">
						</form>
					</td>
				</tr>
			</c:forEach>
		</table>
		</c:if>
		<c:if test="${empty reservations}">予約がありません</c:if>
	<c:remove var="errorMessages" scope="session"/>
		<c:remove var="loginErrorMessages" scope="session"/>
	</body>
</html>