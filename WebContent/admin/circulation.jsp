<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="../css/styleOkada.css" rel="stylesheet" type="text/css">
<link href="../css/styleTaiki.css" rel="stylesheet" type="text/css">
<link href="../css/styleKuniyoshi.css" rel="stylesheet" type="text/css">
<title>貸し出し管理</title>
</head>
<body>
	<p><a href="manageBook">本の情報一覧</a></p>
	<h1>貸出履歴</h1>
	<c:if test="${not empty circulations }">
	<form action="circulation" method="post">
		<table border="2" class="manage">
			<tr>
				<th>ユーザー名</th>
				<th>書籍名</th>
				<th>最寄り図書館</th>
				<th>貸した日</th>
				<th>期限</th>
				<th></th>
			</tr>
			<tr>
				<c:forEach items = "${circulations}" var="circulation">
					<td><c:out value = "${circulation.userName}" /></td>
					<td><c:out value = "${circulation.bookName}" /></td>
					<td><c:out value = "${circulation.libraryName}" /></td>
					<td>
						<fmt:parseDate var="date" value="${circulation.lentDate}" pattern="yyyy-MM-dd HH:mm:ss" />
						<fmt:formatDate pattern = "yyyy年MM月dd日" value = "${date}" />
					</td>
					<td>
					<fmt:parseDate var="date" value="${circulation.limitedDate}" pattern="yyyy-MM-dd HH:mm:ss" />
					<fmt:formatDate pattern = "yyyy年MM月dd日" value = "${date}" />
					</td>
					<td>
						<c:if test="${circulation.returning ==1}">
							<c:out value="遅延中"></c:out>
						</c:if>
					</td>
			</c:forEach>
			</tr>
		</table>
	</form>
	</c:if>
	<c:if test="${empty circulations}">
	<br>貸出履歴がありません
	</c:if>
</body>
</html>