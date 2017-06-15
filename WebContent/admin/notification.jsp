<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>お知らせフォーム</title>
</head>
<body>
	<a href = "./manage">管理画面</a>


<form action = "notification"method = "post"><br />


	<label for = "libraryId">図書館</label><br>
	<input name = "libraryId" id = "libraryId" value = "libraryId"/><br />

	<label for = "registerDate">登校日</label><br>
	<input name = "registerDate" id = "registerDate" value = "registerDate"/><br />

	投稿(1000字まで)<br />
	<textarea name="message" id="message" cols="100" rows="5" ></textarea><br>

	<input type="submit" value="登録" />

	</form>
</body>
</html>