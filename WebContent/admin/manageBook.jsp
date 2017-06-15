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
	<p>本の編集</p>
	<p><a href="./manage">管理画面</a></p>
	<table>
		<tr><th>名前</th><th>著者</th><th>出版社</th><th>カテゴリ</th><th>種類</th><th>図書館</th><th>棚番号</th><th>出版日</th><th>保管中<th>貸出中</th><th>予約中</th><th>整理中</th></tr>
		<c:forEach items="${books}" var="book">
			<tr>
				<td>${book.name}</td>
				<td>${book.author}</td>
				<td>${book.publisher}</td>
				<td>${book.category}</td>
				<td>${book.type}</td>
				<td>${book.libraryId}</td>
				<td>${book.shelfId}</td>
				<td>${book.publishedDate}</td>
				<td>${book.keeping}</td>
				<td>${book.lending}</td>
				<td>${book.reserving}</td>
				<td>${book.disposing}</td>

					<td>
	   	 	<form action="editBook" method="get">
	   	 		<input type="hidden" name="id" value="${book.id }" >
	   	 		<input type="submit" value="編集" />
	   	 	</form>

   	 		<td>
   	 			<form action="lendingBook" method="post">
   	 				<input type="hidden" name="id" value="${book.id }" >
					<c:if test="${book.lending == 0 }">
						<input type="hidden" name="num" value=1 >
						<input type="submit" value="貸出" />
					</c:if>
					<c:if test="${book.lending == 1 }">
						<input type="hidden" name="num" value=0>
						<input type="submit" value="返却" />
					</c:if>
   	 			</form>
   	 		</td>


			</tr>
		</c:forEach>
	</table>
</body>
</html>