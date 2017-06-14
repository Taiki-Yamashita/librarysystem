<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ユーザー編集</title>
</head>
<body>
	<p>ユーザー編集</p>
	<p><a href="./manage">管理画面</a></p>
	<table>
		<tr><th>ID</th><th>ログインID</th><th>パスワード</th><th>名前</th><th>住所</th><th>TEL</th><th>メールアドレス</th><th>ポイント<th>登録日</th><th>図書館</th><th>停止</th></tr>
		<c:forEach items="${users}" var="user">
			<tr>
				<td>${user.id}</td>
				<td>${user.loginId}</td>
				<td>${user.password}</td>
				<td>${user.name}</td>
				<td>${user.address}</td>
				<td>${user.tel}</td>
				<td>${user.mail}</td>
				<td>${user.point}</td>
				<td>${user.registerDate}</td>
				<td>${user.libraryId}</td>



					<td>
	   	 	<form action="editUser" method="get">
	   	 		<input type="hidden" name="id" value="${user.id }" >
	   	 		<input type="submit" value="編集" />
	   	 	</form>
   	 	</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>