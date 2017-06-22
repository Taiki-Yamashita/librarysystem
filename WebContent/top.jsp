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
			<tr><td><a href = "./logout">ログアウト</a></td></tr>
		</table>

	<h5>お知らせ</h5>
		<c:forEach items="${informations}" var="information">
			<a href="information?id=${information.id }"name="id"><c:out value="${information.title}"/></a>
			<div class="registeredDate">投稿日時:<c:out value="${information.registeredDate}" /></div>
			<div class="libraryId">
			<c:forEach items="${libraries}" var="library">
				<c:if test = "${library.id == information.libraryId}">
					図書館:<c:out value="${library.name}" /></br>
					-------------------------------------------------------------------------
				</c:if>
			</c:forEach>
			</div>
		</c:forEach>

	</body>
</html>