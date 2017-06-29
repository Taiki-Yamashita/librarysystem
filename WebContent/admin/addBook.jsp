<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>本登録</title>
<link href="../css/styleOkada.css" rel="stylesheet" type="text/css">
<link href="../css/styleTaiki.css" rel="stylesheet" type="text/css">
<link href="../css/styleKuniyoshi.css" rel="stylesheet" type="text/css">
</head>

<body class="admin">
<p><a href = "./manageBook">本の情報管理</a></p>
	<h1>図書システム借りたいナ☆</h1>
	<h2>ユーザー登録画面</h2>
<c:if test="${ not empty errorMessages }">
	<div class="errorMessages">
		<c:forEach items="${errorMessages}" var="message">
			<p><c:out value="${message}" /></p>
		</c:forEach>
	</div>
	<c:remove var="errorMessages" scope="session"/>
</c:if>
<div class=addBookText></div>
<form class="addBookForm" action = "./addBook"method = "post" name="register"><br />
	<label for = "name">名前</label>*必須<br>
	<input name = "name" id = "name" value = "${newBook.name }"/><br />

	<label for = "author">著者</label>*必須<br>
	<input name = "author" id = "author" value = "${newBook.author }"/><br />

	<label for = "publisher">出版社</label>*必須<br>
	<input name = "publisher" id = "publisher" value = "${newBook.publisher }"/><br />
	<label for = "category">カテゴリ</label>*必須<br>
						<%
							String[] checkBoxCategoryNumbers = {"1","2","3","4","5","6","7","8","9"};
							session.setAttribute("checkBoxCategoryNumbers", checkBoxCategoryNumbers);
						%>
						<c:forEach items="${checkBoxCategoryNumbers}" var="checkBoxCategoryNumber">
							<%session.setAttribute("checkCategory", 0);%>
							<c:forEach items="${categories}" var="category">
								<c:if test="${category == '文学' && checkBoxCategoryNumber == 1}">
									<%session.setAttribute("checkCategory",1);%>
									<input type="radio" name="category" value="1"  >文学
								</c:if>
								<c:if test="${category == '経済' && checkBoxCategoryNumber == 2}">
									<%session.setAttribute("checkCategory",1);%>
									<input type="radio" name="category" value="2" >経済
								</c:if>
								<c:if test="${category == '芸能' && checkBoxCategoryNumber == 3}">
									<%session.setAttribute("checkCategory",1);%>
									<input type="radio" name="category" value="3" >芸能
								</c:if>
								<c:if test="${category == '歴史' && checkBoxCategoryNumber == 4}">
									<%session.setAttribute("checkCategory",1);%>
									<input type="radio" name="category" value="4" >歴史
								</c:if>
								<c:if test="${category == '学問' && checkBoxCategoryNumber == 5}">
									<%session.setAttribute("checkCategory",1);%>
									<input type="radio" name="category" value="5" >学問
								</c:if>
								<c:if test="${category == '政治' && checkBoxCategoryNumber == 6}">
									<%session.setAttribute("checkCategory",1);%>
									<input type="radio" name="category" value="6" >政治
								</c:if>
								<c:if test="${category == '暮らし' && checkBoxCategoryNumber == 7}">
									<%session.setAttribute("checkCategory",1);%>
									<input type="radio" name="category" value="7" >暮らし
								</c:if>
								<c:if test="${category == '教育' && checkBoxCategoryNumber == 8}">
									<%session.setAttribute("checkCategory",1);%>
									<input type="radio" name="category" value="8" >教育
								</c:if>
								<c:if test="${category == 'SF' && checkBoxCategoryNumber == 9}">
									<%session.setAttribute("checkCategory",1);%>
									<input type="radio" name="category" value="9" >SF
								</c:if>
							</c:forEach>
							<c:if test="${checkCategory == 0 && checkBoxCategoryNumber == 1}">
								<input type="radio" name="category" value="1" >文学
							</c:if>
							<c:if test="${checkCategory == 0 && checkBoxCategoryNumber == 2}">
								<input type="radio" name="category" value="2">経済
							</c:if>
							<c:if test="${checkCategory == 0 && checkBoxCategoryNumber == 3}">
								<input type="radio" name="category" value="3">芸能
							</c:if>
							<c:if test="${checkCategory == 0 && checkBoxCategoryNumber == 4}">
								<input type="radio" name="category" value="4">歴史
							</c:if>
							<c:if test="${checkCategory == 0 && checkBoxCategoryNumber == 5}">
								<input type="radio" name="category" value="5">学問
							</c:if>
							<c:if test="${checkCategory == 0 && checkBoxCategoryNumber == 6}">
								<input type="radio" name="category" value="6">政治
							</c:if>
							<c:if test="${checkCategory == 0 && checkBoxCategoryNumber == 7}">
								<input type="radio" name="category" value="7">暮らし
							</c:if>
							<c:if test="${checkCategory == 0 && checkBoxCategoryNumber == 8}">
								<input type="radio" name="category" value="8">教育
							</c:if>
							<c:if test="${checkCategory == 0 && checkBoxCategoryNumber == 9}">
								<input type="radio" name="category" value="9">SF
							</c:if>
						</c:forEach><br>
	<label for = "type">種類</label>*必須<br>
				<%
					String[] checkBoxTypeNumbers = {"1","2","3","4"};
					session.setAttribute("checkBoxTypeNumbers", checkBoxTypeNumbers);
				%>
						<c:forEach items="${checkBoxTypeNumbers}" var="checkBoxTypeNumber">
							<%session.setAttribute("checkType", 0);%>
							<c:forEach items="${types}" var="type">
								<c:if test="${type == '文庫' && checkBoxTypeNumber == 1}">
									<%session.setAttribute("checkType",1);%>
									<input type="radio" name="type" value="1">文庫
								</c:if>
								<c:if test="${type == '新書' && checkBoxTypeNumber == 2}">
									<%session.setAttribute("checkType",1);%>
									<input type="radio" name="type" value="2">新書
								</c:if>
								<c:if test="${type == '雑誌' && checkBoxTypeNumber == 3}">
									<%session.setAttribute("checkType",1);%>
									<input type="radio" name="type" value="3" >雑誌
								</c:if>
								<c:if test="${type == 'コミックス' && checkBoxTypeNumber == 4}">
									<%session.setAttribute("checkType",1);%>
									<input type="radio" name="type" value="4">コミックス
								</c:if>
							</c:forEach>

							<c:if test="${checkType == 0 && checkBoxTypeNumber == 1}">
								<input type="radio" name="type" value="1">文庫
							</c:if>
							<c:if test="${checkType == 0 && checkBoxTypeNumber == 2}">
								<input type="radio" name="type" value="2">新書
							</c:if>
							<c:if test="${checkType == 0 && checkBoxTypeNumber == 3}">
								<input type="radio" name="type" value="3">雑誌
							</c:if>
							<c:if test="${checkType == 0 && checkBoxTypeNumber == 4}">
								<input type="radio" name="type" value="4">コミックス
							</c:if>
						</c:forEach><br>
	<label for = "library">図書館</label>*必須<br>
				<%
					String[] checkBoxLibraryNumbers = {"1","2","3","4","5"};
					session.setAttribute("checkBoxLibraryNumbers", checkBoxLibraryNumbers);
				%>
						<c:forEach items="${checkBoxLibraryNumbers}" var="checkBoxLibraryNumber">
							<%session.setAttribute("checkLibrary", 0);%>
							<c:forEach items="${libraries}" var="library">
								<c:if test="${library == '西馬込' && checkBoxLibraryNumber == 1}">
									<%session.setAttribute("checkLibrary",1);%>
									<input type="radio" name="libraryId" value="1">西馬込
								</c:if>
								<c:if test="${library == '馬込' && checkBoxLibraryNumber == 2}">
									<%session.setAttribute("checkLibrary",1);%>
									<input type="radio" name="libraryId" value="2">馬込
								</c:if>
								<c:if test="${library == '中延' && checkBoxLibraryNumber == 3}">
									<%session.setAttribute("checkLibrary",1);%>
									<input type="radio" name="libraryId" value="3" >中延
								</c:if>
								<c:if test="${library == '戸越' && checkBoxLibraryNumber == 4}">
									<%session.setAttribute("checkLibrary",1);%>
									<input type="radio" name="libraryId" value="4" >戸越
								</c:if>
								<c:if test="${library == '五反田' && checkBoxLibraryNumber == 5}">
									<%session.setAttribute("checkLibraryId",1);%>
								<input type="radio" name="library" value="5" >五反田
								</c:if>

							</c:forEach>

							<c:if test="${checkLibrary == 0 && checkBoxLibraryNumber == 1}">
								<input type="radio" name="libraryId" value="1">西馬込
							</c:if>
							<c:if test="${checkLibrary == 0 && checkBoxLibraryNumber == 2}">
								<input type="radio" name="libraryId" value="2">馬込
							</c:if>
							<c:if test="${checkLibrary == 0 && checkBoxLibraryNumber == 3}">
								<input type="radio" name="libraryId" value="3">中延
							</c:if>
							<c:if test="${checkLibrary == 0 && checkBoxLibraryNumber == 4}">
								<input type="radio" name="libraryId" value="4">戸越
							</c:if>
							<c:if test="${checkLibrary == 0 && checkBoxLibraryNumber == 5}">
								<input type="radio" name="libraryId" value="5">五反田
							</c:if>
						</c:forEach><br>

	<label for="shelfId">棚番号</label>*必須<br>
	<input name = "shelfId" id = "shelfId" value = "${newBook.shelfId }"/><br />

	<label for = "isbnId">ISBN番号</label>*必須<br>
	<input name = "isbnId" id = "isbnId" value = "${newBook.isbnId }"/><br />

	<label for = "publishedDate">出版日</label>*必須<br>
	<input name = "publishedDate" id = "publishedDate" value ="${publishedDate }"/>年
	<input name = "publishedDate2" id = "publishedDate2" value ="${publishedDate2 }"/>月
	<input name = "publishedDate3" id = "publishedDate3" value ="${publishedDate3 }"/>日<br>
	<input class ="register" type="submit" value="登録" />
	</form>
</body>
</html>