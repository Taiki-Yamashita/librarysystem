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

	<table>
		<c:forEach items = "${circulations}" var="circulation">
			<c:out value = "${circulation.userName}" />
			<c:out value = "${circulation.bookName}" />
			<c:out value = "${circulation.libraryName}" />
			<c:out value = "${circulation.lentDate}" />
			<c:out value = "${circulation.limitedDate}" />
		</c:forEach>
	</table>
</body>
</html>