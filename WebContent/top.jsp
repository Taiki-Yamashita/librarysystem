<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>トップ</title>
	</head>
	<body>
		<h1>図書システム借りたいナ☆</h1>

		<table border="1">
			<tr><td><a href = "./search">検索</a></td></tr>
			<tr><td><a href = "./favorite">お気に入り</a></td></tr>
			<tr><td><a href = "./require">本のリクエスト</a></td></tr>
			<tr><td><a href = "./admin/manage">管理画面</a></td></tr>
			<tr><td><a href = "./introduction">本の紹介</a></td></tr>
			<tr><td><a href = "./user">マイページ</a></td></tr>
			<tr><td><a href = "./ranking">ランキング</a></td></tr>
			<tr><td><a href = "./login">ログアウト</a></td></tr>
		</table>

	<h5>お知らせ</h5>
		<c:forEach items="${informations}" var="information">

			<div class="libraryId"><c:out value="${information.libraryId}" /></div>
			<div class="registeredDate">投稿者:<c:out value="${information.registeredDate}" /></div>
			<div class="message">投稿:<c:out value="${information.message}" /></div>

		</c:forEach>













	</body>
</html>