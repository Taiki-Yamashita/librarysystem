<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>未返却リスト</title>
<link href="../css/styleOkada.css" rel="stylesheet" type="text/css">
<link href="../css/styleTaiki.css" rel="stylesheet" type="text/css">
<link href="../css/styleKuniyoshi.css" rel="stylesheet" type="text/css">
</head>
<body>
未返却リストだよ
<a href = "manage">管理画面</a>
	<form action="notreturn" method="post">
		<table>
			<tr>
				<th>ユーザー名</th>
				<th>書籍名</th>
				<th>最寄り図書館</th>
				<th>オーバー期限</th>
				<th>返却</th>
			</tr>
			<c:forEach items="${notReturnedlists}" var="notReturnedlist">
				<tr>
					<td><c:out value="${notReturnedlist.userName}" /></td>
					<td><c:out value="${notReturnedlist.bookName}" /></td>
					<td><c:out value="${notReturnedlist.libraryName}" /></td>
					<td><c:out value="${notReturnedlist.limitedDate}" /></td>
					<td>
					<input type="hidden" name="lending" value="${notReturned.id}"/>
					<input type="button" value="返却"/>>
					</td>
				</tr>
			</c:forEach>
		</table>
	</form>
</body>
</html>
