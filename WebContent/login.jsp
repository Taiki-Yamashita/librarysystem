<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="./css/styleOkada.css" rel="stylesheet" type="text/css">
		<link href="./css/styleTaiki.css" rel="stylesheet" type="text/css">
		<link href="./css/styleKuniyoshi.css" rel="stylesheet" type="text/css">
		<title>ログイン</title>
	</head>
	<body>
		<h1>図書システム借りたいナ☆</h1>

		<c:if test="${ not empty errorMessages }">
			<div class="errorMessages">
				<c:forEach items="${errorMessages}" var="message">
					<p><c:out value="${message}" /></p>
				</c:forEach>
			</div>
			<c:remove var="errorMessages" scope="session"/>
		</c:if>
		<div class="center">
			<form action="./login" method="POST" class="default">
				<table border="1" class="center">
					<tr><td>ログインID:<input name="loginId" type="text"/></td></tr>
					<tr><td>パスワード:<input name="password" type="password"/></td></tr>
				</table>
				<input type="submit"  value="ログイン" />
			</form>

			<table border="1" class="center">
				<tr>
					<td><a href = "./search">検索</a></td>
					<td><a href = "./ranking">ランキング</a></td>
				</tr>
			</table>
		</div>
	</body>
</html>