<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>本情報管理</title>
	</head>
	<body>

		<p>◎検索</p>
		<form action="./manageBook" method="GET">
			<table>
				<tr>
					<td>
						<select name="selectBox">
							<c:if test="${not empty selectBox}">
								<option value="${selectBoxId}">${selectBox}</option>
							</c:if>
							<c:if test="${selectBoxId != 1}"><option value="1">全て</option></c:if>
							<c:if test="${selectBoxId != 2}"><option value="2">書籍</option></c:if>
							<c:if test="${selectBoxId != 3}"><option value="3">著者</option></c:if>
							<c:if test="${selectBoxId != 4}"><option value="4">出版社</option></c:if>
							<c:if test="${selectBoxId != 5}"><option value="5">ISBN番号</option></c:if>
						</select>が
					</td>
					<td>
						<c:if test="${not empty freeWord}">
							<input type="text" name="freeWord" value="${freeWord}"/>
						</c:if>
						<c:if test="${empty freeWord}">
							<input type="text" name="freeWord" placeholder="未記入で全て検索"/>
						</c:if>
					</td>
					<td>
						<select name="condition">
							<c:if test="${not empty condition}">
								<c:if test="${condition == 1}"><option value="1">を含む</option></c:if>
								<c:if test="${condition == 2}"><option value="2">から始まる</option></c:if>
								<c:if test="${condition == 3}"><option value="3">で終わる</option></c:if>
								<c:if test="${condition == 4}"><option value="4">と一致する</option></c:if>

							</c:if>
							<c:if test="${condition != 1}"><option value="1">を含む</option></c:if>
							<c:if test="${condition != 2}"><option value="2">から始まる</option></c:if>
							<c:if test="${condition != 3}"><option value="3">で終わる</option></c:if>
							<c:if test="${condition != 4}"><option value="4">と一致する</option></c:if>
						</select>
					</td>
				</tr>
			</table>

			<table>
				<tr>
					<td>状態</td>
					<td>
						<c:if test="${empty bookStatus}">
							<input type="radio" name="bookStatus" value="1" checked>全て
							<input type="radio" name="bookStatus" value="2">棚保管中
							<input type="radio" name="bookStatus" value="3">貸出中
							<input type="radio" name="bookStatus" value="4">整理中
						</c:if>
						<c:if test="${not empty bookStatus}">
							<c:if test="${bookStatus == 1}"><input type="radio" name="bookStatus" value="1" checked>全て</c:if>
							<c:if test="${bookStatus != 1}"><input type="radio" name="bookStatus" value="1">全て</c:if>
							<c:if test="${bookStatus == 2}"><input type="radio" name="bookStatus" value="2" checked>棚保管中</c:if>
							<c:if test="${bookStatus != 2}"><input type="radio" name="bookStatus" value="2">棚保管中</c:if>
							<c:if test="${bookStatus == 3}"><input type="radio" name="bookStatus" value="3" checked>貸出中</c:if>
							<c:if test="${bookStatus != 3}"><input type="radio" name="bookStatus" value="3">貸出中</c:if>
							<c:if test="${bookStatus == 4}"><input type="radio" name="bookStatus" value="4" checked>整理中</c:if>
							<c:if test="${bookStatus != 4}"><input type="radio" name="bookStatus" value="4">整理中</c:if>
						</c:if>
					</td>
				</tr>
			</table>

			<table>
				<tr>
					<td>図書館</td>
					<td>
						<c:if test="${empty selectedLibrary}">
							<input type="radio" name="selectedLibrary" value="0" checked>全て
							<c:forEach items="${libraryList}" var="library" varStatus="libraryCount">
								<input type="radio" name="selectedLibrary" value="${libraryCount.count}">${library.name}
							</c:forEach>
						</c:if>
						<c:if test="${not empty selectedLibrary}">
							<c:if test="${selectedLibrary == 0}"><input type="radio" name="selectedLibrary" value="0" checked>全て</c:if>
							<c:if test="${selectedLibrary != 0}"><input type="radio" name="selectedLibrary" value="0">全て</c:if>
							<c:forEach items="${libraryList}" var="library" varStatus="libraryCount">
								<c:if test="${libraryCount.count == selectedLibrary}">
									<input type="radio" name="selectedLibrary" value="${libraryCount.count}" checked>${library.name}
								</c:if>
								<c:if test="${libraryCount.count != selectedLibrary}">
									<input type="radio" name="selectedLibrary" value="${libraryCount.count}">${library.name}
								</c:if>
							</c:forEach>
						</c:if>
					</td>
				</tr>
			</table>

			<table>
				<tr>
					<td>棚番号</td>
					<td>
						<c:if test="${empty selectedShelfId}">
							<input type="radio" name="selectedShelfId" value="0" checked>全て
							<c:forEach items="${shelfIdList}" var="shelfId" varStatus="shelfIdCount">
								<input type="radio" name="selectedShelfId" value="${shelfIdCount.count}">${shelfId.shelfId}
							</c:forEach>
						</c:if>
						<c:if test="${not empty selectedShelfId}">
							<c:if test="${selectedShelfId == 0}"><input type="radio" name="selectedShelfId" value="0" checked>全て</c:if>
							<c:if test="${selectedShelfId != 0}"><input type="radio" name="selectedShelfId" value="0">全て</c:if>
							<c:forEach items="${shelfIdList}" var="shelfId" varStatus="shelfIdCount">
								<c:if test="${shelfIdCount.count == selectedShelfId}">
									<input type="radio" name="selectedShelfId" value="${shelfIdCount.count}" checked>${shelfId.shelfId}
								</c:if>
								<c:if test="${shelfIdCount.count != selectedShelfId}">
									<input type="radio" name="selectedShelfId" value="${shelfIdCount.count}">${shelfId.shelfId}
								</c:if>
							</c:forEach>
						</c:if>
					</td>
				</tr>
			</table>

			<table>
				<tr>
					<td>予約の有無</td>
					<td>
						<c:if test="${empty isReserving}">
							<input type="radio" name="isReserving" value="1" checked>全て
							<input type="radio" name="isReserving" value="2">予約
							<input type="radio" name="isReserving" value="3">未予約
						</c:if>
						<c:if test="${not empty isReserving}">
							<c:if test="${isReserving == 1}"><input type="radio" name="isReserving" value="1" checked>全て</c:if>
							<c:if test="${isReserving != 1}"><input type="radio" name="isReserving" value="1">全て</c:if>
							<c:if test="${isReserving == 2}"><input type="radio" name="isReserving" value="2" checked>予約</c:if>
							<c:if test="${isReserving != 2}"><input type="radio" name="isReserving" value="2">予約</c:if>
							<c:if test="${isReserving == 3}"><input type="radio" name="isReserving" value="3" checked>未予約</c:if>
							<c:if test="${isReserving != 3}"><input type="radio" name="isReserving" value="3">未予約</c:if>
						</c:if>
					</td>
				</tr>
			</table>

			<table>
				<tr>
					<td>延滞の有無</td>
					<td>
						<c:if test="${empty delay}">
							<input type="radio" name="delay" value="1" checked>全て
							<input type="radio" name="delay" value="2" >延滞無
							<input type="radio" name="delay" value="3">延滞有
						</c:if>
						<c:if test="${not empty delay}">
							<c:if test="${delay == 1}"><input type="radio" name="delay" value="1" checked>全て</c:if>
							<c:if test="${delay != 1}"><input type="radio" name="delay" value="1">全て</c:if>
							<c:if test="${delay == 2}"><input type="radio" name="delay" value="2" checked>延滞無</c:if>
							<c:if test="${delay != 2}"><input type="radio" name="delay" value="2">延滞無</c:if>
							<c:if test="${delay == 3}"><input type="radio" name="delay" value="3" checked>延滞有</c:if>
							<c:if test="${delay != 3}"><input type="radio" name="delay" value="3">延滞有</c:if>
						</c:if>
					</td>
				</tr>
			</table>
			<input type="hidden" name="isSearching" value="1">
			<input type="submit" value="絞込み">
		</form>
		<p><input type="button" onclick="location.href='./manageBook'"value="クリア"></p>
		<hr width="1500px">

		<p>◎本リスト</p>
		<p><a href="./manage">管理画面</a></p>

		<form action="addBook" method ="get">
			<input type = "submit" value = "本の追加" />
		</form><br>

		<c:if test="${ not empty errorMessages }">
			<c:forEach items="${errorMessages}" var="message">
				<font color="#ff0000"><c:out value="${message}" /></font><br>
			</c:forEach>
		</c:if>

		<c:if test="${not empty books}">
			<table>
				<tr><th>名前</th><th>著者</th><th>出版社</th><th>カテゴリ</th><th>種類</th><th>図書館</th><th>棚番号</th><th>出版日</th><th>本の状態</th><th>予約数</th><th>延滞</th></tr>
				<c:forEach items="${books}" var="book" varStatus="statusBook">
					<tr>
						<td>${book.name}</td>
						<td>${book.author}</td>
						<td>${book.publisher}</td>
						<td>${book.category}</td>
						<td>${book.type}</td>
						<td>
							<c:forEach items="${libraryList}" var="library">
								<c:if test="${book.libraryId ==library.id}">
									<option value="${library.id}">${library.name}</option>
								</c:if>
							</c:forEach>
						</td>
						<td>${book.shelfId}</td>

						<td>
							<fmt:parseDate var="date" value="${book.publishedDate}" pattern="yyyy-MM-dd HH:mm:ss" />
							<fmt:formatDate pattern = "yyyy年MM月dd日" value = "${date}" />
						</td>
						<td>
							<c:if test="${book.keeping ==1}">保管中</c:if>
							<c:if test="${book.lending ==1}">貸出中</c:if>
							<c:if test="${book.disposing ==1}">整理中</c:if>
						<td>
							<c:forEach items="${reservationCounts}" var="count" varStatus="statusCount">
								<c:if test="${statusBook.count == statusCount.count}">
									<c:if test="${count !=-1}">
										<option value="${count}">${count}</option>
									</c:if>
									<c:if test="${count ==-1}">
										0
									</c:if>
								</c:if>
							</c:forEach>
						</td>
						<td>
							<c:forEach items="${notReturnedCounts}" var="count" varStatus="statusCount">
								<c:if test="${statusBook.index == statusCount.index}">
									<c:if test="${count ==1}">
										<option value="${count}">！</option>
										</c:if>
									<c:if test="${count !=1}">

									</c:if>
								</c:if>
							</c:forEach>
						</td>
						<td>
					   	 	<form action = "editBook" method = "get">
					   	 		<input type = "hidden" name = "id" value = "${book.id}" >
					   	 		<input type = "submit" value = "編集" />
					   	 	</form>
				   	 	</td>
				   	 	<td>
					   	 	<form action = "circulation" method = "get">
					   	 		<input type = "hidden" name = "id" value = "${book.id}" >
					   	 		<input type = "submit" value = "貸出履歴" />
					   	 	</form>
				   	 	</td>
				   	 	<td>
							<c:forEach items="${reservationCounts}" var="count" varStatus="statusCount">
								<c:if test="${statusBook.index == statusCount.index}">
									<c:if test="${count !=-1}">
								   	 	<form action = "reservation" method = "get">
								   	 		<input type = "hidden" name = "id" value = "${book.id}" >
								   	 		<input type = "submit" value = "予約一覧" />
								   	 	</form>
									</c:if>
									<c:if test="${count ==-1}">
										予約なし
									</c:if>
								</c:if>
							</c:forEach>
				   	 	</td>
				   	 	<td>
				   	 	<c:forEach items="${notReturnedCounts}" var="count" varStatus="statusCount">
							<c:if test="${statusBook.index == statusCount.index}">
								<c:if test="${count ==1}">
								   	<form action = "notReturned" method = "get">
								   		 <input type = "hidden" name = "id" value = "${book.id}" >
								   	 	<input type = "submit" value = "未返却者情報" />
								   	</form>
					   	 		</c:if>
					   	 		<c:if test="${count !=1}">
					   	 			<c:out value="未返却なし"></c:out>
					   	 		</c:if>
					   	 	</c:if>
					   	 </c:forEach>
				   	 	</td>
					</tr>
				</c:forEach>
			</table>
		</c:if>

		<!-- エラーメッセージ -->
		<c:remove var="errorMessages" scope="session"/>

	</body>
</html>