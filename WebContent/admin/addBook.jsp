<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>本登録</title>
</head>
<body>
	<br>
	<a href = "./manage">管理画面</a>

<form action = "./addBook"method = "post"><br />
	<label for = "name">名前</label><br>
	<input name = "name" id = "name" value = "${newBook.name }"/><br />

	<label for = "author">著者</label><br>
	<input name = "author" id = "author" value = "${newBook.author }"/><br />

	<label for = "publisher">出版社</label><br>
	<input name = "publisher" id = "publisher" value = "${newBook.publisher }"/><br />

	<label for = "category">カテゴリ</label><br>
	<input name = "category" id = "category" value = "${newBook.category }"/><br />

	<label for = "type">タイプ</label><br>
	<input name = "type" id = "type" value = "${newBook.type }"/><br />

	<label for = "library">図書館</label><br>
	<select name="libraryId">
		<option value="0">選択してください</option>
			<c:forEach items="${libraries}" var="library">
			<c:if test="${newBook.libraryId == library.id }">
					<option selected value="${library.id}">${library.name } </option>
				</c:if>
				<c:if test="${newBook.libraryId != library.id }">
					<option  value="${library.id}">${library.name } </option>
				</c:if>
		</c:forEach>
	</select><br>

	<label for="shelfId">棚番号</label><br>
	<input name = "shelfId" id = "shelfId" value = "${newBook.shelfId }"/><br />

	<label for = "isbnId">ISBN番号</label><br>
	<input name = "isbnId" id = "isbnId" value = "${newBook.isbnId }"/><br />

	<label for = "publishedDate">出版日</label><br>
	<input name = "publishedDate" id = "publishedDate" value ="${newBook.publishedDate }"/><br />



	<input type="submit" value="登録" />

	</form>
</body>
</html>