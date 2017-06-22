<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<c:forEach items="${books}" var="book">

			<td>${book.name}</td>

   	 		<td>
   	 			<form action = "admin/lendingBook" method = "post">
   	 				<input type = "hidden" id = "bookId" name = "bookId" value = "${book.id}" >
   	 				<input type = "hidden" id = "libraryId" name = "libraryId" value = "${book.libraryId}" >
						<input id = "userId" name = "userId"  >
						<input type = "hidden" name = "num" value = 1 >
						<input type = "hidden" name = "resetNum" value=1>
						<input type = "submit" value = "貸出" />

   	 			</form>
   	 		</td>
</c:forEach>
</body>
</html>