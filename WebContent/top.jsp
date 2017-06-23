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
		<c:if test="${ not empty errorMessages }">
			<div class="errorMessages">
				<ul>
					<c:forEach items="${errorMessages}" var="message">
						<li><c:out value="${message}" />
						</c:forEach>
						</ul>
			</div>
			<c:remove var="errorMessages" scope="session"/>
		</c:if>
		<table border="1">
			<tr><td><a href = "./search">検索</a></td><td><a href = "./ranking">ランキング</a></td><c:if test="loginUser.id !=null"></c:if>
			</tr></table>

		<h4>登録者メニュー</h4>
		<table border="1">
			<c:if test="${loginUser.id ==null}">
			<tr><td><a href = "./login">ログイン</a></td></tr>
			<tr><td>ユーザーページ</td></tr>
			<tr><td>お気に入り</td></tr>
			<tr><td>本のリクエスト</td></tr>
			</c:if>
			<c:if test="${loginUser.id !=null}">
			<tr><td><a href = "./user">マイページ</a></td></tr>
			<tr><td><a href="renewPassword?id=${loginUser.id}" name="id">パスワード編集</a>*ログイン必須</td></tr>
			<tr><td><a href = "./favorite">お気に入り</a>*ログイン必須</td></tr>
			<tr><td><a href = "./require">本のリクエスト</a>*ログイン必須</td></tr>
			<tr><td><a href = "./logout">ログアウト</a></td></tr>
			</c:if>
		</table>
			<a href = "./admin/manage">管理画面</a>

	<h5>お知らせ</h5>
		<c:forEach items="${informations}" var="information">
			<a href="information?id=${information.id }"name="id"><c:out value="${information.title}"/></a>
			<div class="registeredDate">投稿日時:
			<fmt:parseDate var="date" value="${information.registeredDate}" pattern="yyyy-MM-dd HH:mm:ss" />
			<fmt:formatDate pattern = "yyyy年MM月dd日" value = "${date}" />
			</div>
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