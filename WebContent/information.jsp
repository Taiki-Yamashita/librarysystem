<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>お知らせ</title>
</head>
<body>
<table>
	<tr>
		<td><h1>タイトル:<c:out value="${information.title}" /></h1></td>
	</tr>
	<tr>
		<td>
			<c:forEach items="${libraries}" var="library">
				<c:if test = "${library.id == information.libraryId}">
					<h2>『<c:out value="${library.name}" />』図書館からのお知らせだよ～～～</h2>
				</c:if>
			</c:forEach>
		</td>
	</tr>
	<tr>
		<td>
			投稿日時:
			<fmt:parseDate var="date" value="${information.registeredDate}" pattern="yyyy-MM-dd HH:mm:ss" />
			<fmt:formatDate pattern = "yyyy年MM月dd日" value = "${date}" />
		</td>
	</tr>
	<tr>
		<td>お知らせ内容:<c:out value="${information.message}" /></td>
	</tr>
</table>





</body>
</html>