<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>貸出用ページ</title>
</head>
<body>
	<a href = "./">トップ</a>
	<a href = "./search">検索</a>
	<a href = "./favorite">お気に入り</a>
	<a href = "./require">本のリクエスト</a>
	<a href = "./admin/manage">管理画面</a>
	<a href = "./user">マイページ</a>

<table>
		<tr>
			<th>書籍名</th>
			<th>状態</th>
			<th></th>
		</tr>
<c:forEach items="${books}" var="book">
	<c:forEach items="${circulations}" var="circulation">
		<c:if test="${circulation.returning == 1 && loginUser.id == circulation.userId}">
			<c:set var="flag" value="1"/>
		</c:if>
	</c:forEach>
			<tr>
				<td>${book.name}</td>
				<c:if test="${book.lending == 1 }">
				<td>貸出中</td>
				</c:if>
				<c:if test="${book.lending != 1 }">
				<td>貸出可</td>
				</c:if>

	   	 		<td>
	   	 			<c:if test="${book.lending == 1}">
		   	 			<form action="lendingBook" method = "post">
		   	 				<input type = "hidden" id = "bookId" name = "bookId" value = "${book.id}" >
		   	 				<input type = "hidden" id = "libraryId" name = "libraryId" value = "${book.libraryId}" >
							<input type ="hidden" name = "userId" value = "${loginUser.id }" >
							<input type = "hidden" name = "num" value = 0 >
							<input type = "submit" value = "返却" />
		   	 			</form>
	   	 			</c:if>

					<c:if test="${book.lending != 1 && empty flag }">
						<c:if test="${circulationSize<8 || circulationSize ==null}" >
		   	 			<form action = "lendingBook" method = "post">
		   	 				<input type = "hidden" id = "bookId" name = "bookId" value = "${book.id}" >
		   	 				<input type = "hidden" id = "libraryId" name = "libraryId" value = "${book.libraryId}" >
							<input type ="hidden" name = "userId" value = "${loginUser.id }" >
							<input type = "hidden" name = "num" value = 1 >
							<input type = "submit" value = "貸出" />
		   	 			</form>
		   	 			</c:if>
		   	 			<c:if test="${circulationSize>7}">貸出NG</c:if>
	   	 			</c:if>
	   	 			<c:if test="${book.lending != 1 && flag == '1'}">
	   	 				貸出NG
	   	 			</c:if>
	   	 		</td>
   	 		</tr>

	<c:remove var="return"/>
</c:forEach>
</table>
</body>
</html>