
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>予約管理</title>
		<link href="./css/styleOkada.css" rel="stylesheet" type="text/css">
		<link href="./css/styleTaiki.css" rel="stylesheet" type="text/css">
		<link href="./css/styleKuniyoshi.css" rel="stylesheet" type="text/css">
	</head>
	<body class="userPage">
		<h1>図書システムかりたいナ☆</h1>
		<h2>${loginUser.name}さんのマイページ</h2>
		<div class="subButton">
			<input class="logout" type="button" class="subButton" onclick="location.href='./logout'"value="ログアウト">
		</div><br><br>

		<table class="menuBar">
			<tr>
				<td><input type="button" onclick="location.href='./'"value="トップ"></td>
				<td><input type="button" onclick="location.href='./search'"value="検索"></td>
				<td><input type="button" onclick="location.href='./ranking'"value="貸出/予約ランキング"></td>
				<td><input type="button" onclick="location.href='./favorite'"value="お気に入り"></td>
				</tr>
		</table>

		<h2>【登録情報】</h2>
		<table border="2" class="manage">
			<tr class="font1">
				<td>
					<!-- ユーザー情報 -->
					<table>
						<tr>
							<th>住所:</th>
							<td>${loginUser.address}</td>
						</tr>
						<tr>
							<th>電話番号:</th>
							<td>${loginUser.tel}</td>
						</tr>
						<tr>
							<th>メールアドレス:</th>
							<td>${loginUser.mail}</td>
						</tr>
						<tr>
							<th>受取図書館:</th>
							<td>
								<c:forEach items="${libraries}" var="library">
									<c:if test="${library.id == loginUser.libraryId}">${library.name}</c:if>
								</c:forEach>
							</td>
						</tr>
						<tr>
							<th>アカウントの有効期限:</th>
							<td>
								<fmt:parseDate var="date" value="${loginUser.registerDate}(現在は登録日)" pattern="yyyy-MM-dd HH:mm:ss" />
								<fmt:formatDate pattern = "yyyy年MM月dd日" value = "${date}" />
							</td>
						</tr>
						<tr>
							<th>ポイント:</th>
							<td>${loginUser.point}</td>
						</tr>
					</table>
				</td>
				<td>
					<button type='submit' class="userChange" onclick="location.href='./renewPassword'">ログインID<br>パスワード編集</button>
					<p>住所などの変更は窓口まで</p>
				</td>
			</tr>
		</table>



		<!-- 貸出 -->
		<c:forEach items="${circulations}" var="circulation">
			<c:if test="${circulation.userId == loginUser.id && circulation.returning == 0 && circulation.lending ==1}">
				<c:set var="flag" value="1" />
			</c:if>
		</c:forEach>
		<c:if test="${not empty flag}">
			<h2>【借りている本の一覧】</h2>
			<table border="2" class="manage">
				<tr class="font1">
					<th>本の名前</th>
					<th>貸出日</th>
					<th>期限</th>
					<th>貸出図書館</th>

				</tr>
				<c:forEach items="${circulations}" var="circulation">

					<c:if test="${circulation.userId == loginUser.id && circulation.returning == 0 && circulation.lending ==1}">
						<tr class="font2">
							<td>
								<c:forEach items="${books}" var="book">
									<c:if test="${book.id == circulation.bookId}">${book.name}</c:if>
								</c:forEach>
							</td>
							<td>
								<fmt:parseDate var="date" value="${circulation.lentDate}" pattern="yyyy-MM-dd HH:mm:ss" />
								<fmt:formatDate pattern = "yyyy年MM月dd日" value = "${date}" />
							</td>
							<td>
								<fmt:parseDate var="date" value="${circulation.limitedDate}" pattern="yyyy-MM-dd HH:mm:ss" />
								<fmt:formatDate pattern = "yyyy年MM月dd日" value = "${date}" />
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
		</c:if>

		<c:if test="${empty flag}">
			<h3>※借りている本はありません</h3>
		</c:if>

		<!-- 予約テーブル -->
		<c:forEach items="${reservations}" var="reservation">
				<c:if test="${reservation.userId == loginUser.id && reservation.canceling == 0 && reservation.delivering == 0}">
					<c:set var="flag2" value="1" />
				</c:if>
		</c:forEach>

		<c:if test="${not empty flag2}">
			<h2>【予約一覧】</h2>
				<table border="2" class="manage">
					<tr class="font1">
						<th>本の名前</th>
						<th>受取図書館</th>
						<th>予約日</th>
						<th>予約取消し</th>
					</tr>
					<c:forEach items="${reservations}" var="reservation">

						<c:if test="${reservation.userId == loginUser.id && reservation.canceling == 0 && reservation.delivering == 0}">
							<tr class="font2">
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
								<td>
									<fmt:parseDate var="date" value="${reservation.reservedDate}" pattern="yyyy-MM-dd HH:mm:ss" />
									<fmt:formatDate pattern = "yyyy年MM月dd日" value = "${date}" />
								</td>
								<td>
									<form action = "cancelingBook" method = "post">
										<input type = "hidden" name = "bookId" value = "${reservation.bookId}" >
										<input type = "hidden" name ="bookName" value= "${reservation.bookName}" >
										<input type = "hidden" id = "libraryId" name = "libraryId" value = "${reservation.libraryId }" >
										<input type = "hidden" name = "time" value = "${reservation.reservedDate }">
										<c:if test="${reservation.canceling == 0 }">
											<input type = "hidden" name = "num" value =1>
											<input class="cancel" type = "submit" value = "取消" />
										</c:if>
									</form>
								</td>
							</tr>
						</c:if>
					</c:forEach>
				</table>
		</c:if>

		<c:if test="${empty flag2}">
			<h2>※予約してる本はありません</h2>
		</c:if>
	</body>
</html>

