<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>お気に入り</title>
</head>
<body>
	<a href = "./">トップ</a>
	<a href = "./search">検索</a>
	<a href = "./require">本のリクエスト</a>
	<a href = "./admin/manage">管理画面</a>
	<a href = "./introduction">本の紹介</a>

	<table>
		<c:forEach items="${favorites}" var="favorite">
			<c:if test = "${user.id == favorite.user_id }">
				<tr>
					<td><c:out value="${favorite.userName}" /></td>
					<td><c:out value="${favorite.bookName}" /></td>
					<td><c:out value="${favorite.author}" /></td>
				</tr>
			</c:if>
		</c:forEach>
	</table>
</body>
</html>