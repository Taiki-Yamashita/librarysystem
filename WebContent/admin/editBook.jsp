<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>本の編集</title>
<link href="css/style.css" rel="stylesheet" type="text/css">
</head>
<body>


<form action="editBook"method="post"><br />

	<input name="book_id" type="hidden" value="${editBook.id}"/>

	<label for="name">名前</label><br>
	<input name="name" id="name" value="${editBook.name }"/><br />

	<label for="author">著者</label><br>
	<input name="author" id="author" value="${editBook.author }"/><br />

	<label for="publisher">出版社</label><br>
	<input name="publisher" id="publisher" value ="${editBook.publisher }"/><br />

	<label for = "category">カテゴリ</label><br>
	<c:if test="${editBook.category == '文学'}"><input type="radio" name="category" value="1" checked >文学</c:if>
	<c:if test="${editBook.category != '文学'}"><input type="radio" name="category" value="1">文学</c:if>
	<c:if test="${editBook.category == '経済'}"><input type="radio" name="category" value="2" checked >経済</c:if>
	<c:if test="${editBook.category != '経済'}"><input type="radio" name="category" value="2">経済</c:if>
	<c:if test="${editBook.category == '芸能'}"><input type="radio" name="category" value="3" checked >芸能</c:if>
	<c:if test="${editBook.category != '芸能'}"><input type="radio" name="category" value="3">芸能</c:if>
	<c:if test="${editBook.category == '歴史'}"><input type="radio" name="category" value="4" checked >歴史</c:if>
	<c:if test="${editBook.category != '歴史'}"><input type="radio" name="category" value="4">歴史</c:if>
	<c:if test="${editBook.category == '学問'}"><input type="radio" name="category" value="5" checked >学問</c:if>
	<c:if test="${editBook.category != '学問'}"><input type="radio" name="category" value="5">学問</c:if>
	<c:if test="${editBook.category == '政治'}"><input type="radio" name="category" value="6" checked >政治</c:if>
	<c:if test="${editBook.category != '政治'}"><input type="radio" name="category" value="6">政治</c:if>
	<c:if test="${editBook.category == '暮らし'}"><input type="radio" name="category" value="7" checked >暮らし</c:if>
	<c:if test="${editBook.category != '暮らし'}"><input type="radio" name="category" value="7">暮らし</c:if>
	<c:if test="${editBook.category == '教育'}"><input type="radio" name="category" value="8" checked >教育</c:if>
	<c:if test="${editBook.category != '教育'}"><input type="radio" name="category" value="8">教育</c:if>
	<c:if test="${editBook.category == 'SF'}"><input type="radio" name="category" value="9" checked >SF</c:if>
	<c:if test="${editBook.category != 'SF'}"><input type="radio" name="category" value="9">SF</c:if>
	<br>
	<label for = "type">タイプ</label><br>
	<c:if test="${editBook.type == '文庫'}"><input type="radio" name="type" value="1" checked >文庫</c:if>
	<c:if test="${editBook.type != '文庫'}"><input type="radio" name="type" value="1">文庫</c:if>
	<c:if test="${editBook.type == '新書'}"><input type="radio" name="type" value="2" checked >新書</c:if>
	<c:if test="${editBook.type != '新書'}"><input type="radio" name="type" value="2">新書</c:if>
	<c:if test="${editBook.type == '雑誌'}"><input type="radio" name="type" value="3" checked >雑誌</c:if>
	<c:if test="${editBook.type != '雑誌'}"><input type="radio" name="type" value="3">雑誌</c:if>
	<c:if test="${editBook.type == 'コミック'}"><input type="radio" name="type" value="4" checked >コミック</c:if>
	<c:if test="${editBook.type != 'コミック'}"><input type="radio" name="type" value="4">コミック</c:if>
	<br>
	<label for = "library">図書館</label><br>
	<c:if test="${editBook.libraryId == '1'}"><input type="radio" name="libraryId" value="1" checked >西馬込</c:if>
	<c:if test="${editBook.libraryId != '1'}"><input type="radio" name="libraryId" value="1">西馬込</c:if>
	<c:if test="${editBook.libraryId == '2'}"><input type="radio" name="libraryId" value="1" checked >馬込</c:if>
	<c:if test="${editBook.libraryId != '2'}"><input type="radio" name="libraryId" value="1">馬込</c:if>
	<c:if test="${editBook.libraryId == '3'}"><input type="radio" name="libraryId" value="1" checked >中延</c:if>
	<c:if test="${editBook.libraryId != '3'}"><input type="radio" name="libraryId" value="1">中延</c:if>
	<c:if test="${editBook.libraryId == '4'}"><input type="radio" name="libraryId" value="1" checked >戸越</c:if>
	<c:if test="${editBook.libraryId != '4'}"><input type="radio" name="libraryId" value="1">戸越</c:if>
	<c:if test="${editBook.libraryId == '5'}"><input type="radio" name="libraryId" value="1" checked >五反田</c:if>
	<c:if test="${editBook.libraryId != '5'}"><input type="radio" name="libraryId" value="1">五反田</c:if>

	<br>
	<label for="shelfId">棚番号</label><br>
	<input name="shelfId" id="shelfId" value="${editBook.shelfId }"/><br />

	<label for="isbnId">ISBN番号</label><br>
	<input name="isbnId" id="isbnId" value="${editBook.isbnId }"/><br />

	<label for="publishedDate">出版日</label><br>
	<input name="publishedDate" id="publishedDate" value="${editBook.publishedDate }"/><br />


	<label for = "status">本の状態</label><br>
				<%
					String[] checkBoxStatusNumbers = {"1","2","3"};
					session.setAttribute("checkBoxStatusNumbers", checkBoxStatusNumbers);
				%>
						<c:forEach items="${checkBoxStatusNumbers}" var="checkBoxStatusNumber">
							<%session.setAttribute("checkStatus", 0);%>
							<c:forEach items="${status}" var="status">
								<c:if test="${status == '保管中' && checkBoxStatusNumber == 1}">
									<%session.setAttribute("checkStatus",1);%>
									<input type="radio" name="status" value="1">保管中
								</c:if>
								<c:if test="${status == '貸出中' && checkBoxStatusNumber == 2}">
									<%session.setAttribute("checkStatus",1);%>
									<input type="radio" name="status" value="2">貸出中
								</c:if>
								<c:if test="${status == '整理中' && checkBoxStatusNumber == 3}">
									<%session.setAttribute("checkStatus",1);%>
									<input type="radio" name="status" value="3" >整理中
								</c:if>
							</c:forEach>

							<c:if test="${checkStatus == 0 && checkBoxStatusNumber == 1}">
								<input type="radio" name="status" value="1">保管中
							</c:if>
							<c:if test="${checkStatus == 0 && checkBoxStatusNumber == 2}">
								<input type="radio" name="status" value="2">貸出中
							</c:if>
							<c:if test="${checkStatus == 0 && checkBoxStatusNumber == 3}">
								<input type="radio" name="status" value="3">整理中
							</c:if>

						</c:forEach><br>




	<br><input type="submit" value="投稿">
</form>

		<c:remove var="checkBoxLibraryNumber" scope="session"/>
		<c:remove var="checkLibrary" scope="session"/>
		<c:remove var="checkBoxCategoryNumber" scope="session"/>
		<c:remove var="checkCategory" scope="session"/>
		<c:remove var="checkBoxTypeNumber" scope="session"/>
		<c:remove var="checkType" scope="session"/>
		<c:remove var="checkBoxStatusNumber" scope="session"/>
		<c:remove var="checkStatus" scope="session"/>

</body>
</html>