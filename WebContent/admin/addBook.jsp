<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	<input name = "name" id = "name" value = "name"/><br />

	<label for = "author">著者</label><br>
	<input name = "author" id = "author" value = "author"/><br />

	<label for = "publisher">出版社</label><br>
	<input name = "publisher" id = "publisher"/><br />

	<label for = "category">カテゴリ</label><br>
	<input name = "category" id = "category" value = "category"/><br />

	<label for = "type">タイプ</label><br>
	<input name = "type" id = "type" value = "type"/><br />

	<label for = "libraryId">図書館</label><br>
	<input name = "libraryId" id = "libraryId" value = "libraryId"/><br />

	<label for="shelfId">棚番号</label><br>
	<input name = "shelfId" id = "shelfId" value = "shelfId"/><br />

	<label for = "isbnId">ISBN番号</label><br>
	<input name = "isbnId" id = "isbnId"/><br />

	<label for = "publishedDate">出版日</label><br>
	<input name = "publishedDate" id = "publishedDate"/><br />



	<input type="submit" value="登録" />

	</form>
</body>
</html>