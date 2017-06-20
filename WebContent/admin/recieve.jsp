<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>問い合わせ、リクエスト受信</title>
</head>
<body>
	<a href = "manage">管理画面</a>
	<form action="recieve" method = "post">
		<label for = "num">未読表示</label>
		<input type="checkbox" name="num" value="0">

		<label for = "num">既読表示</label>
		<input type="checkbox" name="num" value="1">

		<input type="submit" value="絞込み" />
	</form>

	<form action="recieve" method = "post">

		<table>

			<tr>
				<th>ユーザー名</th>
				<th>書籍名</th>
				<th>著者</th>
				<th>出版社</th>
				<th>リクエスト日</th>
				<th>既読</th>
			</tr>

			<c:forEach items="${recieves}" var="recieve">
				<tr>
					<td><c:out value="${recieve.userName}" /></td>
					<td><c:out value="${recieve.bookName}" /></td>
					<td><c:out value="${recieve.author}" /></td>
					<td><c:out value="${recieve.publisher}" /></td>
					<td><c:out value="${recieve.requiredDate}" /></td>
					<td>
					<input type="checkbox" name="flag" value="1">
					<input type="hidden" name="id" value="${recieve.id}">
					</td>
				</tr>
			</c:forEach>


		</table>
		<input type="submit" value="送信" />
	</form>
</body>
</html>