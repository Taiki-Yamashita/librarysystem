<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>未返却リスト</title>
</head>
<body>
未返却リストだよ

	<table>
		<c:forEach items="${notReturnedlists}" var="notReturnedlist">
			<c:out value="${notReturnedlist.userName}" />
			<c:out value="${notReturnedlist.bookName}" />
			<c:out value="${notReturnedlist.libraryName}" />
		</c:forEach>
	</table>
</body>
</html>
