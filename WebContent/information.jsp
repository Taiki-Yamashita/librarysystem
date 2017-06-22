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
タイトル:<c:out value="${information.title}" /></br>
お知らせ内容:<c:out value="${information.message}" /></br>
<c:forEach items="${libraries}" var="library">
	<c:if test = "${library.id == information.libraryId}">
		図書館:<c:out value="${library.name}" /></br>
	</c:if>
</c:forEach>
投稿日時:<c:out value="${information.registeredDate}" />

</body>
</html>