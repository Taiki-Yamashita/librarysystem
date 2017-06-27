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
	<a href="manageBook">本の情報一覧</a>
	<c:if test="${not empty circulation }">
	<form action="circulation" method="post">
		<table>
			<tr>
				<th>ユーザー名</th>
				<th>書籍名</th>
				<th>最寄り図書館</th>
				<th>貸した日</th>
				<th>期限</th>
				<th></th>
			</tr>
			<c:forEach items = "${circulations}" var="circulation">
				<tr>
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
					<td>
						<c:if test="${circulation.returning ==1}">
							<c:out value="遅延中"></c:out>
						</c:if>

				</tr>
			</c:forEach>
		</table>
	</form>
	</c:if>
	<c:if test="${empty circulation}">
	<br>貸出履歴がありません
	</c:if>
</body>
</html>