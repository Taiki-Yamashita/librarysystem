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
	<a href = "./">トップ</a>
	<table>
		<c:forEach items="${recieves}" var="recieve">
			<c:out value="${recieve.userName}" />
			<c:out value="${recieve.bookName}" />
			<c:out value="${recieve.author}" />
			<c:out value="${recieve.publisher}" />
			<c:out value="${recieve.requiredDate}" />
		</c:forEach>
	</table>
</body>
</html>