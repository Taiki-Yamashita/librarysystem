<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>貸し出し管理</title>
</head>
<body>
	<a href="manage">管理画面</a>
	<form action="circulation" method="post">
		<table>
			<tr>
				<th>ユーザー名</th>
				<th>書籍名</th>
				<th>最寄り図書館</th>
				<th>貸した日</th>
				<th>期限</th>
			</tr>
			<c:forEach items = "${circulations}" var="circulation">
				<tr>
					<td><c:out value = "${circulation.userName}" /></td>
					<td><c:out value = "${circulation.bookName}" /></td>
					<td><c:out value = "${circulation.libraryName}" /></td>
					<td><c:out value = "${circulation.lentDate}" /></td>
					<td><c:out value = "${circulation.limitedDate}" /></td>
					<td>
					<input type="hidden" name="lending" value="${circulation.id}"/>
					<input type="button" value="返却"/>
					</td>
				</tr>
			</c:forEach>
		</table>
	</form>
</body>
</html>