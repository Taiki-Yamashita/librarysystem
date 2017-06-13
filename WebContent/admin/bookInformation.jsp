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
		<tr><th>名前</th><th>著者</th><th>出版社</th><th>カテゴリ</th></tr>
		<c:forEach items="${books}" var="book">
			<tr>
				<td>${book.name}</td>
				<td>${book.author}</td>
				<td>${book.publisher}</td>
				<td>${book.category}</td>
					<td>
	   	 	<form action="editBooks" method="get">
	   	 		<input type="hidden" name="id" value="${book.id }" >
	   	 		<input type="submit" value="編集" />
	   	 	</form>
   	 	</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>