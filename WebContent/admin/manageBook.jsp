<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>本情報管理</title>
</head>
<body>
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
	<p>本の編集</p>
	<p><a href="./manage">管理画面</a></p>
	<form action="addBook" method ="get">
	<input type = "submit" value = "本の追加" />
	</form>

	<table>
		<tr><th>ID</th><th>名前</th><th>著者</th><th>出版社</th><th>カテゴリ</th><th>種類</th><th>図書館</th><th>棚番号</th><th>出版日</th><th>本の状態</th><th>予約数</th><th>延滞</th></tr>
		<c:forEach items="${books}" var="book" varStatus="statusBook">
			<tr>
				<td>${book.id }</td>
				<td>${book.name}</td>
				<td>${book.author}</td>
				<td>${book.publisher}</td>
				<td>${book.category}</td>
				<td>${book.type}</td>
				<td>
					<c:forEach items="${libraries}" var="library">
						<c:if test="${book.libraryId ==library.id}">
							<option value="${library.id}">${library.name}</option>
						</c:if>
					</c:forEach>
				</td>
				<td>${book.shelfId}</td>
				<td>${book.publishedDate}</td>
				<td>
				<c:if test="${book.keeping ==1}">保管中</c:if>
				<c:if test="${book.lending ==1}">貸出中</c:if>
				<c:if test="${book.disposing ==1}">整理中</c:if>
				<td>
					<c:forEach items="${reservationCounts}" var="count" varStatus="statusCount">
						<c:if test="${statusBook.index == statusCount.index}">
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
		   	 		<input type = "submit" value = "貸出一覧" />
		   	 	</form>
	   	 	</td>


	   	 	<td>
		   	 	<form action = "reservation" method = "get">
		   	 		<input type = "hidden" name = "id" value = "${book.id}" >
		   	 		<input type = "submit" value = "予約一覧" />
		   	 	</form>
	   	 	</td>

	   	 	<td>
		   	 	<form action = "notReturned" method = "get">
		   	 		<input type = "hidden" name = "id" value = "${book.id}" >
		   	 		<input type = "submit" value = "未返却リスト" />
		   	 	</form>
	   	 	</td>


			</tr>

		</c:forEach>
	</table>
</body>
</html>